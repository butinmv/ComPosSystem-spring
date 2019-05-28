package pos.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.system.entities.Company;
import pos.system.entities.Position;
import pos.system.repo.CheckRepository;
import pos.system.repo.PositionRepository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public void save(Position position) { positionRepository.save(position); }

    public Iterable<Position> findAllByCompany(Company company) {
        return positionRepository.findAllByCompany(company);
    }

    public Position findById(Long id) {
        return positionRepository.findById(id).get();
    }

    public void delete(Long id) {
        positionRepository.deleteById(id);
    }

    public Position findByName(String name) {
        return positionRepository.findByName(name);
    }
}
