# [面试题40. 最小的k个数](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/)

## 题目描述
输入整数数组 `arr`，找出其中最小的 `k` 个数。例如，输入 4、5、1、6、2、7、3、8 这 8 个数字，则最小的 4 个数字是 1、2、3、4。

**示例 1：**

```
输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
```

**示例 2：**

```
输入：arr = [0,1,2,1], k = 1
输出：[0]
```

**限制：**

- `0 <= k <= arr.length <= 10000`
- `0 <= arr[i] <= 10000`

## 解法
### Python3
```python
import heapq

class Solution:
    def getLeastNumbers(self, arr: List[int], k: int) -> List[int]:
        if k == 0:
            return []
        heap = []
        for e in arr:
            heapq.heappush(heap, e)
        return heapq.nsmallest(k, heap)
```

### Java
```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[]{};
        }
        PriorityQueue<Integer> bigRoot = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int e : arr) {
            if (bigRoot.size() < k) {
                bigRoot.offer(e);
            } else {
                if (e < bigRoot.peek()) {
                    bigRoot.poll();
                    bigRoot.offer(e);
                }
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; ++i) {
            res[i] = bigRoot.poll();
        }
        return res;
    }
}
```

### ...
```

```
