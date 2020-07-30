package com.accenture.webService.rest;

import com.accenture.be.access.UserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/check-login")
@Service
public class LoginCheckService {
    @Autowired
    private UserAccessService userAccessService;

    @GET
    @Path("/{login}")
    public boolean checkLogin(@PathParam("login") String login){
        return userAccessService.get(login).isPresent();
    }
}
