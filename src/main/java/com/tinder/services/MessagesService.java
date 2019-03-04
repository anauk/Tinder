package com.tinder.services;



import com.tinder.DAO.MessagesDAO_SQL;
import com.tinder.entity.MessageItem;
import com.tinder.entity.MessageItemExtra;

import java.util.List;

public class MessagesService {
    private MessagesDAO_SQL messages;

    public MessagesService(MessagesDAO_SQL messages) {
        this.messages = messages;
    }

    public void addMessage(MessageItem item) {
        messages.add(item);
    }

    public void addMessage (int user1_id, int user2_id, String message) {
        MessageItem item = new MessageItem(user1_id, user2_id, message);
        messages.add(item);
    }

    public void removeItem (int id){ // TODO перехват ElementNotFoundInDbException
        messages.remove(id);
    }

    public List<MessageItem> getAll() {
        return messages.getAll();
    }

    public MessageItem getItem (int id){ // TODO перехват ElementNotFoundInDbException
        return messages.get(id);
    }

    public boolean isGoodsDbEmpty (){
        return messages.isEmpty();
    }

    public List<MessageItemExtra> getByUser(int user1_id, int user2_id) {
        return messages.getByUser(user1_id, user2_id);
    }
}
