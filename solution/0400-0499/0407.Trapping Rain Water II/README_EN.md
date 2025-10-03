---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0407.Trapping%20Rain%20Water%20II/README_EN.md
tags:
    - Breadth-First Search
    - Array
    - Matrix
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [407. Trapping Rain Water II](https://leetcode.com/problems/trapping-rain-water-ii)

[中文文档](/solution/0400-0499/0407.Trapping%20Rain%20Water%20II/README.md)

## Description

<!-- description:start -->

<p>Given an <code>m x n</code> integer matrix <code>heightMap</code> representing the height of each unit cell in a 2D elevation map, return <em>the volume of water it can trap after raining</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0407.Trapping%20Rain%20Water%20II/images/trap1-3d.jpg" style="width: 361px; height: 321px;" />
<pre>
<strong>Input:</strong> heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> After the rain, water is trapped between the blocks.
We have two small ponds 1 and 3 units trapped.
The total volume of water trapped is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0400-0499/0407.Trapping%20Rain%20Water%20II/images/trap2-3d.jpg" style="width: 401px; height: 321px;" />
<pre>
<strong>Input:</strong> heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
<strong>Output:</strong> 10
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == heightMap.length</code></li>
	<li><code>n == heightMap[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= heightMap[i][j] &lt;= 2 * 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Priority Queue (Min Heap)

This is a variant of the trapping rain water problem. Since the heights on the matrix boundaries are fixed, we can add these boundary heights to a priority queue. Then, we repeatedly take out the minimum height from the priority queue and compare it with the heights of its four adjacent cells. If an adjacent cell's height is less than the current height, we can trap water there. The volume of trapped water is the difference between the current height and the adjacent height. We then add the larger height back to the priority queue and repeat this process until the priority queue is empty.

The time complexity is $O(m \times n \times \log (m \times n))$, and the space complexity is $O(m \times n)$, where $m$ and $n$ are the number of rows and columns in the matrix, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def trapRainWater(self, heightMap: List[List[int]]) -> int:
        m, n = len(heightMap), len(heightMap[0])
        vis = [[False] * n for _ in range(m)]
        pq = []
        for i in range(m):
            for j in range(n):
                if i == 0 or i == m - 1 or j == 0 or j == n - 1:
                    heappush(pq, (heightMap[i][j], i, j))
                    vis[i][j] = True
        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        while pq:
            h, i, j = heappop(pq)
            for a, b in pairwise(dirs):
                x, y = i + a, j + b
                if x >= 0 and x < m and y >= 0 and y < n and not vis[x][y]:
                    ans += max(0, h - heightMap[x][y])
                    vis[x][y] = True
                    heappush(pq, (max(h, heightMap[x][y]), x, y))
        return ans
```

#### Java

```java
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] vis = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[] {heightMap[i][j], i, j});
                    vis[i][j] = true;
                }
            }
        }
        int ans = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            var p = pq.poll();
            for (int k = 0; k < 4; ++k) {
                int x = p[1] + dirs[k], y = p[2] + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                    ans += Math.max(0, p[0] - heightMap[x][y]);
                    vis[x][y] = true;
                    pq.offer(new int[] {Math.max(p[0], heightMap[x][y]), x, y});
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int trapRainWater(vector<vector<int>>& heightMap) {
        using tii = tuple<int, int, int>;
        priority_queue<tii, vector<tii>, greater<tii>> pq;
        int m = heightMap.size(), n = heightMap[0].size();
        bool vis[m][n];
        memset(vis, 0, sizeof vis);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.emplace(heightMap[i][j], i, j);
                    vis[i][j] = true;
                }
            }
        }
        int ans = 0;
        int dirs[5] = {-1, 0, 1, 0, -1};
        while (!pq.empty()) {
            auto [h, i, j] = pq.top();
            pq.pop();
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                    ans += max(0, h - heightMap[x][y]);
                    vis[x][y] = true;
                    pq.emplace(max(heightMap[x][y], h), x, y);
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func trapRainWater(heightMap [][]int) (ans int) {
	m, n := len(heightMap), len(heightMap[0])
	pq := hp{}
	vis := make([][]bool, m)
	for i, row := range heightMap {
		vis[i] = make([]bool, n)
		for j, v := range row {
			if i == 0 || i == m-1 || j == 0 || j == n-1 {
				heap.Push(&pq, tuple{v, i, j})
				vis[i][j] = true
			}
		}
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for len(pq) > 0 {
		p := heap.Pop(&pq).(tuple)
		for k := 0; k < 4; k++ {
			x, y := p.i+dirs[k], p.j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] {
				ans += max(0, p.v-heightMap[x][y])
				vis[x][y] = true
				heap.Push(&pq, tuple{max(p.v, heightMap[x][y]), x, y})
			}
		}
	}
	return
}

type tuple struct{ v, i, j int }
type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].v < h[j].v }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

#### TypeScript

```ts
function trapRainWater(heightMap: number[][]): number {
    const m = heightMap.length;
    const n = heightMap[0].length;
    const vis: boolean[][] = Array.from({ length: m }, () => new Array(n).fill(false));
    const pq = new MinPriorityQueue<[number, number, number]>({
        compare: (a, b) => a[0] - b[0],
    });
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i === 0 || i === m - 1 || j === 0 || j === n - 1) {
                pq.enqueue([heightMap[i][j], i, j]);
                vis[i][j] = true;
            }
        }
    }

    let ans = 0;
    const dirs = [-1, 0, 1, 0, -1];
    while (!pq.isEmpty()) {
        const [h, i, j] = pq.dequeue();
        for (let k = 0; k < 4; ++k) {
            const x = i + dirs[k];
            const y = j + dirs[k + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && !vis[x][y]) {
                ans += Math.max(0, h - heightMap[x][y]);
                vis[x][y] = true;
                pq.enqueue([Math.max(h, heightMap[x][y]), x, y]);
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
