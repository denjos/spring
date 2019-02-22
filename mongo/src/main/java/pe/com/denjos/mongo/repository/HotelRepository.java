package pe.com.denjos.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.denjos.mongo.model.Hotel;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,String>{
	
	List<Hotel> findBypricePerNightLessThan(int max);

}
