package com.justas.project.library.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

@Builder
@Getter
@ToString
public class History {
    private int playgroundId;
    private int childId;
    private LocalDateTime startTime;
    @Setter
    private LocalDateTime endTime;
    @Setter
    private Duration stayDuration;

}
