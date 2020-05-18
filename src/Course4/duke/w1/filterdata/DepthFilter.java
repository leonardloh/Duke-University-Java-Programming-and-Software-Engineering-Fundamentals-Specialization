package Course4.duke.w1.filterdata;

public class DepthFilter implements Filter{
    private double min;
    private double max;

    public DepthFilter(double min, double max)
    {
        this.min = min;
        this.max = max;
    }

    @Override
    public String getName() {
        return "Depth Filter";
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        double depth = qe.getDepth();
        if (depth >= min && depth <= max)
        {
            return true;
        }
        return false;
    }
}
