package Course4.duke.w4.WordGramClassStarterProgram;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        // TODO: Complete this method
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        // TODO: Complete this method
        for (String word : myWords)
        {
            ret = ret + " " + word;
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // TODO: Complete this method
        if (!(this.length() == other.length())) {
            return false;
        }

        for (int i = 0; i < myWords.length; i++) {
            if (!myWords[i].equals(other.wordAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) {	
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        // TODO: Complete this method
        String[] shiftedWords = new String[myWords.length];
        for(int i = 1; i<myWords.length; i++)
        {
            shiftedWords[i-1] = myWords[i];
        }
        shiftedWords[myWords.length-1] = word;
        out = new WordGram(shiftedWords, 0, shiftedWords.length);
        return out;
    }

//    public static void main(String[] args) {
//        WordGram wordGram = new WordGram(new String[] { "a", "b", "c", "d"},0, 4);
//        System.out.println(wordGram.shiftAdd("e"));
//    }

}