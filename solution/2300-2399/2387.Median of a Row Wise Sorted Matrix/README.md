# [2387. 行排序矩阵的中位数](https://leetcode.cn/problems/median-of-a-row-wise-sorted-matrix)

[English Version](/solution/2300-2399/2387.Median%20of%20a%20Row%20Wise%20Sorted%20Matrix/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个包含&nbsp;<strong>奇数&nbsp;</strong>个整数的&nbsp;<code>m x n</code> 矩阵&nbsp;<code>grid</code>，其中每一行按 <strong>非递减 </strong>的顺序排序，返回矩阵的&nbsp;<strong>中位数</strong>。</p>

<p>你必须以 <code>O(m * log(n))</code> 的时间复杂度来解决这个问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> grid = [[1,1,2],[2,3,3],[1,3,4]]
<strong>输出:</strong> 2
<strong>解释:</strong> 矩阵的元素按顺序排列为 1,1,1,2,<u>2</u>,3,3,3,4。中位数是 2。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> grid = [[1,1,3,3,4]]
<strong>输出:</strong> 3
<strong>解释:</strong> 矩阵的元素按顺序排列为 1,1,<u>3</u>,3,4。中位数是 3。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>m</code> 和&nbsp;<code>n</code>&nbsp;都是奇数。</li>
	<li><code>1 &lt;= grid[i][j] &lt;= 10<sup>6</sup></code></li>
	<li><code>grid[i]</code> 按非递减顺序排序</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：两次二分查找**

中位数实际上是排序后第 $target = \left \lceil \frac{m\times n}{2} \right \rceil$ 个数。

我们二分枚举矩阵的元素 $x$，统计网格中大于该元素的个数 $cnt$，如果 $cnt \ge target$，说明中位数在 $x$ 的左侧（包含 $x$），否则在右侧。

时间复杂度 $O(m\times \log n \times log M)$，空间复杂度 $O(1)$。其中 $m$ 和 $n$ 分别为网格的行数和列数；而 $M$ 为网格中的最大元素。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def matrixMedian(self, grid: List[List[int]]) -> int:
        def count(x):
            return sum(bisect_right(row, x) for row in grid)

        m, n = len(grid), len(grid[0])
        target = (m * n + 1) >> 1
        return bisect_left(range(10**6 + 1), target, key=count)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][] grid;

    public int matrixMedian(int[][] grid) {
        this.grid = grid;
        int m = grid.length, n = grid[0].length;
        int target = (m * n + 1) >> 1;
        int left = 0, right = 1000010;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (count(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int count(int x) {
        int cnt = 0;
        for (var row : grid) {
            int left = 0, right = row.length;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (row[mid] > x) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            cnt += left;
        }
        return cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int matrixMedian(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        int left = 0, right = 1e6 + 1;
        int target = (m * n + 1) >> 1;
        auto count = [&](int x) {
            int cnt = 0;
            for (auto& row : grid) {
                cnt += (upper_bound(row.begin(), row.end(), x) - row.begin());
            }
            return cnt;
        };
        while (left < right) {
            int mid = (left + right) >> 1;
            if (count(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func matrixMedian(grid [][]int) int {
	m, n := len(grid), len(grid[0])

	count := func(x int) int {
		cnt := 0
		for _, row := range grid {
			left, right := 0, n
			for left < right {
				mid := (left + right) >> 1
				if row[mid] > x {
					right = mid
				} else {
					left = mid + 1
				}
			}
			cnt += left
		}
		return cnt
	}
	left, right := 0, 1000010
	target := (m*n + 1) >> 1
	for left < right {
		mid := (left + right) >> 1
		if count(mid) >= target {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
