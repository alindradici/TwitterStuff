import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by icondor on 16/07/16.
 */
public class AccessDBTest {

    public static void main (String [] args){

        System.out.println(new TweetBean().getMyId());
        System.out.println(new TweetBean().getFriendId());
    }
}


