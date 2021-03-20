package Presentation;

import sn.isi.traitement.ClientDb;
import sn.isi.traitement.UserImp;

import java.util.Scanner;

public class Main {
    public static void main(String[] arg) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean valid;
        do {
            System.out.println("LOGIN : ");
            String login = scanner.nextLine();
            System.out.println("MOT DE PASSE :");
            String mdp = scanner.nextLine();
            UserImp userImp = new UserImp();
            valid = userImp.verifieConnect(login,mdp);
        }while (valid==false);
        if (valid==true){
            System.out.println(" -------BIENVENUE---------");
            int choix;
            String rep;
            String exit = null;
            ClientDb clientImp = new ClientDb();
            do {
                choix =  clientImp.menu();
                switch (choix){
                    case 1:
                        do {
                            System.out.println("-------CREATION-------");
                            sn.isi.entities.Client client = new sn.isi.entities.Client();
                            client =  clientImp.nouveauClient();
                            clientImp.add(client);
                            System.out.println("VOULEZ-VOUS SAISIR UN NOUVEAU CLIENT ? oui/non");
                            rep = scanner.nextLine();
                        }while (rep.equalsIgnoreCase("oui"));
                        break;
                    case 2:
                        do {
                            System.out.println("-------EDITION--------");
                            System.out.println("Entrer l'Id du client a modifier");
                            sn.isi.entities.Client client1 = new sn.isi.entities.Client();
                            int id = Integer.parseInt(scanner.nextLine());;
                            client1 = clientImp.nouveauClient();
                            clientImp.update(client1,id);
                            System.out.println("VOULEZ-VOUS EDITER UN NOUVEAU CLIENT  ? oui/non");
                            rep = scanner.nextLine();
                        }while (rep.equalsIgnoreCase("oui"));
                    break;
                    case 3:
                            System.out.println("-----VISUALISATION----");
                            clientImp.afficherClients();
                        break;
                    case 4:
                        do {
                            System.out.println("------RECHERCHE PAR EMAIL-----");
                            System.out.println("Entrer le mail à rechercher");
                            String mail = scanner.nextLine();;
                            clientImp.afficherMail(mail);
                            System.out.println("VOULEZ-VOUS FAIRE UNE NOUVELLE RECHERCHE ? oui/non");
                            rep = scanner.nextLine();
                        }while (rep.equalsIgnoreCase("oui"));
                        break;
                    default:
                            System.out.println("CHOIX INVALIDE");
                            System.out.println("VOULEZ-VOUS QUITTER ?");
                            exit=scanner.nextLine();

                    }
                }while (exit.equalsIgnoreCase("non"));
            }
        }
}
