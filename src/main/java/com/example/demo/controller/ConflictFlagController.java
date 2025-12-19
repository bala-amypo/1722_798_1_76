package com.example.demo.controller;

import java.util.List;


import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.ConflictFlag;
import com.example.demo.service.ConflictFlagService;

@RestController
@RequestMapping("/api/conflict-flag")
public class ConflictFlagController {

    private ConflictFlagService service;

    public ConflictFlagController(ConflictFlagService service) {
        this.service = service;
    }
    @PostMapping
    public ConflictFlag add(@RequestBody ConflictFlag flag){
        return service.addFlag(flag);
    }
    @GetMapping("/case/{caseId}")
    public List<ConflictFlag> getByCase(
            @PathVariable Long caseId) {
        return service.getFlagsByCase(caseId);
    }

    @GetMapping("/{id}")
    public List<ConflictFlag> getById(@PathVariable Long id){
        return service.getFlagById(id);
    }

    @GetMapping
    public List<ConflictFlag>getAll(){
        return service.GetAllFlags();
    }
}
