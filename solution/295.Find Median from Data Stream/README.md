## 数据流的中位数
### 题目描述

中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

例如，

`[2,3,4]` 的中位数是 `3`

`[2,3]` 的中位数是 `(2 + 3) / 2 = 2.5`

设计一个支持以下两种操作的数据结构：

- void addNum(int num) - 从数据流中添加一个整数到数据结构中。
- double findMedian() - 返回目前所有元素的中位数。

示例：
```
addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
```

进阶:

- 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
- 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？

### 解法
维护一个大根堆 bigRoot 和一个小根堆 smallRoot，若有 n 个数，较小的 n/2 个数放在大根堆，而较大的 n/2 个数放在小根堆。

- 若其中一个堆的元素个数比另一个堆的元素个数大 1，弹出堆顶元素到另一个堆。
- 获取中位数时，若两个堆的元素个数相等，返回两个堆堆顶的平均值。否则返回元素个数较多的堆的堆顶。

```java
class MedianFinder {
    
    private PriorityQueue<Integer> bigRoot;
    private PriorityQueue<Integer> smallRoot;

    /** initialize your data structure here. */
    public MedianFinder() {
        bigRoot = new PriorityQueue<>(Comparator.reverseOrder());
        smallRoot = new PriorityQueue<>(Integer::compareTo);
    }
    
    public void addNum(int num) {
        if (bigRoot.isEmpty() || bigRoot.peek() > num) {
            bigRoot.offer(num);
        } else {
            smallRoot.offer(num);
        }
        
        int size1 = bigRoot.size();
        int size2 = smallRoot.size();
        if (size1 - size2 > 1) {
            smallRoot.offer(bigRoot.poll());
        } else if (size2 - size1 > 1) {
            bigRoot.offer(smallRoot.poll());
        }
    }
    
    public double findMedian() {
        int size1 = bigRoot.size();
        int size2 = smallRoot.size();
        
        return size1 == size2 
            ? (bigRoot.peek() + smallRoot.peek()) * 1.0 / 2
            : (size1 > size2 ? bigRoot.peek() : smallRoot.peek());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
```