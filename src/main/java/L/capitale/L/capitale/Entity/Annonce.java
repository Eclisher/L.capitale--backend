package L.capitale.L.capitale.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false)
    private LocalDate dateAnnonce;

    private boolean disponible;

    private String numeroTelephone;
    private String email;

    // Constructeur complet
    public Annonce(String titre, String description, String imageUrl, LocalDate dateAnnonce,
                   boolean disponible, String numeroTelephone, String email) {
        this.titre = titre;
        this.description = description;
        this.imageUrl = imageUrl;
        this.dateAnnonce = dateAnnonce;
        this.disponible = disponible;
        this.numeroTelephone = numeroTelephone;
        this.email = email;
    }

    // Constructeur par défaut
    public Annonce() {
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(LocalDate dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
