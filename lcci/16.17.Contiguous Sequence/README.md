# [面试题 16.17. 连续数列](https://leetcode.cn/problems/contiguous-sequence-lcci)

[English Version](/lcci/16.17.Contiguous%20Sequence/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个整数数组（有正数有负数），找出总和最大的连续数列，并返回总和。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入：</strong> [-2,1,-3,4,-1,2,1,-5,4]
<strong>输出：</strong> 6
<strong>解释：</strong> 连续子数组 [4,-1,2,1] 的和最大，为 6。
</pre>

<p><strong>进阶：</strong></p>

<p>如果你已经实现复杂度为 O(<em>n</em>) 的解法，尝试使用更为精妙的分治法求解。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

定义状态 `dp[i]` 表示以 `nums[i]` 结尾的连续子数组的最大和，初始时 `dp[0] = nums[0]`，当 $i\gt 0$ 时，状态转移方程为：

$$
dp[i]=\max(dp[i-1],0)+nums[i], i>0
$$

答案为 `dp` 数组中的最大值。

时间复杂度 $O(n)$，其中 $n$ 表示 `nums` 的长度。

由于 `dp[i]` 只与 `dp[i-1]` 有关，因此可以使用滚动数组优化空间复杂度，将空间复杂度降低到 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        n = len(nums)
        dp = [0] * n
        dp[0] = nums[0]
        for i in range(1, n):
            dp[i] = max(dp[i - 1], 0) + nums[i]
        return max(dp)
```

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        ans = s = -inf
        for v in nums:
            s = max(s, 0) + v
            ans = max(ans, s)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int inf = Integer.MIN_VALUE;
        int ans = inf, s = inf;
        for (int v : nums) {
            s = Math.max(s, 0) + v;
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> dp(n);
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < n; ++i) {
            dp[i] = max(dp[i - 1], 0) + nums[i];
            ans = max(ans, dp[i]);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int s = INT_MIN, ans = INT_MIN;
        for (int v : nums) {
            s = max(s, 0) + v;
            ans = max(ans, s);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxSubArray(nums []int) int {
	n := len(nums)
	dp := make([]int, n)
	dp[0] = nums[0]
	ans := dp[0]
	for i := 1; i < n; i++ {
		dp[i] = max(dp[i-1], 0) + nums[i]
		ans = max(ans, dp[i])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxSubArray(nums []int) int {
	inf := math.MinInt32
	ans, s := inf, inf
	for _, v := range nums {
		s = max(s, 0) + v
		ans = max(ans, s)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    const n = nums.length;
    const dp = new Array(n).fill(0);
    dp[0] = nums[0];
    let ans = dp[0];
    for (let i = 1; i < n; ++i) {
        dp[i] = Math.max(dp[i - 1], 0) + nums[i];
        ans = Math.max(ans, dp[i]);
    }
    return ans;
};
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    const inf = -Infinity;
    let s = inf;
    let ans = inf;
    for (const v of nums) {
        s = Math.max(s, 0) + v;
        ans = Math.max(ans, s);
    }
    return ans;
};
```

### **PHP**

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @return Integer
     */
    function maxSubArray($nums) {
        $pre = 0;
        $max = $nums[0];
        for ($i = 0; $i < count($nums); $i++) {
            $pre = max($pre + $nums[$i], $nums[$i]);
            $max = max($pre, $max);
        }
        return $max;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
