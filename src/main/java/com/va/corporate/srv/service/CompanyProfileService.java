package com.va.corporate.srv.service;


import com.va.corporate.srv.dto.PaginatedResponseDto;
import com.va.corporate.srv.models.vacms.CompanyProfileModel;
import com.va.corporate.srv.repository.vacms.CompanyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Validated
public class CompanyProfileService {


    @Autowired
    private final CompanyProfileRepository repository;

    public CompanyProfileService(CompanyProfileRepository repository) {
        this.repository = repository;
    }

    public PaginatedResponseDto<CompanyProfileModel> getPaginatedCompanyProfile(int page, int size, Map<String, String> searching) {
        List<CompanyProfileModel> companyProfiles = null;
        System.out.println("Searching value : "+searching);

        int totalItems = 0;
        int totalPages = 0;
        try {

            totalItems = repository.countAll(searching);
            totalPages = (int) Math.ceil((double) totalItems / size);

            companyProfiles = repository.findAll(page, size, searching);
        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
        }

        return new PaginatedResponseDto<>(companyProfiles, page, totalPages, totalItems);

    }

    public void addCompanyProfile(@Valid CompanyProfileModel companyProfile) throws Exception {
        try {
            //validate if no active profile exist
            List<CompanyProfileModel> companyProfileModels = this.repository.findByCompanyId(companyProfile.getCompanyId());
            if(companyProfileModels != null && !companyProfileModels.isEmpty()) {
                throw new Exception(String.format("row id %s masih aktif, data tidak dapat ditambah, silahkan check kembali", companyProfileModels.get(0).getId()));
            }

            companyProfile.setCreatedAt(LocalDateTime.now());
            companyProfile.setCreatedBy("system");
            this.repository.addCompanyProfile(companyProfile);
        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
            throw e;
        }
    }

    public void updateCompanyProfile(@Valid CompanyProfileModel companyProfile) throws Exception {
        try {
            companyProfile.setUpdatedAt(LocalDateTime.now());
            companyProfile.setUpdatedBy("system");
            this.repository.updateCompanyProfile(companyProfile);
            if(companyProfile.getIsActive()) {
                this.repository.setInactive(companyProfile.getId().intValue(), companyProfile.getCompanyId());
            }
        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
            throw e;
        }
    }

    public void deleteCompanyProfile(@Valid CompanyProfileModel companyProfile) throws Exception {
        try {
            companyProfile.setUpdatedAt(LocalDateTime.now());
            companyProfile.setUpdatedBy("system");
            this.repository.updateCompanyProfile(companyProfile);
        } catch (Exception e) {
            System.out.println("Error : "+e.getLocalizedMessage());
            throw e;
        }
    }

    public void uploadLogo(MultipartFile file, String id) throws IOException {
        try {
            CompanyProfileModel companyProfileModel = this.repository.findById(Integer.valueOf(id));
            companyProfileModel.setCompanyLogo(file.getBytes());
            this.updateCompanyProfile(companyProfileModel);
        } catch (IOException e) {
            System.out.println("Error : "+e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void uploadFavIcon(MultipartFile file, String id) throws IOException {
        try {
            CompanyProfileModel companyProfileModel = this.repository.findById(Integer.valueOf(id));
            companyProfileModel.setCompanyFavIcon(file.getBytes());
            this.updateCompanyProfile(companyProfileModel);
        } catch (IOException e) {
            System.out.println("Error : "+e.getLocalizedMessage());
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
