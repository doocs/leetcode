---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/README.md
rating: 1880
source: 第 67 场双周赛 Q3
tags:
    - 深度优先搜索
    - 广度优先搜索
    - 图
    - 几何
    - 数组
    - 数学
---

<!-- problem:start -->

# [2101. 引爆最多的炸弹](https://leetcode.cn/problems/detonate-the-maximum-bombs)

[English Version](/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个炸弹列表。一个炸弹的 <strong>爆炸范围</strong>&nbsp;定义为以炸弹为圆心的一个圆。</p>

<p>炸弹用一个下标从 <strong>0</strong>&nbsp;开始的二维整数数组&nbsp;<code>bombs</code>&nbsp;表示，其中&nbsp;<code>bombs[i] = [x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub>]</code>&nbsp;。<code>x<sub>i</sub></code> 和&nbsp;<code>y<sub>i</sub></code>&nbsp;表示第 <code>i</code>&nbsp;个炸弹的 X 和 Y 坐标，<code>r<sub>i</sub></code>&nbsp;表示爆炸范围的 <strong>半径</strong>&nbsp;。</p>

<p>你需要选择引爆 <strong>一个&nbsp;</strong>炸弹。当这个炸弹被引爆时，<strong>所有</strong> 在它爆炸范围内的炸弹都会被引爆，这些炸弹会进一步将它们爆炸范围内的其他炸弹引爆。</p>

<p>给你数组&nbsp;<code>bombs</code>&nbsp;，请你返回在引爆&nbsp;<strong>一个</strong>&nbsp;炸弹的前提下，<strong>最多</strong>&nbsp;能引爆的炸弹数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg-3.png" style="width: 300px; height: 300px;"></p>

<pre><b>输入：</b>bombs = [[2,1,3],[6,1,4]]
<b>输出：</b>2
<strong>解释：</strong>
上图展示了 2 个炸弹的位置和爆炸范围。
如果我们引爆左边的炸弹，右边的炸弹不会被影响。
但如果我们引爆右边的炸弹，两个炸弹都会爆炸。
所以最多能引爆的炸弹数目是 max(1, 2) = 2 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg-2.png" style="width: 300px; height: 300px;"></p>

<pre><b>输入：</b>bombs = [[1,1,5],[10,10,5]]
<b>输出：</b>1
<strong>解释：
</strong>引爆任意一个炸弹都不会引爆另一个炸弹。所以最多能引爆的炸弹数目为 1 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2101.Detonate%20the%20Maximum%20Bombs/images/desmos-eg1.png" style="width: 300px; height: 300px;"></p>

<pre><b>输入：</b>bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
<b>输出：</b>5
<strong>解释：</strong>
最佳引爆炸弹为炸弹 0 ，因为：
- 炸弹 0 引爆炸弹 1 和 2 。红色圆表示炸弹 0 的爆炸范围。
- 炸弹 2 引爆炸弹 3 。蓝色圆表示炸弹 2 的爆炸范围。
- 炸弹 3 引爆炸弹 4 。绿色圆表示炸弹 3 的爆炸范围。
所以总共有 5 个炸弹被引爆。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= bombs.length&nbsp;&lt;= 100</code></li>
	<li><code>bombs[i].length == 3</code></li>
	<li><code>1 &lt;= x<sub>i</sub>, y<sub>i</sub>, r<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：BFS

我们定义一个长度为 $n$ 的数组 $g$，其中 $g[i]$ 表示炸弹 $i$ 的爆炸范围内可以引爆的所有炸弹的下标。

然后，我们遍历所有炸弹，对于两个炸弹 $(x_1, y_1, r_1)$ 和 $(x_2, y_2, r_2)$，我们计算它们之间的距离 $\text{dist} = \sqrt{(x_1 - x_2)^2 + (y_1 - y_2)^2}$。如果 $\text{dist} \leq r_1$，那么炸弹 $i$ 的爆炸范围内可以引爆炸弹 $j$，我们就将 $j$ 添加到 $g[i]$ 中。如果 $\text{dist} \leq r_2$，那么炸弹 $j$ 的爆炸范围内可以引爆炸弹 $i$，我们就将 $i$ 添加到 $g[j]$ 中。

接下来，我们遍历所有炸弹，对于每个炸弹 $k$，我们使用广度优先搜索计算炸弹 $k$ 的爆炸范围内可以引爆的所有炸弹的下标，并记录下来。如果这些炸弹的数量等于 $n$，那么我们就可以引爆所有炸弹，直接返回 $n$。否则，我们记录下来这些炸弹的数量，并返回最大值。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为炸弹的数量。

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
