---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0747.Largest%20Number%20At%20Least%20Twice%20of%20Others/README_EN.md
tags:
    - Array
    - Sorting
---

<!-- problem:start -->

# [747. Largest Number At Least Twice of Others](https://leetcode.com/problems/largest-number-at-least-twice-of-others)

[中文文档](/solution/0700-0799/0747.Largest%20Number%20At%20Least%20Twice%20of%20Others/README.md)

## Description

<p>You are given an integer array <code>nums</code> where the largest integer is <strong>unique</strong>.</p>

<p>Determine whether the largest element in the array is <strong>at least twice</strong> as much as every other number in the array. If it is, return <em>the <strong>index</strong> of the largest element, or return </em><code>-1</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,6,1,0]
<strong>Output:</strong> 1
<strong>Explanation:</strong> 6 is the largest integer.
For every other number in the array x, 6 is at least twice as big as x.
The index of value 6 is 1, so we return 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4]
<strong>Output:</strong> -1
<strong>Explanation:</strong> 4 is less than twice the value of 3, so we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 50</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
	<li>The largest element in <code>nums</code> is unique.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Traversal

We can traverse the array $nums$ to find the maximum value $x$ and the second largest value $y$ in the array. If $x \ge 2y$, then return the index of $x$, otherwise return $-1$.

We can also first find the maximum value $x$ in the array and find the index $k$ of the maximum value $x$ at the same time. Then traverse the array again. If we find an element $y$ outside of $k$ that satisfies $x < 2y$, then return $-1$. Otherwise, return $k$ after the traversal ends.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def dominantIndex(self, nums: List[int]) -> int:
        x, y = nlargest(2, nums)
        return nums.index(x) if x >= 2 * y else -1
```

```java
class Solution {
    public int dominantIndex(int[] nums) {
        int n = nums.length;
        int k = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[k] < nums[i]) {
                k = i;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (k != i && nums[k] < nums[i] * 2) {
                return -1;
            }
        }
        return k;
    }
}
```

```cpp
class Solution {
public:
    int dominantIndex(vector<int>& nums) {
        int n = nums.size();
        int k = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[k] < nums[i]) {
                k = i;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (k != i && nums[k] < nums[i] * 2) {
                return -1;
            }
        }
        return k;
    }
};
```

```go
func dominantIndex(nums []int) int {
	k := 0
	for i, x := range nums {
		if nums[k] < x {
			k = i
		}
	}
	for i, x := range nums {
		if k != i && nums[k] < x*2 {
			return -1
		}
	}
	return k
}
```

```ts
function dominantIndex(nums: number[]): number {
    let k = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] > nums[k]) {
            k = i;
        }
    }
    for (let i = 0; i < nums.length; ++i) {
        if (i !== k && nums[k] < nums[i] * 2) {
            return -1;
        }
    }
    return k;
}
```

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var dominantIndex = function (nums) {
    let k = 0;
    for (let i = 0; i < nums.length; ++i) {
        if (nums[i] > nums[k]) {
            k = i;
        }
    }
    for (let i = 0; i < nums.length; ++i) {
        if (i !== k && nums[k] < nums[i] * 2) {
            return -1;
        }
    }
    return k;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
