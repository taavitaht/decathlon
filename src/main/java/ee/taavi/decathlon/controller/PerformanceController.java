package ee.taavi.decathlon.controller;

import ee.taavi.decathlon.entity.Performance;
import ee.taavi.decathlon.entity.ScoringService;
import ee.taavi.decathlon.repository.PerformanceRepository;
import ee.taavi.decathlon.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PerformanceController {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private ScoringService scoringService;

    @GetMapping("performances")
    public List<Performance> getPerformances(){
        return performanceRepository.findAll();
    }

    @PostMapping("performances")
    public Performance addPerformance(@RequestBody Performance performance){
        if(performance.getAthlete() == null){
            throw new RuntimeException("No athlete was provided");
        }
        if(performance.getEvent() == null || performance.getEvent().isEmpty()){
            throw new RuntimeException("Event cannot be empty");
        }
        if(performance.getPerformance() == null || performance.getPerformance() <= 0){
            throw new RuntimeException("Performance must be provided");
        }
        int points = ScoringService.calculatePoints(performance.getEvent(), performance.getPerformance());
        performance.setPoints(points);
        return performanceRepository.save(performance);
    }
}
