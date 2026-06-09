package kr.magicbox.subscribe.application.port.in;

import kr.magicbox.subscribe.application.dto.query.GetMySubscriptionsQuery;
import kr.magicbox.subscribe.application.dto.result.GetMySubscriptionsResult;

import java.util.List;

public interface GetMySubscriptionsUseCase {
    List<GetMySubscriptionsResult> getMySubscriptions(GetMySubscriptionsQuery query);
}
