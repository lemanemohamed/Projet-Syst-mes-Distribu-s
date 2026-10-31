package enset.ma.ebankbot.controllers;

import enset.ma.ebankbot.agent.EbankAgent;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EbankBotRestController {
    private EbankAgent ebankAgent;
    public EbankBotRestController(EbankAgent ebankAgent) {
        this.ebankAgent = ebankAgent;
    }
    @GetMapping("/chat")
    public String chat(@RequestParam String query, @RequestParam String conversionId){
        return ebankAgent.chat(query,conversionId);

    }

}
