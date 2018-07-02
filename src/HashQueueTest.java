import static org.junit.Assert.*;

import org.junit.Test;

public class HashQueueTest {

	
	@Test
	public void testQueue() {
		Queue<Customer> cust = new Queue<Customer>();
		cust.add(new Customer("1111", "OneFirst", "OneLast", "oneEmail@email.com", "111-111-1111"));
		cust.add(new Customer("2222", "TwoFirst", "TwoLast", "TwoEmail@email.com", "222-222-2222"));
		cust.add(new Customer("2222", "TwoFirst", "TwoLast", "TwoEmail@email.com", "222-222-2222"));
		assertEquals(cust.size(), 3);
		
	}

	
	@Test 
	public void testRemove() {
		Queue<Customer> cust = new Queue<Customer>();
		Customer three = new Customer("3333", "ThreeFirst", "ThreeLast", "ThreeEmail@email.com", "333-333-3333");
		Customer one = new Customer("1111", "OneFirst", "OneLast", "oneEmail@email.com", "111-111-1111");
		cust.add(one);
		cust.add(new Customer("2222", "TwoFirst", "TwoLast", "TwoEmail@email.com", "222-222-2222"));
		cust.add(three);
		
		
		assertEquals(cust.contains(three), true);
		
		cust.remove();
		assertEquals(cust.size(), 2);
		assertEquals(cust.contains(one), true);
	}
	
	@Test
	public void testClear() {
		Queue<Customer> cust = new Queue<Customer>();
		Customer three = new Customer("3333", "ThreeFirst", "ThreeLast", "ThreeEmail@email.com", "333-333-3333");
		cust.add(new Customer("1111", "OneFirst", "OneLast", "oneEmail@email.com", "111-111-1111"));
		cust.add(new Customer("2222", "TwoFirst", "TwoLast", "TwoEmail@email.com", "222-222-2222"));
		cust.add(three);
		cust.clear();
		assertEquals(cust.size(), 0);
	}
	
	@Test
	public void testPeek() {
		Queue<Customer> cust = new Queue<Customer>();
		Customer three = new Customer("3333", "ThreeFirst", "ThreeLast", "ThreeEmail@email.com", "333-333-3333");
		Customer head = new Customer("1111", "OneFirst", "OneLast", "oneEmail@email.com", "111-111-1111");
		Customer two = new Customer("2222", "TwoFirst", "TwoLast", "TwoEmail@email.com", "222-222-2222");
		cust.add(head);
		cust.add(two);
		cust.add(three);
		
		assertEquals(cust.peek(), head);
		cust.remove();
		
		assertEquals(cust.peek(), two);
	}
}

