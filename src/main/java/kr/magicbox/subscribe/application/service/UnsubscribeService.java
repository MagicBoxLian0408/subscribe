package kr.magicbox.subscribe.application.service;

import kr.magicbox.subscribe.application.dto.command.UnsubscribeCommand;
import kr.magicbox.subscribe.application.port.in.UnsubscribeUseCase;
import kr.magicbox.subscribe.application.port.out.SubscribeOutboxPort;
import kr.magicbox.subscribe.application.port.out.SubscriptionRepositoryPort;
import kr.magicbox.subscribe.domain.event.SubscriberDeletedEvent;
import kr.magicbox.subscribe.domain.exception.SubscriptionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UnsubscribeService implements UnsubscribeUseCase {
    private final SubscriptionRepositoryPort subscriptionRepositoryPort;
    private final SubscribeOutboxPort subscribeOutboxPort;

    @Transactional
    @Override
    public void unsubscribe(UnsubscribeCommand command) {
        if (!subscriptionRepositoryPort.existsBySubscriberIdAndCreatorId(command.subscriberId(), command.creatorId())) {
            throw new SubscriptionNotFoundException();
        }
        subscriptionRepositoryPort.deleteBySubscriberIdAndCreatorId(command.subscriberId(), command.creatorId());
        subscribeOutboxPort.save(SubscriberDeletedEvent.builder()
                .creatorId(command.creatorId().value())
                .subscriberId(command.subscriberId().value())
                .occurredAt(Instant.now())
                .build());
    }
}
