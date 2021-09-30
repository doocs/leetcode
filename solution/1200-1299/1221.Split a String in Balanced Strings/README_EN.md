# [1221. Split a String in Balanced Strings](https://leetcode.com/problems/split-a-string-in-balanced-strings)

[中文文档](/solution/1200-1299/1221.Split%20a%20String%20in%20Balanced%20Strings/README.md)

## Description

<p><strong>Balanced</strong> strings are those that have an equal quantity of <code>&#39;L&#39;</code> and <code>&#39;R&#39;</code> characters.</p>

<p>Given a <strong>balanced</strong> string <code>s</code>, split it in the maximum amount of balanced strings.</p>

<p>Return <em>the maximum amount of split <strong>balanced</strong> strings</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;RLRRLLRLRL&quot;
<strong>Output:</strong> 4
<strong>Explanation: </strong>s can be split into &quot;RL&quot;, &quot;RRLL&quot;, &quot;RL&quot;, &quot;RL&quot;, each substring contains same number of &#39;L&#39; and &#39;R&#39;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;RLLLLRRRLR&quot;
<strong>Output:</strong> 3
<strong>Explanation: </strong>s can be split into &quot;RL&quot;, &quot;LLLRRR&quot;, &quot;LR&quot;, each substring contains same number of &#39;L&#39; and &#39;R&#39;.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;LLLLRRRR&quot;
<strong>Output:</strong> 1
<strong>Explanation: </strong>s can be split into &quot;LLLLRRRR&quot;.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;RLRRRLLRLL&quot;
<strong>Output:</strong> 2
<strong>Explanation: </strong>s can be split into &quot;RL&quot;, &quot;RRRLLRLL&quot;, since each substring contains an equal number of &#39;L&#39; and &#39;R&#39;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> is either <code>&#39;L&#39;</code> or <code>&#39;R&#39;</code>.</li>
	<li><code>s</code> is a <strong>balanced</strong> string.</li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def balancedStringSplit(self, s: str) -> int:
        n = res = 0
        for c in s:
            if c == 'L':
                n += 1
            else:
                n -= 1
            if n == 0:
                res += 1
        return res
```

### **Java**

```java
class Solution {
    public int balancedStringSplit(String s) {
        int n = 0, res = 0;
        for (char c : s.toCharArray()) {
            if (c == 'L') {
                ++n;
            } else {
                --n;
            }
            if (n == 0) {
                ++res;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int balancedStringSplit(string s) {
        int n = 0, res = 0;
        for (char c : s) {
            if (c == 'L') ++n;
            else --n;
            if (n == 0) ++res;
        }
        return res;
    }
};
```

### **Go**

```go
func balancedStringSplit(s string) int {
	n, res := 0, 0
	for _, c := range s {
		if c == 'L' {
			n++
		} else {
			n--
		}
		if n == 0 {
			res++
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
