package dao;

import db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {

public static <T> T execute(String sql,Object...objects) throws SQLException {
    int i=1;
    PreparedStatement pst= DBConnection.getInstance().getConnection().prepareStatement(sql);
    for (Object obj:objects) {
        pst.setObject(i,objects[i-1]);
    }

    if(sql.startsWith("SELECT") || sql.startsWith("sql")){
        ResultSet r=pst.executeQuery();
        return (T) r;
    }
    else {
        int j=pst.executeUpdate();
        Integer x=Integer.valueOf(j);
        return  (T) (Integer) j; //generics doesn't allow to pass primitive types. so you have to cast it to Integer(boxing)
    }
}


}
