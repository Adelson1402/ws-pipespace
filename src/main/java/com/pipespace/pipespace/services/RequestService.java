package com.pipespace.pipespace.services;

import java.util.List;

import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pipespace.pipespace.repositories.RequestRepository;
import com.pipespace.pipespace.requests.Request;

@Service
public class RequestService {

	@Autowired
	private RequestRepository requestRepository;
	
	public void save(String request) throws InvalidContentTypeException {
		
		Long requestCount = requestRepository.count();
		
		if(requestCount < 100) {
			Request rq = new Request(request);
			requestRepository.save(rq);
		
		} else {
			throw new InvalidContentTypeException("Numero de requests excedidos, contacte o administrador");
		}
	}
}
