# [933. 最近的请求次数](https://leetcode.cn/problems/number-of-recent-calls)

[English Version](/solution/0900-0999/0933.Number%20of%20Recent%20Calls/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>写一个&nbsp;<code>RecentCounter</code>&nbsp;类来计算特定时间范围内最近的请求。</p>

<p>请你实现 <code>RecentCounter</code> 类：</p>

<ul>
	<li><code>RecentCounter()</code> 初始化计数器，请求数为 0 。</li>
	<li><code>int ping(int t)</code> 在时间 <code>t</code> 添加一个新请求，其中 <code>t</code> 表示以毫秒为单位的某个时间，并返回过去 <code>3000</code> 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 <code>[t-3000, t]</code> 内发生的请求数。</li>
</ul>

<p><strong>保证</strong> 每次对 <code>ping</code> 的调用都使用比之前更大的 <code>t</code> 值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
["RecentCounter", "ping", "ping", "ping", "ping"]
[[], [1], [100], [3001], [3002]]
<strong>输出：</strong>
[null, 1, 2, 3, 3]

<strong>解释：</strong>
RecentCounter recentCounter = new RecentCounter();
recentCounter.ping(1);     // requests = [<strong>1</strong>]，范围是 [-2999,1]，返回 1
recentCounter.ping(100);   // requests = [<strong>1</strong>, <strong>100</strong>]，范围是 [-2900,100]，返回 2
recentCounter.ping(3001);  // requests = [<strong>1</strong>, <strong>100</strong>, <strong>3001</strong>]，范围是 [1,3001]，返回 3
recentCounter.ping(3002);  // requests = [1, <strong>100</strong>, <strong>3001</strong>, <strong>3002</strong>]，范围是 [2,3002]，返回 3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= t &lt;= 10<sup>9</sup></code></li>
	<li>保证每次对 <code>ping</code> 调用所使用的 <code>t</code> 值都 <strong>严格递增</strong></li>
	<li>至多调用 <code>ping</code> 方法 <code>10<sup>4</sup></code> 次</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：队列**

由题得知，`t` 是**严格递增**的，当一个元素不满足 `[t - 3000, t]` 条件时，在后续的请求当中，它也不可能满足。

对此，需要将其从记录容器中移除，减少无意义的比较。

可以使用队列。每次将 `t` 进入队尾，同时从队头开始，依次移除小于 `t - 3000` 的元素。然后返回队列的大小（`q.size()`）即可。

**方法二：二分查找**

`t` 严格单调递增，非常适合用二分查找来定位 `[t-3000, t]` 的左右边界。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
