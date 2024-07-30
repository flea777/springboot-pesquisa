package io.github.flea777.pesquisa.dtos;

public class MessageDTO {

    private String username;
    private String body;

    public MessageDTO() {
    }

    public MessageDTO(String username, String body) {
        this.username = username;
        this.body = body;
    }

    public String getUsername() {
        return username;
    }

    public String getBody() {
        return body;
    }
}
