# [861. Score After Flipping Matrix](https://leetcode.com/problems/score-after-flipping-matrix)

[中文文档](/solution/0800-0899/0861.Score%20After%20Flipping%20Matrix/README.md)

## Description

<p>We have a two dimensional matrix&nbsp;<code>A</code> where each value is <code>0</code> or <code>1</code>.</p>

<p>A move consists of choosing any row or column, and toggling each value in that row or column: changing all <code>0</code>s to <code>1</code>s, and all <code>1</code>s to <code>0</code>s.</p>

<p>After making any number of moves, every row of this matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.</p>

<p>Return the highest possible&nbsp;score.</p>

<p>&nbsp;</p>

<ol>

</ol>

<div>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong><span id="example-input-1-1">[[0,0,1,1],[1,0,1,0],[1,1,0,0]]</span>

<strong>Output: </strong><span id="example-output-1">39</span>

<strong>Explanation:

</strong>Toggled to <span id="example-input-1-1">[[1,1,1,1],[1,0,0,1],[1,1,1,1]].

0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39</span></pre>

<p>&nbsp;</p>

<p><strong>Note:</strong></p>

<ol>
	<li><code>1 &lt;= A.length &lt;= 20</code></li>
	<li><code>1 &lt;= A[0].length &lt;= 20</code></li>
	<li><code>A[i][j]</code>&nbsp;is <code>0</code> or <code>1</code>.</li>
</ol>

</div>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def matrixScore(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        for i in range(m):
            if grid[i][0] == 0:
                for j in range(n):
                    grid[i][j] ^= 1

        res = 0
        for j in range(n):
            cnt = 0
            for i in range(m):
                cnt += grid[i][j]
            res += max(cnt, m - cnt) * (1 << (n - j - 1))
        return res
```

### **Java**

```java
class Solution {
    public int matrixScore(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; ++i) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < n; ++j) {
                    grid[i][j] ^= 1;
                }
            }
        }
        int res = 0;
        for (int j = 0; j < n; ++j) {
            int cnt = 0;
            for (int i = 0; i < m; ++i) {
                cnt += grid[i][j];
            }
            res += Math.max(cnt, m - cnt) * (1 << (n - j - 1));
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int matrixScore(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        for (int i = 0; i < m; ++i)
        {
            if (grid[i][0] == 0)
            {
                for (int j = 0; j < n; ++j) grid[i][j] ^= 1;
            }
        }
        int res = 0;
        for (int j = 0; j < n; ++j)
        {
            int cnt = 0;
            for (int i = 0; i < m; ++i) cnt += grid[i][j];
            res += max(cnt, m - cnt) * (1 << (n - j - 1));
        }
        return res;
    }
};
```

### **Go**

```go
func matrixScore(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	for i := 0; i < m; i++ {
		if grid[i][0] == 0 {
			for j := 0; j < n; j++ {
				grid[i][j] ^= 1
			}
		}
	}
	res := 0
	for j := 0; j < n; j++ {
		cnt := 0
		for i := 0; i < m; i++ {
			cnt += grid[i][j]
		}
		res += max(cnt, m-cnt) * (1 << (n - j - 1))
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
