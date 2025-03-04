---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3423.Maximum%20Difference%20Between%20Adjacent%20Elements%20in%20a%20Circular%20Array/README_EN.md
rating: 1184
source: Biweekly Contest 148 Q1
tags:
    - Array
---

<!-- problem:start -->

# [3423. Maximum Difference Between Adjacent Elements in a Circular Array](https://leetcode.com/problems/maximum-difference-between-adjacent-elements-in-a-circular-array)

[中文文档](/solution/3400-3499/3423.Maximum%20Difference%20Between%20Adjacent%20Elements%20in%20a%20Circular%20Array/README.md)

## Description

<!-- description:start -->

<p>Given a <strong>circular</strong> array <code>nums</code>, find the <b>maximum</b> absolute difference between adjacent elements.</p>

<p><strong>Note</strong>: In a circular array, the first and last elements are adjacent.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Because <code>nums</code> is circular, <code>nums[0]</code> and <code>nums[2]</code> are adjacent. They have the maximum absolute difference of <code>|4 - 1| = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-5,-10,-5]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<p>The adjacent elements <code>nums[0]</code> and <code>nums[1]</code> have the maximum absolute difference of <code>|-5 - (-10)| = 5</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We traverse the array $\textit{nums}$, calculate the absolute difference between adjacent elements, and maintain the maximum absolute difference. Finally, we compare it with the absolute difference between the first and last elements and take the maximum value.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxAdjacentDistance(self, nums: List[int]) -> int:
        return max(max(abs(a - b) for a, b in pairwise(nums)), abs(nums[0] - nums[-1]))
```

#### Java

```java
class Solution {
    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        int ans = Math.abs(nums[0] - nums[n - 1]);
        for (int i = 1; i < n; ++i) {
            ans = Math.max(ans, Math.abs(nums[i] - nums[i - 1]));
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxAdjacentDistance(vector<int>& nums) {
        int ans = abs(nums[0] - nums.back());
        for (int i = 1; i < nums.size(); ++i) {
            ans = max(ans, abs(nums[i] - nums[i - 1]));
        }
        return ans;
    }
};
```

#### Go

```go
func maxAdjacentDistance(nums []int) int {
	ans := abs(nums[0] - nums[len(nums)-1])
	for i := 1; i < len(nums); i++ {
		ans = max(ans, abs(nums[i]-nums[i-1]))
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function maxAdjacentDistance(nums: number[]): number {
    const n = nums.length;
    let ans = Math.abs(nums[0] - nums[n - 1]);
    for (let i = 1; i < n; ++i) {
        ans = Math.max(ans, Math.abs(nums[i] - nums[i - 1]));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
