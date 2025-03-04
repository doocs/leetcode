---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3151.Special%20Array%20I/README_EN.md
rating: 1152
source: Weekly Contest 398 Q1
tags:
    - Array
---

<!-- problem:start -->

# [3151. Special Array I](https://leetcode.com/problems/special-array-i)

[中文文档](/solution/3100-3199/3151.Special%20Array%20I/README.md)

## Description

<!-- description:start -->

<p>An array is considered <strong>special</strong> if the <em>parity</em> of every pair of adjacent elements is different. In other words, one element in each pair <strong>must</strong> be even, and the other <strong>must</strong> be odd.</p>

<p>You are given an array of integers <code>nums</code>. Return <code>true</code> if <code>nums</code> is a <strong>special</strong> array, otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>There is only one element. So the answer is <code>true</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>There is only two pairs: <code>(2,1)</code> and <code>(1,4)</code>, and both of them contain numbers with different parity. So the answer is <code>true</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,1,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p><code>nums[1]</code> and <code>nums[2]</code> are both odd. So the answer is <code>false</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We traverse the array from left to right. For each pair of adjacent elements, if their parity is the same, then the array is not a special array, return `false`; otherwise, the array is a special array, return `true`.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isArraySpecial(self, nums: List[int]) -> bool:
        return all(a % 2 != b % 2 for a, b in pairwise(nums))
```

#### Java

```java
class Solution {
    public boolean isArraySpecial(int[] nums) {
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                return false;
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isArraySpecial(vector<int>& nums) {
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                return false;
            }
        }
        return true;
    }
};
```

#### Go

```go
func isArraySpecial(nums []int) bool {
	for i, x := range nums[1:] {
		if x%2 == nums[i]%2 {
			return false
		}
	}
	return true
}
```

#### TypeScript

```ts
function isArraySpecial(nums: number[]): boolean {
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] % 2 === nums[i - 1] % 2) {
            return false;
        }
    }
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
