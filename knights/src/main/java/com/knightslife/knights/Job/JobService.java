package com.knightslife.knights.Job;
import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJobById(Long id, Job updatedJob);
    
}