package vn.scrip.buoi24.entity;

import jakarta.persistence.*;
import lombok.*;
import vn.scrip.buoi24.model.enums.UserRole;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteMovie> favoriteMovies;
}
