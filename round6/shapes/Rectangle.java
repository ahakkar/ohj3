public class Rectangle implements IShapeMetrics {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public String toString() {
        return String.format(
            "Rectangle with height %.2f and width %.2f",
            length, width
            );
    }

    public String name() {
        return "rectangle";
    }

    public double area() {
        return length * width;
    }

    public double circumference() {
        return 2 * (length + width);
    }

    /*
    public static void main(String[] args) {
        Rectangle r = new Rectangle(5, 10);
        System.out.println("Area: " + r.area());
        System.out.println("Circumference: " + r.circumference());
    }
    */
}
