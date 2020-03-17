## 数据流中的第K大元素
### 题目描述

设计一个找到数据流中第 K 大元素的类（class）。注意是排序后的第 K 大元素，不是第 K 个不同的元素。

你的 `KthLargest` 类需要一个同时接收整数 `k` 和整数数组`nums` 的构造器，它包含数据流中的初始元素。每次调用 `KthLargest.add`，返回当前数据流中第 K 大的元素。

**示例:**

```
int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
```

**说明:**

你可以假设 `nums` 的长度≥ `k-1` 且 `k` ≥ 1。

### 解法
建立一个有 k 个元素的小根堆。add 操作时：

- 若堆元素少于 k 个，直接添加到堆中；
- 若堆元素有 k 个，判断堆顶元素 peek() 与 val 的大小关系：若 peek() >= val，直接返回堆顶元素；若 peek() < val，弹出堆顶元素，将 val 添加至堆中，然后返回堆顶。

```java
class KthLargest {

    private PriorityQueue<Integer> minHeap;
    private int size;
    
    public KthLargest(int k, int[] nums) {
        minHeap = new PriorityQueue<>(k);
        size = k;
        for (int e : nums) {
            add(e);
        }
    }
    
    public int add(int val) {
        if (minHeap.size() < size) {
            minHeap.add(val);
        } else {
            if (minHeap.peek() < val) {
                minHeap.poll();
                minHeap.add(val);
            }
        }
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
```