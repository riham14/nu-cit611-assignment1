
package com.software.architecture.repo;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.software.architecture.model.Incident;

public interface IncidentRepository extends MongoRepository<Incident, String> {
  List<Incident> findByTitle(String title);
}