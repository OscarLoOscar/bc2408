package com.example;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.startsWith;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class demoHamcrestTest {
  @Test
  void test1() {
    assertThat(1, is(equalTo(1)));
    assertThat(App.add(1, 1), is(equalTo(2)));
    assertThat(App.subtract(5, 4), is(not(equalTo(2))));
  }

  @Test
  void test2() {
    assertThat(null, is(nullValue()));
    assertThat(null, is(not(equalTo(1))));
    String str = "Hello";
    assertThat(str, is(equalTo("Hello")));
    assertThat(str, is(not(equalTo("Hello World"))));
    assertThat(str, containsString("el"));
    assertThat(str, startsWith("He"));
    assertThat(str, endsWith("lo"));
    assertThat(str, anyOf(startsWith("He"), endsWith("lo")));
    assertThat(str, allOf(startsWith("He"), endsWith("lo")));
  }

  @Test
  void test3() {
    String s = "abc";
    String s1 = "abc";
    assertThat(s, is(sameInstance(s1))); // Literal pool
  }

  @Test
  void test4() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    assertThat(list, hasSize(5));
    assertThat(list, hasItem(3));
    assertThat(list, everyItem(greaterThan(0)));
    assertThat(list, contains(1, 2, 3, 4, 5));
    assertThat(list, containsInAnyOrder(5, 4, 3, 2, 1));
    assertThat(list, not(contains(6)));
  }

  @Test
  void test5() {
    Person person = new Person("John", 20);
    assertThat(person, hasProperty("name", equalTo("John")));
    assertThat(person, hasProperty("age", greaterThan(18)));
  }

  @Test
  void testArray() {
    String[] arr = { "a", "b", "c" };
    assertThat(arr, arrayContaining("a", "b", "c"));
    assertThat(arr, arrayContainingInAnyOrder("c", "b", "a"));
    assertThat(arr, not(arrayContaining("d")));
    assertThat(arr, arrayWithSize(3));
    assertThat(arr, arrayContainingInAnyOrder(new String[] { "a", "b", "c" }));
    assertThat(arr, not(arrayContaining(new String[] { "a", "b", "c", "d" })));
  }

  @Test
  void testMap() {
    Map<String, Integer> map = new HashMap<>();
    map.put("a", 1);
    map.put("b", 2);
    map.put("c", 3);
    assertThat(map, hasKey("a"));
    assertThat(map, hasValue(1));
    assertThat(map, hasEntry("a", 1));
    assertThat(map, hasEntry("b", 2));
    assertThat(map, hasEntry("c", 3));
    assertThat(map, not(hasKey("d")));
    assertThat(map, not(hasValue(4)));
    assertThat(map, not(hasEntry("a", 4)));
  }
}
