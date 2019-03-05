package tinder.com.service;

import tinder.com.entity.MessageItem;
import tinder.com.entity.MessageItemExtra;
import tinder.com.impl.MessagesDAO_SQl;

import java.util.List;

public class MessagesService {
    private final MessagesDAO_SQl messages;

    public MessagesService(MessagesDAO_SQl messages) {
        this.messages = messages;
    }
    public void addMessage(MessageItem item){
        messages.add(item);
    }
    public void addMessage(int id1, int id2, String message){
        MessageItem item = new MessageItem(id1, id2, message);
        messages.add(item);
    }
    public List<MessageItem> all(){
        return messages.all();
    }
    public MessageItem get(int id){
        return messages.get(id);
    }
    public void remove(int id){
        messages.remove(id);
    }
    public boolean isMessageDBEmpty(){
        return messages.isEmpty();
    }
    public List<MessageItemExtra> getByUser(int id1, int id2){
        return messages.getByUser(id1, id2);
    }
}
