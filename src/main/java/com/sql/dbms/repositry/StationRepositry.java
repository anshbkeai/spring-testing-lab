package com.sql.dbms.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sql.dbms.pojo.Station;

@Repository
public interface StationRepositry extends JpaRepository<Station,String> {

    List<Station> findDistinctByCityStartingWithIgnoreCase(String prefix);
    List<Station> findDistinctByCityEndingWithIgnoreCase(String suffix);

    List<Station> findDistinctByCityStartingWithIgnoreCaseAndCityEndingWithIgnoreCase(String prefix , String suffix);

    List<Station> findByLatNBetweenAndLongWBetween(double minLat, double maxLat,
                                                    double minLon, double maxLon);

}
