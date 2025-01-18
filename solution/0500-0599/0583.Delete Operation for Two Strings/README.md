---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0500-0599/0583.Delete%20Operation%20for%20Two%20Strings/README.md
tags:
    - 字符串
    - 动态规划
---

<!-- problem:start -->

# [583. 两个字符串的删除操作](https://leetcode.cn/problems/delete-operation-for-two-strings)

[English Version](/solution/0500-0599/0583.Delete%20Operation%20for%20Two%20Strings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个单词&nbsp;<code>word1</code>&nbsp;和<meta charset="UTF-8" />&nbsp;<code>word2</code>&nbsp;，返回使得<meta charset="UTF-8" />&nbsp;<code>word1</code>&nbsp;和&nbsp;<meta charset="UTF-8" />&nbsp;<code>word2</code><em>&nbsp;</em><strong>相同</strong>所需的<strong>最小步数</strong>。</p>

<p><strong>每步&nbsp;</strong>可以删除任意一个字符串中的一个字符。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> word1 = "sea", word2 = "eat"
<strong>输出:</strong> 2
<strong>解释:</strong> 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
</pre>

<p><strong>示例 &nbsp;2:</strong></p>

<pre>
<b>输入：</b>word1 = "leetcode", word2 = "etco"
<b>输出：</b>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>
<meta charset="UTF-8" />

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 500</code></li>
	<li><code>word1</code>&nbsp;和&nbsp;<code>word2</code>&nbsp;只包含小写英文字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示使得字符串 $\textit{word1}$ 的前 $i$ 个字符和字符串 $\textit{word2}$ 的前 $j$ 个字符相同的最小删除步数。那么答案为 $f[m][n]$，其中 $m$ 和 $n$ 分别是字符串 $\textit{word1}$ 和 $\textit{word2}$ 的长度。

初始时，如果 $j = 0$，那么 $f[i][0] = i$；如果 $i = 0$，那么 $f[0][j] = j$。

当 $i, j > 0$ 时，如果 $\textit{word1}[i - 1] = \textit{word2}[j - 1]$，那么 $f[i][j] = f[i - 1][j - 1]$；否则 $f[i][j] = \min(f[i - 1][j], f[i][j - 1]) + 1$。

最终返回 $f[m][n]$ 即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是字符串 $\textit{word1}$ 和 $\textit{word2}$ 的长度。

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
