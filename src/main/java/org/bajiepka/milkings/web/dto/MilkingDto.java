package org.bajiepka.milkings.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MilkingDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("description")
    private String description;

    @JsonProperty("lastMilking")
    private LocalDateTime lastMilking;

    @JsonProperty("milkedAt")
    private LocalDateTime milkedAt;

    @JsonProperty("milkingDelay")
    private Long delay;
}
