package com.auth.server.annotations;

import com.auth.server.annotations.validator.CurrentUserCheck;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CurrentUserCheck.class)
@AuthenticationPrincipal
public @interface CurrentUser {

}