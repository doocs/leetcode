---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1328.Break%20a%20Palindrome/README.md
rating: 1473
source: 第 18 场双周赛 Q2
tags:
    - 贪心
    - 字符串
---

# [1328. 破坏回文串](https://leetcode.cn/problems/break-a-palindrome)

[English Version](/solution/1300-1399/1328.Break%20a%20Palindrome/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由小写英文字母组成的回文字符串&nbsp;<code>palindrome</code> ，请你将其中&nbsp;<strong>一个</strong> 字符用任意小写英文字母替换，使得结果字符串的 <strong>字典序最小</strong> ，且&nbsp;<strong>不是</strong>&nbsp;回文串。</p>

<p>请你返回结果字符串。如果无法做到，则返回一个 <strong>空串</strong> 。</p>

<p>如果两个字符串长度相同，那么字符串 <code>a</code> 字典序比字符串 <code>b</code> 小可以这样定义：在 <code>a</code> 和 <code>b</code> 出现不同的第一个位置上，字符串 <code>a</code> 中的字符严格小于 <code>b</code> 中的对应字符。例如，<code>"abcc”</code> 字典序比 <code>"abcd"</code> 小，因为不同的第一个位置是在第四个字符，显然 <code>'c'</code> 比 <code>'d'</code> 小。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>palindrome = "abccba"
<strong>输出：</strong>"aaccba"
<strong>解释：</strong>存在多种方法可以使 "abccba" 不是回文，例如 "<em><strong>z</strong></em>bccba", "a<em><strong>a</strong></em>ccba", 和 "ab<em><strong>a</strong></em>cba" 。
在所有方法中，"aaccba" 的字典序最小。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>palindrome = "a"
<strong>输出：</strong>""
<strong>解释：</strong>不存在替换一个字符使 "a" 变成非回文的方法，所以返回空字符串。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= palindrome.length &lt;= 1000</code></li>
	<li><code>palindrome</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

### 方法一：贪心

我们先判断字符串的长度是否为 $1$，若是则直接返回空串。

否则，我们从左到右遍历字符串的前半部分，找到第一个不为 `'a'` 的字符，将其改为 `'a'` 即可。如果不存在这样的字符，那么我们将最后一个字符改为 `'b'` 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串的长度。

<!-- tabs:start -->

```python
class Solution:
    def breakPalindrome(self, palindrome: str) -> str:
        n = len(palindrome)
        if n == 1:
            return ""
        s = list(palindrome)
        i = 0
        while i < n // 2 and s[i] == "a":
            i += 1
        if i == n // 2:
            s[-1] = "b"
        else:
            s[i] = "a"
        return "".join(s)
```

```java
class Solution {
    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if (n == 1) {
            return "";
        }
        char[] cs = palindrome.toCharArray();
        int i = 0;
        while (i < n / 2 && cs[i] == 'a') {
            ++i;
        }
        if (i == n / 2) {
            cs[n - 1] = 'b';
        } else {
            cs[i] = 'a';
        }
        return String.valueOf(cs);
    }
}
```

```cpp
class Solution {
public:
    string breakPalindrome(string palindrome) {
        int n = palindrome.size();
        if (n == 1) {
            return "";
        }
        int i = 0;
        while (i < n / 2 && palindrome[i] == 'a') {
            ++i;
        }
        if (i == n / 2) {
            palindrome[n - 1] = 'b';
        } else {
            palindrome[i] = 'a';
        }
        return palindrome;
    }
};
```

```go
func breakPalindrome(palindrome string) string {
	n := len(palindrome)
	if n == 1 {
		return ""
	}
	i := 0
	s := []byte(palindrome)
	for i < n/2 && s[i] == 'a' {
		i++
	}
	if i == n/2 {
		s[n-1] = 'b'
	} else {
		s[i] = 'a'
	}
	return string(s)
}
```

```ts
function breakPalindrome(palindrome: string): string {
    const n = palindrome.length;
    if (n === 1) {
        return '';
    }
    const s = palindrome.split('');
    let i = 0;
    while (i < n >> 1 && s[i] === 'a') {
        i++;
    }
    if (i == n >> 1) {
        s[n - 1] = 'b';
    } else {
        s[i] = 'a';
    }
    return s.join('');
}
```

<!-- tabs:end -->

<!-- end -->
