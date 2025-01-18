---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0583.Delete%20Operation%20for%20Two%20Strings/README_EN.md
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [583. Delete Operation for Two Strings](https://leetcode.com/problems/delete-operation-for-two-strings)

[中文文档](/solution/0500-0599/0583.Delete%20Operation%20for%20Two%20Strings/README.md)

## Description

<!-- description:start -->

<p>Given two strings <code>word1</code> and <code>word2</code>, return <em>the minimum number of <strong>steps</strong> required to make</em> <code>word1</code> <em>and</em> <code>word2</code> <em>the same</em>.</p>

<p>In one <strong>step</strong>, you can delete exactly one character in either string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;sea&quot;, word2 = &quot;eat&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> You need one step to make &quot;sea&quot; to &quot;ea&quot; and another step to make &quot;eat&quot; to &quot;ea&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word1 = &quot;leetcode&quot;, word2 = &quot;etco&quot;
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 500</code></li>
	<li><code>word1</code> and <code>word2</code> consist of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the minimum number of deletions required to make the first $i$ characters of the string $\textit{word1}$ and the first $j$ characters of the string $\textit{word2}$ the same. The answer is $f[m][n]$, where $m$ and $n$ are the lengths of the strings $\textit{word1}$ and $\textit{word2}$, respectively.

Initially, if $j = 0$, then $f[i][0] = i$; if $i = 0$, then $f[0][j] = j$.

When $i, j > 0$, if $\textit{word1}[i - 1] = \textit{word2}[j - 1]$, then $f[i][j] = f[i - 1][j - 1]$; otherwise, $f[i][j] = \min(f[i - 1][j], f[i][j - 1]) + 1$.

Finally, return $f[m][n]$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the lengths of the strings $\textit{word1}$ and $\textit{word2}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        m, n = len(word1), len(word2)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            f[i][0] = i
        for j in range(1, n + 1):
            f[0][j] = j
        for i, a in enumerate(word1, 1):
            for j, b in enumerate(word2, 1):
                if a == b:
                    f[i][j] = f[i - 1][j - 1]
                else:
                    f[i][j] = min(f[i - 1][j], f[i][j - 1]) + 1
        return f[m][n]
```

#### Java

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i <= m; ++i) {
            f[i][0] = i;
        }
        for (int j = 0; j <= n; ++j) {
            f[0][j] = j;
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                char a = word1.charAt(i - 1);
                char b = word2.charAt(j - 1);
                if (a == b) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        return f[m][n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDistance(string word1, string word2) {
        int m = word1.length(), n = word2.length();
        vector<vector<int>> f(m + 1, vector<int>(n + 1));
        for (int i = 0; i <= m; ++i) {
            f[i][0] = i;
        }
        for (int j = 0; j <= n; ++j) {
            f[0][j] = j;
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                char a = word1[i - 1];
                char b = word2[j - 1];
                if (a == b) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = min(f[i - 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func minDistance(word1 string, word2 string) int {
	m, n := len(word1), len(word2)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
        f[i][0] = i
	}
	for j := 1; j <= n; j++ {
		f[0][j] = j
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			a, b := word1[i-1], word2[j-1]
			if a == b {
				f[i][j] = f[i-1][j-1]
			} else {
				f[i][j] = 1 + min(f[i-1][j], f[i][j-1])
			}
		}
	}
	return f[m][n]
}
```

#### TypeScript

```ts
function minDistance(word1: string, word2: string): number {
    const m = word1.length;
    const n = word2.length;
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        f[i][0] = i;
    }
    for (let j = 1; j <= n; ++j) {
        f[0][j] = j;
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (word1[i - 1] === word2[j - 1]) {
                f[i][j] = f[i - 1][j - 1];
            } else {
                f[i][j] = Math.min(f[i - 1][j], f[i][j - 1]) + 1;
            }
        }
    }
    return f[m][n];
}
```

#### Rust

```rust
impl Solution {
    pub fn min_distance(word1: String, word2: String) -> i32 {
        let m = word1.len();
        let n = word2.len();
        let s: Vec<char> = word1.chars().collect();
        let t: Vec<char> = word2.chars().collect();
        let mut f = vec![vec![0; n + 1]; m + 1];

        for i in 0..=m {
            f[i][0] = i as i32;
        }
        for j in 0..=n {
            f[0][j] = j as i32;
        }

        for i in 1..=m {
            for j in 1..=n {
                let a = s[i - 1];
                let b = t[j - 1];
                if a == b {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = std::cmp::min(f[i - 1][j], f[i][j - 1]) + 1;
                }
            }
        }
        f[m][n]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
