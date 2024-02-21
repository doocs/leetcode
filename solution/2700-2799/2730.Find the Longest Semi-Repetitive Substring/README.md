# [2730. 找到最长的半重复子字符串](https://leetcode.cn/problems/find-the-longest-semi-repetitive-substring)

[English Version](/solution/2700-2799/2730.Find%20the%20Longest%20Semi-Repetitive%20Substring/README_EN.md)

<!-- tags:字符串,滑动窗口 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串&nbsp;<code>s</code>&nbsp;，这个字符串只包含&nbsp;<code>0</code>&nbsp;到&nbsp;<code>9</code>&nbsp;的数字字符。</p>

<p>如果一个字符串&nbsp;<code>t</code>&nbsp;中至多有一对相邻字符是相等的，那么称这个字符串 <code>t</code> 是 <strong>半重复的</strong>&nbsp;。例如，<code>0010</code> 、<code>002020</code> 、<code>0123</code> 、<code>2002</code> 和 <code>54944</code> 是半重复字符串，而 <code>00101022</code> 和 <code>1101234883</code> 不是。</p>

<p>请你返回 <code>s</code>&nbsp;中最长 <strong>半重复</strong>&nbsp;子字符串的长度。</p>

<p>一个 <strong>子字符串</strong>&nbsp;是一个字符串中一段连续 <strong>非空</strong>&nbsp;的字符。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "52233"
<b>输出：</b>4
<b>解释：</b>最长半重复子字符串是 "5223" ，子字符串从 i = 0 开始，在 j = 3 处结束。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "5494"
<b>输出：</b>4
<b>解释：</b>s 就是一个半重复字符串，所以答案为 4 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>s = "1111111"
<b>输出：</b>2
<b>解释：</b>最长半重复子字符串是 "11" ，子字符串从 i = 0 开始，在 j = 1 处结束。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 50</code></li>
	<li><code>'0' &lt;= s[i] &lt;= '9'</code></li>
</ul>

## 解法

### 方法一：双指针

我们用双指针维护一个区间 $s[j..i]$，使得区间内最多只有一个相邻字符相等。我们用 $cnt$ 记录区间内相邻字符相等的个数，如果 $cnt \gt 1$，那么我们就需要移动左指针 $j$，直到 $cnt \le 1$。每一次，我们更新答案为 $ans = \max(ans, i - j + 1)$。

时间复杂度 $O(n)$，其中 $n$ 是字符串的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def longestSemiRepetitiveSubstring(self, s: str) -> int:
        n = len(s)
        ans = cnt = j = 0
        for i in range(n):
            if i and s[i] == s[i - 1]:
                cnt += 1
            while cnt > 1:
                if s[j] == s[j + 1]:
                    cnt -= 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```java
class Solution {
    public int longestSemiRepetitiveSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0, j = 0, cnt = 0; i < n; ++i) {
            if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
                ++cnt;
            }
            while (cnt > 1) {
                if (s.charAt(j) == s.charAt(j + 1)) {
                    --cnt;
                }
                ++j;
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int longestSemiRepetitiveSubstring(string s) {
        int n = s.size();
        int ans = 0;
        for (int i = 0, j = 0, cnt = 0; i < n; ++i) {
            if (i && s[i] == s[i - 1]) {
                ++cnt;
            }
            while (cnt > 1) {
                if (s[j] == s[j + 1]) {
                    --cnt;
                }
                ++j;
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```go
func longestSemiRepetitiveSubstring(s string) (ans int) {
	n := len(s)
	for i, j, cnt := 0, 0, 0; i < n; i++ {
		if i > 0 && s[i] == s[i-1] {
			cnt++
		}
		for cnt > 1 {
			if s[j] == s[j+1] {
				cnt--
			}
			j++
		}
		ans = max(ans, i-j+1)
	}
	return
}
```

```ts
function longestSemiRepetitiveSubstring(s: string): number {
    const n = s.length;
    let ans = 0;
    for (let i = 0, j = 0, cnt = 0; i < n; ++i) {
        if (i > 0 && s[i] === s[i - 1]) {
            ++cnt;
        }
        while (cnt > 1) {
            if (s[j] === s[j + 1]) {
                --cnt;
            }
            ++j;
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
