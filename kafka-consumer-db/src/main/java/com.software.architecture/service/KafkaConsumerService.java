package com.software.architecture.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.software.architecture.model.Incident;
import com.software.architecture.model.IncidentEvent;
import com.software.architecture.repo.IncidentRepository;
import org.slf4j.Logger;
import org.springframework.kafka.support.KafkaHeaders;
import com.fasterxml.jackson.databind.ObjectMapper;

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

  @KafkaListener(
    topics = {"create-incident", "update-incident"}, 
    groupId = "myGroup"
    // containerFactory = "concurrentKafkaListenerContainerFactory"
  )
  public void consume(String incidentEvent, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

    try {

      LOGGER.info(String.format("Topic -> {}"), topic);
      LOGGER.info(String.format("Incident Event -> {}"), incidentEvent);

      ObjectMapper mapper = new ObjectMapper();
      IncidentEvent event = mapper.readValue(incidentEvent, IncidentEvent.class);

      this.handleEvent(event, topic);

      // log all saved incidents
      LOGGER.info("Saved Incidents -> {}", incidentRepository.findAll());

    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  public void handleEvent(IncidentEvent incidentEvent, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
    switch(topic) {
      case "create-incident": {
        LOGGER.info(String.format("inside create -> {}"), topic);

        // save new incident
        Incident incident = new Incident(incidentEvent.title, incidentEvent.description, incidentEvent.address,
            incidentEvent.phone, incidentEvent.createdDate);
        incidentRepository.save(incident);
        break;
      }
      case "update-incident": {
        LOGGER.info(String.format("inside update -> {}"), topic);

        Incident incident = new Incident(incidentEvent.id, incidentEvent.title, incidentEvent.description, incidentEvent.address,
            incidentEvent.phone, incidentEvent.createdDate);

        // update incident
        incidentRepository.save(incident);
        break;
      }
      case "delete-incident": {
        // delete incident
        incidentRepository.deleteById(incidentEvent.id);
        break;
      }
    }
  }
}