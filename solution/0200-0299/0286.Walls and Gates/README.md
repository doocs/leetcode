---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0200-0299/0286.Walls%20and%20Gates/README.md
tags:
    - 广度优先搜索
    - 数组
    - 矩阵
---

# [286. 墙与门 🔒](https://leetcode.cn/problems/walls-and-gates)

[English Version](/solution/0200-0299/0286.Walls%20and%20Gates/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你被给定一个 <code>m × n</code> 的二维网格 <code>rooms</code> ，网格中有以下三种可能的初始化值：</p>

<ol>
	<li><code>-1</code> 表示墙或是障碍物</li>
	<li><code>0</code> 表示一扇门</li>
	<li><code>INF</code> 无限表示一个空的房间。然后，我们用 <code>2<sup>31</sup> - 1 = 2147483647</code> 代表 <code>INF</code>。你可以认为通往门的距离总是小于 <code>2147483647</code> 的。</li>
</ol>

<p>你要给每个空房间位上填上该房间到 <strong>最近门的距离</strong> ，如果无法到达门，则填 <code>INF</code> 即可。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0286.Walls%20and%20Gates/images/grid.jpg" style="width: 500px; height: 223px;" />
<pre>
<strong>输入：</strong>rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
<strong>输出：</strong>[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>rooms = [[-1]]
<strong>输出：</strong>[[-1]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>rooms = [[2147483647]]
<strong>输出：</strong>[[2147483647]]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>rooms = [[0]]
<strong>输出：</strong>[[0]]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == rooms.length</code></li>
	<li><code>n == rooms[i].length</code></li>
	<li><code>1 <= m, n <= 250</code></li>
	<li><code>rooms[i][j]</code> 是 <code>-1</code>、<code>0</code> 或 <code>2<sup>31</sup> - 1</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
