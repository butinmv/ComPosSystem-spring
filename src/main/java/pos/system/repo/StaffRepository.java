package pos.system.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pos.system.entities.Company;
import pos.system.entities.Staff;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Long> {

    Iterable<Staff> findAllByCompany(Company company);
}
