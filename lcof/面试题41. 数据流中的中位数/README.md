---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9841.%20%E6%95%B0%E6%8D%AE%E6%B5%81%E4%B8%AD%E7%9A%84%E4%B8%AD%E4%BD%8D%E6%95%B0/README.md
---

<!-- problem:start -->

# [面试题 41. 数据流中的中位数](https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/)

## 题目描述

<!-- description:start -->

<p>如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。</p>

<p>例如，</p>

<p>[2,3,4]&nbsp;的中位数是 3</p>

<p>[2,3] 的中位数是 (2 + 3) / 2 = 2.5</p>

<p>设计一个支持以下两种操作的数据结构：</p>

<ul>
	<li>void addNum(int num) - 从数据流中添加一个整数到数据结构中。</li>
	<li>double findMedian() - 返回目前所有元素的中位数。</li>
</ul>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：
</strong>[&quot;MedianFinder&quot;,&quot;addNum&quot;,&quot;addNum&quot;,&quot;findMedian&quot;,&quot;addNum&quot;,&quot;findMedian&quot;]
[[],[1],[2],[],[3],[]]
<strong>输出：</strong>[null,null,null,1.50000,null,2.00000]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：
</strong>[&quot;MedianFinder&quot;,&quot;addNum&quot;,&quot;findMedian&quot;,&quot;addNum&quot;,&quot;findMedian&quot;]
[[],[2],[],[3],[]]
<strong>输出：</strong>[null,null,2.00000,null,2.50000]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<ul>
	<li>最多会对&nbsp;<code>addNum、findMedian</code> 进行&nbsp;<code>50000</code>&nbsp;次调用。</li>
</ul>

<p>注意：本题与主站 295 题相同：<a href="https://leetcode.cn/problems/find-median-from-data-stream/">https://leetcode.cn/problems/find-median-from-data-stream/</a></p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：大小根堆（优先队列）

我们可以使用两个堆来维护所有的元素，一个小根堆 $\text{minQ}$ 和一个大根堆 $\text{maxQ}$，其中小根堆 $\text{minQ}$ 存储较大的一半，大根堆 $\text{maxQ}$ 存储较小的一半。

调用 `addNum` 方法时，我们首先将元素加入到大根堆 $\text{maxQ}$，然后将 $\text{maxQ}$ 的堆顶元素弹出并加入到小根堆 $\text{minQ}$。如果此时 $\text{minQ}$ 的大小与 $\text{maxQ}$ 的大小差值大于 $1$，我们就将 $\text{minQ}$ 的堆顶元素弹出并加入到 $\text{maxQ}$。时间复杂度为 $O(\log n)$。

调用 `findMedian` 方法时，如果 $\text{minQ}$ 的大小等于 $\text{maxQ}$ 的大小，说明元素的总数为偶数，我们就可以返回 $\text{minQ}$ 的堆顶元素与 $\text{maxQ}$ 的堆顶元素的平均值；否则，我们返回 $\text{minQ}$ 的堆顶元素。时间复杂度为 $O(1)$。

空间复杂度为 $O(n)$。其中 $n$ 为元素的个数。

<!-- tabs:start -->

#### Python3

```python
class MedianFinder:

    def __init__(self):
        self.minq = []
        self.maxq = []

    def addNum(self, num: int) -> None:
        heappush(self.minq, -heappushpop(self.maxq, -num))
        if len(self.minq) - len(self.maxq) > 1:
            heappush(self.maxq, -heappop(self.minq))

    def findMedian(self) -> float:
        if len(self.minq) == len(self.maxq):
            return (self.minq[0] - self.maxq[0]) / 2
        return self.minq[0]


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
```

#### Java

```java
class MedianFinder {
    private PriorityQueue<Integer> minQ = new PriorityQueue<>();
    private PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());

    public MedianFinder() {
    }

    public void addNum(int num) {
        maxQ.offer(num);
        minQ.offer(maxQ.poll());
        if (minQ.size() - maxQ.size() > 1) {
            maxQ.offer(minQ.poll());
        }
    }

    public double findMedian() {
        return minQ.size() == maxQ.size() ? (minQ.peek() + maxQ.peek()) / 2.0 : minQ.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```

