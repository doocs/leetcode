---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1351.Count%20Negative%20Numbers%20in%20a%20Sorted%20Matrix/README.md
rating: 1139
source: 第 176 场周赛 Q1
tags:
    - 数组
    - 二分查找
    - 矩阵
---

<!-- problem:start -->

# [1351. 统计有序矩阵中的负数](https://leetcode.cn/problems/count-negative-numbers-in-a-sorted-matrix)

[English Version](/solution/1300-1399/1351.Count%20Negative%20Numbers%20in%20a%20Sorted%20Matrix/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<code>m&nbsp;* n</code>&nbsp;的矩阵&nbsp;<code>grid</code>，矩阵中的元素无论是按行还是按列，都以非严格递减顺序排列。&nbsp;请你统计并返回&nbsp;<code>grid</code>&nbsp;中 <strong>负数</strong> 的数目。</p>

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：从左下角开始遍历

由于矩阵是按行和按列非严格递减排序的，我们可以从矩阵的左下角开始遍历，记当前位置为 $(i, j)$。

如果当前位置的元素大于等于 $0$，说明该行的前面所有元素也都大于等于 $0$，因此我们将列索引 $j$ 向右移动一位，即 $j = j + 1$。

如果当前位置的元素小于 $0$，说明该列的当前元素以及其右侧的所有元素都是负数，因此我们可以将负数的数量增加 $n - j$（其中 $n$ 是矩阵的列数），然后将行索引 $i$ 向上移动一位，即 $i = i - 1$。

我们重复上述过程，直到行索引 $i$ 小于 $0$ 或列索引 $j$ 大于等于 $n$。最终，负数的数量即为答案。

时间复杂度 $O(m + n)$，其中 $m$ 和 $n$ 分别是矩阵的行数和列数。空间复杂度 $O(1)$。

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
