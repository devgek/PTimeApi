package at.kah.ptimeapi.service;

import at.kah.ptimeapi.dto.ProjectDTO;
import at.kah.ptimeapi.entity.Project;
import at.kah.ptimeapi.entity.Customer;
import at.kah.ptimeapi.repository.ProjectRepository;
import at.kah.ptimeapi.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository repository;
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;

    public ProjectService(ProjectRepository repository, CustomerRepository customerRepository, ModelMapper mapper) {
        this.repository = repository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    public List<ProjectDTO> getAll() {
        return ((List<Project>)repository.findAll()).stream()
                .map(p -> mapper.map(p, ProjectDTO.class))
                .collect(Collectors.toList());
    }

    public ProjectDTO getById(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(), ProjectDTO.class);
    }

    public ProjectDTO create(ProjectDTO dto) {
        Project entity = mapper.map(dto, Project.class);
        Customer c = customerRepository.findById(dto.getCustomerId()).orElseThrow();
        entity.setCustomerId(c.getId());
        Project saved = repository.save(entity);
        return mapper.map(saved, ProjectDTO.class);
    }

    public ProjectDTO update(Long id, ProjectDTO dto) {
        Project entity = repository.findById(id).orElseThrow();
        entity.setTitle(dto.getTitle());
        entity.setSubTitle(dto.getSubTitle());
        Customer c = customerRepository.findById(dto.getCustomerId()).orElseThrow();
        entity.setCustomerId(c.getId());
        entity.setActive(dto.getActive());
        entity.setBillable(dto.getBillable());
        entity.setPriority(dto.getPriority());
        Project saved = repository.save(entity);
        return mapper.map(saved, ProjectDTO.class);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
