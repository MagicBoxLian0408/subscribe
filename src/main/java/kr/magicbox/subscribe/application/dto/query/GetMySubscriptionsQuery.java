package kr.magicbox.subscribe.application.dto.query;

import kr.magicbox.subscribe.domain.vo.SubscriberId;

public record GetMySubscriptionsQuery(SubscriberId subscriberId, Long cursorId, int size) {
    public static GetMySubscriptionsQuery of(SubscriberId subscriberId, Long cursorId, int size) {
        return new GetMySubscriptionsQuery(subscriberId, cursorId, size);
    }
}
