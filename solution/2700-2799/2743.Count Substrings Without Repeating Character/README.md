---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2743.Count%20Substrings%20Without%20Repeating%20Character/README.md
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [2743. 计算没有重复字符的子字符串数量 🔒](https://leetcode.cn/problems/count-substrings-without-repeating-character)

[English Version](/solution/2700-2799/2743.Count%20Substrings%20Without%20Repeating%20Character/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定你一个只包含小写英文字母的字符串 <code>s</code> 。如果一个子字符串不包含任何字符至少出现两次（换句话说，它不包含重复字符），则称其为 <strong>特殊</strong> 子字符串。你的任务是计算 <strong>特殊</strong> 子字符串的数量。例如，在字符串 <code>"pop"</code> 中，子串 <code>"po"</code> 是一个特殊子字符串，然而 <code>"pop"</code> 不是 <strong>特殊</strong> 子字符串（因为 <code>'p'</code> 出现了两次）。</p>

<p>返回 <strong>特殊</strong> 子字符串的数量。</p>

<p><strong>子字符串</strong> 是指字符串中连续的字符序列。例如，<code>"abc"</code> 是 <code>"abcd"</code> 的一个子字符串，但 <code>"acd"</code> 不是。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>s = "abcd"
<b>输出：</b>10
<b>解释：</b>由于每个字符只出现一次，每个子串都是特殊子串。长度为 1 的子串有 4 个，长度为 2 的有 3 个，长度为 3 的有 2 个，长度为 4 的有 1 个。所以一共有 4 + 3 + 2 + 1 = 10 个特殊子串。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>s = "ooo"
<b>输出：</b>3
<b>解释：</b>任何长度至少为 2 的子串都包含重复字符。所以我们要计算长度为 1 的子串的数量，即 3 个。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>s = "abab"
<b>输出：</b>7
<b>解释：</b>特殊子串如下（按起始位置排序）： 
长度为 1 的特殊子串："a", "b", "a", "b" 
长度为 2 的特殊子串："ab", "ba", "ab" 
并且可以证明没有长度至少为 3 的特殊子串。所以答案是4 + 3 = 7。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数 + 双指针

我们用两个指针 $j$ 和 $i$ 分别表示当前子串的左右边界，用一个长度为 $26$ 的数组 $cnt$ 统计当前子串中每个字符出现的次数。我们从左到右遍历字符串，每次遍历到位置 $i$ 时，将 $s[i]$ 出现的次数加一，然后判断 $s[i]$ 是否出现了至少两次，如果是，那么我们需要将 $s[j]$ 出现的次数减一，并将 $j$ 右移一位，直到 $s[i]$ 出现的次数不超过一次为止。这样一来，我们就得到以 $s[i]$ 结尾的最长特殊子串的长度，即 $i - j + 1$，那么以 $s[i]$ 结尾的特殊子串的数量就是 $i - j + 1$。最后我们将每个位置结尾的特殊子串的数量累加起来，即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 是字符串 $s$ 的长度；而 $C$ 是字符集的大小，这里字符集为小写英文字母，因此 $C = 26$。

<!-- tabs:start -->

```python
class Solution:
    def numberOfSpecialSubstrings(self, s: str) -> int:
        cnt = Counter()
        ans = j = 0
        for i, c in enumerate(s):
            cnt[c] += 1
            while cnt[c] > 1:
                cnt[s[j]] -= 1
                j += 1
            ans += i - j + 1
        return ans
```

```java
class Solution {
    public int numberOfSpecialSubstrings(String s) {
        int n = s.length();
        int ans = 0;
        int[] cnt = new int[26];
        for (int i = 0, j = 0; i < n; ++i) {
            int k = s.charAt(i) - 'a';
            ++cnt[k];
            while (cnt[k] > 1) {
                --cnt[s.charAt(j++) - 'a'];
            }
            ans += i - j + 1;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int numberOfSpecialSubstrings(string s) {
        int n = s.size();
        int cnt[26]{};
        int ans = 0;
        for (int i = 0, j = 0; i < n; ++i) {
            int k = s[i] - 'a';
            ++cnt[k];
            while (cnt[k] > 1) {
                --cnt[s[j++] - 'a'];
            }
            ans += i - j + 1;
        }
        return ans;
    }
};
```

```go
func numberOfSpecialSubstrings(s string) (ans int) {
	j := 0
	cnt := [26]int{}
	for i, c := range s {
		k := c - 'a'
		cnt[k]++
		for cnt[k] > 1 {
			cnt[s[j]-'a']--
			j++
		}
		ans += i - j + 1
	}
	return
}
```

```ts
function numberOfSpecialSubstrings(s: string): number {
    const idx = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const n = s.length;
    const cnt: number[] = Array(26).fill(0);
    let ans = 0;
    for (let i = 0, j = 0; i < n; ++i) {
        const k = idx(s[i]);
        ++cnt[k];
        while (cnt[k] > 1) {
            --cnt[idx(s[j++])];
        }
        ans += i - j + 1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
