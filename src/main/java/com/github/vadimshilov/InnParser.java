package com.github.vadimshilov;

import java.util.HashMap;
import java.util.Map;

public final class InnParser {

  private static final String[] NUMBERS = {
      " _     _  _     _  _  _  _  _ ",
      "| |  | _| _||_||_ |_   ||_||_|",
      "|_|  ||_  _|  | _||_|  ||_| _|"
  };

  private static final int ROW_COUNT = 3;
  private static final int COL_COUNT = 3;

  private static final Map<Long, Character> NUM_MAP;

  static {
    NUM_MAP = new HashMap<>();
    char number = '0';
    for (int strIndex = 0; strIndex < NUMBERS[0].length(); strIndex+=3, number++) {
      NUM_MAP.put(calcHash(NUMBERS, strIndex), number);
    }
  }

  public static String parseInn(String inn) {
    String[] lines = inn.split("\n");
    if (lines.length != ROW_COUNT) {
      throw new RuntimeException("Bad row count");
    }
    int firstLineLength = lines[0].length();
    if (firstLineLength % COL_COUNT != 0) {
      throw new RuntimeException("Bad line length");
    }
    for (int i = 1; i < ROW_COUNT; i++) {
      if (lines[i].length() != firstLineLength) {
        throw new RuntimeException("All lines length should be equal");
      }
    }
    if (firstLineLength == 0) {
      return "";
    }
    StringBuilder resultBuilder = new StringBuilder();
    for (int startIndex = 0; startIndex < firstLineLength; startIndex += 3) {
      long hash = calcHash(lines, startIndex);
      Character num = NUM_MAP.get(hash);
      if (num == null) {
        throw new RuntimeException("Unable to recognize character");
      }
      if (resultBuilder.length() != 0 || num != '0') {
        resultBuilder.append(num);
      }
    }
    if (resultBuilder.length() == 0) {
      resultBuilder.append('0');
    }
    return resultBuilder.toString();
  }

  private static long calcHash(String[] numbers, int startIndex) {
    long result = 0;
    for (int i = 0; i < ROW_COUNT; i++) {
      for (int j = 0; j < COL_COUNT; j++) {
        int charValue;
        switch (numbers[i].charAt(j + startIndex)) {
          case ' ':
            charValue = 0;
            break;
          case '_':
            charValue = 1;
            break;
          case '|':
            charValue = 2;
            break;
          default:
            throw new RuntimeException("Unknown character " + numbers[i].charAt(j + startIndex));
        }
        result = result * 3 + charValue;
      }
    }
    return result;
  }

}
