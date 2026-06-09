package kr.magicbox.subscribe.application.port.in;

import kr.magicbox.subscribe.domain.vo.SubscriberId;

import java.util.List;

public interface GetSubscribedCreatorIdsUseCase {
    List<Long> getSubscribedCreatorIds(SubscriberId subscriberId);
}
