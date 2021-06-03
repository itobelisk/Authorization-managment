package com.auth.server.util;

import com.auth.server.entity.webuser.WebUser;
import com.auth.server.exception.NotAuthorizedUserAccessException;
import com.auth.server.repository.WebUserRepository;
import com.auth.server.security.UserBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final WebUserRepository webUserRepository;

    public WebUser getUserId() {
        Object webUsers = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (webUsers.equals("anonymousUser")) throw new NotAuthorizedUserAccessException();
        Long userId = ((UserBuilder) webUsers).getId();
        return webUserRepository.getOne(userId);

    }
}
