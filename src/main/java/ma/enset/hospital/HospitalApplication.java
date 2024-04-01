package ma.enset.hospital;

import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

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

    @Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder = passwordEncoder();
        return args -> {
            UserDetails user11 = jdbcUserDetailsManager.loadUserByUsername("user11");
            if(user11 == null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            UserDetails user22 = jdbcUserDetailsManager.loadUserByUsername("user22");
            if(user22 == null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
                );
            UserDetails admin2 = jdbcUserDetailsManager.loadUserByUsername("admin2");
            if(admin2 == null)
                jdbcUserDetailsManager.createUser(
                        User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
                );
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
