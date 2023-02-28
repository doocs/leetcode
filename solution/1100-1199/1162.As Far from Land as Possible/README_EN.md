# [1162. As Far from Land as Possible](https://leetcode.com/problems/as-far-from-land-as-possible)

[中文文档](/solution/1100-1199/1162.As%20Far%20from%20Land%20as%20Possible/README.md)

## Description

<p>Given an <code>n x n</code> <code>grid</code>&nbsp;containing only values <code>0</code> and <code>1</code>, where&nbsp;<code>0</code> represents water&nbsp;and <code>1</code> represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance.&nbsp;If no land or water exists in the grid, return <code>-1</code>.</p>

<p>The distance used in this problem is the Manhattan distance:&nbsp;the distance between two cells <code>(x0, y0)</code> and <code>(x1, y1)</code> is <code>|x0 - x1| + |y0 - y1|</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1162.As%20Far%20from%20Land%20as%20Possible/images/1336_ex1.jpg" style="width: 185px; height: 87px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,1],[0,0,0],[1,0,1]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The cell (1, 1) is as far as possible from all the land with distance 2.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1162.As%20Far%20from%20Land%20as%20Possible/images/1336_ex2.jpg" style="width: 184px; height: 87px;" />
<pre>
<strong>Input:</strong> grid = [[1,0,0],[0,0,0],[0,0,0]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The cell (2, 2) is as far as possible from all the land with distance 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 100</code></li>
	<li><code>grid[i][j]</code>&nbsp;is <code>0</code> or <code>1</code></li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxDistance(self, grid: List[List[int]]) -> int:
        n = len(grid)
        q = deque([(i, j) for i in range(n) for j in range(n) if grid[i][j] == 1])
        ans = -1
        valid = False
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                    x, y = i + a, b + j
                    if 0 <= x < n and 0 <= y < n and grid[x][y] == 0:
                        valid = True
                        grid[x][y] = 1
                        q.append((x, y))
        return ans if valid else -1
```

### **Java**

```java
class Solution {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    q.offer(new int[] {i, j});
                }
            }
        }
        int ans = -1;
        if (q.isEmpty() || q.size() == n * n) {
            return ans;
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; --i) {
                int[] p = q.poll();
                for (int k = 0; k < 4; ++k) {
                    int x = p[0] + dirs[k], y = p[1] + dirs[k + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        grid[x][y] = 1;
                        q.offer(new int[] {x, y});
                    }
                }
            }
            ++ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxDistance(vector<vector<int>>& grid) {
        int n = grid.size();
        queue<pair<int, int>> q;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j]) {
                    q.emplace(i, j);
                }
            }
        }
        int ans = -1;
        if (q.empty() || q.size() == n * n) {
            return ans;
        }
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            for (int m = q.size(); m; --m) {
                auto [i, j] = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && !grid[x][y]) {
                        grid[x][y] = 1;
                        q.emplace(x, y);
                    }
                }
            }
            ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxDistance(grid [][]int) int {
	n := len(grid)
	q := [][2]int{}
	for i, row := range grid {
		for j, v := range row {
			if v == 1 {
				q = append(q, [2]int{i, j})
			}
		}
	}
	ans := -1
	if len(q) == 0 || len(q) == n*n {
		return ans
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			for k := 0; k < 4; k++ {
				x, y := p[0]+dirs[k], p[1]+dirs[k+1]
				if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0 {
					grid[x][y] = 1
					q = append(q, [2]int{x, y})
				}
			}
		}
		ans++
	}
	return ans
}
```

### **TypeScript**

```ts
function maxDistance(grid: number[][]): number {
    const n = grid.length;
    const q: [number, number][] = [];
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 1) {
                q.push([i, j]);
            }
        }
    }
    let ans = -1;
    if (q.length === 0 || q.length === n * n) {
        return ans;
    }
    const dirs: number[] = [-1, 0, 1, 0, -1];
    while (q.length > 0) {
        for (let m = q.length; m; --m) {
            const [i, j] = q.shift()!;
            for (let k = 0; k < 4; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] === 0) {
                    grid[x][y] = 1;
                    q.push([x, y]);
                }
            }
        }
        ++ans;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
