# [2503. Maximum Number of Points From Grid Queries](https://leetcode.com/problems/maximum-number-of-points-from-grid-queries)

[中文文档](/solution/2500-2599/2503.Maximum%20Number%20of%20Points%20From%20Grid%20Queries/README.md)

## Description

<p>You are given an <code>m x n</code> integer matrix <code>grid</code> and an array <code>queries</code> of size <code>k</code>.</p>

<p>Find an array <code>answer</code> of size <code>k</code> such that for each integer <code>queries[i]</code> you start in the <strong>top left</strong> cell of the matrix and repeat the following process:</p>

<ul>
	<li>If <code>queries[i]</code> is <strong>strictly</strong> greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any <strong>adjacent</strong> cell in all <code>4</code> directions: up, down, left, and right.</li>
	<li>Otherwise, you do not get any points, and you end this process.</li>
</ul>

<p>After the process, <code>answer[i]</code> is the <strong>maximum</strong> number of points you can get. <strong>Note</strong> that for each query you are allowed to visit the same cell <strong>multiple</strong> times.</p>

<p>Return <em>the resulting array</em> <code>answer</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2503.Maximum%20Number%20of%20Points%20From%20Grid%20Queries/images/yetgriddrawio.png" style="width: 571px; height: 151px;" />
<pre>
<strong>Input:</strong> grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
<strong>Output:</strong> [5,8,1]
<strong>Explanation:</strong> The diagrams above show which cells we visit to get points for each query.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2503.Maximum%20Number%20of%20Points%20From%20Grid%20Queries/images/yetgriddrawio-2.png" />
<pre>
<strong>Input:</strong> grid = [[5,2,1],[1,1,2]], queries = [3]
<strong>Output:</strong> [0]
<strong>Explanation:</strong> We can not get any points because the value of the top left cell is already greater than or equal to 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 1000</code></li>
	<li><code>4 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>k == queries.length</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= grid[i][j], queries[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxPoints(self, grid: List[List[int]], queries: List[int]) -> List[int]:
        m, n = len(grid), len(grid[0])
        qs = sorted((v, i) for i, v in enumerate(queries))
        ans = [0] * len(qs)
        q = [(grid[0][0], 0, 0)]
        cnt = 0
        vis = [[False] * n for _ in range(m)]
        vis[0][0] = True
        for v, k in qs:
            while q and q[0][0] < v:
                _, i, j = heappop(q)
                cnt += 1
                for a, b in pairwise((-1, 0, 1, 0, -1)):
                    x, y = i + a, j + b
                    if 0 <= x < m and 0 <= y < n and not vis[x][y]:
                        heappush(q, (grid[x][y], x, y))
                        vis[x][y] = True
            ans[k] = cnt
        return ans
```

```python
class Solution:
    def maxPoints(self, grid: List[List[int]], queries: List[int]) -> List[int]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            p[pa] = pb
            size[pb] += size[pa]

        m, n = len(grid), len(grid[0])
        arr = sorted((grid[i][j], i, j) for i in range(m) for j in range(n))
        k = len(queries)
        ans = [0] * k
        p = list(range(m * n))
        size = [1] * len(p)
        j = 0
        for i, v in sorted(enumerate(queries), key=lambda x: x[1]):
            while j < len(arr) and arr[j][0] < v:
                _, a, b = arr[j]
                for x, y in pairwise((-1, 0, 1, 0, -1)):
                    c, d = a + x, b + y
                    if 0 <= c < m and 0 <= d < n and grid[c][d] < v:
                        union(a * n + b, c * n + d)
                j += 1
            if grid[0][0] < v:
                ans[i] = size[find(0)]
        return ans
```

### **Java**

```java
class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int k = queries.length;
        int[][] qs = new int[k][2];
        for (int i = 0; i < k; ++i) {
            qs[i] = new int[] {queries[i], i};
        }
        Arrays.sort(qs, (a, b) -> a[0] - b[0]);
        int[] ans = new int[k];
        int m = grid.length, n = grid[0].length;
        boolean[][] vis = new boolean[m][n];
        vis[0][0] = true;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        q.offer(new int[] {grid[0][0], 0, 0});
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        int cnt = 0;
        for (var e : qs) {
            int v = e[0];
            k = e[1];
            while (!q.isEmpty() && q.peek()[0] < v) {
                var p = q.poll();
                ++cnt;
                for (int h = 0; h < 4; ++h) {
                    int x = p[1] + dirs[h], y = p[2] + dirs[h + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                        vis[x][y] = true;
                        q.offer(new int[] {grid[x][y], x, y});
                    }
                }
            }
            ans[k] = cnt;
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

    vector<int> maxPoints(vector<vector<int>>& grid, vector<int>& queries) {
        int k = queries.size();
        vector<pair<int, int>> qs(k);
        for (int i = 0; i < k; ++i) qs[i] = {queries[i], i};
        sort(qs.begin(), qs.end());
        vector<int> ans(k);
        int m = grid.size(), n = grid[0].size();
        bool vis[m][n];
        memset(vis, 0, sizeof vis);
        vis[0][0] = true;
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> q;
        q.push({grid[0][0], 0, 0});
        int cnt = 0;
        for (auto& e : qs) {
            int v = e.first;
            k = e.second;
            while (!q.empty() && get<0>(q.top()) < v) {
                auto [_, i, j] = q.top();
                q.pop();
                ++cnt;
                for (int h = 0; h < 4; ++h) {
                    int x = i + dirs[h], y = j + dirs[h + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                        vis[x][y] = true;
                        q.push({grid[x][y], x, y});
                    }
                }
            }
            ans[k] = cnt;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxPoints(grid [][]int, queries []int) []int {
	k := len(queries)
	qs := make([]pair, k)
	for i, v := range queries {
		qs[i] = pair{v, i}
	}
	sort.Slice(qs, func(i, j int) bool { return qs[i].v < qs[j].v })
	ans := make([]int, k)
	m, n := len(grid), len(grid[0])
	q := hp{}
	heap.Push(&q, tuple{grid[0][0], 0, 0})
	dirs := []int{-1, 0, 1, 0, -1}
	vis := map[int]bool{0: true}
	cnt := 0
	for _, e := range qs {
		v := e.v
		k = e.i
		for len(q) > 0 && q[0].v < v {
			p := heap.Pop(&q).(tuple)
			i, j := p.i, p.j
			cnt++
			for h := 0; h < 4; h++ {
				x, y := i+dirs[h], j+dirs[h+1]
				if x >= 0 && x < m && y >= 0 && y < n && !vis[x*n+y] {
					vis[x*n+y] = true
					heap.Push(&q, tuple{grid[x][y], x, y})
				}
			}
		}
		ans[k] = cnt
	}
	return ans
}

type pair struct{ v, i int }

type tuple struct{ v, i, j int }
type hp []tuple

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].v < h[j].v }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **...**

```

```

<!-- tabs:end -->
