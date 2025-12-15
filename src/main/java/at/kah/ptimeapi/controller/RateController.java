package at.kah.ptimeapi.controller;

import at.kah.ptimeapi.dto.RateDTO;
import at.kah.ptimeapi.service.RateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rates")
public class RateController {
    private final RateService service;
    public RateController(RateService service) { this.service = service; }

    @GetMapping public List<RateDTO> getAll() { return service.getAll(); }
    @GetMapping("/<built-in function id>") public RateDTO getById(@PathVariable Long id) { return service.getById(id); }
    @PostMapping public RateDTO create(@RequestBody RateDTO dto) { return service.create(dto); }
    @PutMapping("/<built-in function id>") public RateDTO update(@PathVariable Long id, @RequestBody RateDTO dto) { return service.update(id, dto); }
    @DeleteMapping("/<built-in function id>") public void delete(@PathVariable Long id) { service.delete(id); }
}
