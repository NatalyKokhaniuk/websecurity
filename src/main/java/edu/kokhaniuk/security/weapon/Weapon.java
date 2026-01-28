package edu.kokhaniuk.security.weapon;

/*
  @author nataly
  @project security
  @class Weapon
  @version 1.0.0
  @since 12/30/2025 - 11.45
*/

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Weapon {
    @Id
    private String id;
    private String name;
    private String category;

    public Weapon(String name, String category) {
        this.name = name;
        this.category = category;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Weapon weapon)) return false;

        return getId().equals(weapon.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
