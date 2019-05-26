package pos.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.system.entities.Check;
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

    public Iterable<Check> findLast7Days() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        return checkRepository.findLast7Days(cal.getTime());
    }

    public Iterable<Check> findLast30Days() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        return checkRepository.findLast30Days(cal.getTime());
    }

    public Iterable<Check> findAll() {
        return checkRepository.findAll();
    }
}
