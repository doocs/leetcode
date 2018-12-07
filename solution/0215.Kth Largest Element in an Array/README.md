## 数组中的第K个最大元素
### 题目描述

在未排序的数组中找到第 `k` 个最大的元素。请注意，你需要找的是数组排序后的第 `k` 个最大的元素，而不是第 `k` 个不同的元素。

**示例1:**
```
输入: [3,2,1,5,6,4] 和 k = 2
输出: 5
```

**示例2:**
```
输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
输出: 4
```

### 解法
建一个小根堆，遍历数组元素：

- 当堆中元素个数少于堆容量时，直接添加该数组元素；
- 否则，当堆顶元素小于数组元素时，先弹出堆顶元素，再把数组元素添加进堆。

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int e : nums) {
            if (minHeap.size() < k) {
                minHeap.add(e);
            } else {
                if (minHeap.peek() < e) {
                    minHeap.poll();
                    minHeap.add(e);
                }
            }
            
        }
        return minHeap.peek();
    }
}
```