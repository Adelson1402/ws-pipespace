package com.pipespace.pipespace.controllers;

import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pipespace.pipespace.requests.MockRequest;
import com.pipespace.pipespace.services.MockService;

import jakarta.ws.rs.core.NoContentException;

@RestController
@RequestMapping(value="/api")
public class MockController {
	
	@Autowired
	private MockService mockService;
	
		@PostMapping("create-mock")
		public ResponseEntity<String> mockRequest(@RequestBody MockRequest mockRequest ) {
			
			
			try {
				mockService.createRule(mockRequest);
				return new ResponseEntity<String>("MOCK cadastrado com SUCESSO" ,HttpStatus.CREATED);	
			} catch (Exception e) {
				return new ResponseEntity<String>("Erro ao cadastrar MOCK" ,HttpStatus.BAD_REQUEST);
			}
			
		}
		
		@PostMapping("consulta-panasonic-token")
		public ResponseEntity<String> mockRequiestToken() {
			
			String response = "{\"access_token\": \"tokenPanasonic\"}";
			
			return new ResponseEntity<String>(response, HttpStatus.OK);
		}
		
		@RequestMapping(value = "mock-request/{section}", method = {RequestMethod.GET, RequestMethod.POST})
		public ResponseEntity<?> executeMock(@PathVariable("section") String section, @RequestParam Map<String, String> queryParams ) throws NoContentException, BadRequestException{
			
			MockRequest mock = mockService.findMockRequestBySection(section);
			
			if(queryParams != null && queryParams.get("returnError") != null) {
				
				mock = mockService.returnErrorByQueryParam(queryParams);
			}
			
			
			if(mock == null) {
				return new ResponseEntity<String>("Não foi encontrada nenhuma section criada para: " + section , HttpStatus.BAD_REQUEST);
				
				
			}
			
			return new ResponseEntity<String>(mock.getResponse(), HttpStatus.valueOf(mock.getHttpResponse())); 				
		}

}
