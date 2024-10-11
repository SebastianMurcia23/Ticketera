package co.edu.uniquindio.proyecto.config;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileInputStream;
import java.io.IOException;


@Configuration
public class FirebaseConfig {


    @Bean
    public FirebaseApp intializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(
                "src/main/resources/ticket2-47d10-firebase-adminsdk-z35xr-94e73f027d.json"
        );


        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("ticket2-47d10")
                .build();


        if(FirebaseApp.getApps().isEmpty()) {
            return FirebaseApp.initializeApp(options);
        }


        return null;
    }


}

