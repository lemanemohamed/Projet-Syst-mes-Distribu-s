package enset.ma.ebankbot.discord;

import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import enset.ma.ebankbot.agent.EbankAgent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@DiscordController
public class DiscorBot {
    private EbankAgent ebankAgent;

    public DiscorBot(EbankAgent ebankAgent) {
        this.ebankAgent = ebankAgent;
    }
    @DiscordMapping
    private void perform(MessageReceivedEvent event){
        if(event.getAuthor().isBot()) return;
        String query=event.getMessage().getContentRaw();
        String responce=ebankAgent.chat(query,"test"+(Math.random()*10));
        event.getChannel().sendMessage(responce).queue();
    }
}
