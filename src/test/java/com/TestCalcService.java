package com;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.CalculatorService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestCalcService {

	CalculatorService calcService = new CalculatorService();

        @Test
        public void testSum() {
               
                Assertions.assertEquals(30,calcService.sum());
        }

        @Test
        public void testMultiply() {
                
                Assertions.assertEquals(200,calcService.multiply());
        }

	@Test
	public void testSubtraction() {
		Assertions.assertEquals(10,calcService.subtraction());
	}

	@Test
	public void testDivision() {
		Assertions.assertEquals(10,calcService.division());
	}

//	@Test
//	public void testSumneg() {
//		Assertions.assertEquals(40,calcService.sum());
//	}
//
//	@Test
//	public void testMulneg() {
//	        Assertions.assertEquals(210,calcService.multiply());
//      }
//
//        @Test
//        public void testSubneg() {
//                Assertions.assertEquals(1,calcService.subtraction());
//        }
//
//        @Test
//        public void testDivneg() {
//                Assertions.assertEquals(0,calcService.division());
//        }
//
}
