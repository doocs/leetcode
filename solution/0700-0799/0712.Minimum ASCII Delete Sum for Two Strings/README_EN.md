---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0712.Minimum%20ASCII%20Delete%20Sum%20for%20Two%20Strings/README_EN.md
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [712. Minimum ASCII Delete Sum for Two Strings](https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings)

[中文文档](/solution/0700-0799/0712.Minimum%20ASCII%20Delete%20Sum%20for%20Two%20Strings/README.md)

## Description

<!-- description:start -->

<p>Given two strings <code>s1</code> and&nbsp;<code>s2</code>, return <em>the lowest <strong>ASCII</strong> sum of deleted characters to make two strings equal</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;sea&quot;, s2 = &quot;eat&quot;
<strong>Output:</strong> 231
<strong>Explanation:</strong> Deleting &quot;s&quot; from &quot;sea&quot; adds the ASCII value of &quot;s&quot; (115) to the sum.
Deleting &quot;t&quot; from &quot;eat&quot; adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s1 = &quot;delete&quot;, s2 = &quot;leet&quot;
<strong>Output:</strong> 403
<strong>Explanation:</strong> Deleting &quot;dee&quot; from &quot;delete&quot; to turn the string into &quot;let&quot;,
adds 100[d] + 101[e] + 101[e] to the sum.
Deleting &quot;e&quot; from &quot;leet&quot; adds 101[e] to the sum.
At the end, both strings are equal to &quot;let&quot;, and the answer is 100+101+101+101 = 403.
If instead we turned both strings into &quot;lee&quot; or &quot;eet&quot;, we would get answers of 433 or 417, which are higher.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s1.length, s2.length &lt;= 1000</code></li>
	<li><code>s1</code> and <code>s2</code> consist of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ as the minimum sum of ASCII values of deleted characters required to make the first $i$ characters of $s_1$ equal to the first $j$ characters of $s_2$. The answer is $f[m][n]$.

If $s_1[i-1] = s_2[j-1]$, then $f[i][j] = f[i-1][j-1]$. Otherwise, we can delete either $s_1[i-1]$ or $s_2[j-1]$ to minimize $f[i][j]$. Therefore, the state transition equation is as follows:

$$
f[i][j]=
\begin{cases}
f[i-1][j-1], & s_1[i-1] = s_2[j-1] \\
min(f[i-1][j] + s_1[i-1], f[i][j-1] + s_2[j-1]), & s_1[i-1] \neq s_2[j-1]
\end{cases}
$$

The initial state is $f[0][j] = f[0][j-1] + s_2[j-1]$, $f[i][0] = f[i-1][0] + s_1[i-1]$.

Finally, return $f[m][n]$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the lengths of $s_1$ and $s_2$ respectively.

Similar problems:

- [1143. Longest Common Subsequence](https://github.com/doocs/leetcode/blob/main/solution/1100-1199/1143.Longest%20Common%20Subsequence/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumDeleteSum(self, s1: str, s2: str) -> int:
        m, n = len(s1), len(s2)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i in range(1, m + 1):
            f[i][0] = f[i - 1][0] + ord(s1[i - 1])
        for j in range(1, n + 1):
            f[0][j] = f[0][j - 1] + ord(s2[j - 1])
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if s1[i - 1] == s2[j - 1]:
                    f[i][j] = f[i - 1][j - 1]
                else:
                    f[i][j] = min(
                        f[i - 1][j] + ord(s1[i - 1]), f[i][j - 1] + ord(s2[j - 1])
                    )
        return f[m][n]
```

#### Java

```java
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            f[i][0] = f[i - 1][0] + s1.charAt(i - 1);
        }
        for (int j = 1; j <= n; ++j) {
            f[0][j] = f[0][j - 1] + s2.charAt(j - 1);
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j]
                        = Math.min(f[i - 1][j] + s1.charAt(i - 1), f[i][j - 1] + s2.charAt(j - 1));
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
    int minimumDeleteSum(string s1, string s2) {
        int m = s1.size(), n = s2.size();
        int f[m + 1][n + 1];
        memset(f, 0, sizeof f);
        for (int i = 1; i <= m; ++i) {
            f[i][0] = f[i - 1][0] + s1[i - 1];
        }
        for (int j = 1; j <= n; ++j) {
            f[0][j] = f[0][j - 1] + s2[j - 1];
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s1[i - 1] == s2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = min(f[i - 1][j] + s1[i - 1], f[i][j - 1] + s2[j - 1]);
                }
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func minimumDeleteSum(s1 string, s2 string) int {
	m, n := len(s1), len(s2)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i, c := range s1 {
		f[i+1][0] = f[i][0] + int(c)
	}
	for j, c := range s2 {
		f[0][j+1] = f[0][j] + int(c)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if s1[i-1] == s2[j-1] {
				f[i][j] = f[i-1][j-1]
			} else {
				f[i][j] = min(f[i-1][j]+int(s1[i-1]), f[i][j-1]+int(s2[j-1]))
			}
		}
	}
	return f[m][n]
}
```

#### TypeScript

```ts
function minimumDeleteSum(s1: string, s2: string): number {
    const m = s1.length;
    const n = s2.length;
    const f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        f[i][0] = f[i - 1][0] + s1[i - 1].charCodeAt(0);
    }
    for (let j = 1; j <= n; ++j) {
        f[0][j] = f[0][j - 1] + s2[j - 1].charCodeAt(0);
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (s1[i - 1] === s2[j - 1]) {
                f[i][j] = f[i - 1][j - 1];
            } else {
                f[i][j] = Math.min(
                    f[i - 1][j] + s1[i - 1].charCodeAt(0),
                    f[i][j - 1] + s2[j - 1].charCodeAt(0),
                );
            }
        }
    }
    return f[m][n];
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_delete_sum(s1: String, s2: String) -> i32 {
        let m: usize = s1.len();
        let n: usize = s2.len();
        let b1 = s1.as_bytes();
        let b2 = s2.as_bytes();

        let mut f: Vec<Vec<i32>> = vec![vec![0; n + 1]; m + 1];

        for i in 1..=m {
            f[i][0] = f[i - 1][0] + b1[i - 1] as i32;
        }
        for j in 1..=n {
            f[0][j] = f[0][j - 1] + b2[j - 1] as i32;
        }

        for i in 1..=m {
            for j in 1..=n {
                if b1[i - 1] == b2[j - 1] {
                    f[i][j] = f[i - 1][j - 1];
                } else {
                    f[i][j] = std::cmp::min(
                        f[i - 1][j] + b1[i - 1] as i32,
                        f[i][j - 1] + b2[j - 1] as i32,
                    );
                }
            }
        }

        f[m][n]
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s1
 * @param {string} s2
 * @return {number}
 */
var minimumDeleteSum = function (s1, s2) {
    const m = s1.length;
    const n = s2.length;
    const f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        f[i][0] = f[i - 1][0] + s1[i - 1].charCodeAt(0);
    }
    for (let j = 1; j <= n; ++j) {
        f[0][j] = f[0][j - 1] + s2[j - 1].charCodeAt(0);
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (s1[i - 1] === s2[j - 1]) {
                f[i][j] = f[i - 1][j - 1];
            } else {
                f[i][j] = Math.min(
                    f[i - 1][j] + s1[i - 1].charCodeAt(0),
                    f[i][j - 1] + s2[j - 1].charCodeAt(0),
                );
            }
        }
    }
    return f[m][n];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
