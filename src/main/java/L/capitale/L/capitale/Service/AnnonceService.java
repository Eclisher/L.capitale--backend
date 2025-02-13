package L.capitale.L.capitale.Service;

import L.capitale.L.capitale.Entity.Annonce;
import L.capitale.L.capitale.Repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AnnonceService {

    @Autowired
    private AnnonceRepository annonceRepository;

    public List<Annonce> findAll() {
        return annonceRepository.findAll();
    }

    public Optional<Annonce> findById(Long id) {
        return annonceRepository.findById(id);
    }

    public Annonce saveAnnonce(Annonce annonce) {
        return annonceRepository.save(annonce);
    }

    public void deleteAnnonce(Long id) {
        annonceRepository.deleteById(id);
    }
    public Annonce updateAnnonce(Long id, Annonce annonceDetails) {
        return annonceRepository.findById(id)
                .map(annonce -> {
                    annonce.setTitre(annonceDetails.getTitre());
                    annonce.setDescription(annonceDetails.getDescription());
                    annonce.setImageUrl(annonceDetails.getImageUrl());
                    annonce.setDateAnnonce(annonceDetails.getDateAnnonce());
                    annonce.setDisponible(annonceDetails.isDisponible());
                    annonce.setNumeroTelephone(annonceDetails.getNumeroTelephone());
                    annonce.setEmail(annonceDetails.getEmail());
                    return annonceRepository.save(annonce);
                })
                .orElseThrow(() -> new RuntimeException("Annonce avec l'ID " + id + " n'existe pas."));
    }
}
