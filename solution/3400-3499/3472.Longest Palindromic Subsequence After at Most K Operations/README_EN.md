---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3472.Longest%20Palindromic%20Subsequence%20After%20at%20Most%20K%20Operations/README_EN.md
rating: 1883
source: Weekly Contest 439 Q2
tags:
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [3472. Longest Palindromic Subsequence After at Most K Operations](https://leetcode.com/problems/longest-palindromic-subsequence-after-at-most-k-operations)

[中文文档](/solution/3400-3499/3472.Longest%20Palindromic%20Subsequence%20After%20at%20Most%20K%20Operations/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> and an integer <code>k</code>.</p>

<p>In one operation, you can replace the character at any position with the next or previous letter in the alphabet (wrapping around so that <code>&#39;a&#39;</code> is after <code>&#39;z&#39;</code>). For example, replacing <code>&#39;a&#39;</code> with the next letter results in <code>&#39;b&#39;</code>, and replacing <code>&#39;a&#39;</code> with the previous letter results in <code>&#39;z&#39;</code>. Similarly, replacing <code>&#39;z&#39;</code> with the next letter results in <code>&#39;a&#39;</code>, and replacing <code>&#39;z&#39;</code> with the previous letter results in <code>&#39;y&#39;</code>.</p>

<p>Return the length of the <strong>longest <span data-keyword="palindrome-string">palindromic</span> <span data-keyword="subsequence-string-nonempty">subsequence</span></strong> of <code>s</code> that can be obtained after performing <strong>at most</strong> <code>k</code> operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abced&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Replace <code>s[1]</code> with the next letter, and <code>s</code> becomes <code>&quot;acced&quot;</code>.</li>
	<li>Replace <code>s[4]</code> with the previous letter, and <code>s</code> becomes <code>&quot;accec&quot;</code>.</li>
</ul>

<p>The subsequence <code>&quot;ccc&quot;</code> forms a palindrome of length 3, which is the maximum.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;</span>aaazzz<span class="example-io">&quot;, k = 4</span></p>

<p><strong>Output:</strong> 6</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Replace <code>s[0]</code> with the previous letter, and <code>s</code> becomes <code>&quot;zaazzz&quot;</code>.</li>
	<li>Replace <code>s[4]</code> with the next letter, and <code>s</code> becomes <code>&quot;zaazaz&quot;</code>.</li>
	<li>Replace <code>s[3]</code> with the next letter, and <code>s</code> becomes <code>&quot;zaaaaz&quot;</code>.</li>
</ul>

<p>The entire string forms a palindrome of length 6.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= 200</code></li>
	<li><code>s</code> consists of only lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoized Search

We design a function $\textit{dfs}(i, j, k)$, which represents the length of the longest palindromic subsequence that can be obtained in the substring $s[i..j]$ with at most $k$ operations. The answer is $\textit{dfs}(0, n - 1, k)$.

The calculation process of the function $\textit{dfs}(i, j, k)$ is as follows:

-   If $i > j$, return $0$;
-   If $i = j$, return $1$;
-   Otherwise, we can ignore $s[i]$ or $s[j]$ and calculate $\textit{dfs}(i + 1, j, k)$ and $\textit{dfs}(i, j - 1, k)$ respectively; or we can change $s[i]$ and $s[j]$ to the same character and calculate $\textit{dfs}(i + 1, j - 1, k - t) + 2$, where $t$ is the ASCII code difference between $s[i]$ and $s[j]$.
-   Return the maximum value of the above three cases.

To avoid repeated calculations, we use memoized search.

The time complexity is $O(n^2 \times k)$, and the space complexity is $O(n^2 \times k)$. Where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestPalindromicSubsequence(self, s: str, k: int) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i > j:
                return 0
            if i == j:
                return 1
            res = max(dfs(i + 1, j, k), dfs(i, j - 1, k))
            d = abs(s[i] - s[j])
            t = min(d, 26 - d)
            if t <= k:
                res = max(res, dfs(i + 1, j - 1, k - t) + 2)
            return res

        s = list(map(ord, s))
        n = len(s)
        ans = dfs(0, n - 1, k)
        dfs.cache_clear()
        return ans
```

#### Java

```java
class Solution {
    private char[] s;
    private Integer[][][] f;

    public int longestPalindromicSubsequence(String s, int k) {
        this.s = s.toCharArray();
        int n = s.length();
        f = new Integer[n][n][k + 1];
        return dfs(0, n - 1, k);
    }

    private int dfs(int i, int j, int k) {
        if (i > j) {
            return 0;
        }
        if (i == j) {
            return 1;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        int res = Math.max(dfs(i + 1, j, k), dfs(i, j - 1, k));
        int d = Math.abs(s[i] - s[j]);
        int t = Math.min(d, 26 - d);
        if (t <= k) {
            res = Math.max(res, 2 + dfs(i + 1, j - 1, k - t));
        }
        f[i][j][k] = res;
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestPalindromicSubsequence(string s, int k) {
        int n = s.size();
        vector f(n, vector(n, vector<int>(k + 1, -1)));
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (i > j) {
                return 0;
            }
            if (i == j) {
                return 1;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            int res = max(dfs(i + 1, j, k), dfs(i, j - 1, k));
            int d = abs(s[i] - s[j]);
            int t = min(d, 26 - d);
            if (t <= k) {
                res = max(res, 2 + dfs(i + 1, j - 1, k - t));
            }
            return f[i][j][k] = res;
        };
        return dfs(0, n - 1, k);
    }
};
```

#### Go

```go
func longestPalindromicSubsequence(s string, k int) int {
	n := len(s)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, k+1)
			for l := range f[i][j] {
				f[i][j][l] = -1
			}
		}
	}
	var dfs func(int, int, int) int
	dfs = func(i, j, k int) int {
		if i > j {
			return 0
		}
		if i == j {
			return 1
		}
		if f[i][j][k] != -1 {
			return f[i][j][k]
		}
		res := max(dfs(i+1, j, k), dfs(i, j-1, k))
		d := abs(int(s[i]) - int(s[j]))
		t := min(d, 26-d)
		if t <= k {
			res = max(res, 2+dfs(i+1, j-1, k-t))
		}
		f[i][j][k] = res
		return res
	}
	return dfs(0, n-1, k)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function longestPalindromicSubsequence(s: string, k: number): number {
    const n = s.length;
    const sCodes = s.split('').map(c => c.charCodeAt(0));
    const f: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: n }, () => Array(k + 1).fill(-1)),
    );

    function dfs(i: number, j: number, k: number): number {
        if (i > j) {
            return 0;
        }
        if (i === j) {
            return 1;
        }

        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }

        let res = Math.max(dfs(i + 1, j, k), dfs(i, j - 1, k));
        const d = Math.abs(sCodes[i] - sCodes[j]);
        const t = Math.min(d, 26 - d);
        if (t <= k) {
            res = Math.max(res, 2 + dfs(i + 1, j - 1, k - t));
        }
        return (f[i][j][k] = res);
    }

    return dfs(0, n - 1, k);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
