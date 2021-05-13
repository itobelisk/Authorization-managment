package com.auth.server.annotations.binding;

import com.auth.server.exception.PasswordRulesException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class BindingManager {

    public void bindingCheck(BindingResult  bindingResult){
        if (bindingResult.hasErrors())throw new PasswordRulesException();
    }
}
