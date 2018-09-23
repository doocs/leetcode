## 最大子序和
### 题目描述

给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:
```
输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。

### 解法
此题可以用动态规划法，开辟一个数组res，res[i] 表示以当前结点nums[i] 结尾的最大连续子数组的和。最后计算 res 的最大元素即可。
也可以用分治法，最大连续子数组有三种情况：在原数组左侧、右侧、跨中间结点，返回这三者的最大值即可。


动态规划法：

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] res = new int[n];
        res[0] = nums[0];
        int max = res[0];
        for (int i = 1; i < n; ++i) {
            res[i] = Math.max(res[i - 1] + nums[i], nums[i]);
            max = Math.max(res[i], max);
        }
        
        return max;
        
    }
}
```

分治法：

```java
class Solution {
    public int maxSubArray(int[] nums) {
        return maxSubArray(nums, 0, nums.length - 1);
    }
    
    private int maxSubArray(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        int mid = start + ((end - start) >> 1);
        int left = maxSubArray(nums, start, mid);
        int right = maxSubArray(nums, mid + 1, end);
        
        int leftSum = 0;
        int leftMax = Integer.MIN_VALUE;
        for (int i = mid; i >= start; --i) {
            leftSum += nums[i];
            leftMax = Math.max(leftSum, leftMax);
        }
        
        int rightSum = 0;
        int rightMax = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= end; ++i) {
            rightSum += nums[i];
            rightMax = Math.max(rightSum, rightMax);
        }
        
        return Math.max(Math.max(left, right), leftMax + rightMax);
        
        
    }
}
```