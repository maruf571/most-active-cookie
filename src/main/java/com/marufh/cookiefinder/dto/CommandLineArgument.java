package com.marufh.cookiefinder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CommandLineArgument {
    private String file;
    private LocalDate date;
}
