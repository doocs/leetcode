# [2503. 矩阵查询可获得的最大分数](https://leetcode.cn/problems/maximum-number-of-points-from-grid-queries)

[English Version](/solution/2500-2599/2503.Maximum%20Number%20of%20Points%20From%20Grid%20Queries/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个大小为 <code>m x n</code> 的整数矩阵 <code>grid</code> 和一个大小为 <code>k</code> 的数组 <code>queries</code> 。</p>

<p>找出一个大小为 <code>k</code> 的数组 <code>answer</code> ，且满足对于每个整数 <code>queres[i]</code> ，你从矩阵 <strong>左上角</strong> 单元格开始，重复以下过程：</p>

<ul>
	<li>如果 <code>queries[i]</code> <strong>严格</strong> 大于你当前所处位置单元格，如果该单元格是第一次访问，则获得 1 分，并且你可以移动到所有 <code>4</code> 个方向（上、下、左、右）上任一 <strong>相邻</strong> 单元格。</li>
	<li>否则，你不能获得任何分，并且结束这一过程。</li>
</ul>

<p>在过程结束后，<code>answer[i]</code> 是你可以获得的最大分数。注意，对于每个查询，你可以访问同一个单元格 <strong>多次</strong> 。</p>

<p>返回结果数组 <code>answer</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2503.Maximum%20Number%20of%20Points%20From%20Grid%20Queries/images/yetgriddrawio.png" style="width: 571px; height: 151px;">
<pre><strong>输入：</strong>grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
<strong>输出：</strong>[5,8,1]
<strong>解释：</strong>上图展示了每个查询中访问并获得分数的单元格。</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2500-2599/2503.Maximum%20Number%20of%20Points%20From%20Grid%20Queries/images/yetgriddrawio-2.png">
<pre><strong>输入：</strong>grid = [[5,2,1],[1,1,2]], queries = [3]
<strong>输出：</strong>[0]
<strong>解释：</strong>无法获得分数，因为左上角单元格的值大于等于 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>2 &lt;= m, n &lt;= 1000</code></li>
	<li><code>4 &lt;= m * n &lt;= 10<sup>5</sup></code></li>
	<li><code>k == queries.length</code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= grid[i][j], queries[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：离线查询 + BFS + 优先队列（小根堆）**

根据题目描述我们知道，每个查询相互独立，查询的顺序不影响结果，并且题目要我们每次从左上角开始，统计所有可以访问的、且值小于当前查询值的单元格的个数。

因此，我们可以先对 `queries` 数组进行排序，然后从小到大依次处理每个查询。

我们用优先队列（小根堆）维护当前访问到的最小单元格的值，用数组或哈希表 `vis` 记录当前单元格是否已经访问过。初始时，将左上角单元格的数据 $(grid[0][0], 0, 0)$ 作为三元组加入优先队列，并将 `vis[0][0]` 置为 `True`。

对于每个查询 `queries[i]`，我们判断当前优先队列的最小值是否小于 `queries[i]`，如果是，则将当前最小值弹出，累加计数器 `cnt`，并将当前单元格的上下左右四个单元格加入优先队列，注意要判断是否已经访问过。重复上述操作，直到当前优先队列的最小值大于等于 `queries[i]`，此时 `cnt` 即为当前查询的答案。

时间复杂度 $O(k \times \log k + m \times n \log(m \times n))$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为网格的行数和列数，而 $k$ 为查询的个数。我们需要对 `queries` 数组进行排序，时间复杂度为 $O(k \times \log k)$。矩阵中的每个单元格最多只会被访问一次，每一次入队和出队的时间复杂度为 $O(\log(m \times n))$。因此，总时间复杂度为 $O(k \times \log k + m \times n \log(m \times n))$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
