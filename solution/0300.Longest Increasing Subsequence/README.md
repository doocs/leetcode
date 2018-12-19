## 最长上升子序列
### 题目描述

给定一个无序的整数数组，找到其中最长上升子序列的长度。

**示例:**
```
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
```

**说明:**

- 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
- 你算法的时间复杂度应该为 `O(n²)` 。

**进阶:** 你能将算法的时间复杂度降低到 `O(n log n)` 吗? 

### 解法
记以 `nums[i]` 结尾的最长递增子序列长度为 `res[i]`。

判断 `nums[j](0 < j < i)` 中是否满足 `nums[j] < nums[i]` 
- 若是，`res[i + 1]` 可更新为 `res[j] + 1`。求出最大值，即为当前 `res[i + 1]` 的值。
- 若不满足，`res[i + 1] = 1`

求解过程中，也求出 `res` 的最大值，即为最长的子序列的长度。

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] res = new int[n];
        res[0] = 1;
        res[1] = nums[1] > nums[0] ? 2 : 1;
        int max = res[1];
        for (int i = 2; i < n; ++i) {
            res[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    res[i] = Math.max(res[i], res[j] + 1);
                }
            }
            max = Math.max(max, res[i]);
        }
        return max;
    }
}
```