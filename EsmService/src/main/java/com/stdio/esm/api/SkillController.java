package com.stdio.esm.api;

import com.stdio.esm.dto.response.DataResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/skills")
public class SkillController {

    @GetMapping(path = "/list-skills")
    @ApiOperation(value = "LIST SKILLS")
    public DataResponse<String> getSkills(){
        return DataResponse.success("skill");
    }

    @PostMapping(path = "/add-skill")
    @ApiOperation(value = "ADD SKILLS")
    public DataResponse<String> addSkill(){
        return DataResponse.success("add skills");
    }

    @PutMapping(path = "/edit-skill")
    @ApiOperation(value = "EDIT SKILLS")
    public DataResponse<String> updateSkill(){
        return DataResponse.success("update skills");
    }

    @DeleteMapping(path = "/delete-skill")
    @ApiOperation(value = "DELETE SKILLS")
    public DataResponse<String> deleteSkill(){
        return DataResponse.success("delete skills");
    }
}
