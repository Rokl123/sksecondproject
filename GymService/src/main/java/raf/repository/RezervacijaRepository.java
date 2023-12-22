package raf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raf.domain.Rezervacija;

import java.util.Optional;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija,Long> {

    Optional<Rezervacija> findByRezervisaniTrening_Sala_Name(String nazivSale);

}
