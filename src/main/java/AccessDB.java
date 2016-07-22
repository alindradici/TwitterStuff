import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by icondor on 16/07/16.
 */
public class AccessDB {


    public static void addTweet(int idUser, String textToTweet) throws ClassNotFoundException, SQLException {
    // 1. load driver
    Class.forName("org.postgresql.Driver");

    // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_alin";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

    // 3. obtain a connection
    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

    // 4. create a query statement
    PreparedStatement pSt = conn.prepareStatement("INSERT INTO tweet (iduser,content,insertdate ) VALUES ('"+idUser+"','"+textToTweet+"',now())");


    // 5. execute a prepared statement
    int rowsInserted = pSt.executeUpdate();

    // 6. close the objects
    pSt.close();
    conn.close();
}



    public static List readTweets() throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_alin";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT id,content,insertdate FROM tweet order by insertdate desc");

        // 6. iterate the result set and print the values

        List<TweetBean> listOfTweets = new ArrayList();
        while (rs.next()) {

            // creez un bean de tipul tweetbean , adica un rand din db
            TweetBean tb = new TweetBean();
            tb.setId(rs.getLong("id"));
            tb.setContent(rs.getString("content").trim());
            tb.setInsertDate(rs.getDate("insertdate"));

            //scriu obiectul(randul) in lista
            listOfTweets.add(tb);

        }

        System.out.println("dimensiunea listei:"+listOfTweets.size());
        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return listOfTweets;
    }



    public static int isUserInDB(String user, String passwd) throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");
        int userid =-1;

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_alin";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT * FROM users where nume='"+user+"' and pass='"+passwd+"'");

        // 6. iterate the result set and print the values

        while (rs.next()) {
            userid=rs.getInt("id");
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return userid;
    }



    public static List readMyFriends(long myID) throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_alin";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT * FROM tweet where iduser in (select id_friend from relations where id_usr="+myID+" ) order by insertdate desc ");

        // 6. iterate the result set and print the values

        List<TweetBean> listOfTweets = new ArrayList();
        while (rs.next()) {

            // creez un bean de tipul tweetbean , adica un rand din db
            TweetBean tb = new TweetBean();
            tb.setId(rs.getLong("id"));
            tb.setContent(rs.getString("content").trim());
            tb.setInsertDate(rs.getDate("insertdate"));

            //scriu obiectul(randul) in lista
            listOfTweets.add(tb);

        }

        System.out.println("dimensiunea listei:"+listOfTweets.size());
        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return listOfTweets;
    }

    public static void addUser (String user,String pass,String emailAdress) throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_alin";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO users (nume,pass,email) VALUES ('"+user+"','"+pass+"','"+emailAdress+"')");


        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }

    public static int isUser(String user) throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");
        int userid =-1;

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5:5432/4_alin";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT * FROM users where nume='"+user+"'");

        // 6. iterate the result set and print the values

        while (rs.next()) {
            userid=rs.getInt("id");
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();

        return userid;
    }

}