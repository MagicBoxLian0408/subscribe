package kr.magicbox.subscribe.adapter.out.persistence.repository;

import kr.magicbox.subscribe.adapter.out.persistence.entity.SubscribeOutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeOutboxRepository extends JpaRepository<SubscribeOutboxEntity, Long> {
}
