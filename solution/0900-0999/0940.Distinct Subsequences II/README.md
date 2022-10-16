# [940. 不同的子序列 II](https://leetcode.cn/problems/distinct-subsequences-ii)

[English Version](/solution/0900-0999/0940.Distinct%20Subsequences%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个字符串 <code>s</code>，计算 <code>s</code> 的 <strong>不同非空子序列</strong> 的个数。因为结果可能很大，所以返回答案需要对<strong> </strong><strong><code>10^9 + 7</code> 取余</strong> 。</p>

<p>字符串的 <strong>子序列</strong> 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。</p>

<ul>
	<li>例如，<code>"ace"</code> 是 <code>"<em><strong>a</strong></em>b<em><strong>c</strong></em>d<em><strong>e</strong></em>"</code> 的一个子序列，但 <code>"aec"</code> 不是。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "abc"
<strong>输出：</strong>7
<strong>解释：</strong>7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aba"
<strong>输出：</strong>6
<strong>解释：</strong>6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "aaa"
<strong>输出：</strong>3
<strong>解释：</strong>3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> 仅由小写英文字母组成</li>
</ul>

<p>&nbsp;</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义 $dp[i]$ 表示以 $s[i]$ 结尾的不同子序列的个数。由于 $s$ 中只包含小写字母，因此我们可以直接创建一个长度为 $26$ 的数组。初始时 $dp$ 所有元素均为 $0$。答案为 $\sum_{i=0}^{25}dp[i]$。

遍历字符串 $s$，对于每个位置的字符 $s[i]$，我们需要更新以 $s[i]$ 结尾的不同子序列的个数，此时 $dp[i]=\sum_{j=0}^{25}dp[j]+1$。其中 $\sum_{j=0}^{25}dp[j]$ 是此前我们已经计算出所有不同子序列的个数，而 $+1$ 是指 $s[i]$ 本身也可以作为一个子序列。

最后，我们需要对 $dp$ 中的所有元素求和，再对 $10^9+7$ 取余，即为答案。

时间复杂度 $O(n\times C)$，其中 $n$ 是字符串 $s$ 的长度，而 $C$ 是字符集的大小，本题中 $C=26$。空间复杂度 $O(C)$。

**方法二：优化的动态规划**

在方法一的基础上，我们还可以维护当前 $dp$ 数组中所有元素的和 $ans$，这样我们每次更新 $dp[i]$ 时，只需要将 $dp[i]$ 加上 $ans-dp[i]+1$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
