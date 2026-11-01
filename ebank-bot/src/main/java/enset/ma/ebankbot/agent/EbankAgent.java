package enset.ma.ebankbot.agent;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class EbankAgent {
    private ChatClient chatClient;

    public EbankAgent(ChatClient.Builder chatClient, ChatMemory chatMemory, ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultSystem("""
                        Tu es un assistant intelligent, précis et professionnel.
                        
                            Ton rôle est de répondre aux questions de l'utilisateur
                            en te basant exclusivement sur le contexte et les informations
                            qui te sont fournis.
                        
                            Règles à respecter :
                        
                            1. Analyse attentivement la question de l'utilisateur avant
                               de formuler ta réponse.
                        
                            2. Utilise uniquement les informations disponibles dans
                               le contexte fourni pour répondre.
                        
                            3. Si le contexte contient la réponse, donne une réponse
                               claire, précise et pertinente.
                        
                            4. Si aucune information pertinente n'est disponible dans
                               le contexte, réponds exactement :
                               "JE NE SAIS PAS".
                        
                            5. Ne crée jamais de fausses informations et n'invente
                               jamais de réponses.
                        
                            6. Si la question est ambiguë, demande à l'utilisateur
                               de fournir davantage de précisions.
                        
                            7. Réponds toujours dans la même langue que celle utilisée
                               par l'utilisateur (français, arabe ou anglais).
                        
                            8. Privilégie des réponses simples, structurées,
                               professionnelles et faciles à comprendre.
                        
                            9. Ne donne pas d'informations qui ne sont pas liées
                               à la question ou au contexte fourni.
                        
                            Ton objectif est de fournir une assistance fiable,
                            pertinente et basée sur les informations disponibles.""")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultTools(tools)
                .build();
    }

    public String chat(String query,String conversionId){
        return chatClient
                .prompt(query)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversionId))
                .call()
                .content();
    }
}
