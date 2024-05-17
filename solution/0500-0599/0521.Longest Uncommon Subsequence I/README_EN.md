---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0521.Longest%20Uncommon%20Subsequence%20I/README_EN.md
tags:
    - String
---

<!-- problem:start -->

# [521. Longest Uncommon Subsequence I](https://leetcode.com/problems/longest-uncommon-subsequence-i)

[中文文档](/solution/0500-0599/0521.Longest%20Uncommon%20Subsequence%20I/README.md)

## Description

<!-- description:start -->

<p>Given two strings <code>a</code> and <code>b</code>, return <em>the length of the <strong>longest uncommon subsequence</strong> between </em><code>a</code> <em>and</em> <code>b</code>. <em>If no such uncommon subsequence exists, return</em> <code>-1</code><em>.</em></p>

<p>An <strong>uncommon subsequence</strong> between two strings is a string that is a <strong><span data-keyword="subsequence-string">subsequence</span> of exactly one of them</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;aba&quot;, b = &quot;cdc&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> One longest uncommon subsequence is &quot;aba&quot; because &quot;aba&quot; is a subsequence of &quot;aba&quot; but not &quot;cdc&quot;.
Note that &quot;cdc&quot; is also a longest uncommon subsequence.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;aaa&quot;, b = &quot;bbb&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong>&nbsp;The longest uncommon subsequences are &quot;aaa&quot; and &quot;bbb&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = &quot;aaa&quot;, b = &quot;aaa&quot;
<strong>Output:</strong> -1
<strong>Explanation:</strong>&nbsp;Every subsequence of string a is also a subsequence of string b. Similarly, every subsequence of string b is also a subsequence of string a. So the answer would be <code>-1</code>.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 100</code></li>
	<li><code>a</code> and <code>b</code> consist of lower-case English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findLUSlength(self, a: str, b: str) -> int:
        return -1 if a == b else max(len(a), len(b))
```

#### Java

```java
class Solution {
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findLUSlength(string a, string b) {
        return a == b ? -1 : max(a.size(), b.size());
    }
};
```

#### Go

```go
func findLUSlength(a string, b string) int {
	if a == b {
		return -1
	}
	if len(a) > len(b) {
		return len(a)
	}
	return len(b)
}
```

#### TypeScript

```ts
function findLUSlength(a: string, b: string): number {
    return a != b ? Math.max(a.length, b.length) : -1;
}
```

#### Rust

```rust
impl Solution {
    pub fn find_lu_slength(a: String, b: String) -> i32 {
        if a == b {
            return -1;
        }
        a.len().max(b.len()) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
