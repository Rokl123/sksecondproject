package raf.runner;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import raf.domain.FiskulturnaSala;
import raf.domain.Rezervacija;
import raf.domain.TipTreninga;
import raf.domain.Trening;
import raf.repository.FiskulturnaSalaRepository;
import raf.repository.RezervacijaRepository;
import raf.repository.TipTreningaRepository;
import raf.repository.TreningRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@Component
public class TestDataRunner implements CommandLineRunner {
    private FiskulturnaSalaRepository fiskulturnaSalaRepository;

    private TipTreningaRepository tipTreningaRepository;

    private TreningRepository treningRepository;

    private RezervacijaRepository rezervacijaRepository;

    @Override
    public void run(String... args) throws Exception {
        FiskulturnaSala fiskulturnaSala = new FiskulturnaSala();
        fiskulturnaSala.setKapacitet(20);
        fiskulturnaSala.setName("Ahilej");
        fiskulturnaSala.setBrojTrenera(5);
        fiskulturnaSala.setLoyalty(2);
        fiskulturnaSala.setManager_id(1L);
        fiskulturnaSalaRepository.save(fiskulturnaSala);

        TipTreninga tipTreninga = new TipTreninga();
        tipTreninga.setNazivTipa("joga");
        TipTreninga tipTreninga1 = new TipTreninga();
        tipTreninga1.setNazivTipa("kalistenika");
        TipTreninga tipTreninga2 = new TipTreninga();
        tipTreninga2.setNazivTipa("power lifting");
        TipTreninga tipTreninga3 = new TipTreninga();
        tipTreninga3.setNazivTipa("fitnes");
        tipTreningaRepository.save(tipTreninga);
        tipTreningaRepository.save(tipTreninga1);
        tipTreningaRepository.save(tipTreninga2);
        tipTreningaRepository.save(tipTreninga3);

        Trening trening = new Trening();
        trening.setSala(fiskulturnaSala);
        trening.setTip(tipTreninga1);
        trening.setGrupni(true);
        trening.setTerminTreninga(LocalDate.of(2024,1,15));
        trening.setPocetakTermina(LocalTime.of(15,15));
        trening.setKrajTermina(LocalTime.of(17,15));
        trening.setCenaTreninga(1500);

        Trening trening1 = new Trening();
        trening1.setSala(fiskulturnaSala);
        trening1.setTip(tipTreninga2);
        trening1.setGrupni(true);
        trening1.setTerminTreninga(LocalDate.of(2024,1,15));
        trening1.setPocetakTermina(LocalTime.of(15,15));
        trening1.setKrajTermina(LocalTime.of(16,15));
        trening1.setCenaTreninga(1300);
        treningRepository.save(trening);
        treningRepository.save(trening1);

        Rezervacija rezervacija = new Rezervacija();

        rezervacija.setClientID(Long.valueOf(1));
        rezervacija.setCenaTreninga(1500);
        rezervacija.setRezervisaniTrening(trening);

        rezervacijaRepository.save(rezervacija);

    }
}
