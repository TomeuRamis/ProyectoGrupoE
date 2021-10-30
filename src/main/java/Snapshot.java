import org.jsoup.Jsoup;

import java.io.*;

public class Snapshot {

    /*
    Hace una "snapshot" a la página que indicada "link" y guarda el código html en un fichero indicado por "filename"
    */
    public void makeSnapshot(String link, String fileName) throws IOException {
        String snapshot = Jsoup.connect(link).get().html();

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(snapshot);

        writer.close();
    }

    /*
    Compara el contenido de dos ficheros indicados por "file1" y "file2"
    */
    public boolean compareSnapshot(String file1, String file2) throws IOException {

        BufferedReader readerOld = new BufferedReader(new FileReader(file1));
        BufferedReader readerNew = new BufferedReader(new FileReader(file2));

        String lineO, lineN;
        lineO = readerOld.readLine();
        lineN = readerNew.readLine();

        boolean equals = true;
        int i = 1;

        while (lineO != null && lineN != null) {
            if (!lineO.equals(lineN)) {
                System.out.println("Las dos snapshots son diferentes en la linea " + i + ": " + lineO + " =/= " + lineN);
                equals = false;
            }

            lineO = readerOld.readLine();
            lineN = readerNew.readLine();
            i++;
        }
        if (lineO != null || lineN != null) {
            System.out.println("Las dos snapshots tienen longitudes distintas");
            equals = false;
        }

        return equals;
    }
}
