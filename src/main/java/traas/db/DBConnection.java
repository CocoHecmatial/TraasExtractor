package traas.db;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CocoHecmatial
 * @since 2019/1/31 17:23
 **/
public class DBConnection {
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://192.168.154.79:5442/smart_junction?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&rewriteBatchedStatements=true";
            String user = "postgres";
            String pswd = "superman";
            connection = DriverManager.getConnection(url,user,pswd);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public static Map<String,Long> getLinkRSpidMap(Connection connection){
        Map<String,Long> resultMap = new HashMap<>();
        String sql = "select linkid,rs_pid " +
                "     from junction_link_m ";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                resultMap.put(resultSet.getString("linkid"),resultSet.getLong("rs_pid"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultMap;
    }
}
