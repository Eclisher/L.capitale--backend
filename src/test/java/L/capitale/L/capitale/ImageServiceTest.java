package L.capitale.L.capitale;
import L.capitale.L.capitale.Service.GoogleDriveService;
import L.capitale.L.capitale.Service.ImageService;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static org.junit.jupiter.    api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ImageServiceTest {

    @Mock
    private GoogleDriveService googleDriveService;

    @Mock
    private Drive drive;

    @Mock
    private Drive.Files files;

    @Mock
    private Drive.Files.Create createRequest;

    @InjectMocks
    private ImageService imageService;
    @Mock
    private Drive.Files.Get getRequest;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Simuler la récupération du Drive service
        when(googleDriveService.getDriveService()).thenReturn(drive);

        // Simuler l'accès aux fichiers
        when(drive.files()).thenReturn(files);

        // Simuler la récupération d'un fichier/dossier
        when(files.get(anyString())).thenReturn(getRequest);
        when(getRequest.setFields(anyString())).thenReturn(getRequest);
        when(getRequest.execute()).thenReturn(new File().setId("fakeFolderId").setName("testFolder"));

        // Simuler la création de fichier
        when(files.create(any(File.class), any())).thenReturn(createRequest);
        when(createRequest.setFields(anyString())).thenReturn(createRequest);
    }

    @Test
    void testUploadMultipleImages() throws Exception {
        // Arrange
        MultipartFile mockFile = new MockMultipartFile("image.jpg", "image.jpg", "image/jpeg", new byte[10]);

        // Mock du dossier Google Drive
        when(googleDriveService.getFolderIdByName("testFolder")).thenReturn("fakeFolderId");

        // Act
        String imageUrl = imageService.uploadImage(mockFile, "fakeFolderId");

        // Assert
        assertNotNull(imageUrl);
        assertTrue(imageUrl.contains("drive.google.com"));
    }
}
