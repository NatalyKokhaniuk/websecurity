package edu.kokhaniuk.security.weapon;

/*
  @author nataly
  @project security
  @class WeaponRestController
  @version 1.0.0
  @since 12/30/2025 - 11.52
*/

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weapons")
@AllArgsConstructor
public class WeaponRestController {

    private final WeaponService service;

    @GetMapping
    public List<Weapon> getWeapons() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Weapon getOneWeapon(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }

    @PostMapping
    public Weapon create(@RequestBody Weapon Weapon) {
        return service.create(Weapon);
    }

    @PutMapping
    public Weapon update(@RequestBody Weapon Weapon) {
        return service.update(Weapon);
    }
}