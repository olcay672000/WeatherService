package org.csystem.app.weather.repository.data.repository;


import org.csystem.app.weather.repository.data.entity.PlaceInfo;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IPlaceInfoRepository extends CrudRepository<PlaceInfo, Integer> {
    PlaceInfo findByPlaceName(@Param("name") String name);

    @Query(value = "select * from place_info pi inner join weather_information wi on pi.place_info_id = wi.place_info_id\n" +
            "where date_part('month', to_timestamp(date_time,'yyyy-mm-dd hh24:mi:ss')) = :month", nativeQuery = true)
    Iterable<PlaceInfo> findByMonth(@Param("month") int month); //yenile

    @Query(value = "select * from place_info pi inner join weather_information wi on pi.place_info_id = wi.place_info_id\n" +
            "where date_part('month', to_timestamp(date_time,'yyyy-mm-dd hh24:mi:ss')) = :month" +
            " and date_part('year', to_timestamp(date_time,'yyyy-mm-dd hh24:mi:ss')) = :year", nativeQuery = true)
    Iterable<PlaceInfo> findByMonthAndYear(@Param("month") int month, @Param("year") int year); //yenile

    @Query(value = "select * from place_info pi inner join weather_information wi on pi.place_info_id = wi.place_info_id\n" +
            "where date_part('month', to_timestamp(date_time,'yyyy-mm-dd hh24:mi:ss')) = :month" +
            " and date_part('day', to_timestamp(date_time,'yyyy-mm-dd hh24:mi:ss')) = :day", nativeQuery = true)
    Iterable<PlaceInfo> findByMonthAndDay(@Param("month") int month, @Param("day") int day); //yenile

}
