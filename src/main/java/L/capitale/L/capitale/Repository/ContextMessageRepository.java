package L.capitale.L.capitale.Repository;

import L.capitale.L.capitale.Entity.ContextMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextMessageRepository extends JpaRepository<ContextMessage, Long> {
}
