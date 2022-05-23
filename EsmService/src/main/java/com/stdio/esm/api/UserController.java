package com.stdio.esm.api;

import com.stdio.esm.dto.response.DataResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @GetMapping(path = "/list-users")
    @ApiOperation(value = "LIST USERS")
    public DataResponse<String> getUsers(){
        return DataResponse.success("users");
    }

    @PostMapping(path = "/add-user")
    @ApiOperation(value = "ADD USER")
    public DataResponse<String> addUser(){
        return DataResponse.success("add user");
    }

    @PutMapping(path = "/edit-user")
    @ApiOperation(value = "UPDATE USER")
    public DataResponse<String> updateUser(){
        return DataResponse.success("update user");
    }

    @DeleteMapping(path = "/delete-user")
    @ApiOperation(value = "DELETE USER")
    public DataResponse<String> deleteUser(){
        return DataResponse.success("delete user");
    }
}
