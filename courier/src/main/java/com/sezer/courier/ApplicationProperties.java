package com.sezer.courier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {
    private LocationQueueConfig locationQueueConfig;

    private LogQueueConfig logQueueConfig;

    public LocationQueueConfig getLocationQueueConfig() {
        return locationQueueConfig;
    }

    public void setLocationQueueConfig(LocationQueueConfig locationQueueConfig) {
        this.locationQueueConfig = locationQueueConfig;
    }

    public LogQueueConfig getLogQueueConfig() {
        return logQueueConfig;
    }

    public void setLogQueueConfig(LogQueueConfig logQueueConfig) {
        this.logQueueConfig = logQueueConfig;
    }

    public static class LogQueueConfig {
        private String name;
        private String exchange;
        private String routingKey;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getRoutingKey() {
            return routingKey;
        }

        public void setRoutingKey(String routingKey) {
            this.routingKey = routingKey;
        }
    }

    public static class LocationQueueConfig {
        private String name;
        private String exchange;
        private String routingKey;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getRoutingKey() {
            return routingKey;
        }

        public void setRoutingKey(String routingKey) {
            this.routingKey = routingKey;
        }
    }
}
