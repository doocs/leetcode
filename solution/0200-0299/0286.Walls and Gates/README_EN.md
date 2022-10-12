# [286. Walls and Gates](https://leetcode.com/problems/walls-and-gates)

[中文文档](/solution/0200-0299/0286.Walls%20and%20Gates/README.md)

## Description

<p>You are given an <code>m x n</code> grid <code>rooms</code>&nbsp;initialized with these three possible values.</p>

<ul>
	<li><code>-1</code>&nbsp;A wall or an obstacle.</li>
	<li><code>0</code> A gate.</li>
	<li><code>INF</code> Infinity means an empty room. We use the value <code>2<sup>31</sup> - 1 = 2147483647</code> to represent <code>INF</code> as you may assume that the distance to a gate is less than <code>2147483647</code>.</li>
</ul>

<p>Fill each empty room with the distance to <em>its nearest gate</em>. If it is impossible to reach a gate, it should be filled with <code>INF</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0286.Walls%20and%20Gates/images/grid.jpg" style="width: 500px; height: 223px;" />
<pre>
<strong>Input:</strong> rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
<strong>Output:</strong> [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> rooms = [[-1]]
<strong>Output:</strong> [[-1]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == rooms.length</code></li>
	<li><code>n == rooms[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 250</code></li>
	<li><code>rooms[i][j]</code> is <code>-1</code>, <code>0</code>, or <code>2<sup>31</sup> - 1</code>.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def wallsAndGates(self, rooms: List[List[int]]) -> None:
        """
        Do not return anything, modify rooms in-place instead.
        """
        m, n = len(rooms), len(rooms[0])
        inf = 2**31 - 1
        q = deque([(i, j) for i in range(m) for j in range(n) if rooms[i][j] == 0])
        d = 0
        while q:
            d += 1
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and rooms[x][y] == inf:
                        rooms[x][y] = d
                        q.append((x, y))
```

### **Java**

```java
class Solution {
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rooms[i][j] == 0) {
                    q.offer(new int[] {i, j});
                }
            }
        }
        int d = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            ++d;
            for (int i = q.size(); i > 0; --i) {
                int[] p = q.poll();
                for (int j = 0; j < 4; ++j) {
                    int x = p[0] + dirs[j];
                    int y = p[1] + dirs[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] == Integer.MAX_VALUE) {
                        rooms[x][y] = d;
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    void wallsAndGates(vector<vector<int>>& rooms) {
        int m = rooms.size();
        int n = rooms[0].size();
        queue<pair<int, int>> q;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                if (rooms[i][j] == 0)
                    q.emplace(i, j);
        int d = 0;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            ++d;
            for (int i = q.size(); i > 0; --i) {
                auto p = q.front();
                q.pop();
                for (int j = 0; j < 4; ++j) {
                    int x = p.first + dirs[j];
                    int y = p.second + dirs[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] == INT_MAX) {
                        rooms[x][y] = d;
                        q.emplace(x, y);
                    }
                }
            }
        }
    }
};
```

### **Go**

```go
func wallsAndGates(rooms [][]int) {
	m, n := len(rooms), len(rooms[0])
	var q [][]int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if rooms[i][j] == 0 {
				q = append(q, []int{i, j})
			}
		}
	}
	d := 0
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		d++
		for i := len(q); i > 0; i-- {
			p := q[0]
			q = q[1:]
			for j := 0; j < 4; j++ {
				x, y := p[0]+dirs[j], p[1]+dirs[j+1]
				if x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] == math.MaxInt32 {
					rooms[x][y] = d
					q = append(q, []int{x, y})
				}
			}
		}
	}
}
```

### **...**

```

```

<!-- tabs:end -->
