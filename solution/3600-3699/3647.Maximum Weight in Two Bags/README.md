---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3647.Maximum%20Weight%20in%20Two%20Bags/README.md
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3647. 两个袋子中的最大重量 🔒](https://leetcode.cn/problems/maximum-weight-in-two-bags)

[English Version](/solution/3600-3699/3647.Maximum%20Weight%20in%20Two%20Bags/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组&nbsp;<code>weights</code>&nbsp;和两个整数&nbsp;<code>w1</code> 和&nbsp;<code>w2</code>&nbsp;表示两个袋子的 <strong>最大</strong>&nbsp;容量。</p>

<p>每个物品 <strong>最多</strong> 可以放入一个袋子中，使得：</p>

<ul>
	<li>袋子 1 <strong>最多</strong> 总共可以装&nbsp;<code>w1</code>&nbsp;重量。</li>
	<li>袋子 2&nbsp;<strong>最多</strong> 总共可以装&nbsp;<code>w2</code>&nbsp;重量。</li>
</ul>

<p>返回两个袋子可以装入的 <strong>最大</strong> 总重量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>weights = [1,4,3,2], w1 = 5, w2 = 4</span></p>

<p><span class="example-io"><b>输出：</b>9</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>袋子 1：放入&nbsp;<code>weights[2] = 3</code> 和&nbsp;<code>weights[3] = 2</code> 满足&nbsp;<code>3 + 2 = 5 &lt;= w1</code></li>
	<li>袋子 2：放入&nbsp;<code>weights[1] = 4</code> 满足&nbsp;<code>4 &lt;= w2</code></li>
	<li>总重量：<code>5 + 4 = 9</code></li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>weights = [3,6,4,8], w1 = 9, w2 = 7</span></p>

<p><span class="example-io"><b>输出：</b>15</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>袋子 1：放入&nbsp;<code>weights[3] = 8</code> 满足&nbsp;<code>8 &lt;= w1</code></li>
	<li>袋子 2：放入&nbsp;<code>weights[0] = 3</code> 和&nbsp;<code>weights[2] = 4</code> 满足&nbsp;<code>3 + 4 = 7 &lt;= w2</code></li>
	<li>总重量：<code>8 + 7 = 15</code></li>
</ul>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">weights = [5,7], w1 = 2, w2 = 3</span></p>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>没有可以放入两个袋子中的重量，所以答案为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= weights.length &lt;= 100</code></li>
	<li><code>1 &lt;= weights[i] &lt;= 100</code></li>
	<li><code>1 &lt;= w1, w2 &lt;= 300</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

<!-- thinking:start -->

> **思考**
>
> 每个物品可放入容量 $w_1$ 或 $w_2$ 的袋子，或放弃。这是二维 $0$-$1$ 背包。$n\le 100$、$w\le 300$，三维状态可再压成两维。
>
> $f[j][k]$ 表示两袋剩余容量为 $j,k$ 时的最大重量。倒序枚举容量，避免同一物品被使用两次。
>
> 对物品 $x$，尝试 $f[j-x][k]+x$ 与 $f[j][k-x]+x$。答案为 $f[w_1][w_2]$。

<!-- thinking:end -->

我们定义 $f[i][j][k]$ 表示前 $i$ 个物品放入两个袋子中，袋子 1 的最大容量为 $j$，袋子 2 的最大容量为 $k$ 时的最大总重量。初始时 $f[0][j][k] = 0$，表示没有物品可放入袋子中。

状态转移方程为：

$$
f[i][j][k] = \max(f[i-1][j][k], f[i-1][j-w_i][k], f[i-1][j][k-w_i]) \quad (w_i \leq j \text{ or } w_i \leq k)
$$

其中 $w_i$ 表示第 $i$ 个物品的重量。

最终答案为 $f[n][w1][w2]$，其中 $n$ 为物品数量。

我们注意到状态转移方程中只依赖于前一层的状态，因此可以将三维 DP 数组压缩为二维 DP 数组。在枚举 $j$ 和 $k$ 时，我们采用倒序遍历的方式。

时间复杂度 $O(n \times w1 \times w2)$，空间复杂度 $O(w1 \times w2)$。其中 $n$ 是数组 $\textit{weights}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxWeight(self, weights: List[int], w1: int, w2: int) -> int:
        f = [[0] * (w2 + 1) for _ in range(w1 + 1)]
        max = lambda a, b: a if a > b else b
        for x in weights:
            for j in range(w1, -1, -1):
                for k in range(w2, -1, -1):
                    if x <= j:
                        f[j][k] = max(f[j][k], f[j - x][k] + x)
                    if x <= k:
                        f[j][k] = max(f[j][k], f[j][k - x] + x)
        return f[w1][w2]
```

#### Java

```java
class Solution {
    public int maxWeight(int[] weights, int w1, int w2) {
        int[][] f = new int[w1 + 1][w2 + 1];
        for (int x : weights) {
            for (int j = w1; j >= 0; --j) {
                for (int k = w2; k >= 0; --k) {
                    if (x <= j) {
                        f[j][k] = Math.max(f[j][k], f[j - x][k] + x);
                    }
                    if (x <= k) {
                        f[j][k] = Math.max(f[j][k], f[j][k - x] + x);
                    }
                }
            }
        }
        return f[w1][w2];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxWeight(vector<int>& weights, int w1, int w2) {
        vector<vector<int>> f(w1 + 1, vector<int>(w2 + 1));
        for (int x : weights) {
            for (int j = w1; j >= 0; --j) {
                for (int k = w2; k >= 0; --k) {
                    if (x <= j) {
                        f[j][k] = max(f[j][k], f[j - x][k] + x);
                    }
                    if (x <= k) {
                        f[j][k] = max(f[j][k], f[j][k - x] + x);
                    }
                }
            }
        }
        return f[w1][w2];
    }
};
```

#### Go

```go
func maxWeight(weights []int, w1 int, w2 int) int {
	f := make([][]int, w1+1)
	for i := range f {
		f[i] = make([]int, w2+1)
	}
	for _, x := range weights {
		for j := w1; j >= 0; j-- {
			for k := w2; k >= 0; k-- {
				if x <= j {
					f[j][k] = max(f[j][k], f[j-x][k]+x)
				}
				if x <= k {
					f[j][k] = max(f[j][k], f[j][k-x]+x)
				}
			}
		}
	}
	return f[w1][w2]
}
```

#### TypeScript

```ts
function maxWeight(weights: number[], w1: number, w2: number): number {
    const f: number[][] = Array.from({ length: w1 + 1 }, () => Array(w2 + 1).fill(0));
    for (const x of weights) {
        for (let j = w1; j >= 0; j--) {
            for (let k = w2; k >= 0; k--) {
                if (x <= j) {
                    f[j][k] = Math.max(f[j][k], f[j - x][k] + x);
                }
                if (x <= k) {
                    f[j][k] = Math.max(f[j][k], f[j][k - x] + x);
                }
            }
        }
    }
    return f[w1][w2];
}
```

#### Rust

```rust
impl Solution {
    pub fn max_weight(weights: Vec<i32>, w1: i32, w2: i32) -> i32 {
        let w1 = w1 as usize;
        let w2 = w2 as usize;
        let mut f = vec![vec![0; w2 + 1]; w1 + 1];
        for &x in &weights {
            let x = x as usize;
            for j in (0..=w1).rev() {
                for k in (0..=w2).rev() {
                    if x <= j {
                        f[j][k] = f[j][k].max(f[j - x][k] + x as i32);
                    }
                    if x <= k {
                        f[j][k] = f[j][k].max(f[j][k - x] + x as i32);
                    }
                }
            }
        }
        f[w1][w2]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
