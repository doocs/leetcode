---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3131.Find%20the%20Integer%20Added%20to%20Array%20I/README_EN.md
rating: 1160
tags:
    - Array
---

# [3131. Find the Integer Added to Array I](https://leetcode.com/problems/find-the-integer-added-to-array-i)

[中文文档](/solution/3100-3199/3131.Find%20the%20Integer%20Added%20to%20Array%20I/README.md)

## Description

<p>You are given two arrays of equal length, <code>nums1</code> and <code>nums2</code>.</p>

<p>Each element in <code>nums1</code> has been increased (or decreased in the case of negative) by an integer, represented by the variable <code>x</code>.</p>

<p>As a result, <code>nums1</code> becomes <strong>equal</strong> to <code>nums2</code>. Two arrays are considered <strong>equal</strong> when they contain the same integers with the same frequencies.</p>

<p>Return the integer <code>x</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">nums1 = [2,6,4], nums2 = [9,7,5]</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The integer added to each element of <code>nums1</code> is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">nums1 = [10], nums2 = [5]</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">-5</span></p>

<p><strong>Explanation:</strong></p>

<p>The integer added to each element of <code>nums1</code> is -5.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">nums1 = [1,1,1,1], nums2 = [1,1,1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io" style="
    font-family: Menlo,sans-serif;
    font-size: 0.85rem;
">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The integer added to each element of <code>nums1</code> is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length == nums2.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
	<li>The test cases are generated in a way that there is an integer <code>x</code> such that <code>nums1</code> can become equal to <code>nums2</code> by adding <code>x</code> to each element of <code>nums1</code>.</li>
</ul>

## Solutions

### Solution 1: Calculate Minimum Difference

We can find the minimum value of each array, then return the difference between the two minimum values.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def addedInteger(self, nums1: List[int], nums2: List[int]) -> int:
        return min(nums2) - min(nums1)
```

```java
class Solution {
    public int addedInteger(int[] nums1, int[] nums2) {
        return Arrays.stream(nums2).min().getAsInt() - Arrays.stream(nums1).min().getAsInt();
    }
}
```

```cpp
class Solution {
public:
    int addedInteger(vector<int>& nums1, vector<int>& nums2) {
        return *min_element(nums2.begin(), nums2.end()) - *min_element(nums1.begin(), nums1.end());
    }
};
```

```go
func addedInteger(nums1 []int, nums2 []int) int {
	return slices.Min(nums2) - slices.Min(nums1)
}
```

```ts
function addedInteger(nums1: number[], nums2: number[]): number {
    return Math.min(...nums2) - Math.min(...nums1);
}
```

<!-- tabs:end -->

<!-- end -->
