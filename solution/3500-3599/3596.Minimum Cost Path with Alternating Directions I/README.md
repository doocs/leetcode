---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3596.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20I/README.md
tags:
    - 脑筋急转弯
    - 数学
---

<!-- problem:start -->

# [3596. 交替方向的最小路径代价 I 🔒](https://leetcode.cn/problems/minimum-cost-path-with-alternating-directions-i)

[English Version](/solution/3500-3599/3596.Minimum%20Cost%20Path%20with%20Alternating%20Directions%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定两个整数&nbsp;<code>m</code> 和&nbsp;<code>n</code>&nbsp;分别表示一个网格的行数和列数。</p>

<p>进入单元格&nbsp;<code>(i, j)</code>&nbsp;的花费定义为&nbsp;<code>(i + 1) * (j + 1)</code>。</p>

<p>路径始终从第 1 步进入单元格 <code>(0, 0)</code> 并支付入场花费开始。</p>

<p>在每一步，你移动到 <strong>相邻</strong>&nbsp;的单元格，遵循交替的模式：</p>

<ul>
	<li>在 <strong>奇数次</strong> 移动，你必须向 <strong>右方</strong> 或 <strong>下方</strong> 移动。</li>
	<li>在 <strong>偶数次</strong> 移动，你必须向 <strong>左方</strong> 或 <strong>上方</strong> 移动。</li>
</ul>

<p>返回到达 <code>(m - 1, n - 1)</code>&nbsp;的最小总花费。如果不可能到达，返回 -1。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">m = 1, n = 1</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>你从单元格&nbsp;<code>(0, 0)</code>&nbsp;开始。</li>
	<li>进入&nbsp;<code>(0, 0)</code>&nbsp;的花费是&nbsp;<code>(0 + 1) * (0 + 1) = 1</code>。</li>
	<li>由于你已经到达了目标，总花费为 1。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">m = 2, n = 1</span></p>

<p><span class="example-io"><b>输出：</b>3</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>你从单元格&nbsp;<code>(0, 0)</code>&nbsp;开始，花费为&nbsp;<code>(0 + 1) * (0 + 1) = 1</code>。</li>
	<li>第 1 次移动（奇数次）：你可以向下移动到&nbsp;<code>(1, 0)</code>，花费为&nbsp;<code>(1 + 1) * (0 + 1) = 2</code>。</li>
	<li>因此，总花费是&nbsp;<code>1 + 2 = 3</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m, n &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

<!-- thinking:start -->

> **思考**
>
> 交替方向的花费规则使得大多数网格无法到达终点。枚举可达情形后只剩 $1\times 1$（花费 $1$）以及 $2\times 1$、$1\times 2$（花费 $3$）。
>
> 其余规模返回 $-1$。判断三个特解即可，不必做最短路。

<!-- thinking:end -->

由于题目中给定的移动规则，实际上只有以下三种情况可以到达目标单元格：

1. 行列数为 $1 \times 1$ 的网格，花费为 $1$。
2. 行数为 $2$，列数为 $1$ 的网格，花费为 $3$。
3. 行数为 $1$，列数为 $2$ 的网格，花费为 $3$。

对于其他情况，无法到达目标单元格，返回 $-1$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(self, m: int, n: int) -> int:
        if m == 1 and n == 1:
            return 1
        if m == 2 and n == 1:
            return 3
        if m == 1 and n == 2:
            return 3
        return -1
```

#### Java

```java
class Solution {
    public int minCost(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }
        if (m == 1 && n == 2) {
            return 3;
        }
        if (m == 2 && n == 1) {
            return 3;
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCost(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }
        if (m == 1 && n == 2) {
            return 3;
        }
        if (m == 2 && n == 1) {
            return 3;
        }
        return -1;
    }
};
```

#### Go

```go
func minCost(m int, n int) int {
	if m == 1 && n == 1 {
		return 1
	}
	if m == 1 && n == 2 {
		return 3
	}
	if m == 2 && n == 1 {
		return 3
	}
	return -1
}
```

#### TypeScript

```ts
function minCost(m: number, n: number): number {
    if (m === 1 && n === 1) {
        return 1;
    }
    if (m === 1 && n === 2) {
        return 3;
    }
    if (m === 2 && n === 1) {
        return 3;
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
