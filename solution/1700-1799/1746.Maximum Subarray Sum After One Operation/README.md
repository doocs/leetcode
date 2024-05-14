---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1746.Maximum%20Subarray%20Sum%20After%20One%20Operation/README.md
tags:
    - 数组
    - 动态规划
---

# [1746. 经过一次操作后的最大子数组和 🔒](https://leetcode.cn/problems/maximum-subarray-sum-after-one-operation)

[English Version](/solution/1700-1799/1746.Maximum%20Subarray%20Sum%20After%20One%20Operation/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一个整数数组 <code>nums</code>。你只能将一个元素 <code>nums[i]</code> 替换为 <code>nums[i] * nums[i]</code>。</p>

<p>返回替换后的最大子数组和。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,-1,-4,-3]
<strong>输出：</strong>17
<strong>解释：</strong>你可以把-4替换为16(-4*(-4))，使nums = [2,-1,<strong>16</strong>,-3]. 现在，最大子数组和为 2 + -1 + 16 = 17.</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,-1,1,1,-1,-1,1]
<strong>输出：</strong>4
<strong>解释：</strong>你可以把第一个-1替换为1，使 nums = [1,<strong>1</strong>,1,1,-1,-1,1]. 现在，最大子数组和为 1 + 1 + 1 + 1 = 4.</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $f[i]$ 表示以 $nums[i]$ 结尾，且没有进行替换的最大子数组和，另外定义 $g[i]$ 表示以 $nums[i]$ 结尾，且进行了替换的最大子数组和。那么有如下状态转移方程：

$$
\begin{aligned}
f[i] &= \max(f[i - 1], 0) + nums[i] \\
g[i] &= \max(\max(f[i - 1], 0) + nums[i] \times nums[i], g[i - 1] + nums[i])
\end{aligned}
$$

最终答案即为所有 $max(f[i], g[i])$ 的最大值。

由于 $f[i]$ 只与 $f[i - 1]$ 有关，因此我们可以只用两个变量来维护 $f[i]$ 和 $g[i]$ 的值，从而将空间复杂度降低到 $O(1)$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxSumAfterOperation(self, nums: List[int]) -> int:
        f = g = 0
        ans = -inf
        for x in nums:
            ff = max(f, 0) + x
            gg = max(max(f, 0) + x * x, g + x)
            f, g = ff, gg
            ans = max(ans, f, g)
        return ans
```

```java
class Solution {
    public int maxSumAfterOperation(int[] nums) {
        int f = 0, g = 0;
        int ans = Integer.MIN_VALUE;
        for (int x : nums) {
            int ff = Math.max(f, 0) + x;
            int gg = Math.max(Math.max(f, 0) + x * x, g + x);
            f = ff;
            g = gg;
            ans = Math.max(ans, Math.max(f, g));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxSumAfterOperation(vector<int>& nums) {
        int f = 0, g = 0;
        int ans = INT_MIN;
        for (int x : nums) {
            int ff = max(f, 0) + x;
            int gg = max(max(f, 0) + x * x, g + x);
            f = ff;
            g = gg;
            ans = max({ans, f, g});
        }
        return ans;
    }
};
```

```go
func maxSumAfterOperation(nums []int) int {
	var f, g int
	ans := -(1 << 30)
	for _, x := range nums {
		ff := max(f, 0) + x
		gg := max(max(f, 0)+x*x, g+x)
		f, g = ff, gg
		ans = max(ans, max(f, g))
	}
	return ans
}
```

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn max_sum_after_operation(nums: Vec<i32>) -> i32 {
        // Here f[i] represents the value of max sub-array that ends with nums[i] with no substitution
        let mut f = 0;
        // g[i] represents the case with exact one substitution
        let mut g = 0;
        let mut ret = 1 << 31;

        // Begin the actual dp process
        for e in &nums {
            // f[i] = MAX(f[i - 1], 0) + nums[i]
            let new_f = std::cmp::max(f, 0) + *e;
            // g[i] = MAX(MAX(f[i - 1], 0) + nums[i] * nums[i], g[i - 1] + nums[i])
            let new_g = std::cmp::max(std::cmp::max(f, 0) + *e * *e, g + *e);
            // Update f[i] & g[i]
            f = new_f;
            g = new_g;
            // Since we start at 0, update answer after updating f[i] & g[i]
            ret = std::cmp::max(ret, g);
        }

        ret
    }
}
```

<!-- tabs:end -->

<!-- end -->
