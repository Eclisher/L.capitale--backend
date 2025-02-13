package L.capitale.L.capitale.Service;

import  L.capitale.L.capitale.Entity.ContextMessage;
import L.capitale.L.capitale.Repository.ContextMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContextMessasgeService {
    @Autowired
    private  ContextMessageRepository contextMessageRepository;

    public List<ContextMessage> findAll() {
        return contextMessageRepository.findAll();
    }

    public Optional<ContextMessage> findById(Long id) {
        return contextMessageRepository.findById(id);
    }
    public ContextMessage saveMessage(ContextMessage contextMessage) {
        return contextMessageRepository.save(contextMessage);
    }

    public void deleteMessage(Long id) {
        contextMessageRepository.deleteById(id);
    }
    public ContextMessage updateMessage(Long id, ContextMessage   contextMessageDetails) {
        return contextMessageRepository.findById(id)
                .map(contextMessage ->{
                    contextMessage.setName(contextMessageDetails.getName());
                    contextMessage.setMessageContext(contextMessageDetails.getMessageContext());
                    return contextMessageRepository.save(contextMessage);
                })
                .orElseThrow(() -> new RuntimeException("Message  avec ces informations: " +contextMessageDetails+" avec l'id : " +id+"n'existe pas "));
    }
}
