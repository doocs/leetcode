---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3316.Find%20Maximum%20Removals%20From%20Source%20String/README_EN.md
tags:
    - Array
    - Hash Table
    - Two Pointers
    - String
    - Dynamic Programming
---

<!-- problem:start -->

# [3316. Find Maximum Removals From Source String](https://leetcode.com/problems/find-maximum-removals-from-source-string)

[中文文档](/solution/3300-3399/3316.Find%20Maximum%20Removals%20From%20Source%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>source</code> of size <code>n</code>, a string <code>pattern</code> that is a <span data-keyword="subsequence-string">subsequence</span> of <code>source</code>, and a <strong>sorted</strong> integer array <code>targetIndices</code> that contains <strong>distinct</strong> numbers in the range <code>[0, n - 1]</code>.</p>

<p>We define an <strong>operation</strong> as removing a character at an index <code>idx</code> from <code>source</code> such that:</p>

<ul>
	<li><code>idx</code> is an element of <code>targetIndices</code>.</li>
	<li><code>pattern</code> remains a <span data-keyword="subsequence-string">subsequence</span> of <code>source</code> after removing the character.</li>
</ul>

<p>Performing an operation <strong>does not</strong> change the indices of the other characters in <code>source</code>. For example, if you remove <code>&#39;c&#39;</code> from <code>&quot;acb&quot;</code>, the character at index 2 would still be <code>&#39;b&#39;</code>.</p>

<p>Return the <strong>maximum</strong> number of <em>operations</em> that can be performed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = &quot;abbaa&quot;, pattern = &quot;aba&quot;, </span>targetIndices<span class="example-io"> = [0,1,2]</span></p>

<p><strong>Output:</strong> 1</p>

<p><strong>Explanation:</strong></p>

<p>We can&#39;t remove <code>source[0]</code> but we can do either of these two operations:</p>

<ul>
	<li>Remove <code>source[1]</code>, so that <code>source</code> becomes <code>&quot;a_baa&quot;</code>.</li>
	<li>Remove <code>source[2]</code>, so that <code>source</code> becomes <code>&quot;ab_aa&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = &quot;bcda&quot;, pattern = &quot;d&quot;, </span>targetIndices<span class="example-io"> = [0,3]</span></p>

<p><strong>Output:</strong> 2</p>

<p><strong>Explanation:</strong></p>

<p>We can remove <code>source[0]</code> and <code>source[3]</code> in two operations.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = &quot;dda&quot;, pattern = &quot;dda&quot;, </span>targetIndices<span class="example-io"> = [0,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>We can&#39;t remove any character from <code>source</code>.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">source = </span>&quot;yeyeykyded&quot;<span class="example-io">, pattern = </span>&quot;yeyyd&quot;<span class="example-io">, </span>targetIndices<span class="example-io"> = </span>[0,2,3,4]</p>

<p><strong>Output:</strong> 2</p>

<p><strong>Explanation:</strong></p>

<p>We can remove <code>source[2]</code> and <code>source[3]</code> in two operations.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == source.length &lt;= 3 * 10<sup>3</sup></code></li>
	<li><code>1 &lt;= pattern.length &lt;= n</code></li>
	<li><code>1 &lt;= targetIndices.length &lt;= n</code></li>
	<li><code>targetIndices</code> is sorted in ascending order.</li>
	<li>The input is generated such that <code>targetIndices</code> contains distinct elements in the range <code>[0, n - 1]</code>.</li>
	<li><code>source</code> and <code>pattern</code> consist only of lowercase English letters.</li>
	<li>The input is generated such that <code>pattern</code> appears as a subsequence in <code>source</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We define $f[i][j]$ to represent the maximum number of deletions in the first $i$ characters of $\textit{source}$ that match the first $j$ characters of $\textit{pattern}$. Initially, $f[0][0] = 0$, and the rest $f[i][j] = -\infty$.

For $f[i][j]$, we have two choices:

-   We can skip the $i$-th character of $\textit{source}$, in which case $f[i][j] = f[i-1][j] + \text{int}(i-1 \in \textit{targetIndices})$;
-   If $\textit{source}[i-1] = \textit{pattern}[j-1]$, we can match the $i$-th character of $\textit{source}$, in which case $f[i][j] = \max(f[i][j], f[i-1][j-1])$.

The final answer is $f[m][n]$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Here, $m$ and $n$ are the lengths of $\textit{source}$ and $\textit{pattern}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxRemovals(self, source: str, pattern: str, targetIndices: List[int]) -> int:
        m, n = len(source), len(pattern)
        f = [[-inf] * (n + 1) for _ in range(m + 1)]
        f[0][0] = 0
        s = set(targetIndices)
        for i, c in enumerate(source, 1):
            for j in range(n + 1):
                f[i][j] = f[i - 1][j] + int((i - 1) in s)
                if j and c == pattern[j - 1]:
                    f[i][j] = max(f[i][j], f[i - 1][j - 1])
        return f[m][n]
```

#### Java

```java
class Solution {
    public int maxRemovals(String source, String pattern, int[] targetIndices) {
        int m = source.length(), n = pattern.length();
        int[][] f = new int[m + 1][n + 1];
        final int inf = Integer.MAX_VALUE / 2;
        for (var g : f) {
            Arrays.fill(g, -inf);
        }
        f[0][0] = 0;
        int[] s = new int[m];
        for (int i : targetIndices) {
            s[i] = 1;
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j] + s[i - 1];
                if (j > 0 && source.charAt(i - 1) == pattern.charAt(j - 1)) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - 1]);
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
    int maxRemovals(string source, string pattern, vector<int>& targetIndices) {
        int m = source.length(), n = pattern.length();
        vector<vector<int>> f(m + 1, vector<int>(n + 1, INT_MIN / 2));
        f[0][0] = 0;

        vector<int> s(m);
        for (int i : targetIndices) {
            s[i] = 1;
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j] + s[i - 1];
                if (j > 0 && source[i - 1] == pattern[j - 1]) {
                    f[i][j] = max(f[i][j], f[i - 1][j - 1]);
                }
            }
        }

        return f[m][n];
    }
};
```

#### Go

```go
func maxRemovals(source string, pattern string, targetIndices []int) int {
	m, n := len(source), len(pattern)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
		for j := range f[i] {
			f[i][j] = -math.MaxInt32 / 2
		}
	}
	f[0][0] = 0

	s := make([]int, m)
	for _, i := range targetIndices {
		s[i] = 1
	}

	for i := 1; i <= m; i++ {
		for j := 0; j <= n; j++ {
			f[i][j] = f[i-1][j] + s[i-1]
			if j > 0 && source[i-1] == pattern[j-1] {
				f[i][j] = max(f[i][j], f[i-1][j-1])
			}
		}
	}

	return f[m][n]
}
```

#### TypeScript

```ts
function maxRemovals(source: string, pattern: string, targetIndices: number[]): number {
    const m = source.length;
    const n = pattern.length;
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(-Infinity));
    f[0][0] = 0;

    const s = Array(m).fill(0);
    for (const i of targetIndices) {
        s[i] = 1;
    }

    for (let i = 1; i <= m; i++) {
        for (let j = 0; j <= n; j++) {
            f[i][j] = f[i - 1][j] + s[i - 1];
            if (j > 0 && source[i - 1] === pattern[j - 1]) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j - 1]);
            }
        }
    }

    return f[m][n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
