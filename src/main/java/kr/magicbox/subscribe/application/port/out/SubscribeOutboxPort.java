package kr.magicbox.subscribe.application.port.out;

import kr.magicbox.subscribe.domain.event.SubscribeDomainEvent;

public interface SubscribeOutboxPort {
    void save(SubscribeDomainEvent event);
}
