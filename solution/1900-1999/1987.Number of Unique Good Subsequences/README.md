---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1987.Number%20of%20Unique%20Good%20Subsequences/README.md
rating: 2422
source: 第 256 场周赛 Q4
tags:
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [1987. 不同的好子序列数目](https://leetcode.cn/problems/number-of-unique-good-subsequences)

[English Version](/solution/1900-1999/1987.Number%20of%20Unique%20Good%20Subsequences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二进制字符串&nbsp;<code>binary</code>&nbsp;。&nbsp;<code>binary</code>&nbsp;的一个 <strong>子序列</strong>&nbsp;如果是 <strong>非空</strong>&nbsp;的且没有 <b>前导</b>&nbsp;<strong>0</strong>&nbsp;（除非数字是 <code>"0"</code>&nbsp;本身），那么它就是一个 <strong>好</strong>&nbsp;的子序列。</p>

<p>请你找到&nbsp;<code>binary</code>&nbsp;<strong>不同好子序列</strong>&nbsp;的数目。</p>

<ul>
	<li>比方说，如果&nbsp;<code>binary = "001"</code>&nbsp;，那么所有 <strong>好</strong>&nbsp;子序列为&nbsp;<code>["0", "0", "1"]</code>&nbsp;，所以 <b>不同</b>&nbsp;的好子序列为&nbsp;<code>"0"</code> 和&nbsp;<code>"1"</code>&nbsp;。 注意，子序列&nbsp;<code>"00"</code>&nbsp;，<code>"01"</code>&nbsp;和&nbsp;<code>"001"</code>&nbsp;不是好的，因为它们有前导 0 。</li>
</ul>

<p>请你返回&nbsp;<code>binary</code>&nbsp;中&nbsp;<strong>不同好子序列</strong>&nbsp;的数目。由于答案可能很大，请将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong> 后返回。</p>

<p>一个 <strong>子序列</strong>&nbsp;指的是从原数组中删除若干个（可以一个也不删除）元素后，不改变剩余元素顺序得到的序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>binary = "001"
<b>输出：</b>2
<b>解释：</b>好的二进制子序列为 ["0", "0", "1"] 。
不同的好子序列为 "0" 和 "1" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>binary = "11"
<b>输出：</b>2
<b>解释：</b>好的二进制子序列为 ["1", "1", "11"] 。
不同的好子序列为 "1" 和 "11" 。</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>binary = "101"
<b>输出：</b>5
<b>解释：</b>好的二进制子序列为 ["1", "0", "1", "10", "11", "101"] 。
不同的好子序列为 "0" ，"1" ，"10" ，"11" 和 "101" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= binary.length &lt;= 10<sup>5</sup></code></li>
	<li><code>binary</code>&nbsp;只含有&nbsp;<code>'0'</code>&nbsp;和&nbsp;<code>'1'</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f$ 表示以 $1$ 结尾的不同好子序列的数目，定义 $g$ 表示以 $0$ 结尾的且以 $1$ 开头的不同好子序列的数目。初始时 $f = g = 0$。

对于一个二进制字符串，我们可以从左到右遍历每一位，假设当前位为 $c$，那么：

-   如果 $c = 0$，那么我们可以在 $f$ 和 $g$ 个不同的好子序列拼上 $c$，因此更新 $g = (g + f) \bmod (10^9 + 7)$；
-   如果 $c = 1$，那么我们可以在 $f$ 和 $g$ 个不同的好子序列拼上 $c$，同时还可以单独拼上 $c$，因此更新 $f = (f + g + 1) \bmod (10^9 + 7)$。

如果字符串包含 $0$，那么最终答案为 $f + g + 1$，否则答案为 $f + g$。

时间复杂度 $O(n)$，其中 $n$ 是字符串长度。空间复杂度 $O(1)$。

相似题目：

-   [940. 不同的子序列 II](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0940.Distinct%20Subsequences%20II/README.md)

<!-- tabs:start -->

```python
class Solution:
    def numberOfUniqueGoodSubsequences(self, binary: str) -> int:
        f = g = 0
        ans = 0
        mod = 10**9 + 7
        for c in binary:
            if c == "0":
                g = (g + f) % mod
                ans = 1
            else:
                f = (f + g + 1) % mod
        ans = (ans + f + g) % mod
        return ans
```

```java
class Solution {
    public int numberOfUniqueGoodSubsequences(String binary) {
        final int mod = (int) 1e9 + 7;
        int f = 0, g = 0;
        int ans = 0;
        for (int i = 0; i < binary.length(); ++i) {
            if (binary.charAt(i) == '0') {
                g = (g + f) % mod;
                ans = 1;
            } else {
                f = (f + g + 1) % mod;
            }
        }
        ans = (ans + f + g) % mod;
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfUniqueGoodSubsequences(string binary) {
        const int mod = 1e9 + 7;
        int f = 0, g = 0;
        int ans = 0;
        for (char& c : binary) {
            if (c == '0') {
                g = (g + f) % mod;
                ans = 1;
            } else {
                f = (f + g + 1) % mod;
            }
        }
        ans = (ans + f + g) % mod;
        return ans;
    }
};
```

```go
func numberOfUniqueGoodSubsequences(binary string) (ans int) {
	const mod int = 1e9 + 7
	f, g := 0, 0
	for _, c := range binary {
		if c == '0' {
			g = (g + f) % mod
			ans = 1
		} else {
			f = (f + g + 1) % mod
		}
	}
	ans = (ans + f + g) % mod
	return
}
```

```ts
function numberOfUniqueGoodSubsequences(binary: string): number {
    let [f, g] = [0, 0];
    let ans = 0;
    const mod = 1e9 + 7;
    for (const c of binary) {
        if (c === '0') {
            g = (g + f) % mod;
            ans = 1;
        } else {
            f = (f + g + 1) % mod;
        }
    }
    ans = (ans + f + g) % mod;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
