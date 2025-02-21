package L.capitale.L.capitale.Service;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
@Service
public class ImageService {
    @Autowired
    private GoogleDriveService googleDriveService;

    public String uploadImage(MultipartFile file, String folderId) throws IOException, GeneralSecurityException {
        if (folderId == null || folderId.isEmpty()) {
            throw new IllegalArgumentException("L'ID du dossier ne peut pas être null ou vide !");
        }

        Drive driveService = googleDriveService.getDriveService();

        // Vérification du dossier
        try {
            File folder = driveService.files().get(folderId).setFields("id, name").execute();
            System.out.println("✅ Dossier trouvé : " + folder.getName());
        } catch (Exception e) {
            throw new IOException("❌ Impossible d'accéder au dossier. Vérifiez l'ID et les permissions.", e);
        }

        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());
        fileMetadata.setParents(Collections.singletonList(folderId));

        ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getBytes());
        File uploadedFile = driveService.files()
                .create(fileMetadata, new InputStreamContent(file.getContentType(), inputStream))
                .setFields("id, webViewLink, webContentLink")
                .execute();

        System.out.println("✅ Fichier uploadé avec ID : " + uploadedFile.getId());
        return uploadedFile.getId();
    }
}