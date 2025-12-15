package at.kah.ptimeapi.controller;

import at.kah.ptimeapi.dto.ProjectDTO;
import at.kah.ptimeapi.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService service;
    public ProjectController(ProjectService service) { this.service = service; }

    @GetMapping public List<ProjectDTO> getAll() { return service.getAll(); }
    @GetMapping("/<built-in function id>") public ProjectDTO getById(@PathVariable Long id) { return service.getById(id); }
    @PostMapping public ProjectDTO create(@RequestBody ProjectDTO dto) { return service.create(dto); }
    @PutMapping("/<built-in function id>") public ProjectDTO update(@PathVariable Long id, @RequestBody ProjectDTO dto) { return service.update(id, dto); }
    @DeleteMapping("/<built-in function id>") public void delete(@PathVariable Long id) { service.delete(id); }
}
