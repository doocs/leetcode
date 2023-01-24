# [1830. 使字符串有序的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-string-sorted)

[English Version](/solution/1800-1899/1830.Minimum%20Number%20of%20Operations%20to%20Make%20String%20Sorted/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串 <code>s</code> （<strong>下标从 0 开始</strong>）。你需要对 <code>s</code> 执行以下操作直到它变为一个有序字符串：</p>

<ol>
	<li>找到 <strong>最大下标</strong> <code>i</code> ，使得 <code>1 &lt;= i &lt; s.length</code> 且 <code>s[i] &lt; s[i - 1]</code> 。</li>
	<li>找到 <strong>最大下标</strong> <code>j</code> ，使得 <code>i &lt;= j &lt; s.length</code> 且对于所有在闭区间 <code>[i, j]</code> 之间的 <code>k</code> 都有 <code>s[k] &lt; s[i - 1]</code> 。</li>
	<li>交换下标为 <code>i - 1</code>​​​​ 和 <code>j</code>​​​​ 处的两个字符。</li>
	<li>将下标 <code>i</code> 开始的字符串后缀反转。</li>
</ol>

<p>请你返回将字符串变成有序的最少操作次数。由于答案可能会很大，请返回它对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "cba"
<b>输出：</b>5
<b>解释：</b>模拟过程如下所示：
操作 1：i=2，j=2。交换 s[1] 和 s[2] 得到 s="cab" ，然后反转下标从 2 开始的后缀字符串，得到 s="cab" 。
操作 2：i=1，j=2。交换 s[0] 和 s[2] 得到 s="bac" ，然后反转下标从 1 开始的后缀字符串，得到 s="bca" 。
操作 3：i=2，j=2。交换 s[1] 和 s[2] 得到 s="bac" ，然后反转下标从 2 开始的后缀字符串，得到 s="bac" 。
操作 4：i=1，j=1。交换 s[0] 和 s[1] 得到 s="abc" ，然后反转下标从 1 开始的后缀字符串，得到 s="acb" 。
操作 5：i=2，j=2。交换 s[1] 和 s[2] 得到 s="abc" ，然后反转下标从 2 开始的后缀字符串，得到 s="abc" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "aabaa"
<b>输出：</b>2
<b>解释：</b>模拟过程如下所示：
操作 1：i=3，j=4。交换 s[2] 和 s[4] 得到 s="aaaab" ，然后反转下标从 3 开始的后缀字符串，得到 s="aaaba" 。
操作 2：i=4，j=4。交换 s[3] 和 s[4] 得到 s="aaaab" ，然后反转下标从 4 开始的后缀字符串，得到 s="aaaab" 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>s = "cdbea"
<b>输出：</b>63</pre>

<p><strong>示例 4：</strong></p>

<pre><b>输入：</b>s = "leetcodeleetcodeleetcode"
<b>输出：</b>982157772
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3000</code></li>
	<li><code>s</code>​ 只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 排列组合 + 预处理**

题目中的操作实际上是求当前排列的上一个字典序排列，因此，我们只需要求出比当前排列小的排列的数量，就是答案。

这里我们需要考虑一个问题，给定每一种字母的频率，我们可以构造出多少种不同的排列？

假设总共有 $n$ 个字母，其中字母 $a$ 有 $n_1$ 个，字母 $b$ 有 $n_2$ 个，字母 $c$ 有 $n_3$ 个，那么我们可以构造出 $\frac{n!}{n_1! \times n_2! \times n_3!}$ 种不同的排列。其中 $n=n_1+n_2+n_3$。

我们可以通过预处理的方式，预先计算出所有的阶乘 $f$ 和阶乘的逆元 $g$。其中阶乘的逆元可以通过费马小定理求得。

接下来，我们从左到右遍历字符串 $s$，对于每一个位置 $i$，我们需要求出当前总共有多少个比 $s[i]$ 小的字母，记为 $m$。那么，我们可以构造出 $m \times \frac{(n - i - 1)!}{n_1! \times n_2! \cdots \times n_k!}$ 种不同的排列，其中 $k$ 为字母的种类数，累加到答案中。接下来，我们将 $s[i]$ 的频率减一，继续遍历下一个位置。

遍历完整个字符串后，即可得到答案。注意答案的取模操作。

时间复杂度 $O(n \times k)$，空间复杂度 $O(n)$。其中 $n$ 和 $k$ 分别为字符串的长度和字母的种类数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
n = 3010
mod = 10**9 + 7
f = [1] + [0] * n
g = [1] + [0] * n

for i in range(1, n):
    f[i] = f[i - 1] * i % mod
    g[i] = pow(f[i], mod - 2, mod)


class Solution:
    def makeStringSorted(self, s: str) -> int:
        cnt = Counter(s)
        ans, n = 0, len(s)
        for i, c in enumerate(s):
            m = sum(v for a, v in cnt.items() if a < c)
            t = f[n - i - 1] * m
            for v in cnt.values():
                t = t * g[v] % mod
            ans = (ans + t) % mod
            cnt[c] -= 1
            if cnt[c] == 0:
                cnt.pop(c)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int N = 3010;
    private static final int MOD = (int) 1e9 + 7;
    private static final long[] f = new long[N];
    private static final long[] g = new long[N];

    static {
        f[0] = 1;
        g[0] = 1;
        for (int i = 1; i < N; ++i) {
            f[i] = f[i - 1] * i % MOD;
            g[i] = qmi(f[i], MOD - 2);
        }
    }

    public static long qmi(long a, int k) {
        long res = 1;
        while (k != 0) {
            if ((k & 1) == 1) {
                res = res * a % MOD;
            }
            k >>= 1;
            a = a * a % MOD;
        }
        return res;
    }

    public int makeStringSorted(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int m = 0;
            for (int j = s.charAt(i) - 'a' - 1; j >= 0; --j) {
                m += cnt[j];
            }
            long t = m * f[n - i - 1] % MOD;
            for (int v : cnt) {
                t = t * g[v] % MOD;
            }
            --cnt[s.charAt(i) - 'a'];
            ans = (ans + t + MOD) % MOD;
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
const int N = 3010;
const int MOD = 1e9 + 7;
long f[N];
long g[N];

long qmi(long a, int k) {
    long res = 1;
    while (k != 0) {
        if ((k & 1) == 1) {
            res = res * a % MOD;
        }
        k >>= 1;
        a = a * a % MOD;
    }
    return res;
}

int init = []() {
    f[0] = g[0] = 1;
    for (int i = 1; i < N; ++i) {
        f[i] = f[i - 1] * i % MOD;
        g[i] = qmi(f[i], MOD - 2);
    }
    return 0;
}();


class Solution {
public:
    int makeStringSorted(string s) {
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
        }
        int n = s.size();
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            int m = 0;
            for (int j = s[i] - 'a' - 1; ~j; --j) {
                m += cnt[j];
            }
            long t = m * f[n - i - 1] % MOD;
            for (int& v : cnt) {
                t = t * g[v] % MOD;
            }
            ans = (ans + t + MOD) % MOD;
            --cnt[s[i] - 'a'];
        }
        return ans;
    }
};
```

### **Go**

```go
const n = 3010
const mod = 1e9 + 7

var f = make([]int, n)
var g = make([]int, n)

func qmi(a, k int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % mod
		}
		k >>= 1
		a = a * a % mod
	}
	return res
}

func init() {
	f[0], g[0] = 1, 1
	for i := 1; i < n; i++ {
		f[i] = f[i-1] * i % mod
		g[i] = qmi(f[i], mod-2)
	}
}

func makeStringSorted(s string) (ans int) {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for i, c := range s {
		m := 0
		for j := int(c-'a') - 1; j >= 0; j-- {
			m += cnt[j]
		}
		t := m * f[len(s)-i-1] % mod
		for _, v := range cnt {
			t = t * g[v] % mod
		}
		ans = (ans + t + mod) % mod
		cnt[c-'a']--
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
