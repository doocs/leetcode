---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1746.Maximum%20Subarray%20Sum%20After%20One%20Operation/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1746. Maximum Subarray Sum After One Operation 🔒](https://leetcode.com/problems/maximum-subarray-sum-after-one-operation)

[中文文档](/solution/1700-1799/1746.Maximum%20Subarray%20Sum%20After%20One%20Operation/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. You must perform <strong>exactly one</strong> operation&nbsp;where you can <strong>replace</strong> one&nbsp;element <code>nums[i]</code> with <code>nums[i] * nums[i]</code>.&nbsp;</p>

<p>Return <em>the <strong>maximum</strong> possible subarray sum after <strong>exactly&nbsp;one</strong> operation</em>. The subarray must be non-empty.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,-1,-4,-3]
<strong>Output:</strong> 17
<strong>Explanation:</strong> You can perform the operation on index 2 (0-indexed) to make nums = [2,-1,<strong>16</strong>,-3]. Now, the maximum subarray sum is 2 + -1 + 16 = 17.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,-1,1,1,-1,-1,1]
<strong>Output:</strong> 4
<strong>Explanation:</strong> You can perform the operation on index 1 (0-indexed) to make nums = [1,<strong>1</strong>,1,1,-1,-1,1]. Now, the maximum subarray sum is 1 + 1 + 1 + 1 = 4.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup>&nbsp;&lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### Rust

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

<!-- solution:end -->

<!-- problem:end -->
