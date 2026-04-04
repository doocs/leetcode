---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2087.Minimum%20Cost%20Homecoming%20of%20a%20Robot%20in%20a%20Grid/README_EN.md
rating: 1743
source: Biweekly Contest 66 Q3
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [2087. Minimum Cost Homecoming of a Robot in a Grid](https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid)

[中文文档](/solution/2000-2099/2087.Minimum%20Cost%20Homecoming%20of%20a%20Robot%20in%20a%20Grid/README.md)

## Description

<!-- description:start -->

<p>There is an <code>m x n</code> grid, where <code>(0, 0)</code> is the top-left cell and <code>(m - 1, n - 1)</code> is the bottom-right cell. You are given an integer array <code>startPos</code> where <code>startPos = [start<sub>row</sub>, start<sub>col</sub>]</code> indicates that <strong>initially</strong>, a <strong>robot</strong> is at the cell <code>(start<sub>row</sub>, start<sub>col</sub>)</code>. You are also given an integer array <code>homePos</code> where <code>homePos = [home<sub>row</sub>, home<sub>col</sub>]</code> indicates that its <strong>home</strong> is at the cell <code>(home<sub>row</sub>, home<sub>col</sub>)</code>.</p>

<p>The robot needs to go to its home. It can move one cell in four directions: <strong>left</strong>, <strong>right</strong>, <strong>up</strong>, or <strong>down</strong>, and it can not move outside the boundary. Every move incurs some cost. You are further given two <strong>0-indexed</strong> integer arrays: <code>rowCosts</code> of length <code>m</code> and <code>colCosts</code> of length <code>n</code>.</p>

<ul>
	<li>If the robot moves <strong>up</strong> or <strong>down</strong> into a cell whose <strong>row</strong> is <code>r</code>, then this move costs <code>rowCosts[r]</code>.</li>
	<li>If the robot moves <strong>left</strong> or <strong>right</strong> into a cell whose <strong>column</strong> is <code>c</code>, then this move costs <code>colCosts[c]</code>.</li>
</ul>

<p>Return <em>the <strong>minimum total cost</strong> for this robot to return home</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2087.Minimum%20Cost%20Homecoming%20of%20a%20Robot%20in%20a%20Grid/images/eg-1.png" style="width: 282px; height: 217px;" />
<pre>
<strong>Input:</strong> startPos = [1, 0], homePos = [2, 3], rowCosts = [5, 4, 3], colCosts = [8, 2, 6, 7]
<strong>Output:</strong> 18
<strong>Explanation:</strong> One optimal path is that:
Starting from (1, 0)
-&gt; It goes down to (<u><strong>2</strong></u>, 0). This move costs rowCosts[2] = 3.
-&gt; It goes right to (2, <u><strong>1</strong></u>). This move costs colCosts[1] = 2.
-&gt; It goes right to (2, <u><strong>2</strong></u>). This move costs colCosts[2] = 6.
-&gt; It goes right to (2, <u><strong>3</strong></u>). This move costs colCosts[3] = 7.
The total cost is 3 + 2 + 6 + 7 = 18</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> startPos = [0, 0], homePos = [0, 0], rowCosts = [5], colCosts = [26]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The robot is already at its home. Since no moves occur, the total cost is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

Let's assume the robot's initial position is $(x_0, y_0)$ and the home position is $(x_1, y_1)$.

If $x_0 < x_1$, the robot needs to move down, passing through rows $[x_0 + 1, x_1]$, with a total cost of $\sum_{i = x_0 + 1}^{x_1} rowCosts[i]$. If $x_0 > x_1$, the robot needs to move up, passing through rows $[x_1, x_0 - 1]$, with a total cost of $\sum_{i = x_1}^{x_0 - 1} rowCosts[i]$. If $x_0 = x_1$, the robot doesn't need to move vertically, and the total cost is $0$.

Similarly, if $y_0 < y_1$, the robot needs to move right, passing through columns $[y_0 + 1, y_1]$, with a total cost of $\sum_{j = y_0 + 1}^{y_1} colCosts[j]$. If $y_0 > y_1$, the robot needs to move left, passing through columns $[y_1, y_0 - 1]$, with a total cost of $\sum_{j = y_1}^{y_0 - 1} colCosts[j]$. If $y_0 = y_1$, the robot doesn't need to move horizontally, and the total cost is $0$.

The answer is the sum of the vertical movement cost and the horizontal movement cost.

The time complexity is $O(m + n)$, where $m$ and $n$ are the lengths of $rowCosts$ and $colCosts$, respectively. The space complexity is $O(1)$.

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
