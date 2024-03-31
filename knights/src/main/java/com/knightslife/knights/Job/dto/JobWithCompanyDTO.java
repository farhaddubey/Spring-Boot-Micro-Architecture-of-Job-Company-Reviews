package com.knightslife.knights.Job.dto;

import com.knightslife.knights.Job.Job;
import com.knightslife.knights.Job.external.Company;

public class JobWithCompanyDTO {
    private Job job;
    private Company company;
    

    /**
     * @return Job return the job
     */
    public Job getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     * @return Company return the company
     */
    public Company getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }

}
