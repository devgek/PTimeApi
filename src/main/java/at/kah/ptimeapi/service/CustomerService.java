package at.kah.ptimeapi.service;

import at.kah.ptimeapi.dto.CustomerDTO;
import at.kah.ptimeapi.entity.Customer;
import at.kah.ptimeapi.repository.CustomerRepository;
import ch.qos.logback.classic.Logger;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    Logger logger = (Logger) LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository repository;
    private final ModelMapper mapper;

    public CustomerService(CustomerRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CustomerDTO> getAll() {
        logger.debug("Getting all customers");
        return ((List<Customer>)repository.findAll()).stream()
                .map(c -> mapper.map(c, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    public CustomerDTO getById(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(), CustomerDTO.class);
    }

    public CustomerDTO create(CustomerDTO dto) {
        Customer entity = mapper.map(dto, Customer.class);
        Customer saved = repository.save(entity);
        return mapper.map(saved, CustomerDTO.class);
    }

    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer entity = repository.findById(id).orElseThrow();
        entity.setName(dto.getName());
        Customer saved = repository.save(entity);
        return mapper.map(saved, CustomerDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
