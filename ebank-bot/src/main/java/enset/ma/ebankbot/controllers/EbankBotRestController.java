package enset.ma.ebankbot.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EbankBotRestController {
    private ChatClient chatClient;

    public EbankBotRestController(ChatClient.Builder chatClient, ChatMemory chatMemory) {
        this.chatClient = chatClient
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
    @GetMapping("/chat")
    public String chat(@RequestParam String query,@RequestParam String conversionId){
        return chatClient
                .prompt(query)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversionId))
                .call()
                .content();
    }
}
