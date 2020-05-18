package Course4.duke.w1.filterdata;
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinMagFilter implements Filter
{
    private double magMin; 
    
    public MinMagFilter(double min) { 
        magMin = min;
    }

    @Override
    public String getName() {
        return "Min Mag Filter";
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin; 
    } 

}
