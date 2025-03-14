
package L.capitale.L.capitale.Controller;

import L.capitale.L.capitale.Entity.Annonce;
import L.capitale.L.capitale.Service.AnnonceService;
import L.capitale.L.capitale.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/annonces")
@CrossOrigin(origins = "${frontend.url}")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;

    @Autowired
    private ImageService imageService;

    @Value("${frontend.url}")
    private String frontendUrl;

    @GetMapping
    public List<Annonce> getAllAnnonces() {
        return annonceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Annonce> getAnnonceById(@PathVariable Long id) {
        return annonceService.findById(id)
                .map(annonce -> ResponseEntity.ok().body(annonce))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Annonce non trouvée"));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Annonce createAnnonce(@RequestParam String titre,
                                 @RequestParam String description,
                                 @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateAnnonce,
                                 @RequestParam boolean disponible,
                                 @RequestParam String numeroTelephone,
                                 @RequestParam String email,
                                 @RequestPart List<MultipartFile> images) throws IOException, GeneralSecurityException {
        if (images.isEmpty()) {
            throw new IllegalArgumentException("Au moins une image est requise");
        }

        String folderId = "1IemkVxVkZphQAAi6PfA4s_u7twwa34Cy";
        List<String> imageUrls = images.stream()
                .map(image -> {
                    try {
                        return imageService.uploadImage(image, folderId);
                    } catch (IOException | GeneralSecurityException e) {
                        throw new RuntimeException("Erreur lors de l'upload de l'image", e);
                    }
                })
                .collect(Collectors.toList());

        Annonce annonce = new Annonce();
        annonce.setTitre(titre);
        annonce.setDescription(description);
        annonce.setDateAnnonce(dateAnnonce != null ? dateAnnonce : LocalDate.now());
        annonce.setDisponible(disponible);
        annonce.setNumeroTelephone(numeroTelephone);
        annonce.setEmail(email);
        annonce.setImageUrls(imageUrls);

        return annonceService.saveAnnonce(annonce);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnonce(@PathVariable Long id) {
        try {
            annonceService.deleteAnnonce(id);
            return ResponseEntity.ok("Annonce supprimée avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
