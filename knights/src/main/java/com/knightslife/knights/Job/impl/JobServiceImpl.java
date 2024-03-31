package com.knightslife.knights.Job.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.knightslife.knights.Job.Job;
import com.knightslife.knights.Job.JobRepository;
import com.knightslife.knights.Job.JobService;
import com.knightslife.knights.Job.dto.JobWithCompanyDTO;
import com.knightslife.knights.Job.external.Company;


@Service
public class JobServiceImpl implements JobService {
    // private List<Job> jobs = new ArrayList<>();

    JobRepository jobRepository;
    

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        // Implementing DTO 
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        for (Job job: jobs){
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            JobWithCompanyDTO.setJob(job);

            Company company = restTemplate.getForObject("http://localhost:8081/companies/", job.getCompanyId(), Company.class);
        }
        Company company = restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
        System.out.println("COMPANY :"+ company.getName());
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        // jobs.add(job);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        // for(Job job: jobs){
        //     if(job.getId().equals(id)){
        //         return job;
        //     }
        // }
        // return null;
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        // Iterator<Job> iterator = jobs.iterator();
        // while (iterator.hasNext()) {
        //     Job job = iterator.next();
        //     if(job.getId().equals(id)){
        //         iterator.remove();
        //         return true;
        //     }
        // }
        // return false;
        try{
            jobRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
    // for (Job job:jobs){
    //     if(job.getId().equals(id)){
    //         job.setTitle(updatedJob.getTitle());
    //         job.setDescription(updatedJob.getDescription());
    //         job.setMinSalary(updatedJob.getMinSalary());
    //         job.setMaxSalary(updatedJob.getMaxSalary());
    //         return true;
    //     }
    // }
    Optional<Job> jobOptional =jobRepository.findById(id);
    if(jobOptional.isPresent()){
        Job job = jobOptional.get();
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setMinSalary(updatedJob.getMinSalary());
        job.setMaxSalary(updatedJob.getMaxSalary());
        job.setLocation(updatedJob.getLocation());
        jobRepository.save(job);
        return true;
    }
    return false;
    }
    

     
}