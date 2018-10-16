public class Prostokat extends Kwadrat
{
    protected double b;
    public Prostokat(double a, double b)
    {
        super(a);
        this.b = b;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double area()
    {
        return a*b;
    }

    public boolean isBigger(Prostokat prostokat)
    {
        return prostokat.area() > this.area();
    }
}
