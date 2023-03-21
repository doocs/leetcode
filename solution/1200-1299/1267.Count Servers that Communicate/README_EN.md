# [1267. Count Servers that Communicate](https://leetcode.com/problems/count-servers-that-communicate)

[中文文档](/solution/1200-1299/1267.Count%20Servers%20that%20Communicate/README.md)

## Description

<p>You are given a map of a server center, represented as a <code>m * n</code> integer matrix&nbsp;<code>grid</code>, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.<br />
<br />
Return the number of servers&nbsp;that communicate with any other server.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1267.Count%20Servers%20that%20Communicate/images/untitled-diagram-6.jpg" style="width: 202px; height: 203px;" /></p>

<pre>
<strong>Input:</strong> grid = [[1,0],[0,1]]
<strong>Output:</strong> 0
<b>Explanation:</b>&nbsp;No servers can communicate with others.</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1267.Count%20Servers%20that%20Communicate/images/untitled-diagram-4.jpg" style="width: 203px; height: 203px;" /></strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0],[1,1]]
<strong>Output:</strong> 3
<b>Explanation:</b>&nbsp;All three servers can communicate with at least one other server.
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1267.Count%20Servers%20that%20Communicate/images/untitled-diagram-1-3.jpg" style="width: 443px; height: 443px;" /></p>

<pre>
<strong>Input:</strong> grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
<strong>Output:</strong> 4
<b>Explanation:</b>&nbsp;The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can&#39;t communicate with any other server.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 250</code></li>
	<li><code>1 &lt;= n &lt;= 250</code></li>
	<li><code>grid[i][j] == 0 or 1</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countServers(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        row = [0] * m
        col = [0] * n
        for i in range(m):
            for j in range(n):
                if grid[i][j]:
                    row[i] += 1
                    col[j] += 1
        return sum(
            grid[i][j] and (row[i] > 1 or col[j] > 1)
            for i in range(m)
            for j in range(n)
        )
```

### **Java**

```java
class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && (row[i] > 1 || col[j] > 1)) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countServers(vector<vector<int>>& grid) {
        int m = grid.size(), n = grid[0].size();
        vector<int> row(m), col(n);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    ++row[i];
                    ++col[j];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans += grid[i][j] && (row[i] > 1 || col[j] > 1);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func countServers(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	row, col := make([]int, m), make([]int, n)
	for i := range grid {
		for j, x := range grid[i] {
			if x == 1 {
				row[i]++
				col[j]++
			}
		}
	}
	for i := range grid {
		for j, x := range grid[i] {
			if x == 1 && (row[i] > 1 || col[j] > 1) {
				ans++
			}
		}
	}
	return
}
```

### **TypeScript**

```ts
function countServers(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const row = new Array(m).fill(0);
    const col = new Array(n).fill(0);
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 1) {
                row[i]++;
                col[j]++;
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 1 && (row[i] > 1 || col[j] > 1)) {
                ans++;
            }
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
