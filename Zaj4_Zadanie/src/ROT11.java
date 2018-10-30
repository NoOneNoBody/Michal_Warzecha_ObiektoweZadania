public class ROT11 implements Algorithm{

    private static int posMod(int a, int b){
        return ((a%b + b)%b);
    }

    @Override
    public String crypt(String word) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(c >= 'A' && c <= 'Z'){
                c =(char)(((c-65+11)%26) + 65);
            }
            if(c >= 'a' && c <= 'z'){
                c =(char)(((c-97+11)%26) + 97);
            }
            builder.append(c);
        }
        return builder.toString();
    }

    @Override
    public String decrypt(String word) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(c >= 'A' && c <= 'Z'){
                c =(char)(posMod(c-65-11,26) + 65);
            }
            if(c >= 'a' && c <= 'z'){
                c =(char)(posMod(c-97-11,26) + 97);
            }
            builder.append(c);
        }
        return builder.toString();
    }
}
