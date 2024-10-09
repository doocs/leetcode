---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1845.Seat%20Reservation%20Manager/README.md
rating: 1428
source: 第 51 场双周赛 Q2
tags:
    - 设计
    - 堆（优先队列）
---

<!-- problem:start -->

# [1845. 座位预约管理系统](https://leetcode.cn/problems/seat-reservation-manager)

[English Version](/solution/1800-1899/1845.Seat%20Reservation%20Manager/README_EN.md)

## 题目描述

<!-- description:start -->

<p>请你设计一个管理 <code>n</code> 个座位预约的系统，座位编号从 <code>1</code> 到 <code>n</code> 。</p>

<p>请你实现 <code>SeatManager</code> 类：</p>

<ul>
	<li><code>SeatManager(int n)</code> 初始化一个 <code>SeatManager</code> 对象，它管理从 <code>1</code> 到 <code>n</code> 编号的 <code>n</code> 个座位。所有座位初始都是可预约的。</li>
	<li><code>int reserve()</code> 返回可以预约座位的 <strong>最小编号</strong> ，此座位变为不可预约。</li>
	<li><code>void unreserve(int seatNumber)</code> 将给定编号 <code>seatNumber</code> 对应的座位变成可以预约。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>
["SeatManager", "reserve", "reserve", "unreserve", "reserve", "reserve", "reserve", "reserve", "unreserve"]
[[5], [], [], [2], [], [], [], [], [5]]
<strong>输出：</strong>
[null, 1, 2, null, 2, 3, 4, 5, null]

<strong>解释：</strong>
SeatManager seatManager = new SeatManager(5); // 初始化 SeatManager ，有 5 个座位。
seatManager.reserve();    // 所有座位都可以预约，所以返回最小编号的座位，也就是 1 。
seatManager.reserve();    // 可以预约的座位为 [2,3,4,5] ，返回最小编号的座位，也就是 2 。
seatManager.unreserve(2); // 将座位 2 变为可以预约，现在可预约的座位为 [2,3,4,5] 。
seatManager.reserve();    // 可以预约的座位为 [2,3,4,5] ，返回最小编号的座位，也就是 2 。
seatManager.reserve();    // 可以预约的座位为 [3,4,5] ，返回最小编号的座位，也就是 3 。
seatManager.reserve();    // 可以预约的座位为 [4,5] ，返回最小编号的座位，也就是 4 。
seatManager.reserve();    // 唯一可以预约的是座位 5 ，所以返回 5 。
seatManager.unreserve(5); // 将座位 5 变为可以预约，现在可预约的座位为 [5] 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= seatNumber &lt;= n</code></li>
	<li>每一次对 <code>reserve</code> 的调用，题目保证至少存在一个可以预约的座位。</li>
	<li>每一次对 <code>unreserve</code> 的调用，题目保证 <code>seatNumber</code> 在调用函数前都是被预约状态。</li>
	<li>对 <code>reserve</code> 和 <code>unreserve</code> 的调用 <strong>总共</strong> 不超过 <code>10<sup>5</sup></code> 次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（小根堆）

我们定义一个优先队列（小根堆）$\textit{q}$，用于存储所有可预约的座位编号。初始时，我们将 $1$ 到 $n$ 的所有座位编号加入到 $\textit{q}$ 中。

调用 `reserve` 方法时，我们从 $\textit{q}$ 中弹出堆顶元素，即可预约的座位编号的最小值。

调用 `unreserve` 方法时，我们将座位编号加入到 $\textit{q}$ 中。

时间复杂度方面，初始化的时间复杂度为 $O(n)$ 或 $O(n \times \log n)$，`reserve` 和 `unreserve` 方法的时间复杂度均为 $O(\log n)$。空间复杂度为 $O(n)$。

<!-- tabs:start -->

#### Python3

```python
class SeatManager:
    def __init__(self, n: int):
        self.q = list(range(1, n + 1))

    def reserve(self) -> int:
        return heappop(self.q)

    def unreserve(self, seatNumber: int) -> None:
        heappush(self.q, seatNumber)


# Your SeatManager object will be instantiated and called as such:
# obj = SeatManager(n)
# param_1 = obj.reserve()
# obj.unreserve(seatNumber)
```

#### Java

```java
class SeatManager {
    private PriorityQueue<Integer> q = new PriorityQueue<>();

    public SeatManager(int n) {
        for (int i = 1; i <= n; ++i) {
            q.offer(i);
        }
    }

    public int reserve() {
        return q.poll();
    }

    public void unreserve(int seatNumber) {
        q.offer(seatNumber);
    }
}

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.reserve();
 * obj.unreserve(seatNumber);
 */
```

#### C++

```cpp
class SeatManager {
public:
    SeatManager(int n) {
        for (int i = 1; i <= n; ++i) {
            q.push(i);
        }
    }

    int reserve() {
        int seat = q.top();
        q.pop();
        return seat;
    }

    void unreserve(int seatNumber) {
        q.push(seatNumber);
    }

private:
    priority_queue<int, vector<int>, greater<int>> q;
};

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager* obj = new SeatManager(n);
 * int param_1 = obj->reserve();
 * obj->unreserve(seatNumber);
 */
```

#### Go

```go
type SeatManager struct {
	q hp
}

func Constructor(n int) SeatManager {
	q := hp{}
	for i := 1; i <= n; i++ {
		heap.Push(&q, i)
	}
	return SeatManager{q}
}

func (this *SeatManager) Reserve() int {
	return heap.Pop(&this.q).(int)
}

func (this *SeatManager) Unreserve(seatNumber int) {
	heap.Push(&this.q, seatNumber)
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

/**
 * Your SeatManager object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Reserve();
 * obj.Unreserve(seatNumber);
 */
```

#### TypeScript

```ts
class SeatManager {
    private q: typeof MinPriorityQueue;
    constructor(n: number) {
        this.q = new MinPriorityQueue();
        for (let i = 1; i <= n; i++) {
            this.q.enqueue(i);
        }
    }

    reserve(): number {
        return this.q.dequeue().element;
    }

    unreserve(seatNumber: number): void {
        this.q.enqueue(seatNumber);
    }
}

/**
 * Your SeatManager object will be instantiated and called as such:
 * var obj = new SeatManager(n)
 * var param_1 = obj.reserve()
 * obj.unreserve(seatNumber)
 */
```

#### C#

```cs
public class SeatManager {
    private PriorityQueue<int, int> q = new PriorityQueue<int, int>();

    public SeatManager(int n) {
        for (int i = 1; i <= n; ++i) {
            q.Enqueue(i, i);
        }
    }

    public int Reserve() {
        return q.Dequeue();
    }

    public void Unreserve(int seatNumber) {
        q.Enqueue(seatNumber, seatNumber);
    }
}

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.Reserve();
 * obj.Unreserve(seatNumber);
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
