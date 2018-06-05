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
import rva.jpa.Igrac;
import rva.jpa.Tim;
import rva.reps.IgracRepository;
import rva.reps.TimRepository;

@RestController
@Api(tags = {"Igrac CRUD operacije"})
public class IgracRestController {
	
	@Autowired
	private IgracRepository igracRepository;
	@Autowired
	private TimRepository timRepository;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@CrossOrigin
	@GetMapping("igrac")
	@ApiOperation(value = "Vraća spisak svih igrača iz baze podataka")
	public Collection<Igrac> getIgraci() {
		return igracRepository.findAll();
	}
	
	@GetMapping("igracId/{id}")
	@ApiOperation(value = "Vraća igrača iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	public Igrac getIgrac(@PathVariable("id") Integer id) {
		return igracRepository.getOne(id);
	}
	//dodala
	//@CrossOrigin
	@GetMapping(value = "igracZaTim/{id}")
	public Collection<Igrac> igraciZaTimId(@PathVariable("id") int id){
		Tim t = timRepository.getOne(id);
		return igracRepository.findByTim(t);
	}
	//PROVERI OBAVEZNO!!
	@GetMapping("igracPrezime/{prezime}")
	@ApiOperation(value = "Vraca igraca iz baze podataka koji u prezimenu sadrzi string prosledjen kao path varijablu")
	public Collection<Igrac> getTimByNaziv(@PathVariable("prezime") String prezime) {
		return igracRepository.findByPrezimeContainingIgnoreCase(prezime);		
	}
	
	
	@CrossOrigin
	@DeleteMapping("igrac/{id}")
	@ApiOperation(value = "Briše igrača iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	public ResponseEntity<Igrac> deleteIgrac(@PathVariable("id") Integer id) {
		if (!igracRepository.existsById(id)) 
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		igracRepository.deleteById(id);
		if (id == -100)
			jdbcTemplate.execute("INSERT INTO \"igrac\"(\"id\", \"ime\", \"prezime\", \"broj_reg\",\"datum_rodjenja\", \"nacionalnost\", \"tim\")VALUES(-100, 'Test ime', 'Test prezime',22,to_date('01.01.1900.', 'dd.mm.yyyy.'), 1,1);");
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping("igrac")
	@ApiOperation(value = "Upisuje igraca u bazu podataka")
	public ResponseEntity<Igrac> insertIgrac(@RequestBody Igrac igrac) {
		if (igracRepository.existsById(igrac.getId())) 
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		igracRepository.save(igrac);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@PutMapping("igrac")
	@ApiOperation(value = "Modifikuje postojeceg igraca iz baze podataka")
	public ResponseEntity<Igrac> updateIgrac(@RequestBody Igrac igrac) {
		if (igracRepository.existsById(igrac.getId())) {
			igracRepository.save(igrac);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}