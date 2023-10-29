package olegivanov.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import olegivanov.dtos.FileResponse;
import olegivanov.service.FileService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j

@RestController
@AllArgsConstructor
@RequestMapping("/list")
public class ListController {

    private final FileService service;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    List<FileResponse> getAllFiles(@RequestHeader("auth-token") String authToken, @RequestParam("limit") Integer limit) {
        log.info("List controller is working  with authtoken {" + authToken + "}  and limit " + limit);
        return service.getAllFiles(authToken, limit);
    }
}
