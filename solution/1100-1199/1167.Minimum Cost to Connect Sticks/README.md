# [1167. 连接木棍的最低费用](https://leetcode.cn/problems/minimum-cost-to-connect-sticks)

[English Version](/solution/1100-1199/1167.Minimum%20Cost%20to%20Connect%20Sticks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一些长度为正整数的木棍。这些长度以数组<meta charset="UTF-8" />&nbsp;<code>sticks</code>&nbsp;的形式给出，<meta charset="UTF-8" />&nbsp;<code>sticks[i]</code>&nbsp;是第 <code>i</code> 个木棍的长度。</p>

<p>你可以通过支付 <code>x + y</code> 的成本将任意两个长度为 <code>x</code> 和 <code>y</code> 的木棍连接成一个木棍。你必须连接所有的木棍，直到剩下一个木棍。</p>

<p>返回以这种方式将所有给定的木棍连接成一个木棍的<em> 最小成本 </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>sticks = [2,4,3]
<strong>输出：</strong>14
<strong>解释：</strong>从 sticks = [2,4,3] 开始。
1. 连接 2 和 3 ，费用为 2 + 3 = 5 。现在 sticks = [5,4]
2. 连接 5 和 4 ，费用为 5 + 4 = 9 。现在 sticks = [9]
所有木棍已经连成一根，总费用 5 + 9 = 14
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>sticks = [1,8,3,5]
<strong>输出：</strong>30
<strong>解释：</strong>从 sticks = [1,8,3,5] 开始。
1. 连接 1 和 3 ，费用为 1 + 3 = 4 。现在 sticks = [4,8,5]
2. 连接 4 和 5 ，费用为 4 + 5 = 9 。现在 sticks = [9,8]
3. 连接 9 和 8 ，费用为 9 + 8 = 17 。现在 sticks = [17]
所有木棍已经连成一根，总费用 4 + 9 + 17 = 30
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>sticks = [5]
<strong>输出：</strong>0
<strong>解释：</strong>只有一根木棍，不必再连接。总费用 0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= sticks.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= sticks[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一：贪心 + 优先队列（小根堆）

我们可以使用贪心的思路，每次选择最短的两根棍子进行拼接，这样可以保证拼接的代价最小。

因此，我们可以使用优先队列（小根堆）来维护当前棍子的长度，每次从优先队列中取出两根棍子进行拼接，再将拼接后的棍子放回优先队列中，直到优先队列中只剩下一根棍子为止。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 `sticks` 的长度。

<!-- tabs:start -->

```python
class Solution:
    def connectSticks(self, sticks: List[int]) -> int:
        heapify(sticks)
        ans = 0
        while len(sticks) > 1:
            z = heappop(sticks) + heappop(sticks)
            ans += z
            heappush(sticks, z)
        return ans
```

```java
class Solution {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : sticks) {
            pq.offer(x);
        }
        int ans = 0;
        while (pq.size() > 1) {
            int z = pq.poll() + pq.poll();
            ans += z;
            pq.offer(z);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int connectSticks(vector<int>& sticks) {
        priority_queue<int, vector<int>, greater<int>> pq;
        for (auto& x : sticks) {
            pq.push(x);
        }
        int ans = 0;
        while (pq.size() > 1) {
            int x = pq.top();
            pq.pop();
            int y = pq.top();
            pq.pop();
            int z = x + y;
            ans += z;
            pq.push(z);
        }
        return ans;
    }
};
```

```go
func connectSticks(sticks []int) (ans int) {
	hp := &hp{sticks}
	heap.Init(hp)
	for hp.Len() > 1 {
		x, y := heap.Pop(hp).(int), heap.Pop(hp).(int)
		ans += x + y
		heap.Push(hp, x+y)
	}
	return
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

```ts
function connectSticks(sticks: number[]): number {
    const pq = new Heap(sticks);
    let ans = 0;
    while (pq.size() > 1) {
        const x = pq.pop();
        const y = pq.pop();
        ans += x + y;
        pq.push(x + y);
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
