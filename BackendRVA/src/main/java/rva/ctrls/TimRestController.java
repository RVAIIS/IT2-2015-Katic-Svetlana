package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Tim;
import rva.reps.TimRepository;

@RestController
@Api(tags = {"Tim CRUD operacije"})
public class TimRestController {
	
	@Autowired
	private TimRepository timRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("tim")
	@ApiOperation(value = "Vraća spisak svih timova iz baze podataka")
	public Collection<Tim> getTim(){
		return timRepository.findAll();
	}
	
	@GetMapping("timId/{id}")
	@ApiOperation(value = "Vraća tim iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	public Tim getTim(@PathVariable("id") Integer id){
		return timRepository.getOne(id);
	}
	
	@GetMapping("timNaziv/{naziv}")
	@ApiOperation(value = "Vraća tim iz baze podataka koji u nazivu sadrži string prosleđen kao path varijabla")
	public Collection<Tim> getTimByNaziv(@PathVariable("naziv") String naziv){
		return timRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@CrossOrigin
	@DeleteMapping("tim/{id}")
	@ApiOperation(value = "Briše tim iz baze podataka čiji je id vrednost prosleđena kao path varijabla")
	public ResponseEntity<Tim> deleteTim(@PathVariable("id") Integer id){
		if(timRepository.existsById(id)){
			timRepository.deleteById(id);
			if(id == -100)
				jdbcTemplate.execute("INSERT INTO \"tim\"(\"id\", \"naziv\", \"osnovan\", \"sediste\", \"liga\") VALUES(-100, 'TIM NAZIV', to_date('01.01.1900.', 'dd.mm.yyyy.'), 'TIM SEDISTE', 1);");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// insert
	@CrossOrigin
	@PostMapping("tim")
	@ApiOperation(value = "Upisuje tim u bazu podataka")
	public ResponseEntity<Tim> insertTim(@RequestBody Tim tim){
		if(timRepository.existsById(tim.getId()))
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		timRepository.save(tim);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// update
	@CrossOrigin
	@PutMapping("tim")
	@ApiOperation(value = "Modifikuje postojeći tim iz baze podataka")
	public ResponseEntity<Tim> updateTim(@RequestBody Tim tim){
		if(timRepository.existsById(tim.getId())){
			timRepository.save(tim);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
