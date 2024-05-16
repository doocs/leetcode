---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0516.Longest%20Palindromic%20Subsequence/README_EN.md
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [516. Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence)

[中文文档](/solution/0500-0599/0516.Longest%20Palindromic%20Subsequence/README.md)

## Description

<p>Given a string <code>s</code>, find <em>the longest palindromic <strong>subsequence</strong>&#39;s length in</em> <code>s</code>.</p>

<p>A <strong>subsequence</strong> is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;bbbab&quot;
<strong>Output:</strong> 4
<strong>Explanation:</strong> One possible longest palindromic subsequence is &quot;bbbb&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cbbd&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> One possible longest palindromic subsequence is &quot;bb&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the length of the longest palindromic subsequence from the $i$-th character to the $j$-th character in string $s$. Initially, $f[i][i] = 1$, and the values of other positions are all $0$.

If $s[i] = s[j]$, then $f[i][j] = f[i + 1][j - 1] + 2$; otherwise, $f[i][j] = \max(f[i + 1][j], f[i][j - 1])$.

Since the value of $f[i][j]$ is related to $f[i + 1][j - 1]$, $f[i + 1][j]$, and $f[i][j - 1]$, we should enumerate $i$ from large to small, and enumerate $j$ from small to large.

The answer is $f[0][n - 1]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Where $n$ is the length of the string $s$.

<!-- tabs:start -->

```python
class Solution:
    def longestPalindromeSubseq(self, s: str) -> int:
        n = len(s)
        f = [[0] * n for _ in range(n)]
        for i in range(n):
            f[i][i] = 1
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                if s[i] == s[j]:
                    f[i][j] = f[i + 1][j - 1] + 2
                else:
                    f[i][j] = max(f[i + 1][j], f[i][j - 1])
        return f[0][-1]
```

```java
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return f[0][n - 1];
    }
}
```

```cpp
class Solution {
public:
    int longestPalindromeSubseq(string s) {
        int n = s.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            f[i][i] = 1;
        }
        for (int i = n - 1; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (s[i] == s[j]) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                } else {
                    f[i][j] = max(f[i + 1][j], f[i][j - 1]);
                }
            }
        }
        return f[0][n - 1];
    }
};
```

```go
func longestPalindromeSubseq(s string) int {
	n := len(s)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		f[i][i] = 1
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				f[i][j] = f[i+1][j-1] + 2
			} else {
				f[i][j] = max(f[i+1][j], f[i][j-1])
			}
		}
	}
	return f[0][n-1]
}
```

```ts
function longestPalindromeSubseq(s: string): number {
    const n = s.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = 0; i < n; ++i) {
        f[i][i] = 1;
    }
    for (let i = n - 2; ~i; --i) {
        for (let j = i + 1; j < n; ++j) {
            if (s[i] === s[j]) {
                f[i][j] = f[i + 1][j - 1] + 2;
            } else {
                f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
            }
        }
    }
    return f[0][n - 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
