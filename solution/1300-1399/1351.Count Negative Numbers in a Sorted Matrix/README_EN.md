# [1351. Count Negative Numbers in a Sorted Matrix](https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix)

[中文文档](/solution/1300-1399/1351.Count%20Negative%20Numbers%20in%20a%20Sorted%20Matrix/README.md)

## Description

<p>Given a <code>m x n</code> matrix <code>grid</code> which is sorted in non-increasing order both row-wise and column-wise, return <em>the number of <strong>negative</strong> numbers in</em> <code>grid</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
<strong>Output:</strong> 8
<strong>Explanation:</strong> There are 8 negatives number in the matrix.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[3,2],[1,0]]
<strong>Output:</strong> 0
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,-1],[-1,-1]]
<strong>Output:</strong> 3
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> grid = [[-1]]
<strong>Output:</strong> 1
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
    let m = grid.length, n = grid[0].length;
    let i = 0, j = n - 1;
    let ans = 0;
    while (i < m && j > -1) {
        let cur = grid[i][j];
        if (cur < 0) {
            j--;
            ans += (m - i);
        } else {
            i++;
        }
    }
    return ans;
};
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
