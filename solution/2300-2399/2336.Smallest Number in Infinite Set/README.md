---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2336.Smallest%20Number%20in%20Infinite%20Set/README.md
rating: 1375
source: 第 301 场周赛 Q2
tags:
    - 设计
    - 哈希表
    - 有序集合
    - 堆（优先队列）
---

<!-- problem:start -->

# [2336. 无限集中的最小数字](https://leetcode.cn/problems/smallest-number-in-infinite-set)

[English Version](/solution/2300-2399/2336.Smallest%20Number%20in%20Infinite%20Set/README_EN.md)

## 题目描述

<!-- description:start -->

<p>现有一个包含所有正整数的集合 <code>[1, 2, 3, 4, 5, ...]</code> 。</p>

<p>实现 <code>SmallestInfiniteSet</code> 类：</p>

<ul>
	<li><code>SmallestInfiniteSet()</code> 初始化 <strong>SmallestInfiniteSet</strong> 对象以包含 <strong>所有</strong> 正整数。</li>
	<li><code>int popSmallest()</code> <strong>移除</strong> 并返回该无限集中的最小整数。</li>
	<li><code>void addBack(int num)</code> 如果正整数 <code>num</code> <strong>不</strong> 存在于无限集中，则将一个 <code>num</code> <strong>添加</strong> 到该无限集中。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
[[], [2], [], [], [], [1], [], [], []]
<strong>输出</strong>
[null, null, 1, 2, 3, null, 1, 4, 5]

<strong>解释</strong>
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 已经在集合中，所以不做任何变更。
smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 是最小的整数，并将其从集合中移除。
smallestInfiniteSet.popSmallest(); // 返回 2 ，并将其从集合中移除。
smallestInfiniteSet.popSmallest(); // 返回 3 ，并将其从集合中移除。
smallestInfiniteSet.addBack(1);    // 将 1 添加到该集合中。
smallestInfiniteSet.popSmallest(); // 返回 1 ，因为 1 在上一步中被添加到集合中，
                                   // 且 1 是最小的整数，并将其从集合中移除。
smallestInfiniteSet.popSmallest(); // 返回 4 ，并将其从集合中移除。
smallestInfiniteSet.popSmallest(); // 返回 5 ，并将其从集合中移除。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 1000</code></li>
	<li>最多调用 <code>popSmallest</code> 和 <code>addBack</code> 方法 <strong>共计</strong> <code>1000</code> 次</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：有序集合 + 模拟

我们注意到，题目中集合的元素范围是 $[1, 1000]$，并且我们需要支持的操作有：

- `popSmallest`：弹出集合中的最小元素
- `addBack`：向集合中添加元素

因此，我们可以使用有序集合来模拟，不妨记有序集合为 $s$，集合中的元素为 $s_1, s_2, \cdots, s_n$，其中 $n$ 为有序集合中的元素个数。本题中 $n \le 1000$。

我们在初始化时，将 $[1, 1000]$ 中的所有元素加入有序集合中。时间复杂度 $O(n \times \log n)$。

在 `popSmallest` 操作中，我们只需要弹出有序集合中的第一个元素即可。单次操作时间复杂度 $O(\log n)$。

在 `addBack` 操作中，我们只需要将元素加入有序集合中即可。单次操作时间复杂度 $O(\log n)$。

空间复杂度 $O(n)$。

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