#### C++

```cpp
class MedianFinder {
public:
    MedianFinder() {
    }

    void addNum(int num) {
        maxQ.push(num);
        minQ.push(maxQ.top());
        maxQ.pop();

        if (minQ.size() > maxQ.size() + 1) {
            maxQ.push(minQ.top());
            minQ.pop();
        }
    }

    double findMedian() {
        return minQ.size() == maxQ.size() ? (minQ.top() + maxQ.top()) / 2.0 : minQ.top();
    }

private:
    priority_queue<int> maxQ;
    priority_queue<int, vector<int>, greater<int>> minQ;
};

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder* obj = new MedianFinder();
 * obj->addNum(num);
 * double param_2 = obj->findMedian();
 */
```

#### Go

```go
type MedianFinder struct {
	minq hp
	maxq hp
}

func Constructor() MedianFinder {
	return MedianFinder{hp{}, hp{}}
}

func (this *MedianFinder) AddNum(num int) {
	minq, maxq := &this.minq, &this.maxq
	heap.Push(maxq, -num)
	heap.Push(minq, -heap.Pop(maxq).(int))
	if minq.Len()-maxq.Len() > 1 {
		heap.Push(maxq, -heap.Pop(minq).(int))
	}
}

func (this *MedianFinder) FindMedian() float64 {
	minq, maxq := this.minq, this.maxq
	if minq.Len() == maxq.Len() {
		return float64(minq.IntSlice[0]-maxq.IntSlice[0]) / 2
	}
	return float64(minq.IntSlice[0])
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
 * Your MedianFinder object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddNum(num);
 * param_2 := obj.FindMedian();
 */
```

#### TypeScript

```ts
class MedianFinder {
    #minQ = new MinPriorityQueue();
    #maxQ = new MaxPriorityQueue();

    addNum(num: number): void {
        const [minQ, maxQ] = [this.#minQ, this.#maxQ];
        maxQ.enqueue(num);
        minQ.enqueue(maxQ.dequeue().element);
        if (minQ.size() - maxQ.size() > 1) {
            maxQ.enqueue(minQ.dequeue().element);
        }
    }

    findMedian(): number {
        const [minQ, maxQ] = [this.#minQ, this.#maxQ];
        if (minQ.size() === maxQ.size()) {
            return (minQ.front().element + maxQ.front().element) / 2;
        }
        return minQ.front().element;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = new MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */
```

#### Rust

```rust
use std::cmp::Reverse;
use std::collections::BinaryHeap;

struct MedianFinder {
    minQ: BinaryHeap<Reverse<i32>>,
    maxQ: BinaryHeap<i32>,
}

impl MedianFinder {
    fn new() -> Self {
        MedianFinder {
            minQ: BinaryHeap::new(),
            maxQ: BinaryHeap::new(),
        }
    }

    fn add_num(&mut self, num: i32) {
        self.maxQ.push(num);
        self.minQ.push(Reverse(self.maxQ.pop().unwrap()));

        if self.minQ.len() > self.maxQ.len() + 1 {
            self.maxQ.push(self.minQ.pop().unwrap().0);
        }
    }

    fn find_median(&self) -> f64 {
        if self.minQ.len() == self.maxQ.len() {
            let min_top = self.minQ.peek().unwrap().0;
            let max_top = *self.maxQ.peek().unwrap();
            (min_top + max_top) as f64 / 2.0
        } else {
            self.minQ.peek().unwrap().0 as f64
        }
    }
}
```

#### JavaScript

