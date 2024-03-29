 package com.electricity.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.electricity.api.exception.BillNotFoundException;
import com.electricity.api.exception.CustomerNotFoundException;
import com.electricity.api.exception.MeterIdNotFoundException;
import com.electricity.api.model.Bill;

import com.electricity.api.model.Customer;
import com.electricity.api.model.Meter;
import com.electricity.api.service.BillService;

import com.electricity.api.service.CustomerService;
import com.electricity.api.service.MeterService;
import com.electricity.api.util.LoggerUtil;
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class BillController {

	@Autowired

	private BillService billService;

	@Autowired

	private CustomerService customerService;
	
	@Autowired
	private MeterService meterService;
	
	
	@PostMapping("/api/bill/add/{cid}/{mid}")

    public ResponseEntity<String> postBill(@PathVariable("cid") int cid,  

                                        @PathVariable("mid") int mid,

                                        @RequestBody Bill bill ) {

        //fetch the customer object based on cid

    Optional<Customer> optionalP =  customerService.getCustomerById(cid);
    if(!optionalP.isPresent())

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Customer Id");
 //fetch the meter object based on mid

   Optional<Meter> optionalD = meterService.getMeterById(mid);
   if(!optionalD.isPresent())

     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Meter Id");
   
     Customer customer = optionalP.get();

     Meter meter = optionalD.get();

    //Attach customer object to bill

        bill.setCustomer(customer);
        
   //Attach meter object to bill
        
        bill.setMeter(meter);

        //save the bill object

        billService.insertBill(bill);
        LoggerUtil.logInfo("Bill details are posted");
       return ResponseEntity.status(HttpStatus.OK).body("Bill added in DB");

    }

	
	// Get API
	@GetMapping("/api/bill/getall")

	public List<Bill> getAllBill() {

		List<Bill> list = billService.getAllBill();	  
		 LoggerUtil.logInfo(" All Bill Records");
		return list;
	}
	
	@GetMapping("/api/bill/{billId}")

	public ResponseEntity<Object> getBillById(@PathVariable("billId") int billId) throws BillNotFoundException {

		Optional<Bill> optional = billService.getBillById(billId);

		
		Bill bill = optional.get();
		
		 LoggerUtil.logInfo("Bill details are Obtained");
		return ResponseEntity.status(HttpStatus.OK).body(bill);

	}
	  @PutMapping("/api/bill/{billId}")

	    public ResponseEntity<String> updateBillById (@PathVariable("billId") int billId,@RequestBody Bill bill)throws BillNotFoundException {

	        billService.updateBillById(bill);
	        LoggerUtil.logInfo("Bill details are Updated");
	        
	        return ResponseEntity.status(HttpStatus.OK).body("Bill is updated");

	        }

    

	    @DeleteMapping("/api/bill/delete/{billId}")

	    public ResponseEntity<String> deleteBillById(@PathVariable("billId") int billId,@RequestBody Bill bill)throws BillNotFoundException {

	 

	        billService.deleteBillById(bill);
	        LoggerUtil.logInfo("Bill details by Id are Deleted");

	        return ResponseEntity.status(HttpStatus.OK).body("Bill is Deleted");

	    }

	    

	    //1.Get Bill by customer id

	       

	        @GetMapping("/api/bill/customer/{cid}")

	        public List<Bill> getBillByCustomerId(@PathVariable("cid") int cid) {
	                List<Bill> list = billService.getBillByCustomerId(cid);
	                LoggerUtil.logInfo("Bill details are Obtained by Customer Id");

	                return list;

	    }	        

	        //2.Get Bill by METER id

	        @GetMapping("/api/bill/meter/{mid}")

	        public List<Bill> getBillByMeterId(@PathVariable("mid") int mid) {	      
	                List<Bill> list = billService.getBillByMeterId(mid);
	                LoggerUtil.logInfo("Bill details are Obtained by Meter Id");
	                return list;

	    }

	    
}
    

 

