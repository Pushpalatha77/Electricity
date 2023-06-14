package com.electricity.api.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.electricity.api.data.BillRepository;
//import com.electricity.api.data.MeterRepository;
//import com.electricity.api.exception.BillNotFoundException;
//import com.electricity.api.model.Bill;
//import com.electricity.api.model.Customer;
//import com.electricity.api.model.Meter;
//import com.electricity.api.model.User;
//
//@ExtendWith(MockitoExtension.class)
//class BillServiceTest {
//
//	@Mock
//	private BillRepository billRepository;
//
//	@Mock
//	private MeterRepository meterRepository;
//
//	private List<Bill> bills = new ArrayList();
//
//	@InjectMocks
//	private BillService service;
//
//	private Bill bill;
//	private Meter meter;
//	private Customer customer;
//	private User user;
//
//	@BeforeEach
//	void setup() {
//		user = new User();
//		user.setId(1);
//		user.setUsername("nagulu");
//		user.setRole("CUSTOMER");
//		user.setPassword("qwertykey");
//		bill = new Bill();
//		meter = new Meter();
//		customer = new Customer();
//		customer.setName("nagulu");
//		customer.setId(1010);
//		customer.setAddress("nandyala");
//		customer.setContactNo("9090809090");
//		bill.setId(101);
//		bill.setConsumerNumber(123456);
//		bill.setAmount(1980);
//		bill.setBillDate(LocalDate.of(2000, 10, 20));
//		bill.setDueDate(LocalDate.of(2000, 11, 05));
//		bill.setBillMonth("OCT");
//		bill.setConsumedUnits(120);
//		meter.setId(1011);
//		meter.setMeterNo(1333);
//		bill.setMeter(meter);
//		customer.setMeter(meter);
//		customer.setUser(user);
//		bill.setCustomer(customer);
//		bills.add(bill);
//
//	}
//
//	@Test
//	void testGetAllBill() {
//		when(billRepository.findAll()).thenReturn(bills);
//		assertEquals(bills, service.getAllBill());
//		assertEquals(1980,bills.get(0).getAmount());
//	}
//	
//	@Test
//	void testGetBillById() throws BillNotFoundException {
//		
//		when(billRepository.findById(101)).thenReturn(Optional.of(bill));
//		assertEquals(Optional.of(bill),service.getBillById(101));
//		assertEquals(120,Optional.of(bill).get().getConsumedUnits());
//	}
//	
//
//    @Test
//    void testUpdateBillById() throws BillNotFoundException {
//        // Mocking the billRepository method
//        when(billRepository.save(bill)).thenReturn(bill);
//
//        // Call the method to be tested
//        service.updateBillById(bill);
//
//        // Verify that the billRepository method was called
//        verify(billRepository).save(bill);
//    }
//
//    @Test
//    void testDeleteBillById() throws BillNotFoundException{
//        // Call the method to be tested
//        service.deleteBillById(bill);
//
//        // Verify that the billRepository method was called
//        verify(billRepository).delete(bill);
//    }
//	/*
//	@Test
//	void testUpdateBillById() {
//	when(billRepository.save(bill)).thenReturn(bill);
//	
//		assertEquals(1980,bills.get(0).getAmount());
//	}
//		*/
//	//	assertEquals(120,bill.get);
//		
//		
//	//}
//	@Test
//	void testGetBillByCustomerId() {
//		when(billRepository.findAll()).thenReturn(bills);
//		assertEquals(bills,service.getBillByCustomerId(1010));
//		assertEquals(1980,bills.get(0).getAmount());
//	}
//	
//	@Test
//	void testGetBillByMeterId() {
//		when(billRepository.findAll()).thenReturn(bills);
//		assertEquals(bills,service.getBillByMeterId(1011));
//		assertEquals(1980,bills.get(0).getAmount());
//	}
//
//}








