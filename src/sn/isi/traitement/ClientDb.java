package sn.isi.traitement;

import sn.isi.entities.Client;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientDb implements IClient{
    private DB db = new DB();
    private ResultSet rs;
    private int ok;

    //LISTER LES CLIENTS
    @Override
    public List<sn.isi.entities.Client> List() throws Exception{
        List<sn.isi.entities.Client> clients = new ArrayList<sn.isi.entities.Client>();
        String sql = "SELECT * FROM client";

        db.initPrepar(sql);
        rs = db.executeSelect();
        while (rs.next()){
            sn.isi.entities.Client client = new sn.isi.entities.Client();
            client.setId(rs.getInt(1));
            client.setNom(rs.getNString(2));
            client.setPrenom(rs.getNString(3));
            client.setEmail(rs.getNString(4));
            client.setTel(rs.getNString(5));
            clients.add(client);
        }
        return clients;
    }
    //AJOUTER DES CLIENTS
    @Override
    public int add(sn.isi.entities.Client client) throws Exception{
        String sql =" INSERT INTO client VALUES(NULL,?, ?, ?, ?)";
            db.initPrepar(sql);
            db.getPstm().setString(1, client.getNom());
            db.getPstm().setString(2, client.getPrenom());
            db.getPstm().setString(3, client.getEmail());
            db.getPstm().setString(4, client.getTel());

            ok = db.executeMaj();
            db.closeConnection();
        return ok;
    }

    //MODIFIER UN CLIENT
    @Override
    public int update(sn.isi.entities.Client client, int id) throws Exception {
        String sql = "UPDATE client SET nom =? , prenom = ? , email = ? , tel = ? WHERE id = ?";
            db.initPrepar(sql);
            db.getPstm().setString(1, client.getNom());
            db.getPstm().setString(2, client.getPrenom());
            db.getPstm().setString(3, client.getEmail());
            db.getPstm().setString(4, client.getTel());
            db.getPstm().setInt(5, id);
            ok = db.executeMaj();
            db.closeConnection();
        return ok;
    }

    //SUPPRIMER UN CLIENT
    @Override
    public int delete(sn.isi.entities.Client client) throws Exception {
        String sql = "DELETE FROM client WHERE id = ?";
        db.initPrepar(sql);
        db.getPstm().setInt(1,client.getId());
        ok = db.executeMaj();
        return ok;
    }

    // VERIFIER SI LE TEL EST VALIDE
    public boolean verifiTel(String tel){
        String str = tel.substring(0,2);
        if((tel.length()==9) && (str.equals("77") || str.equals("78") || str.equals("78")  || str.equals("70")))
        {
            return true;
        }
        return false;
    }

    //VERIFIER SI LE MAIL ES VALIDE
    @Override
    public Boolean isVlidMail(String email) throws Exception {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    //AJOUTER UN CLIENT
    @Override
    public sn.isi.entities.Client nouveauClient() throws Exception {
        Scanner scanner = new Scanner(System.in);
        sn.isi.entities.Client client = new sn.isi.entities.Client();
        System.out.println("Nom : ");
        client.setNom(scanner.nextLine());
        System.out.println("Prenom : ");
        client.setPrenom(scanner.nextLine());
        do {
            System.out.println("Mail : ");
            client.setEmail(scanner.nextLine());
        }while (isVlidMail(client.getEmail())== false);
        boolean valide ;
        do {
            System.out.println("Tel : ");
            client.setTel(scanner.nextLine());
            valide = verifiTel(client.getTel());
        }while (valide == false);

        return client;
    }

    //AFFICHAGE DES CLIENTS
    @Override
    public void afficherClients() throws Exception {
        List<sn.isi.entities.Client> clients = new ArrayList<>();
        clients = List();
        System.out.println("---------------------------------------------------------------------------------------------");
        for (sn.isi.entities.Client cl : clients){
            System.out.println("|ID :  " +cl.getId()+"     |NOM :    "+cl.getNom()+"   |PRENOM : "+cl.getPrenom()+"  |MAIL :  "+cl.getEmail()+"   [TEL :"+cl.getTel());
            System.out.println("-----------------------------------------------------------------------------------------");
        }
    }

    //AFFICHER LES CLIENTS PAR MAIL
    @Override
    public void afficherMail(String email) throws Exception {
        List<sn.isi.entities.Client> clients = new ArrayList<>();
        clients = List();
        System.out.println("null");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (sn.isi.entities.Client cl : clients){
            if (cl.getEmail().equalsIgnoreCase(email)){
                System.out.println("|ID :  " +cl.getId()+"     |NOM :    "+cl.getNom()+"   |PRENOM : "+cl.getPrenom()+"  |MAIL :  "+cl.getEmail()+"   |TEL :"+cl.getTel());
                System.out.println("-------------------------------------------------------------------------------------");
            }
        }

    }

    //MENU
    public int menu()
    {
        int choix = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------MENU-------");
        System.out.println("1_________CREER UN CLIENT");
        System.out.println("2_________EDITER UN CLIENT");
        System.out.println("3_________VISUALISER LES CLIENTS");
        System.out.println("4_________RECHERCHE PAR MAIL");
        System.out.println("5_________QUITTER");
        choix = Integer.parseInt(scanner.nextLine());
        return choix;
    }
}
