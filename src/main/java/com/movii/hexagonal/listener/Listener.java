package com.movii.hexagonal.listener;
import com.movii.hexagonal.application.port.in.CreatePersonMongoUseCase;
import com.movii.hexagonal.application.port.in.DeletePersonMongoUseCase;
import com.movii.hexagonal.application.port.in.FindPersonMySQLUseCase;
import com.movii.hexagonal.application.port.in.UpdatePersonMySQLUseCase;
import com.movii.hexagonal.config.MessagingConfig;
import com.movii.hexagonal.dto.TemplateDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    private final CreatePersonMongoUseCase createPersonMongoUseCase;

    private final DeletePersonMongoUseCase deletePersonMongoUseCase;

    private final UpdatePersonMySQLUseCase updatePersonMySQLUseCase;

    private final FindPersonMySQLUseCase findPersonMySQLUseCase;

    public Listener(CreatePersonMongoUseCase createPersonMongoUseCase,
                    DeletePersonMongoUseCase deletePersonMongoUseCase,
                    UpdatePersonMySQLUseCase updatePersonMySQLUseCase,
                    FindPersonMySQLUseCase findPersonMySQLUseCase){
        this.createPersonMongoUseCase = createPersonMongoUseCase;
        this.deletePersonMongoUseCase = deletePersonMongoUseCase;
        this.updatePersonMySQLUseCase = updatePersonMySQLUseCase;
        this.findPersonMySQLUseCase = findPersonMySQLUseCase;
    }

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(TemplateDTO templateDTO) {
        if (templateDTO.getAction().equalsIgnoreCase("CREATE-MYSQL")){
            createPersonMongoUseCase.createPerson(templateDTO.getPersonDTO());
        } else if (templateDTO.getAction().equalsIgnoreCase("DELETE-MYSQL")) {
            deletePersonMongoUseCase.deletePerson(templateDTO.getPersonDTO().getId());
        } else if (templateDTO.getAction().equalsIgnoreCase("UPDATE-MONGO")) {
            updatePersonMySQLUseCase.updatePerson(templateDTO.getPersonDTO(),
                    templateDTO.getPersonDTO().getId());
        } else if (templateDTO.getAction().equalsIgnoreCase("FIND-MONGO")) {
            findPersonMySQLUseCase.findPerson(templateDTO.getPersonDTO().getId());
    }

    }
}
