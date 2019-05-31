package pos.system.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pos.system.entities.ProductInCheck;

@Repository
public interface ProductInCheckRepository extends CrudRepository<ProductInCheck, Long> {
}
