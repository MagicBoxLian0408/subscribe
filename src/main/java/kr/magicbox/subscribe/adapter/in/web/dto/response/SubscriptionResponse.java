package kr.magicbox.subscribe.adapter.in.web.dto.response;

import kr.magicbox.subscribe.application.dto.result.GetMySubscriptionsResult;

public record SubscriptionResponse(Long subscriptionId, Long creatorId) {
    public static SubscriptionResponse from(GetMySubscriptionsResult result) {
        return new SubscriptionResponse(result.subscriptionId(), result.creatorId());
    }
}
