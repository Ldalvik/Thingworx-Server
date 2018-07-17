package tufts;

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
	public boolean isControl(){
        return getUri().equals("/control/");
    }
	
	public String getText(){
		return getParam("text");
	}
	
	public Float getX(){
		return Float.valueOf(getParam("x"));
	}
	
	public Float getY(){
		return Float.valueOf(getParam("y"));
	}
	
	public String getImageUrl(){
		return getParam("url");
	}
	public int getId(){
		return Integer.valueOf(getParam("id"));
	}
	
	public Float getSize(){
		return Float.valueOf(getParam("size"));
	}
	
	public int[] getColor(){
		String[] rgb = getParam("color").split(",");
		int r = Integer.valueOf(rgb[0]);
		int g = Integer.valueOf(rgb[1]);
		int b = Integer.valueOf(rgb[2]);
		return new int[]{r, g, b};
	}
}
