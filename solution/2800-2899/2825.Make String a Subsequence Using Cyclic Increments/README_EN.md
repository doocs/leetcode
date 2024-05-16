---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2825.Make%20String%20a%20Subsequence%20Using%20Cyclic%20Increments/README_EN.md
rating: 1414
source: Biweekly Contest 111 Q2
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [2825. Make String a Subsequence Using Cyclic Increments](https://leetcode.com/problems/make-string-a-subsequence-using-cyclic-increments)

[中文文档](/solution/2800-2899/2825.Make%20String%20a%20Subsequence%20Using%20Cyclic%20Increments/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> strings <code>str1</code> and <code>str2</code>.</p>

<p>In an operation, you select a <strong>set</strong> of indices in <code>str1</code>, and for each index <code>i</code> in the set, increment <code>str1[i]</code> to the next character <strong>cyclically</strong>. That is <code>&#39;a&#39;</code> becomes <code>&#39;b&#39;</code>, <code>&#39;b&#39;</code> becomes <code>&#39;c&#39;</code>, and so on, and <code>&#39;z&#39;</code> becomes <code>&#39;a&#39;</code>.</p>

<p>Return <code>true</code> <em>if it is possible to make </em><code>str2</code> <em>a subsequence of </em><code>str1</code> <em>by performing the operation <strong>at most once</strong></em>, <em>and</em> <code>false</code> <em>otherwise</em>.</p>

<p><strong>Note:</strong> A subsequence of a string is a new string that is formed from the original string by deleting some (possibly none) of the characters without disturbing the relative positions of the remaining characters.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> str1 = &quot;abc&quot;, str2 = &quot;ad&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Select index 2 in str1.
Increment str1[2] to become &#39;d&#39;. 
Hence, str1 becomes &quot;abd&quot; and str2 is now a subsequence. Therefore, true is returned.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> str1 = &quot;zc&quot;, str2 = &quot;ad&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Select indices 0 and 1 in str1. 
Increment str1[0] to become &#39;a&#39;. 
Increment str1[1] to become &#39;d&#39;. 
Hence, str1 becomes &quot;ad&quot; and str2 is now a subsequence. Therefore, true is returned.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> str1 = &quot;ab&quot;, str2 = &quot;d&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> In this example, it can be shown that it is impossible to make str2 a subsequence of str1 using the operation at most once. 
Therefore, false is returned.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= str1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= str2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>str1</code> and <code>str2</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

This problem actually requires us to determine whether a string $s$ is a subsequence of another string $t$. However, the characters do not have to match exactly. If two characters are the same, or one character is the next character of the other, they can match.

The time complexity is $O(m + n)$, where $m$ and $n$ are the lengths of the strings $str1$ and $str2$ respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def canMakeSubsequence(self, str1: str, str2: str) -> bool:
        i = 0
        for c in str1:
            d = "a" if c == "z" else chr(ord(c) + 1)
            if i < len(str2) and str2[i] in (c, d):
                i += 1
        return i == len(str2)
```

```java
class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int i = 0, n = str2.length();
        for (char c : str1.toCharArray()) {
            char d = c == 'z' ? 'a' : (char) (c + 1);
            if (i < n && (str2.charAt(i) == c || str2.charAt(i) == d)) {
                ++i;
            }
        }
        return i == n;
    }
}
```

```cpp
class Solution {
public:
    bool canMakeSubsequence(string str1, string str2) {
        int i = 0, n = str2.size();
        for (char c : str1) {
            char d = c == 'z' ? 'a' : c + 1;
            if (i < n && (str2[i] == c || str2[i] == d)) {
                ++i;
            }
        }
        return i == n;
    }
};
```

```go
func canMakeSubsequence(str1 string, str2 string) bool {
	i, n := 0, len(str2)
	for _, c := range str1 {
		d := byte('a')
		if c != 'z' {
			d = byte(c + 1)
		}
		if i < n && (str2[i] == byte(c) || str2[i] == d) {
			i++
		}
	}
	return i == n
}
```

```ts
function canMakeSubsequence(str1: string, str2: string): boolean {
    let i = 0;
    const n = str2.length;
    for (const c of str1) {
        const d = c === 'z' ? 'a' : String.fromCharCode(c.charCodeAt(0) + 1);
        if (i < n && (str2[i] === c || str2[i] === d)) {
            ++i;
        }
    }
    return i === n;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
