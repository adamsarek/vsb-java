package pl1.lab09;

/* Priklad ako je definovany typ Tuple, stiahnite dependencies z maven repository na org.javatuples */
public class Tuple<X,Y,Z> {

    public final X x;
    public final Y y;
    public final Z z;
    public Tuple(X x, Y y, Z z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
