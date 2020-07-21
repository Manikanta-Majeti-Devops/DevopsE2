package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorService {

        @GetMapping("/sum")
        public int sum() {
                return 20+10;
        }

	@GetMapping("/multiply")
	public int multiply() {
		return 20*10;
	}

	@GetMapping("/subtraction")
	public int subtraction() {
	       return 20-10;
	}
	
	@GetMapping("/division")
	public int division() {
		return 20/2;
	}
	
}
