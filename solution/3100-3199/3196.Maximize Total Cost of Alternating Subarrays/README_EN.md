---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3196.Maximize%20Total%20Cost%20of%20Alternating%20Subarrays/README_EN.md
rating: 1846
source: Weekly Contest 403 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [3196. Maximize Total Cost of Alternating Subarrays](https://leetcode.com/problems/maximize-total-cost-of-alternating-subarrays)

[中文文档](/solution/3100-3199/3196.Maximize%20Total%20Cost%20of%20Alternating%20Subarrays/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> with length <code>n</code>.</p>

<p>The <strong>cost</strong> of a <span data-keyword="subarray-nonempty">subarray</span> <code>nums[l..r]</code>, where <code>0 &lt;= l &lt;= r &lt; n</code>, is defined as:</p>

<p><code>cost(l, r) = nums[l] - nums[l + 1] + ... + nums[r] * (&minus;1)<sup>r &minus; l</sup></code></p>

<p>Your task is to <strong>split</strong> <code>nums</code> into subarrays such that the <strong>total</strong> <strong>cost</strong> of the subarrays is <strong>maximized</strong>, ensuring each element belongs to <strong>exactly one</strong> subarray.</p>

<p>Formally, if <code>nums</code> is split into <code>k</code> subarrays, where <code>k &gt; 1</code>, at indices <code>i<sub>1</sub>, i<sub>2</sub>, ..., i<sub>k &minus; 1</sub></code>, where <code>0 &lt;= i<sub>1</sub> &lt; i<sub>2</sub> &lt; ... &lt; i<sub>k - 1</sub> &lt; n - 1</code>, then the total cost will be:</p>

<p><code>cost(0, i<sub>1</sub>) + cost(i<sub>1</sub> + 1, i<sub>2</sub>) + ... + cost(i<sub>k &minus; 1</sub> + 1, n &minus; 1)</code></p>

<p>Return an integer denoting the <em>maximum total cost</em> of the subarrays after splitting the array optimally.</p>

<p><strong>Note:</strong> If <code>nums</code> is not split into subarrays, i.e. <code>k = 1</code>, the total cost is simply <code>cost(0, n - 1)</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-2,3,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<p>One way to maximize the total cost is by splitting <code>[1, -2, 3, 4]</code> into subarrays <code>[1, -2, 3]</code> and <code>[4]</code>. The total cost will be <code>(1 + 2 + 3) + 4 = 10</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-1,1,-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>One way to maximize the total cost is by splitting <code>[1, -1, 1, -1]</code> into subarrays <code>[1, -1]</code> and <code>[1, -1]</code>. The total cost will be <code>(1 + 1) + (1 + 1) = 4</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0]</span></p>

<p><strong>Output:</strong> 0</p>

<p><strong>Explanation:</strong></p>

<p>We cannot split the array further, so the answer is 0.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Selecting the whole array gives a total cost of <code>1 + 1 = 2</code>, which is the maximum.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization

Based on the problem description, if the current number has not been flipped, then the next one can either be flipped or not flipped; if the current number has been flipped, then the next one can only remain unflipped.

Therefore, we define a function $\textit{dfs}(i, j)$, which represents starting from the $i$-th number, whether the $i$-th number can be flipped, where $j$ indicates whether the $i$-th number is flipped. If $j = 0$, it means the $i$-th number cannot be flipped, otherwise, it can be flipped. The answer is $\textit{dfs}(0, 0)$.

The execution process of the function $dfs(i, j)$ is as follows:

-   If $i \geq \textit{len}(nums)$, it means the array has been fully traversed, return $0$;
-   Otherwise, the $i$-th number can remain unflipped, in which case the answer is $nums[i] + \textit{dfs}(i + 1, 1)$; if $j = 1$, it means the $i$-th number can be flipped, in which case the answer is $\max(\textit{dfs}(i + 1, 0) - nums[i])$. We take the maximum of the two.

To avoid repeated calculations, we can use memoization to save the results that have already been computed.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTotalCost(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= len(nums):
                return 0
            ans = nums[i] + dfs(i + 1, 1)
            if j == 1:
                ans = max(ans, -nums[i] + dfs(i + 1, 0))
            return ans

        return dfs(0, 0)
```

#### Java

```java
class Solution {
    private Long[][] f;
    private int[] nums;
    private int n;

    public long maximumTotalCost(int[] nums) {
        n = nums.length;
        this.nums = nums;
        f = new Long[n][2];
        return dfs(0, 0);
    }

    private long dfs(int i, int j) {
        if (i >= n) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        f[i][j] = nums[i] + dfs(i + 1, 1);
        if (j == 1) {
            f[i][j] = Math.max(f[i][j], -nums[i] + dfs(i + 1, 0));
        }
        return f[i][j];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumTotalCost(vector<int>& nums) {
        int n = nums.size();
        long long f[n][2];
        fill(f[0], f[n], LLONG_MIN);
        auto dfs = [&](auto&& dfs, int i, int j) -> long long {
            if (i >= n) {
                return 0;
            }
            if (f[i][j] != LLONG_MIN) {
                return f[i][j];
            }
            f[i][j] = nums[i] + dfs(dfs, i + 1, 1);
            if (j) {
                f[i][j] = max(f[i][j], -nums[i] + dfs(dfs, i + 1, 0));
            }
            return f[i][j];
        };
        return dfs(dfs, 0, 0);
    }
};
```

#### Go

```go
func maximumTotalCost(nums []int) int64 {
	n := len(nums)
	f := make([][2]int64, n)
	for i := range f {
		f[i] = [2]int64{-1e18, -1e18}
	}
	var dfs func(int, int) int64
	dfs = func(i, j int) int64 {
		if i >= n {
			return 0
		}
		if f[i][j] != -1e18 {
			return f[i][j]
		}
		f[i][j] = int64(nums[i]) + dfs(i+1, 1)
		if j > 0 {
			f[i][j] = max(f[i][j], int64(-nums[i])+dfs(i+1, 0))
		}
		return f[i][j]
	}
	return dfs(0, 0)
}
```

#### TypeScript

```ts
function maximumTotalCost(nums: number[]): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(2).fill(-Infinity));
    const dfs = (i: number, j: number): number => {
        if (i >= n) {
            return 0;
        }
        if (f[i][j] !== -Infinity) {
            return f[i][j];
        }
        f[i][j] = nums[i] + dfs(i + 1, 1);
        if (j) {
            f[i][j] = Math.max(f[i][j], -nums[i] + dfs(i + 1, 0));
        }
        return f[i][j];
    };
    return dfs(0, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

We can transform the memoization search from Solution 1 into dynamic programming.

Define $f$ and $g$ as two states, where $f$ represents the maximum value when the current number is not flipped, and $g$ represents the maximum value when the current number is flipped.

Traverse the array $nums$, for the $i$-th number, we can update the values of $f$ and $g$ based on their states:

-   If the current number is not flipped, then the value of $f$ is $\max(f, g) + x$, indicating that if the current number is not flipped, the previous number can be flipped or not flipped;
-   If the current number is flipped, then the value of $g$ is $f - x$, indicating that if the current number is flipped, the previous number cannot be flipped.

The final answer is $\max(f, g)$.

The time complexity is $O(n)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumTotalCost(self, nums: List[int]) -> int:
        f, g = -inf, 0
        for x in nums:
            f, g = max(f, g) + x, f - x
        return max(f, g)
```

#### Java

```java
class Solution {
    public long maximumTotalCost(int[] nums) {
        long f = Long.MIN_VALUE / 2, g = 0;
        for (int x : nums) {
            long ff = Math.max(f, g) + x;
            long gg = f - x;
            f = ff;
            g = gg;
        }
        return Math.max(f, g);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumTotalCost(vector<int>& nums) {
        long long f = LLONG_MIN / 2, g = 0;
        for (int x : nums) {
            long long ff = max(f, g) + x, gg = f - x;
            f = ff;
            g = gg;
        }
        return max(f, g);
    }
};
```

#### Go

```go
func maximumTotalCost(nums []int) int64 {
	f, g := math.MinInt64/2, 0
	for _, x := range nums {
		f, g = max(f, g)+x, f-x
	}
	return int64(max(f, g))
}
```

#### TypeScript

```ts
function maximumTotalCost(nums: number[]): number {
    let [f, g] = [-Infinity, 0];
    for (const x of nums) {
        [f, g] = [Math.max(f, g) + x, f - x];
    }
    return Math.max(f, g);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
