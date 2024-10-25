package mavem.ti2cc.service;
import spark.Request;
public class CookieRequest {

    public CookieRequest(){}
    public int getUserId(Request request){
            String userCookie = request.cookie("user_id");
            if(userCookie != null){
                return Integer.parseInt(userCookie);
            }else{
                return -1;
            }
    }
}
