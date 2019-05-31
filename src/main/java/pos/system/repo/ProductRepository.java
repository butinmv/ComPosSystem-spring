package pos.system.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pos.system.entities.Company;
import pos.system.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    public Iterable<Product> findAllByCompany(Company company);

    public Product findByName(String name);
}
