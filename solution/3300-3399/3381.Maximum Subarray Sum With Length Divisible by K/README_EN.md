---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3381.Maximum%20Subarray%20Sum%20With%20Length%20Divisible%20by%20K/README_EN.md
rating: 1943
source: Weekly Contest 427 Q3
tags:
    - Array
    - Hash Table
    - Prefix Sum
---

<!-- problem:start -->

# [3381. Maximum Subarray Sum With Length Divisible by K](https://leetcode.com/problems/maximum-subarray-sum-with-length-divisible-by-k)

[中文文档](/solution/3300-3399/3381.Maximum%20Subarray%20Sum%20With%20Length%20Divisible%20by%20K/README.md)

## Description

<!-- description:start -->

<p>You are given an array of integers <code>nums</code> and an integer <code>k</code>.</p>

<p>Return the <strong>maximum</strong> sum of a <span data-keyword="subarray-nonempty">subarray</span> of <code>nums</code>, such that the size of the subarray is <strong>divisible</strong> by <code>k</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The subarray <code>[1, 2]</code> with sum 3 has length equal to 2 which is divisible by 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,-2,-3,-4,-5], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">-10</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum sum subarray is <code>[-1, -2, -3, -4]</code> which has length equal to 4 which is divisible by 4.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-5,1,2,-3,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The maximum sum subarray is <code>[1, 2, -3, 4]</code> which has length equal to 4 which is divisible by 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum + Enumeration

According to the problem description, for a subarray's length to be divisible by $k$, it is equivalent to requiring that for subarray $\textit{nums}[i+1 \ldots j]$, we have $i \bmod k = j \bmod k$.

We can enumerate the right endpoint $j$ of the subarray and use an array $\textit{f}$ of length $k$ to record the minimum prefix sum for each modulo $k$. Initially, $\textit{f}[k-1] = 0$, indicating that the prefix sum at index $-1$ is $0$.

Then for the current right endpoint $j$ with prefix sum $s$, we can calculate the maximum sum of subarrays ending at $j$ with length divisible by $k$ as $s - \textit{f}[j \bmod k]$, and update the answer accordingly. At the same time, we need to update $\textit{f}[j \bmod k]$ to be the minimum of the current prefix sum $s$ and $\textit{f}[j \bmod k]$.

After the enumeration is complete, return the answer.

The time complexity is $O(n)$ and the space complexity is $O(k)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSubarraySum(self, nums: List[int], k: int) -> int:
        f = [inf] * k
        ans = -inf
        s = f[-1] = 0
        for i, x in enumerate(nums):
            s += x
            ans = max(ans, s - f[i % k])
            f[i % k] = min(f[i % k], s)
        return ans
```

#### Java

```java
class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        long[] f = new long[k];
        final long inf = 1L << 62;
        Arrays.fill(f, inf);
        f[k - 1] = 0;
        long s = 0;
        long ans = -inf;
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            ans = Math.max(ans, s - f[i % k]);
            f[i % k] = Math.min(f[i % k], s);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxSubarraySum(vector<int>& nums, int k) {
        using ll = long long;
        ll inf = 1e18;
        vector<ll> f(k, inf);
        ll ans = -inf;
        ll s = 0;
        f[k - 1] = 0;
        for (int i = 0; i < nums.size(); ++i) {
            s += nums[i];
            ans = max(ans, s - f[i % k]);
            f[i % k] = min(f[i % k], s);
        }
        return ans;
    }
};
```

#### Go

```go
func maxSubarraySum(nums []int, k int) int64 {
	inf := int64(1) << 62
	f := make([]int64, k)
	for i := range f {
		f[i] = inf
	}
	f[k-1] = 0

	var s, ans int64
	ans = -inf
	for i := 0; i < len(nums); i++ {
		s += int64(nums[i])
		ans = max(ans, s-f[i%k])
		f[i%k] = min(f[i%k], s)
	}

	return ans
}
```

#### TypeScript

```ts
function maxSubarraySum(nums: number[], k: number): number {
    const f: number[] = Array(k).fill(Infinity);
    f[k - 1] = 0;
    let ans = -Infinity;
    let s = 0;
    for (let i = 0; i < nums.length; ++i) {
        s += nums[i];
        ans = Math.max(ans, s - f[i % k]);
        f[i % k] = Math.min(f[i % k], s);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_subarray_sum(nums: Vec<i32>, k: i32) -> i64 {
        let k = k as usize;
        let inf = 1i64 << 62;
        let mut f = vec![inf; k];
        f[k - 1] = 0;
        let mut s = 0i64;
        let mut ans = -inf;
        for (i, &x) in nums.iter().enumerate() {
            s += x as i64;
            ans = ans.max(s - f[i % k]);
            f[i % k] = f[i % k].min(s);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
