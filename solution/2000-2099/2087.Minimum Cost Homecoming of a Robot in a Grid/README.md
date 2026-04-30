---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2087.Minimum%20Cost%20Homecoming%20of%20a%20Robot%20in%20a%20Grid/README.md
rating: 1743
source: 第 66 场双周赛 Q3
tags:
    - 贪心
    - 数组
---

<!-- problem:start -->

# [2087. 网格图中机器人回家的最小代价](https://leetcode.cn/problems/minimum-cost-homecoming-of-a-robot-in-a-grid)

[English Version](/solution/2000-2099/2087.Minimum%20Cost%20Homecoming%20of%20a%20Robot%20in%20a%20Grid/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>m x n</code>&nbsp;的网格图，其中&nbsp;<code>(0, 0)</code>&nbsp;是最左上角的格子，<code>(m - 1, n - 1)</code>&nbsp;是最右下角的格子。给你一个整数数组&nbsp;<code>startPos</code>&nbsp;，<code>startPos = [start<sub>row</sub>, start<sub>col</sub>]</code>&nbsp;表示 <strong>初始</strong>&nbsp;有一个 <strong>机器人</strong>&nbsp;在格子&nbsp;<code>(start<sub>row</sub>, start<sub>col</sub>)</code>&nbsp;处。同时给你一个整数数组&nbsp;<code>homePos</code>&nbsp;，<code>homePos = [home<sub>row</sub>, home<sub>col</sub>]</code>&nbsp;表示机器人的 <strong>家</strong>&nbsp;在格子&nbsp;<code>(home<sub>row</sub>, home<sub>col</sub>)</code>&nbsp;处。</p>

<p>机器人需要回家。每一步它可以往四个方向移动：<strong>上</strong>，<strong>下</strong>，<strong>左</strong>，<strong>右</strong>，同时机器人不能移出边界。每一步移动都有一定代价。再给你两个下标从&nbsp;<strong>0</strong>&nbsp;开始的整数数组：长度为&nbsp;<code>m</code>&nbsp;的数组&nbsp;<code>rowCosts</code> &nbsp;和长度为 <code>n</code>&nbsp;的数组&nbsp;<code>colCosts</code>&nbsp;。</p>

<ul>
	<li>如果机器人往 <strong>上</strong>&nbsp;或者往 <strong>下</strong>&nbsp;移动到第 <code>r</code>&nbsp;<strong>行</strong>&nbsp;的格子，那么代价为&nbsp;<code>rowCosts[r]</code>&nbsp;。</li>
	<li>如果机器人往 <strong>左</strong>&nbsp;或者往 <strong>右</strong>&nbsp;移动到第 <code>c</code>&nbsp;<strong>列</strong> 的格子，那么代价为&nbsp;<code>colCosts[c]</code>&nbsp;。</li>
</ul>

<p>请你返回机器人回家需要的 <strong>最小总代价</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2087.Minimum%20Cost%20Homecoming%20of%20a%20Robot%20in%20a%20Grid/images/eg-1.png" style="width: 282px; height: 217px;" /></p>

<pre>
<strong>输入：</strong>startPos = [1, 0], homePos = [2, 3], rowCosts = [5, 4, 3], colCosts = [8, 2, 6, 7]
<b>输出：</b>18
<b>解释：</b>一个最优路径为：
从 (1, 0) 开始
-&gt; 往下走到 (<em><strong>2</strong></em>, 0) 。代价为 rowCosts[2] = 3 。
-&gt; 往右走到 (2, <em><strong>1</strong></em>) 。代价为 colCosts[1] = 2 。
-&gt; 往右走到 (2, <em><strong>2</strong></em>) 。代价为 colCosts[2] = 6 。
-&gt; 往右走到 (2, <em><strong>3</strong></em>) 。代价为 colCosts[3] = 7 。
总代价为 3 + 2 + 6 + 7 = 18</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>startPos = [0, 0], homePos = [0, 0], rowCosts = [5], colCosts = [26]
<b>输出：</b>0
<b>解释：</b>机器人已经在家了，所以不需要移动。总代价为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == rowCosts.length</code></li>
	<li><code>n == colCosts.length</code></li>
	<li><code>1 &lt;= m, n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= rowCosts[r], colCosts[c] &lt;= 10<sup>4</sup></code></li>
	<li><code>startPos.length == 2</code></li>
	<li><code>homePos.length == 2</code></li>
	<li><code>0 &lt;= start<sub>row</sub>, home<sub>row</sub> &lt; m</code></li>
	<li><code>0 &lt;= start<sub>col</sub>, home<sub>col</sub> &lt; n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们不妨假设机器人的初始位置为 $(x_0, y_0)$，家所在的位置为 $(x_1, y_1)$。

如果 $x_0 < x_1$，那么机器人需要往下走，需要经过的行是 $[x_0 + 1, x_1]$，总代价为 $\sum_{i = x_0 + 1}^{x_1} rowCosts[i]$；如果 $x_0 > x_1$，那么机器人需要往上走，需要经过的行是 $[x_1, x_0 - 1]$，总代价为 $\sum_{i = x_1}^{x_0 - 1} rowCosts[i]$；如果 $x_0 = x_1$，那么机器人不需要往上下走，总代价为 $0$。

同理，如果 $y_0 < y_1$，那么机器人需要往右走，需要经过的列是 $[y_0 + 1, y_1]$，总代价为 $\sum_{j = y_0 + 1}^{y_1} colCosts[j]$；如果 $y_0 > y_1$，那么机器人需要往左走，需要经过的列是 $[y_1, y_0 - 1]$，总代价为 $\sum_{j = y_1}^{y_0 - 1} colCosts[j]$；如果 $y_0 = y_1$，那么机器人不需要往左右走，总代价为 $0$。

答案为上下走的总代价与左右走的总代价之和。

时间复杂度 $O(m + n)$，其中 $m$ 和 $n$ 分别是 $rowCosts$ 和 $colCosts$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minCost(
        self,
        startPos: List[int],
        homePos: List[int],
        rowCosts: List[int],
        colCosts: List[int],
    ) -> int:
        x0, y0 = startPos
        x1, y1 = homePos
        dx = sum(rowCosts[x0 + 1 : x1 + 1]) if x0 < x1 else sum(rowCosts[x1:x0])
        dy = sum(colCosts[y0 + 1 : y1 + 1]) if y0 < y1 else sum(colCosts[y1:y0])
        return dx + dy
```

#### Java

```java
class Solution {
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int x0 = startPos[0], y0 = startPos[1];
        int x1 = homePos[0], y1 = homePos[1];
        int dx = x0 < x1 ? calc(rowCosts, x0 + 1, x1) : calc(rowCosts, x1, x0 - 1);
        int dy = y0 < y1 ? calc(colCosts, y0 + 1, y1) : calc(colCosts, y1, y0 - 1);
        return dx + dy;
    }

    private int calc(int[] nums, int i, int j) {
        int res = 0;
        for (int k = i; k <= j; ++k) {
            res += nums[k];
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minCost(vector<int>& startPos, vector<int>& homePos, vector<int>& rowCosts, vector<int>& colCosts) {
        auto calc = [](vector<int>& nums, int i, int j) {
            int res = 0;
            for (int k = i; k <= j; ++k) {
                res += nums[k];
            }
            return res;
        };
        int x0 = startPos[0], y0 = startPos[1];
        int x1 = homePos[0], y1 = homePos[1];
        int dx = x0 < x1 ? calc(rowCosts, x0 + 1, x1) : calc(rowCosts, x1, x0 - 1);
        int dy = y0 < y1 ? calc(colCosts, y0 + 1, y1) : calc(colCosts, y1, y0 - 1);
        return dx + dy;
    }
};
```

#### Go

```go
func minCost(startPos []int, homePos []int, rowCosts []int, colCosts []int) int {
	x0, y0 := startPos[0], startPos[1]
	x1, y1 := homePos[0], homePos[1]
	calc := func(nums []int, i int, j int) int {
		res := 0
		for k := i; k <= j; k++ {
			res += nums[k]
		}
		return res
	}
	dx := 0
	if x0 < x1 {
		dx = calc(rowCosts, x0+1, x1)
	} else {
		dx = calc(rowCosts, x1, x0-1)
	}
	dy := 0
	if y0 < y1 {
		dy = calc(colCosts, y0+1, y1)
	} else {
		dy = calc(colCosts, y1, y0-1)
	}
	return dx + dy
}
```

#### TypeScript

```ts
function minCost(
    startPos: number[],
    homePos: number[],
    rowCosts: number[],
    colCosts: number[],
): number {
    const calc = (nums: number[], i: number, j: number): number => {
        let res = 0;
        for (let k = i; k <= j; ++k) {
            res += nums[k];
        }
        return res;
    };

    const [x0, y0] = startPos;
    const [x1, y1] = homePos;

    const dx = x0 < x1 ? calc(rowCosts, x0 + 1, x1) : calc(rowCosts, x1, x0 - 1);

    const dy = y0 < y1 ? calc(colCosts, y0 + 1, y1) : calc(colCosts, y1, y0 - 1);

    return dx + dy;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_cost(
        start_pos: Vec<i32>,
        home_pos: Vec<i32>,
        row_costs: Vec<i32>,
        col_costs: Vec<i32>,
    ) -> i32 {
        let calc = |nums: &Vec<i32>, i: i32, j: i32| -> i32 {
            let mut res = 0;
            for k in i..=j {
                res += nums[k as usize];
            }
            res
        };

        let x0 = start_pos[0];
        let y0 = start_pos[1];
        let x1 = home_pos[0];
        let y1 = home_pos[1];

        let dx = if x0 < x1 {
            calc(&row_costs, x0 + 1, x1)
        } else {
            calc(&row_costs, x1, x0 - 1)
        };

        let dy = if y0 < y1 {
            calc(&col_costs, y0 + 1, y1)
        } else {
            calc(&col_costs, y1, y0 - 1)
        };

        dx + dy
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
