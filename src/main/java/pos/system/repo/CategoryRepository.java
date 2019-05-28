package pos.system.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pos.system.entities.Category;
import pos.system.entities.Company;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Iterable<Category> findAllByCompany(Company company);

    Category findByName(String name);
}
