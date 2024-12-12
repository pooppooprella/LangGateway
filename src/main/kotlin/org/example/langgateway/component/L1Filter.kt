package org.example.langgateway.component

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@Component
class L1Filter// 주 생성자를 추가하고 상위 클래스의 생성자에 Config 클래스를 전달
    () : AbstractGatewayFilterFactory<L1Filter.Config>(Config::class.java) {

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange?, chain: GatewayFilterChain ->
            if (config.pre) {
                println("Pre local filter 1")
            }
            chain.filter(exchange)
                .then(Mono.fromRunnable {
                    if (config.post) {
                        println("Post local filter 1")
                    }
                })
        }
    }

    // Config 클래스를 data class로 정의
    data class Config(
        val pre: Boolean,
        val post: Boolean
    )
}