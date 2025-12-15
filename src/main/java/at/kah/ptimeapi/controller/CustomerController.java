package at.kah.ptimeapi.controller;

import at.kah.ptimeapi.dto.CustomerDTO;
import at.kah.ptimeapi.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;
    public CustomerController(CustomerService service) { this.service = service; }

    @GetMapping public List<CustomerDTO> getAll() { return service.getAll(); }
    @GetMapping("/<built-in function id>") public CustomerDTO getById(@PathVariable Long id) { return service.getById(id); }
    @PostMapping public CustomerDTO create(@RequestBody CustomerDTO dto) { return service.create(dto); }
    @PutMapping("/<built-in function id>") public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerDTO dto) { return service.update(id, dto); }
    @DeleteMapping("/<built-in function id>") public void delete(@PathVariable Long id) { service.delete(id); }
}
