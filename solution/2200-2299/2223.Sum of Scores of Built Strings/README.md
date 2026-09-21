---
comments: true
difficulty: 困难
rating: 2220
source: 第 75 场双周赛 Q4
tags:
    - 字符串
    - 二分查找
    - 字符串匹配
    - 后缀数组
    - 哈希函数
    - 滚动哈希
---

<!-- problem:start -->

# [2223. 构造字符串的总得分和](https://leetcode.cn/problems/sum-of-scores-of-built-strings)

[English Version](/solution/2200-2299/2223.Sum%20of%20Scores%20of%20Built%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你需要从空字符串开始&nbsp;<strong>构造</strong> 一个长度为 <code>n</code>&nbsp;的字符串 <code>s</code>&nbsp;，构造的过程为每次给当前字符串 <strong>前面</strong>&nbsp;添加 <strong>一个</strong> 字符。构造过程中得到的所有字符串编号为 <code>1</code>&nbsp;到 <code>n</code>&nbsp;，其中长度为 <code>i</code>&nbsp;的字符串编号为 <code>s<sub>i</sub></code>&nbsp;。</p>

<ul>
	<li>比方说，<code>s = "abaca"</code>&nbsp;，<code>s<sub>1</sub> == "a"</code>&nbsp;，<code>s<sub>2</sub> == "ca"</code>&nbsp;，<code>s<sub>3</sub> == "aca"</code>&nbsp;依次类推。</li>
</ul>

<p><code>s<sub>i</sub></code>&nbsp;的 <strong>得分</strong>&nbsp;为&nbsp;<code>s<sub>i</sub></code> 和&nbsp;<code>s<sub>n</sub></code>&nbsp;的 <strong>最长公共前缀</strong> 的长度（注意&nbsp;<code>s == s<sub>n</sub></code>&nbsp;）。</p>

<p>给你最终的字符串&nbsp;<code>s</code>&nbsp;，请你返回每一个<em>&nbsp;</em><code>s<sub>i</sub></code>&nbsp;的&nbsp;<strong>得分之和</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "babab"
<b>输出：</b>9
<b>解释：</b>
s<sub>1</sub> == "b" ，最长公共前缀是 "b" ，得分为 1 。
s<sub>2</sub> == "ab" ，没有公共前缀，得分为 0 。
s<sub>3</sub> == "bab" ，最长公共前缀为 "bab" ，得分为 3 。
s<sub>4</sub> == "abab" ，没有公共前缀，得分为 0 。
s<sub>5</sub> == "babab" ，最长公共前缀为 "babab" ，得分为 5 。
得分和为 1 + 0 + 3 + 0 + 5 = 9 ，所以我们返回 9 。</pre>

<p><strong>示例 2 ：</strong></p>

<pre>
<b>输入：</b>s = "azbazbzaz"
<b>输出：</b>14
<b>解释：</b>
s<sub>2</sub> == "az" ，最长公共前缀为 "az" ，得分为 2 。
s<sub>6</sub> == "azbzaz" ，最长公共前缀为 "azb" ，得分为 3 。
s<sub>9</sub> == "azbazbzaz" ，最长公共前缀为 "azbazbzaz" ，得分为 9 。
其他 s<sub>i</sub> 得分均为 0 。
得分和为 2 + 3 + 9 = 14 ，所以我们返回 14 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 每个前缀添加过程得到的 $s_i$ 其实是 $s$ 的长度为 $i$ 的后缀，其得分是该后缀与 $s$ 本身的最长公共前缀。对每个后缀暴力比较是 $O(n^2)$，$n \le 10^5$ 不可接受。
>
> 这些 LCP 正是 Z 函数：$z[i]$ 表示 $s[i:]$ 与 $s$ 的最长公共前缀，再补上 $z[0]=n$。线性求出 Z 数组后求和即可。若用字符串哈希，也可对每个起点二分 LCP，时间 $O(n\log n)$。

<!-- thinking:end -->

对字符串 $s$ 求 Z 函数，$z[i]$ 为 $s[i:]$ 与 $s$ 的最长公共前缀长度。长度为 $i$ 的后缀 $s_i$ 的得分即 $z[n-i]$，整串得分则为 $n$。因此将 Z 数组求和并加上 $n$ 即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sumScores(self, s: str) -> int:
        n = len(s)
        z = [0] * n
        l = r = 0
        for i in range(1, n):
            if i <= r:
                z[i] = min(r - i + 1, z[i - l])
            while i + z[i] < n and s[z[i]] == s[i + z[i]]:
                z[i] += 1
            if i + z[i] - 1 > r:
                l, r = i, i + z[i] - 1
        return n + sum(z)
```

#### Java

```java
class Solution {
    public long sumScores(String s) {
        int n = s.length();
        int[] z = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; ++i) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                ++z[i];
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        long ans = n;
        for (int x : z) {
            ans += x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long sumScores(string s) {
        int n = s.size();
        vector<int> z(n);
        for (int i = 1, l = 0, r = 0; i < n; ++i) {
            if (i <= r) {
                z[i] = min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                ++z[i];
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return n + accumulate(z.begin(), z.end(), 0LL);
    }
};
```

#### Go

```go
func sumScores(s string) int64 {
	n := len(s)
	z := make([]int, n)
	for i, l, r := 1, 0, 0; i < n; i++ {
		if i <= r {
			z[i] = min(r-i+1, z[i-l])
		}
		for i+z[i] < n && s[z[i]] == s[i+z[i]] {
			z[i]++
		}
		if i+z[i]-1 > r {
			l, r = i, i+z[i]-1
		}
	}
	ans := int64(n)
	for _, x := range z {
		ans += int64(x)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
