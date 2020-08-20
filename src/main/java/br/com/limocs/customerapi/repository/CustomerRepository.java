package br.com.limocs.customerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.limocs.customerapi.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

}
