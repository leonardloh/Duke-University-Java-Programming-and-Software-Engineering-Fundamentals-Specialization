package Course3.w1;

public class WordPlay {

    public boolean isVowel(char ch)
    {
        String vowel = "aeiou";
        for (int i =0; i < vowel.length(); i++)
        {
            if (vowel.charAt(i) == Character.toLowerCase(ch))
            {
                return true;
            }
        }
        return  false;
    }

    public StringBuilder replaceVowels(String phase, char ch)
    {
        StringBuilder encrypted = new StringBuilder(phase);
        for (int i=0; i< encrypted.length(); i++)
        {
            char currChar = encrypted.charAt(i);
            if (isVowel(currChar))
            {
                encrypted.setCharAt(i, ch);
            }
        }
        return encrypted;
    }

    public String emphasize(String phase, char ch)
    {
        StringBuilder encrypted = new StringBuilder(phase);
        for (int i=0; i< encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.toLowerCase(currChar) == Character.toLowerCase(ch)) {
                if (i % 2 == 0) {
                    encrypted.setCharAt(i, '*');
                } else {
                    encrypted.setCharAt(i, '+');
                }

            }
        }

        return encrypted.toString();
    }

    public void testIsVowel()
    {
        System.out.println(isVowel('a'));
    }

    public void testReplaceVowels()
    {
        System.out.println(replaceVowels("Hello World", '*'));
    }

    public void testEmphasize()
    {
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

    public static void main(String[] args) {
        WordPlay wp = new WordPlay();
//        wp.testIsVowel();

//        wp.testReplaceVowels();

        wp.testEmphasize();
    }
}
