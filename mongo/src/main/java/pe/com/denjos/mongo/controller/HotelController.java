package pe.com.denjos.mongo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.denjos.mongo.model.Hotel;
import pe.com.denjos.mongo.repository.HotelRepository;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	private HotelRepository hotelRepository;

	public HotelController(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	@GetMapping("/all")
	public List<Hotel> getAll()
	{
		List<Hotel> hotels=this.hotelRepository.findAll();
		return hotels;
	}
	
	@PostMapping(value="/insert",consumes="application/json")
	public void insert(@RequestBody Hotel hotel)
	{
		this.hotelRepository.insert(hotel);
	}
	
	
	@PostMapping
	public void update(@RequestBody Hotel hotel)
	{
		this.hotelRepository.save(hotel);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		this.hotelRepository.deleteById(id);;
	}
	
	@GetMapping("/{id}")
	public Hotel getById(@PathVariable("id") String id) {
		Optional<Hotel> hotel=this.hotelRepository.findById(id);
		return hotel.get();
	}
	@GetMapping("/price/{maxPrice}")
	public List<Hotel> getById(@PathVariable("maxPrice") int maxPrice) {
		List<Hotel> hotels=this.hotelRepository.findBypricePerNightLessThan(maxPrice);
		return hotels;
	}
	

}
