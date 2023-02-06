# [17.20. Continuous Median](https://leetcode.cn/problems/continuous-median-lcci)

[中文文档](/lcci/17.20.Continuous%20Median/README.md)

## Description

<p>Numbers are randomly generated and passed to a method. Write a program to find and maintain the median value as new values are generated.</p>

<p>Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.</p>

<p>For example,</p>

<p>[2,3,4], the median is&nbsp;3</p>

<p>[2,3], the median is (2 + 3) / 2 = 2.5</p>

<p>Design a data structure that supports the following two operations:</p>

<ul>
	<li>void addNum(int num) - Add a integer number from the data stream to the data structure.</li>
	<li>double findMedian() - Return the median of all elements so far.</li>
</ul>

<p><strong>Example: </strong></p>

<pre>

addNum(1)

addNum(2)

findMedian() -&gt; 1.5

addNum(3) 

findMedian() -&gt; 2

</pre>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

### **...**

```

```

<!-- tabs:end -->
