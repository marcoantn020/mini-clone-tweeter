package marcoantn020.twiterclone.repository;

import marcoantn020.twiterclone.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {
}
