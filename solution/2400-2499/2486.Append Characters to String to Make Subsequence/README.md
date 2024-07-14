---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2400-2499/2486.Append%20Characters%20to%20String%20to%20Make%20Subsequence/README.md
rating: 1362
source: 第 321 场周赛 Q2
tags:
    - 贪心
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [2486. 追加字符以获得子序列](https://leetcode.cn/problems/append-characters-to-string-to-make-subsequence)

[English Version](/solution/2400-2499/2486.Append%20Characters%20to%20String%20to%20Make%20Subsequence/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个仅由小写英文字母组成的字符串 <code>s</code> 和 <code>t</code> 。</p>

<p>现在需要通过向 <code>s</code> 末尾追加字符的方式使 <code>t</code> 变成 <code>s</code> 的一个 <strong>子序列</strong> ，返回需要追加的最少字符数。</p>

<p>子序列是一个可以由其他字符串删除部分（或不删除）字符但不改变剩下字符顺序得到的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "coaching", t = "coding"
<strong>输出：</strong>4
<strong>解释：</strong>向 s 末尾追加字符串 "ding" ，s = "coachingding" 。
现在，t 是 s ("<em><strong>co</strong></em>aching<em><strong>ding</strong></em>") 的一个子序列。
可以证明向 s 末尾追加任何 3 个字符都无法使 t 成为 s 的一个子序列。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abcde", t = "a"
<strong>输出：</strong>0
<strong>解释：</strong>t 已经是 s ("<em><strong>a</strong></em>bcde") 的一个子序列。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "z", t = "abcde"
<strong>输出：</strong>5
<strong>解释：</strong>向 s 末尾追加字符串 "abcde" ，s = "zabcde" 。
现在，t 是 s ("z<em><strong>abcde</strong></em>") 的一个子序列。 
可以证明向 s 末尾追加任何 4 个字符都无法使 t 成为 s 的一个子序列。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, t.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> 和 <code>t</code> 仅由小写英文字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们定义两个指针 $i$ 和 $j$，分别指向字符串 $s$ 和 $t$ 的首字符。遍历字符串 $s$，如果 $s[i] = t[j]$，则将 $j$ 向后移动一位。最终返回 $n - j$，其中 $n$ 是字符串 $t$ 的长度。

时间复杂度 $(m + n)$，其中 $m$ 和 $n$ 分别是字符串 $s$ 和 $t$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def appendCharacters(self, s: str, t: str) -> int:
        n, j = len(t), 0
        for c in s:
            if j < n and c == t[j]:
                j += 1
        return n - j
```

#### Java

```java
class Solution {
    public int appendCharacters(String s, String t) {
        int n = t.length(), j = 0;
        for (int i = 0; i < s.length() && j < n; ++i) {
            if (s.charAt(i) == t.charAt(j)) {
                ++j;
            }
        }
        return n - j;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int appendCharacters(string s, string t) {
        int n = t.length(), j = 0;
        for (int i = 0; i < s.size() && j < n; ++i) {
            if (s[i] == t[j]) {
                ++j;
            }
        }
        return n - j;
    }
};
```

#### Go

```go
func appendCharacters(s string, t string) int {
	n, j := len(t), 0
	for _, c := range s {
		if j < n && byte(c) == t[j] {
			j++
		}
	}
	return n - j
}
```

#### TypeScript

```ts
function appendCharacters(s: string, t: string): number {
    let j = 0;
    for (const c of s) {
        if (c === t[j]) {
            ++j;
        }
    }
    return t.length - j;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
