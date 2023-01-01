# [1351. Count Negative Numbers in a Sorted Matrix](https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix)

[中文文档](/solution/1300-1399/1351.Count%20Negative%20Numbers%20in%20a%20Sorted%20Matrix/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countNegatives(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        i, j = m - 1, 0
        ans = 0
        while i >= 0 and j < n:
            if grid[i][j] < 0:
                ans += n - j
                i -= 1
            else:
                j += 1
        return ans
```

```python
class Solution:
    def countNegatives(self, grid: List[List[int]]) -> int:
        return sum(bisect_left(row[::-1], 0) for row in grid)
```

### **Java**

```java
class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int i = m - 1, j = 0; i >= 0 && j < n;) {
            if (grid[i][j] < 0) {
                ans += n - j;
                --i;
            } else {
                ++j;
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int countNegatives(int[][] grid) {
        int ans = 0;
        int n = grid[0].length;
        for (int[] row : grid) {
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (row[mid] < 0) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            ans += n - left;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countNegatives(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int ans = 0;
        for (int i = m - 1, j = 0; i >= 0 && j < n;) {
            if (grid[i][j] < 0) {
                ans += n - j;
                --i;
            } else
                ++j;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int countNegatives(vector<vector<int>>& grid) {
        int ans = 0;
        for (auto& row : grid) {
            ans += lower_bound(row.rbegin(), row.rend(), 0) - row.rbegin();
        }
        return ans;
    }
};
```

### **Go**

```go
func countNegatives(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	ans := 0
	for i, j := m-1, 0; i >= 0 && j < n; {
		if grid[i][j] < 0 {
			ans += n - j
			i--
		} else {
			j++
		}
	}
	return ans
}
```

```go
func countNegatives(grid [][]int) int {
	ans, n := 0, len(grid[0])
	for _, row := range grid {
		left, right := 0, n
		for left < right {
			mid := (left + right) >> 1
			if row[mid] < 0 {
				right = mid
			} else {
				left = mid + 1
			}
		}
		ans += n - left
	}
	return ans
}
```

### **TypeScript**

```ts
function countNegatives(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let ans = 0;
    for (let i = m - 1, j = 0; i >= 0 && j < n; ) {
        if (grid[i][j] < 0) {
            ans += n - j;
            --i;
        } else {
            ++j;
        }
    }
    return ans;
}
```

```ts
function countNegatives(grid: number[][]): number {
    const n = grid[0].length;
    let ans = 0;
    for (let row of grid) {
        let left = 0,
            right = n;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (row[mid] < 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        ans += n - left;
    }
    return ans;
}
```

### **JavaScript**

```js
/**
 * @param {number[][]} grid
 * @return {number}
 */
var countNegatives = function (grid) {
    const m = grid.length,
        n = grid[0].length;
    let ans = 0;
    for (let i = m - 1, j = 0; i >= 0 && j < n; ) {
        if (grid[i][j] < 0) {
            ans += n - j;
            --i;
        } else {
            ++j;
        }
    }
    return ans;
};
```

```js
/**
 * @param {number[][]} grid
 * @return {number}
 */
var countNegatives = function (grid) {
    const n = grid[0].length;
    let ans = 0;
    for (let row of grid) {
        let left = 0,
            right = n;
        while (left < right) {
            const mid = (left + right) >> 1;
            if (row[mid] < 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        ans += n - left;
    }
    return ans;
};
```

### **Rust**

```rust
impl Solution {
    pub fn count_negatives(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid[0].len();
        grid.into_iter()
            .map(|nums| {
                let mut left = 0;
                let mut right = n;
                while left < right {
                    let mid = left + (right - left) / 2;
                    if nums[mid] >= 0 {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                (n - left) as i32
            })
            .sum()
    }
}
```

```rust
impl Solution {
    pub fn count_negatives(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut i = m;
        let mut j = 0;
        let mut res = 0;
        while i > 0 && j < n {
            if grid[i - 1][j] >= 0 {
                j += 1;
            } else {
                res += n - j;
                i -= 1;
            }
        }
        res as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
