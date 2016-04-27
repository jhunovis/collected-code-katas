package jhunovis.wordwrap

/**
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
class WordWrapScala(private val column : Int) {

  private def firstLineOf(text: String) = {
    if (text.length <= column) {
      text
    } else {
      val currentLine = text.substring(0, column + 1)
      val lastSpacePosition = currentLine.lastIndexOf(" ")
      currentLine.substring(0, if (lastSpacePosition > 0) lastSpacePosition else column).trim
    }
  }

  /**
    * Unlike [[WordWrapJava]] which splits the text at the spaces before reconstructing the lines, this implementation
    * takes chunks of the column width from the beginning of the text an builds the lines from them. This is based
    * on the observation that the split-spaces-first approach will put most of the spaces at which it did split the text,
    * right back in to fill a text column.
    *
    * This implementation starts with the fullest possible column and goes back to rightmost space in order to avoid
    * intra-word breaks. It also remove all leading and trailing spaces for each split lines while it ignores
    * multiple subsequent spaces inside a line. I do not really like this "feature" which is really just a workaround
    * to the issue that [[wrap()]] cannot tell whether [[firstLineOf()]] actually replaced a trailing space with
    * a newline or not.
    *
    * @param text the text to wrap
    * @return the wrapped text
    */
  def wrap(text: String): List[String] = {
    val trimmedText: String = text.trim
    if (trimmedText.isEmpty) {
      List.empty
    } else {
      val nextLine = firstLineOf(trimmedText)
      (nextLine + "\n") :: wrap(trimmedText.substring(nextLine.length))
    }
  }

}