---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0340.Longest%20Substring%20with%20At%20Most%20K%20Distinct%20Characters/README.md
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

我们可以使用滑动窗口的思想，用一个哈希表 $\textit{cnt}$ 记录窗口中每个字符的出现次数，用 $\textit{l}$ 记录窗口的左边界。

遍历字符串，每次将右边界的字符加入哈希表，如果哈希表中不同字符的个数超过了 $k$，则将左边界的字符从哈希表中删除，然后更新左边界 $\textit{l}$。

最后返回字符串的长度减去左边界的长度即可。

时间复杂度 $O(n)$，空间复杂度 $O(k)$。其中 $n$ 为字符串的长度。

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
