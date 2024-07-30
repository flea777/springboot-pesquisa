package io.github.flea777.pesquisa.services;

import io.github.flea777.pesquisa.dtos.MessageDTO;
import io.github.flea777.pesquisa.dtos.StatusDTO;
import io.github.flea777.pesquisa.entities.Status;
import io.github.flea777.pesquisa.enums.StatusEnum;
import io.github.flea777.pesquisa.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusService {

    @Autowired
    private StatusRepository repository;

    public StatusDTO updateOrCreateStatus(MessageDTO dto) {

        Optional<Status> existingStatus = repository.findByUsername(dto.getUsername());

        if (existingStatus.isPresent()) {
            Status status = existingStatus.get();
            changeStatus(status);
            status.setLastMessage(dto.getBody());
            repository.save(status);
            return new StatusDTO(status);
        } else if(dto.getBody().equals("-start-")) {
            Status status = new Status(dto.getUsername(), StatusEnum.ASK_NAME, dto.getBody());
            repository.save(status);
            return new StatusDTO(status);
        } else {
            Status status = new Status(dto.getUsername(), StatusEnum.NULL, dto.getBody());
            repository.save(status);
            return new StatusDTO(status);
        }
    }

    public void changeStatus(Status status) {
        if (status.getStatus() == StatusEnum.ASK_NAME) {
            status.setStatus(StatusEnum.ASK_CPF);
        } else if (status.getStatus() == StatusEnum.ASK_CPF) {
            status.setStatus(StatusEnum.ASK_EMAIL);
        } else if (status.getStatus() == StatusEnum.ASK_EMAIL) {
            status.setStatus(StatusEnum.ASK_VOTE);
        } else if (status.getStatus() == StatusEnum.ASK_VOTE) {
            status.setStatus(StatusEnum.DONE);
        } else if (status.getStatus() == StatusEnum.NULL) {
            status.setStatus(StatusEnum.NULL);
        }
    }

    public StatusDTO findStatusByUser(String user) {
        Status status = repository.findByUsername(user).orElse(null);
        return new StatusDTO(status);
    }

    public List<StatusDTO> findAllUsers() {
        List<Status> data = repository.findAll();
        List<StatusDTO> statusDTOS = new ArrayList<>();
        for (Status s : data) {
            statusDTOS.add(new StatusDTO(s));
        }
        return statusDTOS;
    }
}
