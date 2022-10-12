# [305. Number of Islands II](https://leetcode.com/problems/number-of-islands-ii)

[中文文档](/solution/0300-0399/0305.Number%20of%20Islands%20II/README.md)

## Description

<p>You are given an empty 2D binary grid <code>grid</code> of size <code>m x n</code>. The grid represents a map where <code>0</code>&#39;s represent water and <code>1</code>&#39;s represent land. Initially, all the cells of <code>grid</code> are water cells (i.e., all the cells are <code>0</code>&#39;s).</p>

<p>We may perform an add land operation which turns the water at position into a land. You are given an array <code>positions</code> where <code>positions[i] = [r<sub>i</sub>, c<sub>i</sub>]</code> is the position <code>(r<sub>i</sub>, c<sub>i</sub>)</code> at which we should operate the <code>i<sup>th</sup></code> operation.</p>

<p>Return <em>an array of integers</em> <code>answer</code> <em>where</em> <code>answer[i]</code> <em>is the number of islands after turning the cell</em> <code>(r<sub>i</sub>, c<sub>i</sub>)</code> <em>into a land</em>.</p>

<p>An <strong>island</strong> is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0305.Number%20of%20Islands%20II/images/tmp-grid.jpg" style="width: 500px; height: 294px;" />
<pre>
<strong>Input:</strong> m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
<strong>Output:</strong> [1,1,2,3]
<strong>Explanation:</strong>
Initially, the 2d grid is filled with water.
- Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
- Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
- Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
- Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> m = 1, n = 1, positions = [[0,0]]
<strong>Output:</strong> [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m, n, positions.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= m * n &lt;= 10<sup>4</sup></code></li>
	<li><code>positions[i].length == 2</code></li>
	<li><code>0 &lt;= r<sub>i</sub> &lt; m</code></li>
	<li><code>0 &lt;= c<sub>i</sub> &lt; n</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you solve it in time complexity <code>O(k log(mn))</code>, where <code>k == positions.length</code>?</p>

## Solutions

Union find.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numIslands2(self, m: int, n: int, positions: List[List[int]]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        grid = [[0] * n for _ in range(m)]
        cnt = 0
        p = list(range(m * n))
        ans = []
        for i, j in positions:
            if grid[i][j] == 1:
                ans.append(cnt)
                continue
            grid[i][j] = 1
            cnt += 1
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                if (
                    0 <= x < m
                    and 0 <= y < n
                    and grid[x][y] == 1
                    and find(i * n + j) != find(x * n + y)
                ):
                    p[find(i * n + j)] = find(x * n + y)
                    cnt -= 1
            ans.append(cnt)
        return ans
```

### **Java**

```java
class Solution {
    private int[] p;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        p = new int[m * n];
        for (int i = 0; i < p.length; ++i) {
            p[i] = i;
        }
        int[][] grid = new int[m][n];
        int cnt = 0;
        List<Integer> ans = new ArrayList<>();
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int[] pos : positions) {
            int i = pos[0];
            int j = pos[1];
            if (grid[i][j] == 1) {
                ans.add(cnt);
                continue;
            }
            grid[i][j] = 1;
            ++cnt;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k];
                int y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1
                    && find(x * n + y) != find(i * n + j)) {
                    p[find(x * n + y)] = find(i * n + j);
                    --cnt;
                }
            }
            ans.add(cnt);
        }
        return ans;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> p;

    vector<int> numIslands2(int m, int n, vector<vector<int>>& positions) {
        p.resize(m * n);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        vector<vector<int>> grid(m, vector<int>(n));
        vector<int> ans;
        int cnt = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        for (auto& pos : positions) {
            int i = pos[0], j = pos[1];
            if (grid[i][j] == 1) {
                ans.push_back(cnt);
                continue;
            }
            grid[i][j] = 1;
            ++cnt;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && find(x * n + y) != find(i * n + j)) {
                    p[find(x * n + y)] = find(i * n + j);
                    --cnt;
                }
            }
            ans.push_back(cnt);
        }
        return ans;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};
```

### **Go**

```go
func numIslands2(m int, n int, positions [][]int) []int {
	p := make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	grid := make([][]int, m)
	for i := 0; i < m; i++ {
		grid[i] = make([]int, n)
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	var ans []int
	cnt := 0
	dirs := []int{-1, 0, 1, 0, -1}
	for _, pos := range positions {
		i, j := pos[0], pos[1]
		if grid[i][j] == 1 {
			ans = append(ans, cnt)
			continue
		}
		grid[i][j] = 1
		cnt++
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && find(x*n+y) != find(i*n+j) {
				p[find(x*n+y)] = find(i*n + j)
				cnt--
			}
		}
		ans = append(ans, cnt)
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
