package Course4.duke.w1.filterdata;

import java.util.ArrayList;

public class MatchAllFilters implements Filter{
    private ArrayList<Filter> filters;

    public MatchAllFilters(){
        this.filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f){
        this.filters.add(f);
    }

    @Override
    public String getName() {
        String names = "";
        for (Filter f : filters)
        {
//            System.out.println(f.getName());
            names = f.getName() + ", " + names;
        }

        return names;
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {
        int count =0;
        for (Filter f : this.filters)
        {
            if (f.satisfies(qe))
            {
                count++;
            }
        }

        if (count == this.filters.size())
        {
            return true;
        }
        return false;
    }
}
