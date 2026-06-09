package kr.magicbox.subscribe.application.service;

import kr.magicbox.subscribe.application.dto.query.GetMySubscriptionsQuery;
import kr.magicbox.subscribe.application.dto.result.GetMySubscriptionsResult;
import kr.magicbox.subscribe.application.port.in.GetMySubscriptionsUseCase;
import kr.magicbox.subscribe.application.port.out.SubscriptionRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMySubscriptionsService implements GetMySubscriptionsUseCase {
    private final SubscriptionRepositoryPort subscriptionRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public List<GetMySubscriptionsResult> getMySubscriptions(GetMySubscriptionsQuery query) {
        return subscriptionRepositoryPort.findBySubscriberIdWithCursor(query.subscriberId(), query.cursorId(), query.size())
                .stream()
                .map(subscription -> GetMySubscriptionsResult.of(subscription.getId().value(), subscription.getCreatorIdValue()))
                .toList();
    }
}
