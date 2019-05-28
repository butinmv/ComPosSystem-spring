package pos.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.system.entities.Company;
import pos.system.entities.Staff;
import pos.system.repo.StaffRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class StaffService {

    private StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public void save(Staff staff) {
        staffRepository.save(staff);
    }

    public Iterable<Staff> findAllByCompany(Company company) {
        return staffRepository.findAllByCompany(company);
    }

    public Staff findById(Long id) {
        return staffRepository.findById(id).get();
    }

    public void delete(Long id) {
        staffRepository.deleteById(id);
    }
}
