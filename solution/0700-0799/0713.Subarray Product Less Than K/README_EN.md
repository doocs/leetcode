---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0713.Subarray%20Product%20Less%20Than%20K/README_EN.md
tags:
    - Array
    - Binary Search
    - Prefix Sum
    - Sliding Window
---

<!-- problem:start -->

# [713. Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k)

[中文文档](/solution/0700-0799/0713.Subarray%20Product%20Less%20Than%20K/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code> and an integer <code>k</code>, return <em>the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than </em><code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,5,2,6], k = 100
<strong>Output:</strong> 8
<strong>Explanation:</strong> The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], k = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Two Pointers

We can use two pointers to maintain a sliding window, where the product of all elements in the window is less than $k$.

Define two pointers $l$ and $r$ pointing to the left and right boundaries of the sliding window, initially $l = r = 0$. We use a variable $p$ to record the product of all elements in the window, initially $p = 1$.

Each time, we move $r$ one step to the right, adding the element $x$ pointed to by $r$ to the window, and update $p = p \times x$. Then, if $p \geq k$, we move $l$ one step to the right in a loop and update $p = p \div \text{nums}[l]$ until $p < k$ or $l \gt r$. Thus, the number of contiguous subarrays ending at $r$ with a product less than $k$ is $r - l + 1$. We then add this number to the answer and continue moving $r$ until $r$ reaches the end of the array.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numSubarrayProductLessThanK(self, nums: List[int], k: int) -> int:
        ans = l = 0
        p = 1
        for r, x in enumerate(nums):
            p *= x
            while l <= r and p >= k:
                p //= nums[l]
                l += 1
            ans += r - l + 1
        return ans
```

#### Java

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0, l = 0;
        int p = 1;
        for (int r = 0; r < nums.length; ++r) {
            p *= nums[r];
            while (l <= r && p >= k) {
                p /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        int ans = 0, l = 0;
        int p = 1;
        for (int r = 0; r < nums.size(); ++r) {
            p *= nums[r];
            while (l <= r && p >= k) {
                p /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }
};
```

#### Go

```go
func numSubarrayProductLessThanK(nums []int, k int) (ans int) {
    l, p := 0, 1
    for r, x := range nums {
        p *= x
        for l <= r && p >= k {
            p /= nums[l]
            l++
        }
        ans += r - l + 1
    }
    return
}
```

#### TypeScript

```ts
function numSubarrayProductLessThanK(nums: number[], k: number): number {
    const n = nums.length;
    let [ans, l, p] = [0, 0, 1];
    for (let r = 0; r < n; ++r) {
        p *= nums[r];
        while (l <= r && p >= k) {
            p /= nums[l++];
        }
        ans += r - l + 1;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn num_subarray_product_less_than_k(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = 0;
        let mut l = 0;
        let mut p = 1;

        for (r, &x) in nums.iter().enumerate() {
            p *= x;
            while l <= r && p >= k {
                p /= nums[l];
                l += 1;
            }
            ans += (r - l + 1) as i32;
        }

        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var numSubarrayProductLessThanK = function (nums, k) {
    const n = nums.length;
    let [ans, l, p] = [0, 0, 1];
    for (let r = 0; r < n; ++r) {
        p *= nums[r];
        while (l <= r && p >= k) {
            p /= nums[l++];
        }
        ans += r - l + 1;
    }
    return ans;
};
```

#### Kotlin

```kotlin
class Solution {
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        var ans = 0
        var l = 0
        var p = 1

        for (r in nums.indices) {
            p *= nums[r]
            while (l <= r && p >= k) {
                p /= nums[l]
                l++
            }
            ans += r - l + 1
        }

        return ans
    }
}
```

#### C#

```cs
public class Solution {
    public int NumSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0, l = 0;
        int p = 1;
        for (int r = 0; r < nums.Length; ++r) {
            p *= nums[r];
            while (l <= r && p >= k) {
                p /= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
