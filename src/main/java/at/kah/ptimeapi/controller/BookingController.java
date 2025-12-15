package at.kah.ptimeapi.controller;

import at.kah.ptimeapi.dto.BookingDTO;
import at.kah.ptimeapi.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService service;
    public BookingController(BookingService service) { this.service = service; }

    @GetMapping public List<BookingDTO> getAll() { return service.getAll(); }
    @GetMapping("/<built-in function id>") public BookingDTO getById(@PathVariable Long id) { return service.getById(id); }
    @PostMapping public BookingDTO create(@RequestBody BookingDTO dto) { return service.create(dto); }
    @PutMapping("/<built-in function id>") public BookingDTO update(@PathVariable Long id, @RequestBody BookingDTO dto) { return service.update(id, dto); }
    @DeleteMapping("/<built-in function id>") public void delete(@PathVariable Long id) { service.delete(id); }
}
