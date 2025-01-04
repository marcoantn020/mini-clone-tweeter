package marcoantn020.twiterclone.service;

import marcoantn020.twiterclone.controller.dto.CreateTweetDto;
import marcoantn020.twiterclone.controller.dto.FeedItemDto;
import marcoantn020.twiterclone.controller.dto.ResponseFeedDto;
import marcoantn020.twiterclone.entity.Role;
import marcoantn020.twiterclone.entity.Tweet;
import marcoantn020.twiterclone.repository.TweetRepository;
import marcoantn020.twiterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void create(CreateTweetDto dto, JwtAuthenticationToken token) {
        var user = userRepository.findById(UUID.fromString(token.getName()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        var tweet = new Tweet(
                user,
                dto.content()
        );

        tweetRepository.save(tweet);
    }

    @Transactional
    public void delete(Long tweetId, JwtAuthenticationToken token) {

        var user = userRepository.findById(UUID.fromString(token.getName()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        var tweet = tweetRepository.findById(tweetId)
                        .orElseThrow(() -> new RuntimeException("Tweet não encontrado"));

        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

        if (isAdmin || tweet.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
            tweetRepository.deleteById(tweetId);
            return;
        }
        throw new RuntimeException("Você não pode deletar esse Tweet.");
    }


    public ResponseFeedDto feed(int page, int sizePage) {
        var tweets = tweetRepository
                .findAll(PageRequest.of(page, sizePage, Sort.Direction.DESC,"creationTimestamp"))
                .map(tweet -> new FeedItemDto(
                        tweet.getTweetId(),
                        tweet.getUser().getUsername(),
                        tweet.getContent()));

        return new ResponseFeedDto(
                tweets.getContent(),
                page,
                sizePage,
                tweets.getTotalPages(),
                tweets.getNumberOfElements()
        );
    }
}
