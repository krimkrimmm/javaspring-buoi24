package vn.scrip.buoi24.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FavoriteMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
