# [1091. Shortest Path in Binary Matrix](https://leetcode.com/problems/shortest-path-in-binary-matrix)

[中文文档](/solution/1000-1099/1091.Shortest%20Path%20in%20Binary%20Matrix/README.md)

## Description

<p>Given an <code>n x n</code> binary matrix <code>grid</code>, return <em>the length of the shortest <strong>clear path</strong> in the matrix</em>. If there is no clear path, return <code>-1</code>.</p>

<p>A <strong>clear path</strong> in a binary matrix is a path from the <strong>top-left</strong> cell (i.e., <code>(0, 0)</code>) to the <strong>bottom-right</strong> cell (i.e., <code>(n - 1, n - 1)</code>) such that:</p>

<ul>
	<li>All the visited cells of the path are <code>0</code>.</li>
	<li>All the adjacent cells of the path are <strong>8-directionally</strong> connected (i.e., they are different and they share an edge or a corner).</li>
</ul>

<p>The <strong>length of a clear path</strong> is the number of visited cells of this path.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1091.Shortest%20Path%20in%20Binary%20Matrix/images/example1_1.png" style="width: 500px; height: 234px;" />
<pre>
<strong>Input:</strong> grid = [[0,1],[1,0]]
<strong>Output:</strong> 2
</pre>

<p><strong>Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1000-1099/1091.Shortest%20Path%20in%20Binary%20Matrix/images/example2_1.png" style="height: 216px; width: 500px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,0],[1,1,0],[1,1,0]]
<strong>Output:</strong> 4
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0,0],[1,1,0],[1,1,0]]
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>grid[i][j] is 0 or 1</code></li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shortestPathBinaryMatrix(self, grid: List[List[int]]) -> int:
        if grid[0][0]:
            return -1
        n = len(grid)
        q = deque([(0, 0)])
        grid[0][0] = 1
        ans = 0
        while q:
            ans += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                if (i, j) == (n - 1, n - 1):
                    return ans
                for x in range(i - 1, i + 2):
                    for y in range(j - 1, j + 2):
                        if 0 <= x < n and 0 <= y < n and grid[x][y] == 0:
                            q.append((x, y))
                            grid[x][y] = 1
        return -1
```

### **Java**

```java
class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        int n = grid.length;
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        grid[0][0] = 1;
        int ans = 0;
        while (!q.isEmpty()) {
            ++ans;
            for (int m = q.size(); m > 0; --m) {
                int[] p = q.poll();
                int i = p[0], j = p[1];
                if (i == n - 1 && j == n - 1) {
                    return ans;
                }
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                            q.offer(new int[]{x, y});
                            grid[x][y] = 1;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
        if (grid[0][0]) return -1;
        int n = grid.size();
        queue<pair<int, int>> q;
        q.push({0, 0});
        grid[0][0] = 1;
        int ans = 0;
        while (!q.empty()) {
            ++ans;
            for (int m = q.size(); m > 0; --m) {
                auto p = q.front();
                q.pop();
                int i = p.first, j = p.second;
                if (i == n - 1 && j == n - 1) return ans;
                for (int x = i - 1; x <= i + 1; ++x) {
                    for (int y = j - 1; y <= j + 1; ++y) {
                        if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                            q.push({x, y});
                            grid[x][y] = 1;
                        }
                    }
                }
            }
        }
        return -1;
    }
};
```

### **Go**

```go
func shortestPathBinaryMatrix(grid [][]int) int {
	if grid[0][0] == 1 {
		return -1
	}
	n := len(grid)
	q := [][]int{[]int{0, 0}}
	grid[0][0] = 1
	ans := 0
	for len(q) > 0 {
		ans++
		for m := len(q); m > 0; m-- {
			p := q[0]
			q = q[1:]
			i, j := p[0], p[1]
			if i == n-1 && j == n-1 {
				return ans
			}
			for x := i - 1; x <= i+1; x++ {
				for y := j - 1; y <= j+1; y++ {
					if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0 {
						q = append(q, []int{x, y})
						grid[x][y] = 1
					}
				}
			}
		}
	}
	return -1
}
```

### **Rust**

```rust
use std::collections::VecDeque;
impl Solution {
    pub fn shortest_path_binary_matrix(mut grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let mut queue = VecDeque::new();
        queue.push_back([0, 0]);
        let mut res = 0;
        while !queue.is_empty() {
            res += 1;
            for _ in 0..queue.len() {
                let [i, j] = queue.pop_front().unwrap();
                if grid[i][j] == 1 {
                    continue;
                }
                if i == n - 1 && j == n - 1 {
                    return res;
                }
                grid[i][j] = 1;
                for x in -1..=1 {
                    for y in -1..=1 {
                        let x = x + i as i32;
                        let y = y + j as i32;
                        if x < 0 || x == n as i32 || y < 0 || y == n as i32 {
                            continue;
                        }
                        queue.push_back([x as usize, y as usize]);
                    }
                }
            }
        }
        -1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
