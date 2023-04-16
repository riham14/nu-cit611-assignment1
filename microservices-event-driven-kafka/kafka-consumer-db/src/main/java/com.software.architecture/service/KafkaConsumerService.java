package com.software.architecture.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.software.architecture.model.Incident;
import com.software.architecture.model.IncidentEvent;
import com.software.architecture.repo.IncidentRepository;
import org.slf4j.Logger;

@Component
@Service
public final class KafkaConsumerService {

  int number;

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);

  @Autowired
  IncidentRepository incidentRepository;

  public KafkaConsumerService(IncidentRepository incidentRepository) {
    this.incidentRepository = incidentRepository;
  }

  @KafkaListener(topics = "create-incident", groupId = "myGroup", containerFactory = "concurrentKafkaListenerContainerFactory")
  public void consume(IncidentEvent incidentEvent) {

    try {

      LOGGER.info(String.format("Incident Event -> {}"), incidentEvent);

      // save new incident
      Incident incident = new Incident(incidentEvent.title, incidentEvent.description, incidentEvent.address,
          incidentEvent.phone);
      incidentRepository.save(incident);

      // log all saved incidents
      LOGGER.info("Saved Incidents -> {}", incidentRepository.findAll());

    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}