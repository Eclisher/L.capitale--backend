package L.capitale.L.capitale.Repository;
import L.capitale.L.capitale.Entity.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
}