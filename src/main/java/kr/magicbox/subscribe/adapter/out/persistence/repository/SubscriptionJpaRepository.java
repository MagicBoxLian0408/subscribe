package kr.magicbox.subscribe.adapter.out.persistence.repository;

import kr.magicbox.subscribe.adapter.out.persistence.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionJpaRepository extends JpaRepository<SubscriptionEntity, Long> {
    boolean existsBySubscriberIdAndCreatorId(Long subscriberId, Long creatorId);

    long countByCreatorId(Long creatorId);

    List<SubscriptionEntity> findAllBySubscriberId(Long subscriberId);

    List<SubscriptionEntity> findAllBySubscriberIdAndIdLessThanOrderByIdDesc(Long subscriberId, Long cursorId, org.springframework.data.domain.Pageable pageable);

    List<SubscriptionEntity> findAllBySubscriberIdOrderByIdDesc(Long subscriberId, org.springframework.data.domain.Pageable pageable);

    List<SubscriptionEntity> findAllByCreatorId(Long creatorId);

    void deleteBySubscriberIdAndCreatorId(Long subscriberId, Long creatorId);

    @Query("SELECT s.creatorId FROM SubscriptionEntity s WHERE s.subscriberId = :subscriberId")
    List<Long> findCreatorIdsBySubscriberId(@Param("subscriberId") Long subscriberId);

    @Modifying(clearAutomatically = true)
    @Query("delete from SubscriptionEntity s where s.subscriberId = :subscriberId")
    int deleteAllBySubscriberId(@Param("subscriberId") Long subscriberId);

    @Modifying(clearAutomatically = true)
    @Query("delete from SubscriptionEntity s where s.creatorId = :creatorId")
    int deleteAllByCreatorId(@Param("creatorId") Long creatorId);
}
