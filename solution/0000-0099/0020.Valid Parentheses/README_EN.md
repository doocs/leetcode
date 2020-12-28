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
class Solution:
    def isValid(self, s: str) -> bool:
        q = []
        parentheses = {'()', '[]', '{}'}
        for ch in s:
            if ch in '([{':
                q.append(ch)
            elif not q or q.pop() + ch not in parentheses:
                return False
        return not q
```

### **Java**

```java
class Solution {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Deque<Character> q = new ArrayDeque<>();
        for (char ch : chars) {
            boolean left = ch == '(' || ch == '[' || ch == '{';
            if (left) q.push(ch);
            else if (q.isEmpty() || !match(q.pop(), ch)) return false;
        }
        return q.isEmpty();
    }

    private boolean match(char l, char r) {
        return (l == '(' && r == ')') || (l == '{' && r == '}') || (l == '[' && r == ']');
    }
}
```

### **...**

```

```

<!-- tabs:end -->
