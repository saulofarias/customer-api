package br.com.limocs.customerapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.limocs.customerapi.model.Customer;
import br.com.limocs.customerapi.model.CustomerAddress;
import br.com.limocs.customerapi.repository.CustomerRepository;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository repository;

	@InjectMocks
	private CustomerService customerService;

	@Test
	public void shouldReturnsAllCustomers() throws Exception {
		CustomerAddress address = new CustomerAddress(null, "Av. Sao Sebastiao", "100", "Limoeiro", "PE",
				"55700000", "Brasil");
		Customer customer = new Customer("11111111111", "John", address);

		List<Customer> expectedCustomers = Arrays.asList(customer);
		doReturn(expectedCustomers).when(repository).findAll();

		List<Customer> actualCustomers = customerService.findAll();

		assertThat(actualCustomers).isEqualTo(expectedCustomers);
	}

	@Test
	public void shouldReturnCustomerById() throws Exception {
		CustomerAddress address = new CustomerAddress(null, "Av. Sao Sebastiao", "100", "Limoeiro", "PE",
				"55700000", "Brasil");
		Customer customer = new Customer("11111111111", "John", address);

		when(repository.findById(customer.getCpf())).thenReturn(Optional.of(customer));

		Customer actualCustomer = customerService.getById(customer.getCpf());

		assertEquals(customer, actualCustomer);
	}

	@Test
	public void shouldReturnCustomerCreatedWithSucess() throws Exception {
		CustomerAddress address = new CustomerAddress(null, "Av. Sao Sebastiao", "100", "Limoeiro", "PE",
				"55700000", "Brasil");
		Customer customer = new Customer("11111111111", "John", address);

		doReturn(customer).when(repository).save(customer);

		Customer actualCustomer = customerService.save(customer);

		assertThat(actualCustomer).isNotNull();

	}

	@Test
	public void shouldPersistAndChangeDataWithSucess() throws Exception {
		CustomerAddress address = new CustomerAddress(null, "Av. Sao Sebastiao", "100", "Limoeiro", "PE",
				"55700000", "Brasil");
		Customer customer = new Customer("11111111111", "John", address);

		when(repository.save(customer)).thenReturn(customer);
		when(repository.findById(customer.getCpf())).thenReturn(Optional.of(customer));

		Customer newCustomer = customerService.getById(customer.getCpf());
		newCustomer.setFullName("Mary");

		Customer foundCustomer = customerService.update(newCustomer.getCpf(), newCustomer);

		assertEquals(foundCustomer.getFullName(), newCustomer.getFullName());

	}
}
