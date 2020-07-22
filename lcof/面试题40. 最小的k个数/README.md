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
<!-- tabs:start -->

### **Python3**
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

### **Java**
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

### **JavaScript**
```js
/**
 * @param {number[]} arr
 * @param {number} k
 * @return {number[]}
 */
var getLeastNumbers = function(arr, k) {
    // 排序
    // return arr.sort((a,b)=>a-b).slice(0,k)
    // ==========================================
    // 快排思想
    let left = 0
    let right = arr.length-1
    while(left < right) {
        let i = partition(left, right)
        if(i <= k) {
            left = i+1
        }
        if(i >= k){
            right = i-1
        }
    }
    function partition(left, right) {
        let pivot = arr[left]
        while(left < right) {
            while(left < right && arr[right] >= pivot) {
                right--
            }
            arr[left] = arr[right]
            while(left < right && arr[left] <= pivot) {
                left++
            }
            arr[right] = arr[left]
        }
        arr[left] = pivot
        return left
    }
    return arr.slice(0, k)
};
```

### **...**
```

```

<!-- tabs:end -->