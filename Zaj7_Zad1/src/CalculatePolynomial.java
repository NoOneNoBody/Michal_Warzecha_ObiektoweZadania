public class CalculatePolynomial {

    public static float calculate(final float x, final float[] parameters){
        float result = 0;
        for(int i=0;i<parameters.length;++i){
            result += parameters[i] * Math.pow(x,i);
        }
        return result;
    }
}
