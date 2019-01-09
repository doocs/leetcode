## 戳气球
### 题目描述

有 `n` 个气球，编号为 `0` 到 `n-1`，每个气球上都标有一个数字，这些数字存在数组 `nums` 中。

现在要求你戳破所有的气球。每当你戳破一个气球 `i` 时，你可以获得 `nums[left] * nums[i] * nums[right]` 个硬币。 这里的 `left` 和 `right` 代表和 `i` 相邻的两个气球的序号。注意当你戳破了气球 `i` 后，气球 `left` 和气球 `right` 就变成了相邻的气球。

求所能获得硬币的最大数量。

**说明:**

- 你可以假设 `nums[-1] = nums[n] = 1`，但注意它们不是真实存在的所以并不能被戳破。
- 0 ≤ `n` ≤ 500, 0 ≤ `nums[i]` ≤ 100

**示例:**
```
输入: [3,1,5,8]
输出: 167 
解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

```     

### 解法
数组 `f` 表示某范围内(开区间)戳破所有气球能获得的最大硬币。那么题意转换为求`f[0][n+1]`。

假设最后一个戳破的气球为 `i`，那么：
```
f[0][n+1] = f[0][i] + f[i][n+1] + nums[i] * nums[0][n+1]。
```

利用记忆化搜索，遍历当最后一个戳破的气球为 `i` 时，求 `f[0][n+1]` 的最大值。

```java
class Solution {
    
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] f = new int[n + 2][n + 2];
        for (int i= 0; i < n + 2; ++i) {
            for (int j = 0; j < n + 2; ++j) {
                f[i][j] = -1;
            }
        }
        int[] bak = new int[n + 2];
        bak[0] = bak[n + 1] = 1;
        for (int i = 1; i < n + 1; ++i) {
            bak[i] = nums[i - 1];
        }
        return dp(bak, f, 0, n + 1);
    }
    
    private int dp(int[] nums, int[][] f, int x, int y) {
        if (f[x][y] != -1) {
            return f[x][y];
        }
        
        f[x][y] = 0;
        
        //枚举最后一个戳破的气球的位置
        for (int i = x + 1; i < y; ++i) {
            f[x][y] = Math.max(f[x][y], nums[i] * nums[x] * nums[y] + dp(nums,f,  x, i) + dp(nums, f, i, y));
        }
        return f[x][y];
        
    }
    
    
}
```
