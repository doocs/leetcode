# [467. Unique Substrings in Wraparound String](https://leetcode.com/problems/unique-substrings-in-wraparound-string)

[中文文档](/solution/0400-0499/0467.Unique%20Substrings%20in%20Wraparound%20String/README.md)

## Description

<p>We define the string <code>s</code> to be the infinite wraparound string of <code>&quot;abcdefghijklmnopqrstuvwxyz&quot;</code>, so <code>s</code> will look like this:</p>

<ul>
	<li><code>&quot;...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....&quot;</code>.</li>
</ul>

<p>Given a string <code>p</code>, return <em>the number of <strong>unique non-empty substrings</strong> of </em><code>p</code><em> are present in </em><code>s</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> p = &quot;a&quot;
<strong>Output:</strong> 1
Explanation: Only the substring &quot;a&quot; of p is in s.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> p = &quot;cac&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two substrings (&quot;a&quot;, &quot;c&quot;) of p in s.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> p = &quot;zab&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> There are six substrings (&quot;z&quot;, &quot;a&quot;, &quot;b&quot;, &quot;za&quot;, &quot;ab&quot;, and &quot;zab&quot;) of p in s.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= p.length &lt;= 10<sup>5</sup></code></li>
	<li><code>p</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findSubstringInWraproundString(self, p: str) -> int:
        dp = [0] * 26
        k = 0
        for i, c in enumerate(p):
            if i and (ord(c) - ord(p[i - 1])) % 26 == 1:
                k += 1
            else:
                k = 1
            idx = ord(c) - ord('a')
            dp[idx] = max(dp[idx], k)
        return sum(dp)
```

### **Java**

```java
class Solution {
    public int findSubstringInWraproundString(String p) {
        int[] dp = new int[26];
        int k = 0;
        for (int i = 0; i < p.length(); ++i) {
            char c = p.charAt(i);
            if (i > 0 && (c - p.charAt(i - 1) + 26) % 26 == 1) {
                ++k;
            } else {
                k = 1;
            }
            dp[c - 'a'] = Math.max(dp[c - 'a'], k);
        }
        int ans = 0;
        for (int v : dp) {
            ans += v;
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function findSubstringInWraproundString(p: string): number {
    const n = p.length;
    const dp = new Array(26).fill(0);
    let cur = 1;
    dp[p.charCodeAt(0) - 'a'.charCodeAt(0)] = 1;
    for (let i = 1; i < n; i++) {
        if ((p.charCodeAt(i) - p.charCodeAt(i - 1) + 25) % 26 == 0) {
            cur++;
        } else {
            cur = 1;
        }
        const index = p.charCodeAt(i) - 'a'.charCodeAt(0);
        dp[index] = Math.max(dp[index], cur);
    }
    return dp.reduce((r, v) => r + v);
}
```

### **Rust**

```rust
impl Solution {
    pub fn find_substring_in_wrapround_string(p: String) -> i32 {
        let n = p.len();
        let p = p.as_bytes();
        let mut dp = [0; 26];
        let mut cur = 1;
        dp[(p[0] - b'a') as usize] = 1;
        for i in 1..n {
            if (p[i] - p[i - 1] + 25) % 26 == 0 {
                cur += 1;
            } else {
                cur = 1;
            }
            let index = (p[i] - b'a') as usize;
            dp[index] = dp[index].max(cur);
        }
        dp.into_iter().sum()
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int findSubstringInWraproundString(string p) {
        vector<int> dp(26);
        int k = 0;
        for (int i = 0; i < p.size(); ++i) {
            char c = p[i];
            if (i && (c - p[i - 1] + 26) % 26 == 1)
                ++k;
            else
                k = 1;
            dp[c - 'a'] = max(dp[c - 'a'], k);
        }
        int ans = 0;
        for (int& v : dp) ans += v;
        return ans;
    }
};
```

### **Go**

```go
func findSubstringInWraproundString(p string) int {
	dp := make([]int, 26)
	k := 0
	for i := range p {
		c := p[i]
		if i > 0 && (c-p[i-1]+26)%26 == 1 {
			k++
		} else {
			k = 1
		}
		dp[c-'a'] = max(dp[c-'a'], k)
	}
	ans := 0
	for _, v := range dp {
		ans += v
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
