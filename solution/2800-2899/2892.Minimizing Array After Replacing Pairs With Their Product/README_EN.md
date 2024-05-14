---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2892.Minimizing%20Array%20After%20Replacing%20Pairs%20With%20Their%20Product/README_EN.md
tags:
    - Greedy
    - Array
    - Dynamic Programming
---

# [2892. Minimizing Array After Replacing Pairs With Their Product 🔒](https://leetcode.com/problems/minimizing-array-after-replacing-pairs-with-their-product)

[中文文档](/solution/2800-2899/2892.Minimizing%20Array%20After%20Replacing%20Pairs%20With%20Their%20Product/README.md)

## Description

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, you can perform the following operation on the array any number of times:</p>

<ul>
	<li>Select two <strong>adjacent</strong> elements of the array like <code>x</code> and <code>y</code>, such that <code>x * y &lt;= k</code>, and replace both of them with a <strong>single element</strong> with value <code>x * y</code> (e.g. in one operation the array <code>[1, 2, 2, 3]</code> with <code>k = 5</code> can become <code>[1, 4, 3]</code> or <code>[2, 2, 3]</code>, but can&#39;t become <code>[1, 2, 6]</code>).</li>
</ul>

<p>Return <em>the <strong>minimum</strong> possible length of </em><code>nums</code><em> after any number of operations</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,3,7,3,5], k = 20
<strong>Output:</strong> 3
<strong>Explanation:</strong> We perform these operations:
1. [<u>2,3</u>,3,7,3,5] -&gt; [<u>6</u>,3,7,3,5]
2. [<u>6,3</u>,7,3,5] -&gt; [<u>18</u>,7,3,5]
3. [18,7,<u>3,5</u>] -&gt; [18,7,<u>15</u>]
It can be shown that 3 is the minimum length possible to achieve with the given operation.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,3,3,3], k = 6
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can&#39;t perform any operations since the product of every two adjacent elements is greater than 6.
Hence, the answer is 4.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Greedy

We use a variable $ans$ to record the current length of the array, and a variable $y$ to record the current product of the array. Initially, $ans = 1$ and $y = nums[0]$.

We start traversing from the second element of the array. Let the current element be $x$:

-   If $x = 0$, then the product of the entire array is $0 \le k$, so the minimum length of the answer array is $1$, and we can return directly.
-   If $x \times y \le k$, then we can merge $x$ and $y$, that is, $y = x \times y$.
-   If $x \times y \gt k$, then we cannot merge $x$ and $y$, so we need to treat $x$ as a separate element, that is, $ans = ans + 1$, and $y = x$.

The final answer is $ans$.

The time complexity is $O(n)$, where n is the length of the array. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def minArrayLength(self, nums: List[int], k: int) -> int:
        ans, y = 1, nums[0]
        for x in nums[1:]:
            if x == 0:
                return 1
            if x * y <= k:
                y *= x
            else:
                y = x
                ans += 1
        return ans
```

```java
class Solution {
    public int minArrayLength(int[] nums, int k) {
        int ans = 1;
        long y = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int x = nums[i];
            if (x == 0) {
                return 1;
            }
            if (x * y <= k) {
                y *= x;
            } else {
                y = x;
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int minArrayLength(vector<int>& nums, int k) {
        int ans = 1;
        long long y = nums[0];
        for (int i = 1; i < nums.size(); ++i) {
            int x = nums[i];
            if (x == 0) {
                return 1;
            }
            if (x * y <= k) {
                y *= x;
            } else {
                y = x;
                ++ans;
            }
        }
        return ans;
    }
};
```

```go
func minArrayLength(nums []int, k int) int {
	ans, y := 1, nums[0]
	for _, x := range nums[1:] {
		if x == 0 {
			return 1
		}
		if x*y <= k {
			y *= x
		} else {
			y = x
			ans++
		}
	}
	return ans
}
```

```ts
function minArrayLength(nums: number[], k: number): number {
    let [ans, y] = [1, nums[0]];
    for (const x of nums.slice(1)) {
        if (x === 0) {
            return 1;
        }
        if (x * y <= k) {
            y *= x;
        } else {
            y = x;
            ++ans;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
