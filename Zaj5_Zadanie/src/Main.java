public class Main {

    public static void main(final String[] args){

        try {
            SubtitlesEditor.delay("input.txt", "output.txt", 1000,60);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
