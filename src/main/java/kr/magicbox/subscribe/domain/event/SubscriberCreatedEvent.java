package kr.magicbox.subscribe.domain.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.Instant;

@Builder
public record SubscriberCreatedEvent(
        @JsonProperty("creator_id") Long creatorId,
        @JsonProperty("subscriber_id") Long subscriberId,
        @JsonProperty("occurred_at") Instant occurredAt
) implements SubscribeDomainEvent {

    @Override
    public String key() {
        return subscriberId.toString();
    }

    @Override
    public SubscribeDomainEventType eventType() {
        return SubscribeDomainEventType.SUBSCRIBER_CREATED;
    }
}
