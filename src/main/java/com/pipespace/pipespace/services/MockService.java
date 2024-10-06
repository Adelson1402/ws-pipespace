package com.pipespace.pipespace.services;

import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pipespace.pipespace.repositories.MockRepository;
import com.pipespace.pipespace.requests.MockRequest;

import jakarta.persistence.EntityExistsException;

@Service
public class MockService {

	@Autowired
	private MockRepository mockRepo;
	
	public void createRule(MockRequest mockRequest) {
		
		MockRequest existsMockRequestBySection = mockRepo.findMockRequestBySection(mockRequest.getSection());
		
		if(existsMockRequestBySection != null) {
			throw new EntityExistsException("Mock ja cadastrado com esta section");
		}
		
		mockRepo.save(mockRequest);
	}
	
	public MockRequest findMockRequestBySection(String section) {
		MockRequest existsMockRequestBySection = mockRepo.findMockRequestBySection(section);
		
		return existsMockRequestBySection;
	}
	
	public MockRequest returnErrorByQueryParam(Map<String, String> queryParams) throws BadRequestException {
		
		MockRequest mock = new MockRequest();
		
		try {
			mock.setHttpResponse(Integer.parseInt(queryParams.get("httpResponse")));
		} catch (Exception e) {
			throw new BadRequestException("O query param httpResponse deve ser um numero ");
		}
		
		mock.setResponse(queryParams.get("responseError"));
		
		return mock;
	}
}
