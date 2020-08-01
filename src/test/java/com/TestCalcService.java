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

	      @Test
	      public void testSquare() {
	              Assertions.assertEquals(4,calcService.square());
        }

         @Test
         public void testCube() {
                Assertions.assertEquals(8,calcService.cube());
          }
	
	 @Test
         public void testSquareroot() {
                Assertions.assertEquals(2,calcService.squareroot());
          }
	
	 @Test
         public void testCuberoot() {
                Assertions.assertEquals(4,calcService.cuberoot());
          }
	
	@Test
         public void testAplusBSquare() {
                Assertions.assertEquals(25,calcService.aplusbsquare());
          }
	
	
	
}
