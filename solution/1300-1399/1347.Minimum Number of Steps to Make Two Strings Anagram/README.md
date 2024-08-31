---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1347.Minimum%20Number%20of%20Steps%20to%20Make%20Two%20Strings%20Anagram/README.md
rating: 1330
source: 第 175 场周赛 Q2
tags:
    - 哈希表
    - 字符串
    - 计数
---

<!-- problem:start -->

# [1347. 制造字母异位词的最小步骤数](https://leetcode.cn/problems/minimum-number-of-steps-to-make-two-strings-anagram)

[English Version](/solution/1300-1399/1347.Minimum%20Number%20of%20Steps%20to%20Make%20Two%20Strings%20Anagram/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度相等的字符串&nbsp;<code>s</code> 和 <code>t</code>。每一个步骤中，你可以选择将&nbsp;<code>t</code>&nbsp;中的 <strong>任一字符</strong> 替换为 <strong>另一个字符</strong>。</p>

<p>返回使&nbsp;<code>t</code>&nbsp;成为&nbsp;<code>s</code>&nbsp;的字母异位词的最小步骤数。</p>

<p><strong>字母异位词</strong> 指字母相同，但排列不同（也可能相同）的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输出：</strong>s = &quot;bab&quot;, t = &quot;aba&quot;
<strong>输出：</strong>1
<strong>提示：</strong>用 &#39;b&#39; 替换 t 中的第一个 &#39;a&#39;，t = &quot;bba&quot; 是 s 的一个字母异位词。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输出：</strong>s = &quot;leetcode&quot;, t = &quot;practice&quot;
<strong>输出：</strong>5
<strong>提示：</strong>用合适的字符替换 t 中的 &#39;p&#39;, &#39;r&#39;, &#39;a&#39;, &#39;i&#39; 和 &#39;c&#39;，使 t 变成 s 的字母异位词。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输出：</strong>s = &quot;anagram&quot;, t = &quot;mangaar&quot;
<strong>输出：</strong>0
<strong>提示：</strong>&quot;anagram&quot; 和 &quot;mangaar&quot; 本身就是一组字母异位词。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输出：</strong>s = &quot;xxyyzz&quot;, t = &quot;xxyyzz&quot;
<strong>输出：</strong>0
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输出：</strong>s = &quot;friend&quot;, t = &quot;family&quot;
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50000</code></li>
	<li><code>s.length == t.length</code></li>
	<li><code>s</code> 和 <code>t</code>&nbsp;只包含小写英文字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们可以使用一个哈希表或者数组 $\textit{cnt}$ 来统计字符串 $\textit{s}$ 中每个字符出现的次数，然后遍历字符串 $\textit{t}$，对于每个字符，我们在 $\textit{cnt}$ 中将其出现的次数减一，如果减一后的值小于 $0$，说明这个字符在字符串 $\textit{t}$ 中出现的次数比在字符串 $\textit{s}$ 中多，我们需要将这个字符替换掉，将答案加一。

遍历结束后，返回答案即可。

时间复杂度 $O(m + n)$，空间复杂度 $O(|\Sigma|)$，其中 $m$ 和 $n$ 分别是字符串 $\textit{s}$ 和 $\textit{t}$ 的长度，而 $|\Sigma|$ 是字符集的大小，本题中字符集为小写字母，因此 $|\Sigma| = 26$。

#### Python3

```python
class Solution:
    def minSteps(self, s: str, t: str) -> int:
        cnt = Counter(s)
        ans = 0
        for c in t:
            cnt[c] -= 1
            ans += cnt[c] < 0
        return ans
```

#### Java

```java
class Solution {
    public int minSteps(String s, String t) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        int ans = 0;
        for (char c : t.toCharArray()) {
            if (--cnt[c - 'a'] < 0) {
                ans++;
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
    int minSteps(string s, string t) {
        int cnt[26]{};
        for (char c : s) {
            ++cnt[c - 'a'];
        }
        int ans = 0;
        for (char c : t) {
            if (--cnt[c - 'a'] < 0) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minSteps(s string, t string) (ans int) {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for _, c := range t {
		cnt[c-'a']--
		if cnt[c-'a'] < 0 {
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function minSteps(s: string, t: string): number {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    let ans = 0;
    for (const c of t) {
        if (--cnt[c.charCodeAt(0) - 97] < 0) {
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
 * @param {string} t
 * @return {number}
 */
var minSteps = function (s, t) {
    const cnt = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    let ans = 0;
    for (const c of t) {
        if (--cnt[c.charCodeAt(0) - 97] < 0) {
            ++ans;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
