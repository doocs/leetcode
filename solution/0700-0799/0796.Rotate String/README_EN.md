# [796. Rotate String](https://leetcode.com/problems/rotate-string)

[中文文档](/solution/0700-0799/0796.Rotate%20String/README.md)

## Description

<p>We are given two strings, <code>A</code> and <code>B</code>.</p>

<p>A <em>shift on <code>A</code></em> consists of taking string <code>A</code> and moving the leftmost character to the rightmost position. For example, if <code>A = &#39;abcde&#39;</code>, then it will be <code>&#39;bcdea&#39;</code> after one shift on <code>A</code>. Return <code>True</code> if and only if <code>A</code> can become <code>B</code> after some number of shifts on <code>A</code>.</p>

<pre>

<strong>Example 1:</strong>

<strong>Input:</strong> A = &#39;abcde&#39;, B = &#39;cdeab&#39;

<strong>Output:</strong> true



<strong>Example 2:</strong>

<strong>Input:</strong> A = &#39;abcde&#39;, B = &#39;abced&#39;

<strong>Output:</strong> false

</pre>

<p><strong>Note:</strong></p>

<ul>
	<li><code>A</code> and <code>B</code> will have length at most <code>100</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rotateString(self, s: str, goal: str) -> bool:
        if len(s) != len(goal):
            return False
        return goal in s + s
```

### **Java**

```java
class Solution {
    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        return (s + s).contains(goal);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool rotateString(string s, string goal) {
        if (s.size() != goal.size()) {
            return false;
        }
        return !!strstr((s + s).data(), goal.data());
    }
};
```

### **Go**

```go
func rotateString(s string, goal string) bool {
	if len(s) != len(goal) {
		return false
	}
	return strings.Contains(s+s, goal)
}
```

### **...**

```

```

<!-- tabs:end -->
