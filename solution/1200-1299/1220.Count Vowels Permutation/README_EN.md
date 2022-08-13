# [1220. Count Vowels Permutation](https://leetcode.com/problems/count-vowels-permutation)

[中文文档](/solution/1200-1299/1220.Count%20Vowels%20Permutation/README.md)

## Description

<p>Given an integer <code>n</code>, your task is to count how many strings of length <code>n</code> can be formed under the following rules:</p>

<ul>
	<li>Each character is a lower case vowel&nbsp;(<code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;u&#39;</code>)</li>
	<li>Each vowel&nbsp;<code>&#39;a&#39;</code> may only be followed by an <code>&#39;e&#39;</code>.</li>
	<li>Each vowel&nbsp;<code>&#39;e&#39;</code> may only be followed by an <code>&#39;a&#39;</code>&nbsp;or an <code>&#39;i&#39;</code>.</li>
	<li>Each vowel&nbsp;<code>&#39;i&#39;</code> <strong>may not</strong> be followed by another <code>&#39;i&#39;</code>.</li>
	<li>Each vowel&nbsp;<code>&#39;o&#39;</code> may only be followed by an <code>&#39;i&#39;</code> or a&nbsp;<code>&#39;u&#39;</code>.</li>
	<li>Each vowel&nbsp;<code>&#39;u&#39;</code> may only be followed by an <code>&#39;a&#39;.</code></li>
</ul>

<p>Since the answer&nbsp;may be too large,&nbsp;return it modulo <code>10^9 + 7.</code></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> All possible strings are: &quot;a&quot;, &quot;e&quot;, &quot;i&quot; , &quot;o&quot; and &quot;u&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 10
<strong>Explanation:</strong> All possible strings are: &quot;ae&quot;, &quot;ea&quot;, &quot;ei&quot;, &quot;ia&quot;, &quot;ie&quot;, &quot;io&quot;, &quot;iu&quot;, &quot;oi&quot;, &quot;ou&quot; and &quot;ua&quot;.
</pre>

<p><strong>Example 3:&nbsp;</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 68</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10^4</code></li>
</ul>

## Solutions

```bash
a [e]
e [a|i]
i [a|e|o|u]
o [i|u]
u [a]

=>

[e|i|u]	a
[a|i]	e
[e|o]	i
[i]	o
[i|o]	u
```

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countVowelPermutation(self, n: int) -> int:
        dp = (1, 1, 1, 1, 1)
        MOD = 1000000007
        for _ in range(n - 1):
            dp = (
                (dp[1] + dp[2] + dp[4]) % MOD,
                (dp[0] + dp[2]) % MOD,
                (dp[1] + dp[3]) % MOD,
                dp[2],
                (dp[2] + dp[3]) % MOD,
            )
        return sum(dp) % MOD
```

### **Java**

```java
class Solution {
    private static final long MOD = (long) 1e9 + 7;

    public int countVowelPermutation(int n) {
        long[] dp = new long[5];
        long[] t = new long[5];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n - 1; ++i) {
            t[0] = (dp[1] + dp[2] + dp[4]) % MOD;
            t[1] = (dp[0] + dp[2]) % MOD;
            t[2] = (dp[1] + dp[3]) % MOD;
            t[3] = dp[2];
            t[4] = (dp[2] + dp[3]) % MOD;
            System.arraycopy(t, 0, dp, 0, 5);
        }
        long ans = 0;
        for (int i = 0; i < 5; ++i) {
            ans = (ans + dp[i]) % MOD;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countVowelPermutation(int n) {
        using ll = long long;
        const ll mod = 1e9 + 7;
        vector<ll> dp(5, 1);
        vector<ll> t(5);
        for (int i = 0; i < n - 1; ++i) {
            t[0] = (dp[1] + dp[2] + dp[4]) % mod;
            t[1] = (dp[0] + dp[2]) % mod;
            t[2] = (dp[1] + dp[3]) % mod;
            t[3] = dp[2];
            t[4] = (dp[2] + dp[3]) % mod;
            dp = t;
        }
        return accumulate(dp.begin(), dp.end(), 0LL) % mod;
    }
};
```

### **Go**

```go
func countVowelPermutation(n int) int {
	const mod int = 1e9 + 7
	dp := [5]int{1, 1, 1, 1, 1}
	for i := 0; i < n-1; i++ {
		dp = [5]int{
			(dp[1] + dp[2] + dp[4]) % mod,
			(dp[0] + dp[2]) % mod,
			(dp[1] + dp[3]) % mod,
			dp[2],
			(dp[2] + dp[3]) % mod,
		}
	}
	ans := 0
	for _, v := range dp {
		ans = (ans + v) % mod
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var countVowelPermutation = function (n) {
    const mod = 1000000007;
    const dp = new Array(5).fill(1);
    const t = new Array(5).fill(0);
    for (let i = 0; i < n - 1; ++i) {
        t[0] = (dp[1] + dp[2] + dp[4]) % mod;
        t[1] = (dp[0] + dp[2]) % mod;
        t[2] = (dp[1] + dp[3]) % mod;
        t[3] = dp[2];
        t[4] = (dp[2] + dp[3]) % mod;
        dp.splice(0, 5, ...t);
    }
    let ans = 0;
    for (let i = 0; i < 5; ++i) {
        ans = (ans + dp[i]) % mod;
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
