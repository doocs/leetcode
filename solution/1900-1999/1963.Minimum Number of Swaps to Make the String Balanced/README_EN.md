# [1963. Minimum Number of Swaps to Make the String Balanced](https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced)

[中文文档](/solution/1900-1999/1963.Minimum%20Number%20of%20Swaps%20to%20Make%20the%20String%20Balanced/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> of <strong>even</strong> length <code>n</code>. The string consists of <strong>exactly</strong> <code>n / 2</code> opening brackets <code>&#39;[&#39;</code> and <code>n / 2</code> closing brackets <code>&#39;]&#39;</code>.</p>

<p>A string is called <strong>balanced</strong> if and only if:</p>

<ul>
	<li>It is the empty string, or</li>
	<li>It can be written as <code>AB</code>, where both <code>A</code> and <code>B</code> are <strong>balanced</strong> strings, or</li>
	<li>It can be written as <code>[C]</code>, where <code>C</code> is a <strong>balanced</strong> string.</li>
</ul>

<p>You may swap the brackets at <strong>any</strong> two indices <strong>any</strong> number of times.</p>

<p>Return <em>the <strong>minimum</strong> number of swaps to make </em><code>s</code> <em><strong>balanced</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;][][&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can make the string balanced by swapping index 0 with index 3.
The resulting string is &quot;[[]]&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;]]][[[&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> You can do the following to make the string balanced:
- Swap index 0 with index 4. s = &quot;[]][][&quot;.
- Swap index 1 with index 5. s = &quot;[[][]]&quot;.
The resulting string is &quot;[[][]]&quot;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;[]&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> The string is already balanced.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == s.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>6</sup></code></li>
	<li><code>n</code> is even.</li>
	<li><code>s[i]</code> is either <code>&#39;[&#39; </code>or <code>&#39;]&#39;</code>.</li>
	<li>The number of opening brackets <code>&#39;[&#39;</code> equals <code>n / 2</code>, and the number of closing brackets <code>&#39;]&#39;</code> equals <code>n / 2</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minSwaps(self, s: str) -> int:
        ans = 0
        for c in s:
            if c == '[':
                ans += 1
            elif ans:
                ans -= 1
        return (ans + 1) >> 1
```

### **Java**

```java
class Solution {
    public int minSwaps(String s) {
        int ans = 0;
        for (char c : s.toCharArray()) {
            if (c == '[') {
                ++ans;
            } else if (ans > 0) {
                --ans;
            }
        }
        return (ans + 1) >> 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSwaps(string s) {
        int ans = 0;
        for (char& c : s) {
            if (c == '[')
                ++ans;
            else if (ans)
                --ans;
        }
        return (ans + 1) >> 1;
    }
};
```

### **Go**

```go
func minSwaps(s string) int {
	ans := 0
	for _, c := range s {
		if c == '[' {
			ans++
		} else if ans > 0 {
			ans--
		}
	}
	return (ans + 1) >> 1
}
```

### **...**

```

```

<!-- tabs:end -->
