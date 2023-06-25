package com.capstone.cart.controller;

import java.util.List;

import com.capstone.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capstone.cart.model.Cart;
import com.capstone.cart.service.CartService;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin("localhost:8082/api/v1/allorders")
//@CrossOrigin(origins = "*")
@CrossOrigin("http://localhost:4200")
public class  CartController {
	
	@Autowired
	CartService cartServ;
	@Autowired
	CartRepository cartRepo;
	//retrieve item by cart id
//	@CrossOrigin(origins = "*")
	//@CrossOrigin(origins = "http://localhost:8082")
//	@GetMapping("/allorders")
//	public ResponseEntity<List<Cart>> getAllOrders(){
//		List<Cart> orders = cartServ.getAllOrder();
//		return new ResponseEntity<List<Cart>>(orders,HttpStatus.OK);
//	}  // working function for getting all orders
	@GetMapping("/sortbystatus")
	public ResponseEntity<List<Cart>> getByStatus(){
		List<Cart> orders = cartRepo.getByStatus();
		return new ResponseEntity<List<Cart>>(orders,HttpStatus.OK);
	}
	@GetMapping("cart/viewBycart/{cId}")
	public ResponseEntity<?> getDetailsByCartId(@PathVariable Long cId){
		Cart c = cartServ.getByCartId(cId);
		if(c!=null)
			return new ResponseEntity<Cart>(c, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Cart not available",HttpStatus.NOT_FOUND);
	}

	//retrieve item by userid
	@GetMapping("cart/viewByuser/{uId}")
	public ResponseEntity<?> getDetailsByUserId(@PathVariable Long uId){
		List<Cart> cList = cartServ.getByUserId(uId);
		if(cList != null)
			return new ResponseEntity<List<Cart>>(cList, HttpStatus.OK);
		else
			return new ResponseEntity<String>("User not available",HttpStatus.NOT_FOUND);
		
	}
	//to add item into cart
	@PostMapping("cart/addCart")
	public ResponseEntity<?> addCart(@RequestBody Cart cNew){

		Cart c = cartServ.addCart(cNew);

		return new ResponseEntity<Cart>(c, HttpStatus.OK);
	}
	
//	@DeleteMapping("delete/{cId}/{uId}")
//	public ResponseEntity<?> deleteCart(@PathVariable("cId") Long cId,@PathVariable("uId") Long uId){
//		Boolean cartRes= cartServ.deleteCart(cId,uId);
//		return new ResponseEntity<Boolean>(cartRes, HttpStatus.OK);
//	}
	//to delete an item in cart
	@DeleteMapping("cart/del/{cartId}/{userId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId, @PathVariable Long userId) {
		cartServ.deleteCartByCartIdAndUserId(cartId, userId);
        return ResponseEntity.ok("Cart deleted successfully.");
    }

	//to increment the quantity of items in cart
	@PutMapping("cart/incrementUpdateqQuantity/{cartid}")
	public ResponseEntity<Cart> iupdateQuantity(@PathVariable Long cartid){
		return ResponseEntity.ok(cartServ.iupdateQuantity(cartid));
	}
	//to decrement the quantity of items in cart
	@PutMapping("cart/decrementUpdateQuantity/{cartid}")
	public ResponseEntity<Cart> dupdateQuantity(@PathVariable Long cartid){
		return ResponseEntity.ok(cartServ.dupdateQuantity(cartid));
	}
	@PutMapping("status/{cartid}")
	public ResponseEntity<Cart> updateStatus(@PathVariable Long cartid){
		return ResponseEntity.ok(cartServ.updateStatus(cartid));
	}


}
