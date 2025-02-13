package L.capitale.L.capitale.Controller;

import L.capitale.L.capitale.Entity.ContextMessage;
import L.capitale.L.capitale.Service.ContextMessasgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class ContextMessageController {
    @Autowired
    private ContextMessasgeService contextMessasgeService;

    @GetMapping
    public List<ContextMessage> getAllMessages() {
        return contextMessasgeService.findAll();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        try {
            contextMessasgeService.deleteMessage(id);
            return ResponseEntity.ok("Context Message supprimer avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
