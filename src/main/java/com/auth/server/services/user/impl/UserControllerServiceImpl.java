package com.auth.server.services.user.impl;

import com.auth.server.repository.WebUserRepository;
import com.auth.server.services.user.UserControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserControllerServiceImpl implements UserControllerService {

    private final WebUserRepository userRepository;


}
