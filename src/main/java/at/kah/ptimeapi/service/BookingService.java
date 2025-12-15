package at.kah.ptimeapi.service;

import at.kah.ptimeapi.dto.BookingDTO;
import at.kah.ptimeapi.entity.Booking;
import at.kah.ptimeapi.entity.Project;
import at.kah.ptimeapi.repository.BookingRepository;
import at.kah.ptimeapi.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final BookingRepository repository;
    private final ProjectRepository projectRepository;
    private final ModelMapper mapper;

    public BookingService(BookingRepository repository, ProjectRepository projectRepository, ModelMapper mapper) {
        this.repository = repository;
        this.projectRepository = projectRepository;
        this.mapper = mapper;
    }

    public List<BookingDTO> getAll() {
        return ((List<Booking>)repository.findAll()).stream()
                .map(b -> mapper.map(b, BookingDTO.class))
                .collect(Collectors.toList());
    }

    public BookingDTO getById(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(), BookingDTO.class);
    }

    public BookingDTO create(BookingDTO dto) {
        Booking entity = mapper.map(dto, Booking.class);
        Project p = projectRepository.findById(dto.getProjectId()).orElseThrow();
        entity.setProjectId(p.getId());
        Booking saved = repository.save(entity);
        return mapper.map(saved, BookingDTO.class);
    }

    public BookingDTO update(Long id, BookingDTO dto) {
        Booking entity = repository.findById(id).orElseThrow();
        entity.setFromDate(dto.getFromDate());
        entity.setToDate(dto.getToDate());
        entity.setMinutes(dto.getMinutes());
        entity.setBreakMinutes(dto.getBreakMinutes());
        entity.setBillable(dto.getBillable());
        entity.setNote(dto.getNote());
        Project p = projectRepository.findById(dto.getProjectId()).orElseThrow();
        entity.setProjectId(p.getId());
        Booking saved = repository.save(entity);
        return mapper.map(saved, BookingDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
