package org.example.ihm;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuIHM {
    private static final Scanner sc = new Scanner(System.in);
    private final static File file = new File("journal.txt");

    public static void MenuStart(){
        while(true){
            System.out.println("--- Menu ---");
            System.out.println("1. Ajouter une activité");
            System.out.println("2. Afficher les activités");
            System.out.println("3. Sauvegarder en binaire");
            System.out.println("4. Lire le fichier binaire");
            System.out.println("5. Quitter");

            int choice = sc.nextInt();
            if (choice < 1 || choice > 5){
                System.out.println("Au revoir !!");
                break;
            }

            switch (choice) {
                case 1:
                    verifyFile();
                    addActivities();
                    break;
                case 2:
                    displayActivities();
                    break;
                case 3:
                    convertToBinaryFile();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option! Try again");

            }
        }
    }

    public static void verifyFile(){
        try{
            File file = new File("journal.txt");
            if (!file.exists()){
                file.createNewFile();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void addActivities(){
        try(BufferedWriter br = new BufferedWriter(new FileWriter(file, true))) {
                System.out.println("Entrez une description de l'activité :");
                String addAcitivity = sc.next();
                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String entry = timestamp + " - " + addAcitivity;
                br.write(entry);
                br.newLine();
                System.out.println("Activité ajouté avec succès: "+addAcitivity);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void displayActivities(){
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void convertToBinaryFile(){
        File binaryFile = new File("journal_backup.dat");

        try(BufferedInputStream brs = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(binaryFile))){
            byte[] buffer = new byte[1024];add
            int nb;
            while ((nb = brs.read(buffer)) > 0 ){
                bos.write(buffer, 0, nb);
                bos.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
