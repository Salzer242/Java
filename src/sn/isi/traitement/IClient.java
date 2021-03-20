package sn.isi.traitement;

import sn.isi.entities.Client;

import java.util.List;

public interface IClient {
    public int add(Client C) throws Exception;
    public List<Client> List() throws Exception;
    public int update(Client client, int id) throws Exception;
    public Client nouveauClient() throws Exception;
    public int delete(Client client) throws Exception;
    public void afficherClients() throws Exception;
    public void afficherMail(String email) throws Exception;
    public Boolean isVlidMail(String email) throws Exception;
    public boolean verifiTel(String tel);
}
