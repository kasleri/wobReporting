package com.wobReporting.server.repository.helper.reporter.Data;

import java.time.LocalDate;

public interface IBestLister {
    LocalDate getMonth();

    String getBestLister();
}
