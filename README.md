# 工程简介
springboot整合rabbitmq demo
# 延伸阅读
幂等性：不重复消费 和 消息不丢失
重复消费：
分析：
（1）生产者重复发消息了
（3）消费者异常导致重试，多次消费消息了
解决：
（1）消息加唯一id，并且保证消息准确送达交换机和队列
（2）消费端开启重试机制，在basicAck确认前，保存消息信息，根据唯一的id判断消息是否消费过；
消息不丢失：
1.保证消息不丢失
（1）RabbitTemplate -> change
实现Rabbitmq.ConfirmCallback接口重写confirm方法，并根据ack返回值写业务逻辑
（2）change -> queue
实现RabbitTemplate.ReturnCallback接口重写returnedMessage方法
（3）queue -> consumer
消费者配置监听的队列，手动确认消息channel.basicAck 
（4）rabbitmq开启持久化机制