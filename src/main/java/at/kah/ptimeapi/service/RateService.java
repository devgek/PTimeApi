package at.kah.ptimeapi.service;

import at.kah.ptimeapi.dto.RateDTO;
import at.kah.ptimeapi.entity.Rate;
import at.kah.ptimeapi.entity.Customer;
import at.kah.ptimeapi.repository.RateRepository;
import at.kah.ptimeapi.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateService {
    private final RateRepository repository;
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    public RateService(RateRepository repository, CustomerRepository customerRepository, ModelMapper mapper) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    public List<RateDTO> getAll() {
        return ((List<Rate>)repository.findAll()).stream()
                .map(r -> mapper.map(r, RateDTO.class))
                .collect(Collectors.toList());
    }

    public RateDTO getById(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(), RateDTO.class);
    }

    public RateDTO create(RateDTO dto) {
        Rate entity = mapper.map(dto, Rate.class);
        // ensure customer exists
        Customer c = customerRepository.findById(dto.getCustomerId()).orElseThrow();
        entity.setCustomerId(c.getId());
        Rate saved = repository.save(entity);
        return mapper.map(saved, RateDTO.class);
    }

    public RateDTO update(Long id, RateDTO dto) {
        Rate entity = repository.findById(id).orElseThrow();
        entity.setRate(dto.getRate());
        entity.setFromDate(dto.getFromDate());
        entity.setToDate(dto.getToDate());
        Customer c = customerRepository.findById(dto.getCustomerId()).orElseThrow();
        entity.setCustomerId(c.getId());
        Rate saved = repository.save(entity);
        return mapper.map(saved, RateDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
