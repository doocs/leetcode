---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2336.Smallest%20Number%20in%20Infinite%20Set/README_EN.md
rating: 1375
source: Weekly Contest 301 Q2
tags:
    - Design
    - Hash Table
    - Ordered Set
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2336. Smallest Number in Infinite Set](https://leetcode.com/problems/smallest-number-in-infinite-set)

[中文文档](/solution/2300-2399/2336.Smallest%20Number%20in%20Infinite%20Set/README.md)

## Description

<!-- description:start -->

<p>You have a set which contains all positive integers <code>[1, 2, 3, 4, 5, ...]</code>.</p>

<p>Implement the <code>SmallestInfiniteSet</code> class:</p>

<ul>
	<li><code>SmallestInfiniteSet()</code> Initializes the <strong>SmallestInfiniteSet</strong> object to contain <strong>all</strong> positive integers.</li>
	<li><code>int popSmallest()</code> <strong>Removes</strong> and returns the smallest integer contained in the infinite set.</li>
	<li><code>void addBack(int num)</code> <strong>Adds</strong> a positive integer <code>num</code> back into the infinite set, if it is <strong>not</strong> already in the infinite set.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;SmallestInfiniteSet&quot;, &quot;addBack&quot;, &quot;popSmallest&quot;, &quot;popSmallest&quot;, &quot;popSmallest&quot;, &quot;addBack&quot;, &quot;popSmallest&quot;, &quot;popSmallest&quot;, &quot;popSmallest&quot;]
[[], [2], [], [], [], [1], [], [], []]
<strong>Output</strong>
[null, null, 1, 2, 3, null, 1, 4, 5]

<strong>Explanation</strong>
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
                                   // is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 1000</code></li>
	<li>At most <code>1000</code> calls will be made <strong>in total</strong> to <code>popSmallest</code> and <code>addBack</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Ordered Set + Simulation

We note that the range of elements in the set given by the problem is $[1, 1000]$, and the operations we need to support are:

- `popSmallest`: Pop the smallest element from the set
- `addBack`: Add an element back to the set

Therefore, we can use an ordered set to simulate this. Let's denote the ordered set as $s$, and the elements in the set as $s_1, s_2, \cdots, s_n$, where $n$ is the number of elements in the ordered set. In this problem, $n \le 1000$.

During initialization, we add all elements in $[1, 1000]$ to the ordered set. The time complexity is $O(n \times \log n)$.

In the `popSmallest` operation, we just need to pop the first element from the ordered set. The time complexity for a single operation is $O(\log n)$.

In the `addBack` operation, we just need to add the element back to the ordered set. The time complexity for a single operation is $O(\log n)$.

The space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class SmallestInfiniteSet:
    def __init__(self):
        self.s = SortedSet(range(1, 1001))

    def popSmallest(self) -> int:
        x = self.s[0]
        self.s.remove(x)
        return x

    def addBack(self, num: int) -> None:
        self.s.add(num)


# Your SmallestInfiniteSet object will be instantiated and called as such:
# obj = SmallestInfiniteSet()
# param_1 = obj.popSmallest()
# obj.addBack(num)
```

#### Java

```java
class SmallestInfiniteSet {
    private TreeSet<Integer> s = new TreeSet<>();

    public SmallestInfiniteSet() {
        for (int i = 1; i <= 1000; ++i) {
            s.add(i);
        }
    }

    public int popSmallest() {
        return s.pollFirst();
    }

    public void addBack(int num) {
        s.add(num);
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
```

#### C++

```cpp
class SmallestInfiniteSet {
public:
    SmallestInfiniteSet() {
        for (int i = 1; i <= 1000; ++i) {
            s.insert(i);
        }
    }

    int popSmallest() {
        int x = *s.begin();
        s.erase(s.begin());
        return x;
    }

    void addBack(int num) {
        s.insert(num);
    }

private:
    set<int> s;
};

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet* obj = new SmallestInfiniteSet();
 * int param_1 = obj->popSmallest();
 * obj->addBack(num);
 */
```

#### Go

```go
type SmallestInfiniteSet struct {
	s *treemap.Map
}

func Constructor() SmallestInfiniteSet {
	s := treemap.NewWithIntComparator()
	for i := 1; i <= 1000; i++ {
		s.Put(i, nil)
	}
	return SmallestInfiniteSet{s}
}

func (this *SmallestInfiniteSet) PopSmallest() int {
	x, _ := this.s.Min()
	this.s.Remove(x.(int))
	return x.(int)
}

func (this *SmallestInfiniteSet) AddBack(num int) {
	this.s.Put(num, nil)
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.PopSmallest();
 * obj.AddBack(num);
 */
```

#### TypeScript

```ts
class SmallestInfiniteSet {
    private pq = new MinPriorityQueue<number>();
    private s = new Set<number>();

    constructor() {
        for (let i = 1; i <= 1000; i++) {
            this.pq.enqueue(i);
            this.s.add(i);
        }
    }

    popSmallest(): number {
        const x = this.pq.dequeue();
        this.s.delete(x);
        return x;
    }

    addBack(num: number): void {
        if (!this.s.has(num)) {
            this.pq.enqueue(num);
            this.s.add(num);
        }
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * var obj = new SmallestInfiniteSet()
 * var param_1 = obj.popSmallest()
 * obj.addBack(num)
 */
```

#### Rust

```rust
use std::collections::BTreeSet;

struct SmallestInfiniteSet {
    s: BTreeSet<i32>,
}

impl SmallestInfiniteSet {
    fn new() -> Self {
        let mut set = BTreeSet::new();
        for i in 1..=1000 {
            set.insert(i);
        }
        SmallestInfiniteSet { s: set }
    }

    fn pop_smallest(&mut self) -> i32 {
        let x = *self.s.iter().next().unwrap();
        self.s.remove(&x);
        x
    }

    fn add_back(&mut self, num: i32) {
        self.s.insert(num);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
