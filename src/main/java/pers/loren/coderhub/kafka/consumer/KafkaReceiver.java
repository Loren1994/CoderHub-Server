package pers.loren.coderhub.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.logging.Logger;

//@Component
public class KafkaReceiver {

    private Logger logger = java.util.logging.Logger.getLogger("KafkaReceiver");

    @KafkaListener(topics = {"k_message"})
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            Object message = kafkaMessage.get();
            logger.info(">>>>>>> record =" + record);
            logger.info(">>>>>>> message =" + message);
        }
    }
}
