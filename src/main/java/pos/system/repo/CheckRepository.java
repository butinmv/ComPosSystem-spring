package pos.system.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pos.system.entities.Check;
import pos.system.entities.Company;

@Repository
public interface CheckRepository extends CrudRepository<Check, Long> {

    Iterable<Check> findAllByCompany(Company company);
}