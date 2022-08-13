# [933. Number of Recent Calls](https://leetcode.com/problems/number-of-recent-calls)

[中文文档](/solution/0900-0999/0933.Number%20of%20Recent%20Calls/README.md)

## Description

<p>You have a <code>RecentCounter</code> class which counts the number of recent requests within a certain time frame.</p>

<p>Implement the <code>RecentCounter</code> class:</p>

<ul>
	<li><code>RecentCounter()</code> Initializes the counter with zero recent requests.</li>
	<li><code>int ping(int t)</code> Adds a new request at time <code>t</code>, where <code>t</code> represents some time in milliseconds, and returns the number of requests that has happened in the past <code>3000</code> milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range <code>[t - 3000, t]</code>.</li>
</ul>

<p>It is <strong>guaranteed</strong> that every call to <code>ping</code> uses a strictly larger value of <code>t</code> than the previous call.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;RecentCounter&quot;, &quot;ping&quot;, &quot;ping&quot;, &quot;ping&quot;, &quot;ping&quot;]
[[], [1], [100], [3001], [3002]]
<strong>Output</strong>
[null, 1, 2, 3, 3]

<strong>Explanation</strong>
RecentCounter recentCounter = new RecentCounter();
recentCounter.ping(1);     // requests = [<u>1</u>], range is [-2999,1], return 1
recentCounter.ping(100);   // requests = [<u>1</u>, <u>100</u>], range is [-2900,100], return 2
recentCounter.ping(3001);  // requests = [<u>1</u>, <u>100</u>, <u>3001</u>], range is [1,3001], return 3
recentCounter.ping(3002);  // requests = [1, <u>100</u>, <u>3001</u>, <u>3002</u>], range is [2,3002], return 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= t &lt;= 10<sup>9</sup></code></li>
	<li>Each test case will call <code>ping</code> with <strong>strictly increasing</strong> values of <code>t</code>.</li>
	<li>At most <code>10<sup>4</sup></code> calls will be made to <code>ping</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class RecentCounter:
    def __init__(self):
        self.q = deque()

    def ping(self, t: int) -> int:
        self.q.append(t)
        while self.q[0] < t - 3000:
            self.q.popleft()
        return len(self.q)


# Your RecentCounter object will be instantiated and called as such:
# obj = RecentCounter()
# param_1 = obj.ping(t)
```

```python
class RecentCounter:

    def __init__(self):
        self.s = []

    def ping(self, t: int) -> int:
        self.s.append(t)
        return len(self.s) - bisect_left(self.s, t - 3000)


# Your RecentCounter object will be instantiated and called as such:
# obj = RecentCounter()
# param_1 = obj.ping(t)
```

### **Java**

```java
class RecentCounter {
    private Deque<Integer> q = new ArrayDeque<>();

    public RecentCounter() {

    }

    public int ping(int t) {
        q.offer(t);
        while (q.peekFirst() < t - 3000) {
            q.pollFirst();
        }
        return q.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
```

```java
class RecentCounter {
    private int[] s = new int[10010];
    private int idx;

    public RecentCounter() {

    }

    public int ping(int t) {
        s[idx++] = t;
        return idx - search(t - 3000);
    }

    private int search(int x) {
        int left = 0, right = idx;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (s[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
```

### **C++**

```cpp
class RecentCounter {
public:
    queue<int> q;

    RecentCounter() {
    }

    int ping(int t) {
        q.push(t);
        while (q.front() < t - 3000) q.pop();
        return q.size();
    }
};

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter* obj = new RecentCounter();
 * int param_1 = obj->ping(t);
 */
```

```cpp
class RecentCounter {
public:
    vector<int> s;

    RecentCounter() {

    }

    int ping(int t) {
        s.push_back(t);
        return s.size() - (lower_bound(s.begin(), s.end(), t - 3000) - s.begin());
    }
};

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter* obj = new RecentCounter();
 * int param_1 = obj->ping(t);
 */
```

### **Go**

```go
type RecentCounter struct {
	q []int
}

func Constructor() RecentCounter {
	return RecentCounter{[]int{}}
}

func (this *RecentCounter) Ping(t int) int {
	this.q = append(this.q, t)
	for this.q[0] < t-3000 {
		this.q = this.q[1:]
	}
	return len(this.q)
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Ping(t);
 */
```

```go
type RecentCounter struct {
	s []int
}

func Constructor() RecentCounter {
	return RecentCounter{[]int{}}
}

func (this *RecentCounter) Ping(t int) int {
	this.s = append(this.s, t)
	search := func(x int) int {
		left, right := 0, len(this.s)
		for left < right {
			mid := (left + right) >> 1
			if this.s[mid] >= x {
				right = mid
			} else {
				left = mid + 1
			}
		}
		return left
	}
	return len(this.s) - search(t-3000)
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Ping(t);
 */
```

### **JavaScript**

```js
var RecentCounter = function () {
    this.q = [];
};

/**
 * @param {number} t
 * @return {number}
 */
RecentCounter.prototype.ping = function (t) {
    this.q.push(t);
    while (this.q[0] < t - 3000) {
        this.q.shift();
    }
    return this.q.length;
};

/**
 * Your RecentCounter object will be instantiated and called as such:
 * var obj = new RecentCounter()
 * var param_1 = obj.ping(t)
 */
```

### **C#**

```cs
public class RecentCounter {
    private Queue<int> q = new Queue<int>();

    public RecentCounter() {

    }

    public int Ping(int t) {
        q.Enqueue(t);
        while (q.Peek() < t - 3000)
        {
            q.Dequeue();
        }
        return q.Count;
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.Ping(t);
 */
```

### **TypeScript**

```ts
class RecentCounter {
    private queue: number[];

    constructor() {
        this.queue = [];
    }

    ping(t: number): number {
        this.queue.push(t);
        while (this.queue[0] < t - 3000) {
            this.queue.shift();
        }
        return this.queue.length;
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * var obj = new RecentCounter()
 * var param_1 = obj.ping(t)
 */
```

### **Rust**

```rust
use std::collections::VecDeque;
struct RecentCounter {
    queue: VecDeque<i32>
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl RecentCounter {

    fn new() -> Self {
        Self {
            queue: VecDeque::new()
        }
    }

    fn ping(&mut self, t: i32) -> i32 {
        self.queue.push_back(t);
        while let Some(&v) = self.queue.front() {
            if v >= t - 3000  {
                break;
            }
            self.queue.pop_front();
        }
        self.queue.len() as i32
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * let obj = RecentCounter::new();
 * let ret_1: i32 = obj.ping(t);
 */
```

### **...**

```

```

<!-- tabs:end -->
