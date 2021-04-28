package sample;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageListResponseClass extends RequestClass{

    List<MessageClass> messageList = new ArrayList<>();

    public MessageListResponseClass(List <MessageClass> newMessageList)
    {
        _class = "MessageListResponse";
        messageList = newMessageList;
    }

    public MessageListResponseClass(JSONObject newJSON)
    {
        JSONArray messageCollection = (JSONArray)newJSON.get("messages");

        for (Object newObj : messageCollection)
        {
            JSONObject messageObj = (JSONObject) newObj;
            Long newLong = (long) messageObj.get("when");
            int newInt = newLong.intValue();

            messageList.add(new MessageClass((String)messageObj.get("from"),(String)messageObj.get("body")));
        }
    }

    @Override
    public JSONObject newJSONObj()
    {
        List<JSONObject> jsonObj = new ArrayList<JSONObject>();

        for (MessageClass newMsg : messageList)
        {
            jsonObj.add(newMsg.newJSONObj());
        }

        JSONObject newObject = new JSONObject();
        newObject.put("_class", _class);
        newObject.put("messages", jsonObj);
        return newObject;
    }

    public List<MessageClass> getMessages()
    {
        return messageList;
    }



}
