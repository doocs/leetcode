# [1351. 统计有序矩阵中的负数](https://leetcode-cn.com/problems/count-negative-numbers-in-a-sorted-matrix)

[English Version](/solution/1300-1399/1351.Count%20Negative%20Numbers%20in%20a%20Sorted%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <code>m * n</code> 的矩阵 <code>grid</code>，矩阵中的元素无论是按行还是按列，都以非递增顺序排列。 </p>

<p>请你统计并返回 <code>grid</code> 中 <strong>负数</strong> 的数目。</p>

<p> </p>

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

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>grid = [[1,-1],[-1,-1]]
<strong>输出：</strong>3
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>grid = [[-1]]
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 <= m, n <= 100</code></li>
	<li><code>-100 <= grid[i][j] <= 100</code></li>
</ul>

<p> </p>

<p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(n + m)</code> 的解决方案吗？</p>

<p> </p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

从右上角开始遍历。当遇到负数时，说明这一列从当前行往下的所有数（共 `m - i` 个数）均为负数，`cnt += (m - i)`，然后 j 往左移动一个位置。否则 i 往下移动一个位置。

最后返回 cnt 值即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countNegatives(self, grid: List[List[int]]) -> int:
        m, n, cnt = len(grid), len(grid[0]), 0
        i, j = 0, n - 1
        while i < m and j >= 0:
            if grid[i][j] < 0:
                cnt += (m - i)
                j -= 1
            else:
                i += 1
        return cnt
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int cnt = 0;
        for (int i = 0, j = n - 1; j >= 0 && i < m;) {
            if (grid[i][j] < 0) {
                cnt += (m - i);
                --j;
            } else {
                ++i;
            }
        }
        return cnt;
    }
}
```

### **TypeScript**

```ts
function countNegatives(grid: number[][]): number {
    let m = grid.length,
        n = grid[0].length;
    let i = 0,
        j = n - 1;
    let ans = 0;
    while (i < m && j > -1) {
        let cur = grid[i][j];
        if (cur < 0) {
            j--;
            ans += m - i;
        } else {
            i++;
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int countNegatives(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int i = 0, j = n - 1, cnt = 0;
        while (i < m && j >= 0) {
            if (grid[i][j] < 0) {
                cnt += (m - i);
                --j;
            } else {
                ++i;
            }
        }
        return cnt;
    }
};
```

### **Go**

```go
func countNegatives(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	i, j, cnt := 0, n-1, 0
	for i < m && j >= 0 {
		if grid[i][j] < 0 {
			cnt += (m - i)
			j--
		} else {
			i++
		}
	}
	return cnt
}
```

### **...**

```

```

<!-- tabs:end -->
