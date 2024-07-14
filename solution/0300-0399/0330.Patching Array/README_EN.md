---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0330.Patching%20Array/README_EN.md
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [330. Patching Array](https://leetcode.com/problems/patching-array)

[中文文档](/solution/0300-0399/0330.Patching%20Array/README.md)

## Description

<!-- description:start -->

<p>Given a sorted integer array <code>nums</code> and an integer <code>n</code>, add/patch elements to the array such that any number in the range <code>[1, n]</code> inclusive can be formed by the sum of some elements in the array.</p>

<p>Return <em>the minimum number of patches required</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3], n = 6
<strong>Output:</strong> 1
Explanation:
Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,10], n = 20
<strong>Output:</strong> 2
Explanation: The two patches can be [2, 4].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2], n = 5
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> is sorted in <strong>ascending order</strong>.</li>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

Let's assume that the number $x$ is the smallest positive integer that cannot be represented. Then all the numbers in $[1,..x-1]$ can be represented. In order to represent the number $x$, we need to add a number that is less than or equal to $x$:

-   If the added number equals $x$, since all numbers in $[1,..x-1]$ can be represented, after adding $x$, all numbers in the range $[1,..2x-1]$ can be represented, and the smallest positive integer that cannot be represented becomes $2x$.
-   If the added number is less than $x$, let's assume it's $x'$, since all numbers in $[1,..x-1]$ can be represented, after adding $x'$, all numbers in the range $[1,..x+x'-1]$ can be represented, and the smallest positive integer that cannot be represented becomes $x+x' \lt 2x$.

Therefore, we should greedily add the number $x$ to cover a larger range.

We use a variable $x$ to record the current smallest positive integer that cannot be represented, initialized to $1$. At this time, $[1,..x-1]$ is empty, indicating that no number can be covered; we use a variable $i$ to record the current index of the array being traversed.

We perform the following operations in a loop:

-   If $i$ is within the range of the array and $nums[i] \le x$, it means that the current number can be covered, so we add the value of $nums[i]$ to $x$, and increment $i$ by $1$.
-   Otherwise, it means that $x$ is not covered, so we need to supplement a number $x$ in the array, and then update $x$ to $2x$.
-   Repeat the above operations until the value of $x$ is greater than $n$.

The final answer is the number of supplemented numbers.

The time complexity is $O(m + \log n)$, where $m$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minPatches(self, nums: List[int], n: int) -> int:
        x = 1
        ans = i = 0
        while x <= n:
            if i < len(nums) and nums[i] <= x:
                x += nums[i]
                i += 1
            else:
                ans += 1
                x <<= 1
        return ans
```

#### Java

```java
class Solution {
    public int minPatches(int[] nums, int n) {
        long x = 1;
        int ans = 0;
        for (int i = 0; x <= n;) {
            if (i < nums.length && nums[i] <= x) {
                x += nums[i++];
            } else {
                ++ans;
                x <<= 1;
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
    int minPatches(vector<int>& nums, int n) {
        long long x = 1;
        int ans = 0;
        for (int i = 0; x <= n;) {
            if (i < nums.size() && nums[i] <= x) {
                x += nums[i++];
            } else {
                ++ans;
                x <<= 1;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minPatches(nums []int, n int) (ans int) {
	x := 1
	for i := 0; x <= n; {
		if i < len(nums) && nums[i] <= x {
			x += nums[i]
			i++
		} else {
			ans++
			x <<= 1
		}
	}
	return
}
```

#### TypeScript

```ts
function minPatches(nums: number[], n: number): number {
    let x = 1;
    let ans = 0;
    for (let i = 0; x <= n; ) {
        if (i < nums.length && nums[i] <= x) {
            x += nums[i++];
        } else {
            ++ans;
            x *= 2;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
