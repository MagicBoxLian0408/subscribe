package kr.magicbox.subscribe.adapter.in.web;

import kr.magicbox.subscribe.adapter.in.web.constants.CursorConstants;
import kr.magicbox.subscribe.adapter.in.web.dto.response.CursorResponse;
import kr.magicbox.subscribe.adapter.in.web.dto.response.SubscriptionResponse;
import kr.magicbox.subscribe.adapter.in.web.validation.CursorSize;
import kr.magicbox.subscribe.application.dto.query.GetMySubscriptionsQuery;
import kr.magicbox.subscribe.application.dto.result.GetMySubscriptionsResult;
import kr.magicbox.subscribe.application.port.in.GetMySubscriptionsUseCase;
import kr.magicbox.subscribe.domain.vo.SubscriberId;
import kr.magicbox.subscribe.domain.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/me")
@RequiredArgsConstructor
@Validated
public class SubscribeQueryController {
    private final GetMySubscriptionsUseCase getMySubscriptionsUseCase;

    @GetMapping
    public ResponseEntity<CursorResponse<SubscriptionResponse>> getMySubscriptions(
            @AuthenticationPrincipal UserId userId,
            @RequestParam(required = false) Long cursorId,
            @CursorSize @RequestParam(defaultValue = CursorConstants.DEFAULT_SIZE) int size) {
        List<GetMySubscriptionsResult> results = getMySubscriptionsUseCase.getMySubscriptions(
                GetMySubscriptionsQuery.of(SubscriberId.of(userId.value()), cursorId, size + 1));
        List<SubscriptionResponse> responses = results.stream().map(SubscriptionResponse::from).toList();
        return ResponseEntity.ok(CursorResponse.of(responses, size, r -> r.subscriptionId()));
    }
}
