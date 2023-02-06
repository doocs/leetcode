# [1765. 地图中的最高点](https://leetcode.cn/problems/map-of-highest-peak)

[English Version](/solution/1700-1799/1765.Map%20of%20Highest%20Peak/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为&nbsp;<code>m x n</code>&nbsp;的整数矩阵&nbsp;<code>isWater</code>&nbsp;，它代表了一个由 <strong>陆地</strong>&nbsp;和 <strong>水域</strong>&nbsp;单元格组成的地图。</p>

<ul>
	<li>如果&nbsp;<code>isWater[i][j] == 0</code>&nbsp;，格子&nbsp;<code>(i, j)</code>&nbsp;是一个 <strong>陆地</strong>&nbsp;格子。</li>
	<li>如果&nbsp;<code>isWater[i][j] == 1</code>&nbsp;，格子&nbsp;<code>(i, j)</code>&nbsp;是一个 <strong>水域</strong>&nbsp;格子。</li>
</ul>

<p>你需要按照如下规则给每个单元格安排高度：</p>

<ul>
	<li>每个格子的高度都必须是非负的。</li>
	<li>如果一个格子是 <strong>水域</strong>&nbsp;，那么它的高度必须为 <code>0</code>&nbsp;。</li>
	<li>任意相邻的格子高度差 <strong>至多</strong>&nbsp;为 <code>1</code>&nbsp;。当两个格子在正东、南、西、北方向上相互紧挨着，就称它们为相邻的格子。（也就是说它们有一条公共边）</li>
</ul>

<p>找到一种安排高度的方案，使得矩阵中的最高高度值&nbsp;<strong>最大</strong>&nbsp;。</p>

<p>请你返回一个大小为&nbsp;<code>m x n</code>&nbsp;的整数矩阵 <code>height</code>&nbsp;，其中 <code>height[i][j]</code>&nbsp;是格子 <code>(i, j)</code>&nbsp;的高度。如果有多种解法，请返回&nbsp;<strong>任意一个</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1765.Map%20of%20Highest%20Peak/images/screenshot-2021-01-11-at-82045-am.png" style="width: 220px; height: 219px;" /></strong></p>

<pre>
<b>输入：</b>isWater = [[0,1],[0,0]]
<b>输出：</b>[[1,0],[2,1]]
<b>解释：</b>上图展示了给各个格子安排的高度。
蓝色格子是水域格，绿色格子是陆地格。
</pre>

<p><strong>示例 2：</strong></p>

<p><strong><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1700-1799/1765.Map%20of%20Highest%20Peak/images/screenshot-2021-01-11-at-82050-am.png" style="width: 300px; height: 296px;" /></strong></p>

<pre>
<b>输入：</b>isWater = [[0,0,1],[1,0,0],[0,0,0]]
<b>输出：</b>[[1,1,0],[0,1,1],[1,2,2]]
<b>解释：</b>所有安排方案中，最高可行高度为 2 。
任意安排方案中，只要最高高度为 2 且符合上述规则的，都为可行方案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == isWater.length</code></li>
	<li><code>n == isWater[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 1000</code></li>
	<li><code>isWater[i][j]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;，要么是&nbsp;<code>1</code>&nbsp;。</li>
	<li>至少有 <strong>1</strong>&nbsp;个水域格子。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：BFS**

根据题目描述，水域的高度必须是 $0$，而任意相邻格子的高度差至多为 $1$。因此，我们可以从所有水域格子出发，用 BFS 搜索相邻且未访问过的格子，将其高度置为当前格子的高度再加一。

最后返回结果矩阵即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别是整数矩阵 `isWater` 的行数和列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
