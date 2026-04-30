---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3914.Minimum%20Operations%20to%20Make%20Array%20Non%20Decreasing/README_EN.md
---

<!-- problem:start -->

# [3914. Minimum Operations to Make Array Non Decreasing](https://leetcode.com/problems/minimum-operations-to-make-array-non-decreasing)

[中文文档](/solution/3900-3999/3914.Minimum%20Operations%20to%20Make%20Array%20Non%20Decreasing/README.md)

## Description

<!-- description:start -->

<p data-end="140" data-start="88">You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>In one operation, you may choose any <strong><span data-keyword="subarray-nonempty">subarray</span></strong> <code>nums[l..r]</code> and <strong>increase</strong> each element in that <strong>subarray</strong> by <code>x</code>, where <code>x</code> is any <strong>positive</strong> integer.</p>

<p>Return the <strong>minimum</strong> possible <strong>sum</strong> of the values of <code>x</code> across all operations required to make the array <strong>non-decreasing</strong>.</p>

<p>An array is <strong>non-decreasing</strong> if <code>nums[i] &lt;= nums[i + 1]</code> for all <code>0 &lt;= i &lt; n - 1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal set of operations:</p>

<ul>
	<li>Choose subarray <code>[2..3]</code> and add <code>x = 1</code> resulting in <code>[3, 3, 3, 2]</code></li>
	<li>Choose subarray <code>[3..3]</code> and add <code>x = 1</code> resulting in <code>[3, 3, 3, 3]</code></li>
</ul>

<p>The array becomes non-decreasing, and the total sum of chosen <code>x</code> values is <code>1 + 1 = 2</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal set of operations:</p>

<ul>
	<li>Choose subarray <code>[1..3]</code> and add <code>x = 4</code> resulting in <code>[5, 5, 6, 7]</code></li>
</ul>

<p>The array becomes non-decreasing, and the total sum of chosen <code>x</code> values is <code>4</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

We can traverse the array from left to right and calculate the difference between each pair of adjacent elements. If the current element is smaller than the previous one, we need to increase the current element so that it is at least equal to the previous element. The amount to increase is the difference between the previous element and the current element.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: list[int]) -> int:
        return sum(max(a - b, 0) for a, b in pairwise(nums))
```

#### Java

```java
class Solution {
    public long minOperations(int[] nums) {
        long ans = 0;
        for (int i = 1; i < nums.length; ++i) {
            ans += Math.max(nums[i - 1] - nums[i], 0);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minOperations(vector<int>& nums) {
        long long ans = 0;
        for (int i = 1; i < nums.size(); ++i) {
            ans += max(nums[i - 1] - nums[i], 0);
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int) (ans int64) {
	for i := 1; i < len(nums); i++ {
		ans += max(int64(nums[i-1]-nums[i]), 0)
	}
	return
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    let ans = 0;
    for (let i = 1; i < nums.length; ++i) {
        ans += Math.max(nums[i - 1] - nums[i], 0);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
