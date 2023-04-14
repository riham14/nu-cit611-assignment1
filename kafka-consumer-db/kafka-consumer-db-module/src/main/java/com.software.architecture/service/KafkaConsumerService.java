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
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

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
    groupId = "myGroup"
    // containerFactory = "concurrentKafkaListenerContainerFactory"
  )
  public void consume(String incidentEvent) {
    LOGGER.info(String.format("Incident received -> %s", incidentEvent));

    try{
      JSONParser parser = new JSONParser();
      JSONObject jsonObject = (JSONObject) parser.parse(incidentEvent);

      String title = (String) jsonObject.get("title");
      String description = (String) jsonObject.get("description");
      String address = (String) jsonObject.get("address");
      String phone = (String) jsonObject.get("phone");

  
      LOGGER.info(String.format("Incident json -> {}"), jsonObject);
      LOGGER.info(String.format("Incident title -> %s", title));
  
  
  
      // save new incident
      Incident incident = new Incident(title, description, address, phone);  
      incidentRepository.save(incident);
  
      // log all saved incidents
      LOGGER.info("Saved Incidents -> {}", incidentRepository.findAll());

    } catch (Exception ex){
      ex.printStackTrace();
    }

  }
}