package com.stdio.esm.api;

import com.stdio.esm.dto.response.DataResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/projects")
public class ProjectController {

    @GetMapping(path = "/list-projects")
    @ApiOperation(value = "LIST PROJECTS")
    public DataResponse<String> getProjects(){
        return DataResponse.success("projects");
    }

    @PostMapping(path = "/add-project")
    @ApiOperation(value = "ADD PROJECTS")
    public DataResponse<String> addProject(){
        return DataResponse.success("add project");
    }

    @PutMapping(path = "/edit-project")
    @ApiOperation(value = "UPDATE PROJECTS")
    public DataResponse<String> updateProject(){
        return DataResponse.success("update project");
    }

    @DeleteMapping(path = "/delete-project")
    @ApiOperation(value = "DELETE PROJECTS")
    public DataResponse<String> deleteProject(){
        return DataResponse.success("delete project");
    }
}
