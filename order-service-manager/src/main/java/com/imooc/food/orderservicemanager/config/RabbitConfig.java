package com.imooc.food.orderservicemanager.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitConfig {

//    @Autowired
//    OrderMessageService orderMessageService;
//
//    @Autowired
//    public void startListenMessage() throws IOException, TimeoutException, InterruptedException {
//        orderMessageService.handleMessage();
//    }
//
//    /*---------------------restaurant---------------------*/
//    @Bean
//    public Exchange exchange1() {
//        return new DirectExchange("exchange.order.restaurant");
//    }
//
//    @Bean
//    public Queue queue1() {
//        return new Queue("queue.order");
//    }
//
//    @Bean
//    public Binding binding1() {
//        return new Binding(
//                "queue.order",
//                Binding.DestinationType.QUEUE,
//                "exchange.order.restaurant",
//                "key.order",
//                null);
//    }
//
//    /*---------------------deliveryman---------------------*/
//    @Bean
//    public Exchange exchange2() {
//        return new DirectExchange("exchange.order.deliveryman");
//    }
//
//    @Bean
//    public Binding binding2() {
//        return new Binding(
//                "queue.order",
//                Binding.DestinationType.QUEUE,
//                "exchange.order.deliveryman",
//                "key.order",
//                null);
//    }
//
//
//    /*---------settlement---------*/
//    @Bean
//    public Exchange exchange3() {
//        return new FanoutExchange("exchange.order.settlement");
//    }
//
//    @Bean
//    public Exchange exchange4() {
//        return new FanoutExchange("exchange.settlement.order");
//    }
//
//    @Bean
//    public Binding binding3() {
//        return new Binding(
//                "queue.order",
//                Binding.DestinationType.QUEUE,
//                "exchange.order.settlement",
//                "key.order",
//                null);
//    }
//
//    /*--------------reward----------------*/
//    @Bean
//    public Exchange exchange5() {
//        return new TopicExchange("exchange.order.reward");
//    }
//
//    @Bean
//    public Binding binding4() {
//        return new Binding(
//                "queue.order",
//                Binding.DestinationType.QUEUE,
//                "exchange.order.reward",
//                "key.order",
//                null);
//    }
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost("127.0.0.1");
//        connectionFactory.setPort(5672);
//        connectionFactory.setPassword("guest");
//        connectionFactory.setUsername("guest");
//
//        // 发送者回调消息确认模式
//        // SIMPLE 简单的
//        // CORRELATED 相关的
//        // NONE
//        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
//        connectionFactory.setPublisherReturns(true);
//        connectionFactory.createConnection();
//        return connectionFactory;
//    }
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
//        admin.setAutoStartup(true);
//        return admin;
//    }
//
//    /**
//     * 发送消息，生产者和Server（broker）之间的消息确认
//     *
//     * @param connectionFactory
//     * @return
//     */
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMandatory(true);// 消息返回机制自动托管
//        // ReturnCallback 接口，启动消息失败返回，比如路由不到队列时触发回调
//        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
//            log.error("message:{}, replyCode:{}, replyText:{}, exchange:{}, routingKey:{}",
//                    message, replyCode, replyText, exchange, routingKey);
//        });
//        // ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
//        //
//        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
//                log.info("correlationData:{}, ack:{}, cause:{}", correlationData, ack, cause));
//        return rabbitTemplate;
//    }
//
//
//    @Bean
//    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        return factory;
//    }
//
//    /**
//     * 接受消息，消费者和Server（broker）之间的消息确认
//     *
//     * @param connectionFactory
//     * @return
//     */
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
//        messageListenerContainer.setQueueNames("queue.order"); // 设置监听一个或多个队列
//        messageListenerContainer.setConcurrentConsumers(1); // 设置消费者并发数-设置有几个消费者同时消费数据
//        messageListenerContainer.setMaxConcurrentConsumers(3); // 设置消费者最大并发数
//
//        // 消费者收到消息自动确认
////        messageListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
////        messageListenerContainer.setMessageListener(new MessageListener() {
////            @Override
////            public void onMessage(Message message) {
////                log.info("message:{}", message);
////            }
////        });
//
//        // 消费者收到消息手动ack确认
//        messageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
////        messageListenerContainer.setMessageListener(new ChannelAwareMessageListener() {
////            @Override
////            public void onMessage(Message message, Channel channel) throws Exception {
////                //显示调用消息处理
////                orderMessageService.handleMessage(message.getBody());
////                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
////            }
////        });
//
//        //
//        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter();
//        messageListenerAdapter.setDelegate(orderMessageService);
//
//        Map<String, String> methodMap = new HashMap<>();
//        methodMap.put("queue.order", "handleMessage");
//        methodMap.put("queue.order1", "handleMessage1");
//        methodMap.put("queue.order2", "handleMessage2");
//        messageListenerAdapter.setQueueOrTagToMethodName(methodMap);
//
//        // Jackson2JsonMessageConverter消息转换器，利用setClassMapper将消息转换为对象
//        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
//        converter.setClassMapper(new ClassMapper() {
//            @Override
//            public void fromClass(Class<?> clazz, MessageProperties properties) {
//
//            }
//
//            @Override
//            public Class<?> toClass(MessageProperties properties) {
//                return OrderMessageDTO.class;
//            }
//        });
//        messageListenerAdapter.setMessageConverter(converter);
//        messageListenerContainer.setMessageListener(messageListenerAdapter);
//
//        // 消费者限流
//        messageListenerContainer.setPrefetchCount(2);
//        return messageListenerContainer;
//    }
}
