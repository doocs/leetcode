---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1845.Seat%20Reservation%20Manager/README_EN.md
rating: 1428
source: Biweekly Contest 51 Q2
tags:
    - Design
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1845. Seat Reservation Manager](https://leetcode.com/problems/seat-reservation-manager)

[中文文档](/solution/1800-1899/1845.Seat%20Reservation%20Manager/README.md)

## Description

<!-- description:start -->

<p>Design a system that manages the reservation state of <code>n</code> seats that are numbered from <code>1</code> to <code>n</code>.</p>

<p>Implement the <code>SeatManager</code> class:</p>

<ul>
	<li><code>SeatManager(int n)</code> Initializes a <code>SeatManager</code> object that will manage <code>n</code> seats numbered from <code>1</code> to <code>n</code>. All seats are initially available.</li>
	<li><code>int reserve()</code> Fetches the <strong>smallest-numbered</strong> unreserved seat, reserves it, and returns its number.</li>
	<li><code>void unreserve(int seatNumber)</code> Unreserves the seat with the given <code>seatNumber</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;SeatManager&quot;, &quot;reserve&quot;, &quot;reserve&quot;, &quot;unreserve&quot;, &quot;reserve&quot;, &quot;reserve&quot;, &quot;reserve&quot;, &quot;reserve&quot;, &quot;unreserve&quot;]
[[5], [], [], [2], [], [], [], [], [5]]
<strong>Output</strong>
[null, 1, 2, null, 2, 3, 4, 5, null]

<strong>Explanation</strong>
SeatManager seatManager = new SeatManager(5); // Initializes a SeatManager with 5 seats.
seatManager.reserve();    // All seats are available, so return the lowest numbered seat, which is 1.
seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
seatManager.unreserve(2); // Unreserve seat 2, so now the available seats are [2,3,4,5].
seatManager.reserve();    // The available seats are [2,3,4,5], so return the lowest of them, which is 2.
seatManager.reserve();    // The available seats are [3,4,5], so return the lowest of them, which is 3.
seatManager.reserve();    // The available seats are [4,5], so return the lowest of them, which is 4.
seatManager.reserve();    // The only available seat is seat 5, so return 5.
seatManager.unreserve(5); // Unreserve seat 5, so now the available seats are [5].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= seatNumber &lt;= n</code></li>
	<li>For each call to <code>reserve</code>, it is guaranteed that there will be at least one unreserved seat.</li>
	<li>For each call to <code>unreserve</code>, it is guaranteed that <code>seatNumber</code> will be reserved.</li>
	<li>At most <code>10<sup>5</sup></code> calls <strong>in total</strong> will be made to <code>reserve</code> and <code>unreserve</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Priority Queue (Min-Heap)

We define a priority queue (min-heap) $\textit{q}$ to store all the available seat numbers. Initially, we add all seat numbers from $1$ to $n$ into $\textit{q}$.

When calling the `reserve` method, we pop the top element from $\textit{q}$, which is the smallest available seat number.

When calling the `unreserve` method, we add the seat number back into $\textit{q}$.

In terms of time complexity, the initialization time complexity is $O(n)$ or $O(n \times \log n)$, and the time complexity of the `reserve` and `unreserve` methods is both $O(\log n)$. The space complexity is $O(n)$.

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
    private q = new MinPriorityQueue<number>();
    constructor(n: number) {
        for (let i = 1; i <= n; i++) {
            this.q.enqueue(i);
        }
    }

    reserve(): number {
        return this.q.dequeue();
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
