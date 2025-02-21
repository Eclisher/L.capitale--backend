package L.capitale.L.capitale.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleDriveService {
    private static final String APPLICATION_NAME = "lcapitale";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

    public Drive getDriveService() throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        String credentialsJson = System.getenv("GOOGLE_CREDENTIALS");
        if (credentialsJson == null || credentialsJson.isEmpty()) {
            throw new IOException("❌ Variable d'environnement GOOGLE_CREDENTIALS non définie ou vide.");
        }
        GoogleCredentials credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(credentialsJson.getBytes(StandardCharsets.UTF_8)))
                .createScoped(SCOPES);
        return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpCredentialsAdapter(credentials))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public String getFolderIdByName(String folderName) throws IOException, GeneralSecurityException {
        Drive driveService = getDriveService();
        String query = "mimeType='application/vnd.google-apps.folder' and name='" + folderName + "' and trashed=false";
        var result = driveService.files().list()
                .setQ(query)
                .setSpaces("drive")
                .setFields("files(id, name)")
                .execute();
        var files = result.getFiles();
        if (!files.isEmpty()) {
            return files.get(0).getId();
        }
        return null;
    }

    public com.google.api.services.drive.model.File getFolderById(String folderId) throws IOException, GeneralSecurityException {
        Drive driveService = getDriveService();
        return driveService.files().get(folderId).setFields("id, name").execute();
    }
}
