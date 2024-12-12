package org.example.langgateway.component

import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class G1Filter : GlobalFilter, Ordered{
    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
        println("Pre Global Filter Order - 1")
        return chain!!.filter(exchange)
            .then(Mono.fromRunnable {
                println("Post global Filter order - 1")
            })
    }

    //필터들이 다수일 경우 getOrder 값에 따라 순서가 정해진다 (숫자가 낮을수록 우선순위 빠름)
    override fun getOrder(): Int {
        return -1
    }
}