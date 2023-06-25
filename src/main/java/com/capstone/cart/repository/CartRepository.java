package com.capstone.cart.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.cart.model.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	public List<Cart> findByUserid(Long id);
	
	public void deleteByCartidAndUserid(Long cId, Long uId);

	List<Cart> findAllByRestName(String restName);
//	@Query("SELECT c FROM cart c WHERE c.status = ")
//	@Query("SELECT c FROM cart c WHERE c.quantity = 1")
//	Collection<Cart> findByActiveUsers();

//	@Query(value = "SELECT * FROM cart WHERE status=ready", nativeQuery = true)
//	List<Cart> findAll();
	@Query(value = "SELECT * FROM cart c ORDER BY c.status", nativeQuery = true)
	List<Cart> getByStatus();
}
