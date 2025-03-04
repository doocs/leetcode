---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2311.Longest%20Binary%20Subsequence%20Less%20Than%20or%20Equal%20to%20K/README.md
rating: 1839
source: 第 298 场周赛 Q3
tags:
    - 贪心
    - 记忆化搜索
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [2311. 小于等于 K 的最长二进制子序列](https://leetcode.cn/problems/longest-binary-subsequence-less-than-or-equal-to-k)

[English Version](/solution/2300-2399/2311.Longest%20Binary%20Subsequence%20Less%20Than%20or%20Equal%20to%20K/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二进制字符串&nbsp;<code>s</code>&nbsp;和一个正整数&nbsp;<code>k</code>&nbsp;。</p>

<p>请你返回 <code>s</code>&nbsp;的 <strong>最长</strong>&nbsp;子序列，且该子序列对应的 <strong>二进制</strong>&nbsp;数字小于等于 <code>k</code>&nbsp;。</p>

<p>注意：</p>

<ul>
	<li>子序列可以有 <strong>前导 0</strong>&nbsp;。</li>
	<li>空字符串视为&nbsp;<code>0</code>&nbsp;。</li>
	<li><strong>子序列</strong>&nbsp;是指从一个字符串中删除零个或者多个字符后，不改变顺序得到的剩余字符序列。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "1001010", k = 5
<b>输出：</b>5
<b>解释：</b>s 中小于等于 5 的最长子序列是 "00010" ，对应的十进制数字是 2 。
注意 "00100" 和 "00101" 也是可行的最长子序列，十进制分别对应 4 和 5 。
最长子序列的长度为 5 ，所以返回 5 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>s = "00101001", k = 1
<b>输出：</b>6
<b>解释：</b>"000001" 是 s 中小于等于 1 的最长子序列，对应的十进制数字是 1 。
最长子序列的长度为 6 ，所以返回 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s[i]</code> 要么是&nbsp;<code>'0'</code>&nbsp;，要么是&nbsp;<code>'1'</code> 。</li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

最长二进制子序列必然包含原字符串中所有的 $0$，在此基础上，我们从右到左遍历 $s$，若遇到 $1$，判断子序列能否添加 $1$，使得子序列对应的二进制数字 $v \leq k$。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubsequence(self, s: str, k: int) -> int:
        ans = v = 0
        for c in s[::-1]:
            if c == "0":
                ans += 1
            elif ans < 30 and (v | 1 << ans) <= k:
                v |= 1 << ans
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int longestSubsequence(String s, int k) {
        int ans = 0, v = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '0') {
                ++ans;
            } else if (ans < 30 && (v | 1 << ans) <= k) {
                v |= 1 << ans;
                ++ans;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestSubsequence(string s, int k) {
        int ans = 0, v = 0;
        for (int i = s.size() - 1; ~i; --i) {
            if (s[i] == '0') {
                ++ans;
            } else if (ans < 30 && (v | 1 << ans) <= k) {
                v |= 1 << ans;
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestSubsequence(s string, k int) (ans int) {
	for i, v := len(s)-1, 0; i >= 0; i-- {
		if s[i] == '0' {
			ans++
		} else if ans < 30 && (v|1<<ans) <= k {
			v |= 1 << ans
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function longestSubsequence(s: string, k: number): number {
    let ans = 0;
    for (let i = s.length - 1, v = 0; ~i; --i) {
        if (s[i] == '0') {
            ++ans;
        } else if (ans < 30 && (v | (1 << ans)) <= k) {
            v |= 1 << ans;
            ++ans;
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {number} k
 * @return {number}
 */
var longestSubsequence = function (s, k) {
    let ans = 0;
    for (let i = s.length - 1, v = 0; ~i; --i) {
        if (s[i] == '0') {
            ++ans;
        } else if (ans < 30 && (v | (1 << ans)) <= k) {
            v |= 1 << ans;
            ++ans;
        }
    }
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int LongestSubsequence(string s, int k) {
        int ans = 0, v = 0;
        for (int i = s.Length - 1; i >= 0; --i) {
            if (s[i] == '0') {
                ++ans;
            } else if (ans < 30 && (v | 1 << ans) <= k) {
                v |= 1 << ans;
                ++ans;
            }
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
