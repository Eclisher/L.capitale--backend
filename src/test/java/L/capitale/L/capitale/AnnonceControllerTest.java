package L.capitale.L.capitale;


import L.capitale.L.capitale.Controller.AnnonceController;
import L.capitale.L.capitale.Entity.Annonce;
import L.capitale.L.capitale.Service.AnnonceService;
import L.capitale.L.capitale.Service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;





public class AnnonceControllerTest {

    @Mock
    private AnnonceService annonceService;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private AnnonceController annonceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAnnonce_Success() throws IOException, GeneralSecurityException {
        // Mock d'une image
        MockMultipartFile mockFile = new MockMultipartFile(
                "images", "test.jpg", MediaType.IMAGE_JPEG_VALUE, "fake image content".getBytes());

        List<MultipartFile> images = Collections.singletonList(mockFile); // Liste d'images au lieu d'un seul fichier

        // Valeurs d'entrée
        String titre = "Annonce Test";
        String description = "Ceci est une annonce de test";
        LocalDate dateAnnonce = LocalDate.of(2025, 2, 16);
        boolean disponible = true;
        String numeroTelephone = "0321122334";
        String email = "test@email.com";

        // Simulation du service d'upload d'image
        when(imageService.uploadImage(any(MultipartFile.class), anyString())).thenReturn("image123");

        // Simulation du service d'annonce
        Annonce annonceMock = new Annonce();
        annonceMock.setTitre(titre);
        annonceMock.setDescription(description);
        annonceMock.setDateAnnonce(dateAnnonce);
        annonceMock.setDisponible(disponible);
        annonceMock.setNumeroTelephone(numeroTelephone);
        annonceMock.setEmail(email);
        annonceMock.setImageUrls(Collections.singletonList("image123"));

        when(annonceService.saveAnnonce(any(Annonce.class))).thenReturn(annonceMock);

        // Appel du contrôleur
        Annonce result = annonceController.createAnnonce(titre, description, dateAnnonce, disponible, numeroTelephone, email, images);

        // Vérifications
        assertNotNull(result);
        assertEquals("Annonce Test", result.getTitre());
        assertEquals("Ceci est une annonce de test", result.getDescription());
        assertEquals(dateAnnonce, result.getDateAnnonce());
        assertEquals("0321122334", result.getNumeroTelephone());
        assertEquals("test@email.com", result.getEmail());
        assertEquals(1, result.getImageUrls().size());
        assertEquals("image123", result.getImageUrls().get(0));

        // Vérification que les services ont été appelés
        verify(imageService, times(1)).uploadImage(any(MultipartFile.class), anyString());
        verify(annonceService, times(1)).saveAnnonce(any(Annonce.class));
    }
}