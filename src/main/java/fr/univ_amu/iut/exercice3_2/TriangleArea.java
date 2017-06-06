package fr.univ_amu.iut.exercice3_2;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class TriangleArea {

    private IntegerProperty x1 = new SimpleIntegerProperty(0);
    private IntegerProperty y1 = new SimpleIntegerProperty(0);

    private IntegerProperty x2 = new SimpleIntegerProperty(0);
    private IntegerProperty y2 = new SimpleIntegerProperty(0);

    private IntegerProperty x3 = new SimpleIntegerProperty(0);
    private IntegerProperty y3 = new SimpleIntegerProperty(0);

    private NumberBinding area;

    private StringExpression output;

    public TriangleArea() {
        createBinding();
    }

    public static void main(String[] args) {
        TriangleArea triangleArea = new TriangleArea();

        triangleArea.printResult();

        triangleArea.setP1(0, 1);
        triangleArea.setP2(5, 0);
        triangleArea.setP3(4, 3);

        triangleArea.printResult();

        triangleArea.setP1(1, 0);
        triangleArea.setP2(2, 2);
        triangleArea.setP3(0, 1);

        triangleArea.printResult();
    }

    public void setP1(int x1, int y1) {
        this.x1.set(x1);
        this.y1.set(y1);
    }

    public void setP2(int x2, int y2) {
        this.x2.set(x2);
        this.y2.set(y2);
    }

    public void setP3(int x3, int y3) {
        this.x3.set(x3);
        this.y3.set(y3);
    }

    public double getArea() {
        return area.getValue().doubleValue();
    }

    void printResult() {
        // "For P1(0,0), P2(6,0), P3(4,3), the area of triangle ABC is 9.0"

        System.out.println(output.getValue());
    }

    private void createBinding() {
        //|(x1*y2 - x1*y3 + x2*y3 - x2*y1 + x3*y1 - x3*y2)|/2
        area = new DoubleBinding() {
            {
                super.bind(x1, y1, x2, y2, x3, y3);
            }
            @Override
            protected double computeValue() {
                return (x1.get() * y2.get() - x1.get() * y3.get() + x2.get() * y3.get() - x2.get() * y1.get() + x3.get() * y1.get()) / 2.0;
            }
        };

        output = Bindings.format("For P1(%d,%d), P2(%d,%d), P3(%d,%d), the area of triangle ABC is %3.1f", x1, y1, x2, y2, x3, y3, area);
    }
}
