# [1162. 地图分析](https://leetcode.cn/problems/as-far-from-land-as-possible)

[English Version](/solution/1100-1199/1162.As%20Far%20from%20Land%20as%20Possible/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你现在手里有一份大小为<meta charset="UTF-8" />&nbsp;<code>n x n</code>&nbsp;的 网格 <code>grid</code>，上面的每个 单元格 都用&nbsp;<code>0</code>&nbsp;和&nbsp;<code>1</code>&nbsp;标记好了。其中&nbsp;<code>0</code>&nbsp;代表海洋，<code>1</code>&nbsp;代表陆地。</p>

<p>请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的，并返回该距离。如果网格上只有陆地或者海洋，请返回&nbsp;<code>-1</code>。</p>

<p>我们这里说的距离是「曼哈顿距离」（&nbsp;Manhattan Distance）：<code>(x0, y0)</code> 和&nbsp;<code>(x1, y1)</code>&nbsp;这两个单元格之间的距离是&nbsp;<code>|x0 - x1| + |y0 - y1|</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1162.As%20Far%20from%20Land%20as%20Possible/images/1336_ex1.jpeg" /></strong></p>

<pre>
<strong>输入：</strong>grid = [[1,0,1],[0,0,0],[1,0,1]]
<strong>输出：</strong>2
<strong>解释： </strong>
海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1100-1199/1162.As%20Far%20from%20Land%20as%20Possible/images/1336_ex2.jpeg" /></strong></p>

<pre>
<strong>输入：</strong>grid = [[1,0,0],[0,0,0],[0,0,0]]
<strong>输出：</strong>4
<strong>解释： </strong>
海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p><meta charset="UTF-8" /></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n&nbsp;&lt;= 100</code></li>
	<li><code>grid[i][j]</code>&nbsp;不是&nbsp;<code>0</code>&nbsp;就是&nbsp;<code>1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

BFS。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        int ans = -1;
        boolean valid = false;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                int[] p = q.poll();
                for (int i = 0; i < 4; ++i) {
                    int x = p[0] + dirs[i];
                    int y = p[1] + dirs[i + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        valid = true;
                        grid[x][y] = 1;
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
        return valid ? ans : -1;
    }
}
```

### **TypeScript**

```ts
function maxDistance(grid: number[][]): number {
    const m = grid.length,
        n = grid[0].length;
    let queue: Array<Array<number>> = [];
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j]) {
                queue.push([i, j]);
            }
        }
    }
    if ([0, m * n].includes(queue.length)) return -1;
    const directions = [
        [0, 1],
        [0, -1],
        [1, 0],
        [-1, 0],
    ];
    let depth = 1;
    while (queue.length) {
        depth += 1;
        let nextLevel: Array<Array<number>> = [];
        for (let [x, y] of queue) {
            for (let [dx, dy] of directions) {
                const [i, j] = [x + dx, y + dy];
                if (i >= 0 && i < m && j >= 0 && j < n && !grid[i][j]) {
                    grid[i][j] = depth;
                    nextLevel.push([i, j]);
                }
            }
        }
        queue = nextLevel;
    }
    return depth - 2;
}
```

### **C++**

```cpp
class Solution {
public:
    int maxDistance(vector<vector<int>>& grid) {
        int n = grid.size();
        typedef pair<int, int> pii;
        queue<pii> q;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                if (grid[i][j] == 1)
                    q.push({i, j});
        int ans = -1;
        bool valid = false;
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            ++ans;
            for (int k = q.size(); k > 0; --k) {
                pii p = q.front();
                q.pop();
                for (int i = 0; i < 4; ++i) {
                    int x = p.first + dirs[i];
                    int y = p.second + dirs[i + 1];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        valid = true;
                        grid[x][y] = 1;
                        q.push({x, y});
                    }
                }
            }
        }
        return valid ? ans : -1;
    }
};
```

### **Go**

```go
func maxDistance(grid [][]int) int {
	n := len(grid)
	var q [][]int
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 1 {
				q = append(q, []int{i, j})
			}
		}
	}
	ans := -1
	valid := false
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		ans++
		for k := len(q); k > 0; k-- {
			p := q[0]
			q = q[1:]
			for i := 0; i < 4; i++ {
				x, y := p[0]+dirs[i], p[1]+dirs[i+1]
				if x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0 {
					valid = true
					grid[x][y] = 1
					q = append(q, []int{x, y})
				}
			}
		}
	}
	if valid {
		return ans
	}
	return -1
}
```

### **...**

```

```

<!-- tabs:end -->
