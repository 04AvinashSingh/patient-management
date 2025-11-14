package com.pm.patient_service.kafka;

import com.pm.patient_service.model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import patient.events.PatientEvent;

@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String,byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String,byte[]> KafkaTemplate) {
        this.kafkaTemplate = KafkaTemplate;
    }
    public void sendEvent(Patient patient){
        PatientEvent event=PatientEvent.newBuilder()
                .setPatientId(patient.getId().toString())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();

        try {
            kafkaTemplate.send("patient",event.toByteArray());
        }catch (Exception e){
            log.error("error sending PatientCreated event: {}",event);
        }
    }
}
