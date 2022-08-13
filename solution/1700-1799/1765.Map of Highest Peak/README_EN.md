# [1765. Map of Highest Peak](https://leetcode.com/problems/map-of-highest-peak)

[中文文档](/solution/1700-1799/1765.Map%20of%20Highest%20Peak/README.md)

## Description

<p>You are given an integer matrix <code>isWater</code> of size <code>m x n</code> that represents a map of <strong>land</strong> and <strong>water</strong> cells.</p>

<ul>
	<li>If <code>isWater[i][j] == 0</code>, cell <code>(i, j)</code> is a <strong>land</strong> cell.</li>
	<li>If <code>isWater[i][j] == 1</code>, cell <code>(i, j)</code> is a <strong>water</strong> cell.</li>
</ul>

<p>You must assign each cell a height in a way that follows these rules:</p>

<ul>
	<li>The height of each cell must be non-negative.</li>
	<li>If the cell is a <strong>water</strong> cell, its height must be <code>0</code>.</li>
	<li>Any two adjacent cells must have an absolute height difference of <strong>at most</strong> <code>1</code>. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).</li>
</ul>

<p>Find an assignment of heights such that the maximum height in the matrix is <strong>maximized</strong>.</p>

<p>Return <em>an integer matrix </em><code>height</code><em> of size </em><code>m x n</code><em> where </em><code>height[i][j]</code><em> is cell </em><code>(i, j)</code><em>&#39;s height. If there are multiple solutions, return <strong>any</strong> of them</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1765.Map%20of%20Highest%20Peak/images/screenshot-2021-01-11-at-82045-am.png" style="width: 220px; height: 219px;" /></strong></p>

<pre>
<strong>Input:</strong> isWater = [[0,1],[0,0]]
<strong>Output:</strong> [[1,0],[2,1]]
<strong>Explanation:</strong> The image shows the assigned heights of each cell.
The blue cell is the water cell, and the green cells are the land cells.
</pre>

<p><strong>Example 2:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1765.Map%20of%20Highest%20Peak/images/screenshot-2021-01-11-at-82050-am.png" style="width: 300px; height: 296px;" /></strong></p>

<pre>
<strong>Input:</strong> isWater = [[0,0,1],[1,0,0],[0,0,0]]
<strong>Output:</strong> [[1,1,0],[0,1,1],[1,2,2]]
<strong>Explanation:</strong> A height of 2 is the maximum possible height of any assignment.
Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == isWater.length</code></li>
	<li><code>n == isWater[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>isWater[i][j]</code> is <code>0</code> or <code>1</code>.</li>
	<li>There is at least <strong>one</strong> water cell.</li>
</ul>

## Solutions

BFS.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def highestPeak(self, isWater: List[List[int]]) -> List[List[int]]:
        m, n = len(isWater), len(isWater[0])
        ans = [[-1] * n for _ in range(m)]
        q = deque()
        for i in range(m):
            for j in range(n):
                if isWater[i][j] == 1:
                    ans[i][j] = 0
                    q.append((i, j))
        while q:
            i, j = q.popleft()
            for a, b in [[0, -1], [0, 1], [1, 0], [-1, 0]]:
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and ans[x][y] == -1:
                    ans[x][y] = ans[i][j] + 1
                    q.append((x, y))
        return ans
```

### **Java**

```java
class Solution {
    private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; ++i) {
            Arrays.fill(ans[i], -1);
        }
        Deque<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (isWater[i][j] == 1) {
                    ans[i][j] = 0;
                    q.offerLast(new int[]{i, j});
                }
            }
        }
        while (!q.isEmpty()) {
            int[] p = q.pollFirst();
            int i = p[0], j = p[1];
            for (int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                    ans[x][y] = ans[i][j] + 1;
                    q.offerLast(new int[]{x, y});
                }
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function highestPeak(isWater: number[][]): number[][] {
    const m = isWater.length,
        n = isWater[0].length;
    let ans: Array<Array<number>> = Array.from({ length: m }, v =>
        new Array(n).fill(-1),
    );
    // BFS
    let queue: Array<Array<number>> = []; // i, j, num
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            if (isWater[i][j]) {
                ans[i][j] = 0;
                queue.push([i, j, 0]);
            }
        }
    }
    const directions = [
        [0, -1],
        [-1, 0],
        [0, 1],
        [1, 0],
    ]; // left, up, right, down
    while (queue.length) {
        // 消除push/shift出现超时问题
        let tmp: Array<Array<number>> = [];
        for (const [i, j, num] of queue) {
            for (const [dx, dy] of directions) {
                const x = i + dx,
                    y = j + dy;
                // 校验合法的相邻格子
                if (x > -1 && x < m && y > -1 && y < n && ans[x][y] == -1) {
                    ans[x][y] = num + 1;
                    tmp.push([x, y, num + 1]);
                }
            }
        }
        queue = tmp;
    }
    return ans;
}
```

### **C++**

```cpp
typedef pair<int, int> PII;

class Solution {
public:
    vector<vector<int>> dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    vector<vector<int>> highestPeak(vector<vector<int>>& isWater) {
        int m = isWater.size(), n = isWater[0].size();
        vector<vector<int>> ans(m, vector<int>(n, -1));
        queue<PII> q;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (isWater[i][j] == 1) {
                    ans[i][j] = 0;
                    q.push({i, j});
                }
            }
        }
        while (!q.empty()) {
            PII p = q.front();
            q.pop();
            int i = p.first, j = p.second;
            for (auto& dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                    ans[x][y] = ans[i][j] + 1;
                    q.push({x, y});
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func highestPeak(isWater [][]int) [][]int {
	m, n := len(isWater), len(isWater[0])
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
		for j := range ans[i] {
			ans[i][j] = -1
		}
	}
	type pair struct{ i, j int }
	var q []pair
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if isWater[i][j] == 1 {
				ans[i][j] = 0
				q = append(q, pair{i, j})
			}
		}
	}
	dirs := [4][2]int{{0, -1}, {0, 1}, {1, 0}, {-1, 0}}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		for _, dir := range dirs {
			x, y := p.i+dir[0], p.j+dir[1]
			if x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1 {
				ans[x][y] = ans[p.i][p.j] + 1
				q = append(q, pair{x, y})
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
