# [2662. Minimum Cost of a Path With Special Roads](https://leetcode.com/problems/minimum-cost-of-a-path-with-special-roads)

[中文文档](/solution/2600-2699/2662.Minimum%20Cost%20of%20a%20Path%20With%20Special%20Roads/README.md)

<!-- tags:Graph,Array,Shortest Path,Heap (Priority Queue) -->

<!-- difficulty:Medium -->

## Description

<p>You are given an array <code>start</code> where <code>start = [startX, startY]</code> represents your initial position <code>(startX, startY)</code> in a 2D space. You are also given the array <code>target</code> where <code>target = [targetX, targetY]</code> represents your target position <code>(targetX, targetY)</code>.</p>

<p>The cost of going from a position <code>(x1, y1)</code> to any other position in the space <code>(x2, y2)</code> is <code>|x2 - x1| + |y2 - y1|</code>.</p>

<p>There are also some special roads. You are given a 2D array <code>specialRoads</code> where <code>specialRoads[i] = [x1<sub>i</sub>, y1<sub>i</sub>, x2<sub>i</sub>, y2<sub>i</sub>, cost<sub>i</sub>]</code> indicates that the <code>i<sup>th</sup></code> special road can take you from <code>(x1<sub>i</sub>, y1<sub>i</sub>)</code> to <code>(x2<sub>i</sub>, y2<sub>i</sub>)</code> with a cost equal to <code>cost<sub>i</sub></code>. You can use each special road any number of times.</p>

<p>Return <em>the minimum cost required to go from</em> <code>(startX, startY)</code> to <code>(targetX, targetY)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The optimal path from (1,1) to (4,5) is the following:
- (1,1) -&gt; (1,2). This move has a cost of |1 - 1| + |2 - 1| = 1.
- (1,2) -&gt; (3,3). This move uses the first special edge, the cost is 2.
- (3,3) -&gt; (3,4). This move has a cost of |3 - 3| + |4 - 3| = 1.
- (3,4) -&gt; (4,5). This move uses the second special edge, the cost is 1.
So the total cost is 1 + 2 + 1 + 1 = 5.
It can be shown that we cannot achieve a smaller total cost than 5.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> It is optimal to not use any special edges and go directly from the starting to the ending position with a cost |5 - 3| + |7 - 2| = 7.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

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

## Solutions

### Solution 1: Dijkstra

We can find that for each coordinate $(x, y)$ we visit, suppose the minimum cost from the start point to $(x, y)$ is $d$. If we choose to move directly to $(targetX, targetY)$, then the total cost is $d + |x - targetX| + |y - targetY|$. If we choose to go through a special path $(x_1, y_1) \rightarrow (x_2, y_2)$, then we need to spend $|x - x_1| + |y - y_1| + cost$ to move from $(x, y)$ to $(x_2, y_2)$.

Therefore, we can use Dijkstra algorithm to find the minimum cost from the start point to all points, and then choose the smallest one from them.

We define a priority queue $q$, each element in the queue is a triple $(d, x, y)$, which means that the minimum cost from the start point to $(x, y)$ is $d$. Initially, we add $(0, startX, startY)$ to the queue.

In each step, we take out the first element $(d, x, y)$ in the queue, at this time we can update the answer, that is $ans = \min(ans, d + dist(x, y, targetX, targetY))$. Then we enumerate all special paths $(x_1, y_1) \rightarrow (x_2, y_2)$ and add $(d + dist(x, y, x_1, y_1) + cost, x_2, y_2)$ to the queue.

Finally, when the queue is empty, we can get the answer.

The time complexity is $O(n^2 \times \log n)$, and the space complexity is $O(n^2)$. Where $n$ is the number of special paths.

<!-- tabs:start -->

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

<!-- end -->
