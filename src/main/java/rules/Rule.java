package rules;

/**
 * Created by hosainfathelbab on 4/4/15.
 */
public interface Rule {

    public boolean isValid(String word, String abbreviation, int index, String wordTranslationSoFar);

}
