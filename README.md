# 工程简介
springboot整合rabbitmq demo
# 延伸阅读
1.保证消息不丢失
（1）producer -> change
实现Rabbitmq.ConfirmCallback接口重写confirm方法，并根据ack返回值写业务逻辑
（2）change -> queue
实现RabbitTemplate.ReturnCallback接口重写returnedMessage方法
（3）queue -> consumer
消费者配置监听的队列，手动确认消息channel.basicAck 
（4）rabbitmq开启持久化机制