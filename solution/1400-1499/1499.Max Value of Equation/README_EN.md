---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1499.Max%20Value%20of%20Equation/README_EN.md
rating: 2456
tags:
    - Queue
    - Array
    - Sliding Window
    - Monotonic Queue
    - Heap (Priority Queue)
---

# [1499. Max Value of Equation](https://leetcode.com/problems/max-value-of-equation)

[中文文档](/solution/1400-1499/1499.Max%20Value%20of%20Equation/README.md)

## Description

<p>You are given an array <code>points</code> containing the coordinates of points on a 2D plane, sorted by the x-values, where <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> such that <code>x<sub>i</sub> &lt; x<sub>j</sub></code> for all <code>1 &lt;= i &lt; j &lt;= points.length</code>. You are also given an integer <code>k</code>.</p>

<p>Return <em>the maximum value of the equation </em><code>y<sub>i</sub> + y<sub>j</sub> + |x<sub>i</sub> - x<sub>j</sub>|</code> where <code>|x<sub>i</sub> - x<sub>j</sub>| &lt;= k</code> and <code>1 &lt;= i &lt; j &lt;= points.length</code>.</p>

<p>It is guaranteed that there exists at least one pair of points that satisfy the constraint <code>|x<sub>i</sub> - x<sub>j</sub>| &lt;= k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
<strong>Output:</strong> 4
<strong>Explanation:</strong> The first two points satisfy the condition |x<sub>i</sub> - x<sub>j</sub>| &lt;= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
No other pairs satisfy the condition, so we return the max of 4 and 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> points = [[0,0],[3,0],[9,2]], k = 3
<strong>Output:</strong> 3
<strong>Explanation: </strong>Only the first two points have an absolute difference of 3 or less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= points.length &lt;= 10<sup>5</sup></code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-10<sup>8</sup> &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>8</sup></code></li>
	<li><code>0 &lt;= k &lt;= 2 * 10<sup>8</sup></code></li>
	<li><code>x<sub>i</sub> &lt; x<sub>j</sub></code> for all <code>1 &lt;= i &lt; j &lt;= points.length</code></li>
	<li><code>x<sub>i</sub></code> form a strictly increasing sequence.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def findMaxValueOfEquation(self, points: List[List[int]], k: int) -> int:
        ans = -inf
        pq = []
        for x, y in points:
            while pq and x - pq[0][1] > k:
                heappop(pq)
            if pq:
                ans = max(ans, x + y - pq[0][0])
            heappush(pq, (x - y, x))
        return ans
```

```java
class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int ans = -(1 << 30);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (var p : points) {
            int x = p[0], y = p[1];
            while (!pq.isEmpty() && x - pq.peek()[1] > k) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                ans = Math.max(ans, x + y + pq.peek()[0]);
            }
            pq.offer(new int[] {y - x, x});
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findMaxValueOfEquation(vector<vector<int>>& points, int k) {
        int ans = -(1 << 30);
        priority_queue<pair<int, int>> pq;
        for (auto& p : points) {
            int x = p[0], y = p[1];
            while (pq.size() && x - pq.top().second > k) {
                pq.pop();
            }
            if (pq.size()) {
                ans = max(ans, x + y + pq.top().first);
            }
            pq.emplace(y - x, x);
        }
        return ans;
    }
};
```

```go
func findMaxValueOfEquation(points [][]int, k int) int {
	ans := -(1 << 30)
	hp := hp{}
	for _, p := range points {
		x, y := p[0], p[1]
		for hp.Len() > 0 && x-hp[0].x > k {
			heap.Pop(&hp)
		}
		if hp.Len() > 0 {
			ans = max(ans, x+y+hp[0].v)
		}
		heap.Push(&hp, pair{y - x, x})
	}
	return ans
}

type pair struct{ v, x int }

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.v > b.v
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

```ts
function findMaxValueOfEquation(points: number[][], k: number): number {
    let ans = -(1 << 30);
    const pq = new Heap<[number, number]>((a, b) => b[0] - a[0]);
    for (const [x, y] of points) {
        while (pq.size() && x - pq.top()[1] > k) {
            pq.pop();
        }
        if (pq.size()) {
            ans = Math.max(ans, x + y + pq.top()[0]);
        }
        pq.push([y - x, x]);
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

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def findMaxValueOfEquation(self, points: List[List[int]], k: int) -> int:
        ans = -inf
        q = deque()
        for x, y in points:
            while q and x - q[0][0] > k:
                q.popleft()
            if q:
                ans = max(ans, x + y + q[0][1] - q[0][0])
            while q and y - x >= q[-1][1] - q[-1][0]:
                q.pop()
            q.append((x, y))
        return ans
```

```java
class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int ans = -(1 << 30);
        Deque<int[]> q = new ArrayDeque<>();
        for (var p : points) {
            int x = p[0], y = p[1];
            while (!q.isEmpty() && x - q.peekFirst()[0] > k) {
                q.pollFirst();
            }
            if (!q.isEmpty()) {
                ans = Math.max(ans, x + y + q.peekFirst()[1] - q.peekFirst()[0]);
            }
            while (!q.isEmpty() && y - x >= q.peekLast()[1] - q.peekLast()[0]) {
                q.pollLast();
            }
            q.offerLast(p);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int findMaxValueOfEquation(vector<vector<int>>& points, int k) {
        int ans = -(1 << 30);
        deque<pair<int, int>> q;
        for (auto& p : points) {
            int x = p[0], y = p[1];
            while (!q.empty() && x - q.front().first > k) {
                q.pop_front();
            }
            if (!q.empty()) {
                ans = max(ans, x + y + q.front().second - q.front().first);
            }
            while (!q.empty() && y - x >= q.back().second - q.back().first) {
                q.pop_back();
            }
            q.emplace_back(x, y);
        }
        return ans;
    }
};
```

```go
func findMaxValueOfEquation(points [][]int, k int) int {
	ans := -(1 << 30)
	q := [][2]int{}
	for _, p := range points {
		x, y := p[0], p[1]
		for len(q) > 0 && x-q[0][0] > k {
			q = q[1:]
		}
		if len(q) > 0 {
			ans = max(ans, x+y+q[0][1]-q[0][0])
		}
		for len(q) > 0 && y-x >= q[len(q)-1][1]-q[len(q)-1][0] {
			q = q[:len(q)-1]
		}
		q = append(q, [2]int{x, y})
	}
	return ans
}
```

```ts
function findMaxValueOfEquation(points: number[][], k: number): number {
    let ans = -(1 << 30);
    const q: number[][] = [];
    for (const [x, y] of points) {
        while (q.length > 0 && x - q[0][0] > k) {
            q.shift();
        }
        if (q.length > 0) {
            ans = Math.max(ans, x + y + q[0][1] - q[0][0]);
        }
        while (q.length > 0 && y - x > q[q.length - 1][1] - q[q.length - 1][0]) {
            q.pop();
        }
        q.push([x, y]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
