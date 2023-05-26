package edu.hackeru.evgenyzakalinsky.mytstsql.controller;

import edu.hackeru.evgenyzakalinsky.mytstsql.dto.band.PostRequest;
import edu.hackeru.evgenyzakalinsky.mytstsql.dto.band.PostResponse;
import edu.hackeru.evgenyzakalinsky.mytstsql.entity.Band;
import edu.hackeru.evgenyzakalinsky.mytstsql.service.BandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BandController {

    //controller -> service -> repository -> entity
    //talk to the service:
    //controller responsible for ui + status + response**

    private final BandService bandService;

    @GetMapping("/bands")
    public List<Band> getBand() {

        return bandService.getAll();
    }

    @DeleteMapping("/bands/{id}")
    public Map<String, String> removeBand(@PathVariable long id) {
        var isDelete = bandService.delete(id);

        //ui:
        return Map.of("Message", isDelete ? "Deleted" : "Not Deleted");
    }

    @PutMapping("/bands")
    public Band update(@RequestBody Band band) {

        return bandService.updateBand(band);
    }

    @GetMapping("/bands-by-country")
    public List<Band> getBandByCountry(@RequestBody Map<String, String> map) {

        return bandService.getBandByCountry(map.get("country"));
    }

    @GetMapping("/bands-by-country-or-name/q")
    public List<Band> getBand(
            @RequestParam(required = false, defaultValue = "") String country,
            @RequestParam(required = false, defaultValue = "") String bandName
    ) {
        return bandService.getBandByCountryOrName(country, bandName);
    }

    @GetMapping("/bands/q")
    //query parameter:
    public List<Band> getBandByCountry(@RequestParam String country) {

        return bandService.getBandByCountry(country);
    }

    //dto:

    @PostMapping("/bands")
    public PostResponse saveBand(@RequestBody PostRequest dto) {

        return bandService.save(dto);
    }

    @PostMapping("/bands-dto")
    public ResponseEntity<PostResponse> add(@RequestBody PostRequest dto) {

       var response = bandService.save(dto);

        //created = status 201 + uri for the added entity + body
        return ResponseEntity.created(URI.create(
                "http://localhost:8080/api/v1/bands-dto/"
                        + response.id())).body(response);
    }

    @GetMapping ("/bands-dto/{id}")
    ResponseEntity<Band> getById(@PathVariable long id) {
        var result = bandService.getById(id);
        return ResponseEntity.ok(result);
    }
}
