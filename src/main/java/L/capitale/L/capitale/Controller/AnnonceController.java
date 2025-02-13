package L.capitale.L.capitale.Controller;

import L.capitale.L.capitale.Entity.Annonce;
import L.capitale.L.capitale.Service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/annonces")
@CrossOrigin(origins = "http://localhost:5173")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;

    // Récupérer toutes les annonces
    @GetMapping
    public List<Annonce> getAllAnnonces() {
        return annonceService.findAll();
    }

    // Ajouter une annonce
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> ajouterAnnonce(
            @RequestParam("titre") String titre,
            @RequestParam("description") String description,
            @RequestParam("date_annonce") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateAnnonce,
            @RequestParam(value = "disponible", required = false, defaultValue = "true") boolean disponible,
            @RequestParam(value = "numero_telephone", required = false) String numeroTelephone,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "image", required = true) MultipartFile image
    ) throws IOException {
        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            String uploadDir = "uploads/";
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) uploadPath.mkdirs();

            String imagePath = uploadDir + image.getOriginalFilename();
            Files.copy(image.getInputStream(), Paths.get(imagePath), StandardCopyOption.REPLACE_EXISTING);
            imageUrl = imagePath;
        }

        Annonce annonce = new Annonce(titre, description, imageUrl, dateAnnonce, disponible, numeroTelephone, email);
        annonceService.saveAnnonce(annonce);

        return ResponseEntity.ok("Annonce ajoutée avec succès !");
    }

    // Supprimer une annonce
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
