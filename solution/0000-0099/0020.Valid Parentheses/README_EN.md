# [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses)

[中文文档](/solution/0000-0099/0020.Valid%20Parentheses/README.md)

## Description

<p>Given a string containing just the characters <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code>, <code>&#39;{&#39;</code>, <code>&#39;}&#39;</code>, <code>&#39;[&#39;</code> and <code>&#39;]&#39;</code>, determine if the input string is valid.</p>

<p>An input string is valid if:</p>

<ol>

    <li>Open brackets must be closed by the same type of brackets.</li>

    <li>Open brackets must be closed in the correct order.</li>

</ol>

<p>Note that an empty string is&nbsp;also considered valid.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input:</strong> &quot;()&quot;

<strong>Output:</strong> true

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input:</strong> &quot;()[]{}&quot;

<strong>Output:</strong> true

</pre>

<p><strong>Example 3:</strong></p>

<pre>

<strong>Input:</strong> &quot;(]&quot;

<strong>Output:</strong> false

</pre>

<p><strong>Example 4:</strong></p>

<pre>

<strong>Input:</strong> &quot;([)]&quot;

<strong>Output:</strong> false

</pre>

<p><strong>Example 5:</strong></p>

<pre>

<strong>Input:</strong> &quot;{[]}&quot;

<strong>Output:</strong> true

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

```java
class Solution {

  public boolean isValid(String s) {
    if (s == null || s == "") {
      return true;
    }
    char[] chars = s.toCharArray();
    Stack<Character> helper = new Stack<>();
    for (char c : chars) {
      if (isLeft(c)) {
        helper.push(c);
      } else {
        if (helper.isEmpty() || !match(helper.pop(), c)) {
          return false;
        }
      }
    }
    return helper.isEmpty();
  }

  private boolean isLeft(char c) {
    return c == '(' || c == '[' || c == '{';
  }

  private boolean isRight(char c) {
    return c == ')' || c == ']' || c == '}';
  }

  private boolean match(char left, char right) {
    return (
      (left == '(' && right == ')') ||
      (left == '[' && right == ']') ||
      (left == '{' && right == '}')
    );
  }
}
```

### **...**

```

```

<!-- tabs:end -->
