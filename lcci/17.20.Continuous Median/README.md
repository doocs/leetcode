---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.20.Continuous%20Median/README.md
---

<!-- problem:start -->

# [面试题 17.20. 连续中值](https://leetcode.cn/problems/continuous-median-lcci)

[English Version](/lcci/17.20.Continuous%20Median/README_EN.md)

## 题目描述

<!-- description:start -->

<p>随机产生数字并传递给一个方法。你能否完成这个方法，在每次产生新值时，寻找当前所有值的中间值（中位数）并保存。</p>

<p>中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。</p>

<p>例如，</p>

<p>[2,3,4]&nbsp;的中位数是 3</p>

<p>[2,3] 的中位数是 (2 + 3) / 2 = 2.5</p>

<p>设计一个支持以下两种操作的数据结构：</p>

<ul>
	<li>void addNum(int num) - 从数据流中添加一个整数到数据结构中。</li>
	<li>double findMedian() - 返回目前所有元素的中位数。</li>
</ul>

<p><strong>示例：</strong></p>

<pre>addNum(1)
addNum(2)
findMedian() -&gt; 1.5
addNum(3) 
findMedian() -&gt; 2
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（双堆）

创建大根堆、小根堆，其中：大根堆存放较小的一半元素，小根堆存放较大的一半元素。

添加元素时，先放入小根堆，然后将小根堆对顶元素弹出并放入大根堆（使得大根堆个数多 $1$）；若大小根堆元素个数差超过 $1$，则将大根堆元素弹出放入小根堆。

取中位数时，若大根堆元素较多，取大根堆堆顶，否则取两堆顶元素和的平均值。

**时间复杂度分析：**

每次添加元素的时间复杂度为 $O(\log n)$，取中位数的时间复杂度为 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class MedianFinder:
    def __init__(self):
        """
        initialize your data structure here.
        """
        self.h1 = []
        self.h2 = []

    def addNum(self, num: int) -> None:
        heappush(self.h1, num)
        heappush(self.h2, -heappop(self.h1))
        if len(self.h2) - len(self.h1) > 1:
            heappush(self.h1, -heappop(self.h2))

    def findMedian(self) -> float:
        if len(self.h2) > len(self.h1):
            return -self.h2[0]
        return (self.h1[0] - self.h2[0]) / 2


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
```

#### Java

```java
class MedianFinder {
    private PriorityQueue<Integer> q1 = new PriorityQueue<>();
    private PriorityQueue<Integer> q2 = new PriorityQueue<>(Collections.reverseOrder());

    /** initialize your data structure here. */
    public MedianFinder() {
    }

    public void addNum(int num) {
        q1.offer(num);
        q2.offer(q1.poll());
        if (q2.size() - q1.size() > 1) {
            q1.offer(q2.poll());
        }
    }

    public double findMedian() {
        if (q2.size() > q1.size()) {
            return q2.peek();
        }
        return (q1.peek() + q2.peek()) * 1.0 / 2;
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
    /** initialize your data structure here. */
    MedianFinder() {
    }

    void addNum(int num) {
        q1.push(num);
        q2.push(q1.top());
        q1.pop();
        if (q2.size() - q1.size() > 1) {
            q1.push(q2.top());
            q2.pop();
        }
    }

    double findMedian() {
        if (q2.size() > q1.size()) {
            return q2.top();
        }
        return (double) (q1.top() + q2.top()) / 2;
    }

private:
    priority_queue<int, vector<int>, greater<int>> q1;
    priority_queue<int> q2;
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
	q1 hp
	q2 hp
}

/** initialize your data structure here. */
func Constructor() MedianFinder {
	return MedianFinder{hp{}, hp{}}
}

func (this *MedianFinder) AddNum(num int) {
	heap.Push(&this.q1, num)
	heap.Push(&this.q2, -heap.Pop(&this.q1).(int))
	if this.q2.Len()-this.q1.Len() > 1 {
		heap.Push(&this.q1, -heap.Pop(&this.q2).(int))
	}
}

func (this *MedianFinder) FindMedian() float64 {
	if this.q2.Len() > this.q1.Len() {
		return -float64(this.q2.IntSlice[0])
	}
	return float64(this.q1.IntSlice[0]-this.q2.IntSlice[0]) / 2.0
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddNum(num);
 * param_2 := obj.FindMedian();
 */

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

#### Swift

```swift
class MedianFinder {
    private var minHeap = Heap<Int>(sort: <)
    private var maxHeap = Heap<Int>(sort: >)

    init() {
    }

    func addNum(_ num: Int) {
        maxHeap.insert(num)
        minHeap.insert(maxHeap.remove()!)

        if maxHeap.count < minHeap.count {
            maxHeap.insert(minHeap.remove()!)
        }
    }

    func findMedian() -> Double {
        if maxHeap.count > minHeap.count {
            return Double(maxHeap.peek()!)
        }
        return (Double(maxHeap.peek()!) + Double(minHeap.peek()!)) / 2.0
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
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
