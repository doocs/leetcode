---
comments: true
difficulty: 中等
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [340. 至多包含 K 个不同字符的最长子串 🔒](https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters)

[English Version](/solution/0300-0399/0340.Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> ，请你找出&nbsp;<strong>至多&nbsp;</strong>包含<em> <code>k</code></em> 个 <strong>不同</strong> 字符的最长<span data-keyword="substring-nonempty">子串</span>，并返回该子串的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "eceba", k = 2
<strong>输出：</strong>3
<strong>解释：</strong>满足题目要求的子串是 "ece" ，长度为 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aa", k = 1
<strong>输出：</strong>2
<strong>解释：</strong>满足题目要求的子串是 "aa" ，长度为 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口 + 哈希表

<!-- thinking:start -->

> **思考**
>
> 枚举每个起点，再向右扩展并统计不同字符，需要 $O(n^2)$ 时间。为避免重复枚举，可以用哈希表维护滑动窗口中的字符计数。
>
> 题目只求最长长度，所以我们只尝试将已找到的最优长度增加 $1$。右端纳入字符后，若种类数超过 $k$，本次长度就无法增长，只将左端右移一次以保持原长度。这样，窗口可以暂时不合法，但其长度始终等于已遍历前缀中的最优值。

<!-- thinking:end -->

我们维护一个长度单调不减的滑动窗口，用一个哈希表 $\textit{cnt}$ 记录窗口中每个字符的出现次数，用 $l$ 记录窗口的左边界。

遍历字符串，每次将右边界的字符计数加 $1$。如果哈希表中不同字符的个数超过了 $k$，则将左边界的字符计数减 $1$，计数为 $0$ 时删除该字符，并将 $l$ 右移一次。这里使用单次 `if`，每轮最多移动一次左边界，因此窗口长度只会增加 $1$ 或保持不变，而窗口中的不同字符数可能仍然超过 $k$。

下面证明每轮结束时，窗口长度等于已遍历前缀中的最长合法子串长度。设上一轮的窗口长度和最优值均为 $L$。加入一个字符后，最优值至多为 $L+1$，因为任何以新字符结尾的合法子串删去末字符后仍然合法。此时窗口恰好是唯一一个以新字符结尾、长度为 $L+1$ 的子串：若它合法，最优值和窗口长度都增加到 $L+1$；否则最优值仍为 $L$，左边界右移一次也使窗口长度保持为 $L$。从空前缀开始归纳可知，最后返回 $n-l$ 即为答案，其中 $n$ 为字符串长度。

例如，字符串为 `eceba` 且 $k=2$ 时，每轮结束后的窗口依次为 `e`、`ec`、`ece`、`ceb`、`eba`，长度依次为 $1,2,3,3,3$。最后两个窗口虽然不合法，但长度仍然记录着合法子串 `ece` 的长度 $3$。当 $k=0$ 时，每轮加入的字符都会立即被移除，答案为 $0$。

假设哈希表操作的平均时间为 $O(1)$，时间复杂度为 $O(n)$。哈希表的空间复杂度为 $O(\min(n, |\Sigma|))$，其中 $\Sigma$ 表示字符集；字符集固定时，哈希表占用 $O(1)$ 空间。

空间不能一般地写成 $O(k)$：例如 $k=1$ 时，先读入 $m$ 个相同字符，再读入 $m$ 个互不相同且不同于前者的字符，最终窗口包含 $m$ 种字符。此外，Java 实现中的 `toCharArray()` 还需要 $O(n)$ 的额外空间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lengthOfLongestSubstringKDistinct(self, s: str, k: int) -> int:
        l = 0
        cnt = Counter()
        for c in s:
            cnt[c] += 1
            if len(cnt) > k:
                cnt[s[l]] -= 1
                if cnt[s[l]] == 0:
                    del cnt[s[l]]
                l += 1
        return len(s) - l
```

#### Java

```java
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> cnt = new HashMap<>();
        int l = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            cnt.merge(c, 1, Integer::sum);
            if (cnt.size() > k) {
                if (cnt.merge(cs[l], -1, Integer::sum) == 0) {
                    cnt.remove(cs[l]);
                }
                ++l;
            }
        }
        return cs.length - l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int lengthOfLongestSubstringKDistinct(string s, int k) {
        unordered_map<char, int> cnt;
        int l = 0;
        for (char& c : s) {
            ++cnt[c];
            if (cnt.size() > k) {
                if (--cnt[s[l]] == 0) {
                    cnt.erase(s[l]);
                }
                ++l;
            }
        }
        return s.size() - l;
    }
};
```

#### Go

```go
func lengthOfLongestSubstringKDistinct(s string, k int) int {
	cnt := map[byte]int{}
	l := 0
	for _, c := range s {
		cnt[byte(c)]++
		if len(cnt) > k {
			cnt[s[l]]--
			if cnt[s[l]] == 0 {
				delete(cnt, s[l])
			}
			l++
		}
	}
	return len(s) - l
}
```

#### TypeScript

```ts
function lengthOfLongestSubstringKDistinct(s: string, k: number): number {
    const cnt: Map<string, number> = new Map();
    let l = 0;
    for (const c of s) {
        cnt.set(c, (cnt.get(c) ?? 0) + 1);
        if (cnt.size > k) {
            cnt.set(s[l], cnt.get(s[l])! - 1);
            if (cnt.get(s[l]) === 0) {
                cnt.delete(s[l]);
            }
            l++;
        }
    }
    return s.length - l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
