# [面试题59 - I. 滑动窗口的最大值](https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)

## 题目描述
<!-- 这里写题目描述 -->
给定一个数组 `nums` 和滑动窗口的大小 `k`，请找出所有滑动窗口里的最大值。

**示例:**

```
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

**提示：**

- 你可以假设 k 总是有效的，在输入数组不为空的情况下，`1 ≤ k ≤ 输入数组的大小`。

注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/

## 解法
<!-- 这里可写通用的实现逻辑 -->
双端队列实现。

### Python3
<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        q, res = [], []
        for i, num in enumerate(nums):
            while len(q) != 0 and nums[q[-1]] <= num:
                q.pop(-1)
            q.append(i)

            if q[0] == i - k:
                q = q[1:]
            if i >= k - 1:
                res.append(nums[q[0]])
        return res

```

### Java
<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int index = 0, n = nums.length;
        if (k == 0 || n == 0) {
            return new int[0];
        }
        int[] res = new int[n - k + 1];
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();
            }
            q.addLast(i);
            if (q.peekFirst() == i - k) {
                q.pollFirst();
            }
            if (i >= k - 1) {
                res[index++] = nums[q.peekFirst()];
            }
        }
        return res;
    }
}
```

### JavaScript
```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number[]}
 */
var maxSlidingWindow = function(nums, k) {
    if(!nums.length || !k) return []
    if(k === 1) return nums
    let res = []
    let tmpMax = -Infinity
    let len = nums.length
    let window = []
    for(let i=0;i<k;i++) {
        tmpMax = Math.max(nums[i],tmpMax)
        window.push(nums[i])
    }
    res.push(tmpMax)
    for(let i=k;i<len;i++) {
        let a = window.shift()
        window.push(nums[i])
        if(nums[i] > tmpMax) {
            tmpMax = nums[i]
        } else if(tmpMax === a) {
            tmpMax = Math.max(...window)
        }
        res.push(tmpMax)
    }
    return res
};
```

### ...
```

```
