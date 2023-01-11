public class Mean {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {        
        double mean = 0.0;

        for (var num : args) {
            mean += Double.parseDouble(num);
        }
        mean = mean / args.length;
        System.out.print("Mean: " + mean + "\n");
    }
}