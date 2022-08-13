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
        self.min_heap = []
        self.max_heap = []

    def addNum(self, num: int) -> None:
        heappush(self.min_heap, num)
        heappush(self.max_heap, -heappop(self.min_heap))
        if len(self.max_heap) - len(self.min_heap) > 1:
            heappush(self.min_heap, -heappop(self.max_heap))

    def findMedian(self) -> float:
        if len(self.max_heap) > len(self.min_heap):
            return -self.max_heap[0]
        return (self.min_heap[0] - self.max_heap[0]) / 2


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
```

### **Java**

```java
class MedianFinder {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());
        if (maxHeap.size() - minHeap.size() > 1) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        return (minHeap.peek() + maxHeap.peek()) * 1.0 / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```

### **...**

```

```

<!-- tabs:end -->
