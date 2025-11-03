package ee.taavi.decathlon.controller;

import ee.taavi.decathlon.entity.Athlete;
import ee.taavi.decathlon.entity.Performance;
import ee.taavi.decathlon.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AthleteController {

    @Autowired
    private AthleteRepository athleteRepository;

    @GetMapping("athletes")
    public List<Athlete> getAthletes(){
        return athleteRepository.findAll();
    }

    @PostMapping("athletes")
    public Athlete addAthlete(@RequestBody Athlete athlete){
        if(athlete.getName() == null || athlete.getName().isEmpty()){
            throw new RuntimeException("No name was provided");
        }
        if(athlete.getNation() == null || athlete.getNation().isEmpty()){
            throw new RuntimeException("Nation cannot be empty");
        }
        if(athlete.getAge() <= 0){
            throw new RuntimeException("Age must be provided");
        }
        return athleteRepository.save(athlete);
    }
}
