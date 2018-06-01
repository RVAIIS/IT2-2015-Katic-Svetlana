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
import rva.jpa.Liga;
import rva.reps.LigaRepository;

@RestController
@Api(tags = {"Liga CRUD operacije"})
public class LigaRestController {
	@Autowired
	private LigaRepository ligaRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("liga")
	@ApiOperation(value = "Vraća spisak svih liga iz baze podataka")
	public Collection<Liga> getLiga(){
		return ligaRepository.findAll();
	}
	
	@GetMapping("ligaId/{id}")
	@ApiOperation(value = "Vraća ligu iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	public Liga getLiga(@PathVariable("id") Integer id){
		return ligaRepository.getOne(id);
	}
	
	@GetMapping("ligaNaziv/{naziv}")
	@ApiOperation(value = "Vraća ligu iz baze podataka koji u nazivu sadrži string prosleđen kao path varijabla")
	public Collection<Liga> getLigaByNaziv(@PathVariable("naziv") String naziv){
		return ligaRepository.findByNazivContainingIgnoreCase(naziv);
	}
	
	@CrossOrigin
	@DeleteMapping("liga/{id}")
	@ApiOperation(value = "Briše ligu iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	public ResponseEntity<Liga> deleteLiga(@PathVariable("id") Integer id){
		if(ligaRepository.existsById(id)){
			ligaRepository.deleteById(id);
			if(id == -100)
				jdbcTemplate.execute("INSERT INTO \"liga\"(\"id\", \"naziv\", \"oznaka\") VALUES(-100, 'LIGA NAZIV', 'LIGA SKRACENICA');");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// insert
	@CrossOrigin
	@PostMapping("liga")
	@ApiOperation(value = "Upisuje ligu u bazu podataka")
	public ResponseEntity<Liga> insertLiga(@RequestBody Liga liga){
		if(ligaRepository.existsById(liga.getId()))
				return new ResponseEntity<>(HttpStatus.CONFLICT);
		ligaRepository.save(liga);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// update
	@CrossOrigin
	@PutMapping("liga")
	@ApiOperation(value = "Modifikuje ligu iz baze podataka")
	public ResponseEntity<Liga> updateLiga(@RequestBody Liga liga){
		if(ligaRepository.existsById(liga.getId())){
			ligaRepository.save(liga);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
