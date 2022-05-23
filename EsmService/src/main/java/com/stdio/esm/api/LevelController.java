package com.stdio.esm.api;


import com.stdio.esm.dto.response.DataResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/levels")
public class LevelController {

    @GetMapping(path = "/list-levels")
    @ApiOperation(value = "LIST LEVELS")
    public DataResponse<String> getLevels(){
        return DataResponse.success("levels");
    }

}
