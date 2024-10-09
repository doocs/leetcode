---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3135.Equalize%20Strings%20by%20Adding%20or%20Removing%20Characters%20at%20Ends/README_EN.md
tags:
    - String
    - Binary Search
    - Dynamic Programming
    - Sliding Window
    - Hash Function
---

<!-- problem:start -->

# [3135. Equalize Strings by Adding or Removing Characters at Ends 🔒](https://leetcode.com/problems/equalize-strings-by-adding-or-removing-characters-at-ends)

[中文文档](/solution/3100-3199/3135.Equalize%20Strings%20by%20Adding%20or%20Removing%20Characters%20at%20Ends/README.md)

## Description

<!-- description:start -->

<p>Given two strings <code>initial</code> and <code>target</code>, your task is to modify <code>initial</code> by performing a series of operations to make it equal to <code>target</code>.</p>

<p>In one operation, you can add or remove <strong>one character</strong> only at the <em>beginning</em> or the <em>end</em> of the string <code>initial</code>.</p>

<p>Return the <strong>minimum</strong> number of operations required to <em>transform</em> <code>initial</code> into <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">initial = &quot;abcde&quot;, target = &quot;cdef&quot;</span></p>

<p><strong>Output:</strong> 3</p>

<p><strong>Explanation:</strong></p>

<p>Remove <code>&#39;a&#39;</code> and <code>&#39;b&#39;</code> from the beginning of <code>initial</code>, then add <code>&#39;f&#39;</code> to the end.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">initial = &quot;axxy&quot;, target = &quot;yabx&quot;</span></p>

<p><strong>Output:</strong> 6</p>

<p><strong>Explanation:</strong></p>

<table border="1">
	<tbody>
		<tr>
			<th>Operation</th>
			<th>Resulting String</th>
		</tr>
		<tr>
			<td>Add <code>&#39;y&#39;</code> to the beginning</td>
			<td><code>&quot;yaxxy&quot;</code></td>
		</tr>
		<tr>
			<td>Remove from end</td>
			<td><code>&quot;yaxx&quot;</code></td>
		</tr>
		<tr>
			<td>Remove from end</td>
			<td><code>&quot;yax&quot;</code></td>
		</tr>
		<tr>
			<td>Remove from end</td>
			<td><code>&quot;ya&quot;</code></td>
		</tr>
		<tr>
			<td>Add <code>&#39;b&#39;</code> to the end</td>
			<td><code>&quot;yab&quot;</code></td>
		</tr>
		<tr>
			<td>Add <code>&#39;x&#39;</code> to the end</td>
			<td><code>&quot;yabx&quot;</code></td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">initial = &quot;xyz&quot;, target = &quot;xyz&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>No operations are needed as the strings are already equal.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= initial.length, target.length &lt;= 1000</code></li>
	<li><code>initial</code> and <code>target</code> consist only of lowercase English letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

Let's assume that the lengths of the strings `initial` and `target` are $m$ and $n$, respectively.

According to the problem description, we only need to find the length $mx$ of the longest common substring of `initial` and `target`. Then, we can delete $m - mx$ characters from `initial` and add $n - mx$ characters to transform `initial` into `target`. Therefore, the answer is $m + n - 2 \times mx$.

We can use dynamic programming to find the length $mx$ of the longest common substring of `initial` and `target`. We define a two-dimensional array $f$, where $f[i][j]$ represents the length of the longest common substring ending with `initial[i - 1]` and `target[j - 1]`. Then, we can get the state transition equation:

$$
f[i][j] = \begin{cases}
f[i - 1][j - 1] + 1, & \textit{if } \textit{initial}[i - 1] = \textit{target}[j - 1], \\
0, & \textit{otherwise}.
\end{cases}
$$

Then $mx = \max f[i][j]$, and the final answer is $m + n - 2 \times mx$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are the lengths of the strings `initial` and `target`, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, initial: str, target: str) -> int:
        m, n = len(initial), len(target)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        mx = 0
        for i, a in enumerate(initial, 1):
            for j, b in enumerate(target, 1):
                if a == b:
                    f[i][j] = f[i - 1][j - 1] + 1
                    mx = max(mx, f[i][j])
        return m + n - mx * 2
```

#### Java

```java
class Solution {
    public int minOperations(String initial, String target) {
        int m = initial.length(), n = target.length();
        int[][] f = new int[m + 1][n + 1];
        int mx = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (initial.charAt(i - 1) == target.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    mx = Math.max(mx, f[i][j]);
                }
            }
        }
        return m + n - 2 * mx;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(string initial, string target) {
        int m = initial.size(), n = target.size();
        int f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        int mx = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (initial[i - 1] == target[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    mx = max(mx, f[i][j]);
                }
            }
        }
        return m + n - 2 * mx;
    }
};
```

#### Go

```go
func minOperations(initial string, target string) int {
	m, n := len(initial), len(target)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	mx := 0
	for i, a := range initial {
		for j, b := range target {
			if a == b {
				f[i+1][j+1] = f[i][j] + 1
				mx = max(mx, f[i+1][j+1])
			}
		}
	}
	return m + n - 2*mx
}
```

#### TypeScript

```ts
function minOperations(initial: string, target: string): number {
    const m = initial.length;
    const n = target.length;
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    let mx: number = 0;
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            if (initial[i - 1] === target[j - 1]) {
                f[i][j] = f[i - 1][j - 1] + 1;
                mx = Math.max(mx, f[i][j]);
            }
        }
    }
    return m + n - 2 * mx;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
