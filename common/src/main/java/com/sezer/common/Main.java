package com.sezer.common;

import com.sezer.common.util.DistanceCalculatorUtil;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        double v = DistanceCalculatorUtil.haversineDistance(40.9632463,29.0630908,43.9672463, 29.0630908);
        int v1 = (int)(v / 300 * 3600);
        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
        System.out.println(zonedDateTime);
    }
}
