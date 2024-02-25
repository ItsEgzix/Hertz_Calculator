package com.example.hertz_calculator;

public class FrequencyCalculator {
    public static double calculateFrequency(double tempoBpm, int beatDuration) {
        // Convert the tempo from beats per minute to beats per second
        double beatsPerSecond = tempoBpm / 60;

        // Calculate the frequency of the specified beat duration in Hz
        double frequencyHz = beatsPerSecond * beatDuration/4;

        return frequencyHz;
    }
    }
