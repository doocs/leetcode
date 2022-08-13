# [1845. Seat Reservation Manager](https://leetcode.com/problems/seat-reservation-manager)

[中文文档](/solution/1800-1899/1845.Seat%20Reservation%20Manager/README.md)

## Description

<p>Design a system that manages the reservation state of <code>n</code> seats that are numbered from <code>1</code> to <code>n</code>.</p>

<p>Implement the <code>SeatManager</code> class:</p>

<ul>
	<li><code>SeatManager(int n)</code> Initializes a <code>SeatManager</code> object that will manage <code>n</code> seats numbered from <code>1</code> to <code>n</code>. All seats are initially available.</li>
	<li><code>int reserve()</code> Fetches the <strong>smallest-numbered</strong> unreserved seat, reserves it, and returns its number.</li>
	<li><code>void unreserve(int seatNumber)</code> Unreserves the seat with the given <code>seatNumber</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class SeatManager:
    def __init__(self, n: int):
        self.q = [i for i in range(1, n + 1)]

    def reserve(self) -> int:
        return heappop(self.q)

    def unreserve(self, seatNumber: int) -> None:
        heappush(self.q, seatNumber)


# Your SeatManager object will be instantiated and called as such:
# obj = SeatManager(n)
# param_1 = obj.reserve()
# obj.unreserve(seatNumber)
```

### **Java**

```java
class SeatManager {
    private PriorityQueue<Integer> q;

    public SeatManager(int n) {
        q = new PriorityQueue<>(n);
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

### **...**

```

```

<!-- tabs:end -->
