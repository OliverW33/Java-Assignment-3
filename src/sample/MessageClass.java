package sample;

import org.json.simple.JSONObject;

import java.util.List;

public class MessageClass extends RequestClass{

    private List<MessageClass> messageList;
    private String from = "";
    private String body = "";
    private long when = 0;

    public MessageClass(String newMessageRecipient, String newMessageBody)
    {
        _class = "Message";
        from = newMessageRecipient;
        body = newMessageBody;
        when = System.currentTimeMillis();
    }

    public String getMessageRecipient()
    {
        return from;
    }

    public String getMessageBody()
    {
        return body;
    }

    public float getMessageTimestamp()
    {
        return when;
    }

    public MessageClass(JSONObject jsonObject)
    {
        if (jsonObject != null)
        {
            from = (String) jsonObject.get("from");
            body = (String) jsonObject.get("body");
            when = (long) jsonObject.get("when");
        }
    }

    public JSONObject newJSONObj()
    {
        JSONObject newObject = new JSONObject();
        newObject.put("_class", _class);
        newObject.put("from", from);
        newObject.put("body", body);
        newObject.put("when", when);
        return newObject;
    }




}
