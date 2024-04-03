package com.knightslife.knights.Job.external;

// this is just used to track the progress of company 
public class Company {
    private Long id;
    private String name;
    private String description;
    
    //This means we're haivng a field "company" in the jobs table which is mapping to company 
    // That prevents from making extra table just for maintining the relationship
    // OneToMany that means 1 company can have list of jobs... 

    // Now another table is CREATED to maintain the relation ship between compnay and the job entities 
    // Now mappedBy = "company" indicates, that there's separate column in company table for mainting the relationship
    // Every company have list of jobs and every company have list of reviews 
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        id = this.id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


}
