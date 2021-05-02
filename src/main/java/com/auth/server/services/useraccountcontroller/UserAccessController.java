package com.auth.server.services.useraccountcontroller;

import com.auth.server.annotations.CurrentUser;
import com.auth.server.entity.webuser.WebUser;
import com.auth.server.security.UserBuilder;

@FunctionalInterface
public interface UserAccessController {

    WebUser getCurrentUser(@CurrentUser UserBuilder userPrincipal);
}
