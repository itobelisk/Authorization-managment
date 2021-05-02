package com.auth.server.annotations.validator;

import com.auth.server.annotations.CurrentUser;
import com.auth.server.entity.useraccess.UserAccessController;
import com.auth.server.entity.webuser.WebUser;
import com.auth.server.exception.UserNotFoundException;
import com.auth.server.exception.constance.ExceptionConstance;
import com.auth.server.repository.WebUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
@Log4j2
public class CurrentUserCheck extends UserAccessController implements ConstraintValidator<CurrentUser, UserAccessController> {

    private final WebUserRepository userRepository;
    private final JwtValidator jwtValidator;

    @Override
    public boolean isValid(UserAccessController o, ConstraintValidatorContext constraintValidatorContext) {
        return getCurrentUser(o.getAccessToken()) != null;
    }

    public WebUser getCurrentUser(String userAccessController) {
        return userRepository
                .findById((Long.parseLong(
                        jwtValidator.validate(userAccessController))))
                .orElseThrow(() -> new UserNotFoundException(
                        ExceptionConstance.USER_NOT_FOUND_EXCEPTION));
    }

}
