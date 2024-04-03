package com.knightslife.knights.Job.mapper;

import java.util.List;

import com.knightslife.knights.Job.Job;
import com.knightslife.knights.Job.dto.JobWithCompanyDTO;
import com.knightslife.knights.Job.external.Company;
import com.knightslife.knights.Job.external.Review;

public class JobMapper {
    public static JobWithCompanyDTO mapToJobWithCompanyDTO(Job job, Company company, List<Review> reviews){
        // Now we need to create a object and then start mapping 
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setCompany(company);
        jobWithCompanyDTO.setReviews(reviews);
        return jobWithCompanyDTO;
    }
    
}
