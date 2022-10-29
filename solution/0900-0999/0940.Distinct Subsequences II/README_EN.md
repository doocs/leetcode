# [940. Distinct Subsequences II](https://leetcode.com/problems/distinct-subsequences-ii)

[中文文档](/solution/0900-0999/0940.Distinct%20Subsequences%20II/README.md)

## Description

<p>Given a string s, return <em>the number of <strong>distinct non-empty subsequences</strong> of</em> <code>s</code>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>
A <strong>subsequence</strong> of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., <code>&quot;ace&quot;</code> is a subsequence of <code>&quot;<u>a</u>b<u>c</u>d<u>e</u>&quot;</code> while <code>&quot;aec&quot;</code> is not.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> The 7 distinct subsequences are &quot;a&quot;, &quot;b&quot;, &quot;c&quot;, &quot;ab&quot;, &quot;ac&quot;, &quot;bc&quot;, and &quot;abc&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aba&quot;
<strong>Output:</strong> 6
<strong>Explanation:</strong> The 6 distinct subsequences are &quot;a&quot;, &quot;b&quot;, &quot;ab&quot;, &quot;aa&quot;, &quot;ba&quot;, and &quot;aba&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaa&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The 3 distinct subsequences are &quot;a&quot;, &quot;aa&quot; and &quot;aaa&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def distinctSubseqII(self, s: str) -> int:
        mod = 10**9 + 7
        n = len(s)
        dp = [[0] * 26 for _ in range(n + 1)]
        for i, c in enumerate(s, 1):
            k = ord(c) - ord('a')
            for j in range(26):
                if j == k:
                    dp[i][j] = sum(dp[i - 1]) % mod + 1
                else:
                    dp[i][j] = dp[i - 1][j]
        return sum(dp[-1]) % mod
```

```python
class Solution:
    def distinctSubseqII(self, s: str) -> int:
        mod = 10**9 + 7
        dp = [0] * 26
        for c in s:
            i = ord(c) - ord('a')
            dp[i] = sum(dp) % mod + 1
        return sum(dp) % mod
```

```python
class Solution:
    def distinctSubseqII(self, s: str) -> int:
        mod = 10**9 + 7
        dp = [0] * 26
        ans = 0
        for c in s:
            i = ord(c) - ord('a')
            add = ans - dp[i] + 1
            ans = (ans + add) % mod
            dp[i] += add
        return ans
```

### **Java**

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int distinctSubseqII(String s) {
        int[] dp = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            dp[j] = sum(dp) + 1;
        }
        return sum(dp);
    }

    private int sum(int[] arr) {
        int x = 0;
        for (int v : arr) {
            x = (x + v) % MOD;
        }
        return x;
    }
}
```

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int distinctSubseqII(String s) {
        int[] dp = new int[26];
        int ans = 0;
        for (int i = 0; i < s.length(); ++i) {
            int j = s.charAt(i) - 'a';
            int add = (ans - dp[j] + 1) % MOD;
            ans = (ans + add) % MOD;
            dp[j] = (dp[j] + add) % MOD;
        }
        return (ans + MOD) % MOD;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int distinctSubseqII(string s) {
        vector<long> dp(26);
        for (char& c : s) {
            int i = c - 'a';
            dp[i] = accumulate(dp.begin(), dp.end(), 1l) % mod;
        }
        return accumulate(dp.begin(), dp.end(), 0l) % mod;
    }
};
```

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int distinctSubseqII(string s) {
        vector<long> dp(26);
        long ans = 0;
        for (char& c : s) {
            int i = c - 'a';
            long add = ans - dp[i] + 1;
            ans = (ans + add + mod) % mod;
            dp[i] = (dp[i] + add) % mod;
        }
        return ans;
    }
};
```

### **Go**

```go
func distinctSubseqII(s string) int {
	const mod int = 1e9 + 7
	sum := func(arr []int) int {
		x := 0
		for _, v := range arr {
			x = (x + v) % mod
		}
		return x
	}

	dp := make([]int, 26)
	for _, c := range s {
		c -= 'a'
		dp[c] = sum(dp) + 1
	}
	return sum(dp)
}
```

```go
func distinctSubseqII(s string) int {
	const mod int = 1e9 + 7
	dp := make([]int, 26)
	ans := 0
	for _, c := range s {
		c -= 'a'
		add := ans - dp[c] + 1
		ans = (ans + add) % mod
		dp[c] = (dp[c] + add) % mod
	}
	return (ans + mod) % mod
}
```

### **C**

```c
int distinctSubseqII(char * s){
    int mod = 1e9 + 7;
    int n = strlen(s);
    int dp[26] = {0};
    for (int i = 0 ; i < n; i++) {
        int sum = 0;
        for (int j = 0; j < 26; j++) {
            sum = (sum + dp[j]) % mod;
        }
        dp[s[i] - 'a'] = sum + 1;
    }
    int res = 0;
    for (int i = 0 ; i < 26; i++) {
        res = (res + dp[i]) % mod;
    }
    return res;
}
```

### **TypeScript**

```ts
function distinctSubseqII(s: string): number {
    const mod = 1e9 + 7;
    const dp = new Array(26).fill(0);
    for (const c of s) {
        dp[c.charCodeAt(0) - 'a'.charCodeAt(0)] =
            dp.reduce((r, v) => (r + v) % mod, 0) + 1;
    }
    return dp.reduce((r, v) => (r + v) % mod, 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn distinct_subseq_ii(s: String) -> i32 {
        const MOD: i32 = 1e9 as i32 + 7;
        let mut dp = [0; 26];
        for u in s.as_bytes() {
            let i = (u - &b'a') as usize;
            dp[i] = {
                let mut sum = 0;
                dp.iter().for_each(|&v| sum = (sum + v) % MOD);
                sum
            } + 1;
        }
        let mut res = 0;
        dp.iter().for_each(|&v| res = (res + v) % MOD);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
