package com.lebanking.api.application.feign;

import com.lebanking.api.domain.model.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "webhook-site", url = "https://webhook.site")
public interface WebhookSiteClient {

    @PostMapping("/e08539b3-6770-47a1-9021-f5b12d09cdd8")
    void sendCallback(@RequestBody Transaction transaction);
}

