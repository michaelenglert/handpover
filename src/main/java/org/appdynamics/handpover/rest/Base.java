package org.appdynamics.handpover.rest;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.appdynamics.handpover.config.Globals;
//import com.sun.jersey.api.client.filter.LoggingFilter;

/**
 * Created by michi on 28.08.16.
 */
class Base {
    static ClientResponse getClientResponse(String url) throws Exception {
        Client client = Client.create();
        //client.addFilter(new LoggingFilter(System.out));
        WebResource resource = client.resource(url);

        ClientResponse response =   resource.cookie(Auth.authCookie).cookie(Auth.sessionCookie).get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException(Globals.ERROR_HTTP + response.getStatus());
        }

        return response;
    }
}
