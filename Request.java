package thingworx;

import fi.iki.elonen.NanoHTTPD;

import java.util.List;
import java.util.Map;

public class Request {
    private NanoHTTPD.IHTTPSession session;

    public Request(NanoHTTPD.IHTTPSession session){
        this.session = session;
    }

    public String getParam(String param){
        Map<String, String> params = session.getParms();
        return params.get(param);
    }

    public String getMethod(){
        return String.valueOf(session.getMethod());
    }

    public String getUri(){
        return session.getUri();
    }

    public boolean isDelete(){
        return getUri().equals("/delete/");
    }

    public boolean isUpdate(){
        return getUri().equals("/update/");
    }

    public boolean isAdd(){
        return getUri().equals("/add/");
    }
}
