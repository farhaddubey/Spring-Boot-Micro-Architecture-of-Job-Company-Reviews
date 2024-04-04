package com.knightscom.knightscom.company;

import java.util.List;

import com.knightscom.knightscom.company.dto.ReviewMessage;

public interface  CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
    public void updateCompanyRating(ReviewMessage reviewMessage);
    
}
