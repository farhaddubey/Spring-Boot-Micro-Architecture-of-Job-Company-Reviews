package com.knightslife.knights.Job.impl;

public @interface CircuitBreaker {

    String fallbackMethod();

    String name();

}
