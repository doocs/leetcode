## 打家劫舍
### 题目描述

你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。

示例 1:
```
输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。偷窃到的最高金额 = 1 + 3 = 4 。
```

示例 2:
```
输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。偷窃到的最高金额 = 2 + 9 + 1 = 12 。
```     

### 解法
每个房屋都有两种选择，抢 or 不抢。从第 n 个房屋开始往前抢，进行递归。

以上递归方式会超时，因为中间有很多重复的计算，我们可以开一个空间，如数组，来记录中间结果，避免重复计算，这也就是动态规划。

递归版（超时）：

```java
class Solution {
    private int process(int n, int[] nums) {
        return n < 0 ? 0 : Math.max(nums[n] + process(n - 2, nums), process(n - 1, nums));
    }
    
    public int rob(int[] nums) {
        return (nums == null || nums.length == 0) ? 0 : process(nums.length - 1, nums);
    }
}
```

动态规划版（改进）：

```java
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        
        int[] result = new int[n];
        result[0] = nums[0];
        result[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < n; ++i) {
            result[i] = Math.max(nums[i] + result[i - 2], result[i - 1]);
        }
        
        return result[n - 1];
        
    }
}
```