---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3417.Zigzag%20Grid%20Traversal%20With%20Skip/README_EN.md
rating: 1290
source: Weekly Contest 432 Q1
tags:
    - Array
    - Matrix
    - Simulation
---

<!-- problem:start -->

# [3417. Zigzag Grid Traversal With Skip](https://leetcode.com/problems/zigzag-grid-traversal-with-skip)

[中文文档](/solution/3400-3499/3417.Zigzag%20Grid%20Traversal%20With%20Skip/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> 2D array <code>grid</code> of <strong>positive</strong> integers.</p>

<p>Your task is to traverse <code>grid</code> in a <strong>zigzag</strong> pattern while skipping every <strong>alternate</strong> cell.</p>

<p>Zigzag pattern traversal is defined as following the below actions:</p>

<ul>
	<li>Start at the top-left cell <code>(0, 0)</code>.</li>
	<li>Move <em>right</em> within a row until the end of the row is reached.</li>
	<li>Drop down to the next row, then traverse <em>left</em> until the beginning of the row is reached.</li>
	<li>Continue <strong>alternating</strong> between right and left traversal until every row has been traversed.</li>
</ul>

<p><strong>Note </strong>that you <strong>must skip</strong> every <em>alternate</em> cell during the traversal.</p>

<p>Return an array of integers <code>result</code> containing, <strong>in order</strong>, the value of the cells visited during the zigzag traversal with skips.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2],[3,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,4]</span></p>

<p><strong>Explanation:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3417.Zigzag%20Grid%20Traversal%20With%20Skip/images/4012_example0.png" style="width: 200px; height: 200px;" /></strong></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[2,1],[2,1],[2,1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[2,1,2]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3417.Zigzag%20Grid%20Traversal%20With%20Skip/images/4012_example1.png" style="width: 200px; height: 240px;" /></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,2,3],[4,5,6],[7,8,9]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,3,5,7,9]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3417.Zigzag%20Grid%20Traversal%20With%20Skip/images/4012_example2.png" style="width: 260px; height: 250px;" /></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n == grid.length &lt;= 50</code></li>
	<li><code>2 &lt;= m == grid[i].length &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 2500</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We traverse each row. If the current row index is odd, we reverse the elements of that row. Then, we traverse the elements of the row and add them to the answer array according to the rules specified in the problem.

The time complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns of the 2D array $\textit{grid}$, respectively. Ignoring the space consumption of the answer array, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def zigzagTraversal(self, grid: List[List[int]]) -> List[int]:
        ok = True
        ans = []
        for i, row in enumerate(grid):
            if i % 2:
                row.reverse()
            for x in row:
                if ok:
                    ans.append(x)
                ok = not ok
        return ans
```

#### Java

```java
class Solution {
    public List<Integer> zigzagTraversal(int[][] grid) {
        boolean ok = true;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < grid.length; ++i) {
            if (i % 2 == 1) {
                reverse(grid[i]);
            }
            for (int x : grid[i]) {
                if (ok) {
                    ans.add(x);
                }
                ok = !ok;
            }
        }
        return ans;
    }

    private void reverse(int[] nums) {
        for (int i = 0, j = nums.length - 1; i < j; ++i, --j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> zigzagTraversal(vector<vector<int>>& grid) {
        vector<int> ans;
        bool ok = true;
        for (int i = 0; i < grid.size(); ++i) {
            if (i % 2 != 0) {
                ranges::reverse(grid[i]);
            }
            for (int x : grid[i]) {
                if (ok) {
                    ans.push_back(x);
                }
                ok = !ok;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func zigzagTraversal(grid [][]int) (ans []int) {
	ok := true
	for i, row := range grid {
		if i%2 != 0 {
			slices.Reverse(row)
		}
		for _, x := range row {
			if ok {
				ans = append(ans, x)
			}
			ok = !ok
		}
	}
	return
}
```

#### TypeScript

```ts
function zigzagTraversal(grid: number[][]): number[] {
    const ans: number[] = [];
    let ok: boolean = true;
    for (let i = 0; i < grid.length; ++i) {
        if (i % 2) {
            grid[i].reverse();
        }
        for (const x of grid[i]) {
            if (ok) {
                ans.push(x);
            }
            ok = !ok;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
