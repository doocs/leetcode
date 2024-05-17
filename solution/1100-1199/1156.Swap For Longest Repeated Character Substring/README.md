---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1156.Swap%20For%20Longest%20Repeated%20Character%20Substring/README.md
rating: 1787
source: 第 149 场周赛 Q3
tags:
    - 哈希表
    - 字符串
    - 滑动窗口
---

<!-- problem:start -->

# [1156. 单字符重复子串的最大长度](https://leetcode.cn/problems/swap-for-longest-repeated-character-substring)

[English Version](/solution/1100-1199/1156.Swap%20For%20Longest%20Repeated%20Character%20Substring/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果字符串中的所有字符都相同，那么这个字符串是单字符重复的字符串。</p>

<p>给你一个字符串&nbsp;<code>text</code>，你只能交换其中两个字符一次或者什么都不做，然后得到一些单字符重复的子串。返回其中最长的子串的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>text = &quot;ababa&quot;
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>text = &quot;aaabaaa&quot;
<strong>输出：</strong>6
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>text = &quot;aaabbaaa&quot;
<strong>输出：</strong>4
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>text = &quot;aaaaa&quot;
<strong>输出：</strong>5
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>text = &quot;abcdef&quot;
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= text.length &lt;= 20000</code></li>
	<li><code>text</code> 仅由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们先用哈希表或数组 $cnt$ 统计字符串 $text$ 中每个字符出现的次数。

接下来，我们定义一个指针 $i$，初始时 $i = 0$。每一次，我们将指针 $j$ 指向 $i$，并不断地向右移动 $j$，直到 $j$ 指向的字符与 $i$ 指向的字符不同，此时我们得到了一个长度为 $l = j - i$ 的子串 $text[i..j-1]$，其中所有字符都相同。

然后我们跳过指针 $j$ 指向的字符，用指针 $k$ 继续向右移动，直到 $k$ 指向的字符与 $i$ 指向的字符不同，此时我们得到了一个长度为 $r = k - j - 1$ 的子串 $text[j+1..k-1]$，其中所有字符都相同。那么我们最多通过一次交换操作，可以得到的最长单字符重复子串的长度为 $\min(l + r + 1, cnt[text[i]])$。接下来，我们将指针 $i$ 移动到 $j$，继续寻找下一个子串。我们取所有满足条件的子串的最大长度即可。

时间复杂度为 $O(n)$，空间复杂度 $O(C)$。其中 $n$ 为字符串的长度；而 $C$ 为字符集的大小，本题中 $C = 26$。

<!-- tabs:start -->

```python
class Solution:
    def maxRepOpt1(self, text: str) -> int:
        cnt = Counter(text)
        n = len(text)
        ans = i = 0
        while i < n:
            j = i
            while j < n and text[j] == text[i]:
                j += 1
            l = j - i
            k = j + 1
            while k < n and text[k] == text[i]:
                k += 1
            r = k - j - 1
            ans = max(ans, min(l + r + 1, cnt[text[i]]))
            i = j
        return ans
```

```java
class Solution {
    public int maxRepOpt1(String text) {
        int[] cnt = new int[26];
        int n = text.length();
        for (int i = 0; i < n; ++i) {
            ++cnt[text.charAt(i) - 'a'];
        }
        int ans = 0, i = 0;
        while (i < n) {
            int j = i;
            while (j < n && text.charAt(j) == text.charAt(i)) {
                ++j;
            }
            int l = j - i;
            int k = j + 1;
            while (k < n && text.charAt(k) == text.charAt(i)) {
                ++k;
            }
            int r = k - j - 1;
            ans = Math.max(ans, Math.min(l + r + 1, cnt[text.charAt(i) - 'a']));
            i = j;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxRepOpt1(string text) {
        int cnt[26] = {0};
        for (char& c : text) {
            ++cnt[c - 'a'];
        }
        int n = text.size();
        int ans = 0, i = 0;
        while (i < n) {
            int j = i;
            while (j < n && text[j] == text[i]) {
                ++j;
            }
            int l = j - i;
            int k = j + 1;
            while (k < n && text[k] == text[i]) {
                ++k;
            }
            int r = k - j - 1;
            ans = max(ans, min(l + r + 1, cnt[text[i] - 'a']));
            i = j;
        }
        return ans;
    }
};
```

```go
func maxRepOpt1(text string) (ans int) {
	cnt := [26]int{}
	for _, c := range text {
		cnt[c-'a']++
	}
	n := len(text)
	for i, j := 0, 0; i < n; i = j {
		j = i
		for j < n && text[j] == text[i] {
			j++
		}
		l := j - i
		k := j + 1
		for k < n && text[k] == text[i] {
			k++
		}
		r := k - j - 1
		ans = max(ans, min(l+r+1, cnt[text[i]-'a']))
	}
	return
}
```

```ts
function maxRepOpt1(text: string): number {
    const idx = (c: string) => c.charCodeAt(0) - 'a'.charCodeAt(0);
    const cnt: number[] = new Array(26).fill(0);
    for (const c of text) {
        cnt[idx(c)]++;
    }
    let ans = 0;
    let i = 0;
    const n = text.length;
    while (i < n) {
        let j = i;
        while (j < n && text[j] === text[i]) {
            ++j;
        }
        const l = j - i;
        let k = j + 1;
        while (k < n && text[k] === text[i]) {
            ++k;
        }
        const r = k - j - 1;
        ans = Math.max(ans, Math.min(cnt[idx(text[i])], l + r + 1));
        i = j;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
