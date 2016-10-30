package org.appdynamics.handpover.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.appdynamics.handpover.config.Globals;

import javax.ws.rs.core.NewCookie;
import java.util.List;

/**
 * Created by michi on 28.08.16.
 */
@SuppressWarnings("WeakerAccess")
public class Base {
    public static NewCookie authCookie;
    public static NewCookie sessionCookie;

    public void doAuth( String user, String password, String account) throws Exception {

        String auth = user + Globals.AT + account + Globals.COLON + password;
        String encodedAuth = new String(Base64.encodeBase64(auth.getBytes()));
        String loginUrl = Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_LOGIN;

        ClientResponse response =   getWebResource(loginUrl).header(Globals.REST_AUTH_KEY, Globals.REST_AUTH_METHOD + encodedAuth).get(ClientResponse.class);

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

    static ClientResponse getClientResponse(String url) throws Exception{



        ClientResponse response = getWebResource(url).cookie(Base.authCookie).cookie(Base.sessionCookie).get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException(Globals.ERROR_HTTP + response.getStatus());
        }

        return response;
    }

    static WebResource getWebResource(String url) throws Exception{
        Client client = Client.create();

        if (url.startsWith(Globals.CONTROLLER_HTTPS)) {
            final ClientConfig config = new DefaultClientConfig();
            config.getProperties()
                    .put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
                            new HTTPSProperties(
                                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER,
                                    SslClientHelper.SSLUtil.getInsecureSSLContext()));
            client = Client.create(config);
        }

        return client.resource(url);
    }
}
