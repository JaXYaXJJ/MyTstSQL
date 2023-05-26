package edu.hackeru.evgenyzakalinsky.mytstsql.service;

import edu.hackeru.evgenyzakalinsky.mytstsql.dto.band.PostRequest;
import edu.hackeru.evgenyzakalinsky.mytstsql.dto.band.PostResponse;
import edu.hackeru.evgenyzakalinsky.mytstsql.entity.Band;
import edu.hackeru.evgenyzakalinsky.mytstsql.error.IDNotFoundException;
import edu.hackeru.evgenyzakalinsky.mytstsql.error.UniqueConstraintViolationException;
import edu.hackeru.evgenyzakalinsky.mytstsql.repository.BandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BandService {

    //controller -> service -> repository -> entity
    //talk to the repository:
    //service responsible for business logic**

    //props:
    private final BandRepository bandRepository;

    //methods:
    //INSERT INTO Band
    public PostResponse save(PostRequest requestDto) { //@UniqueConstraint

        //copy the dto to new department (entity) for db saving:
        var band = new Band();

        band.setBandName(requestDto.bandName());
        band.setGenre(requestDto.genre());
        band.setCountry(requestDto.country());

        try {
            var saved = bandRepository.save(band);

            return new PostResponse(
                    saved.getId(),
                    saved.getBandName(),
                    saved.getGenre(),
                    saved.getCountry(),
                    saved.getBandName() + ": Saved to DB"
            );


        } catch (DataIntegrityViolationException e) {
            /*
            return new PostResponse(
                    -1, "", "", "", "..."
            );
            */
            throw new UniqueConstraintViolationException(
                    band.getBandName() + " already exists in the DB. Band name must be unique."
            );
        }
    }

    public boolean delete(Long id) {
        var exists = bandRepository.existsById(id.toString());
        if (!exists) return false;

        bandRepository.deleteById(id.toString());

        return true;
    }

    public Band updateBand(Band band) {

        //a. find the band by id:
        Optional<Band> b = bandRepository.findBandById(band.getId());

        //b. if dbBand is null -> throw runtime exception:
        b.orElseThrow();

        return bandRepository.save(band);
    }

    //SELECT * FROM Band
    public List<Band> getAll() {

        return bandRepository.findAll();
    }

    public Band getById(long id) {

        return bandRepository.findById(String.valueOf(id)).orElseThrow(
                //message: "can't find a resource with id"
                () -> new IDNotFoundException(id)
        );
    }

    public List<Band> getBandByCountryOrName(String country, String bandName) {

        return bandRepository.findBandByCountryContainingIgnoreCaseOrBandNameContainingIgnoreCase(
                country, bandName
        );
    }

    public List<Band> getBandByCountry(String country) {

        return bandRepository.findBandByCountryIgnoreCase(country);
    }
}
