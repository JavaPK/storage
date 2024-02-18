package pl.office.storage.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class FileServiceImpl implements FileService {

	private final ObjectMapper objectMapper;

	@Value("${storage.directory}")
	private String basePath;

	@Override
	public UUID upload(MultipartFile file, String metadata) throws IOException {
		Map<String, String> metadataMap = objectMapper.readValue(metadata, Map.class);
		String id = metadataMap.get("id");

		try (FileOutputStream fileOutputStream = new FileOutputStream(basePath + "\\" + id);
			 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
			bufferedOutputStream.write(file.getBytes());
		} catch (IOException e) {
			throw new RuntimeException("Problem with saving file");
		}

		return UUID.fromString(id);
	}
}
