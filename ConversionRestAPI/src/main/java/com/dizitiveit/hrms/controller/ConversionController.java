package com.dizitiveit.hrms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dizitiveit.hrms.model.Conversion;
import com.dizitiveit.hrms.service.IConversion;
@RestController
@RequestMapping("/conversion")
public class ConversionController {

	@Autowired
	private IConversion conver;
	
	@PostMapping("/con")
	public String con(@RequestBody Conversion conversion) {// throws StreamWriteException, DatabindException, IOException {
	
		System.out.println(conversion);
//		ObjectMapper mapper=new ObjectMapper();
//		mapper.writeValue(new File("conversion.json"), conversion);
//		System.out.println(mapper);
//		ObjectMapper map=new ObjectMapper();
//		map.writeValue(new File("conversion.xml"), conversion);
		//System.out.println(map);
	//	RestTemplate rt=new RestTemplate();
	//	List<HttpMessageConverter<?>> list=rt.getMessageConverters();
		String con=conver.con();
		return "Successfull "+con;
	}//method
	
	
//	@GetMapping("/get")
//	public ResponseEntity<String> get()  {
//
//		Conversion conversion=new Conversion(12,"karan","kljd","sfsd");
//		
//		return new ResponseEntity<>("Successfull "+conversion,HttpStatus.CREATED);
//	}//method
	
	
}
