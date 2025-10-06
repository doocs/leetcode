---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3688.Bitwise%20OR%20of%20Even%20Numbers%20in%20an%20Array/README_EN.md
rating: 1204
source: Weekly Contest 468 Q1
tags:
    - Bit Manipulation
    - Array
    - Simulation
---

<!-- problem:start -->

# [3688. Bitwise OR of Even Numbers in an Array](https://leetcode.com/problems/bitwise-or-of-even-numbers-in-an-array)

[中文文档](/solution/3600-3699/3688.Bitwise%20OR%20of%20Even%20Numbers%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Return the bitwise <strong>OR</strong> of all <strong>even</strong> numbers in the array.</p>

<p>If there are no even numbers in <code>nums</code>, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p>The even numbers are 2, 4, and 6. Their bitwise OR equals 6.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,9,11]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no even numbers, so the result is 0.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,8,16]</span></p>

<p><strong>Output:</strong> <span class="example-io">24</span></p>

<p><strong>Explanation:</strong></p>

<p>The even numbers are 8 and 16. Their bitwise OR equals 24.</p>
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

### Solution 1: Simulation

We define a variable $\textit{ans}$ with an initial value of 0. Then, we iterate through each element $x$ in the array $\textit{nums}$; if $x$ is even, we update $\textit{ans}$ with the bitwise OR of $\textit{ans}$ and $x$.

Finally, we return $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def evenNumberBitwiseORs(self, nums: List[int]) -> int:
        return reduce(or_, (x for x in nums if x % 2 == 0), 0)
```

#### Java

```java
class Solution {
    public int evenNumberBitwiseORs(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                ans |= x;
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
    int evenNumberBitwiseORs(vector<int>& nums) {
        int ans = 0;
        for (int x : nums) {
            if (x % 2 == 0) {
                ans |= x;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func evenNumberBitwiseORs(nums []int) (ans int) {
	for _, x := range nums {
		if x%2 == 0 {
			ans |= x
		}
	}
	return
}
```

#### TypeScript

```ts
function evenNumberBitwiseORs(nums: number[]): number {
    return nums.reduce((ans, x) => (x % 2 === 0 ? ans | x : ans), 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
