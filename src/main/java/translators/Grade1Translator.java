package translators;

import org.springframework.stereotype.Repository;
import tables.Grade1Map;
import utils.grade2.WordIterator;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
@Repository(value = "grade1Translator")
public class Grade1Translator extends Translator{

    @Override
    public String translate(String input) {

        StringBuffer sb = new StringBuffer();

        Iterator<String> wordIterator = new WordIterator(input);
        while (wordIterator.hasNext()) {
            String word = wordIterator.next();
            sb.append(translateWord(word));
        }

        return sb.toString();

    }

    private String translateWord(String word) {
        HashMap<Character, String> table = Grade1Map.table;
        StringBuffer sb = new StringBuffer();

        word = handleSpecialCases(word);

        char[] chars = word.toCharArray();

        for (int i=0; i<chars.length; i++) {
            char c = chars[i];
            if (table.containsKey(c)) {
                if(isNumber(c) && (i == 0 || !isNumber(chars[i-1]))){
                    sb.append("⠼");
                }
                sb.append(table.get(c));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private String handleSpecialCases(String word) {
        // handle "لا"
        while(word.contains("لا")) {
            int index = word.indexOf("لا");
            if(index == 1 && word.charAt(0) == 'ا' ) {
                word = word.replace("لا", "⠇⠁");
            }
            else {
                word = word.replace("لا", "⠧");
            }
        }

        return word;
    }

    private boolean isNumber(char c){
        if((c>='0'&&c<='9') || (c>='٠'&&c<='٩')) {
            return true;
        }
        else{
            return false;
        }
    }
}
