# [1446. Consecutive Characters](https://leetcode.com/problems/consecutive-characters)

[中文文档](/solution/1400-1499/1446.Consecutive%20Characters/README.md)

## Description

<p>The <strong>power</strong> of the string is the maximum length of a non-empty substring that contains only one unique character.</p>

<p>Given a string <code>s</code>, return <em>the <strong>power</strong> of</em> <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The substring &quot;ee&quot; is of length 2 with the character &#39;e&#39; only.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abbcccddddeeeeedcba&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The substring &quot;eeeee&quot; is of length 5 with the character &#39;e&#39; only.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 500</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxPower(self, s: str) -> int:
        ans = t = 0
        for i, c in enumerate(s):
            if i == 0 or c == s[i - 1]:
                t += 1
            else:
                t = 1
            ans = max(ans, t)
        return ans
```

### **Java**

```java
class Solution {
    public int maxPower(String s) {
        int ans = 0, t = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (i == 0 || s.charAt(i) == s.charAt(i - 1)) {
                ++t;
            } else {
                t = 1;
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxPower(string s) {
        int ans = 0, t = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (i == 0 || s[i] == s[i - 1])
                ++t;
            else
                t = 1;
            ans = max(ans, t);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxPower(s string) int {
	ans, t := 0, 0
	for i := range s {
		if i == 0 || s[i] == s[i-1] {
			t++
		} else {
			t = 1
		}
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
