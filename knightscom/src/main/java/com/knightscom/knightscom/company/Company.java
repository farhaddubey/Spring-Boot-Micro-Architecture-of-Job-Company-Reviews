package com.knightscom.knightscom.company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Company(){

    }
    public Company(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    
    //This means we're haivng a field "company" in the jobs table which is mapping to company 
    // That prevents from making extra table just for maintining the relationship
    // OneToMany that means 1 company can have list of jobs... 

    // Now another table is CREATED to maintain the relation ship between compnay and the job entities 
    // Now mappedBy = "company" indicates, that there's separate column in company table for mainting the relationship
    // Every company have list of jobs and every company have list of reviews 
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
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        id = this.id;
    }

}
