---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3467.Transform%20Array%20by%20Parity/README_EN.md
rating: 1165
source: Biweekly Contest 151 Q1
tags:
    - Array
    - Counting
    - Sorting
---

<!-- problem:start -->

# [3467. Transform Array by Parity](https://leetcode.com/problems/transform-array-by-parity)

[中文文档](/solution/3400-3499/3467.Transform%20Array%20by%20Parity/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. Transform <code>nums</code> by performing the following operations in the <strong>exact</strong> order specified:</p>

<ol>
	<li>Replace each even number with 0.</li>
	<li>Replace each odd numbers with 1.</li>
	<li>Sort the modified array in <strong>non-decreasing</strong> order.</li>
</ol>

<p>Return the resulting array after performing these operations.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,0,1,1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Replace the even numbers (4 and 2) with 0 and the odd numbers (3 and 1) with 1. Now, <code>nums = [0, 1, 0, 1]</code>.</li>
	<li>After sorting <code>nums</code> in non-descending order, <code>nums = [0, 0, 1, 1]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,5,1,4,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,0,1,1,1]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Replace the even numbers (4 and 2) with 0 and the odd numbers (1, 5 and 1) with 1. Now, <code>nums = [1, 1, 1, 0, 0]</code>.</li>
	<li>After sorting <code>nums</code> in non-descending order, <code>nums = [0, 0, 1, 1, 1]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can traverse the array $\textit{nums}$ and count the number of even elements $\textit{even}$. Then, we set the first $\textit{even}$ elements of the array to $0$ and the remaining elements to $1$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def transformArray(self, nums: List[int]) -> List[int]:
        even = sum(x % 2 == 0 for x in nums)
        for i in range(even):
            nums[i] = 0
        for i in range(even, len(nums)):
            nums[i] = 1
        return nums
```

#### Java

```java
class Solution {
    public int[] transformArray(int[] nums) {
        int even = 0;
        for (int x : nums) {
            even += (x & 1 ^ 1);
        }
        for (int i = 0; i < even; ++i) {
            nums[i] = 0;
        }
        for (int i = even; i < nums.length; ++i) {
            nums[i] = 1;
        }
        return nums;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> transformArray(vector<int>& nums) {
        int even = 0;
        for (int x : nums) {
            even += (x & 1 ^ 1);
        }
        for (int i = 0; i < even; ++i) {
            nums[i] = 0;
        }
        for (int i = even; i < nums.size(); ++i) {
            nums[i] = 1;
        }
        return nums;
    }
};
```

#### Go

```go
func transformArray(nums []int) []int {
	even := 0
	for _, x := range nums {
		even += x&1 ^ 1
	}
	for i := 0; i < even; i++ {
		nums[i] = 0
	}
	for i := even; i < len(nums); i++ {
		nums[i] = 1
	}
	return nums
}
```

#### TypeScript

```ts
function transformArray(nums: number[]): number[] {
    const even = nums.filter(x => x % 2 === 0).length;
    for (let i = 0; i < even; ++i) {
        nums[i] = 0;
    }
    for (let i = even; i < nums.length; ++i) {
        nums[i] = 1;
    }
    return nums;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
