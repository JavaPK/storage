package pl.office.storage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;

public interface FileService {
    UUID upload(HttpServletRequest request) throws IOException;
}
