---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3674.Minimum%20Operations%20to%20Equalize%20Array/README_EN.md
rating: 1369
source: Weekly Contest 466 Q1
---

<!-- problem:start -->

# [3674. Minimum Operations to Equalize Array](https://leetcode.com/problems/minimum-operations-to-equalize-array)

[中文文档](/solution/3600-3699/3674.Minimum%20Operations%20to%20Equalize%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>In one operation, choose any subarray <code>nums[l...r]</code> (<code>0 &lt;= l &lt;= r &lt; n</code>) and <strong>replace</strong> each element in that subarray with the <strong>bitwise AND</strong> of all elements.</p>

<p>Return the <strong>minimum</strong> number of operations required to make all elements of <code>nums</code> equal.</p>
A <strong>subarray</strong> is a contiguous <b>non-empty</b> sequence of elements within an array.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>Choose <code>nums[0...1]</code>: <code>(1 AND 2) = 0</code>, so the array becomes <code>[0, 0]</code> and all elements are equal in 1 operation.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,5,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p><code>nums</code> is <code>[5, 5, 5]</code> which already has all elements equal, so 0 operations are required.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

If all elements in $\textit{nums}$ are equal, no operations are needed; otherwise, we can select the entire array as a subarray and perform one operation.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        return int(any(x != nums[0] for x in nums))
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        for (int x : nums) {
            if (x != nums[0]) {
                return 1;
            }
        }
        return 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        for (int x : nums) {
            if (x != nums[0]) {
                return 1;
            }
        }
        return 0;
    }
};
```

#### Go

```go
func minOperations(nums []int) int {
	for _, x := range nums {
		if x != nums[0] {
			return 1
		}
	}
	return 0
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    for (const x of nums) {
        if (x !== nums[0]) {
            return 1;
        }
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
