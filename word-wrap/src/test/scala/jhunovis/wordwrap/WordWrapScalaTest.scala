package jhunovis.wordwrap

import org.scalatest.{FlatSpec, Matchers}

/**
  * A unit-test for [[WordWrapScala]].
  *
  * @author <a href="mailto:jhunovis@gmail.com">Jan Hackel</a>
  * @version $Revision$ $Date$ $Author$
  */
class WordWrapScalaTest extends FlatSpec with Matchers {

  "Empty texts" should "be ignored" in {
    new WordWrapScala(10).wrap("") shouldBe empty
  }

  "Each split line" should "be terminated by a newline character" in {
    val wrappedLines: List[String] = new WordWrapScala(2).wrap("A sample text.")

    all(wrappedLines) should endWith ("\n")
  }

  "Space at the beginning of a line" should "be trimmed" in {
    new WordWrapScala(8).wrap(" word ") should equal (List("word\n"))
  }

  "Spaces at the end of split line" should "be replaced by the newline character" in {
    new WordWrapScala(6).wrap("Space: ") should equal (List("Space:\n"))
  }

  "Subsequent spaces inside a line" should "be preserved" in {
    new WordWrapScala(6).wrap("4    4    space") should equal (List("4    4\n", "space\n"))
  }

  "Break at space" should "be preferred over full columns" in {
    new WordWrapScala(6).wrap("Three words here.") should
      contain inOrderOnly("Three\n", "words\n", "here.\n")
  }

  "Words wider than the column" should "be split to fit the column" in {
    new WordWrapScala(9).wrap("An extraordinarily long word breaks.") should
      contain inOrderOnly("An\n", "extraordi\n", "narily\n", "long word\n", "breaks.\n")

  }
}
