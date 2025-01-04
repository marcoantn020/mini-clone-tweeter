package marcoantn020.twiterclone.controller.dto;

import java.util.List;

public record ResponseFeedDto(List<FeedItemDto> feeds,
                              int page,
                              int sizePage,
                              int totalPage,
                              int totalElement) {
}
