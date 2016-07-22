import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/button")
public class followUserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("bun venit pe pagina de follow");
        boolean doIt = true;
        String buttonvalue = "";
        buttonvalue = req.getParameter("buttonVal");
        int myId=0;
        int friendId=0;
        try {
             myId = AccessDB.isUser(new Login().getName());
             friendId = AccessDB.isUser(new UserServlet().getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        do {
            if (buttonvalue.equals("true")) {
                try {
                    AccessDB.addRelation(myId, friendId);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            doIt=false;

        }while(doIt);

    }
}
