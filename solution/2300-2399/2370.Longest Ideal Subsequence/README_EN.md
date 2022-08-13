# [2370. Longest Ideal Subsequence](https://leetcode.com/problems/longest-ideal-subsequence)

[中文文档](/solution/2300-2399/2370.Longest%20Ideal%20Subsequence/README.md)

## Description

<p>You are given a string <code>s</code> consisting of lowercase letters and an integer <code>k</code>. We call a string <code>t</code> <strong>ideal</strong> if the following conditions are satisfied:</p>

<ul>
	<li><code>t</code> is a <strong>subsequence</strong> of the string <code>s</code>.</li>
	<li>The absolute difference in the alphabet order of every two <strong>adjacent</strong> letters in <code>t</code> is less than or equal to <code>k</code>.</li>
</ul>

<p>Return <em>the length of the <strong>longest</strong> ideal string</em>.</p>

<p>A <strong>subsequence</strong> is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.</p>

<p><strong>Note</strong> that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of <code>&#39;a&#39;</code> and <code>&#39;z&#39;</code> is <code>25</code>, not <code>1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;acfgbd&quot;, k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest ideal string is &quot;acbd&quot;. The length of this string is 4, so 4 is returned.
Note that &quot;acfgbd&quot; is not ideal because &#39;c&#39; and &#39;f&#39; have a difference of 3 in alphabet order.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, k = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> The longest ideal string is &quot;abcd&quot;. The length of this string is 4, so 4 is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 25</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestIdealString(self, s: str, k: int) -> int:
        n = len(s)
        ans = 1
        dp = [1] * n
        d = {s[0]: 0}
        for i in range(1, n):
            a = ord(s[i])
            for b in ascii_lowercase:
                if abs(a - ord(b)) > k:
                    continue
                if b in d:
                    dp[i] = max(dp[i], dp[d[b]] + 1)
            d[s[i]] = i
        return max(dp)
```

### **Java**

```java
class Solution {
    public int longestIdealString(String s, int k) {
        int n = s.length();
        int ans = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        Map<Character, Integer> d = new HashMap<>(26);
        d.put(s.charAt(0), 0);
        for (int i = 1; i < n; ++i) {
            char a = s.charAt(i);
            for (char b = 'a'; b <= 'z'; ++b) {
                if (Math.abs(a - b) > k) {
                    continue;
                }
                if (d.containsKey(b)) {
                    dp[i] = Math.max(dp[i], dp[d.get(b)] + 1);
                }
            }
            d.put(a, i);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int longestIdealString(string s, int k) {
        int n = s.size();
        int ans = 1;
        vector<int> dp(n, 1);
        unordered_map<char, int> d;
        d[s[0]] = 0;
        for (int i = 1; i < n; ++i) {
            char a = s[i];
            for (char b = 'a'; b <= 'z'; ++b) {
                if (abs(a - b) > k) continue;
                if (d.count(b)) dp[i] = max(dp[i], dp[d[b]] + 1);
            }
            d[a] = i;
            ans = max(ans, dp[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func longestIdealString(s string, k int) int {
	n := len(s)
	ans := 1
	dp := make([]int, n)
	for i := range dp {
		dp[i] = 1
	}
	d := map[byte]int{s[0]: 0}
	for i := 1; i < n; i++ {
		a := s[i]
		for b := byte('a'); b <= byte('z'); b++ {
			if int(a)-int(b) > k || int(b)-int(a) > k {
				continue
			}
			if v, ok := d[b]; ok {
				dp[i] = max(dp[i], dp[v]+1)
			}
		}
		d[a] = i
		ans = max(ans, dp[i])
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

### **TypeScript**

```ts
function longestIdealString(s: string, k: number): number {
    const dp = new Array(26).fill(0);
    for (const c of s) {
        const x = c.charCodeAt(0) - 'a'.charCodeAt(0);
        let t = 0;
        for (let i = 0; i < 26; i++) {
            if (Math.abs(x - i) <= k) {
                t = Math.max(t, dp[i] + 1);
            }
        }
        dp[x] = Math.max(dp[x], t);
    }

    return dp.reduce((r, c) => Math.max(r, c), 0);
}
```

### **...**

```

```

<!-- tabs:end -->
