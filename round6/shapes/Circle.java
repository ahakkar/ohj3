public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }


    public String toString() {
        return "Circle with radius: " + radius;
    }

    
    public String name() {
        return "circle";
    }


    public double area() {
        return Math.PI * radius * radius;
    }


    public double circumference() {
        return 2 * Math.PI * radius;
    }

    /*
    public static void main(String[] args) {
        Circle c = new Circle(5);
        System.out.println("Area: " + c.area());
        System.out.println("Circumference: " + c.circumference());
    }
    */
}