```js
var MedianFinder = function () {
    this.minQ = new MinPriorityQueue();
    this.maxQ = new MaxPriorityQueue();
};

/**
 * @param {number} num
 * @return {void}
 */
MedianFinder.prototype.addNum = function (num) {
    this.maxQ.enqueue(num);
    this.minQ.enqueue(this.maxQ.dequeue().element);
    if (this.minQ.size() - this.maxQ.size() > 1) {
        this.maxQ.enqueue(this.minQ.dequeue().element);
    }
};

/**
 * @return {number}
 */
MedianFinder.prototype.findMedian = function () {
    if (this.minQ.size() === this.maxQ.size()) {
        return (this.minQ.front().element + this.maxQ.front().element) / 2;
    }
    return this.minQ.front().element;
};

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = new MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */
```

#### C#

```cs
public class MedianFinder {
    private PriorityQueue<int, int> minQ = new PriorityQueue<int, int>();
    private PriorityQueue<int, int> maxQ = new PriorityQueue<int, int>(Comparer<int>.Create((a, b) => b.CompareTo(a)));

    public MedianFinder() {

    }

    public void AddNum(int num) {
        maxQ.Enqueue(num, num);
        minQ.Enqueue(maxQ.Peek(), maxQ.Dequeue());
        if (minQ.Count > maxQ.Count + 1) {
            maxQ.Enqueue(minQ.Peek(), minQ.Dequeue());
        }
    }

    public double FindMedian() {
        return minQ.Count == maxQ.Count ? (minQ.Peek() + maxQ.Peek()) / 2.0 : minQ.Peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.AddNum(num);
 * double param_2 = obj.FindMedian();
 */
```

#### Swift

```swift
class MedianFinder {
    private var minQ = Heap<Int>(sort: <)
    private var maxQ = Heap<Int>(sort: >)

    init() {
    }

    func addNum(_ num: Int) {
        maxQ.insert(num)
        minQ.insert(maxQ.remove()!)
        if maxQ.count < minQ.count {
            maxQ.insert(minQ.remove()!)
        }
    }

    func findMedian() -> Double {
        if maxQ.count > minQ.count {
            return Double(maxQ.peek()!)
        }
        return (Double(maxQ.peek()!) + Double(minQ.peek()!)) / 2.0
    }
}

struct Heap<T> {
    var elements: [T]
    let sort: (T, T) -> Bool

    init(sort: @escaping (T, T) -> Bool, elements: [T] = []) {
        self.sort = sort
        self.elements = elements
        if !elements.isEmpty {
            for i in stride(from: elements.count / 2 - 1, through: 0, by: -1) {
                siftDown(from: i)
            }
        }
    }

    var isEmpty: Bool {
        return elements.isEmpty
    }

    var count: Int {
        return elements.count
    }

    func peek() -> T? {
        return elements.first
    }

    mutating func insert(_ value: T) {
        elements.append(value)
        siftUp(from: elements.count - 1)
    }

    mutating func remove() -> T? {
        guard !elements.isEmpty else { return nil }
        elements.swapAt(0, elements.count - 1)
        let removedValue = elements.removeLast()
        siftDown(from: 0)
        return removedValue
    }

    private mutating func siftUp(from index: Int) {
        var child = index
        var parent = parentIndex(ofChildAt: child)
        while child > 0 && sort(elements[child], elements[parent]) {
            elements.swapAt(child, parent)
            child = parent
            parent = parentIndex(ofChildAt: child)
        }
    }

    private mutating func siftDown(from index: Int) {
        var parent = index
        while true {
            let left = leftChildIndex(ofParentAt: parent)
            let right = rightChildIndex(ofParentAt: parent)
            var candidate = parent
            if left < count && sort(elements[left], elements[candidate]) {
                candidate = left
            }
            if right < count && sort(elements[right], elements[candidate]) {
                candidate = right
            }
            if candidate == parent {
                return
            }
            elements.swapAt(parent, candidate)
            parent = candidate
        }
    }

    private func parentIndex(ofChildAt index: Int) -> Int {
        return (index - 1) / 2
    }

    private func leftChildIndex(ofParentAt index: Int) -> Int {
        return 2 * index + 1
    }

    private func rightChildIndex(ofParentAt index: Int) -> Int {
        return 2 * index + 2
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * let obj = MedianFinder()
 * obj.addNum(num)
 * let ret_2: Double = obj.findMedian()
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
