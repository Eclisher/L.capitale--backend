package L.capitale.L.capitale.Entity;

import jakarta.persistence.*;

@Entity
public class ContextMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String messageContext;

    public ContextMessage() {
    }

    public ContextMessage(Long id, String name, String messageContext) {
        Id = id;
        this.name = name;
        this.messageContext = messageContext;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(String messageContext) {
        this.messageContext = messageContext;
    }
}