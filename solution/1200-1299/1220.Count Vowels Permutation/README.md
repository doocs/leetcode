# [1220. 统计元音字母序列的数目](https://leetcode.cn/problems/count-vowels-permutation)

[English Version](/solution/1200-1299/1220.Count%20Vowels%20Permutation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>，请你帮忙统计一下我们可以按下述规则形成多少个长度为&nbsp;<code>n</code>&nbsp;的字符串：</p>

<ul>
	<li>字符串中的每个字符都应当是小写元音字母（<code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, <code>&#39;u&#39;</code>）</li>
	<li>每个元音&nbsp;<code>&#39;a&#39;</code>&nbsp;后面都只能跟着&nbsp;<code>&#39;e&#39;</code></li>
	<li>每个元音&nbsp;<code>&#39;e&#39;</code>&nbsp;后面只能跟着&nbsp;<code>&#39;a&#39;</code>&nbsp;或者是&nbsp;<code>&#39;i&#39;</code></li>
	<li>每个元音&nbsp;<code>&#39;i&#39;</code>&nbsp;后面&nbsp;<strong>不能</strong> 再跟着另一个&nbsp;<code>&#39;i&#39;</code></li>
	<li>每个元音&nbsp;<code>&#39;o&#39;</code>&nbsp;后面只能跟着&nbsp;<code>&#39;i&#39;</code>&nbsp;或者是&nbsp;<code>&#39;u&#39;</code></li>
	<li>每个元音&nbsp;<code>&#39;u&#39;</code>&nbsp;后面只能跟着&nbsp;<code>&#39;a&#39;</code></li>
</ul>

<p>由于答案可能会很大，所以请你返回 模&nbsp;<code>10^9 + 7</code>&nbsp;之后的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 1
<strong>输出：</strong>5
<strong>解释：</strong>所有可能的字符串分别是：&quot;a&quot;, &quot;e&quot;, &quot;i&quot; , &quot;o&quot; 和 &quot;u&quot;。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 2
<strong>输出：</strong>10
<strong>解释：</strong>所有可能的字符串分别是：&quot;ae&quot;, &quot;ea&quot;, &quot;ei&quot;, &quot;ia&quot;, &quot;ie&quot;, &quot;io&quot;, &quot;iu&quot;, &quot;oi&quot;, &quot;ou&quot; 和 &quot;ua&quot;。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 5
<strong>输出：</strong>68</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10^4</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

根据题目描述，我们可以推出每个元音字母的前一个字母可以为哪些。

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

设 $dp[i][j]$ 表示当前长度为 $i$ 且以字符 j 为结尾的字符串的数目，其中 j = {0,1,2,3,4} 分别代表元音字母 `a,e,i,o,u`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
