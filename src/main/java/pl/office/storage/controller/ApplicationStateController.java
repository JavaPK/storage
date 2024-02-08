package pl.office.storage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/storage/state")
public class ApplicationStateController {

    @GetMapping
    public ResponseEntity<Void> getApplicationState(){
        return ResponseEntity.ok()
                .build();
    }
}
