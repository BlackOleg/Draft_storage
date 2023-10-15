package olegivanov.controllers;

import lombok.AllArgsConstructor;
import olegivanov.dtos.FileResponse;
import olegivanov.service.FileService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/list")
public class ListController {

    private final FileService service;

    @GetMapping
    List<FileResponse> getAllFiles(@RequestHeader("auth-token") String authToken, @RequestParam("limit") Integer limit) {
        return service.getAllFiles(authToken, limit);
    }
}
