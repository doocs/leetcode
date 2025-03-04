---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1278.Palindrome%20Partitioning%20III/README_EN.md
rating: 1979
source: Weekly Contest 165 Q4
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [1278. Palindrome Partitioning III](https://leetcode.com/problems/palindrome-partitioning-iii)

[中文文档](/solution/1200-1299/1278.Palindrome%20Partitioning%20III/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> containing lowercase letters and an integer <code>k</code>. You need to :</p>

<ul>
	<li>First, change some characters of <code>s</code> to other lowercase English letters.</li>
	<li>Then divide <code>s</code> into <code>k</code> non-empty disjoint substrings such that each substring is a palindrome.</li>
</ul>

<p>Return <em>the minimal number of characters that you need to change to divide the string</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abc&quot;, k = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong>&nbsp;You can split the string into &quot;ab&quot; and &quot;c&quot;, and change 1 character in &quot;ab&quot; to make it palindrome.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aabbc&quot;, k = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong>&nbsp;You can split the string into &quot;aa&quot;, &quot;bb&quot; and &quot;c&quot;, all of them are palindrome.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;leetcode&quot;, k = 8
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= s.length &lt;= 100</code>.</li>
	<li><code>s</code> only contains lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ to represent the minimum number of changes needed to partition the first $i$ characters of the string $s$ into $j$ palindromic substrings. We assume the index $i$ starts from 1, and the answer is $f[n][k]$.

For $f[i][j]$, we can enumerate the position $h$ of the last character of the $(j-1)$-th palindromic substring. Then $f[i][j]$ is equal to the minimum value of $f[h][j-1] + g[h][i-1]$, where $g[h][i-1]$ represents the minimum number of changes needed to turn the substring $s[h..i-1]$ into a palindrome (this part can be preprocessed with a time complexity of $O(n^2)$).

The time complexity is $O(n^2 \times k)$, and the space complexity is $O(n \times (n + k))$. Where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def palindromePartition(self, s: str, k: int) -> int:
        n = len(s)
        g = [[0] * n for _ in range(n)]
        for i in range(n - 1, -1, -1):
            for j in range(i + 1, n):
                g[i][j] = int(s[i] != s[j])
                if i + 1 < j:
                    g[i][j] += g[i + 1][j - 1]

        f = [[0] * (k + 1) for _ in range(n + 1)]
        for i in range(1, n + 1):
            for j in range(1, min(i, k) + 1):
                if j == 1:
                    f[i][j] = g[0][i - 1]
                else:
                    f[i][j] = inf
                    for h in range(j - 1, i):
                        f[i][j] = min(f[i][j], f[h][j - 1] + g[h][i - 1])
        return f[n][k]
```

#### Java

```java
class Solution {
    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] g = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                g[i][j] = s.charAt(i) != s.charAt(j) ? 1 : 0;
                if (i + 1 < j) {
                    g[i][j] += g[i + 1][j - 1];
                }
            }
        }
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= Math.min(i, k); ++j) {
                if (j == 1) {
                    f[i][j] = g[0][i - 1];
                } else {
                    f[i][j] = 10000;
                    for (int h = j - 1; h < i; ++h) {
                        f[i][j] = Math.min(f[i][j], f[h][j - 1] + g[h][i - 1]);
                    }
                }
            }
        }
        return f[n][k];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int palindromePartition(string s, int k) {
        int n = s.size();
        vector<vector<int>> g(n, vector<int>(n));
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                g[i][j] = s[i] != s[j] ? 1 : 0;
                if (i + 1 < j) g[i][j] += g[i + 1][j - 1];
            }
        }
        vector<vector<int>> f(n + 1, vector<int>(k + 1));
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= min(i, k); ++j) {
                if (j == 1) {
                    f[i][j] = g[0][i - 1];
                } else {
                    f[i][j] = 10000;
                    for (int h = j - 1; h < i; ++h) {
                        f[i][j] = min(f[i][j], f[h][j - 1] + g[h][i - 1]);
                    }
                }
            }
        }
        return f[n][k];
    }
};
```

#### Go

```go
func palindromePartition(s string, k int) int {
	n := len(s)
	g := make([][]int, n)
	for i := range g {
		g[i] = make([]int, n)
	}
	for i := n - 1; i >= 0; i-- {
		for j := 1; j < n; j++ {
			if s[i] != s[j] {
				g[i][j] = 1
			}
			if i+1 < j {
				g[i][j] += g[i+1][j-1]
			}
		}
	}
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := 1; i <= n; i++ {
		for j := 1; j <= min(i, k); j++ {
			if j == 1 {
				f[i][j] = g[0][i-1]
			} else {
				f[i][j] = 100000
				for h := j - 1; h < i; h++ {
					f[i][j] = min(f[i][j], f[h][j-1]+g[h][i-1])
				}
			}
		}
	}
	return f[n][k]
}
```

#### TypeScript

```ts
function palindromePartition(s: string, k: number): number {
    const n = s.length;
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = n - 1; i >= 0; i--) {
        for (let j = i + 1; j < n; j++) {
            g[i][j] = s[i] !== s[j] ? 1 : 0;
            if (i + 1 < j) {
                g[i][j] += g[i + 1][j - 1];
            }
        }
    }
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(0));
    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= Math.min(i, k); j++) {
            if (j === 1) {
                f[i][j] = g[0][i - 1];
            } else {
                f[i][j] = 1 << 30;
                for (let h = j - 1; h < i; h++) {
                    f[i][j] = Math.min(f[i][j], f[h][j - 1] + g[h][i - 1]);
                }
            }
        }
    }
    return f[n][k];
}
```

#### Rust

```rust
impl Solution {
    pub fn palindrome_partition(s: String, k: i32) -> i32 {
        let n = s.len();
        let s: Vec<char> = s.chars().collect();
        let mut g = vec![vec![0; n]; n];

        for i in (0..n).rev() {
            for j in i + 1..n {
                g[i][j] = if s[i] != s[j] { 1 } else { 0 };
                if i + 1 < j {
                    g[i][j] += g[i + 1][j - 1];
                }
            }
        }

        let mut f = vec![vec![0; (k + 1) as usize]; n + 1];
        let inf = i32::MAX;

        for i in 1..=n {
            for j in 1..=i.min(k as usize) {
                if j == 1 {
                    f[i][j] = g[0][i - 1];
                } else {
                    f[i][j] = inf;
                    for h in (j - 1)..i {
                        f[i][j] = f[i][j].min(f[h][j - 1] + g[h][i - 1]);
                    }
                }
            }
        }

        f[n][k as usize]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
