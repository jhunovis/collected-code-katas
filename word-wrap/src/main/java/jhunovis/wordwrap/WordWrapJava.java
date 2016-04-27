package jhunovis.wordwrap;

import com.google.common.base.Splitter;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implement the word-wrap kata in Java.
 * <p/>
 * The "Word-wrap" kata.
 *
 * You write a class called "Wrapper", that has a single function named "wrap"
 * that takes two arguments, a string, and a column number. The function returns
 * the string, but with line breaks inserted at just the right places to make
 * sure that no line is longer than the column number. You try to break lines at word boundaries.
 * Like a word processor, break the line by replacing the last space in a line with a newline.
 *
 * (Quoted from <a href="http://codingdojo.org/cgi-bin/index.pl?KataWordWrap">http://codingdojo.org/cgi-bin/index.pl?KataWordWrap</a>)
 *
 * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
 * @version $Revision$ $Date$ $Author$
 */
public final class WordWrapJava {

    private final int column;

    public WordWrapJava(int column) {
        this.column = column;
    }

    public List<String> wrap(String textToSplit) {
        List<String> lines = new ArrayList<>();
        String[] splitAtSpace = textToSplit.split(" ");
        String currentLine = "";
        for (String word : splitAtSpace) {
            String currentLinePlusNextWord = join(currentLine, word);
            if (currentLinePlusNextWord.length() <= column) {
                currentLine = currentLinePlusNextWord;
            } else {
                if (StringUtils.isNotBlank(currentLine)) {
                    lines.add(currentLine + "\n");
                }
                lines.addAll(appendNewLine(intraWordSplit(word, column)));
                currentLine = fittingTailOf(word, column);
            }
        }
        if (StringUtils.isNotBlank(currentLine)) {
            lines.add(currentLine + "\n");
        }
        return lines;
    }

    private String join(String currentLine, String word) {
        return StringUtils.isBlank(currentLine) ? word : currentLine + " " + word;
    }

    private Collection<? extends String> appendNewLine(Collection<? extends String> lines) {
        ArrayList<String> linesWithLineBreak = new ArrayList<>(lines.size());
        for (String line : lines) {
            linesWithLineBreak.add(line + "\n");
        }
        return linesWithLineBreak;
    }

    private Collection<? extends String> intraWordSplit(String word, int columnWidth) {
        List<String> wordSplitAtColumns = Splitter.fixedLength(columnWidth).splitToList(word);
        if (wordSplitAtColumns.get(wordSplitAtColumns.size() - 1).length() < columnWidth) {
            return wordSplitAtColumns.subList(0, wordSplitAtColumns.size() - 1);
        } else {
            return wordSplitAtColumns;
        }
    }

    private String fittingTailOf(String word, int columnWidth) {
        int tailBegin = word.length() / columnWidth * columnWidth;
        return word.substring(tailBegin);
    }
}
