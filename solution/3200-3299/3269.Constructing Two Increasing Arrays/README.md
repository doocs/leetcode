---
comments: true
difficulty: 困难
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3269. 构建两个递增数组 🔒](https://leetcode.cn/problems/constructing-two-increasing-arrays)

[English Version](/solution/3200-3299/3269.Constructing%20Two%20Increasing%20Arrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个只包含 0 和 1 的整数数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>，你的任务是执行下面操作后使数组 <code>nums1</code> 和 <code>nums2</code> 中 <strong>最大</strong> 可达数字 <strong>尽可能小</strong>。</p>

<p>将每个 0 替换为正偶数，将每个 1 替换为正奇数。在替换后，两个数组都应该&nbsp;<strong>递增</strong>&nbsp;并且每个整数&nbsp;<strong>至多</strong>&nbsp;被使用一次。</p>

<p>返回执行操作后最小的最大可达数字。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums1 = [], nums2 = [1,0,1,1]</span></p>

<p><span class="example-io"><b>输出：</b>5</span></p>

<p><strong>解释：</strong></p>

<p>在替换之后， <code>nums1 = []</code>&nbsp;与&nbsp;<code>nums2 = [1, 2, 3, 5]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums1 = [0,1,0,1], nums2 = [1,0,0,1]</span></p>

<p><span class="example-io"><b>输出：</b>9</span></p>

<p><strong>解释：</strong></p>

<p>有最大元素 9 的一种替换方式， <code>nums1 = [2, 3, 8, 9]</code>&nbsp;与&nbsp;<code>nums2 = [1, 4, 6, 7]</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums1 = [0,1,0,0,1], nums2 = [0,0,0,1]</span></p>

<p><span class="example-io"><b>输出：</b>13</span></p>

<p><strong>解释：</strong></p>

<p>有最大元素 13 的一种替换方式，<code>nums1 = [2, 3, 4, 6, 7]</code>&nbsp;与&nbsp;<code>nums2 = [8, 10, 12, 13]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= nums1.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums2.length &lt;= 1000</code></li>
	<li><code>nums1</code>&nbsp;和&nbsp;<code>nums2</code>&nbsp;只包含 0 和 1。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 两个数组要被填成严格递增，且奇偶由 $0/1$ 指定，最小化最终的最大数。$m,n\le 1000$，枚举最大值再判定可行可以，但填数过程有最优子结构。
>
> $f[i][j]$ 为处理完 $nums1$ 前 $i$ 个与 $nums2$ 前 $j$ 个时的最小最大数。下一步只能接到其中一个数组，取比当前最大数大且奇偶正确的最小整数。边界只沿一条数组走，中间取两种衔接的较小者。

<!-- thinking:end -->

我们定义 $f[i][j]$ 表示数组 $\textit{nums1}$ 的前 $i$ 个元素和数组 $\textit{nums2}$ 的前 $j$ 个元素中，最小的最大值。初始时 $f[i][j] = 0$，答案为 $f[m][n]$，其中 $m$ 和 $n$ 分别是数组 $\textit{nums1}$ 和 $\textit{nums2}$ 的长度。

如果 $j = 0$，那么 $f[i][0]$ 的值只能由 $f[i - 1][0]$ 转移得到，转移方程为 $f[i][0] = \textit{nxt}(f[i - 1][0], \textit{nums1}[i - 1])$，其中 $\textit{nxt}(x, y)$ 表示比 $x$ 大且奇偶性与 $y$ 相同的最小整数。

如果 $i = 0$，那么 $f[0][j]$ 的值只能由 $f[0][j - 1]$ 转移得到，转移方程为 $f[0][j] = \textit{nxt}(f[0][j - 1], \textit{nums2}[j - 1])$。

如果 $i > 0$ 且 $j > 0$，那么 $f[i][j]$ 的值可以由 $f[i - 1][j]$ 和 $f[i][j - 1]$ 转移得到，转移方程为 $f[i][j] = \min(\textit{nxt}(f[i - 1][j], \textit{nums1}[i - 1]), \textit{nxt}(f[i][j - 1], \textit{nums2}[j - 1]))$。

最后返回 $f[m][n]$ 即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是数组 $\textit{nums1}$ 和 $\textit{nums2}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minLargest(self, nums1: List[int], nums2: List[int]) -> int:
        def nxt(x: int, y: int) -> int:
            return x + 1 if (x & 1 ^ y) == 1 else x + 2

        m, n = len(nums1), len(nums2)
        f = [[0] * (n + 1) for _ in range(m + 1)]
        for i, x in enumerate(nums1, 1):
            f[i][0] = nxt(f[i - 1][0], x)
        for j, y in enumerate(nums2, 1):
            f[0][j] = nxt(f[0][j - 1], y)
        for i, x in enumerate(nums1, 1):
            for j, y in enumerate(nums2, 1):
                f[i][j] = min(nxt(f[i - 1][j], x), nxt(f[i][j - 1], y))
        return f[m][n]
```

#### Java

```java
class Solution {
    public int minLargest(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            f[i][0] = nxt(f[i - 1][0], nums1[i - 1]);
        }
        for (int j = 1; j <= n; ++j) {
            f[0][j] = nxt(f[0][j - 1], nums2[j - 1]);
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int x = nxt(f[i - 1][j], nums1[i - 1]);
                int y = nxt(f[i][j - 1], nums2[j - 1]);
                f[i][j] = Math.min(x, y);
            }
        }
        return f[m][n];
    }

    private int nxt(int x, int y) {
        return (x & 1 ^ y) == 1 ? x + 1 : x + 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minLargest(vector<int>& nums1, vector<int>& nums2) {
        int m = nums1.size(), n = nums2.size();
        int f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        auto nxt = [](int x, int y) -> int {
            return (x & 1 ^ y) == 1 ? x + 1 : x + 2;
        };
        for (int i = 1; i <= m; ++i) {
            f[i][0] = nxt(f[i - 1][0], nums1[i - 1]);
        }
        for (int j = 1; j <= n; ++j) {
            f[0][j] = nxt(f[0][j - 1], nums2[j - 1]);
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                int x = nxt(f[i - 1][j], nums1[i - 1]);
                int y = nxt(f[i][j - 1], nums2[j - 1]);
                f[i][j] = min(x, y);
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func minLargest(nums1 []int, nums2 []int) int {
	m, n := len(nums1), len(nums2)
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	nxt := func(x, y int) int {
		if (x&1 ^ y) == 1 {
			return x + 1
		}
		return x + 2
	}
	for i := 1; i <= m; i++ {
		f[i][0] = nxt(f[i-1][0], nums1[i-1])
	}
	for j := 1; j <= n; j++ {
		f[0][j] = nxt(f[0][j-1], nums2[j-1])
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			x := nxt(f[i-1][j], nums1[i-1])
			y := nxt(f[i][j-1], nums2[j-1])
			f[i][j] = min(x, y)
		}
	}
	return f[m][n]
}
```

#### TypeScript

```ts
function minLargest(nums1: number[], nums2: number[]): number {
    const m = nums1.length;
    const n = nums2.length;
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    const nxt = (x: number, y: number): number => {
        return (x & 1) ^ y ? x + 1 : x + 2;
    };
    for (let i = 1; i <= m; ++i) {
        f[i][0] = nxt(f[i - 1][0], nums1[i - 1]);
    }
    for (let j = 1; j <= n; ++j) {
        f[0][j] = nxt(f[0][j - 1], nums2[j - 1]);
    }
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i][j] = Math.min(nxt(f[i - 1][j], nums1[i - 1]), nxt(f[i][j - 1], nums2[j - 1]));
        }
    }
    return f[m][n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
