package Course4.duke.w1.filterdata;

public class DistanceFilter implements Filter{
    private Location location;
    private int maximumDistance;

    public DistanceFilter(Location location, int maximumDistance) {
        this.location = location;
        this.maximumDistance = maximumDistance;
    }

    @Override
    public String getName() {
        return "Distance Filter";
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
//        System.out.println(location.distanceTo(qe.getLocation()));
        if(location.distanceTo(qe.getLocation()) < maximumDistance)
        {
            return true;
        }
        return false;
    }
}
