---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3329.Count%20Substrings%20With%20K-Frequency%20Characters%20II/README.md
tags:
    - 滑动窗口
---

<!-- problem:start -->

# [3329. 字符至少出现 K 次的子字符串 II 🔒](https://leetcode.cn/problems/count-substrings-with-k-frequency-characters-ii)

[English Version](/solution/3300-3399/3329.Count%20Substrings%20With%20K-Frequency%20Characters%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个整数 <code>k</code>，在 <code>s</code> 的所有 <span data-keyword="substring-nonempty">子字符串</span> 中，请你统计并返回 <strong>至少有一个 </strong>字符 <strong>至少出现</strong> <code>k</code> 次的子字符串总数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<div class="example-block">

<p><strong>输入：</strong> s = "abacb", k = 2</p>

<p><strong>输出：</strong> 4</p>

<p><strong>解释：</strong></p>

<p>符合条件的子字符串如下：</p>

<ul>
	<li><code>"aba"</code>（字符 <code>'a'</code> 出现 2 次）。</li>
	<li><code>"abac"</code>（字符 <code>'a'</code> 出现 2 次）。</li>
	<li><code>"abacb"</code>（字符 <code>'a'</code> 出现 2 次）。</li>
	<li><code>"bacb"</code>（字符 <code>'b'</code> 出现 2 次）。</li>
</ul>
</div>

<p><strong>示例 2：</strong></p>
<div class="example-block">

<p><strong>输入：</strong> s = "abcde", k = 1</p>

<p><strong>输出：</strong> 15</p>

<p><strong>解释：</strong></p>

<p>所有子字符串都有效，因为每个字符至少出现一次。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code> 仅由小写英文字母组成。</li>
</ul>

<p>&nbsp;</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：滑动窗口

我们可以枚举子字符串的右端点，然后用一个滑动窗口维护子字符串的左端点，使得滑动窗口内的子字符串中的每个字符出现次数都小于 $k$。

我们可以用一个数组 $\textit{cnt}$ 维护滑动窗口内的每个字符的出现次数，然后用一个变量 $\textit{l}$ 维护滑动窗口的左端点，用一个变量 $\textit{ans}$ 维护答案。

当我们枚举右端点时，我们可以将右端点的字符加入滑动窗口，然后判断滑动窗口内右端点的字符出现次数是否大于等于 $k$，如果是，则将左端点的字符移出滑动窗口，直到滑动窗口内的每个字符出现次数都小于 $k$。此时，对于左端点为 $[0, ..l - 1]$，且右端点为 $r$ 的子字符串，都满足题目要求，因此答案加上 $l$。

枚举结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(|\Sigma|)$，其中 $\Sigma$ 是字符集，这里是小写字母集合，因此 $|\Sigma| = 26$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfSubstrings(self, s: str, k: int) -> int:
        cnt = Counter()
        ans = l = 0
        for c in s:
            cnt[c] += 1
            while cnt[c] >= k:
                cnt[s[l]] -= 1
                l += 1
            ans += l
        return ans
```

#### Java

```java
class Solution {
    public long numberOfSubstrings(String s, int k) {
        int[] cnt = new int[26];
        long ans = 0;
        for (int l = 0, r = 0; r < s.length(); ++r) {
            int c = s.charAt(r) - 'a';
            ++cnt[c];
            while (cnt[c] >= k) {
                --cnt[s.charAt(l) - 'a'];
                l++;
            }
            ans += l;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long numberOfSubstrings(string s, int k) {
        int n = s.size();
        long long ans = 0, l = 0;
        int cnt[26]{};
        for (char& c : s) {
            ++cnt[c - 'a'];
            while (cnt[c - 'a'] >= k) {
                --cnt[s[l++] - 'a'];
            }
            ans += l;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfSubstrings(s string, k int) (ans int64) {
	l := 0
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
		for cnt[c-'a'] >= k {
			cnt[s[l]-'a']--
			l++
		}
		ans += int64(l)
	}
	return
}
```

#### TypeScript

```ts
function numberOfSubstrings(s: string, k: number): number {
    let [ans, l] = [0, 0];
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        const x = c.charCodeAt(0) - 97;
        ++cnt[x];
        while (cnt[x] >= k) {
            --cnt[s[l++].charCodeAt(0) - 97];
        }
        ans += l;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
