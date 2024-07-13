---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3190.Find%20Minimum%20Operations%20to%20Make%20All%20Elements%20Divisible%20by%20Three/README_EN.md
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [3190. Find Minimum Operations to Make All Elements Divisible by Three](https://leetcode.com/problems/find-minimum-operations-to-make-all-elements-divisible-by-three)

[中文文档](/solution/3100-3199/3190.Find%20Minimum%20Operations%20to%20Make%20All%20Elements%20Divisible%20by%20Three/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>. In one operation, you can add or subtract 1 from <strong>any</strong> element of <code>nums</code>.</p>

<p>Return the <strong>minimum</strong> number of operations to make all elements of <code>nums</code> divisible by 3.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>All array elements can be made divisible by 3 using 3 operations:</p>

<ul>
	<li>Subtract 1 from 1.</li>
	<li>Add 1 to 2.</li>
	<li>Subtract 1 from 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,6,9]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

We directly iterate through the array $\text{nums}$. For each element $x$, we calculate the remainder of $x$ divided by 3, $x \bmod 3$. If the remainder is not 0, we need to make $x$ divisible by 3 with the minimum number of operations. Therefore, we can choose to either decrease $x$ by $x \bmod 3$ or increase $x$ by $3 - x \bmod 3$, and we accumulate the minimum of these two values to the answer.

The time complexity is $O(n)$, where $n$ is the length of the array $\text{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        ans = 0
        for x in nums:
            if mod := x % 3:
                ans += min(mod, 3 - mod)
        return ans
```

#### Java

```java
class Solution {
    public int minimumOperations(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            int mod = x % 3;
            if (mod != 0) {
                ans += Math.min(mod, 3 - mod);
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
    int minimumOperations(vector<int>& nums) {
        int ans = 0;
        for (int x : nums) {
            int mod = x % 3;
            if (mod) {
                ans += min(mod, 3 - mod);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumOperations(nums []int) (ans int) {
	for _, x := range nums {
		if mod := x % 3; mod > 0 {
			ans += min(mod, 3-mod)
		}
	}
	return
}
```

#### TypeScript

```ts
function minimumOperations(nums: number[]): number {
    let ans = 0;
    for (const x of nums) {
        const mod = x % 3;
        if (mod) {
            ans += Math.min(mod, 3 - mod);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
