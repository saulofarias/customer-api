package br.com.limocs.customerapi.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Lazy;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.limocs.customerapi.constants.MessagesConst;
import br.com.limocs.customerapi.model.Customer;
import br.com.limocs.customerapi.model.CustomerAddress;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

	@Autowired
	public TestEntityManager entityManager;

	@Autowired
	@Lazy
	public CustomerRepository repository;

	@Test
	public void shouldReturnCustomers() {
		CustomerAddress address = new CustomerAddress(null, "Av. Sao Sebastiao", "100", "Limoeiro", "PE",
				"55700000", "Brasil");
		Customer customer1 = new Customer("11111111111", "John", address);
		Customer customer2 = new Customer("22222222222", "Mary", address);
		Customer customer3 = new Customer("33333333333", "Peter", address);

		List<Customer> customers = Arrays.asList(customer1, customer2, customer3);

		for (Customer customer : customers) {
			entityManager.persist(customer);
			entityManager.flush();
		}

		assertEquals(3, repository.findAll().size());
	}

	@Test
	public void shouldReturnCustomer() {
		CustomerAddress address = new CustomerAddress(null, "Av. Sao Sebastiao", "100", "Limoeiro", "PE",
				"55700000", "Brasil");
		Customer customer = new Customer("11111111111", "John", address);

		entityManager.persist(customer);
		entityManager.flush();

		Customer customerFound = repository.findById(customer.getCpf()).get();

		assertEquals(customerFound.getCpf(), customer.getCpf());
	}

	@Test
	public void shouldReturnCustomerCreatedWithSucess() {
		CustomerAddress address = new CustomerAddress(null, "Av. Sao Sebastiao", "100", "Limoeiro", "PE",
				"55700000", "Brasil");
		Customer customer = new Customer("44444444444", "Jacob", address);

		Customer newCustomer = this.repository.save(customer);
		assertNotNull(newCustomer);
		assertNotNull(newCustomer.getCustomerAddress());
		assertEquals(newCustomer.getCpf(), customer.getCpf());
		assertEquals(newCustomer.getFullName(), customer.getFullName());

	}

	@Test
	public void shouldPersistAndChangeDataWithSucess() {
		CustomerAddress address = new CustomerAddress(null, "Av. Sao Sebastiao", "100", "Limoeiro", "PE",
				"55700000", "Brasil");
		Customer customer = new Customer("11111111111", "John", address);

		entityManager.persist(customer);
		entityManager.flush();

		String name = "Mary";

		Customer customerFound = repository.findById(customer.getCpf()).get();
		customerFound.setFullName(name);

		repository.save(customerFound);
		customerFound = repository.getOne(customer.getCpf());

		assertNotNull(customerFound);
		assertNotNull(customerFound.getCustomerAddress());
		assertEquals(customerFound.getFullName(), name);
	}

	@Test
	public void deleteShouldRemoveData() {
		CustomerAddress address = new CustomerAddress(null, "Av. Sao Sebastiao", "100", "Limoeiro", "PE",
				"55700000", "Brasil");
		Customer customer = new Customer("11111111111", "John", address);

		entityManager.persist(customer);
		entityManager.flush();

		repository.deleteById(customer.getCpf());

		assertFalse(repository.findById(customer.getCpf()).isPresent());
	}

	@Test
	public void saveWhenCPFIsNullShouldThrowJpaSystemException() {
		Exception exception = assertThrows(JpaSystemException.class, () -> {
			repository.save(new Customer());
		});

		String expectedMessage = MessagesConst.CPF_NOT_EMPTY;
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	// @Test
	// public void saveWhenNameIsNullShouldThrow() {
	// Exception exception = assertThrows(ConstraintViolationException.class, () ->
	// {
	// CustomerAddress address = new CustomerAddress(null, "Av. Sao Sebastiao",
	// "100", "Limoeiro",
	// "PE", "55700000", "Brasil");
	// Customer customer = new Customer();
	// customer.setCpf("62794485091");
	// customer.setCustomerAddress(address);
	// repository.save(customer);
	// });

	// String expectedMessage = MessagesConst.NAME_NOT_EMPTY;
	// String actualMessage = exception.getMessage();
	// assertTrue(actualMessage.contains(expectedMessage));
	// }

	@Test
	public void saveWhenAddresIsNullShouldThrow() throws Exception {
		Exception exception = assertThrows(ConstraintViolationException.class, () -> {
			Customer customer = new Customer();
			customer.setCpf("62794485091");
			customer.setFullName("Test");
			customer.setCustomerAddress(new CustomerAddress());
			repository.save(customer);
		});

		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(MessagesConst.STREET_NOT_EMPTY));
		assertTrue(actualMessage.contains(MessagesConst.CITY_NOT_EMPTY));
		assertTrue(actualMessage.contains(MessagesConst.STATE_NOT_EMPTY));
		assertTrue(actualMessage.contains(MessagesConst.NUMBER_NOT_EMPTY));
		assertTrue(actualMessage.contains(MessagesConst.COUNTRY_NOT_EMPTY));
		assertTrue(actualMessage.contains(MessagesConst.ZIP_CODE_NOT_EMPTY));
	}
}
