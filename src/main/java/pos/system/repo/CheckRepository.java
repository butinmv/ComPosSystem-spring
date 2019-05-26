package pos.system.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pos.system.entities.Check;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Repository
public interface CheckRepository extends CrudRepository<Check, Long> {

    @Query("select c from Check c where c.timeClose >= :date")
    Iterable<Check> findLast7Days(@Param("date") Date date);

    @Query("select c from Check c where c.timeClose >= :date")
    Iterable<Check> findLast30Days(@Param("date") Date date);
}