package pl.office.storage.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.office.storage.service.FileService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/storage")
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<UUID> upload(@RequestParam(name = "file") MultipartFile file,
                                       @RequestParam(name = "metadata") String metadata) throws IOException {

        return ResponseEntity
                .ok(fileService.upload(file, metadata));
    }
}
