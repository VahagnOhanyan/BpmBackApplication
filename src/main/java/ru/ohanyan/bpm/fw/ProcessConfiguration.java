package ru.ohanyan.bpm.fw;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * todo Document type ProcessConfiguration
 */
@Configuration
@Component("processConfiguration")
public class ProcessConfiguration {

    private String period = "R/PT5S";

    public String getPeriod() {
        return period;
    }
}

