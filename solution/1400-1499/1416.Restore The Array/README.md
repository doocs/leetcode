---
comments: true
difficulty: 困难
rating: 1919
source: 第 24 场双周赛 Q4
tags:
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [1416. 恢复数组](https://leetcode.cn/problems/restore-the-array)

[English Version](/solution/1400-1499/1416.Restore%20The%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>某个程序本来应该输出一个整数数组。但是这个程序忘记输出空格了以致输出了一个数字字符串，我们所知道的信息只有：数组中所有整数都在 <code>[1, k]</code>&nbsp;之间，且数组中的数字都没有前导 0 。</p>

<p>给你字符串&nbsp;<code>s</code>&nbsp;和整数&nbsp;<code>k</code>&nbsp;。可能会有多种不同的数组恢复结果。</p>

<p>按照上述程序，请你返回所有可能输出字符串&nbsp;<code>s</code>&nbsp;的数组方案数。</p>

<p>由于数组方案数可能会很大，请你返回它对&nbsp;<code>10^9 + 7</code>&nbsp;<strong>取余</strong>&nbsp;后的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;1000&quot;, k = 10000
<strong>输出：</strong>1
<strong>解释：</strong>唯一一种可能的数组方案是 [1000]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;1000&quot;, k = 10
<strong>输出：</strong>0
<strong>解释：</strong>不存在任何数组方案满足所有整数都 &gt;= 1 且 &lt;= 10 同时输出结果为 s 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;1317&quot;, k = 2000
<strong>输出：</strong>8
<strong>解释：</strong>可行的数组方案为 [1317]，[131,7]，[13,17]，[1,317]，[13,1,7]，[1,31,7]，[1,3,17]，[1,3,1,7]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;2020&quot;, k = 30
<strong>输出：</strong>1
<strong>解释：</strong>唯一可能的数组方案是 [20,20] 。 [2020] 不是可行的数组方案，原因是 2020 &gt; 30 。 [2,020] 也不是可行的数组方案，因为 020 含有前导 0 。
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>s = &quot;1234567890&quot;, k = 90
<strong>输出：</strong>34
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code>.</li>
	<li><code>s</code>&nbsp;只包含数字且不包含前导 0 。</li>
	<li><code>1 &lt;= k &lt;= 10^9</code>.</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 将数字串切成若干属于 $[1,k]$ 且无前导零的整数，$n\le 10^5$，枚举所有切法不可行。$k\le 10^9$，从某一位置向后延伸的合法数字长度不超过 $10$。
>
> 定义 $f(i)$ 为后缀 $s[i:]$ 的恢复方案数：若 $s[i]='0'$ 则无法开始；否则枚举结束位置 $j$，在数值 $\le k$ 时累加 $f(j+1)$。对 $f(i)$ 记忆化或改写成从右向左的线性 DP。

<!-- thinking:end -->

从右往左计算 $f(i)$，$f(n)=1$。$s[i]='0'$ 时 $f(i)=0$；否则从下标 $i$ 起向右拼数，数值一旦大于 $k$ 就停止，并把每个合法切点后的 $f(j+1)$ 累加进 $f(i)$。答案是 $f(0)$，对 $10^9+7$ 取模。

时间复杂度 $O(n \times d)$，空间复杂度 $O(n)$。其中 $n$ 是 $s$ 的长度，$d$ 是 $k$ 的十进制位数，不超过 $10$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfArrays(self, s: str, k: int) -> int:
        mod = 10**9 + 7
        n = len(s)
        f = [0] * (n + 1)
        f[n] = 1
        for i in range(n - 1, -1, -1):
            if s[i] == '0':
                continue
            x = 0
            for j in range(i, n):
                x = x * 10 + int(s[j])
                if x > k:
                    break
                f[i] = (f[i] + f[j + 1]) % mod
        return f[0]
```

#### Java

```java
class Solution {
    public int numberOfArrays(String s, int k) {
        final int mod = 1_000_000_007;
        int n = s.length();
        int[] f = new int[n + 1];
        f[n] = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                continue;
            }
            long x = 0;
            for (int j = i; j < n; ++j) {
                x = x * 10 + s.charAt(j) - '0';
                if (x > k) {
                    break;
                }
                f[i] = (int) ((f[i] + f[j + 1]) % mod);
            }
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfArrays(string s, int k) {
        const int mod = 1e9 + 7;
        int n = s.size();
        vector<int> f(n + 1);
        f[n] = 1;
        for (int i = n - 1; i >= 0; --i) {
            if (s[i] == '0') {
                continue;
            }
            long long x = 0;
            for (int j = i; j < n; ++j) {
                x = x * 10 + s[j] - '0';
                if (x > k) {
                    break;
                }
                f[i] = (f[i] + f[j + 1]) % mod;
            }
        }
        return f[0];
    }
};
```

#### Go

```go
func numberOfArrays(s string, k int) int {
	const mod = int(1e9 + 7)
	n := len(s)
	f := make([]int, n+1)
	f[n] = 1
	for i := n - 1; i >= 0; i-- {
		if s[i] == '0' {
			continue
		}
		x := 0
		for j := i; j < n; j++ {
			x = x*10 + int(s[j]-'0')
			if x > k {
				break
			}
			f[i] = (f[i] + f[j+1]) % mod
		}
	}
	return f[0]
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
