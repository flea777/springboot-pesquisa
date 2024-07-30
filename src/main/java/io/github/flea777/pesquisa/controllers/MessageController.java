package io.github.flea777.pesquisa.controllers;

import io.github.flea777.pesquisa.dtos.MessageDTO;
import io.github.flea777.pesquisa.dtos.StatusDTO;
import io.github.flea777.pesquisa.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private StatusService service;

    @GetMapping(value = "/{user}")
    public StatusDTO findStatusByUser(@PathVariable String user) {
        StatusDTO status = service.findStatusByUser(user);
        return status;
    }

    @GetMapping
    public List<StatusDTO> findAllUsers() {
        List<StatusDTO> data = service.findAllUsers();
        return data;
    }

    @PostMapping
    public StatusDTO newStatus(@RequestBody MessageDTO dto) {
        StatusDTO status = service.updateOrCreateStatus(dto);
        return status;
    }

    @PutMapping
    public StatusDTO updateStatus(@RequestBody MessageDTO dto) {
        StatusDTO status = service.updateOrCreateStatus(dto);
        return status;
    }
}
