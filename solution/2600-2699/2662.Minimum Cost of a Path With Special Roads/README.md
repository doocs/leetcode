---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2600-2699/2662.Minimum%20Cost%20of%20a%20Path%20With%20Special%20Roads/README.md
rating: 2153
source: 第 343 场周赛 Q3
tags:
    - 图
    - 数组
    - 最短路
    - 堆（优先队列）
---

<!-- problem:start -->

# [2662. 前往目标的最小代价](https://leetcode.cn/problems/minimum-cost-of-a-path-with-special-roads)

[English Version](/solution/2600-2699/2662.Minimum%20Cost%20of%20a%20Path%20With%20Special%20Roads/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组 <code>start</code> ，其中 <code>start = [startX, startY]</code> 表示你的初始位置位于二维空间上的 <code>(startX, startY)</code> 。另给你一个数组 <code>target</code> ，其中 <code>target = [targetX, targetY]</code> 表示你的目标位置 <code>(targetX, targetY)</code> 。</p>

<p>从位置 <code>(x1, y1)</code> 到空间中任一其他位置 <code>(x2, y2)</code> 的代价是 <code>|x2 - x1| + |y2 - y1|</code> 。</p>

<p>给你一个二维数组 <code>specialRoads</code> ，表示空间中存在的一些特殊路径。其中 <code>specialRoads[i] = [x1<sub>i</sub>, y1<sub>i</sub>, x2<sub>i</sub>, y2<sub>i</sub>, cost<sub>i</sub>]</code> 表示第 <code>i</code> 条特殊路径可以从 <code>(x1<sub>i</sub>, y1<sub>i</sub>)</code> 到 <code>(x2<sub>i</sub>, y2<sub>i</sub>)</code> ，但成本等于 <code>cost<sub>i</sub></code> 。你可以使用每条特殊路径任意次数。</p>

<p>返回从 <code>(startX, startY)</code> 到 <code>(targetX, targetY)</code> 所需的最小代价。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
<strong>输出：</strong>5
<strong>解释：</strong>从 (1,1) 到 (4,5) 的最优路径如下：
- (1,1) -&gt; (1,2) ，移动的代价是 |1 - 1| + |2 - 1| = 1 。
- (1,2) -&gt; (3,3) ，移动使用第一条特殊路径，代价是 2 。
- (3,3) -&gt; (3,4) ，移动的代价是 |3 - 3| + |4 - 3| = 1.
- (3,4) -&gt; (4,5) ，移动使用第二条特殊路径，代价是 1 。
总代价是 1 + 2 + 1 + 1 = 5 。
可以证明无法以小于 5 的代价完成从 (1,1) 到 (4,5) 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
<strong>输出：</strong>7
<strong>解释：</strong>最优路径是不使用任何特殊路径，直接以 |5 - 3| + |7 - 2| = 7 的代价从初始位置到达目标位置。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>start.length == target.length == 2</code></li>
	<li><code>1 &lt;= startX &lt;= targetX &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= startY &lt;= targetY &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= specialRoads.length &lt;= 200</code></li>
	<li><code>specialRoads[i].length == 5</code></li>
	<li><code>startX &lt;= x1<sub>i</sub>, x2<sub>i</sub> &lt;= targetX</code></li>
	<li><code>startY &lt;= y1<sub>i</sub>, y2<sub>i</sub> &lt;= targetY</code></li>
	<li><code>1 &lt;= cost<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：Dijkstra

我们可以发现，对于访问到的每个坐标点 $(x, y)$，假设从起点到 $(x, y)$ 的最小代价为 $d$。如果选择直接移动到 $(targetX, targetY)$，那么总代价就是 $d + |x - targetX| + |y - targetY|$。如果选择经过某条特殊路径 $(x_1, y_1) \rightarrow (x_2, y_2)$，那么我们需要可以花费 $|x - x_1| + |y - y_1| + cost$ 的代价，从 $(x, y)$ 移动到 $(x_2, y_2)$。

因此，我们可以使用 Dijkstra 算法求出从起点到所有点的最小代价，然后从中选择最小的那个。

我们定义一个优先队列 $q$，队列中的每一个元素是一个三元组 $(d, x, y)$，表示从起点到 $(x, y)$ 的最小代价为 $d$。初始时，我们将 $(0, startX, startY)$ 加入队列中。

在每一步中，我们取出队首元素 $(d, x, y)$，此时我们可以更新答案，即 $ans = \min(ans, d + dist(x, y, targetX, targetY))$。然后我们枚举所有的特殊路径 $(x_1, y_1) \rightarrow (x_2, y_2)$，将 $(d + dist(x, y, x_1, y_1) + cost, x_2, y_2)$ 加入队列中。

最后当队列为空时，我们就可以得到答案。

时间复杂度 $O(n^2 \times \log n)$，空间复杂度 $O(n^2)$。其中 $n$ 是特殊路径的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumCost(
        self, start: List[int], target: List[int], specialRoads: List[List[int]]
    ) -> int:
        def dist(x1: int, y1: int, x2: int, y2: int) -> int:
            return abs(x1 - x2) + abs(y1 - y2)

        q = [(0, start[0], start[1])]
        vis = set()
        ans = inf
        while q:
            d, x, y = heappop(q)
            if (x, y) in vis:
                continue
            vis.add((x, y))
            ans = min(ans, d + dist(x, y, *target))
            for x1, y1, x2, y2, cost in specialRoads:
                heappush(q, (d + dist(x, y, x1, y1) + cost, x2, y2))
        return ans
```

#### Java

```java
class Solution {
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int ans = 1 << 30;
        int n = 1000000;
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Set<Long> vis = new HashSet<>();
        q.offer(new int[] {0, start[0], start[1]});
        while (!q.isEmpty()) {
            var p = q.poll();
            int x = p[1], y = p[2];
            long k = 1L * x * n + y;
            if (vis.contains(k)) {
                continue;
            }
            vis.add(k);
            int d = p[0];
            ans = Math.min(ans, d + dist(x, y, target[0], target[1]));
            for (var r : specialRoads) {
                int x1 = r[0], y1 = r[1], x2 = r[2], y2 = r[3], cost = r[4];
                q.offer(new int[] {d + dist(x, y, x1, y1) + cost, x2, y2});
            }
        }
        return ans;
    }

    private int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumCost(vector<int>& start, vector<int>& target, vector<vector<int>>& specialRoads) {
        auto dist = [](int x1, int y1, int x2, int y2) {
            return abs(x1 - x2) + abs(y1 - y2);
        };
        int ans = 1 << 30;
        int n = 1e6;
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> pq;
        pq.push({0, start[0], start[1]});
        unordered_set<long long> vis;
        while (!pq.empty()) {
            auto [d, x, y] = pq.top();
            pq.pop();
            long long k = 1LL * x * n + y;
            if (vis.count(k)) {
                continue;
            }
            vis.insert(k);
            ans = min(ans, d + dist(x, y, target[0], target[1]));
            for (auto& r : specialRoads) {
                int x1 = r[0], y1 = r[1], x2 = r[2], y2 = r[3], cost = r[4];
                pq.push({d + dist(x, y, x1, y1) + cost, x2, y2});
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumCost(start []int, target []int, specialRoads [][]int) int {
	ans := 1 << 30
	const n int = 1e6
	pq := hp{{0, start[0], start[1]}}
	vis := map[int]bool{}
	for len(pq) > 0 {
		p := pq[0]
		heap.Pop(&pq)
		d, x, y := p.d, p.x, p.y
		if vis[x*n+y] {
			continue
		}
		vis[x*n+y] = true
		ans = min(ans, d+dist(x, y, target[0], target[1]))
		for _, r := range specialRoads {
			x1, y1, x2, y2, cost := r[0], r[1], r[2], r[3], r[4]
			heap.Push(&pq, tuple{d + dist(x, y, x1, y1) + cost, x2, y2})
		}
	}
	return ans
}

func dist(x1, y1, x2, y2 int) int {
	return abs(x1-x2) + abs(y1-y2)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

type tuple struct {
	d, x, y int
}
type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].d < h[j].d }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

#### TypeScript

```ts
function minimumCost(start: number[], target: number[], specialRoads: number[][]): number {
    const dist = (x1: number, y1: number, x2: number, y2: number): number => {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    };
    const q = new Heap<[number, number, number]>((a, b) => a[0] - b[0]);
    q.push([0, start[0], start[1]]);
    const n = 1000000;
    const vis: Set<number> = new Set();
    let ans = 1 << 30;
    while (q.size()) {
        const [d, x, y] = q.pop();
        const k = x * n + y;
        if (vis.has(k)) {
            continue;
        }
        vis.add(k);
        ans = Math.min(ans, d + dist(x, y, target[0], target[1]));
        for (const [x1, y1, x2, y2, cost] of specialRoads) {
            q.push([d + dist(x, y, x1, y1) + cost, x2, y2]);
        }
    }
    return ans;
}

type Compare<T> = (lhs: T, rhs: T) => number;

class Heap<T = number> {
    data: Array<T | null>;
    lt: (i: number, j: number) => boolean;
    constructor();
    constructor(data: T[]);
    constructor(compare: Compare<T>);
    constructor(data: T[], compare: Compare<T>);
    constructor(data: T[] | Compare<T>, compare?: (lhs: T, rhs: T) => number);
    constructor(
        data: T[] | Compare<T> = [],
        compare: Compare<T> = (lhs: T, rhs: T) => (lhs < rhs ? -1 : lhs > rhs ? 1 : 0),
    ) {
        if (typeof data === 'function') {
            compare = data;
            data = [];
        }
        this.data = [null, ...data];
        this.lt = (i, j) => compare(this.data[i]!, this.data[j]!) < 0;
        for (let i = this.size(); i > 0; i--) this.heapify(i);
    }

    size(): number {
        return this.data.length - 1;
    }

    push(v: T): void {
        this.data.push(v);
        let i = this.size();
        while (i >> 1 !== 0 && this.lt(i, i >> 1)) this.swap(i, (i >>= 1));
    }

    pop(): T {
        this.swap(1, this.size());
        const top = this.data.pop();
        this.heapify(1);
        return top!;
    }

    top(): T {
        return this.data[1]!;
    }
    heapify(i: number): void {
        while (true) {
            let min = i;
            const [l, r, n] = [i * 2, i * 2 + 1, this.data.length];
            if (l < n && this.lt(l, min)) min = l;
            if (r < n && this.lt(r, min)) min = r;
            if (min !== i) {
                this.swap(i, min);
                i = min;
            } else break;
        }
    }

    clear(): void {
        this.data = [null];
    }

    private swap(i: number, j: number): void {
        const d = this.data;
        [d[i], d[j]] = [d[j], d[i]];
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
