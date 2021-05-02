package com.auth.server.services.privilege.impl;

import com.auth.server.base.BaseResponse;
import com.auth.server.entity.privelege.response.PrivilegeResponse;
import com.auth.server.mapper.PrivilegeMapper;
import com.auth.server.repository.PrivilegeRepository;
import com.auth.server.services.privilege.PrivilegeControllerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrivilegesControllerServiceImpl implements PrivilegeControllerService {

    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeMapper privilegeMapper;

    @Override
    public BaseResponse<?> getAll() {
        List<PrivilegeResponse> response = null;
        try {
            response = privilegeMapper.toPrivilegeNameResponseList(privilegeRepository.findAll());
        } catch (Exception e) {
            return new BaseResponse<>(new Date(), false, HttpStatus.CONFLICT, e.getMessage());
        }
        return new BaseResponse<>(new Date(), true, HttpStatus.OK, response);
    }


}
