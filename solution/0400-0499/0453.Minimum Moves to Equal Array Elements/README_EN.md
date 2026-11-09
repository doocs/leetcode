---
comments: true
difficulty: Medium
tags:
    - Array
    - Math
---

<!-- problem:start -->

# [453. Minimum Moves to Equal Array Elements](https://leetcode.com/problems/minimum-moves-to-equal-array-elements)

[中文文档](/solution/0400-0499/0453.Minimum%20Moves%20to%20Equal%20Array%20Elements/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> of size <code>n</code>, return <em>the minimum number of moves required to make all array elements equal</em>.</p>

<p>In one move, you can increment <code>n - 1</code> elements of the array by <code>1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Only three moves are needed (remember each move increments two elements):
[1,2,3]  =&gt;  [2,3,3]  =&gt;  [3,4,3]  =&gt;  [4,4,4]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>The answer is guaranteed to fit in a <strong>32-bit</strong> integer.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

<!-- thinking:start -->

> **Thinking**
>
> Incrementing $n-1$ elements is the same as decrementing the one left out, until everything equals the minimum. Simulating the increments rewrites the whole array.
>
> The number of decrements is $\sum \textit{nums} - n\cdot\min(\textit{nums})$.
>
> One pass for the min and the sum is enough; the operations need not be applied.

<!-- thinking:end -->

Let the minimum value of the array $\textit{nums}$ be $mi$, the sum of the array be $s$, and the length of the array be $n$.

Assume the minimum number of operations is $k$, and the final value of all elements in the array is $x$. Then we have:

$$
\begin{aligned}
s + (n - 1) \times k &= n \times x \\
x &= mi + k \\
\end{aligned}
$$

Substituting the second equation into the first equation, we get:

$$
\begin{aligned}
s + (n - 1) \times k &= n \times (mi + k) \\
s + (n - 1) \times k &= n \times mi + n \times k \\
k &= s - n \times mi \\
\end{aligned}
$$

Therefore, the minimum number of operations is $s - n \times mi$.

$|\textit{nums}[i]|$ reaches $10^9$ and $n$ reaches $10^5$, so both $s$ and $n \times mi$ may fall outside a 32-bit integer. The problem guarantees that their difference still fits, so the sum is computed with a 64-bit integer.

The time complexity is $O(n)$, and the space complexity is $O(1)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minMoves(self, nums: list[int]) -> int:
        return sum(nums) - min(nums) * len(nums)
```

#### Java

```java
class Solution {
    public int minMoves(int[] nums) {
        long s = 0;
        int mi = nums[0];
        for (int x : nums) {
            s += x;
            mi = Math.min(mi, x);
        }
        return (int) (s - (long) mi * nums.length);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minMoves(vector<int>& nums) {
        long long s = 0;
        int mi = nums[0];
        int n = nums.size();
        for (int x : nums) {
            s += x;
            mi = min(mi, x);
        }
        return (int) (s - 1LL * mi * n);
    }
};
```

#### Go

```go
func minMoves(nums []int) int {
	mi := 1 << 30
	s := 0
	for _, x := range nums {
		s += x
		if x < mi {
			mi = x
		}
	}
	return s - mi*len(nums)
}
```

#### TypeScript

```ts
function minMoves(nums: number[]): number {
    let mi = 1 << 30;
    let s = 0;
    for (const x of nums) {
        s += x;
        mi = Math.min(mi, x);
    }
    return s - mi * nums.length;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_moves(nums: Vec<i32>) -> i32 {
        let (s, mi) = nums
            .iter()
            .fold((0i64, i32::MAX), |(s, mi), &x| (s + x as i64, mi.min(x)));
        (s - mi as i64 * nums.len() as i64) as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
