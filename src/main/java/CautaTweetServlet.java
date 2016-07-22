import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by alin on 7/22/2016.
 */@WebServlet("/caut")
public class CautaTweetServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         String cauta = req.getParameter("cautaTweet");
        try {
            // List l = AccessDB.readTweets();
            List l = AccessDB.readTweets(cauta);

            // put the list in a json
            JsonObjectBuilder jObjBuilder = Json.createObjectBuilder();
            JsonArrayBuilder jArrayBuilder = Json.createArrayBuilder();
            for (ListIterator<TweetBean> iterator = l.listIterator(); iterator.hasNext(); ) {
                TweetBean element = iterator.next();
                jArrayBuilder.add(Json.createObjectBuilder()
                        .add("content", element.getContent())
                        .add("insertDate", element.getInsertDate().toString())

                );

            }
            jObjBuilder.add("myFeed", jArrayBuilder);
            JsonObject jSonFinal = jObjBuilder.build();

            System.out.println("json pe list: " + jSonFinal.toString());

            returnJsonResponse(resp, jSonFinal.toString());


        }catch(Exception e){
            e.printStackTrace();
        }


    }
    public void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }

    }

