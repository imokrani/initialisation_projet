package org.cnam.cloud.wsrest.server;

import java.util.ArrayList;
import java.util.List;

import org.cnam.cloud.wsrest.model.User;
import org.restlet.resource.ServerResource;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;



public class UserController extends ServerResource implements
UserControllerInterface {
	
	public UserController() {
	 }

	 @Override
	 public void create(User user) {
	 ObjectifyService.register(User.class);
	 Objectify ofy = ObjectifyService.begin();

	 User tp = new User();
	 tp.setFirstname(user.getFirstname());
	 tp.setLastname(user.getLastname());
	 tp.setMail(user.getMail());
	 tp.setPhone(user.getPhone());
	 ofy.put(tp);
	 }

	 @Override
	 public Container getAllUsers() {
	 Container content = null;
	 List users = new ArrayList();
	 ObjectifyService.register(User.class);
	 Objectify ofy = ObjectifyService.begin();
	 
	 

	 Query<User> q = ofy.query(User.class);
	for (User u : q)
	    users.add(u);


	 content = new Container();
	 content.setUser_list(users);

	 return content;
	 }

}
