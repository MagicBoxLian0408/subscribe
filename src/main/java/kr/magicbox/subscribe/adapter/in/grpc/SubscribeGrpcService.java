package kr.magicbox.subscribe.adapter.in.grpc;

import io.grpc.stub.StreamObserver;
import kr.magicbox.subscribe.application.dto.query.GetSubscriberCountQuery;
import kr.magicbox.subscribe.application.dto.query.IsSubscribedQuery;
import kr.magicbox.subscribe.application.port.in.GetSubscribedCreatorIdsUseCase;
import kr.magicbox.subscribe.application.port.in.GetSubscriberCountUseCase;
import kr.magicbox.subscribe.application.port.in.IsSubscribedUseCase;
import kr.magicbox.subscribe.domain.vo.CreatorId;
import kr.magicbox.subscribe.domain.vo.SubscriberId;
import kr.magicbox.subscribe.grpc.subscribe.GetSubscribedCreatorIdsRequest;
import kr.magicbox.subscribe.grpc.subscribe.GetSubscribedCreatorIdsResponse;
import kr.magicbox.subscribe.grpc.subscribe.GetSubscriberCountRequest;
import kr.magicbox.subscribe.grpc.subscribe.GetSubscriberCountResponse;
import kr.magicbox.subscribe.grpc.subscribe.IsSubscribedRequest;
import kr.magicbox.subscribe.grpc.subscribe.IsSubscribedResponse;
import kr.magicbox.subscribe.grpc.subscribe.SubscribeServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class SubscribeGrpcService extends SubscribeServiceGrpc.SubscribeServiceImplBase {
    private final GetSubscriberCountUseCase getSubscriberCountUseCase;
    private final IsSubscribedUseCase isSubscribedUseCase;
    private final GetSubscribedCreatorIdsUseCase getSubscribedCreatorIdsUseCase;

    @Override
    public void getSubscriberCount(GetSubscriberCountRequest request,
                                   StreamObserver<GetSubscriberCountResponse> responseObserver) {
        long subscriberCount = getSubscriberCountUseCase.getSubscriberCount(
                GetSubscriberCountQuery.of(CreatorId.of(request.getCreatorId()))
        );

        responseObserver.onNext(GetSubscriberCountResponse.newBuilder()
                .setSubscriberCount(subscriberCount)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void isSubscribed(IsSubscribedRequest request,
                             StreamObserver<IsSubscribedResponse> responseObserver) {
        boolean subscribed = isSubscribedUseCase.isSubscribed(
                IsSubscribedQuery.of(CreatorId.of(request.getCreatorId()), SubscriberId.of(request.getUserId()))
        );

        responseObserver.onNext(IsSubscribedResponse.newBuilder()
                .setSubscribed(subscribed)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void getSubscribedCreatorIds(GetSubscribedCreatorIdsRequest request,
                                        StreamObserver<GetSubscribedCreatorIdsResponse> responseObserver) {
        List<Long> creatorIds = getSubscribedCreatorIdsUseCase.getSubscribedCreatorIds(
                SubscriberId.of(request.getUserId())
        );

        responseObserver.onNext(GetSubscribedCreatorIdsResponse.newBuilder()
                .addAllCreatorIds(creatorIds)
                .build());
        responseObserver.onCompleted();
    }
}
