package br.com.limocs.customerapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.limocs.customerapi.exception.CustomerNotFoundException;
import br.com.limocs.customerapi.model.Customer;
import br.com.limocs.customerapi.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repository;

	public List<Customer> findAll() {
		return repository.findAll();
	}

	public Customer getById(String cpf) {
		return repository.findById(cpf).orElseThrow(() -> new CustomerNotFoundException(cpf));
	}

	public Customer save(Customer newCustomer) {
		return repository.save(newCustomer);
	}

	public Customer update(String id, Customer customer) {
		if (!isPresent(id)) {
			new CustomerNotFoundException(id);
		}
		customer.setCpf(id);
		return repository.save(customer);
	}

	public void deleteById(String id) {
		if (!isPresent(id)) {
			new CustomerNotFoundException(id);
		}
		repository.deleteById(id);
	}

	private Boolean isPresent(String id) {
		return repository.findById(id).isPresent();
	}

}
