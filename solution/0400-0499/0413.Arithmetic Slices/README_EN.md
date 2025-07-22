---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0413.Arithmetic%20Slices/README_EN.md
tags:
    - Array
    - Dynamic Programming
    - Sliding Window
---

<!-- problem:start -->

# [413. Arithmetic Slices](https://leetcode.com/problems/arithmetic-slices)

[中文文档](/solution/0400-0499/0413.Arithmetic%20Slices/README.md)

## Description

<!-- description:start -->

<p>An integer array is called arithmetic if it consists of <strong>at least three elements</strong> and if the difference between any two consecutive elements is the same.</p>

<ul>
	<li>For example, <code>[1,3,5,7,9]</code>, <code>[7,7,7,7]</code>, and <code>[3,-1,-5,-9]</code> are arithmetic sequences.</li>
</ul>

<p>Given an integer array <code>nums</code>, return <em>the number of arithmetic <strong>subarrays</strong> of</em> <code>nums</code>.</p>

<p>A <strong>subarray</strong> is a contiguous subsequence of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Iteration and Counting

We use $d$ to represent the current difference between two adjacent elements, and $cnt$ to represent the length of the current arithmetic sequence. Initially, $d = 3000$, $cnt = 2$.

We iterate through the array `nums`. For two adjacent elements $a$ and $b$, if $b - a = d$, it means that the current element $b$ also belongs to the current arithmetic sequence, and we increment $cnt$ by 1. Otherwise, it means that the current element $b$ does not belong to the current arithmetic sequence, and we update $d = b - a$, and $cnt = 2$. If $cnt \ge 3$, it means that the length of the current arithmetic sequence is at least 3, and the number of arithmetic sequences is $cnt - 2$, which we add to the answer.

After the iteration, we can get the answer.

In the code implementation, we can also initialize $cnt$ to $0$, and when resetting $cnt$, we directly set $cnt$ to $0$. When adding to the answer, we directly add $cnt$.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Where $n$ is the length of the array `nums`.

Similar problems:

-   [1513. Number of Substrings With Only 1s](https://github.com/doocs/leetcode/blob/main/solution/1500-1599/1513.Number%20of%20Substrings%20With%20Only%201s/README_EN.md)
-   [2348. Number of Zero-Filled Subarrays](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2348.Number%20of%20Zero-Filled%20Subarrays/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfArithmeticSlices(self, nums: List[int]) -> int:
        ans = cnt = 0
        d = 3000
        for a, b in pairwise(nums):
            if b - a == d:
                cnt += 1
            else:
                d = b - a
                cnt = 0
            ans += cnt
        return ans
```

#### Java

```java
class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int ans = 0, cnt = 0;
        int d = 3000;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i + 1] - nums[i] == d) {
                ++cnt;
            } else {
                d = nums[i + 1] - nums[i];
                cnt = 0;
            }
            ans += cnt;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int ans = 0, cnt = 0;
        int d = 3000;
        for (int i = 0; i < nums.size() - 1; ++i) {
            if (nums[i + 1] - nums[i] == d) {
                ++cnt;
            } else {
                d = nums[i + 1] - nums[i];
                cnt = 0;
            }
            ans += cnt;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfArithmeticSlices(nums []int) (ans int) {
	cnt, d := 0, 3000
	for i, b := range nums[1:] {
		a := nums[i]
		if b-a == d {
			cnt++
		} else {
			d = b - a
			cnt = 0
		}
		ans += cnt
	}
	return
}
```

#### TypeScript

```ts
function numberOfArithmeticSlices(nums: number[]): number {
    let ans = 0;
    let cnt = 0;
    let d = 3000;
    for (let i = 0; i < nums.length - 1; ++i) {
        const a = nums[i];
        const b = nums[i + 1];
        if (b - a == d) {
            ++cnt;
        } else {
            d = b - a;
            cnt = 0;
        }
        ans += cnt;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
