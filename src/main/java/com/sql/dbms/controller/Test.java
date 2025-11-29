package com.sql.dbms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sql.dbms.pojo.Station;
import com.sql.dbms.service.StationService;

import jakarta.annotation.PostConstruct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/station")
@CrossOrigin("http://localhost:5173/")
public class Test {

    @Autowired
    private StationService  stationService;

    @PostConstruct
    public void init(){
        System.out.println("Station Controller initialized");
        stationService.addStation(new Station("ST001", "Austin", "TX", 30.2672, -97.7431));
        stationService.addStation(new Station("ST002", "Boston", "MA", 42.3601, -71.0589));
        stationService.addStation(new Station("ST003", "Chicago", "IL", 41.8781, -87.6298));
        stationService.addStation(new Station("ST004", "Orlando", "FL", 28.5383, -81.3792));
        stationService.addStation(new Station("ST005", "Seattle", "WA", 47.6062, -122.3321));
         stationService.addStation(new Station("ST006", "Eeattle", "WA", 47.6062, -122.3321));
        
    }

    @GetMapping("/vowles")
    public ResponseEntity<List<Station>> getVowelsCityStation() {
        return ResponseEntity.ok().body(stationService.getVowelsCityStation());
    }

    @GetMapping("/vowles/end")
    public ResponseEntity<List<Station>> getVowelsCityStationEnd() {
        return ResponseEntity.ok().body(stationService.getVowelsCityStationEnd());
    }
    @GetMapping("/vowles/end/start")
    public ResponseEntity<List<Station>> getVowelsCityStationEndStart() {
        return ResponseEntity.ok().body(stationService.getVowelsCityStationEndStart());
    }
    

    /*
   Endpoint 2: Geo Radius Search
    GET /stations/geo/radius?lat=18.52&long=73.85&radius=15
    Requirements:
    Use Haversine formula in SQL/JPA
    Return nearest stations sorted by distance
    Show distance in response (DTO)
    Industry use-case: nearest railway stations, metro stops, fuel pumps.
    */

    @GetMapping("/geo/radius")
    public ResponseEntity<List<Station>> getStationNearby(@RequestParam double lat , @RequestParam double longi , @RequestParam int radius) {
        return  ResponseEntity.ok().body(stationService.getStationNearby(lat, longi, radius));
    }
    

}

