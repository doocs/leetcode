# [面试题42. 连续子数组的最大和](https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/)

## 题目描述
输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为 O(n)。

**示例1:**

```
输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

**提示：**

- `1 <= arr.length <= 10^5`
- `-100 <= arr[i] <= 100`

## 解法
### Python3
```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        res = t = nums[0]
        for i in range(1, len(nums)):
            t = nums[i] + (0 if t < 0 else t)
            res = max(res, t)
        return res

```

### Java
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int res = nums[0], t = nums[0];
        for (int i = 1, n = nums.length; i < n; ++i) {
            t = nums[i] + (t < 0 ? 0 : t);
            res = Math.max(res, t);
        }
        return res;
    }
}
```

### ...
```

```
