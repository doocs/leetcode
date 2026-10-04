---
comments: true
difficulty: Medium
rating: 1952
source: Weekly Contest 496 Q3
tags:
    - Greedy
    - Array
    - Dynamic Programming
    - Prefix Sum
---

<!-- problem:start -->

# [3891. Minimum Increase to Maximize Special Indices](https://leetcode.com/problems/minimum-increase-to-maximize-special-indices)

[中文文档](/solution/3800-3899/3891.Minimum%20Increase%20to%20Maximize%20Special%20Indices/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code>.</p>

<p>An index <code>i</code> (<code>0 &lt; i &lt; n - 1</code>) is <strong>special</strong> if <code>nums[i] &gt; nums[i - 1]</code> and <code>nums[i] &gt; nums[i + 1]</code>.</p>

<p>You may perform operations where you choose <strong>any</strong> index <code>i</code> and <strong>increase</strong> <code>nums[i]</code> by 1.</p>

<p>Your goal is to:</p>

<ul>
	<li><strong>Maximize</strong> the number of <strong>special</strong> indices.</li>
	<li><strong>Minimize</strong> the total number of <strong>operations</strong> required to achieve that <strong>maximum</strong>.</li>
</ul>

<p>Return an integer denoting the <strong>minimum</strong> total number of operations required.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Start with <code>nums = [1, 2, 2]</code>.</li>
	<li>Increase <code>nums[1]</code> by 1, array becomes <code>[1, 3, 2]</code>.</li>
	<li>The final array is <code>[1, 3, 2]</code> has 1 special index, which is the maximum achievable.</li>
	<li>It is impossible to achieve this number of special indices with fewer operations. Thus, the answer is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,1,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Start with <code>nums = [2, 1, 1, 3]</code>.</li>
	<li>Perform 2 operations at index 1, array becomes <code>[2, 3, 1, 3]</code>.</li>
	<li>The final array is <code>[2, 3, 1, 3]</code> has 1 special index, which is the maximum achievable. Thus, the answer is 2.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,2,1,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong>​​​​​​​​​​​​​​​​​​​​​</p>

<ul>
	<li>Start with <code>nums = [5, 2, 1, 4, 3]</code>.</li>
	<li>Perform 4 operations at index 1, array becomes <code>[5, 6, 1, 4, 3]</code>.</li>
	<li>The final array is <code>[5, 6, 1, 4, 3]</code> has 2 special indices, which is the maximum achievable. Thus, the answer is 4.​​​​​​​</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoized Search

<!-- thinking:start -->

> **Thinking**
>
> A special index is a strict peak. We may only add $1$, first maximizing the number of peaks then minimizing the total added. $n \le 10^5$.
>
> Peaks cannot be adjacent. An odd-length array can take every odd index; an even-length array must skip one index in $[1,n-2]$.
>
> Raising $i$ above both neighbors costs $\max(0,\max(nums[i-1],nums[i+1])+1-nums[i])$. Memoized $\mathrm{dfs}(i,j)$ starts at $i$ with $j$ skips left.
>
> Raising jumps to $i+2$; a remaining skip may instead go to $i+1$ and spend it. The search starts at $1$ with $j$ opposite $n \bmod 2$.

<!-- thinking:end -->

We observe that if the array length is odd, then increasing all elements at odd indices so that each is $1$ greater than both adjacent elements yields the maximum possible number of special indices. If the array length is even, then among indices in the range $[1, n - 2]$, we skip exactly one index, and for the remaining indices, increase every other element so that each is $1$ greater than both adjacent elements; this also yields the maximum possible number of special indices.

Therefore, we design a function $\text{dfs}(i, j)$, which represents the minimum number of operations needed to obtain the maximum number of special indices starting from index $i$, with $j$ remaining skips. For each index $i$, we can either increase it so that it is $1$ greater than both neighbors, or skip it. We use memoized search to avoid repeated computation.

The implementation of $\text{dfs}(i, j)$ is as follows:

- If $i \geq n - 1$, return $0$.
- Compute the number of operations required to increase $nums[i]$ so that it is $1$ greater than both adjacent elements, denoted as $cost$.
- Compute the total cost for choosing to increase $nums[i]$: $cost + \text{dfs}(i + 2, j)$.
- If $j > 0$, compute the total cost for choosing to skip $nums[i]$: $\text{dfs}(i + 1, 0)$, and update $ans$ to the smaller of the two.

Finally, return $\text{dfs}(1, (n \bmod 2) \oplus 1)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minIncrease(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= len(nums) - 1:
                return 0
            cost = max(0, max(nums[i - 1], nums[i + 1]) + 1 - nums[i])
            ans = cost + dfs(i + 2, j)
            if j:
                ans = min(ans, dfs(i + 1, 0))
            return ans

        return dfs(1, len(nums) & 1 ^ 1)
```

#### Java

```java
class Solution {
    private Long[][] f;
    private int[] nums;
    private int n;

    public long minIncrease(int[] nums) {
        n = nums.length;
        this.nums = nums;
        f = new Long[n][2];
        return dfs(1, n & 1 ^ 1);
    }

    private long dfs(int i, int j) {
        if (i >= n - 1) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int cost = Math.max(0, Math.max(nums[i - 1], nums[i + 1]) + 1 - nums[i]);
        long ans = cost + dfs(i + 2, j);
        if (j > 0) {
            ans = Math.min(ans, dfs(i + 1, 0));
        }
        return f[i][j] = ans;
    }
}
```

#### C++

```cpp
class Solution {
private:
    vector<vector<long long>> f;
    vector<int> nums;
    int n;

public:
    long long minIncrease(vector<int>& nums) {
        this->nums = nums;
        n = nums.size();
        f.assign(n, vector<long long>(2, -1));
        return dfs(1, (n & 1) ^ 1);
    }

    long long dfs(int i, int j) {
        if (i >= n - 1) {
            return 0;
        }
        if (f[i][j] != -1) {
            return f[i][j];
        }
        int cost = max(0, max(nums[i - 1], nums[i + 1]) + 1 - nums[i]);
        long long ans = cost + dfs(i + 2, j);
        if (j > 0) {
            ans = min(ans, dfs(i + 1, 0));
        }
        return f[i][j] = ans;
    }
};
```

#### Go

```go
func minIncrease(nums []int) int64 {
	n := len(nums)

	f := make([][]int64, n)
	for i := range f {
		f[i] = []int64{-1, -1}
	}

	var dfs func(i, j int) int64
	dfs = func(i, j int) int64 {
		if i >= n-1 {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}

		cost := max(0, max(nums[i-1], nums[i+1])+1-nums[i])
		ans := int64(cost) + dfs(i+2, j)

		if j > 0 {
			if t := dfs(i+1, 0); t < ans {
				ans = t
			}
		}

		f[i][j] = ans
		return ans
	}

	return dfs(1, (n&1)^1)
}
```

#### TypeScript

```ts
function minIncrease(nums: number[]): number {
    const n = nums.length;

    const f: number[][] = Array.from({ length: n }, () => Array(2).fill(-1));

    const dfs = (i: number, j: number): number => {
        if (i >= n - 1) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }

        const cost = Math.max(0, Math.max(nums[i - 1], nums[i + 1]) + 1 - nums[i]);
        let ans = cost + dfs(i + 2, j);

        if (j > 0) {
            ans = Math.min(ans, dfs(i + 1, 0));
        }

        f[i][j] = ans;
        return ans;
    };

    return dfs(1, (n & 1) ^ 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> A special index is a strict peak. We may only add $1$, first making as many peaks as possible and then minimizing the total added. $n$ can reach $10^5$, and every raise enters $\mathrm{dfs}(i+2, j)$ first, so that chain is about $n/2$ deep and exceeds the default recursion limit.
>
> Peaks cannot be adjacent. An odd length can take every odd index; an even length must skip exactly one index in $[1, n-2]$. The cost at a position depends only on its two neighbors, and later choices do not change it.
>
> Let $f[i][j]$ be the minimum cost starting at index $i$ with $j$ skips left. Cells with $i \ge n-1$ are $0$. Raising the current index adds its cost and moves to $i+2$; if $j>0$, we may instead move to $i+1$ and spend the skip. Every dependency has a larger index, so $i$ runs from $n-2$ down to $1$. The answer is $f[1][(n \bmod 2) \oplus 1]$.

<!-- thinking:end -->

We observe that if the array length is odd, then increasing all elements at odd indices so that each is $1$ greater than both adjacent elements yields the maximum possible number of special indices. If the array length is even, then among indices in the range $[1, n - 2]$, we skip exactly one index, and for the remaining indices, increase every other element so that each is $1$ greater than both adjacent elements; this also yields the maximum possible number of special indices.

Let $f[i][j]$ be the minimum number of operations needed to obtain the maximum number of special indices starting from index $i$, with $j$ skips remaining. When $i \ge n - 1$, $f[i][j] = 0$.

For $i$ from $n - 2$ down to $1$, first compute the cost of raising $nums[i]$ so that it is $1$ greater than both neighbors:

$$
cost = \max(0, \max(nums[i - 1], nums[i + 1]) + 1 - nums[i]).
$$

Raising this index costs $cost + f[i + 2][j]$. If $j > 0$, we may instead take $f[i + 1][0]$, and keep the smaller of the two.

The answer is $f[1][(n \bmod 2) \oplus 1]$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minIncrease(self, nums: List[int]) -> int:
        n = len(nums)
        f = [[0, 0] for _ in range(n + 1)]
        for i in range(n - 2, 0, -1):
            cost = max(0, max(nums[i - 1], nums[i + 1]) + 1 - nums[i])
            f[i][0] = cost + f[i + 2][0]
            f[i][1] = min(cost + f[i + 2][1], f[i + 1][0])
        return f[1][n & 1 ^ 1]
```

#### Java

```java
class Solution {
    public long minIncrease(int[] nums) {
        int n = nums.length;
        long[][] f = new long[n + 1][2];
        for (int i = n - 2; i >= 1; --i) {
            int cost = Math.max(0, Math.max(nums[i - 1], nums[i + 1]) + 1 - nums[i]);
            f[i][0] = cost + f[i + 2][0];
            f[i][1] = Math.min(cost + f[i + 2][1], f[i + 1][0]);
        }
        return f[1][(n & 1) ^ 1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minIncrease(vector<int>& nums) {
        int n = nums.size();
        vector<array<long long, 2>> f(n + 1);
        for (int i = n - 2; i >= 1; --i) {
            long long cost = max(0, max(nums[i - 1], nums[i + 1]) + 1 - nums[i]);
            f[i][0] = cost + f[i + 2][0];
            f[i][1] = min(cost + f[i + 2][1], f[i + 1][0]);
        }
        return f[1][(n & 1) ^ 1];
    }
};
```

#### Go

```go
func minIncrease(nums []int) int64 {
	n := len(nums)
	f := make([][2]int64, n+1)
	for i := n - 2; i >= 1; i-- {
		cost := int64(max(0, max(nums[i-1], nums[i+1])+1-nums[i]))
		f[i][0] = cost + f[i+2][0]
		t := cost + f[i+2][1]
		if f[i+1][0] < t {
			t = f[i+1][0]
		}
		f[i][1] = t
	}
	return f[1][(n&1)^1]
}
```

#### TypeScript

```ts
function minIncrease(nums: number[]): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n + 1 }, () => [0, 0]);
    for (let i = n - 2; i >= 1; --i) {
        const cost = Math.max(0, Math.max(nums[i - 1], nums[i + 1]) + 1 - nums[i]);
        f[i][0] = cost + f[i + 2][0];
        f[i][1] = Math.min(cost + f[i + 2][1], f[i + 1][0]);
    }
    return f[1][(n & 1) ^ 1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
