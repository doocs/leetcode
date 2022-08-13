# [1790. Check if One String Swap Can Make Strings Equal](https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal)

[中文文档](/solution/1700-1799/1790.Check%20if%20One%20String%20Swap%20Can%20Make%20Strings%20Equal/README.md)

## Description

<p>You are given two strings <code>s1</code> and <code>s2</code> of equal length. A <strong>string swap</strong> is an operation where you choose two indices in a string (not necessarily different) and swap the characters at these indices.</p>

<p>Return <code>true</code> <em>if it is possible to make both strings equal by performing <strong>at most one string swap </strong>on <strong>exactly one</strong> of the strings. </em>Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;bank&quot;, s2 = &quot;kanb&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> For example, swap the first character with the last character of s2 to make &quot;bank&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;attack&quot;, s2 = &quot;defend&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to make them equal with one string swap.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;kelb&quot;, s2 = &quot;kelb&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The two strings are already equal, so no string swap operation is required.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 100</code></li>
	<li><code>s1.length == s2.length</code></li>
	<li><code>s1</code> and <code>s2</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def areAlmostEqual(self, s1: str, s2: str) -> bool:
        cnt, n = 0, len(s1)
        c1 = c2 = None
        for i in range(n):
            if s1[i] != s2[i]:
                cnt += 1
                if (cnt == 2 and (s1[i] != c2 or s2[i] != c1)) or cnt > 2:
                    return False
                c1, c2 = s1[i], s2[i]
        return cnt == 0 or cnt == 2
```

### **Java**

```java
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        int cnt = 0;
        char c1 = 0;
        char c2 = 0;
        for (int i = 0; i < n; ++i) {
            char t1 = s1.charAt(i), t2 = s2.charAt(i);
            if (t1 != t2) {
                ++cnt;
                if ((cnt == 2 && (c1 != t2 || c2 != t1)) || cnt > 2) {
                    return false;
                }
                c1 = t1;
                c2 = t2;
            }
        }
        return cnt == 0 || cnt == 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool areAlmostEqual(string s1, string s2) {
        char c1 = 0, c2 = 0;
        int n = s1.size();
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (s1[i] != s2[i]) {
                ++cnt;
                if ((cnt == 2 && (c1 != s2[i] || c2 != s1[i])) || cnt > 2) return false;
                c1 = s1[i];
                c2 = s2[i];
            }
        }
        return cnt == 0 || cnt == 2;
    }
};
```

### **Go**

```go
func areAlmostEqual(s1 string, s2 string) bool {
	var c1, c2 byte
	cnt, n := 0, len(s1)
	for i := 0; i < n; i++ {
		if s1[i] != s2[i] {
			cnt++
			if (cnt == 2 && (c1 != s2[i] || c2 != s1[i])) || cnt > 2 {
				return false
			}
			c1, c2 = s1[i], s2[i]
		}
	}
	return cnt == 0 || cnt == 2
}
```

### **Rust**

```rust
impl Solution {
    pub fn are_almost_equal(s1: String, s2: String) -> bool {
        let (s1, s2) = (s1.as_bytes(), s2.as_bytes());
        let n = s1.len();
        let mut indexs = vec![];
        for i in 0..n {
            let (c1, c2) = (s1[i], s2[i]);
            if c1 != c2 {
                indexs.push(i);
                if indexs.len() > 2 {
                    return false;
                }
            }
        }
        let size = indexs.len();
        if size == 2 {
            return s1[indexs[0]] == s2[indexs[1]] && s2[indexs[0]] == s1[indexs[1]];
        }
        size != 1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
