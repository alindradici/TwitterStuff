

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cautare")
public class UserServlet extends HttpServlet {

    private String name ="";
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // read user and password introduced by the user in the UI
        String user = request.getParameter("u");
        System.out.println("*****userul e :" + user);
        name=user;

         int userid = -1;
        try {
            userid = AccessDB.isUser(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userid!=-1) {
            System.out.println("user gasit cu id:"+userid);
            HttpSession session = request.getSession(true);
            session.setAttribute("numeuser", user);
            session.setAttribute("iduser", userid);
            String success = "/user.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(success);
            dispatcher.forward(request, response);
            new TweetBean().setFriendId(userid);
        }
        else {

            System.out.println("nu exista acest user in db, deci nu fac nimic ");
            String back = "/index.html";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
            dispatcher.forward(request, response);
        }

        System.out.println("login service called...");
        System.out.println(userid);
    }
    public String getName(){
        return name;
    }

}

