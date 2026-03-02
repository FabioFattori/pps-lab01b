package it.unibo.pps.e3;

/*
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString well implemented.
 */


public record Pair<X, Y>(X x, Y y) {


    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pair other = (Pair) obj;
        if (x == null) {
            if (other.x != null)
                return false;
        } else if (!x.equals(other.x))
            return false;
        if (y == null) {
            return other.y == null;
        } else return y.equals(other.y);
    }

    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}
