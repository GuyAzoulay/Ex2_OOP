package api;

public class GeoLoc implements GeoLocation {
    double x,y,z;

    // a geoLocation constructor
    GeoLoc(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double x() {
        return this.x;
    }

    public double y() {
        return this.y;
    }

    public double z() {
        return this.z;
    }

    // computing geographic distance location
    public double distance(GeoLocation g) {
        double nx= Math.pow(this.x-g.x(),2);
        double ny= Math.pow(this.y-g.y(),2);
        double nz= Math.pow(this.z-g.z(),2);
        return Math.sqrt(nx+ny+nz);
    }

    @Override
    public String toString() {
        return
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
