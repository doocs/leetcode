---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1351.Count%20Negative%20Numbers%20in%20a%20Sorted%20Matrix/README_EN.md
rating: 1139
source: Weekly Contest 176 Q1
tags:
    - Array
    - Binary Search
    - Matrix
---

<!-- problem:start -->

# [1351. Count Negative Numbers in a Sorted Matrix](https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix)

[中文文档](/solution/1300-1399/1351.Count%20Negative%20Numbers%20in%20a%20Sorted%20Matrix/README.md)

## Description

<!-- description:start -->

<p>Given a <code>m x n</code> matrix <code>grid</code> which is sorted in non-increasing order both row-wise and column-wise, return <em>the number of <strong>negative</strong> numbers in</em> <code>grid</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
<strong>Output:</strong> 8
<strong>Explanation:</strong> There are 8 negatives number in the matrix.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[3,2],[1,0]]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>-100 &lt;= grid[i][j] &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow up:</strong> Could you find an <code>O(n + m)</code> solution?

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traverse from the Bottom-Left Corner

Since the matrix is sorted in non-strictly decreasing order both row-wise and column-wise, we can start traversing from the bottom-left corner of the matrix. Let the current position be $(i, j)$.

If the element at the current position is greater than or equal to $0$, it means all preceding elements in that row are also greater than or equal to $0$. Therefore, we move the column index $j$ one position to the right, i.e., $j = j + 1$.

If the element at the current position is less than $0$, it means the current element and all elements to its right in that row are negative. Therefore, we can add $n - j$ to the count of negative numbers (where $n$ is the number of columns in the matrix), and then move the row index $i$ one position upward, i.e., $i = i - 1$.

We repeat the above process until the row index $i$ is less than $0$ or the column index $j$ is greater than or equal to $n$. Finally, the count of negative numbers is the answer.

The time complexity is $O(m + n)$, where $m$ and $n$ are the number of rows and columns of the matrix, respectively. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countNegatives(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        i, j = m - 1, 0
        ans = 0
        while i >= 0 and j < n:
            if grid[i][j] >= 0:
                j += 1
            else:
                ans += n - j
                i -= 1
        return ans
```

#### Java

```java
class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int i = m - 1;
        int j = 0;
        int ans = 0;
        while (i >= 0 && j < n) {
            if (grid[i][j] >= 0) {
                j++;
            } else {
                ans += n - j;
                i--;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countNegatives(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        int i = m - 1;
        int j = 0;
        int ans = 0;
        while (i >= 0 && j < n) {
            if (grid[i][j] >= 0) {
                j++;
            } else {
                ans += n - j;
                i--;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countNegatives(grid [][]int) (ans int) {
	m := len(grid)
	n := len(grid[0])
	i := m - 1
	j := 0
	for i >= 0 && j < n {
		if grid[i][j] >= 0 {
			j++
		} else {
			ans += n - j
			i--
		}
	}
	return
}
```

#### TypeScript

```ts
function countNegatives(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    let i = m - 1;
    let j = 0;
    let ans = 0;
    while (i >= 0 && j < n) {
        if (grid[i][j] >= 0) {
            j++;
        } else {
            ans += n - j;
            i--;
        }
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_negatives(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut i: i32 = m as i32 - 1;
        let mut j: usize = 0;
        let mut ans: i32 = 0;
        while i >= 0 && j < n {
            if grid[i as usize][j] >= 0 {
                j += 1;
            } else {
                ans += (n - j) as i32;
                i -= 1;
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[][]} grid
 * @return {number}
 */
var countNegatives = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    let i = m - 1;
    let j = 0;
    let ans = 0;
    while (i >= 0 && j < n) {
        if (grid[i][j] >= 0) {
            j++;
        } else {
            ans += n - j;
            i--;
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
