---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2500.Delete%20Greatest%20Value%20in%20Each%20Row/README_EN.md
rating: 1309
source: Weekly Contest 323 Q1
tags:
    - Array
    - Matrix
    - Sorting
    - Simulation
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2500. Delete Greatest Value in Each Row](https://leetcode.com/problems/delete-greatest-value-in-each-row)

[中文文档](/solution/2500-2599/2500.Delete%20Greatest%20Value%20in%20Each%20Row/README.md)

## Description

<!-- description:start -->

<p>You are given an <code>m x n</code> matrix <code>grid</code> consisting of positive integers.</p>

<p>Perform the following operation until <code>grid</code> becomes empty:</p>

<ul>
	<li>Delete the element with the greatest value from each row. If multiple such elements exist, delete any of them.</li>
	<li>Add the maximum of deleted elements to the answer.</li>
</ul>

<p><strong>Note</strong> that the number of columns decreases by one after each operation.</p>

<p>Return <em>the answer after performing the operations described above</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2500.Delete%20Greatest%20Value%20in%20Each%20Row/images/q1ex1.jpg" style="width: 600px; height: 135px;" />
<pre>
<strong>Input:</strong> grid = [[1,2,4],[3,3,1]]
<strong>Output:</strong> 8
<strong>Explanation:</strong> The diagram above shows the removed values in each step.
- In the first operation, we remove 4 from the first row and 3 from the second row (notice that, there are two cells with value 3 and we can remove any of them). We add 4 to the answer.
- In the second operation, we remove 2 from the first row and 3 from the second row. We add 3 to the answer.
- In the third operation, we remove 1 from the first row and 1 from the second row. We add 1 to the answer.
The final answer = 4 + 3 + 1 = 8.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2500.Delete%20Greatest%20Value%20in%20Each%20Row/images/q1ex2.jpg" style="width: 83px; height: 83px;" />
<pre>
<strong>Input:</strong> grid = [[10]]
<strong>Output:</strong> 10
<strong>Explanation:</strong> The diagram above shows the removed values in each step.
- In the first operation, we remove 10 from the first row. We add 10 to the answer.
The final answer = 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code>1 &lt;= grid[i][j] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting

Since each operation involves removing the maximum value from each row and then adding the maximum value to the answer, we can first sort each row.

Next, we traverse each column, take the maximum value from each column, and add it to the answer.

The time complexity is $O(m \times n \times \log n)$, and the space complexity is $O(\log n)$. Here, $m$ and $n$ are the number of rows and columns of the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def deleteGreatestValue(self, grid: List[List[int]]) -> int:
        for row in grid:
            row.sort()
        return sum(max(col) for col in zip(*grid))
```

#### Java

```java
class Solution {
    public int deleteGreatestValue(int[][] grid) {
        for (var row : grid) {
            Arrays.sort(row);
        }
        int ans = 0;
        for (int j = 0; j < grid[0].length; ++j) {
            int t = 0;
            for (int i = 0; i < grid.length; ++i) {
                t = Math.max(t, grid[i][j]);
            }
            ans += t;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int deleteGreatestValue(vector<vector<int>>& grid) {
        for (auto& row : grid) {
            ranges::sort(row);
        }
        int ans = 0;
        for (int j = 0; j < grid[0].size(); ++j) {
            int t = 0;
            for (int i = 0; i < grid.size(); ++i) {
                t = max(t, grid[i][j]);
            }
            ans += t;
        }
        return ans;
    }
};
```

#### Go

```go
func deleteGreatestValue(grid [][]int) (ans int) {
	for _, row := range grid {
		sort.Ints(row)
	}
	for j := range grid[0] {
		t := 0
		for i := range grid {
			if t < grid[i][j] {
				t = grid[i][j]
			}
		}
		ans += t
	}
	return
}
```

#### TypeScript

```ts
function deleteGreatestValue(grid: number[][]): number {
    for (const row of grid) {
        row.sort((a, b) => a - b);
    }

    let ans = 0;
    for (let j = 0; j < grid[0].length; ++j) {
        let t = 0;
        for (let i = 0; i < grid.length; ++i) {
            t = Math.max(t, grid[i][j]);
        }
        ans += t;
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn delete_greatest_value(grid: Vec<Vec<i32>>) -> i32 {
        let mut grid = grid;
        for i in 0..grid.len() {
            grid[i].sort();
        }

        let mut ans = 0;
        for j in 0..grid[0].len() {
            let mut mx = 0;

            for i in 0..grid.len() {
                if grid[i][j] > mx {
                    mx = grid[i][j];
                }
            }

            ans += mx;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
