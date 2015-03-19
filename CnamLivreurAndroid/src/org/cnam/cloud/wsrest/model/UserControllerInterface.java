package org.cnam.cloud.wsrest.model;

import org.cnam.cloud.wsrest.model.User;
import org.restlet.resource.Get;
import org.restlet.resource.Put;



public interface UserControllerInterface {
    @Put
    void create(User user);
    
    @Get
    Container getAllUsers();
}
