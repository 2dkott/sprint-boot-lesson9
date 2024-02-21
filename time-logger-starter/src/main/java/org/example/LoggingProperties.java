package org.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("timer.logging")
public class LoggingProperties {

    private boolean methodData = true;

}
