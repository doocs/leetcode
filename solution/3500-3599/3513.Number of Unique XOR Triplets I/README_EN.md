---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3513.Number%20of%20Unique%20XOR%20Triplets%20I/README_EN.md
rating: 1663
source: Biweekly Contest 154 Q2
tags:
    - Bit Manipulation
    - Array
    - Math
---

<!-- problem:start -->

# [3513. Number of Unique XOR Triplets I](https://leetcode.com/problems/number-of-unique-xor-triplets-i)

[中文文档](/solution/3500-3599/3513.Number%20of%20Unique%20XOR%20Triplets%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>, where <code>nums</code> is a <strong><span data-keyword="permutation">permutation</span></strong> of the numbers in the range <code>[1, n]</code>.</p>

<p>A <strong>XOR triplet</strong> is defined as the XOR of three elements <code>nums[i] XOR nums[j] XOR nums[k]</code> where <code>i &lt;= j &lt;= k</code>.</p>

<p>Return the number of <strong>unique</strong> XOR triplet values from all possible triplets <code>(i, j, k)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The possible XOR triplet values are:</p>

<ul>
	<li><code>(0, 0, 0) &rarr; 1 XOR 1 XOR 1 = 1</code></li>
	<li><code>(0, 0, 1) &rarr; 1 XOR 1 XOR 2 = 2</code></li>
	<li><code>(0, 1, 1) &rarr; 1 XOR 2 XOR 2 = 1</code></li>
	<li><code>(1, 1, 1) &rarr; 2 XOR 2 XOR 2 = 2</code></li>
</ul>

<p>The unique XOR values are <code>{1, 2}</code>, so the output is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The possible XOR triplet values include:</p>

<ul>
	<li><code>(0, 0, 0) &rarr; 3 XOR 3 XOR 3 = 3</code></li>
	<li><code>(0, 0, 1) &rarr; 3 XOR 3 XOR 1 = 1</code></li>
	<li><code>(0, 0, 2) &rarr; 3 XOR 3 XOR 2 = 2</code></li>
	<li><code>(0, 1, 2) &rarr; 3 XOR 1 XOR 2 = 0</code></li>
</ul>

<p>The unique XOR values are <code>{0, 1, 2, 3}</code>, so the output is 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= n</code></li>
	<li><code>nums</code> is a permutation of integers from <code>1</code> to <code>n</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Bit Manipulation

Since $\textit{nums}$ is a permutation of $[1, n]$, the available values are fixed as $\{1, 2, \ldots, n\}$. With indices satisfying $i \le j \le k$, the same index may be chosen more than once, so a XOR triplet is equivalent to picking three numbers (with replacement) from this set and taking their XOR.

When $n \le 2$, enumeration shows the answers are $1$ ($n = 1$) and $2$ ($n = 2$), i.e., the answer equals $n$.

When $n \ge 3$, it can be shown that all possible XOR results exactly fill the interval $[0, 2^{k} - 1]$, where $2^{k}$ is the smallest power of $2$ strictly greater than $n$. This value also equals $2^{\lfloor \log_2 n \rfloor + 1}$, which can be obtained via each language's bit-length function:

$$
\textit{ans} = 1 \ll \textit{bitLength}(n)
$$

For example, when $n = 3$, $\textit{bitLength}(3) = 2$, so the answer is $4$, matching the example set $\{0, 1, 2, 3\}$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def uniqueXorTriplets(self, nums: List[int]) -> int:
        n = len(nums)
        return n if n <= 2 else 1 << n.bit_length()
```

#### Java

```java
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        return n <= 2 ? n : 1 << (32 - Integer.numberOfLeadingZeros(n));
    }
}
```

#### C++

```cpp
class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {
        size_t n = nums.size();
        return n <= 2 ? n : 1 << bit_width(n);
    }
};
```

#### Go

```go
func uniqueXorTriplets(nums []int) int {
	n := len(nums)
	if n <= 2 {
		return n
	}
	return 1 << bits.Len(uint(n))
}
```

#### TypeScript

```ts
function uniqueXorTriplets(nums: number[]): number {
    const n = nums.length;
    if (n <= 2) {
        return n;
    }
    return 1 << (32 - Math.clz32(n));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
