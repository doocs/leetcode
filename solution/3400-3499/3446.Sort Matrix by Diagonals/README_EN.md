---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3446.Sort%20Matrix%20by%20Diagonals/README_EN.md
---

<!-- problem:start -->

# [3446. Sort Matrix by Diagonals](https://leetcode.com/problems/sort-matrix-by-diagonals)

[中文文档](/solution/3400-3499/3446.Sort%20Matrix%20by%20Diagonals/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>n x n</code> square matrix of integers <code>grid</code>. Return the matrix such that:</p>

<ul>
	<li>The diagonals in the <strong>bottom-left triangle</strong> (including the middle diagonal) are sorted in <strong>non-increasing order</strong>.</li>
	<li>The diagonals in the <strong>top-right triangle</strong> are sorted in <strong>non-decreasing order</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,7,3],[9,8,2],[4,5,6]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[8,2,3],[9,6,7],[4,5,1]]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3446.Sort%20Matrix%20by%20Diagonals/images/4052example1drawio.png" style="width: 461px; height: 181px;" /></p>

<p>The diagonals with a black arrow (bottom-left triangle) should be sorted in non-increasing order:</p>

<ul>
	<li><code>[1, 8, 6]</code> becomes <code>[8, 6, 1]</code>.</li>
	<li><code>[9, 5]</code> and <code>[4]</code> remain unchanged.</li>
</ul>

<p>The diagonals with a blue arrow (top-right triangle) should be sorted in non-decreasing order:</p>

<ul>
	<li><code>[7, 2]</code> becomes <code>[2, 7]</code>.</li>
	<li><code>[3]</code> remains unchanged.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[0,1],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[2,1],[1,0]]</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/3400-3499/3446.Sort%20Matrix%20by%20Diagonals/images/4052example2adrawio.png" style="width: 383px; height: 141px;" /></p>

<p>The diagonals with a black arrow must be non-increasing, so <code>[0, 2]</code> is changed to <code>[2, 0]</code>. The other diagonals are already in the correct order.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[1]]</span></p>

<p><strong>Explanation:</strong></p>

<p>Diagonals with exactly one element are already in order, so no changes are needed.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>grid.length == grid[i].length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10</code></li>
	<li><code>-10<sup>5</sup> &lt;= grid[i][j] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation + Sorting

We can simulate the diagonal sorting process as described in the problem.

First, we sort the diagonals of the lower-left triangle, including the main diagonal, in non-increasing order. Then, we sort the diagonals of the upper-right triangle in non-decreasing order. Finally, we return the sorted matrix.

The time complexity is $O(n^2 \log n)$, and the space complexity is $O(n)$. Here, $n$ is the size of the matrix.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def sortMatrix(self, grid: List[List[int]]) -> List[List[int]]:
        n = len(grid)
        for k in range(n - 2, -1, -1):
            i, j = k, 0
            t = []
            while i < n and j < n:
                t.append(grid[i][j])
                i += 1
                j += 1
            t.sort()
            i, j = k, 0
            while i < n and j < n:
                grid[i][j] = t.pop()
                i += 1
                j += 1
        for k in range(n - 2, 0, -1):
            i, j = k, n - 1
            t = []
            while i >= 0 and j >= 0:
                t.append(grid[i][j])
                i -= 1
                j -= 1
            t.sort()
            i, j = k, n - 1
            while i >= 0 and j >= 0:
                grid[i][j] = t.pop()
                i -= 1
                j -= 1
        return grid
```

#### Java

```java
class Solution {
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        for (int k = n - 2; k >= 0; --k) {
            int i = k, j = 0;
            List<Integer> t = new ArrayList<>();
            while (i < n && j < n) {
                t.add(grid[i++][j++]);
            }
            Collections.sort(t);
            for (int x : t) {
                grid[--i][--j] = x;
            }
        }
        for (int k = n - 2; k > 0; --k) {
            int i = k, j = n - 1;
            List<Integer> t = new ArrayList<>();
            while (i >= 0 && j >= 0) {
                t.add(grid[i--][j--]);
            }
            Collections.sort(t);
            for (int x : t) {
                grid[++i][++j] = x;
            }
        }
        return grid;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<int>> sortMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        for (int k = n - 2; k >= 0; --k) {
            int i = k, j = 0;
            vector<int> t;
            while (i < n && j < n) {
                t.push_back(grid[i++][j++]);
            }
            ranges::sort(t);
            for (int x : t) {
                grid[--i][--j] = x;
            }
        }
        for (int k = n - 2; k > 0; --k) {
            int i = k, j = n - 1;
            vector<int> t;
            while (i >= 0 && j >= 0) {
                t.push_back(grid[i--][j--]);
            }
            ranges::sort(t);
            for (int x : t) {
                grid[++i][++j] = x;
            }
        }
        return grid;
    }
};
```

#### Go

```go
func sortMatrix(grid [][]int) [][]int {
	n := len(grid)
	for k := n - 2; k >= 0; k-- {
		i, j := k, 0
		t := []int{}
		for ; i < n && j < n; i, j = i+1, j+1 {
			t = append(t, grid[i][j])
		}
		sort.Ints(t)
		for _, x := range t {
			i, j = i-1, j-1
			grid[i][j] = x
		}
	}
	for k := n - 2; k > 0; k-- {
		i, j := k, n-1
		t := []int{}
		for ; i >= 0 && j >= 0; i, j = i-1, j-1 {
			t = append(t, grid[i][j])
		}
		sort.Ints(t)
		for _, x := range t {
			i, j = i+1, j+1
			grid[i][j] = x
		}
	}
	return grid
}
```

#### TypeScript

```ts
function sortMatrix(grid: number[][]): number[][] {
    const n = grid.length;
    for (let k = n - 2; k >= 0; --k) {
        let [i, j] = [k, 0];
        const t: number[] = [];
        while (i < n && j < n) {
            t.push(grid[i++][j++]);
        }
        t.sort((a, b) => a - b);
        for (const x of t) {
            grid[--i][--j] = x;
        }
    }
    for (let k = n - 2; k > 0; --k) {
        let [i, j] = [k, n - 1];
        const t: number[] = [];
        while (i >= 0 && j >= 0) {
            t.push(grid[i--][j--]);
        }
        t.sort((a, b) => a - b);
        for (const x of t) {
            grid[++i][++j] = x;
        }
    }
    return grid;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
