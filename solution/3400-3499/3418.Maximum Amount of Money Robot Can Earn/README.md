---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3418.Maximum%20Amount%20of%20Money%20Robot%20Can%20Earn/README.md
rating: 1798
source: 第 432 场周赛 Q2
tags:
    - 数组
    - 动态规划
    - 矩阵
---

<!-- problem:start -->

# [3418. 机器人可以获得的最大金币数](https://leetcode.cn/problems/maximum-amount-of-money-robot-can-earn)

[English Version](/solution/3400-3499/3418.Maximum%20Amount%20of%20Money%20Robot%20Can%20Earn/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <code>m x n</code> 的网格。一个机器人从网格的左上角 <code>(0, 0)</code> 出发，目标是到达网格的右下角 <code>(m - 1, n - 1)</code>。在任意时刻，机器人只能向右或向下移动。</p>

<p>网格中的每个单元格包含一个值 <code>coins[i][j]</code>：</p>

<ul>
	<li>如果 <code>coins[i][j] &gt;= 0</code>，机器人可以获得该单元格的金币。</li>
	<li>如果 <code>coins[i][j] &lt; 0</code>，机器人会遇到一个强盗，强盗会抢走该单元格数值的&nbsp;<strong>绝对值</strong> 的金币。</li>
</ul>

<p>机器人有一项特殊能力，可以在行程中&nbsp;<strong>最多感化&nbsp;</strong>2个单元格的强盗，从而防止这些单元格的金币被抢走。</p>

<p><strong>注意：</strong>机器人的总金币数可以是负数。</p>

<p>返回机器人在路径上可以获得的&nbsp;<strong>最大金币数&nbsp;</strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">coins = [[0,1,-1],[1,-2,3],[2,-3,4]]</span></p>

<p><strong>输出：</strong> <span class="example-io">8</span></p>

<p><strong>解释：</strong></p>

<p>一个获得最多金币的最优路径如下：</p>

<ol>
	<li>从 <code>(0, 0)</code> 出发，初始金币为 <code>0</code>（总金币 = <code>0</code>）。</li>
	<li>移动到 <code>(0, 1)</code>，获得 <code>1</code> 枚金币（总金币 = <code>0 + 1 = 1</code>）。</li>
	<li>移动到 <code>(1, 1)</code>，遇到强盗抢走 <code>2</code> 枚金币。机器人在此处使用一次感化能力，避免被抢（总金币 = <code>1</code>）。</li>
	<li>移动到 <code>(1, 2)</code>，获得 <code>3</code> 枚金币（总金币 = <code>1 + 3 = 4</code>）。</li>
	<li>移动到 <code>(2, 2)</code>，获得 <code>4</code> 枚金币（总金币 = <code>4 + 4 = 8</code>）。</li>
</ol>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">coins = [[10,10,10],[10,10,10]]</span></p>

<p><strong>输出：</strong> <span class="example-io">40</span></p>

<p><strong>解释：</strong></p>

<p>一个获得最多金币的最优路径如下：</p>

<ol>
	<li>从 <code>(0, 0)</code> 出发，初始金币为 <code>10</code>（总金币 = <code>10</code>）。</li>
	<li>移动到 <code>(0, 1)</code>，获得 <code>10</code> 枚金币（总金币 = <code>10 + 10 = 20</code>）。</li>
	<li>移动到 <code>(0, 2)</code>，再获得 <code>10</code> 枚金币（总金币 = <code>20 + 10 = 30</code>）。</li>
	<li>移动到 <code>(1, 2)</code>，获得 <code>10</code> 枚金币（总金币 = <code>30 + 10 = 40</code>）。</li>
</ol>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == coins.length</code></li>
	<li><code>n == coins[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>-1000 &lt;= coins[i][j] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $\textit{dfs}(i, j, k)$，表示机器人从 $(i, j)$ 出发，还剩下 $k$ 次感化机会时，能够获得的最大金币数。机器人只能向右或向下移动，因此 $\textit{dfs}(i, j, k)$ 的值只与 $\textit{dfs}(i + 1, j, k)$ 和 $\textit{dfs}(i, j + 1, k)$ 有关。

- 如果 $i \geq m$ 或 $j \geq n$，表示机器人走出了网格，此时返回一个极小值。
- 如果 $i = m - 1$ 且 $j = n - 1$，表示机器人到达了网格的右下角，此时如果 $k > 0$，则机器人可以选择感化当前位置的强盗，因此返回 $\max(0, \textit{coins}[i][j])$；如果 $k = 0$，则机器人不能感化当前位置的强盗，因此返回 $\textit{coins}[i][j]$。
- 如果 $\textit{coins}[i][j] < 0$，表示当前位置有强盗，此时如果 $k > 0$，则机器人可以选择感化当前位置的强盗，因此返回 $\textit{coins}[i][j] + \max(\textit{dfs}(i + 1, j, k), \textit{dfs}(i, j + 1, k))$；如果 $k = 0$，则机器人不能感化当前位置的强盗，因此返回 $\textit{coins}[i][j] + \max(\textit{dfs}(i + 1, j, k), \textit{dfs}(i, j + 1, k))$。

根据上述分析，我们可以编写出记忆化搜索的代码。

时间复杂度 $O(m \times n \times k)$，空间复杂度 $O(m \times n \times k)$。其中 $m$ 和 $n$ 分别是二维数组 $\textit{coins}$ 的行数和列数，而 $k$ 是感化机会的状态数，本题中 $k = 3$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumAmount(self, coins: List[List[int]]) -> int:
        @cache
        def dfs(i: int, j: int, k: int) -> int:
            if i >= m or j >= n:
                return -inf
            if i == m - 1 and j == n - 1:
                return max(coins[i][j], 0) if k else coins[i][j]
            ans = coins[i][j] + max(dfs(i + 1, j, k), dfs(i, j + 1, k))
            if coins[i][j] < 0 and k:
                ans = max(ans, dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1))
            return ans

        m, n = len(coins), len(coins[0])
        return dfs(0, 0, 2)
```

#### Java

```java
class Solution {
    private Integer[][][] f;
    private int[][] coins;
    private int m;
    private int n;

    public int maximumAmount(int[][] coins) {
        m = coins.length;
        n = coins[0].length;
        this.coins = coins;
        f = new Integer[m][n][3];
        return dfs(0, 0, 2);
    }

    private int dfs(int i, int j, int k) {
        if (i >= m || j >= n) {
            return Integer.MIN_VALUE / 2;
        }
        if (f[i][j][k] != null) {
            return f[i][j][k];
        }
        if (i == m - 1 && j == n - 1) {
            return k > 0 ? Math.max(0, coins[i][j]) : coins[i][j];
        }
        int ans = coins[i][j] + Math.max(dfs(i + 1, j, k), dfs(i, j + 1, k));
        if (coins[i][j] < 0 && k > 0) {
            ans = Math.max(ans, Math.max(dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1)));
        }
        return f[i][j][k] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumAmount(vector<vector<int>>& coins) {
        int m = coins.size(), n = coins[0].size();
        vector<vector<vector<int>>> f(m, vector<vector<int>>(n, vector<int>(3, -1)));
        auto dfs = [&](this auto&& dfs, int i, int j, int k) -> int {
            if (i >= m || j >= n) {
                return INT_MIN / 2;
            }
            if (f[i][j][k] != -1) {
                return f[i][j][k];
            }
            if (i == m - 1 && j == n - 1) {
                return k > 0 ? max(0, coins[i][j]) : coins[i][j];
            }
            int ans = coins[i][j] + max(dfs(i + 1, j, k), dfs(i, j + 1, k));
            if (coins[i][j] < 0 && k > 0) {
                ans = max({ans, dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1)});
            }
            return f[i][j][k] = ans;
        };
        return dfs(0, 0, 2);
    }
};
```

#### Go

```go
func maximumAmount(coins [][]int) int {
	m, n := len(coins), len(coins[0])
	f := make([][][]int, m)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, 3)
			for k := range f[i][j] {
				f[i][j][k] = math.MinInt32
			}
		}
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i >= m || j >= n {
			return math.MinInt32 / 2
		}
		if f[i][j][k] != math.MinInt32 {
			return f[i][j][k]
		}
		if i == m-1 && j == n-1 {
			if k > 0 {
				return max(0, coins[i][j])
			}
			return coins[i][j]
		}
		ans := coins[i][j] + max(dfs(i+1, j, k), dfs(i, j+1, k))
		if coins[i][j] < 0 && k > 0 {
			ans = max(ans, max(dfs(i+1, j, k-1), dfs(i, j+1, k-1)))
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, 0, 2)
}
```

#### TypeScript

```ts
function maximumAmount(coins: number[][]): number {
    const [m, n] = [coins.length, coins[0].length];
    const f = Array.from({ length: m }, () =>
        Array.from({ length: n }, () => Array(3).fill(-Infinity)),
    );
    const dfs = (i: number, j: number, k: number): number => {
        if (i >= m || j >= n) {
            return -Infinity;
        }
        if (f[i][j][k] !== -Infinity) {
            return f[i][j][k];
        }
        if (i === m - 1 && j === n - 1) {
            return k > 0 ? Math.max(0, coins[i][j]) : coins[i][j];
        }
        let ans = coins[i][j] + Math.max(dfs(i + 1, j, k), dfs(i, j + 1, k));
        if (coins[i][j] < 0 && k > 0) {
            ans = Math.max(ans, dfs(i + 1, j, k - 1), dfs(i, j + 1, k - 1));
        }
        return (f[i][j][k] = ans);
    };
    return dfs(0, 0, 2);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
