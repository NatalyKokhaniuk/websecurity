package edu.kokhaniuk.security.weapon;

/*
  @author eugen
  @project security
  @class WeaponRepository
  @version 1.0.0
  @since 9/30/2025 - 11.45
*/

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends MongoRepository<Weapon, String> {
}