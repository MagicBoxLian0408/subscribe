package kr.magicbox.subscribe.adapter.out.persistence;

import kr.magicbox.subscribe.adapter.out.persistence.entity.SubscribeOutboxEntity;
import kr.magicbox.subscribe.adapter.out.persistence.repository.SubscribeOutboxRepository;
import kr.magicbox.subscribe.application.port.out.SubscribeOutboxPort;
import kr.magicbox.subscribe.domain.event.SubscribeDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

@Repository
@RequiredArgsConstructor
public class SubscribeOutboxAdapter implements SubscribeOutboxPort {

    private final SubscribeOutboxRepository subscribeOutboxRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void save(SubscribeDomainEvent event) {
        String payload = objectMapper.writeValueAsString(event);
        subscribeOutboxRepository.save(SubscribeOutboxEntity.builder()
                .eventType(event.eventType().getValue())
                .key(event.key())
                .payload(payload)
                .build());
    }
}
