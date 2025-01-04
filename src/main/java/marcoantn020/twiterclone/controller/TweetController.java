package marcoantn020.twiterclone.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import marcoantn020.twiterclone.controller.dto.CreateTweetDto;
import marcoantn020.twiterclone.controller.dto.ResponseFeedDto;
import marcoantn020.twiterclone.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweets")
@SecurityRequirement(name = "bearer-key")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @PostMapping
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDto dto, JwtAuthenticationToken token) {
        tweetService.create(dto, token);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") Long tweetId, JwtAuthenticationToken token) {
        tweetService.delete(tweetId, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/feed")
    public ResponseEntity<ResponseFeedDto> feed(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "sizePage", defaultValue = "10") int sizePage) {
        return ResponseEntity.ok(tweetService.feed(page, sizePage));
    }
}
