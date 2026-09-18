package com.muhammettd.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // React'in bağlanacağı ana endpoint
        registry.addEndpoint("/ws-location")
                .setAllowedOriginPatterns("http://localhost:5173", "http://localhost:3000","*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // "/topic" prefixi ile başlayan kanallar mesajların yayınlanacağı yerlerdir
        registry.enableSimpleBroker("/topic");
    }
}
