package org.bajiepka.milkings.util;

import org.bajiepka.milkings.model.Milking;
import org.bajiepka.milkings.web.dto.MilkingDto;

import java.time.Duration;
import java.util.Objects;

public class CommonMapper {
    public static MilkingDto toMilkingDto(Milking milking) {
        return new MilkingDto(
                milking.getId(),
                milking.getDescription(),
                milking.getLastMilking(),
                milking.getMilkedAt(),
                getDelay(milking));
    }

    private static Long getDelay(Milking milking) {
        if (Objects.nonNull(milking.getLastMilking())) {
            return Duration.between(milking.getLastMilking(), milking.getMilkedAt()).toMinutes();
        }
        return null;
    }

    public static Milking toMilking(MilkingDto dto) {
        Milking milking = new Milking();
        milking.setDescription(dto.getDescription());
        milking.setMilkedAt(dto.getMilkedAt());
        milking.setLastMilking(dto.getLastMilking());
        return milking;
    }
}
