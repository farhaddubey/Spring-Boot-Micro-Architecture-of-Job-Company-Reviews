package com.knightslife.knights.Job;
import java.util.List;

import com.knightslife.knights.Job.dto.JobWithCompanyDTO;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);
    JobWithCompanyDTO getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id, Job updatedJob);
    
}