# [413. 等差数列划分](https://leetcode.cn/problems/arithmetic-slices)

[English Version](/solution/0400-0499/0413.Arithmetic%20Slices/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个数列 <strong>至少有三个元素</strong> ，并且任意两个相邻元素之差相同，则称该数列为等差数列。</p>

<ul>
	<li>例如，<code>[1,3,5,7,9]</code>、<code>[7,7,7,7]</code> 和 <code>[3,-1,-5,-9]</code> 都是等差数列。</li>
</ul>

<div class="original__bRMd">
<div>
<p>给你一个整数数组 <code>nums</code> ，返回数组 <code>nums</code> 中所有为等差数组的 <strong>子数组</strong> 个数。</p>

<p><strong>子数组</strong> 是数组中的一个连续序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4]
<strong>输出：</strong>3
<strong>解释：</strong>nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 5000</code></li>
	<li><code>-1000 <= nums[i] <= 1000</code></li>
</ul>
</div>
</div>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划法。

设 `dp[i]` 表示以 i 结尾的数组构成的等差数列的个数。

如果 `nums[i] + nums[i - 2] ≠ nums[i - 1] * 2`，说明以 i 结尾的数组无法构成等差数列，`dp[i] = 0`；否则 `dp[i] = 1 + dp[i - 1]`。

结果返回 dp 数组所有元素之和即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * n
        for i in range(2, n):
            if nums[i] + nums[i - 2] == (nums[i - 1] << 1):
                dp[i] = 1 + dp[i - 1]
        return sum(dp)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 2; i < n; ++i) {
            if (nums[i] + nums[i - 2] == (nums[i - 1] << 1)) {
                dp[i] = 1 + dp[i - 1];
            }
        }
        int res = 0;
        for (int e : dp) {
            res += e;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int n = nums.size();
        vector<int> dp(n, 0);
        for (int i = 2; i < n; ++i) {
            if (nums[i] + nums[i - 2] == (nums[i - 1] * 2)) {
                dp[i] = 1 + dp[i - 1];
            }
        }
        int res = 0;
        for (auto e : dp) {
            res += e;
        }
        return res;
    }
};
```

### **Go**

```go
func numberOfArithmeticSlices(nums []int) int {
	n := len(nums)
	dp := make([]int, n)
	for i := 2; i < n; i++ {
		if nums[i]-nums[i-1] == nums[i-1]-nums[i-2] {
			dp[i] = 1 + dp[i-1]
		}
	}
	res := 0
	for _, e := range dp {
		res += e
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
