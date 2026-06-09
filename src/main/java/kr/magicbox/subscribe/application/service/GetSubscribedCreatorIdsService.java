package kr.magicbox.subscribe.application.service;

import kr.magicbox.subscribe.application.port.in.GetSubscribedCreatorIdsUseCase;
import kr.magicbox.subscribe.application.port.out.SubscriptionRepositoryPort;
import kr.magicbox.subscribe.domain.vo.SubscriberId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSubscribedCreatorIdsService implements GetSubscribedCreatorIdsUseCase {

    private final SubscriptionRepositoryPort subscriptionRepositoryPort;

    @Override
    @Transactional(readOnly = true)
    public List<Long> getSubscribedCreatorIds(SubscriberId subscriberId) {
        return subscriptionRepositoryPort.findCreatorIdsBySubscriberId(subscriberId);
    }
}
