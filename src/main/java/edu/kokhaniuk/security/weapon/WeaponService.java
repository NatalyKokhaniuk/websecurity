package edu.kokhaniuk.security.weapon;

/*
  @author eugen
  @project security
  @class WeaponService
  @version 1.0.0
  @since 9/30/2025 - 11.46
*/

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WeaponService {

    private final WeaponRepository repository;

    private List<Weapon> weapons;

    @PostConstruct
    void init() {
        weapons.add(new Weapon("1", "AR-15", "Assault rifle"));
        weapons.add(new Weapon("2", "Bohdana", "Self-propelled artillery"));
        weapons.add(new Weapon("3", "Sting", "Anti-aircraft FPV drone"));
        repository.deleteAll();
        repository.saveAll(weapons);
    }

    public List<Weapon> getAll() {
        return repository.findAll();
    }

    public Weapon getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Weapon create(Weapon weapon) {
        return repository.save(weapon);
    }

    public Weapon update(Weapon weapon) {
        return repository.save(weapon);
    }
}