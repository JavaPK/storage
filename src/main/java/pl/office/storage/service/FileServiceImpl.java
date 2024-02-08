package pl.office.storage.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.office.storage.model.FileDocument;
import pl.office.storage.repository.FileDocumentRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class FileServiceImpl implements FileService {

    private final FileDocumentRepository fileDocumentRepository;
    private final ObjectMapper objectMapper;

    @Value("${storage.directory}")
    private String basePath;

    @Transactional
    @Override
    public UUID upload(MultipartFile file, String metadata) throws IOException {
        Map<String, String> metadataMap = objectMapper.readValue(metadata, Map.class);


        UUID id  = UUID.randomUUID();
        int lastIndexOf = file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastIndexOf);
        String fileName = file.getOriginalFilename().substring(0, lastIndexOf);

        String sha = Hashing.sha512().hashBytes(file.getBytes()).toString();

        FileDocument fileDocument = FileDocument.builder()
                .id(id)
                .name(fileName)
                .extension(extension)
                .contentLength(file.getSize())
                .sha(sha)
                .metadata(metadataMap)
                .build();

        try(FileOutputStream fileOutputStream = new FileOutputStream(basePath + "\\" + id)){
            fileOutputStream.write(file.getBytes());
        } catch (IOException e){
            throw new RuntimeException("Problem with saving file");
        }

        return fileDocumentRepository.save(fileDocument).getId();
    }
}
