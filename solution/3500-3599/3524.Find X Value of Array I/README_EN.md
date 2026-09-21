---
comments: true
difficulty: Medium
rating: 2008
source: Weekly Contest 446 Q3
tags:
    - Array
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [3524. Find X Value of Array I](https://leetcode.com/problems/find-x-value-of-array-i)

[中文文档](/solution/3500-3599/3524.Find%20X%20Value%20of%20Array%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an array of <strong>positive</strong> integers <code>nums</code>, and a <strong>positive</strong> integer <code>k</code>.</p>

<p>You are allowed to perform an operation <strong>once</strong> on <code>nums</code>, where in each operation you can remove any <strong>non-overlapping</strong> prefix and suffix from <code>nums</code> such that <code>nums</code> remains <strong>non-empty</strong>.</p>

<p>You need to find the <strong>x-value</strong> of <code>nums</code>, which is the number of ways to perform this operation so that the <strong>product</strong> of the remaining elements leaves a <em>remainder</em> of <code>x</code> when divided by <code>k</code>.</p>

<p>Return an array <code>result</code> of size <code>k</code> where <code>result[x]</code> is the <strong>x-value</strong> of <code>nums</code> for <code>0 &lt;= x &lt;= k - 1</code>.</p>

<p>A <strong>prefix</strong> of an array is a <span data-keyword="subarray">subarray</span> that starts from the beginning of the array and extends to any point within it.</p>

<p>A <strong>suffix</strong> of an array is a <span data-keyword="subarray">subarray</span> that starts at any point within the array and extends to the end of the array.</p>

<p><strong>Note</strong> that the prefix and suffix to be chosen for the operation can be <strong>empty</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[9,2,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>x = 0</code>, the possible operations include all possible ways to remove non-overlapping prefix/suffix that do not remove <code>nums[2] == 3</code>.</li>
	<li>For <code>x = 1</code>, the possible operations are:
	<ul>
		<li>Remove the empty prefix and the suffix <code>[2, 3, 4, 5]</code>. <code>nums</code> becomes <code>[1]</code>.</li>
		<li>Remove the prefix <code>[1, 2, 3]</code> and the suffix <code>[5]</code>. <code>nums</code> becomes <code>[4]</code>.</li>
	</ul>
	</li>
	<li>For <code>x = 2</code>, the possible operations are:
	<ul>
		<li>Remove the empty prefix and the suffix <code>[3, 4, 5]</code>. <code>nums</code> becomes <code>[1, 2]</code>.</li>
		<li>Remove the prefix <code>[1]</code> and the suffix <code>[3, 4, 5]</code>. <code>nums</code> becomes <code>[2]</code>.</li>
		<li>Remove the prefix <code>[1, 2, 3]</code> and the empty suffix. <code>nums</code> becomes <code>[4, 5]</code>.</li>
		<li>Remove the prefix <code>[1, 2, 3, 4]</code> and the empty suffix. <code>nums</code> becomes <code>[5]</code>.</li>
	</ul>
	</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,4,8,16,32], k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">[18,1,2,0]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For <code>x = 0</code>, the only operations that <strong>do not</strong> result in <code>x = 0</code> are:

    <ul>
    	<li>Remove the empty prefix and the suffix <code>[4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[1, 2]</code>.</li>
    	<li>Remove the empty prefix and the suffix <code>[2, 4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[1]</code>.</li>
    	<li>Remove the prefix <code>[1]</code> and the suffix <code>[4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[2]</code>.</li>
    </ul>
    </li>
    <li>For <code>x = 1</code>, the only possible operation is:
    <ul>
    	<li>Remove the empty prefix and the suffix <code>[2, 4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[1]</code>.</li>
    </ul>
    </li>
    <li>For <code>x = 2</code>, the possible operations are:
    <ul>
    	<li>Remove the empty prefix and the suffix <code>[4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[1, 2]</code>.</li>
    	<li>Remove the prefix <code>[1]</code> and the suffix <code>[4, 8, 16, 32]</code>. <code>nums</code> becomes <code>[2]</code>.</li>
    </ul>
    </li>
    <li>For <code>x = 3</code>, there is no possible way to perform the operation.</li>

</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,1,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[9,6]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= 5</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> Removing a prefix and a suffix leaves one subarray; we need how many have product $\equiv x \pmod k$. $n \le 10^5$ and $k \le 5$, so DP on remainders replaces enumeration.
>
> Let $f[i][r]$ be the number of subarrays ending at $i$ whose product is $r$ modulo $k$. Transfer from $f[i-1]$ by multiplying $nums[i]$, and start a new subarray at $i$. Summing by remainder fills $\textit{result}$.

<!-- thinking:end -->

After removing any non-overlapping prefix and suffix, the remainder is a non-empty subarray. The task is to count subarrays whose product modulo $k$ equals $0, 1, \ldots, k-1$.

Let $f[r]$ be the number of subarrays ending at the current index whose product is $r$ modulo $k$. Scan $x = \textit{nums}[i]$ from left to right and use $g$ for the new ending-at-$i$ state: move each $f[r]$ to $g[(r \times x) \bmod k]$, then add the singleton subarray $[x]$ into $g[x \bmod k]$. Add $g$ into the answer and set $f \leftarrow g$.

The time complexity is $O(n \times k)$ and the space complexity is $O(k)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def resultArray(self, nums: list[int], k: int) -> list[int]:
        ans = [0] * k
        f = [0] * k
        for x in nums:
            g = [0] * k
            for r, cnt in enumerate(f):
                g[r * x % k] += cnt
            g[x % k] += 1
            for r, cnt in enumerate(g):
                ans[r] += cnt
            f = g
        return ans
```

#### Java

```java
class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] f = new long[k];
        for (int x : nums) {
            long[] g = new long[k];
            for (int r = 0; r < k; ++r) {
                g[(int) (1L * r * x % k)] += f[r];
            }
            g[x % k] += 1;
            for (int r = 0; r < k; ++r) {
                ans[r] += g[r];
            }
            f = g;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<long long> resultArray(vector<int>& nums, int k) {
        vector<long long> ans(k);
        vector<long long> f(k);
        for (int x : nums) {
            vector<long long> g(k);
            for (int r = 0; r < k; ++r) {
                g[1LL * r * x % k] += f[r];
            }
            g[x % k] += 1;
            for (int r = 0; r < k; ++r) {
                ans[r] += g[r];
            }
            f.swap(g);
        }
        return ans;
    }
};
```

#### Go

```go
func resultArray(nums []int, k int) []int64 {
	ans := make([]int64, k)
	f := make([]int64, k)
	for _, x := range nums {
		g := make([]int64, k)
		for r, cnt := range f {
			g[r*x%k] += cnt
		}
		g[x%k]++
		for r, cnt := range g {
			ans[r] += cnt
		}
		f = g
	}
	return ans
}
```

#### TypeScript

```ts
function resultArray(nums: number[], k: number): number[] {
    const ans = Array(k).fill(0);
    let f = Array(k).fill(0);
    for (const x of nums) {
        const g = Array(k).fill(0);
        for (let r = 0; r < k; ++r) {
            g[(r * x) % k] += f[r];
        }
        g[x % k] += 1;
        for (let r = 0; r < k; ++r) {
            ans[r] += g[r];
        }
        f = g;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
