package com.knightscom.knightscom.company.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Service;

import com.knightscom.knightscom.company.CompanyService;
import com.knightscom.knightscom.company.dto.ReviewMessage;

@Service
public class ReviewMessageConsumer {
    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService){
        this.companyService= companyService;
    }

    @RabbitHandler(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        companyService.updateCompanyRating(reviewMessage);
    }
    
}
