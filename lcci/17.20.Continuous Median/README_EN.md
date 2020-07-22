# [17.20. Continuous Median](https://leetcode-cn.com/problems/continuous-median-lcci)

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
        self.max_heap = []
        self.min_heap = []


    def addNum(self, num: int) -> None:
        if len(self.max_heap) == len(self.min_heap):
            heapq.heappush(self.min_heap, -heapq.heappushpop(self.max_heap, -num))
        else:
            heapq.heappush(self.max_heap, -heapq.heappushpop(self.min_heap, num))

    def findMedian(self) -> float:
        return (-self.max_heap[0] + self.min_heap[0]) / 2 if len(self.max_heap) == len(self.min_heap) else self.min_heap[0]


# Your MedianFinder object will be instantiated and called as such:
# obj = MedianFinder()
# obj.addNum(num)
# param_2 = obj.findMedian()
```

### **Java**

```java
class MedianFinder {
    private Queue<Integer> minHeap;
    private Queue<Integer> maxHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }
    
    public void addNum(int num) {
        if (minHeap.size() == maxHeap.size()) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        return minHeap.size() == maxHeap.size() ? (minHeap.peek() + maxHeap.peek()) / 2.0 : minHeap.peek();
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