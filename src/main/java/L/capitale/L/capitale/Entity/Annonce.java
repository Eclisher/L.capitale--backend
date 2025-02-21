package L.capitale.L.capitale.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "annonce_image_urls", joinColumns = @JoinColumn(name = "annonce_id"))
    @Column(name = "image_urls")
    private List<String> imageUrls;

    @Column(nullable = false)
    private LocalDate dateAnnonce;

    private boolean disponible;
    private String numeroTelephone;
    private String email;

    public Annonce(String titre, String description, List<String> imageUrls, LocalDate dateAnnonce,
                   boolean disponible, String numeroTelephone, String email) {
        this.titre = titre;
        this.description = description;
        this.imageUrls = imageUrls;
        this.dateAnnonce = dateAnnonce;
        this.disponible = disponible;
        this.numeroTelephone = numeroTelephone;
        this.email = email;
    }

    public Annonce() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(List<String> imageUrls) { this.imageUrls = imageUrls; }
    public LocalDate getDateAnnonce() { return dateAnnonce; }
    public void setDateAnnonce(LocalDate dateAnnonce) { this.dateAnnonce = dateAnnonce; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public String getNumeroTelephone() { return numeroTelephone; }
    public void setNumeroTelephone(String numeroTelephone) { this.numeroTelephone = numeroTelephone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

