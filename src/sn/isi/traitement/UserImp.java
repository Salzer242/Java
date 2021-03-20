package sn.isi.traitement;

import sn.isi.entities.Client;
import sn.isi.entities.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserImp implements IUser{
    private DB db = new DB();
    private ResultSet rs;

    @Override
    public boolean verifieConnect(String login, String mdp) throws Exception {
        List<User> user = new ArrayList<>();
        user = users() ;
        System.out.println("---------------------------------------------------------------------------------------------");
        for (User u: user)
        {
            if (u.getLogin().equals(login) && u.getPassword().equals(mdp))
            {
                return true;
            }
        }
        return false;

    }

    @Override
    public List<User> users() throws Exception {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM user";

        db.initPrepar(sql);
        rs = db.executeSelect();
        while (rs.next()){
            User user = new User();
            user.setId(rs.getInt(1));
            user.setLogin(rs.getNString(2));
            user.setPassword(rs.getNString(3));
            users.add(user);
        }
        return users;
    }
}
