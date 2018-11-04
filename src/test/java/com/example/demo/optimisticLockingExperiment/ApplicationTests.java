package com.example.demo.optimisticLockingExperiment;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Mock
        CustomerRepository mockRepository;
        
        @Autowired
        ExperimentController ec;
        
        @Test
	public void contextLoads() {
	}
        
        @Test
        public void testSaveCustomerWithoutLockingException(){
            
           Customer c = new Customer();
           c.setFirstName("myFristName");
           c.setLastName("myLastName");
           c.setId(new Long("999"));
           c.setVersion(0);
           ArrayList custList = new ArrayList();
           custList.add(c);
           when(this.mockRepository.findAll()).thenReturn(custList);
           
           this.ec.saveCustomerWithoutLockingException();
           
        }

}
