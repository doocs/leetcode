# [1351. 统计有序矩阵中的负数](https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix)

[English Version](/solution/1300-1399/1351.Count%20Negative%20Numbers%20in%20a%20Sorted%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个&nbsp;<code>m&nbsp;* n</code>&nbsp;的矩阵&nbsp;<code>grid</code>，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。&nbsp;请你统计并返回&nbsp;<code>grid</code>&nbsp;中 <strong>负数</strong> 的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
<strong>输出：</strong>8
<strong>解释：</strong>矩阵中共有 8 个负数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [[3,2],[1,0]]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 100</code></li>
	<li><code>-100 &lt;= grid[i][j] &lt;= 100</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(n + m)</code> 的解决方案吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：从左下角或右上角开始遍历**

根据**其行列都以非递增顺序排列**的特点，可以从**左下角**开始**往右上方向遍历**。

当遇到负数时，说明这一行从当前位置开始往右的所有元素均为负数，我们将答案加上这一行剩余的元素个数，即 $n - j$，并且向上移动一行，即 $i \leftarrow i - 1$。否则，向右移动一列，即 $j \leftarrow j + 1$。

遍历结束，返回答案。

时间复杂度 $O(m + n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

**方法二：二分查找**

遍历每一行，二分查找每一行第一个小于 $0$ 的位置，从该位置开始往右的所有元素均为负数，累加负数个数到答案中。

遍历结束，返回答案。

时间复杂度 $O(m \times \log n)$。其中 $m$ 和 $n$ 分别为矩阵的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
