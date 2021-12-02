package com.example.mailtest;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MailTestApplication {

    private static final String path = "src/main/resources/";


    public static void main(String[] args) throws IOException {

        //Definimos la web a la que queremos hacer una snapshot y donde se guardará el fichero creado
        String web1 = "https://stackoverflow.com/";
        String file1 = path + "web_snapshot.txt";


        Snapshot snapshot = new Snapshot();
        Email email = new Email();

        //Hacemos la snapshot
        try {
            snapshot.makeSnapshot(web1, file1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Comparamos la snapshot hecha con un fichero ligeramente diferente creado anteriormente
        try {
            if (snapshot.compareSnapshot(file1, path + "web_snapshot2.txt")) {
                System.out.println("Las snapshots son iguales");
            }
            else {
                email.sendEmail();
                System.out.println("Las snapshots son distintas, email enviado");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
