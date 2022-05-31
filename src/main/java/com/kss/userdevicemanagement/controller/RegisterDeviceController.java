package com.kss.userdevicemanagement.controller;

import com.kss.userdevicemanagement.common.ResponseData;
import com.kss.userdevicemanagement.ex.ApiException;
import com.kss.userdevicemanagement.model.request.DeviceRegisterRequest;
import com.kss.userdevicemanagement.model.response.DeviceRegisterResponse;
import com.kss.userdevicemanagement.service.UserDeviceManagementService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "Register Device")
@RestController
public class RegisterDeviceController {

    @Autowired
    UserDeviceManagementService userDeviceManagementService;

    @PostMapping(value = "/p/device/register", produces = "application/json")
    public ResponseData<DeviceRegisterResponse> registerDevice(@ApiIgnore HttpServletRequest httpServletRequest, @RequestBody DeviceRegisterRequest deviceRegisterRequest) {
        String remoteAddr = httpServletRequest.getRemoteAddr();
        String remoteHost = httpServletRequest.getRemoteHost();
        String remoteUser = httpServletRequest.getRemoteUser();
        String localAddr = httpServletRequest.getLocalAddr();
        String localname = httpServletRequest.getLocalName();

        System.out.println("remoteAddr: " + remoteAddr);
        System.out.println("remoteHost: " + remoteHost);
        System.out.println("remoteUser: " + remoteUser);
        System.out.println("localAddr: " + localAddr);
        System.out.println("localname: " + localname);

        deviceRegisterRequest.setPublicIp(remoteAddr);

        return new ResponseData<DeviceRegisterResponse>().success(userDeviceManagementService.registerDevice(deviceRegisterRequest));
    }

    @PostMapping(value = "/e/{user_id}/device/register", produces = "application/json")
    public ResponseData<Void> registerUserDevice(@RequestParam(name = "device_uuid") String deviceUUID, @PathVariable(value = "user_id") String userId) throws ApiException {
        userDeviceManagementService.userDeviceRegister(userId, deviceUUID);
        return new ResponseData<>();
    }

}
