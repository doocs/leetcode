# [53. 最大子数组和](https://leetcode.cn/problems/maximum-subarray)

[English Version](/solution/0000-0099/0053.Maximum%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。</p>

<p><strong>子数组 </strong>是数组中的一个连续部分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>输出：</strong>6
<strong>解释：</strong>连续子数组&nbsp;[4,-1,2,1] 的和最大，为&nbsp;6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,4,-1,7,8]
<strong>输出：</strong>23
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果你已经实现复杂度为 <code>O(n)</code> 的解法，尝试使用更为精妙的 <strong>分治法</strong> 求解。</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

### 1. 动态规划

设 `dp[i]` 表示 `[0..i]` 中，以 `nums[i]` 结尾的最大子数组和，状态转移方程 `dp[i] = nums[i] + max(dp[i - 1], 0)`。

由于 `dp[i]` 只与子问题 `dp[i-1]` 有关，故可以用一个变量 f 来表示。

### 2. 分治

最大子数组和可能有三种情况：

1. 在数组左半部分
1. 在数组右半部分
1. 跨越左右半部分

递归求得三者，返回最大值即可。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

动态规划：

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        res = f = nums[0]
        for num in nums[1:]:
            f = num + max(f, 0)
            res = max(res, f)
        return res
```

分治：

```python
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        def crossMaxSub(nums, left, mid, right):
            lsum = rsum = 0
            lmx = rmx = -inf
            for i in range(mid, left - 1, -1):
                lsum += nums[i]
                lmx = max(lmx, lsum)
            for i in range(mid + 1, right + 1):
                rsum += nums[i]
                rmx = max(rmx, rsum)
            return lmx + rmx

        def maxSub(nums, left, right):
            if left == right:
                return nums[left]
            mid = (left + right) >> 1
            lsum = maxSub(nums, left, mid)
            rsum = maxSub(nums, mid + 1, right)
            csum = crossMaxSub(nums, left, mid, right)
            return max(lsum, rsum, csum)

        left, right = 0, len(nums) - 1
        return maxSub(nums, left, right)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

动态规划：

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int f = nums[0], res = nums[0];
        for (int i = 1, n = nums.length; i < n; ++i) {
            f = nums[i] + Math.max(f, 0);
            res = Math.max(res, f);
        }
        return res;
    }
}
```

分治：

```java
class Solution {
    public int maxSubArray(int[] nums) {
        return maxSub(nums, 0, nums.length - 1);
    }

    private int maxSub(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) >>> 1;
        int lsum = maxSub(nums, left, mid);
        int rsum = maxSub(nums, mid + 1, right);
        return Math.max(Math.max(lsum, rsum), crossMaxSub(nums, left, mid, right));
    }

    private int crossMaxSub(int[] nums, int left, int mid, int right) {
        int lsum = 0, rsum = 0;
        int lmx = Integer.MIN_VALUE, rmx = Integer.MIN_VALUE;
        for (int i = mid; i >= left; --i) {
            lsum += nums[i];
            lmx = Math.max(lmx, lsum);
        }
        for (int i = mid + 1; i <= right; ++i) {
            rsum += nums[i];
            rmx = Math.max(rmx, rsum);
        }
        return lmx + rmx;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxSubArray(vector<int>& nums) {
        int f = nums[0], res = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            f = nums[i] + max(f, 0);
            res = max(res, f);
        }
        return res;
    }
};
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var maxSubArray = function (nums) {
    let f = nums[0],
        res = nums[0];
    for (let i = 1; i < nums.length; ++i) {
        f = nums[i] + Math.max(f, 0);
        res = Math.max(res, f);
    }
    return res;
};
```

### **Go**

```go
func maxSubArray(nums []int) int {
    f, res := nums[0], nums[0]
    for i := 1; i < len(nums); i++ {
        if f > 0 {
            f += nums[i]
        } else {
            f = nums[i]
        }
        if f > res {
            res = f
        }
    }
    return res
}
```

### **C#**

```cs
public class Solution {
    public int MaxSubArray(int[] nums) {
        int res = nums[0], f = nums[0];
        for (int i = 1; i < nums.Length; ++i)
        {
            f = nums[i] + Math.Max(f, 0);
            res = Math.Max(res, f);
        }
        return res;
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_sub_array(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = nums[0];
        let mut sum = nums[0];
        for i in 1..n {
            let num = nums[i];
            sum = num.max(sum + num);
            res = res.max(sum);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
