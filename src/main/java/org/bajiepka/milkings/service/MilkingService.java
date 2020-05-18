package org.bajiepka.milkings.service;

import org.bajiepka.milkings.model.Milking;
import org.bajiepka.milkings.repository.MilkingRepository;
import org.bajiepka.milkings.util.CommonMapper;
import org.bajiepka.milkings.web.dto.MilkingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MilkingService {

    private final MilkingRepository milkingRepository;

    @Autowired
    public MilkingService(MilkingRepository milkingRepository) {
        this.milkingRepository = milkingRepository;
    }

    public MilkingDto findById(long id) {
        return milkingRepository.findById(id)
                .map(CommonMapper::toMilkingDto)
                .orElse(null);
    }

    private Milking findByIdInternal(long id) {
        return milkingRepository.findById(id).orElse(null);
    }

    public Milking deleteById(long id) {
        Milking milkingToDelete = findByIdInternal(id);
        if (!Objects.isNull(milkingToDelete)){
            milkingRepository.deleteById(id);
            return milkingToDelete;
        } else {
            return null;
        }
    }

    public List<MilkingDto> findAll(){
        Sort sortByMilkDateDesc = Sort.by(Sort.Direction.ASC, "id");
        return milkingRepository.findAll(sortByMilkDateDesc).stream()
                .map(CommonMapper::toMilkingDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MilkingDto save(MilkingDto dto) {
        if (dto.getId().equals(-1L) || dto.getId().equals(0L)){

            Milking createMilking = Optional.of(dto)
                    .map(CommonMapper::toMilking)
                    .orElseThrow(() -> new IllegalStateException("Невозможно сохранить: " + dto.getDescription()));

            createMilking.setLastMilking(milkingRepository.getLastMilkingTime().orElse(null));
            milkingRepository.save(createMilking);
            return dto;
        } else {
            Milking milkingById = milkingRepository.getOne(dto.getId());
            milkingById.setDescription(dto.getDescription());
            milkingRepository.save(milkingById);
            return dto;
        }
    }
}
