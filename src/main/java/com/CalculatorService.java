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
	
	@GetMapping("/square")
	public int square() {
		return 2*2;
	}
	
	@GetMapping("/cube")
	public int cube() {
		return 2*2*2;
	}
	
	@GetMapping("/squareroot")
	public int squareroot() {
		return Math.sqrt(4);
	}
	
	
	@GetMapping("/cuberoot")
	public int cuberoot() {
		return Math.cbrt(64);
	}

	@GetMapping("/aplusbsquare")
	public int aplusbsquare() {
		return 2*2 + 3*3 + 2*2*3;
	}
}
