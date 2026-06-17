package kr.magicbox.subscribe.domain.event;

public interface SubscribeDomainEvent {
    String key();
    SubscribeDomainEventType eventType();
}
