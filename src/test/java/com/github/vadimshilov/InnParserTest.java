package com.github.vadimshilov;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InnParserTest {

  @Test
  public void Test1() {
    String data =
      "    _  _     _  _  _  _  _ \n" +
      "  | _| _||_||_ |_   ||_||_|\n" +
      "  ||_  _|  | _||_|  ||_| _|\n";
    assertEquals("123456789", InnParser.parseInn(data));
  }

  @Test
  public void Test2() {
    String data =
        " _  _  _  _  _  _  _  _  _ \n" +
        "| | _| _|| ||_ |_   ||_||_|\n" +
        "|_||_  _||_| _||_|  ||_| _|\n";
    assertEquals("23056789", InnParser.parseInn(data));
  }

  @Test
  public void Test3() {
    String data =
        " _  _  _  _  _  _  _  _  _ \n" +
        "|_| _| _||_||_ |_ |_||_||_|\n" +
        "|_||_  _||_| _||_| _||_| _|\n";
    assertEquals("823856989", InnParser.parseInn(data));
  }

  @Test
  public void Test4() {
    String data =
        " _  _ \n" +
        "| || |\n" +
        "|_||_|\n";
    assertEquals("0", InnParser.parseInn(data));
  }

}
