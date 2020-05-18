package org.bajiepka.milkings.web;

import org.bajiepka.milkings.model.Milking;
import org.bajiepka.milkings.service.MilkingService;
import org.bajiepka.milkings.web.dto.MilkingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class MilkingController {

    private final MilkingService milkingService;

    @Autowired
    public MilkingController(MilkingService milkingService) {
        this.milkingService = milkingService;
    }

    @GetMapping("/family/{child}/milkings")
    public List<MilkingDto> getMilkingById(@PathVariable String child){
        return milkingService.findAll();
    }

    @GetMapping("/family/{child}/milkings/{id}")
    public MilkingDto getMilkingById(
            @PathVariable String child,
            @PathVariable Long id){
        return milkingService.findById(id);
    }

    @PostMapping("/family/{child}/milkings")
    public ResponseEntity<Void> createMilking(
            @PathVariable String child,
            @RequestBody MilkingDto dto){
        MilkingDto milking = milkingService.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(milking.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/family/{child}/milkings/{id}")
    public ResponseEntity<MilkingDto> save(
            @PathVariable String child,
            @PathVariable Long id,
            @RequestBody MilkingDto dto){
        MilkingDto milking = milkingService.save(dto);
        return new ResponseEntity<MilkingDto>(milking, HttpStatus.OK);
    }

    @DeleteMapping("/family/{child}/milkings/{id}")
    public ResponseEntity<Void> deleteMilking(
            @PathVariable String child,
            @PathVariable long id){
        Milking milkingToDelete = milkingService.deleteById(id);
        if (Objects.isNull(milkingToDelete)) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
