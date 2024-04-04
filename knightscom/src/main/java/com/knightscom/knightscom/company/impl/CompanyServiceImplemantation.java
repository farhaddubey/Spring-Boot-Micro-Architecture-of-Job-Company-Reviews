package com.knightscom.knightscom.company.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.knightscom.knightscom.company.Company;
import com.knightscom.knightscom.company.CompanyRepository;
import com.knightscom.knightscom.company.CompanyService;
import com.knightscom.knightscom.company.clients.ReviewClient;
import com.knightscom.knightscom.company.dto.ReviewMessage;

import jakarta.ws.rs.NotFoundException;

@Service
public class CompanyServiceImplemantation implements CompanyService {
    private CompanyRepository companyRepository;
    ReviewClient reviewClient;
    public CompanyServiceImplemantation(CompanyRepository companyRepository, ReviewClient reviewClient) {
        this.reviewClient = reviewClient;
        this.companyRepository = companyRepository;
    }
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
        //returns all the instances of the company
    }
    @Override
    public boolean updateCompany(Company company, Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyRepository.save(companyToUpdate);
            return true;

        } else{
            return false;
        }
    }
    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }
    @Override
    public boolean deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());
        Company company = companyRepository.findById(reviewMessage.getCompanyId())
        .orElseThrow(()->new NotFoundException("Company Not Found"+ reviewMessage));
        double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getRating());
        company.setRating(averageRating);
        companyRepository.save(company);
    }

   
}
