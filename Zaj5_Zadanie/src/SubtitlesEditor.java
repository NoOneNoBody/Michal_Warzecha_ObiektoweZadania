import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubtitlesEditor {

    public static void delay(final String in, final String out, int delay, int fps) throws Exception{

        BufferedReader reader = new BufferedReader(new FileReader(new File(in)));

        StringBuilder builder = new StringBuilder();

        String line;
        int lineNum = 0;
        final String regex = "^\\{[0-9]+\\}\\{[0-9]+\\}.*$";
        final String subtitlesRegex = "^\\{[0-9]+\\}\\{[0-9]+\\}";
        final Pattern pattern = Pattern.compile("[0-9]+");

        while((line = reader.readLine()) != null){
            ++lineNum;
            if(!line.matches(regex)) throw new Exception("Incorrect syntax at line: " + lineNum + "\n\"" + line + "\"");

            Matcher matcher = pattern.matcher(line);
            int beginFrame,endFrame;

            if(!matcher.find()) throw new Exception("No begin frame number at line: " + lineNum + "\n\"" + line + "\"");
            beginFrame = Integer.parseInt(matcher.group());
            if(!matcher.find()) throw new Exception("No end frame number at line: " + lineNum + "\n\"" + line + "\"");
            endFrame = Integer.parseInt(matcher.group());

            if(endFrame <= beginFrame) throw new Exception("Begin frame number greater than end frame at line: "
                                                            + lineNum + "\n\"" + line + "\"");

            String subtitles = line.replaceAll(subtitlesRegex,"");

            int frameDelay = delay*fps/1000;

            beginFrame += frameDelay;
            endFrame += frameDelay;

            builder.append("{"+beginFrame+"}{"+endFrame+"}"+subtitles+"\n");
        }
        reader.close();


        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(out)));
        writer.append(builder);
        writer.close();


    }

}
