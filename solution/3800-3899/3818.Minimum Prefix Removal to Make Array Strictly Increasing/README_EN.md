---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3818.Minimum%20Prefix%20Removal%20to%20Make%20Array%20Strictly%20Increasing/README_EN.md
---

<!-- problem:start -->

# [3818. Minimum Prefix Removal to Make Array Strictly Increasing](https://leetcode.com/problems/minimum-prefix-removal-to-make-array-strictly-increasing)

[中文文档](/solution/3800-3899/3818.Minimum%20Prefix%20Removal%20to%20Make%20Array%20Strictly%20Increasing/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You need to remove <strong>exactly</strong> one prefix (possibly empty) from nums.</p>

<p>Return an integer denoting the <strong>minimum</strong> length of the removed <span data-keyword="array-prefix">prefix</span> such that the remaining array is <strong><span data-keyword="strictly-increasing-array">strictly increasing</span></strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-1,2,3,3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>Removing the <code>prefix = [1, -1, 2, 3]</code> leaves the remaining array <code>[3, 4, 5]</code> which is strictly increasing.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,-2,-5]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Removing the <code>prefix = [4, 3, -2]</code> leaves the remaining array <code>[-5]</code> which is strictly increasing.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The array <code>nums = [1, 2, 3, 4]</code> is already strictly increasing so removing an empty prefix is sufficient.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup>​​​​​​​</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Reverse Traversal

We can traverse the array backwards from the end to find the first position $i$ that does not satisfy the strictly increasing condition, i.e., $nums[i-1] \geq nums[i]$. At this point, the minimum length of the prefix to remove is $i$.

If the entire array is strictly increasing, we do not need to remove any prefix, so we return $0$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumPrefixLength(self, nums: List[int]) -> int:
        for i in range(len(nums) - 1, 0, -1):
            if nums[i - 1] >= nums[i]:
                return i
        return 0
```

#### Java

```java
class Solution {
    public int minimumPrefixLength(int[] nums) {
        for (int i = nums.length - 1; i > 0; --i) {
            if (nums[i - 1] >= nums[i]) {
                return i;
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
    int minimumPrefixLength(vector<int>& nums) {
        for (int i = nums.size() - 1; i; --i) {
            if (nums[i - 1] >= nums[i]) {
                return i;
            }
        }
        return 0;
    }
};
```

#### Go

```go
func minimumPrefixLength(nums []int) int {
	for i := len(nums) - 1; i > 0; i-- {
		if nums[i-1] >= nums[i] {
			return i
		}
	}
	return 0
}
```

#### TypeScript

```ts
function minimumPrefixLength(nums: number[]): number {
    for (let i = nums.length - 1; i; --i) {
        if (nums[i - 1] >= nums[i]) {
            return i;
        }
    }
    return 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
