# [1499. 满足不等式的最大值](https://leetcode.cn/problems/max-value-of-equation)

[English Version](/solution/1400-1499/1499.Max%20Value%20of%20Equation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组 <code>points</code> 和一个整数 <code>k</code> 。数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序。也就是说 <code>points[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> ，并且在 <code>1 &lt;= i &lt; j &lt;= points.length</code> 的前提下， <code>x<sub>i</sub> &lt; x<sub>j</sub></code> 总成立。</p>

<p>请你找出<em> </em><code>y<sub>i</sub>&nbsp;+ y<sub>j</sub>&nbsp;+ |x<sub>i</sub>&nbsp;- x<sub>j</sub>|</code> 的 <strong>最大值</strong>，其中 <code>|x<sub>i</sub>&nbsp;- x<sub>j</sub>|&nbsp;&lt;= k</code> 且 <code>1 &lt;= i &lt; j &lt;= points.length</code>。</p>

<p>题目测试数据保证至少存在一对能够满足 <code>|x<sub>i</sub>&nbsp;- x<sub>j</sub>|&nbsp;&lt;= k</code> 的点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
<strong>输出：</strong>4
<strong>解释：</strong>前两个点满足 |x<sub>i</sub>&nbsp;- x<sub>j</sub>| &lt;= 1 ，代入方程计算，则得到值 3 + 0 + |1 - 2| = 4 。第三个和第四个点也满足条件，得到值 10 + -10 + |5 - 6| = 1 。
没有其他满足条件的点，所以返回 4 和 1 中最大的那个。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>points = [[0,0],[3,0],[9,2]], k = 3
<strong>输出：</strong>3
<strong>解释：</strong>只有前两个点满足 |x<sub>i</sub>&nbsp;- x<sub>j</sub>| &lt;= 3 ，代入方程后得到值 0 + 0 + |0 - 3| = 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= points.length &lt;= 10^5</code></li>
	<li><code>points[i].length == 2</code></li>
	<li><code>-10^8&nbsp;&lt;= points[i][0], points[i][1] &lt;= 10^8</code></li>
	<li><code>0 &lt;= k &lt;= 2 * 10^8</code></li>
	<li>对于所有的<code>1 &lt;= i &lt; j &lt;= points.length</code> ，<code>points[i][0] &lt; points[j][0]</code> 都成立。也就是说，<code>x<sub>i</sub></code> 是严格递增的。</li>
</ul>

## 解法

### 方法一：优先队列（大根堆）

题目要求 $y_i + y_j + |x_i - x_j|$ 的最大值，其中 $i \lt j$，并且 $|x_i - x_j| \leq k$。由于 $x_i$ 是严格单调递增的，那么：

$$
\begin{aligned}
y_i + y_j + |x_i - x_j| & = y_i + y_j + x_j - x_i \\
& = (y_i - x_i) + (x_j + y_j)
\end{aligned}
$$

因此，对于当前遍历到的点 $(x_j, y_j)$，我们只需要找到前面所有满足 $x_j - x_i \leq k$ 的点 $(x_i, y_i)$ 中 $y_i - x_i$ 的最大值，再加上当前的 $x_j + y_j$ 即可。而 $y_i - x_i$ 的最大值，我们可以使用优先队列（大根堆）来维护。

具体地，我们定义一个优先队列（大根堆） $pq$，堆中每个元素是一个二元组 $(y_i - x_i, x_i)$。

当我们遍历到点 $(x, y)$ 时，如果堆 $pq$ 不为空，并且 $x - pq[0][1] \gt k$，那么循环将堆顶元素弹出，直到堆为空或者满足 $x - pq[0][1] \leq k$。此时，堆顶元素 $(y_i - x_i, x_i)$ 即为所有满足 $x_j - x_i \leq k$ 的点中 $y_i - x_i$ 的最大值，此时更新答案 $ans = \max(ans, x + y + pq[0][0])$。

然后，我们将点 $(x, y)$ 加入堆中，继续遍历下一个点，直到遍历完整个数组 $points$。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $points$ 的长度。

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

### 方法二：单调队列

这道题实际上需要我们维护的是一个长度为 $k$ 的窗口中 $y-x$ 的最大值，单调队列可以很好地解决这个问题。

具体地，我们定义一个单调队列 $q$，队列中每个元素是一个二元组 $(x_i, y_i)$。

当我们遍历到点 $(x, y)$ 时，如果队列 $q$ 不为空，并且 $x - q[0][0] \gt k$，那么不断弹出队首元素，直到队列为空或者满足 $x - q[0][0] \leq k$。此时，队首元素 $(x_i, y_i)$ 即为所有满足 $x_j - x_i \leq k$ 的点中 $y_i - x_i$ 的最大值，此时更新答案 $ans = \max(ans, x + y + y_i - x_i)$。

接下来，在将点 $(x, y)$ 加入队尾之前，我们将队列中所有 $y_i - x_i \leq y - x$ 的元素 $(x_i, y_i)$ 弹出队列，然后将点 $(x, y)$ 加入队尾。继续遍历下一个点，直到遍历完整个数组 $points$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $points$ 的长度。

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
