package Course4.duke.w1.filterdata;
/**
 * Write a description of interface Filter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface Filter
{
    public String getName();
    public  boolean satisfies(QuakeEntry qe); 
}
