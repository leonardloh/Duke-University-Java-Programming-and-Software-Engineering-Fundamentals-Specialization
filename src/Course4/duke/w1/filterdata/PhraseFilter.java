package Course4.duke.w1.filterdata;

public class PhraseFilter implements Filter{

    private String where;
    private String phrase;

    public PhraseFilter(String where, String phrase)
    {
        this.where = where;
        this.phrase = phrase;
    }

    @Override
    public String getName() {
        return "Phrase Filter";
    }

    @Override
    public boolean satisfies(QuakeEntry qe) {

        String title = qe.getInfo();
        String[] splited = title.split("\\b+");
        if (where.toLowerCase().equals("start"))
        {
            if (splited[0].contains(phrase))
            {
                return true;
            }
        }
        if (where.toLowerCase().equals("end"))
        {
            if (splited[splited.length - 1].contains(phrase))
            {
                return true;
            }
        }
        if (where.toLowerCase().equals("any"))
        {

            if (title.indexOf(phrase) != -1)
            {
                return true;
            }

        }
        return false;
    }
}
