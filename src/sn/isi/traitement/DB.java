package sn.isi.traitement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {
    //POUR LA CONNEXION
    private Connection cnx;
    //Requette SELECT
    private ResultSet rs;
    //Requettes preparees
    private PreparedStatement pstm;
    //Reqettes MAJ (INSERT,UPDATE)
    private int ok;

    private void getConnection() throws Exception{
        String url="jdbc:mysql://localhost:3306/marketing";
        String user = "root";
        String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection(url,user,password);
    }

    public void initPrepar(String sql) throws Exception
    {
            getConnection();
            pstm = cnx.prepareStatement(sql);
    }
    public ResultSet executeSelect() throws Exception
    {
        rs = null;
            rs = pstm.executeQuery();
        return rs;
    }

    public int executeMaj() throws Exception{
            ok = pstm.executeUpdate();
        return ok;
    }

    public void closeConnection() throws Exception
    {
            if(cnx != null)
                cnx.close();
    }

    public PreparedStatement getPstm() {
        return pstm;
    }

    public void setPstm(PreparedStatement pstm) {
        this.pstm = pstm;
    }
}
