package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.exception.EmailAlreadyExistsException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository=  patientRepository;
    }

    public List<PatientResponseDTO> getPatient(){
        List<Patient> patients=patientRepository.findAll();

        return patients.stream().map(PatientMapper::toDTO).toList();


    }

    public PatientResponseDTO createpatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email" +"already exists"+patientRequestDTO.getEmail());

        }


        Patient newPatient=patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        return PatientMapper.toDTO(newPatient);

    }

    public PatientResponseDTO updatepatient(UUID id, PatientRequestDTO patientRequestDTO){
        Patient patient=patientRepository.findById(id).orElseThrow(
                ()-> new PatientNotFoundException("Patient not found with ID: "+ id));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
            throw new EmailAlreadyExistsException("A patient with this email" +"already exists"+patientRequestDTO.getEmail());

        }

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        if (patientRequestDTO.getRegisteredDate() != null && !patientRequestDTO.getRegisteredDate().isEmpty()) {
            patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        }

        if (patientRequestDTO.getRegisteredDate() != null && !patientRequestDTO.getRegisteredDate().isEmpty()) {
            patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        }


        Patient updatedPatient=patientRepository.save(patient);

        return PatientMapper.toDTO(updatedPatient);


    }


    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }



}
