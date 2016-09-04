package org.appdynamics.handpover.rest;

//import com.sun.jersey.api.client.filter.LoggingFilter;
import org.apache.commons.codec.binary.Base64;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.NewCookie;
import java.util.List;
import org.appdynamics.handpover.config.Globals;

/**
 * Created by michi on 15.08.16.
 */
public class Auth {

    static NewCookie authCookie;
    static NewCookie sessionCookie;

    public void doAuth( String user, String password, String account) throws Exception {

        String auth = user + Globals.AT + account + Globals.COLON + password;
        String encodedAuth = new String(Base64.encodeBase64(auth.getBytes()));
        String loginUrl = Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_LOGIN;

        Client client = Client.create();
        //client.addFilter(new LoggingFilter(System.out));
        WebResource resource = client.resource(loginUrl);
        ClientResponse response =   resource.header(Globals.REST_AUTH_KEY, Globals.REST_AUTH_METHOD + encodedAuth).get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException(Globals.ERROR_HTTP + response.getStatus());
        }

        List<NewCookie> cookies = response.getCookies();

        for (NewCookie c : cookies) {
            if (c.toCookie().getName().equals(Globals.COOKIE_CSRF)) {
                authCookie = c;
            } else if (c.toCookie().getName().equals(Globals.COOKIE_JSESSION)) {
                sessionCookie = c;
            }
        }

        CheckPermission.doCheckPermission();
    }
}
