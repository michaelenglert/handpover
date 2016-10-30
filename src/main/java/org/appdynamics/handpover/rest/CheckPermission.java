package org.appdynamics.handpover.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import org.appdynamics.handpover.config.Globals;
import org.appdynamics.handpover.json.Roles;
import org.appdynamics.handpover.json.User;

import java.util.List;


/**
 * Created by michi on 03.09.16.
 */
@SuppressWarnings("WeakerAccess")
public class CheckPermission {

    public static void doCheckPermission() throws Exception {
        ClientResponse response = Base.getClientResponse(Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_GET_USER);
        String output = response.getEntity(String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        User currentUser = objectMapper.readValue(output, User.class);
        List<Roles> roleList = currentUser.getRoles();

        int hasAdmin = 0;

        for (Roles role : roleList ) {
            if (role.getName().contains(Globals.CONTROLLER_ADMIN)){
                hasAdmin++;
            }
        }

        if (hasAdmin == 0){
            throw new RuntimeException(Globals.ERROR_NO_ADMIN);
        }
    }
}
