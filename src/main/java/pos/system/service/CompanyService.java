package pos.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.system.dto.CompanyDTO;
import pos.system.entities.Company;
import pos.system.repo.CompanyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Iterable <Company> read() {
        return companyRepository.findAll();
    }

    public void save(Company company) { companyRepository.save(company); }

    public Company get(Long id) {
        return companyRepository.findById(id).get();
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    public Company findByUsername(String username) {
        return companyRepository.findByUsername(username);
    }

}