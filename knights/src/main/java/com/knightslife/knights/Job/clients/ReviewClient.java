package com.knightslife.knights.Job.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @FeignClient(name = "REVIEW-SERVICE")
@FeignClient(name = "REVIEW-SERVICE", url="${review-service.url}")
public interface ReviewClient {
    @GetMapping("/reviews")
    List<com.knightslife.knights.Job.external.Review> getReviews(@RequestParam("companyId") Long companyId);
}
