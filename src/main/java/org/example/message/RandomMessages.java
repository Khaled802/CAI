package org.example.message;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomMessages {
    private static final Map<Message, List<String>> messages = Map.of(
            Message.SUCCESS, List.of("Very good!", "Keep up the good work!", "Excellent!", "Nice work!"),
            Message.FAILED, List.of("No. Please try again.", "Wrong. Try once more.", "Don't give up!", "No. Keep trying")
    );
    private static final Random random = new Random();
    public static String getMessage(Message message) {
        List<String> messageList = messages.get(message);
        return messageList.get(random.nextInt(messageList.size()));
    }
}
