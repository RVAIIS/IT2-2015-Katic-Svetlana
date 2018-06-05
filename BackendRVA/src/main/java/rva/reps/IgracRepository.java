package rva.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rva.jpa.Tim;
import rva.jpa.Igrac;

public interface IgracRepository extends JpaRepository<Igrac, Integer>{
	Collection<Igrac> findByPrezimeContainingIgnoreCase(String prezime);
	Collection<Igrac> findByTim(Tim t);
	@Query(value = "select coalesce(max(redni_broj)+1, 1) from igrac where tim = ?1", nativeQuery = true)
	Integer nextRBr(Integer timId);
}
