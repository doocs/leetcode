---
comments: true
difficulty: Hard
rating: 2013
source: Weekly Contest 319 Q4
tags:
    - Greedy
    - Two Pointers
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [2472. Maximum Number of Non-overlapping Palindrome Substrings](https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings)

[中文文档](/solution/2400-2499/2472.Maximum%20Number%20of%20Non-overlapping%20Palindrome%20Substrings/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and a <strong>positive</strong> integer <code>k</code>.</p>

<p>Select a set of <strong>non-overlapping</strong> substrings from the string <code>s</code> that satisfy the following conditions:</p>

<ul>
	<li>The <strong>length</strong> of each substring is <strong>at least</strong> <code>k</code>.</li>
	<li>Each substring is a <strong>palindrome</strong>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of substrings in an optimal selection</em>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abaccdbbd&quot;, k = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> We can select the substrings underlined in s = &quot;<u><strong>aba</strong></u>cc<u><strong>dbbd</strong></u>&quot;. Both &quot;aba&quot; and &quot;dbbd&quot; are palindromes and have a length of at least k = 3.
It can be shown that we cannot find a selection with more than two valid substrings.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;adbcda&quot;, k = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no palindrome substring of length at least 2 in the string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 2000</code></li>
	<li><code>s</code> consists of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Preprocessing + Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> We want as many non-overlapping palindromes of length at least $k$ as possible. With $n \le 2000$, enumerating partitions is too slow. Whether $s[i..j]$ is a palindrome can be precomputed in $O(n^2)$ as $g[i][j]$. The remaining choice at index $i$ is to skip $s[i]$, or take a palindrome starting at $i$ with length at least $k$ and continue after its right end. Filling $f[i]$ from the right makes each transition look only at larger indices.

<!-- thinking:end -->

First, preprocess the string $s$ to get $g[i][j]$, which represents whether the substring $s[i..j]$ is a palindrome.

Then, define $f[i]$ as the maximum number of non-overlapping palindrome substrings that can be selected from $s[i..]$. Initially, $f[n] = 0$. For $i$ from $n - 1$ down to $0$, we can skip $s[i]$, i.e., $f[i] = f[i + 1]$; we can also enumerate the ending index $j$ ($j \ge i + k - 1$), and if $g[i][j]$ is true, we take this palindrome and continue from $j + 1$. That is,

$$
\begin{aligned}
f[i] &= \begin{cases}
0, & i \geq n \\
\max\bigl\{f[i + 1],\ \max\limits_{\substack{j \ge i + k - 1 \\ g[i][j]}} \{f[j + 1] + 1\}\bigr\}, & i < n
\end{cases}
\end{aligned}
$$

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxPalindromes(self, s: str, k: int) -> int:
        n = len(s)
        g = [[True] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                g[i][j] = s[i] == s[j] and g[i + 1][j - 1]
        f = [0] * (n + 1)
        for i in range(n - 1, -1, -1):
            f[i] = f[i + 1]
            for j in range(i + k - 1, n):
                if g[i][j]:
                    f[i] = max(f[i], 1 + f[j + 1])
        return f[0]
```

#### Java

```java
class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (var row : g) {
            Arrays.fill(row, true);
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }
        int[] f = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = f[i + 1];
            for (int j = i + k - 1; j < n; ++j) {
                if (g[i][j]) {
                    f[i] = Math.max(f[i], 1 + f[j + 1]);
                }
            }
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxPalindromes(string s, int k) {
        int n = s.size();
        bool g[n][n];
        memset(g, true, sizeof(g));
        for (int i = n - 1; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s[i] == s[j] && g[i + 1][j - 1];
            }
        }
        int f[n + 1];
        memset(f, 0, sizeof(f));
        for (int i = n - 1; ~i; --i) {
            f[i] = f[i + 1];
            for (int j = i + k - 1; j < n; ++j) {
                if (g[i][j]) {
                    f[i] = max(f[i], 1 + f[j + 1]);
                }
            }
        }
        return f[0];
    }
};
```

#### Go

```go
func maxPalindromes(s string, k int) int {
	n := len(s)
	g := make([][]bool, n)
	for i := range g {
		g[i] = make([]bool, n)
		for j := range g[i] {
			g[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			g[i][j] = s[i] == s[j] && g[i+1][j-1]
		}
	}
	f := make([]int, n+1)
	for i := n - 1; i >= 0; i-- {
		f[i] = f[i+1]
		for j := i + k - 1; j < n; j++ {
			if g[i][j] {
				f[i] = max(f[i], 1+f[j+1])
			}
		}
	}
	return f[0]
}
```

#### TypeScript

```ts
function maxPalindromes(s: string, k: number): number {
    const n = s.length;
    const g: boolean[][] = Array.from({ length: n }, () => Array(n).fill(true));
    for (let i = n - 1; ~i; --i) {
        for (let j = i + 1; j < n; ++j) {
            g[i][j] = s[i] === s[j] && g[i + 1][j - 1];
        }
    }
    const f: number[] = Array(n + 1).fill(0);
    for (let i = n - 1; ~i; --i) {
        f[i] = f[i + 1];
        for (let j = i + k - 1; j < n; ++j) {
            if (g[i][j]) {
                f[i] = Math.max(f[i], 1 + f[j + 1]);
            }
        }
    }
    return f[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
