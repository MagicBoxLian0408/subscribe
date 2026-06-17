package kr.magicbox.subscribe.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "subscribe_outbox")
public class SubscribeOutboxEntity extends BaseEntity {

    @Column(nullable = false)
    private String eventType;

    @Column(name = "event_key", nullable = false)
    private String key;

    @Column(nullable = false, columnDefinition = "JSON")
    private String payload;

    @Builder
    public SubscribeOutboxEntity(String eventType, String key, String payload) {
        this.eventType = eventType;
        this.key = key;
        this.payload = payload;
    }
}
