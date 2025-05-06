package vn.scrip.buoi24.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "favorite_movies")
@Data

@NoArgsConstructor
@AllArgsConstructor
public class FavoriteMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}








