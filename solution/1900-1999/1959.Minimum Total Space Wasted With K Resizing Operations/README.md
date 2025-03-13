---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1959.Minimum%20Total%20Space%20Wasted%20With%20K%20Resizing%20Operations/README.md
rating: 2310
source: 第 58 场双周赛 Q3
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [1959. K 次调整数组大小浪费的最小总空间](https://leetcode.cn/problems/minimum-total-space-wasted-with-k-resizing-operations)

[English Version](/solution/1900-1999/1959.Minimum%20Total%20Space%20Wasted%20With%20K%20Resizing%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你正在设计一个动态数组。给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;，其中&nbsp;<code>nums[i]</code>&nbsp;是&nbsp;<code>i</code>&nbsp;时刻数组中的元素数目。除此以外，你还有一个整数 <code>k</code>&nbsp;，表示你可以 <strong>调整</strong>&nbsp;数组大小的 <strong>最多</strong>&nbsp;次数（每次都可以调整成 <strong>任意</strong>&nbsp;大小）。</p>

<p><code>t</code>&nbsp;时刻数组的大小&nbsp;<code>size<sub>t</sub></code>&nbsp;必须大于等于&nbsp;<code>nums[t]</code>&nbsp;，因为数组需要有足够的空间容纳所有元素。<code>t</code>&nbsp;时刻 <strong>浪费的空间</strong>&nbsp;为&nbsp;<code>size<sub>t</sub> - nums[t]</code>&nbsp;，<strong>总</strong>&nbsp;浪费空间为满足&nbsp;<code>0 &lt;= t &lt; nums.length</code>&nbsp;的每一个时刻&nbsp;<code>t</code>&nbsp;浪费的空间&nbsp;<strong>之和</strong>&nbsp;。</p>

<p>在调整数组大小不超过 <code>k</code>&nbsp;次的前提下，请你返回 <strong>最小总浪费空间</strong>&nbsp;。</p>

<p><strong>注意：</strong>数组最开始时可以为&nbsp;<strong>任意大小</strong>&nbsp;，且&nbsp;<strong>不计入</strong>&nbsp;调整大小的操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [10,20], k = 0
<b>输出：</b>10
<b>解释：</b>size = [20,20].
我们可以让数组初始大小为 20 。
总浪费空间为 (20 - 10) + (20 - 20) = 10 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [10,20,30], k = 1
<b>输出：</b>10
<b>解释：</b>size = [20,20,30].
我们可以让数组初始大小为 20 ，然后时刻 2 调整大小为 30 。
总浪费空间为 (20 - 10) + (20 - 20) + (30 - 30) = 10 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [10,20,15,30,20], k = 2
<b>输出：</b>15
<b>解释：</b>size = [10,20,20,30,30].
我们可以让数组初始大小为 10 ，时刻 1 调整大小为 20 ，时刻 3 调整大小为 30 。
总浪费空间为 (10 - 10) + (20 - 20) + (20 - 15) + (30 - 30) + (30 - 20) = 15 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= k &lt;= nums.length - 1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

题目等价于我们将数组 $\textit{nums}$ 分成 $k + 1$ 段，那么每一段的浪费空间为该段的最大值乘以该段的长度减去该段的元素之和。我们累加每一段的浪费空间，即可得到总浪费空间。我们将 $k$ 加 $1$，那么就相当于将数组分成 $k$ 段。

因此，我们定义数组 $\textit{g}[i][j]$ 表示 $\textit{nums}[i..j]$ 的最大值乘以 $\textit{nums}[i..j]$ 的长度减去 $\textit{nums}[i..j]$ 的元素之和。我们在 $[0, n)$ 的范围内枚举 $i$，在 $[i, n)$ 的范围内枚举 $j$，用一个变量 $s$ 维护 $\textit{nums}[i..j]$ 的元素之和，用一个变量 $\textit{mx}$ 维护 $\textit{nums}[i..j]$ 的最大值，那么我们可以得到：

$$
\textit{g}[i][j] = \textit{mx} \times (j - i + 1) - s
$$

接下来，我们定义 $\textit{f}[i][j]$ 表示前 $i$ 个元素分成 $j$ 段的最小浪费空间。我们初始化 $\textit{f}[0][0] = 0$，其余位置初始化为无穷大。我们在 $[1, n]$ 的范围内枚举 $i$，在 $[1, k]$ 的范围内枚举 $j$，然后我们枚举前 $j - 1$ 段的最后一个元素 $h$，那么有：

$$
\textit{f}[i][j] = \min(\textit{f}[i][j], \
\textit{f}[h][j - 1] + \textit{g}[h][i - 1])
$$

最终答案为 $\textit{f}[n][k]$。

时间复杂度 $O(n^2 \times k)$，空间复杂度 $O(n \times (n + k))$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSpaceWastedKResizing(self, nums: List[int], k: int) -> int:
        k += 1
        n = len(nums)
        g = [[0] * n for _ in range(n)]
        for i in range(n):
            s = mx = 0
            for j in range(i, n):
                s += nums[j]
                mx = max(mx, nums[j])
                g[i][j] = mx * (j - i + 1) - s
        f = [[inf] * (k + 1) for _ in range(n + 1)]
        f[0][0] = 0
        for i in range(1, n + 1):
            for j in range(1, k + 1):
                for h in range(i):
                    f[i][j] = min(f[i][j], f[h][j - 1] + g[h][i - 1])
        return f[-1][-1]
```

#### Java

```java
class Solution {
    public int minSpaceWastedKResizing(int[] nums, int k) {
        ++k;
        int n = nums.length;
        int[][] g = new int[n][n];
        for (int i = 0; i < n; ++i) {
            int s = 0, mx = 0;
            for (int j = i; j < n; ++j) {
                s += nums[j];
                mx = Math.max(mx, nums[j]);
                g[i][j] = mx * (j - i + 1) - s;
            }
        }
        int[][] f = new int[n + 1][k + 1];
        int inf = 0x3f3f3f3f;
        for (int i = 0; i < f.length; ++i) {
            Arrays.fill(f[i], inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                for (int h = 0; h < i; ++h) {
                    f[i][j] = Math.min(f[i][j], f[h][j - 1] + g[h][i - 1]);
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
    int minSpaceWastedKResizing(vector<int>& nums, int k) {
        ++k;
        int n = nums.size();
        vector<vector<int>> g(n, vector<int>(n));
        for (int i = 0; i < n; ++i) {
            int s = 0, mx = 0;
            for (int j = i; j < n; ++j) {
                mx = max(mx, nums[j]);
                s += nums[j];
                g[i][j] = mx * (j - i + 1) - s;
            }
        }
        int inf = 0x3f3f3f3f;
        vector<vector<int>> f(n + 1, vector<int>(k + 1, inf));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                for (int h = 0; h < i; ++h) {
                    f[i][j] = min(f[i][j], f[h][j - 1] + g[h][i - 1]);
                }
            }
        }
        return f[n][k];
    }
};
```

#### Go

```go
func minSpaceWastedKResizing(nums []int, k int) int {
	k++
	n := len(nums)
	g := make([][]int, n)
	for i := range g {
		g[i] = make([]int, n)
	}
	for i := 0; i < n; i++ {
		s, mx := 0, 0
		for j := i; j < n; j++ {
			s += nums[j]
			mx = max(mx, nums[j])
			g[i][j] = mx*(j-i+1) - s
		}
	}
	f := make([][]int, n+1)
	inf := 0x3f3f3f3f
	for i := range f {
		f[i] = make([]int, k+1)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := 1; j <= k; j++ {
			for h := 0; h < i; h++ {
				f[i][j] = min(f[i][j], f[h][j-1]+g[h][i-1])
			}
		}
	}
	return f[n][k]
}
```

#### TypeScript

```ts
function minSpaceWastedKResizing(nums: number[], k: number): number {
    k += 1;
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(0));

    for (let i = 0; i < n; i++) {
        let s = 0,
            mx = 0;
        for (let j = i; j < n; j++) {
            s += nums[j];
            mx = Math.max(mx, nums[j]);
            g[i][j] = mx * (j - i + 1) - s;
        }
    }

    const inf = Number.POSITIVE_INFINITY;
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(k + 1).fill(inf));
    f[0][0] = 0;

    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= k; j++) {
            for (let h = 0; h < i; h++) {
                f[i][j] = Math.min(f[i][j], f[h][j - 1] + g[h][i - 1]);
            }
        }
    }

    return f[n][k];
}
```

#### Rust

```rust
impl Solution {
    pub fn min_space_wasted_k_resizing(nums: Vec<i32>, k: i32) -> i32 {
        let mut k = k + 1;
        let n = nums.len();
        let mut g = vec![vec![0; n]; n];

        for i in 0..n {
            let (mut s, mut mx) = (0, 0);
            for j in i..n {
                s += nums[j];
                mx = mx.max(nums[j]);
                g[i][j] = mx * (j as i32 - i as i32 + 1) - s;
            }
        }

        let inf = 0x3f3f3f3f;
        let mut f = vec![vec![inf; (k + 1) as usize]; n + 1];
        f[0][0] = 0;

        for i in 1..=n {
            for j in 1..=k as usize {
                for h in 0..i {
                    f[i][j] = f[i][j].min(f[h][j - 1] + g[h][i - 1]);
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
