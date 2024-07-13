---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0362.Design%20Hit%20Counter/README_EN.md
tags:
    - Design
    - Queue
    - Array
    - Binary Search
    - Data Stream
---

<!-- problem:start -->

# [362. Design Hit Counter 🔒](https://leetcode.com/problems/design-hit-counter)

[中文文档](/solution/0300-0399/0362.Design%20Hit%20Counter/README.md)

## Description

<!-- description:start -->

<p>Design a hit counter which counts the number of hits received in the past <code>5</code> minutes (i.e., the past <code>300</code> seconds).</p>

<p>Your system should accept a <code>timestamp</code> parameter (<strong>in seconds</strong> granularity), and you may assume that calls are being made to the system in chronological order (i.e., <code>timestamp</code> is monotonically increasing). Several hits may arrive roughly at the same time.</p>

<p>Implement the <code>HitCounter</code> class:</p>

<ul>
	<li><code>HitCounter()</code> Initializes the object of the hit counter system.</li>
	<li><code>void hit(int timestamp)</code> Records a hit that happened at <code>timestamp</code> (<strong>in seconds</strong>). Several hits may happen at the same <code>timestamp</code>.</li>
	<li><code>int getHits(int timestamp)</code> Returns the number of hits in the past 5 minutes from <code>timestamp</code> (i.e., the past <code>300</code> seconds).</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;HitCounter&quot;, &quot;hit&quot;, &quot;hit&quot;, &quot;hit&quot;, &quot;getHits&quot;, &quot;hit&quot;, &quot;getHits&quot;, &quot;getHits&quot;]
[[], [1], [2], [3], [4], [300], [300], [301]]
<strong>Output</strong>
[null, null, null, null, 3, null, 4, 3]

<strong>Explanation</strong>
HitCounter hitCounter = new HitCounter();
hitCounter.hit(1);       // hit at timestamp 1.
hitCounter.hit(2);       // hit at timestamp 2.
hitCounter.hit(3);       // hit at timestamp 3.
hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
hitCounter.hit(300);     // hit at timestamp 300.
hitCounter.getHits(300); // get hits at timestamp 300, return 4.
hitCounter.getHits(301); // get hits at timestamp 301, return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= timestamp &lt;= 2 * 10<sup>9</sup></code></li>
	<li>All the calls are being made to the system in chronological order (i.e., <code>timestamp</code> is monotonically increasing).</li>
	<li>At most <code>300</code> calls will be made to <code>hit</code> and <code>getHits</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> What if the number of hits per second could be huge? Does your design scale?</p>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Binary Search

Since `timestamp` is monotonically increasing, we can use an array `ts` to store all `timestamp`s. Then in the `getHits` method, we use binary search to find the first position that is greater than or equal to `timestamp - 300 + 1`, and then return the length of `ts` minus this position.

In terms of time complexity, the time complexity of the `hit` method is $O(1)$, and the time complexity of the `getHits` method is $O(\log n)$. Where $n$ is the length of `ts`.

<!-- tabs:start -->

#### Python3

```python
class HitCounter:

    def __init__(self):
        self.ts = []

    def hit(self, timestamp: int) -> None:
        self.ts.append(timestamp)

    def getHits(self, timestamp: int) -> int:
        return len(self.ts) - bisect_left(self.ts, timestamp - 300 + 1)


# Your HitCounter object will be instantiated and called as such:
# obj = HitCounter()
# obj.hit(timestamp)
# param_2 = obj.getHits(timestamp)
```

#### Java

```java
class HitCounter {
    private List<Integer> ts = new ArrayList<>();

    public HitCounter() {
    }

    public void hit(int timestamp) {
        ts.add(timestamp);
    }

    public int getHits(int timestamp) {
        int l = search(timestamp - 300 + 1);
        return ts.size() - l;
    }

    private int search(int x) {
        int l = 0, r = ts.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (ts.get(mid) >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
```

#### C++

```cpp
class HitCounter {
public:
    HitCounter() {

    }

    void hit(int timestamp) {
        ts.push_back(timestamp);
    }

    int getHits(int timestamp) {
        return ts.end() - lower_bound(ts.begin(), ts.end(), timestamp - 300 + 1);
    }

private:
    vector<int> ts;
};

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter* obj = new HitCounter();
 * obj->hit(timestamp);
 * int param_2 = obj->getHits(timestamp);
 */
```

#### Go

```go
type HitCounter struct {
	ts []int
}

func Constructor() HitCounter {
	return HitCounter{}
}

func (this *HitCounter) Hit(timestamp int) {
	this.ts = append(this.ts, timestamp)
}

func (this *HitCounter) GetHits(timestamp int) int {
	return len(this.ts) - sort.SearchInts(this.ts, timestamp-300+1)
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Hit(timestamp);
 * param_2 := obj.GetHits(timestamp);
 */
```

#### TypeScript

```ts
class HitCounter {
    private ts: number[] = [];

    constructor() {}

    hit(timestamp: number): void {
        this.ts.push(timestamp);
    }

    getHits(timestamp: number): number {
        const search = (x: number) => {
            let [l, r] = [0, this.ts.length];
            while (l < r) {
                const mid = (l + r) >> 1;
                if (this.ts[mid] >= x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l;
        };
        return this.ts.length - search(timestamp - 300 + 1);
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * var obj = new HitCounter()
 * obj.hit(timestamp)
 * var param_2 = obj.getHits(timestamp)
 */
```

#### Rust

```rust
struct HitCounter {
    ts: Vec<i32>,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl HitCounter {
    fn new() -> Self {
        HitCounter { ts: Vec::new() }
    }

    fn hit(&mut self, timestamp: i32) {
        self.ts.push(timestamp);
    }

    fn get_hits(&self, timestamp: i32) -> i32 {
        let l = self.search(timestamp - 300 + 1);
        (self.ts.len() - l) as i32
    }

    fn search(&self, x: i32) -> usize {
        let (mut l, mut r) = (0, self.ts.len());
        while l < r {
            let mid = (l + r) / 2;
            if self.ts[mid] >= x {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
