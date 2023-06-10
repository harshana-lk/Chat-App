package me.harshana.chatapp.client.util.helpers;

import me.harshana.chatapp.client.util.config.MessageConfiguration;
import me.harshana.chatapp.client.util.config.StreamConfiguration;

public class ApplicationContext {
    private static final StreamConfiguration streamConfiguration = new StreamConfiguration();
    private static final MessageConfiguration messageConfiguration = new MessageConfiguration();

    public static StreamConfiguration getStreamConfiguration() {
        return streamConfiguration;
    }

    public static MessageConfiguration getMessageConfiguration() {
        return messageConfiguration;
    }
}
