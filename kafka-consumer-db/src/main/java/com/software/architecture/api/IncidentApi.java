package com.software.architecture.api;

import org.springframework.web.bind.annotation.*;
import com.software.architecture.model.Incident;
import com.software.architecture.repo.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/incident")
public class IncidentApi {

    private IncidentRepository repository;
    
    @Autowired
    public void setIncidentRepository(IncidentRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Incident> getAll() {
        return repository.findAll();
    }
}
