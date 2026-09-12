---
comments: true
difficulty: 中等
tags:
    - 字符串
    - 二分查找
    - 动态规划
    - 滑动窗口
    - 哈希函数
---

<!-- problem:start -->

# [3135. 通过添加或删除结尾字符来同化字符串 🔒](https://leetcode.cn/problems/equalize-strings-by-adding-or-removing-characters-at-ends)

[English Version](/solution/3100-3199/3135.Equalize%20Strings%20by%20Adding%20or%20Removing%20Characters%20at%20Ends/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个字符串&nbsp;<code>initial</code> 和&nbsp;<code>target</code>，你的任务是通过一系列操作改变&nbsp;<code>initial</code>&nbsp;以使它与&nbsp;<code>target</code>&nbsp;相同。</p>

<p>在一次操作中，您只能在&nbsp;<code>initial</code> 字符串开头或结尾添加或删除一个字符。</p>

<p>返回将&nbsp;<code>initial</code>&nbsp;变为&nbsp;<code>target</code>&nbsp;所需的<strong>最小</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">initial = "abcde", target = "cdef"</span></p>

<p><strong>输出：</strong>3</p>

<p><strong>解释：</strong></p>

<p>从&nbsp;<code>initial</code>&nbsp;的开头删除 <code>'a'</code>&nbsp;和&nbsp;<code>'b'</code>&nbsp;并添加&nbsp;<code>'f'</code>&nbsp;到结尾。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">initial = "axxy", target = "yabx"</span></p>

<p><strong>输出：</strong>6</p>

<p><strong>解释：</strong></p>

<table border="1">
	<tbody>
		<tr>
			<th>操作</th>
			<th>结果字符串</th>
		</tr>
		<tr>
			<td>将&nbsp;<code>'y'</code>&nbsp;添加到开头</td>
			<td><code>"yaxxy"</code></td>
		</tr>
		<tr>
			<td>从结尾删除</td>
			<td><code>"yaxx"</code></td>
		</tr>
		<tr>
			<td>从结尾删除</td>
			<td><code>"yax"</code></td>
		</tr>
		<tr>
			<td>从结尾删除</td>
			<td><code>"ya"</code></td>
		</tr>
		<tr>
			<td>将&nbsp;<code>'b'</code>&nbsp;添加到结尾</td>
			<td><code>"yab"</code></td>
		</tr>
		<tr>
			<td>将&nbsp;<code>'x'</code> 添加到结尾</td>
			<td><code>"yabx"</code></td>
		</tr>
	</tbody>
</table>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">initial = "xyz", target = "xyz"</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>不需要任何操作，因为字符串已经相等。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= initial.length, target.length &lt;= 1000</code></li>
	<li><code>initial</code> 和&nbsp;<code>target</code>&nbsp;只包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 只能删前缀/后缀或在两端添加，本质上是保留一段公共连续子串。枚举 $initial$ 每个子串在 $target$ 中查找，时间为 $O(m^2n)$。
>
> 操作次数等于两端丢掉的字符数加上补齐 $target$ 其余部分，即 $m+n-2\cdot mx$，其中 $mx$ 为最长公共子串长度。
>
> 经典 DP：$f[i][j]$ 表示以 $initial[i-1]$ 与 $target[j-1]$ 结尾的公共子串长，相等则由 $f[i-1][j-1]+1$ 转移。取全局最大 $mx$ 即可。

<!-- thinking:end -->

我们不妨假设字符串 `initial` 和 `target` 的长度分别为 $m$ 和 $n$。

根据题目描述，我们只需要求出 `initial` 和 `target` 的最长公共子串的长度 $mx$，那么我们可以从 `initial` 中删除 $m - mx$ 个字符，然后再添加 $n - mx$ 个字符，即可将 `initial` 转换为 `target`，因此答案为 $m + n - 2 \times mx$。

我们可以使用动态规划的方法求出 `initial` 和 `target` 的最长公共子串的长度 $mx$。我们定义一个二维数组 $f$，其中 $f[i][j]$ 表示以 `initial[i - 1]` 和 `target[j - 1]` 结尾的最长公共子串的长度。那么我们可以得到状态转移方程：

$$
f[i][j] = \begin{cases}
f[i - 1][j - 1] + 1, & \textit{if } \textit{initial}[i - 1] = \textit{target}[j - 1], \\
0, & \textit{otherwise}.
\end{cases}
$$

那么 $mx = \max f[i][j]$，最终答案为 $m + n - 2 \times mx$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为字符串 `initial` 和 `target` 的长度。

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
