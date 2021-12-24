# [面试题 41. 数据流中的中位数](https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

`[2,3,4]`  的中位数是 3

`[2,3]` 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

- void addNum(int num) - 从数据流中添加一个整数到数据结构中。
- double findMedian() - 返回目前所有元素的中位数。

**示例 1：**

```
输入：
["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]
```

**示例 2：**

```
输入：
["MedianFinder","addNum","findMedian","addNum","findMedian"]
[[],[2],[],[3],[]]
输出：[null,null,2.00000,null,2.50000]
```

**限制：**

- 最多会对  addNum、findMedia 进行  50000  次调用。

注意：本题与主站 295 题相同：https://leetcode-cn.com/problems/find-median-from-data-stream/

## 解法

<!-- 这里可写通用的实现逻辑 -->

- 创建大根堆、小根堆，其中：大根堆存放较小的一半元素，小根堆存放较大的一半元素。
- 添加元素时，若两堆元素个数相等，放入小根堆（使得小根堆个数多 1）；若不等，放入大根堆（使得大小根堆元素个数相等）
- 取中位数时，若两堆元素个数相等，取两堆顶求平均值；若不等，取小根堆堆顶。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class MedianFinder {
    Queue<Integer> minHeap;
    Queue<Integer> maxHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            maxHeap.offer(num);
            // 放入小根堆(小根堆多1)
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            // 放入大根堆(大小堆数量相等)
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (((maxHeap.size() + minHeap.size()) & 1) == 0) {
            // 偶数个，取两个堆顶平均值
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return minHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```

### **JavaScript**

```js
/**
 * initialize your data structure here.
 */
var MedianFinder = function () {
    this.val = [];
};

/**
 * @param {number} num
 * @return {void}
 */
MedianFinder.prototype.addNum = function (num) {
    let left = 0;
    let right = this.val.length;
    while (left < right) {
        let mid = left + ~~((right - left) / 2);
        if (num > this.val[mid]) {
            left = mid + 1;
        } else {
            right = mid;
        }
    }
    this.val.splice(left, 0, num);
};

/**
 * @return {number}
 */
MedianFinder.prototype.findMedian = function () {
    let mid = ~~(this.val.length / 2);
    return this.val.length % 2
        ? this.val[mid]
        : (this.val[mid - 1] + this.val[mid]) / 2;
};
```

### **C++**

```cpp
class MedianFinder {
public:
    /** initialize your data structure here. */
    MedianFinder() {
    }

    void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            maxHeap.push(num);
            int temp = maxHeap.top();
            maxHeap.pop();
            minHeap.push(temp);
        } else {
            minHeap.push(num);
            int temp = minHeap.top();
            minHeap.pop();
            maxHeap.push(temp);
        }
    }

    double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.top() + minHeap.top()) / 2.0;
        }
        return minHeap.top();
    }

private:
    priority_queue<int> maxHeap;
    priority_queue<int, vector<int>, greater<int>> minHeap;

};
```

### **...**

```

```

<!-- tabs:end -->
