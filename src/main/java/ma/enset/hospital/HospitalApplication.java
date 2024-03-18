package ma.enset.hospital;

import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        /*
        // En utilisant Constructeur sans paramètre
        Patient patient1 = new Patient();
        patient1.setId(null);
        patient1.setNom("Hassan");
        patient1.setDateNaissance(new Date());
        patient1.setMalade(false);
        patient1.setScore(50);

        // En utilisant Constructeur avec paramètre
        Patient patient2 = new Patient(null, "Ahmed", new Date(), false, 60);

        // En utilisant Builder
        Patient patient3 = Patient.builder().nom("Karim").malade(true).score(70).build();
        */

        patientRepository.save(new Patient(null, "Hassan", new Date(), false, 50));
        patientRepository.save(new Patient(null, "Ahmed", new Date(), false, 60));
        patientRepository.save(new Patient(null, "Karim", new Date(), true, 70));


    }
}
