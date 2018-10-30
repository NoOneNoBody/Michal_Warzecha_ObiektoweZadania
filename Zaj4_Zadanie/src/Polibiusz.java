public class Polibiusz implements Algorithm {

    @Override
    public String crypt(String word) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(c >= 'A' && c <= 'Z'){
                if(c >= 'J') --c;
                c -= 65;
                int val = 10*(c/5) + (c%5) + 11;
                builder.append(Integer.toString(val));
            }
            if(c >= 'a' && c <= 'z'){
                if(c >= 'j') --c;
                c -= 97;
                int val = 10*(c/5) + (c%5) + 11;
                builder.append(Integer.toString(val));
            }
        }
        return builder.toString();
    }

    @Override
    public String decrypt(String word) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < word.length(); i += 2) {
            int v1 = Character.getNumericValue(word.charAt(i - 1));
            int v2 = Character.getNumericValue(word.charAt(i));
            char c = (char)((v1 - 1)*5 + v2 - 1);
            if(c >= 9) ++c;
            c += 65;
            builder.append(c);
        }
        return builder.toString();
    }
}
