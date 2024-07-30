package io.github.flea777.pesquisa.dtos;

import io.github.flea777.pesquisa.entities.Status;
import io.github.flea777.pesquisa.enums.StatusEnum;

public class StatusDTO {

    private StatusEnum status;
    private String username;

    public StatusDTO(Status data) {
        status = data.getStatus();
        username = data.getUsername();
    }

    public StatusEnum getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }
}
