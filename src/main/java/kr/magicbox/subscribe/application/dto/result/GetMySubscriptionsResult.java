package kr.magicbox.subscribe.application.dto.result;

public record GetMySubscriptionsResult(Long subscriptionId, Long creatorId) {
    public static GetMySubscriptionsResult of(Long subscriptionId, Long creatorId) {
        return new GetMySubscriptionsResult(subscriptionId, creatorId);
    }
}
