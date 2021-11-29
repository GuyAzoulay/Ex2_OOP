package api;

public class GeoLoc implements GeoLocation {
    double x,y,z;
    GeoLoc(int x,int y,int z){
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

    public double distance(GeoLocation g) {
        double nx= Math.pow(this.x-g.x(),2);
        double ny= Math.pow(this.y-g.y(),2);
        double nz= Math.pow(this.z-g.z(),2);
        return Math.sqrt(nx+ny+nz);
    }
}
