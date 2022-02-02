This folder will contain rest api

package com.work.champ.rest.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.work.champ.conf.EmailService;
import com.work.champ.controller.ApplicantController;
import com.work.champ.model.dto.ApplicantDto;
import com.work.champ.model.helping.RestResponse;
import com.work.champ.repo.main.ApplicantRepo;
import com.work.champ.utils.AppConstants;

@RestController
@RequestMapping(AppConstants.REST_API_PREFIX+"applicants")
public class ApplicantRest {

	@Autowired
	ApplicantRepo applicantRepo;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/getAllApplicants")
	public RestResponse getAllApplicants(HttpServletRequest request) {

		return ApplicantController.getAllApplicants(request, applicantRepo);
	}

	@PostMapping("/saveApplicant")
	public RestResponse saveApplicant(@RequestBody ApplicantDto applicantDto, HttpServletRequest request) {
		return ApplicantController.saveApplicant(applicantDto, applicantRepo, request);
	}
	
}