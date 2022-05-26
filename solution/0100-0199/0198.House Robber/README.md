# [198. 打家劫舍](https://leetcode.cn/problems/house-robber)

[English Version](/solution/0100-0199/0198.House%20Robber/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，<strong>如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警</strong>。</p>

<p>给定一个代表每个房屋存放金额的非负整数数组，计算你<strong> 不触动警报装置的情况下 </strong>，一夜之内能够偷窃到的最高金额。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[1,2,3,1]
<strong>输出：</strong>4
<strong>解释：</strong>偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[2,7,9,3,1]
<strong>输出：</strong>12
<strong>解释：</strong>偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 100</code></li>
	<li><code>0 <= nums[i] <= 400</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划法。状态转移方程：`f(n) = Math.max(f(n - 2) + nums[n], nums[n - 1])`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rob(self, nums: List[int]) -> int:
        a, b = 0, nums[0]
        for num in nums[1:]:
            a, b = b, max(num + a, b)
        return b
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        int a = 0, b = nums[0];
        for (int i = 1; i < n; ++i) {
            int c = max(nums[i] + a, b);
            a = b;
            b = c;
        }
        return b;
    }
};
```

### **C++**

```cpp
class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size();
        int a = 0, b = nums[0];
        for (int i = 1; i < n; ++i) {
            int c = max(nums[i] + a, b);
            a = b;
            b = c;
        }
        return b;
    }
};
```

### **Go**

```go
func rob(nums []int) int {
    a, b, n := 0, nums[0], len(nums)
    for i := 1; i < n; i++ {
        a, b = b, max(nums[i] + a, b)
    }
    return b
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}
```

### **TypeScript**

```ts
function rob(nums: number[]): number {
    const dp = [0, 0];
    for (const num of nums) {
        [dp[0], dp[1]] = [dp[1], Math.max(dp[1], dp[0] + num)];
    }
    return dp[1];
}
```

### **Rust**

```rust
impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        let mut dp = [0, 0];
        for num in nums {
            dp = [dp[1], dp[1].max(dp[0] + num)]
        }
        dp[1]
    }
}
```

### **...**

```

```

<!-- tabs:end -->
