package com.sql.dbms.service;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sql.dbms.pojo.Station;
import com.sql.dbms.repositry.StationRepositry;

@Service
public class StationService {

    @Autowired
    private StationRepositry stationRepositry;

    public List<Station> getVowelsCityStation() {
        return Stream.of("A", "E", "I", "O", "U")
                .flatMap(vowel -> stationRepositry.findDistinctByCityStartingWithIgnoreCase(vowel).stream())
                .toList();
    }

    public List<Station> getVowelsCityStationEnd() {
        return Stream.of("A", "E", "I", "O", "U")
                .flatMap(vowel -> stationRepositry.findDistinctByCityEndingWithIgnoreCase(vowel).stream())
                .toList();
    }

    public List<Station> getVowelsCityStationEndStart() {
        return Stream.of("A", "E", "I", "O", "U")
                .flatMap(vowel ->  {
                    return  Stream.of("A", "E", "I", "O", "U")
                            .flatMap(vowelEnd -> stationRepositry
                                    .findDistinctByCityStartingWithIgnoreCaseAndCityEndingWithIgnoreCase(vowel,
                                            vowelEnd)
                                    .stream());
                })
                .toList();
    }

    public List<Station> getStationNearby(double lat , double longi , int radius) {
        double[] searchArea = boundingBox(lat, longi, radius);
        System.out.println(searchArea[0] + " " +  searchArea[1] + " " + searchArea[2]  + " " + searchArea[3]);
        return stationRepositry.findByLatNBetweenAndLongWBetween(searchArea[0] , searchArea[1], searchArea[2] ,searchArea[3]);
    }

    public void addStation(Station station) {
        stationRepositry.save(station);
    }

    public static double[] boundingBox(double lat, double lon, int radiusMeters) {

    double radiusInDegrees = (double)radiusMeters / 111000.0;

    double minLat = lat - radiusInDegrees;
    double maxLat = lat + radiusInDegrees;

    double deltaLon = radiusMeters / (111000.0 * Math.cos(Math.toRadians(lat)));

    double minLon = lon - deltaLon;
    double maxLon = lon + deltaLon;

    return new double[] { minLat, maxLat, minLon, maxLon };
}

}
