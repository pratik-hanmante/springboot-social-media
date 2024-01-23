package com.pratik.websocket.chatroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Optional<String> getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ) {
        return chatRoomRepository.findBySenderIdAndRecipientId(senderId,recipientId)
                .map(ChatRoom::getChatId)
//                creating new room if doesnt exist
                .or(() -> {
                    if (createNewRoomIfNotExists) {
                        var chatId = createChat(senderId, recipientId);
                    }
                    return Optional.empty();
                });
    }

    private String createChat(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId); // pratik_pratikHanmante

        ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .build();
    }
    ChatRoom senderRecipient = ChatRoom.builder()
                .chatId(chatId)
                .build();
    }
}
