---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1328.Break%20a%20Palindrome/README_EN.md
rating: 1473
tags:
    - Greedy
    - String
---

# [1328. Break a Palindrome](https://leetcode.com/problems/break-a-palindrome)

[中文文档](/solution/1300-1399/1328.Break%20a%20Palindrome/README.md)

## Description

<p>Given a palindromic string of lowercase English letters <code>palindrome</code>, replace <strong>exactly one</strong> character with any lowercase English letter so that the resulting string is <strong>not</strong> a palindrome and that it is the <strong>lexicographically smallest</strong> one possible.</p>

<p>Return <em>the resulting string. If there is no way to replace a character to make it not a palindrome, return an <strong>empty string</strong>.</em></p>

<p>A string <code>a</code> is lexicographically smaller than a string <code>b</code> (of the same length) if in the first position where <code>a</code> and <code>b</code> differ, <code>a</code> has a character strictly smaller than the corresponding character in <code>b</code>. For example, <code>&quot;abcc&quot;</code> is lexicographically smaller than <code>&quot;abcd&quot;</code> because the first position they differ is at the fourth character, and <code>&#39;c&#39;</code> is smaller than <code>&#39;d&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> palindrome = &quot;abccba&quot;
<strong>Output:</strong> &quot;aaccba&quot;
<strong>Explanation:</strong> There are many ways to make &quot;abccba&quot; not a palindrome, such as &quot;<u>z</u>bccba&quot;, &quot;a<u>a</u>ccba&quot;, and &quot;ab<u>a</u>cba&quot;.
Of all the ways, &quot;aaccba&quot; is the lexicographically smallest.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> palindrome = &quot;a&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> There is no way to replace a single character to make &quot;a&quot; not a palindrome, so return an empty string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= palindrome.length &lt;= 1000</code></li>
	<li><code>palindrome</code> consists of only lowercase English letters.</li>
</ul>

## Solutions

### Solution 1

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
