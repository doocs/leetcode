# [2414. Length of the Longest Alphabetical Continuous Substring](https://leetcode.com/problems/length-of-the-longest-alphabetical-continuous-substring)

[中文文档](/solution/2400-2499/2414.Length%20of%20the%20Longest%20Alphabetical%20Continuous%20Substring/README.md)

## Description

<p>An <strong>alphabetical continuous string</strong> is a string consisting of consecutive letters in the alphabet. In other words, it is any substring of the string <code>&quot;abcdefghijklmnopqrstuvwxyz&quot;</code>.</p>

<ul>
	<li>For example, <code>&quot;abc&quot;</code> is an alphabetical continuous string, while <code>&quot;acb&quot;</code> and <code>&quot;za&quot;</code> are not.</li>
</ul>

<p>Given a string <code>s</code> consisting of lowercase letters only, return the <em>length of the <strong>longest</strong> alphabetical continuous substring.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abacaba&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 4 distinct continuous substrings: &quot;a&quot;, &quot;b&quot;, &quot;c&quot; and &quot;ab&quot;.
&quot;ab&quot; is the longest continuous substring.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcde&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> &quot;abcde&quot; is the longest continuous substring.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only English lowercase letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestContinuousSubstring(self, s: str) -> int:
        ans = 0
        i, j = 0, 1
        while j < len(s):
            ans = max(ans, j - i)
            if ord(s[j]) - ord(s[j - 1]) != 1:
                i = j
            j += 1
        ans = max(ans, j - i)
        return ans
```

### **Java**

```java
class Solution {
    public int longestContinuousSubstring(String s) {
        int ans = 0;
        int i = 0, j = 1;
        for (; j < s.length(); ++j) {
            ans = Math.max(ans, j - i);
            if (s.charAt(j) - s.charAt(j - 1) != 1) {
                i = j;
            }
        }
        ans = Math.max(ans, j - i);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestContinuousSubstring(string s) {
        int ans = 0;
        int i = 0, j = 1;
        for (; j < s.size(); ++j) {
            ans = max(ans, j - i);
            if (s[j] - s[j - 1] != 1) {
                i = j;
            }
        }
        ans = max(ans, j - i);
        return ans;
    }
};
```

### **Go**

```go
func longestContinuousSubstring(s string) int {
	ans := 0
	i, j := 0, 1
	for ; j < len(s); j++ {
		ans = max(ans, j-i)
		if s[j]-s[j-1] != 1 {
			i = j
		}
	}
	ans = max(ans, j-i)
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C**

```c
#define max(a,b) (((a) > (b)) ? (a) : (b))

int longestContinuousSubstring(char *s) {
    int n = strlen(s);
    int i = 0;
    int res = 1;
    for (int j = 1; j < n; j++) {
        if (s[j] - s[j - 1] != 1) {
            res = max(res, j - i);
            i = j;
        }
    }
    return max(res, n - i);
}
```

### **TypeScript**

```ts
function longestContinuousSubstring(s: string): number {
    const n = s.length;
    let res = 1;
    let i = 0;
    for (let j = 1; j < n; j++) {
        if (s[j].charCodeAt(0) - s[j - 1].charCodeAt(0) !== 1) {
            res = Math.max(res, j - i);
            i = j;
        }
    }
    return Math.max(res, n - i);
}
```

### **Rust**

```rust
impl Solution {
    pub fn longest_continuous_substring(s: String) -> i32 {
        let s = s.as_bytes();
        let n = s.len();
        let mut res = 1;
        let mut i = 0;
        for j in 1..n {
            if s[j] - s[j - 1] != 1 {
                res = res.max(j - i);
                i = j;
            }
        }
        res.max(n - i) as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
