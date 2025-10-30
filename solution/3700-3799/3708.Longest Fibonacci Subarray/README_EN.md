---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3708.Longest%20Fibonacci%20Subarray/README_EN.md
rating: 1380
source: Biweekly Contest 167 Q2
tags:
    - Array
---

<!-- problem:start -->

# [3708. Longest Fibonacci Subarray](https://leetcode.com/problems/longest-fibonacci-subarray)

[中文文档](/solution/3700-3799/3708.Longest%20Fibonacci%20Subarray/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <strong>positive</strong> integers <code>nums</code>.</p>

<p>A <strong>Fibonacci</strong> array is a contiguous sequence whose third and subsequent terms each equal the sum of the two preceding terms.</p>

<p>Return the length of the longest <strong>Fibonacci</strong> <strong><span data-keyword="subarray-nonempty">subarray</span></strong> in <code>nums</code>.</p>

<p><strong>Note:</strong> Subarrays of length 1 or 2 are always <strong>Fibonacci</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,1,1,2,3,5,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest Fibonacci subarray is <code>nums[2..6] = [1, 1, 2, 3, 5]</code>.</p>

<p><code>[1, 1, 2, 3, 5]</code> is Fibonacci because <code>1 + 1 = 2</code>, <code>1 + 2 = 3</code>, and <code>2 + 3 = 5</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,2,7,9,16]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest Fibonacci subarray is <code>nums[0..4] = [5, 2, 7, 9, 16]</code>.</p>

<p><code>[5, 2, 7, 9, 16]</code> is Fibonacci because <code>5 + 2 = 7</code>, <code>2 + 7 = 9</code>, and <code>7 + 9 = 16</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1000000000,1000000000,1000000000]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The longest Fibonacci subarray is <code>nums[1..2] = [1000000000, 1000000000]</code>.</p>

<p><code>[1000000000, 1000000000]</code> is Fibonacci because its length is 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

We can use a variable $f$ to record the length of the longest Fibonacci subarray ending at the current element. Initially, $f=2$, indicating that any two elements can form a Fibonacci subarray.

Then we traverse the array starting from index $2$. For each element $nums[i]$, if it equals the sum of the previous two elements, i.e., $nums[i] = nums[i-1] + nums[i-2]$, it means the current element can be appended to the previous Fibonacci subarray, so we increment $f$ by $1$. Otherwise, it means the current element cannot be appended to the previous Fibonacci subarray, so we reset $f$ to $2$. During the traversal, we continuously update the answer $\textit{ans} = \max(\textit{ans}, f)$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        ans = f = 2
        for i in range(2, n):
            if nums[i] == nums[i - 1] + nums[i - 2]:
                f = f + 1
                ans = max(ans, f)
            else:
                f = 2
        return ans
```

#### Java

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int f = 2;
        int ans = f;
        for (int i = 2; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1] + nums[i - 2]) {
                ++f;
                ans = Math.max(ans, f);
            } else {
                f = 2;
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
    int longestSubarray(vector<int>& nums) {
        int f = 2;
        int ans = f;
        for (int i = 2; i < nums.size(); ++i) {
            if (nums[i] == nums[i - 1] + nums[i - 2]) {
                ++f;
                ans = max(ans, f);
            } else {
                f = 2;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func longestSubarray(nums []int) int {
	f := 2
	ans := f
	for i := 2; i < len(nums); i++ {
		if nums[i] == nums[i-1]+nums[i-2] {
			f++
			ans = max(ans, f)
		} else {
			f = 2
		}
	}
	return ans
}
```

#### TypeScript

```ts
function longestSubarray(nums: number[]): number {
    let f = 2;
    let ans = f;
    for (let i = 2; i < nums.length; ++i) {
        if (nums[i] === nums[i - 1] + nums[i - 2]) {
            ans = Math.max(ans, ++f);
        } else {
            f = 2;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
