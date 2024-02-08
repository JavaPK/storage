package pl.office.storage.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.office.storage.model.FileDocument;

import java.util.UUID;

@Repository
public interface FileDocumentRepository extends MongoRepository<FileDocument, UUID> {
}
