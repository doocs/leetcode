# [1260. Shift 2D Grid](https://leetcode.com/problems/shift-2d-grid)

[中文文档](/solution/1200-1299/1260.Shift%202D%20Grid/README.md)

## Description

<p>Given a 2D <code>grid</code> of size <code>m x n</code>&nbsp;and an integer <code>k</code>. You need to shift the <code>grid</code>&nbsp;<code>k</code> times.</p>

<p>In one shift operation:</p>

<ul>
	<li>Element at <code>grid[i][j]</code> moves to <code>grid[i][j + 1]</code>.</li>
	<li>Element at <code>grid[i][n - 1]</code> moves to <code>grid[i + 1][0]</code>.</li>
	<li>Element at <code>grid[m&nbsp;- 1][n - 1]</code> moves to <code>grid[0][0]</code>.</li>
</ul>

<p>Return the <em>2D grid</em> after applying shift operation <code>k</code> times.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1260.Shift%202D%20Grid/images/e1.png" style="width: 400px; height: 178px;" />
<pre>
<strong>Input:</strong> <code>grid</code> = [[1,2,3],[4,5,6],[7,8,9]], k = 1
<strong>Output:</strong> [[9,1,2],[3,4,5],[6,7,8]]
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1260.Shift%202D%20Grid/images/e2.png" style="width: 400px; height: 166px;" />
<pre>
<strong>Input:</strong> <code>grid</code> = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
<strong>Output:</strong> [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> <code>grid</code> = [[1,2,3],[4,5,6],[7,8,9]], k = 9
<strong>Output:</strong> [[1,2,3],[4,5,6],[7,8,9]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m ==&nbsp;grid.length</code></li>
	<li><code>n ==&nbsp;grid[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 50</code></li>
	<li><code>1 &lt;= n &lt;= 50</code></li>
	<li><code>-1000 &lt;= grid[i][j] &lt;= 1000</code></li>
	<li><code>0 &lt;= k &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shiftGrid(self, grid: List[List[int]], k: int) -> List[List[int]]:
        m, n = len(grid), len(grid[0])
        k %= m * n
        t = [grid[i][j] for i in range(m) for j in range(n)]
        t = t[-k:] + t[:-k]
        for i in range(m):
            for j in range(n):
                grid[i][j] = t[i * n + j]
        return grid
```

### **Java**

```java
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        k %= (m * n);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            List<Integer> t = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                t.add(0);
            }
            ans.add(t);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = (i * n + j + k) % (m * n);
                ans.get(t / n).set(t % n, grid[i][j]);
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function shiftGrid(grid: number[][], k: number): number[][] {
    const m = grid.length,
        n = grid[0].length;
    const size = m * n;
    k = k % size;
    if (k == 0 || size <= 1) return grid;
    let arr = grid.flat();
    if (size <= 1) return grid;
    let ans = Array.from({ length: m }, v => new Array(n));
    for (let i = 0, j = size - k; i < size; i++) {
        ans[Math.floor(i / n)][i % n] = arr[j];
        j = j == size - 1 ? 0 : j + 1;
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> shiftGrid(vector<vector<int>>& grid, int k) {
        int m = grid.size(), n = grid[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int t = (i * n + j + k) % (m * n);
                ans[t / n][t % n] = grid[i][j];
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func shiftGrid(grid [][]int, k int) [][]int {
	m, n := len(grid), len(grid[0])
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			t := (i*n + j + k) % (m * n)
			ans[t/n][t%n] = grid[i][j]
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
