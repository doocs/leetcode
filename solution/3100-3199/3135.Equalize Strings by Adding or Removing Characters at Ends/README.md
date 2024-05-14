---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3135.Equalize%20Strings%20by%20Adding%20or%20Removing%20Characters%20at%20Ends/README.md
tags:
    - 字符串
    - 二分查找
    - 动态规划
    - 滑动窗口
    - 哈希函数
---

# [3135. Equalize Strings by Adding or Removing Characters at Ends 🔒](https://leetcode.cn/problems/equalize-strings-by-adding-or-removing-characters-at-ends)

[English Version](/solution/3100-3199/3135.Equalize%20Strings%20by%20Adding%20or%20Removing%20Characters%20at%20Ends/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一：动态规划

我们不妨假设字符串 `initial` 和 `target` 的长度分别为 $m$ 和 $n$。

根据题目描述，我们只需要求出 `initial` 和 `target` 的最长公共子串的长度 $mx$，那么我们可以从 `initial` 中删除 $m - mx$ 个字符，然后再添加 $n - mx$ 个字符，即可将 `initial` 转换为 `target`，因此答案为 $m + n - 2 \times mx$。

我们可以使用动态规划的方法求出 `initial` 和 `target` 的最长公共子串的长度 $mx$。我们定义一个二维数组 $f$，其中 $f[i][j]$ 表示以 `initial[i - 1]` 和 `target[j - 1]` 结尾的最长公共子串的长度。那么我们可以得到状态转移方程：

$$
f[i][j] = \begin{cases}
f[i - 1][j - 1] + 1, & \text{if } \text{initial}[i - 1] = \text{target}[j - 1], \\
0, & \text{otherwise}.
\end{cases}
$$

那么 $mx = \max f[i][j]$，最终答案为 $m + n - 2 \times mx$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为字符串 `initial` 和 `target` 的长度。

<!-- tabs:start -->

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

<!-- end -->
