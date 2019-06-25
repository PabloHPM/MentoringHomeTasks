package homeWork1;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class QuadraticEquation {

    public QuadraticEquation(double a, double b, double c) {
        if (a != 0)
            calculateQuadraticEquation(a, b, c);
        else
            log.fatal("Dividing by zero!!!!!!");
    }

    private void calculateQuadraticEquation(double a, double b, double c) {
        double x1;
        double x2;
        double d = Math.pow(b, 2) - 4 * a * c;
        if ((d >= 0)) {
            x1 = (-b + Math.sqrt(d))/(2*a);
            x2 = (-b - Math.sqrt(d))/(2*a);
            log.info("Quadratic equation has two root");
            log.info(String.format("Quadratic equation: %.1fx^2 + %.1fx + %.1f = 0 %n\t\tRoots: \tx1 = %.2f\tx2 = %.2f", a, b, c, x1, x2));
        } else {
            log.error("Quadratic equation has no root");
        }
    }
}
