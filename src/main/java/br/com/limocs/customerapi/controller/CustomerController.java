package br.com.limocs.customerapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.limocs.customerapi.model.Customer;
import br.com.limocs.customerapi.service.CustomerService;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<Customer>> findAll() {
		return ResponseEntity.ok().body(customerService.findAll());
	}

	@GetMapping("/{cpf}")
	public ResponseEntity<Customer> getById(@PathVariable String cpf) {
		return ResponseEntity.ok().body(customerService.getById(cpf));
	}

	@PostMapping
	public ResponseEntity<Object> save(@Valid @RequestBody Customer customer) {
		Customer savedCustomer = customerService.save(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCustomer.getCpf()).toUri();
		return ResponseEntity.created(location).body(savedCustomer);
	}

	@PutMapping("/{cpf}")
	public ResponseEntity<Object> update(@PathVariable String cpf, @Valid @RequestBody Customer customer) {
		customerService.update(cpf, customer);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<Void> deleteById(@PathVariable String cpf) {
		customerService.deleteById(cpf);
		return ResponseEntity.noContent().build();
	}
}
