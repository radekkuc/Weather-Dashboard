package com.example.WeatherService.Weather.WeatherDto;

import java.time.LocalTime;

public class Current {
    private String last_updated;
    private float temp_c;
    private float temp_f;
    private Condition condition;
    private float pressure_mb;

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public float getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(float temp_c) {
        this.temp_c = temp_c;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public float getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(float pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    public float getTemp_f() {
        return temp_f;
    }

    public void setTemp_f(float temp_f) {
        this.temp_f = temp_f;
    }
}
