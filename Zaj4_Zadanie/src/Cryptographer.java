import java.io.*;

public class Cryptographer {

    public static void cryptfile(File source, File dest, Algorithm algorithm) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(source));
        BufferedWriter writer = new BufferedWriter(new FileWriter(dest));
        String originalLine;
        while((originalLine = reader.readLine()) != null){
            String[] words = originalLine.split(" ");
            for(String word : words){
                writer.write(algorithm.crypt(word));
                writer.write(" ");
            }
            writer.write("\n");
        }
        reader.close();
        writer.close();
}

    public static void decryptfile(File source, File dest, Algorithm algorithm) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(source));
        BufferedWriter writer = new BufferedWriter(new FileWriter(dest));
        String originalLine;
        while((originalLine = reader.readLine()) != null){
            String[] words = originalLine.split(" ");
            for(String word : words){
                writer.write(algorithm.decrypt(word));
                writer.write(" ");
            }
            writer.write("\n");
        }
        reader.close();
        writer.close();
    }

}
