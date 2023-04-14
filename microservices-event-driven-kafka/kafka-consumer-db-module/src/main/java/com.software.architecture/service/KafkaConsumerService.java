package com.software.architecture.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.software.architecture.model.IncidentEvent;
import com.software.architecture.model.Incident;
import com.software.architecture.repo.IncidentRepository;

@Component
@Service
public final class KafkaConsumerService {

  private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);

  @Autowired
  IncidentRepository incidentRepository;

  public KafkaConsumerService(IncidentRepository incidentRepository) {
    this.incidentRepository = incidentRepository;
  }

  @KafkaListener(
    topics = "incident_change", 
    groupId = "myGroup",
    containerFactory = "concurrentKafkaListenerContainerFactory"
  )
  public void consume(IncidentEvent incidentEvent) {
    
    try{
      
      LOGGER.info(String.format("Incident Event -> {}"), incidentEvent);
    
      // save new incident
      Incident incident = new Incident(incidentEvent.title, incidentEvent.description, incidentEvent.address, incidentEvent.phone);  
      incidentRepository.save(incident);
  
      // log all saved incidents
      LOGGER.info("Saved Incidents -> {}", incidentRepository.findAll());

    } catch (Exception ex){
      ex.printStackTrace();
    }

  }
}