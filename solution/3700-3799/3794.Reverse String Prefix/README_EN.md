---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3794.Reverse%20String%20Prefix/README_EN.md
rating: 1229
source: Biweekly Contest 173 Q1
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [3794. Reverse String Prefix](https://leetcode.com/problems/reverse-string-prefix)

[中文文档](/solution/3700-3799/3794.Reverse%20String%20Prefix/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>k</code>.</p>

<p>Reverse the first <code>k</code> characters of <code>s</code> and return the resulting string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcd&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;bacd&quot;</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<p>The first <code>k = 2</code> characters <code>&quot;ab&quot;</code> are reversed to <code>&quot;ba&quot;</code>. The final resulting string is <code>&quot;bacd&quot;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;xyz&quot;, k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;zyx&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>The first <code>k = 3</code> characters <code>&quot;xyz&quot;</code> are reversed to <code>&quot;zyx&quot;</code>. The final resulting string is <code>&quot;zyx&quot;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;hey&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;hey&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>The first <code>k = 1</code> character <code>&quot;h&quot;</code> remains unchanged on reversal. The final resulting string is <code>&quot;hey&quot;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We reverse the first $k$ characters of the string according to the problem description, and then concatenate them with the remaining characters.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the string.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reversePrefix(self, s: str, k: int) -> str:
        return s[:k][::-1] + s[k:]
```

#### Java

```java
class Solution {
    public String reversePrefix(String s, int k) {
        StringBuilder sb = new StringBuilder(s.substring(0, k));
        return sb.reverse().toString() + s.substring(k);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reversePrefix(string s, int k) {
        string t = s.substr(0, k);
        reverse(t.begin(), t.end());
        return t + s.substr(k);
    }
};
```

#### Go

```go
func reversePrefix(s string, k int) string {
	t := []byte(s[:k])
	slices.Reverse(t)
	return string(t) + s[k:]
}
```

#### TypeScript

```ts
function reversePrefix(s: string, k: number): string {
    return s.slice(0, k).split('').reverse().join('') + s.slice(k);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
