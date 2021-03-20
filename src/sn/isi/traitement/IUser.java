package sn.isi.traitement;

import sn.isi.entities.User;

import java.util.List;

public interface IUser {
    public boolean verifieConnect(String login, String mdp) throws Exception;
    public List<User> users() throws Exception;
}
