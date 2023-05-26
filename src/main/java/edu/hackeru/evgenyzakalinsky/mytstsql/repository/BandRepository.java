package edu.hackeru.evgenyzakalinsky.mytstsql.repository;

import edu.hackeru.evgenyzakalinsky.mytstsql.entity.Band;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BandRepository extends JpaRepository<Band, String> {

    //controller -> service -> repository -> entity
    //talk to the entity:

    //derived query methods:
    List<Band> findBandByCountryContainingIgnoreCaseOrBandNameContainingIgnoreCase(
            String country, String bandName
    );

    //SELECT * FROM Band WHERE bandName = ...
    List<Band> findByBandNameIgnoreCase(String bandName);

    //SELECT * FROM Band WHERE country = ...
    List<Band> findBandByCountryIgnoreCase(String country);
    Optional<Band> findBandById(Long id);
}
