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
<p><strong class="example">Example 1:</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1765.Map%20of%20Highest%20Peak/images/screenshot-2021-01-11-at-82045-am.png" style="width: 220px; height: 219px;" /></strong></p>

<pre>
<strong>Input:</strong> isWater = [[0,1],[0,0]]
<strong>Output:</strong> [[1,0],[2,1]]
<strong>Explanation:</strong> The image shows the assigned heights of each cell.
The blue cell is the water cell, and the green cells are the land cells.
</pre>

<p><strong class="example">Example 2:</strong></p>

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
        for i, row in enumerate(isWater):
            for j, v in enumerate(row):
                if v:
                    q.append((i, j))
                    ans[i][j] = 0
        while q:
            i, j = q.popleft()
            for a, b in pairwise((-1, 0, 1, 0, -1)):
                x, y = i + a, j + b
                if 0 <= x < m and 0 <= y < n and ans[x][y] == -1:
                    ans[x][y] = ans[i][j] + 1
                    q.append((x, y))
        return ans
```

```python
class Solution:
    def highestPeak(self, isWater: List[List[int]]) -> List[List[int]]:
        m, n = len(isWater), len(isWater[0])
        ans = [[-1] * n for _ in range(m)]
        q = deque()
        for i, row in enumerate(isWater):
            for j, v in enumerate(row):
                if v:
                    q.append((i, j))
                    ans[i][j] = 0
        while q:
            for _ in range(len(q)):
                i, j = q.popleft()
                for a, b in pairwise((-1, 0, 1, 0, -1)):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and ans[x][y] == -1:
                        ans[x][y] = ans[i][j] + 1
                        q.append((x, y))
        return ans
```

### **Java**

```java
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] ans = new int[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = isWater[i][j] - 1;
                if (ans[i][j] == 0) {
                    q.offer(new int[] {i, j});
                }
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            var p = q.poll();
            int i = p[0], j = p[1];
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                    ans[x][y] = ans[i][j] + 1;
                    q.offer(new int[] {x, y});
                }
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] ans = new int[m][n];
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = isWater[i][j] - 1;
                if (ans[i][j] == 0) {
                    q.offer(new int[] {i, j});
                }
            }
        }
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            for (int t = q.size(); t > 0; --t) {
                var p = q.poll();
                int i = p[0], j = p[1];
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                        ans[x][y] = ans[i][j] + 1;
                        q.offer(new int[] {x, y});
                    }
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
    const int dirs[5] = {-1, 0, 1, 0, -1};

    vector<vector<int>> highestPeak(vector<vector<int>>& isWater) {
        int m = isWater.size(), n = isWater[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        queue<pair<int, int>> q;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = isWater[i][j] - 1;
                if (ans[i][j] == 0) {
                    q.emplace(i, j);
                }
            }
        }
        while (!q.empty()) {
            auto [i, j] = q.front();
            q.pop();
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                    ans[x][y] = ans[i][j] + 1;
                    q.emplace(x, y);
                }
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    const int dirs[5] = {-1, 0, 1, 0, -1};

    vector<vector<int>> highestPeak(vector<vector<int>>& isWater) {
        int m = isWater.size(), n = isWater[0].size();
        vector<vector<int>> ans(m, vector<int>(n));
        queue<pair<int, int>> q;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[i][j] = isWater[i][j] - 1;
                if (ans[i][j] == 0) {
                    q.emplace(i, j);
                }
            }
        }
        while (!q.empty()) {
            for (int t = q.size(); t; --t) {
                auto [i, j] = q.front();
                q.pop();
                for (int k = 0; k < 4; ++k) {
                    int x = i + dirs[k], y = j + dirs[k + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                        ans[x][y] = ans[i][j] + 1;
                        q.emplace(x, y);
                    }
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
	type pair struct{ i, j int }
	q := []pair{}
	for i, row := range isWater {
		ans[i] = make([]int, n)
		for j, v := range row {
			ans[i][j] = v - 1
			if v == 1 {
				q = append(q, pair{i, j})
			}
		}
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		i, j := p.i, p.j
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1 {
				ans[x][y] = ans[i][j] + 1
				q = append(q, pair{x, y})
			}
		}
	}
	return ans
}
```

```go
func highestPeak(isWater [][]int) [][]int {
	m, n := len(isWater), len(isWater[0])
	ans := make([][]int, m)
	type pair struct{ i, j int }
	q := []pair{}
	for i, row := range isWater {
		ans[i] = make([]int, n)
		for j, v := range row {
			ans[i][j] = v - 1
			if v == 1 {
				q = append(q, pair{i, j})
			}
		}
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for len(q) > 0 {
		for t := len(q); t > 0; t-- {
			p := q[0]
			q = q[1:]
			i, j := p.i, p.j
			for k := 0; k < 4; k++ {
				x, y := i+dirs[k], j+dirs[k+1]
				if x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1 {
					ans[x][y] = ans[i][j] + 1
					q = append(q, pair{x, y})
				}
			}
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function highestPeak(isWater: number[][]): number[][] {
    const m = isWater.length;
    const n = isWater[0].length;
    let ans: number[][] = [];
    let q: number[][] = [];
    for (let i = 0; i < m; ++i) {
        ans.push(new Array(n).fill(-1));
        for (let j = 0; j < n; ++j) {
            if (isWater[i][j]) {
                q.push([i, j]);
                ans[i][j] = 0;
            }
        }
    }
    const dirs = [-1, 0, 1, 0, -1];
    while (q.length) {
        let tq: number[][] = [];
        for (const [i, j] of q) {
            for (let k = 0; k < 4; k++) {
                const [x, y] = [i + dirs[k], j + dirs[k + 1]];
                if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                    tq.push([x, y]);
                    ans[x][y] = ans[i][j] + 1;
                }
            }
        }
        q = tq;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
