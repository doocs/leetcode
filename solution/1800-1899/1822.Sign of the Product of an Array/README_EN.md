---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1822.Sign%20of%20the%20Product%20of%20an%20Array/README_EN.md
rating: 1209
source: Weekly Contest 236 Q1
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [1822. Sign of the Product of an Array](https://leetcode.com/problems/sign-of-the-product-of-an-array)

[中文文档](/solution/1800-1899/1822.Sign%20of%20the%20Product%20of%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>Implement a function <code>signFunc(x)</code> that returns:</p>

<ul>
	<li><code>1</code> if <code>x</code> is positive.</li>
	<li><code>-1</code> if <code>x</code> is negative.</li>
	<li><code>0</code> if <code>x</code> is equal to <code>0</code>.</li>
</ul>

<p>You are given an integer array <code>nums</code>. Let <code>product</code> be the product of all values in the array <code>nums</code>.</p>

<p>Return <code>signFunc(product)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,-2,-3,-4,3,2,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The product of all values in the array is 144, and signFunc(144) = 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,0,2,-3]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The product of all values in the array is 0, and signFunc(0) = 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,1,-1,1,-1]
<strong>Output:</strong> -1
<strong>Explanation:</strong> The product of all values in the array is -1, and signFunc(-1) = -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Direct Traversal

The problem requires us to return the sign of the product of the array elements, i.e., return $1$ for positive numbers, $-1$ for negative numbers, and $0$ if it equals $0$.

We can define an answer variable `ans`, initially set to $1$.

Then we traverse each element $v$ in the array. If $v$ is a negative number, we multiply `ans` by $-1$. If $v$ is $0$, we return $0$ in advance.

After the traversal is over, we return `ans`.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def arraySign(self, nums: List[int]) -> int:
        ans = 1
        for v in nums:
            if v == 0:
                return 0
            if v < 0:
                ans *= -1
        return ans
```

#### Java

```java
class Solution {
    public int arraySign(int[] nums) {
        int ans = 1;
        for (int v : nums) {
            if (v == 0) {
                return 0;
            }
            if (v < 0) {
                ans *= -1;
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
    int arraySign(vector<int>& nums) {
        int ans = 1;
        for (int v : nums) {
            if (!v) return 0;
            if (v < 0) ans *= -1;
        }
        return ans;
    }
};
```

#### Go

```go
func arraySign(nums []int) int {
	ans := 1
	for _, v := range nums {
		if v == 0 {
			return 0
		}
		if v < 0 {
			ans *= -1
		}
	}
	return ans
}
```

#### Rust

```rust
impl Solution {
    pub fn array_sign(nums: Vec<i32>) -> i32 {
        let mut ans = 1;
        for &num in nums.iter() {
            if num == 0 {
                return 0;
            }
            if num < 0 {
                ans *= -1;
            }
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var arraySign = function (nums) {
    let ans = 1;
    for (const v of nums) {
        if (!v) {
            return 0;
        }
        if (v < 0) {
            ans *= -1;
        }
    }
    return ans;
};
```

#### C

```c
int arraySign(int* nums, int numsSize) {
    int ans = 1;
    for (int i = 0; i < numsSize; i++) {
        if (nums[i] == 0) {
            return 0;
        }
        if (nums[i] < 0) {
            ans *= -1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
