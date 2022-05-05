package pl.tomaszborowski.zadjavadevhosting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tomaszborowski.zadjavadevhosting.Image;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
}
