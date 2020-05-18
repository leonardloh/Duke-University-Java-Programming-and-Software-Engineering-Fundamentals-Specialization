package Course4.duke.w1.filterdata;

public class MagnitudeFilter implements Filter{
    private double min;
    private double max;

    public MagnitudeFilter(double min, double max)
    {
        this.min = min;
        this.max = max;
    }

    @Override
    public String getName() {
        return "Magnitude Filter";
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        double magnitude = qe.getMagnitude();
        if (magnitude >= min && magnitude <= max)
        {
            return true;
        }
        return false;
    }
}
