---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/README_EN.md
rating: 1880
source: Biweekly Contest 67 Q3
tags:
    - Depth-First Search
    - Breadth-First Search
    - Graph
    - Geometry
    - Array
    - Math
---

<!-- problem:start -->

# [2101. Detonate the Maximum Bombs](https://leetcode.com/problems/detonate-the-maximum-bombs)

[中文文档](/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/README.md)

## Description

<!-- description:start -->

<p>You are given a list of bombs. The <strong>range</strong> of a bomb is defined as the area where its effect can be felt. This area is in the shape of a <strong>circle</strong> with the center as the location of the bomb.</p>

<p>The bombs are represented by a <strong>0-indexed</strong> 2D integer array <code>bombs</code> where <code>bombs[i] = [x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub>]</code>. <code>x<sub>i</sub></code> and <code>y<sub>i</sub></code> denote the X-coordinate and Y-coordinate of the location of the <code>i<sup>th</sup></code> bomb, whereas <code>r<sub>i</sub></code> denotes the <strong>radius</strong> of its range.</p>

<p>You may choose to detonate a <strong>single</strong> bomb. When a bomb is detonated, it will detonate <strong>all bombs</strong> that lie in its range. These bombs will further detonate the bombs that lie in their ranges.</p>

<p>Given the list of <code>bombs</code>, return <em>the <strong>maximum</strong> number of bombs that can be detonated if you are allowed to detonate <strong>only one</strong> bomb</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg-3.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> bombs = [[2,1,3],[6,1,4]]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
The above figure shows the positions and ranges of the 2 bombs.
If we detonate the left bomb, the right bomb will not be affected.
But if we detonate the right bomb, both bombs will be detonated.
So the maximum bombs that can be detonated is max(1, 2) = 2.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg-2.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> bombs = [[1,1,5],[10,10,5]]
<strong>Output:</strong> 1
<strong>Explanation:
</strong>Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg1.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
<strong>Output:</strong> 5
<strong>Explanation:</strong>
The best bomb to detonate is bomb 0 because:
- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
Thus all 5 bombs are detonated.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= bombs.length&nbsp;&lt;= 100</code></li>
	<li><code>bombs[i].length == 3</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: BFS

We define an array $g$ of length $n$, where $g[i]$ represents the indices of all bombs that can be triggered by bomb $i$ within its explosion range.

Next, we iterate over all bombs. For two bombs $(x_1, y_1, r_1)$ and $(x_2, y_2, r_2)$, we calculate the distance between them $\textit{dist} = \sqrt{(x_1 - x_2)^2 + (y_1 - y_2)^2}$. If $\textit{dist} \leq r_1$, then bomb $i$ can trigger bomb $j$ within its explosion range, so we add $j$ to $g[i]$. If $\textit{dist} \leq r_2$, then bomb $j$ can trigger bomb $i$ within its explosion range, so we add $i$ to $g[j]$.

Next, we iterate over all bombs. For each bomb $k$, we use breadth-first search to calculate the indices of all bombs that can be triggered by bomb $k$ within its explosion range and record them. If the number of these bombs equals $n$, then we can trigger all bombs and directly return $n$. Otherwise, we record the number of these bombs and return the maximum value.

The time complexity is $O(n^3)$ and the space complexity is $O(n^2)$, where $n$ is the number of bombs.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumDetonation(self, bombs: List[List[int]]) -> int:
        n = len(bombs)
        g = [[] for _ in range(n)]
        for i in range(n - 1):
            x1, y1, r1 = bombs[i]
            for j in range(i + 1, n):
                x2, y2, r2 = bombs[j]
                dist = hypot(x1 - x2, y1 - y2)
                if dist <= r1:
                    g[i].append(j)
                if dist <= r2:
                    g[j].append(i)
        ans = 0
        for k in range(n):
            vis = {k}
            q = [k]
            for i in q:
                for j in g[i]:
                    if j not in vis:
                        vis.add(j)
                        q.append(j)
            if len(vis) == n:
                return n
            ans = max(ans, len(vis))
        return ans
```

#### Java

```java
class Solution {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int[] p1 = bombs[i], p2 = bombs[j];
                double dist = Math.hypot(p1[0] - p2[0], p1[1] - p2[1]);
                if (dist <= p1[2]) {
                    g[i].add(j);
                }
                if (dist <= p2[2]) {
                    g[j].add(i);
                }
            }
        }
        int ans = 0;
        boolean[] vis = new boolean[n];
        for (int k = 0; k < n; ++k) {
            Arrays.fill(vis, false);
            vis[k] = true;
            int cnt = 0;
            Deque<Integer> q = new ArrayDeque<>();
            q.offer(k);
            while (!q.isEmpty()) {
                int i = q.poll();
                ++cnt;
                for (int j : g[i]) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.offer(j);
                    }
                }
            }
            if (cnt == n) {
                return n;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumDetonation(vector<vector<int>>& bombs) {
        int n = bombs.size();
        vector<int> g[n];
        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                auto& p1 = bombs[i];
                auto& p2 = bombs[j];
                auto dist = hypot(p1[0] - p2[0], p1[1] - p2[1]);
                if (dist <= p1[2]) {
                    g[i].push_back(j);
                }
                if (dist <= p2[2]) {
                    g[j].push_back(i);
                }
            }
        }
        int ans = 0;
        bool vis[n];
        for (int k = 0; k < n; ++k) {
            memset(vis, false, sizeof(vis));
            queue<int> q;
            q.push(k);
            vis[k] = true;
            int cnt = 0;
            while (!q.empty()) {
                int i = q.front();
                q.pop();
                ++cnt;
                for (int j : g[i]) {
                    if (!vis[j]) {
                        vis[j] = true;
                        q.push(j);
                    }
                }
            }
            if (cnt == n) {
                return n;
            }
            ans = max(ans, cnt);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumDetonation(bombs [][]int) (ans int) {
	n := len(bombs)
	g := make([][]int, n)
	for i, p1 := range bombs[:n-1] {
		for j := i + 1; j < n; j++ {
			p2 := bombs[j]
			dist := math.Hypot(float64(p1[0]-p2[0]), float64(p1[1]-p2[1]))
			if dist <= float64(p1[2]) {
				g[i] = append(g[i], j)
			}
			if dist <= float64(p2[2]) {
				g[j] = append(g[j], i)
			}
		}
	}
	for k := 0; k < n; k++ {
		q := []int{k}
		vis := make([]bool, n)
		vis[k] = true
		cnt := 0
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			cnt++
			for _, j := range g[i] {
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
		}
		if cnt == n {
			return n
		}
		ans = max(ans, cnt)
	}
	return
}
```

#### TypeScript

```ts
function maximumDetonation(bombs: number[][]): number {
    const n = bombs.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < n - 1; ++i) {
        const [x1, y1, r1] = bombs[i];
        for (let j = i + 1; j < n; ++j) {
            const [x2, y2, r2] = bombs[j];
            const d = Math.hypot(x1 - x2, y1 - y2);
            if (d <= r1) {
                g[i].push(j);
            }
            if (d <= r2) {
                g[j].push(i);
            }
        }
    }
    let ans = 0;
    for (let k = 0; k < n; ++k) {
        const vis: Set<number> = new Set([k]);
        const q: number[] = [k];
        for (const i of q) {
            for (const j of g[i]) {
                if (!vis.has(j)) {
                    vis.add(j);
                    q.push(j);
                }
            }
        }
        if (vis.size === n) {
            return n;
        }
        ans = Math.max(ans, vis.size);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
