package com.wobReporting.server.repository.helper.reporter.Data;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface MonthlyIBaseStatistics extends IBaseStatistics {

    public LocalDate getActMonth();

}
