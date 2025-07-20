---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3550.Smallest%20Index%20With%20Digit%20Sum%20Equal%20to%20Index/README_EN.md
rating: 1200
source: Weekly Contest 450 Q1
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [3550. Smallest Index With Digit Sum Equal to Index](https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index)

[中文文档](/solution/3500-3599/3550.Smallest%20Index%20With%20Digit%20Sum%20Equal%20to%20Index/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Return the <strong>smallest</strong> index <code>i</code> such that the sum of the digits of <code>nums[i]</code> is equal to <code>i</code>.</p>

<p>If no such index exists, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>nums[2] = 2</code>, the sum of digits is 2, which is equal to index <code>i = 2</code>. Thus, the output is 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,10,11]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>nums[1] = 10</code>, the sum of digits is <code>1 + 0 = 1</code>, which is equal to index <code>i = 1</code>.</li>
	<li>For <code>nums[2] = 11</code>, the sum of digits is <code>1 + 1 = 2</code>, which is equal to index <code>i = 2</code>.</li>
	<li>Since index 1 is the smallest, the output is 1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Since no index satisfies the condition, the output is -1.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Digit Sum

We can start from index $i = 0$ and iterate through each element $x$ in the array, calculating the digit sum $s$ of $x$. If $s = i$, return the index $i$. If no such index is found after traversing all elements, return -1.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$, as only constant extra space is used.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestIndex(self, nums: List[int]) -> int:
        for i, x in enumerate(nums):
            s = 0
            while x:
                s += x % 10
                x //= 10
            if s == i:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int smallestIndex(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            int s = 0;
            while (nums[i] != 0) {
                s += nums[i] % 10;
                nums[i] /= 10;
            }
            if (s == i) {
                return i;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestIndex(vector<int>& nums) {
        for (int i = 0; i < nums.size(); ++i) {
            int s = 0;
            while (nums[i]) {
                s += nums[i] % 10;
                nums[i] /= 10;
            }
            if (s == i) {
                return i;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func smallestIndex(nums []int) int {
	for i, x := range nums {
		s := 0
		for ; x > 0; x /= 10 {
			s += x % 10
		}
		if s == i {
			return i
		}
	}
	return -1
}
```

#### TypeScript

```ts
function smallestIndex(nums: number[]): number {
    for (let i = 0; i < nums.length; ++i) {
        let s = 0;
        for (; nums[i] > 0; nums[i] = Math.floor(nums[i] / 10)) {
            s += nums[i] % 10;
        }
        if (s === i) {
            return i;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
