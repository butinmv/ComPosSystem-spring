package pos.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.system.entities.Check;
import pos.system.entities.Company;
import pos.system.repo.CheckRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Service
@Transactional
public class CheckService {

    private final CheckRepository checkRepository;

    @Autowired
    public CheckService(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    public Iterable<Check> findAllByCompany_id(Company company) {
        return checkRepository.findAllByCompany(company);
    }
}
