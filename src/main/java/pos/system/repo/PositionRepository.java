package pos.system.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pos.system.entities.Company;
import pos.system.entities.Position;

import java.util.Optional;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {

    Iterable<Position> findAllByCompany(Company company);

    Position findByName(String name);
}
