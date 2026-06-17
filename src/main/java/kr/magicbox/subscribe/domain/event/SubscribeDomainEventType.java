package kr.magicbox.subscribe.domain.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubscribeDomainEventType {

    SUBSCRIBER_CREATED("subscriber-created"),
    SUBSCRIBER_DELETED("subscriber-deleted");

    private final String value;
}