import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.electricity.api.data.BillRepository;
import com.electricity.api.data.MeterRepository;
import com.electricity.api.exception.BillNotFoundException;
import com.electricity.api.model.Bill;
import com.electricity.api.model.Customer;
import com.electricity.api.model.Meter;
import com.electricity.api.model.User;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {

	@Mock
	private BillRepository billRepository;

	@Mock
	private MeterRepository meterRepository;

	@InjectMocks
	private BillService service;

	private List<Bill> bills = new ArrayList<>();

	private Bill bill;
	private Meter meter;
	private Customer customer;
	private User user;

	@BeforeEach
	void setup() {
		user = new User();
		user.setId(1);
		user.setUsername("nagulu");
		user.setRole("CUSTOMER");
		user.setPassword("qwertykey");
		bill = new Bill();
		meter = new Meter();
		customer = new Customer();
		customer.setName("nagulu");
		customer.setId(1010);
		customer.setAddress("nandyala");
		customer.setContactNo("9090809090");
		bill.setId(101);
		bill.setConsumerNumber(123456);
		bill.setBillDate(LocalDate.of(2000, 10, 20));
		bill.setDueDate(LocalDate.of(2000, 11, 05));
		bill.setBillMonth("OCT");
		bill.setConsumedUnits(120);
		meter.setId(1011);
		meter.setMeterNo(1333);
		bill.setMeter(meter);
		customer.setMeter(meter);
		customer.setUser(user);
		bill.setCustomer(customer);
		bills.add(bill);
	}

	@Test
	void testGetAllBill() {
		when(billRepository.findAll()).thenReturn(bills);
		List<Bill> result = service.getAllBill();
		assertEquals(bills.size(), result.size());
		assertEquals(bills.get(0).getAmount(), result.get(0).getAmount());
	}

	@Test
	void testGetBillById() throws BillNotFoundException {
		when(billRepository.findById(101)).thenReturn(Optional.of(bill));
		Optional<Bill> result = service.getBillById(101);
		assertEquals(Optional.of(bill), result);
		assertEquals(120, result.get().getConsumedUnits());
	}

	@Test
	void testUpdateBillById_existingBill() throws BillNotFoundException {
		when(billRepository.findById(bill.getId())).thenReturn(Optional.of(bill));
		when(billRepository.save(bill)).thenReturn(bill);

		service.updateBillById(bill);

		verify(billRepository).findById(bill.getId());
		verify(billRepository).save(bill);
	}

	@Test
	void testUpdateBillById_billNotFound() {
		when(billRepository.findById(bill.getId())).thenReturn(Optional.empty());

		assertThrows(BillNotFoundException.class, () -> service.updateBillById(bill));
		verify(billRepository).findById(bill.getId());
		verify(billRepository).save(bill);
	}

	@Test
	void testDeleteBillById_existingBill() throws BillNotFoundException {
		when(billRepository.findById(bill.getId())).thenReturn(Optional.of(bill));

		service.deleteBillById(bill);

		verify(billRepository).findById(bill.getId());
		verify(billRepository).delete(bill);
	}

	@Test
	void testDeleteBillById_billNotFound() {
		when(billRepository.findById(bill.getId())).thenReturn(Optional.empty());

		assertThrows(BillNotFoundException.class, () -> service.deleteBillById(bill));
		verify(billRepository).findById(bill.getId());
		verify(billRepository).delete(bill);
	}

	@Test
	void testGetBillByCustomerId() {
		when(billRepository.findAll()).thenReturn(bills);
		List<Bill> result = service.getBillByCustomerId(1010);
		assertEquals(bills.size(), result.size());
		assertEquals(bills.get(0).getAmount(), result.get(0).getAmount());
	}

	@Test
	void testGetBillByMeterId() {
		when(billRepository.findAll()).thenReturn(bills);
		List<Bill> result = service.getBillByMeterId(1011);
		assertEquals(bills.size(), result.size());
		assertEquals(bills.get(0).getAmount(), result.get(0).getAmount());
	}

}

