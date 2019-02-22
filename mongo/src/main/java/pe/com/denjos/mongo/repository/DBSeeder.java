package pe.com.denjos.mongo.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pe.com.denjos.mongo.model.Address;
import pe.com.denjos.mongo.model.Hotel;
import pe.com.denjos.mongo.model.Review;

@Component
public class DBSeeder implements CommandLineRunner {

	private HotelRepository hotelRepository;

	public DBSeeder(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Hotel marriot = new Hotel("Marriot",130,new Address("Paris", "France"),
				Arrays.asList(new Review("Javier", 8, false), new Review("Oscar", 7, true)));
		Hotel ibit = new Hotel("Ibis",90, new Address("Bucharest", "Romania"),
				Arrays.asList(new Review("Teddy", 8, false), new Review("Mary", 7, true)));
		Hotel sofitel = new Hotel("Sofitel",200,new Address("Rome", "Italy"), new ArrayList<>());
		this.hotelRepository.deleteAll();
		List<Hotel> hotels = Arrays.asList(marriot, ibit, sofitel);
		this.hotelRepository.saveAll(hotels);
	}

}
