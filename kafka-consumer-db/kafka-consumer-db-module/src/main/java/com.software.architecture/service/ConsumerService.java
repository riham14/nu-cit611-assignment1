package com.software.architecture.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.software.architecture.model.Incident;
import com.software.architecture.repo.IncidentRepository;
import org.json.simple.JSONObject;

@Component
@Service
public final class ConsumerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

  @Autowired
  IncidentRepository incidentRepository;

  public ConsumerService(IncidentRepository incidentRepository) {
    this.incidentRepository = incidentRepository;
  }

  @KafkaListener(
    topics = "incident_change", 
    groupId = "myGroup",
    containerFactory = "concurrentKafkaListenerContainerFactory"
  )
  public void consume(Incident incidentEvent) {
    LOGGER.info(String.format("Incident received -> %s", incidentEvent));

    // JSONParser parser = new JSONParser();
    // JSONObject jsonObject = (JSONObject) new JSONObject(message);

    Incident incident = new Incident(incidentEvent.title, incidentEvent.description, incidentEvent.address, incidentEvent.phone);
    // incident.setAddress(message);

    incidentRepository.save(incident);

    LOGGER.info("Saved Incidents -> {}", incidentRepository.findAll());

  }
}