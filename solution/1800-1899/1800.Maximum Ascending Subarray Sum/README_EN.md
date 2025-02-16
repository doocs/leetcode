---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1800.Maximum%20Ascending%20Subarray%20Sum/README_EN.md
rating: 1229
source: Weekly Contest 233 Q1
tags:
    - Array
---

<!-- problem:start -->

# [1800. Maximum Ascending Subarray Sum](https://leetcode.com/problems/maximum-ascending-subarray-sum)

[中文文档](/solution/1800-1899/1800.Maximum%20Ascending%20Subarray%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given an array of positive integers <code>nums</code>, return the <strong>maximum</strong> possible sum of an <span data-keyword="strictly-increasing-array">strictly increasing subarray</span> in<em> </em><code>nums</code>.</p>

<p>A subarray is defined as a contiguous sequence of numbers in an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20,30,5,10,50]
<strong>Output:</strong> 65
<strong>Explanation: </strong>[5,10,50] is the ascending subarray with the maximum sum of 65.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20,30,40,50]
<strong>Output:</strong> 150
<strong>Explanation: </strong>[10,20,30,40,50] is the ascending subarray with the maximum sum of 150.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [12,17,15,13,10,11,12]
<strong>Output:</strong> 33
<strong>Explanation: </strong>[10,11,12] is the ascending subarray with the maximum sum of 33.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Direct Simulation

We use a variable $t$ to record the current sum of the ascending subarray, and a variable $ans$ to record the maximum sum of the ascending subarray.

Traverse the array $nums$:

If the current element is the first element of the array, or the current element is greater than the previous one, then add the current element to the sum of the current ascending subarray, i.e., $t = t + nums[i]$, and update the maximum sum of the ascending subarray $ans = \max(ans, t)$. Otherwise, the current element does not satisfy the condition of the ascending subarray, so reset the sum $t$ of the current ascending subarray to the current element, i.e., $t = nums[i]$.

After the traversal, return the maximum sum of the ascending subarray $ans$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxAscendingSum(self, nums: List[int]) -> int:
        ans = t = 0
        for i, v in enumerate(nums):
            if i == 0 or v > nums[i - 1]:
                t += v
                ans = max(ans, t)
            else:
                t = v
        return ans
```

#### Java

```java
class Solution {
    public int maxAscendingSum(int[] nums) {
        int ans = 0, t = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                t += nums[i];
                ans = Math.max(ans, t);
            } else {
                t = nums[i];
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxAscendingSum(vector<int>& nums) {
        int ans = 0, t = 0;
        for (int i = 0; i < nums.size(); ++i) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                t += nums[i];
                ans = max(ans, t);
            } else {
                t = nums[i];
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxAscendingSum(nums []int) int {
	ans, t := 0, 0
	for i, v := range nums {
		if i == 0 || v > nums[i-1] {
			t += v
			if ans < t {
				ans = t
			}
		} else {
			t = v
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxAscendingSum(nums: number[]): number {
    const n = nums.length;
    let res = nums[0];
    let sum = nums[0];
    for (let i = 1; i < n; i++) {
        if (nums[i] <= nums[i - 1]) {
            res = Math.max(res, sum);
            sum = 0;
        }
        sum += nums[i];
    }
    return Math.max(res, sum);
}
```

#### Rust

```rust
impl Solution {
    pub fn max_ascending_sum(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut res = nums[0];
        let mut sum = nums[0];
        for i in 1..n {
            if nums[i - 1] >= nums[i] {
                res = res.max(sum);
                sum = 0;
            }
            sum += nums[i];
        }
        res.max(sum)
    }
}
```

#### C

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int maxAscendingSum(int* nums, int numsSize) {
    int res = nums[0];
    int sum = nums[0];
    for (int i = 1; i < numsSize; i++) {
        if (nums[i - 1] >= nums[i]) {
            res = max(res, sum);
            sum = 0;
        }
        sum += nums[i];
    }
    return max(res, sum);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
