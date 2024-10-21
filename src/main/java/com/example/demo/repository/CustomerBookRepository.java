package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Customer;

@Repository
public interface CustomerBookRepository extends JpaRepository<Customer, Integer> {

	//	@Query(value = "SELECT " +
	//            "C.id, " +
	//            "C.first_name, " +
	//            "C.last_name, " +
	//            "C.email, " +
	//            "COUNT(CB.book_id) AS BooksBought " +
	//            "FROM Customer_Book CB " +
	//            "FULL OUTER JOIN Customer C ON CB.customer_id = C.id " +
	//            "FULL OUTER JOIN Book B ON CB.book_id = B.id " +
	//            "GROUP BY C.id, C.first_name, C.last_name, C.email " +
	//            "ORDER BY C.id", 
	//            nativeQuery = true)
	//	List<CustomerBookDTO> findCustomerBookInfo();

	   @Query("SELECT " +
	           "C.id, " +
	           "C.firstName, " + // Assuming the attribute is named firstName in the Customer entity
	           "C.lastName, " + // Assuming the attribute is named lastName in the Customer entity
	           "C.email, " +
	           "COUNT(CB.id) AS BooksBought " + // Assuming the relationship is correctly set up in the entities
	           "FROM Customer C " +
	           "LEFT JOIN C.books CB " + // Using left join to get all customers and their book counts
	           "GROUP BY C.id, C.firstName, C.lastName, C.email " +
	           "ORDER BY C.id")
	    List<Object[]> findCustomerBookInfo();

}



