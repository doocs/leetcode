---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0424.Longest%20Repeating%20Character%20Replacement/README.md
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [424. 替换后的最长重复字符](https://leetcode.cn/problems/longest-repeating-character-replacement)

[English Version](/solution/0400-0499/0424.Longest%20Repeating%20Character%20Replacement/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code> 。你可以选择字符串中的任一字符，并将其更改为任何其他大写英文字符。该操作最多可执行 <code>k</code> 次。</p>

<p>在执行上述操作后，返回 <em>包含相同字母的最长子字符串的长度。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "ABAB", k = 2
<strong>输出：</strong>4
<strong>解释：</strong>用两个'A'替换为两个'B',反之亦然。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "AABABBA", k = 1
<strong>输出：</strong>4
<strong>解释：</strong>
将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
子串 "BBBB" 有最长重复字母, 答案为 4。
可能存在其他的方法来得到同样的结果。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 仅由大写英文字母组成</li>
	<li><code>0 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们使用一个哈希表 `cnt` 统计字符串中每个字符出现的次数，使用双指针 `l` 和 `r` 维护一个滑动窗口，使得窗口的大小减去出现次数最多的字符的次数，结果不超过 $k$。

我们遍历字符串，每次更新窗口的右边界 `r`，并更新窗口内的字符出现次数，同时更新出现过的字符的最大出现次数 `mx`。当窗口的大小减去 `mx` 大于 $k$ 时，我们需要缩小窗口的左边界 `l`，同时更新窗口内的字符出现次数，直到窗口的大小减去 `mx` 不大于 $k$。

最后，答案为 $n - l$，其中 $n$ 为字符串的长度。

时间复杂度 $O(n)$，空间复杂度 $O(|\Sigma|)$。其中 $n$ 为字符串的长度，而 $|\Sigma|$ 为字符集的大小，本题中字符集为大写英文字母，所以 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        cnt = Counter()
        l = mx = 0
        for r, c in enumerate(s):
            cnt[c] += 1
            mx = max(mx, cnt[c])
            if r - l + 1 - mx > k:
                cnt[s[l]] -= 1
                l += 1
        return len(s) - l
```

#### Java

```java
class Solution {
    public int characterReplacement(String s, int k) {
        int[] cnt = new int[26];
        int l = 0, mx = 0;
        int n = s.length();
        for (int r = 0; r < n; ++r) {
            mx = Math.max(mx, ++cnt[s.charAt(r) - 'A']);
            if (r - l + 1 - mx > k) {
                --cnt[s.charAt(l++) - 'A'];
            }
        }
        return n - l;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int characterReplacement(string s, int k) {
        int cnt[26]{};
        int l = 0, mx = 0;
        int n = s.length();
        for (int r = 0; r < n; ++r) {
            mx = max(mx, ++cnt[s[r] - 'A']);
            if (r - l + 1 - mx > k) {
                --cnt[s[l++] - 'A'];
            }
        }
        return n - l;
    }
};
```

#### Go

```go
func characterReplacement(s string, k int) int {
	cnt := [26]int{}
	l, mx := 0, 0
	for r, c := range s {
		cnt[c-'A']++
		mx = max(mx, cnt[c-'A'])
		if r-l+1-mx > k {
			cnt[s[l]-'A']--
			l++
		}
	}
	return len(s) - l
}
```

#### TypeScript

```ts
function characterReplacement(s: string, k: number): number {
    const idx = (c: string) => c.charCodeAt(0) - 65;
    const cnt: number[] = Array(26).fill(0);
    const n = s.length;
    let [l, mx] = [0, 0];
    for (let r = 0; r < n; ++r) {
        mx = Math.max(mx, ++cnt[idx(s[r])]);
        if (r - l + 1 - mx > k) {
            --cnt[idx(s[l++])];
        }
    }
    return n - l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
