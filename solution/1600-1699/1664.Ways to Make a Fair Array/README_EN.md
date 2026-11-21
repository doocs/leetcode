---
comments: true
difficulty: Medium
rating: 1590
source: Weekly Contest 216 Q3
tags:
    - Array
    - Prefix Sum
---

<!-- problem:start -->

# [1664. Ways to Make a Fair Array](https://leetcode.com/problems/ways-to-make-a-fair-array)

[中文文档](/solution/1600-1699/1664.Ways%20to%20Make%20a%20Fair%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array&nbsp;<code>nums</code>. You can choose <strong>exactly one</strong> index (<strong>0-indexed</strong>) and remove the element. Notice that the index of the elements may change after the removal.</p>

<p>For example, if <code>nums = [6,1,7,4,1]</code>:</p>

<ul>
	<li>Choosing to remove index <code>1</code> results in <code>nums = [6,7,4,1]</code>.</li>
	<li>Choosing to remove index <code>2</code> results in <code>nums = [6,1,4,1]</code>.</li>
	<li>Choosing to remove index <code>4</code> results in <code>nums = [6,1,7,4]</code>.</li>
</ul>

<p>An array is <strong>fair</strong> if the sum of the odd-indexed values equals the sum of the even-indexed values.</p>

<p>Return the <em><strong>number</strong> of indices that you could choose such that after the removal, </em><code>nums</code><em> </em><em>is <strong>fair</strong>. </em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,6,4]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
Remove index 0: [1,6,4] -&gt; Even sum: 1 + 4 = 5. Odd sum: 6. Not fair.
Remove index 1: [2,6,4] -&gt; Even sum: 2 + 4 = 6. Odd sum: 6. Fair.
Remove index 2: [2,1,4] -&gt; Even sum: 2 + 4 = 6. Odd sum: 1. Not fair.
Remove index 3: [2,1,6] -&gt; Even sum: 2 + 6 = 8. Odd sum: 1. Not fair.
There is 1 index that you can remove to make nums fair.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong>&nbsp;You can remove any index and the remaining array is fair.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 0
<strong>Explanation:</strong>&nbsp;You cannot make a fair array after removing any index.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration + Prefix Sum

<!-- thinking:start -->

> **Thinking**
>
> Removing an index leaves the parity on its left unchanged and swaps the parity of every index on its right. With $n$ up to $10^5$, recomputing both sums for every deletion does not fit.
>
> A fair array needs the even-index sum to equal the odd-index sum after that deletion. Once the right side swaps parity, the new even sum is the left even sum plus the right odd sum, and the new odd sum is the left odd sum plus the right even sum.
>
> Keep the visited parity sums in $l$ and the unvisited ones in $r$. Subtract the current value from $r$ first, count the index when the crossed sums are equal, then add the value into $l$.

<!-- thinking:end -->

Let $s$ be the sum of $\textit{nums}$ and $r_0$ the sum of its even indices. $r[0]$ and $r[1]$ are the even-index and odd-index sums not yet moved to the left. Initially $r=[r_0, s-r_0]$. $l[0]$ and $l[1]$ are the even-index and odd-index sums already passed, both starting at $0$.

Scan from left to right. At index $i$ with value $x$, subtract $x$ from $r[i\bmod 2]$, so $r$ contains only the elements after $i$. Deleting $i$ keeps the left parities and swaps the right parities, so the new even sum is $l[0]+r[1]$ and the new odd sum is $l[1]+r[0]$. If they are equal, increment the answer, then add $x$ to $l[i\bmod 2]$.

The time complexity is $O(n)$, and the space complexity is $O(1)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def waysToMakeFair(self, nums: list[int]) -> int:
        s = sum(nums)
        r0 = sum(nums[::2])
        r = [r0, s - r0]
        l = [0, 0]
        ans = 0
        for i, x in enumerate(nums):
            r[i % 2] -= x
            if l[0] + r[1] == l[1] + r[0]:
                ans += 1
            l[i % 2] += x
        return ans
```

#### Java

```java
class Solution {
    public int waysToMakeFair(int[] nums) {
        int s = 0, r0 = 0;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            if (i % 2 == 0) {
                r0 += nums[i];
            }
        }
        int[] r = {r0, s - r0};
        int[] l = {0, 0};
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            int x = nums[i];
            r[i % 2] -= x;
            if (l[0] + r[1] == l[1] + r[0]) {
                ++ans;
            }
            l[i % 2] += x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int waysToMakeFair(vector<int>& nums) {
        int n = nums.size();
        int s = 0, r0 = 0;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (i % 2 == 0) {
                r0 += nums[i];
            }
        }
        int r[2] = {r0, s - r0};
        int l[2] = {0, 0};
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            r[i % 2] -= x;
            if (l[0] + r[1] == l[1] + r[0]) {
                ++ans;
            }
            l[i % 2] += x;
        }
        return ans;
    }
};
```

#### Go

```go
func waysToMakeFair(nums []int) int {
	s, r0 := 0, 0
	for i, x := range nums {
		s += x
		if i%2 == 0 {
			r0 += x
		}
	}
	r := [2]int{r0, s - r0}
	l := [2]int{}
	ans := 0
	for i, x := range nums {
		r[i%2] -= x
		if l[0]+r[1] == l[1]+r[0] {
			ans++
		}
		l[i%2] += x
	}
	return ans
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var waysToMakeFair = function (nums) {
    const s = nums.reduce((a, b) => a + b, 0);
    let r0 = 0;
    for (let i = 0; i < nums.length; i += 2) {
        r0 += nums[i];
    }
    const r = [r0, s - r0];
    const l = [0, 0];
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        const x = nums[i];
        r[i % 2] -= x;
        if (l[0] + r[1] === l[1] + r[0]) {
            ++ans;
        }
        l[i % 2] += x;
    }
    return ans;
};
```

#### TypeScript

```ts
function waysToMakeFair(nums: number[]): number {
    const s = nums.reduce((a, b) => a + b, 0);
    let r0 = 0;
    for (let i = 0; i < nums.length; i += 2) {
        r0 += nums[i];
    }
    const r = [r0, s - r0];
    const l = [0, 0];
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        const x = nums[i];
        r[i % 2] -= x;
        if (l[0] + r[1] === l[1] + r[0]) {
            ++ans;
        }
        l[i % 2] += x;
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn ways_to_make_fair(nums: Vec<i32>) -> i32 {
        let s: i32 = nums.iter().sum();
        let r0: i32 = nums.iter().step_by(2).sum();
        let mut r = [r0, s - r0];
        let mut l = [0, 0];
        let mut ans = 0;
        for (i, &x) in nums.iter().enumerate() {
            r[i % 2] -= x;
            if l[0] + r[1] == l[1] + r[0] {
                ans += 1;
            }
            l[i % 2] += x;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
