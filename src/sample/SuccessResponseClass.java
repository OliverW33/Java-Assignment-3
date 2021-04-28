package sample;

import org.json.simple.JSONObject;

public class SuccessResponseClass extends RequestClass{

    public SuccessResponseClass()
    {
        _class = "SuccessResponse";
    }

    @Override
    public JSONObject newJSONObj()
    {
        JSONObject newObject = new JSONObject();
        newObject.put("_class", _class);
        return newObject;
    }


}