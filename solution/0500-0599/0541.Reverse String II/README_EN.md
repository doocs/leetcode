---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0541.Reverse%20String%20II/README_EN.md
tags:
    - Two Pointers
    - String
---

<!-- problem:start -->

# [541. Reverse String II](https://leetcode.com/problems/reverse-string-ii)

[中文文档](/solution/0500-0599/0541.Reverse%20String%20II/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>s</code> and an integer <code>k</code>, reverse the first <code>k</code> characters for every <code>2k</code> characters counting from the start of the string.</p>

<p>If there are fewer than <code>k</code> characters left, reverse all of them. If there are less than <code>2k</code> but greater than or equal to <code>k</code> characters, then reverse the first <code>k</code> characters and leave the other as original.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> s = "abcdefg", k = 2
<strong>Output:</strong> "bacdfeg"
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> s = "abcd", k = 2
<strong>Output:</strong> "bacd"
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We can traverse the string $\textit{s}$, iterating over every $\textit{2k}$ characters, and then use the two-pointer technique to reverse the first $\textit{k}$ characters among these $\textit{2k}$ characters.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the string $\textit{s}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseStr(self, s: str, k: int) -> str:
        cs = list(s)
        for i in range(0, len(cs), 2 * k):
            cs[i : i + k] = reversed(cs[i : i + k])
        return "".join(cs)
```

#### Java

```java
class Solution {
    public String reverseStr(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; i += k * 2) {
            for (int l = i, r = Math.min(i + k - 1, n - 1); l < r; ++l, --r) {
                char t = cs[l];
                cs[l] = cs[r];
                cs[r] = t;
            }
        }
        return new String(cs);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reverseStr(string s, int k) {
        int n = s.size();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(s.begin() + i, s.begin() + min(i + k, n));
        }
        return s;
    }
};
```

#### Go

```go
func reverseStr(s string, k int) string {
	cs := []byte(s)
	n := len(cs)
	for i := 0; i < n; i += 2 * k {
		for l, r := i, min(i+k-1, n-1); l < r; l, r = l+1, r-1 {
			cs[l], cs[r] = cs[r], cs[l]
		}
	}
	return string(cs)
}
```

#### TypeScript

```ts
function reverseStr(s: string, k: number): string {
    const n = s.length;
    const cs = s.split('');
    for (let i = 0; i < n; i += 2 * k) {
        for (let l = i, r = Math.min(i + k - 1, n - 1); l < r; l++, r--) {
            [cs[l], cs[r]] = [cs[r], cs[l]];
        }
    }
    return cs.join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
