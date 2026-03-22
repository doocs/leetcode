---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3875.Construct%20Uniform%20Parity%20Array%20I/README_EN.md
---

<!-- problem:start -->

# [3875. Construct Uniform Parity Array I](https://leetcode.com/problems/construct-uniform-parity-array-i)

[中文文档](/solution/3800-3899/3875.Construct%20Uniform%20Parity%20Array%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums1</code> of <code>n</code> <strong>distinct</strong> integers.</p>

<p>You want to construct another array <code>nums2</code> of length <code>n</code> such that the elements in <code>nums2</code> are either <strong>all odd or all even</strong>.</p>

<p>For each index <code>i</code>, you must choose <strong>exactly one</strong> of the following (in any order):</p>

<ul>
	<li><code>nums2[i] = nums1[i]</code></li>
	<li><code>nums2[i] = nums1[i] - nums1[j]</code>, for an index <code>j != i</code></li>
</ul>

<p>Return <code>true</code> if it is possible to construct such an array, otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Choose <code>nums2[0] = nums1[0] - nums1[1] = 2 - 3 = -1</code>.</li>
	<li>Choose <code>nums2[1] = nums1[1] = 3</code>.</li>
	<li><code>nums2 = [-1, 3]</code>, and both elements are odd. Thus, the answer is <code>true</code>​​​​​​​.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums1 = [4,6]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Choose <code>nums2[0] = nums1[0] = 4</code>.</li>
	<li>Choose <code>nums2[1] = nums1[1] = 6</code>.</li>
	<li><code>nums2 = [4, 6]</code>, and all elements are even. Thus, the answer is <code>true</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums1.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums1[i] &lt;= 100</code></li>
	<li><code>nums1</code> consists of distinct integers.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brain Teaser

If all elements in $\textit{nums1}$ are either all odd or all even, we can directly set $\textit{nums2}$ equal to $\textit{nums1}$, which satisfies the condition.

If $\textit{nums1}$ contains both odd and even numbers, we can set each element of $\textit{nums2}$ to the current element of $\textit{nums1}$ minus some element in $\textit{nums1}$ with different parity. Since odd minus even and even minus odd both yield an odd number, all elements of $\textit{nums2}$ will be odd, satisfying the condition.

Therefore, regardless of whether the elements in $\textit{nums1}$ are all odd, all even, or a mix of both, we can always construct a valid $\textit{nums2}$. Thus the answer is always $\text{true}$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniformArray(self, nums1: list[int]) -> bool:
        return True
```

#### Java

```java
class Solution {
    public boolean uniformArray(int[] nums1) {
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool uniformArray(vector<int>& nums1) {
        return true;
    }
};
```

#### Go

```go
func uniformArray(nums1 []int) bool {
	return true
}
```

#### TypeScript

```ts
function uniformArray(nums1: number[]): boolean {
    return true;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
