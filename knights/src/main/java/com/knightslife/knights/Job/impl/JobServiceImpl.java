package com.knightslife.knights.Job.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.knightslife.knights.Job.Job;
import com.knightslife.knights.Job.JobRepository;
import com.knightslife.knights.Job.JobService;
import com.knightslife.knights.Job.clients.CompanyClient;
import com.knightslife.knights.Job.clients.ReviewClient;
import com.knightslife.knights.Job.dto.JobWithCompanyDTO;
import com.knightslife.knights.Job.external.Company;
import com.knightslife.knights.Job.external.Review;
import com.knightslife.knights.Job.mapper.JobMapper;


@Service
public class JobServiceImpl implements JobService {
    // private List<Job> jobs = new ArrayList<>();

    JobRepository jobRepository;
    
    @Autowired
    RestTemplate restTemplate;
    // Autowired means rest gonna take care to provide obj of that class at runtime as that annotated as Bean 

    private CompanyClient companyClient;
    private ReviewClient reviewClient;
    public JobServiceImpl(JobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    private Long nextId = 1L;

    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs=jobRepository.findAll();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private JobWithCompanyDTO convertToDto(Job job){

        // JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        // jobWithCompanyDTO.setJob(job); //We'll be deleting this line & use the mapper recontly made
        // Now the setter of job is not avail on JobWithCompanyDTO class as we modified 
        // Hence we can use mapper insted 
        // & for that purpose we'll be creating another mapper class insidde the mapper package 
        // Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/companies/" + job.getCompanyId(), Company.class);
        // Now we'll be modifieing using the Feign after defining the compnayClient.java classs 
        // We'll be modifing according as Feign 




        // Now we need to add the review also 
        // From Every company Id we can fetch the review 
        // Hence We need to add the review api too 
        // restTemplate.exchange("http://localhost:8083/review?companyId=1", Review.class);
        // Now we need to replae the localhost with SERVICE Name 
        // ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?companyId=1"+ job.getCompanyId(), HttpMethod.GET,
        // null, new ParameterizedTypeReference<List<Review>>() {
        // // 

        // });
        // Now we need to get the review respnse 
        // List<Review> reviews = reviewResponse.getBody();

        // Now we need to add the review also  


        // Now our Feign Interfacees will do the calling job 
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = (List<Review>) reviewClient.getReviews(job.getCompanyId());
        JobWithCompanyDTO jobWithCompanyDTO = JobMapper.mapToJobWithCompanyDTO(job, company, reviews);


        // RestTemplate restTemplate = new RestTemplate();
        // As we are using now the most intelligent version of rest template 
        // Company company =restTemplate.getForObject(
        //     "http://localhost:8081/companies/"+job.getCompanyId(),
        //     Company.class
        // );
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;
    }
    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        // jobs.add(job);
        jobRepository.save(job);
    }

    @Override
    public JobWithCompanyDTO getJobById(Long id) {
        // for(Job job: jobs){
        //     if(job.getId().equals(id)){
        //         return job;
        //     }
        // }
        // return null;

        // return jobRepository.findById(id).orElse(null);
        Job job = jobRepository.findById(id).orElse(null);
        return convertToDto(job);
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