---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2735.Collecting%20Chocolates/README_EN.md
rating: 2043
tags:
    - Array
    - Enumeration
---

# [2735. Collecting Chocolates](https://leetcode.com/problems/collecting-chocolates)

[中文文档](/solution/2700-2799/2735.Collecting%20Chocolates/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of size <code>n</code> representing the cost of collecting different chocolates. The cost of collecting the chocolate at the index <code>i</code>&nbsp;is <code>nums[i]</code>. Each chocolate is of a different type, and initially, the chocolate at the index&nbsp;<code>i</code>&nbsp;is of <code>i<sup>th</sup></code> type.</p>

<p>In one operation, you can do the following with an incurred <strong>cost</strong> of <code>x</code>:</p>

<ul>
	<li>Simultaneously change the chocolate of <code>i<sup>th</sup></code> type to <code>((i + 1) mod n)<sup>th</sup></code> type for all chocolates.</li>
</ul>

<p>Return <em>the minimum cost to collect chocolates of all types, given that you can perform as many operations as you would like.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [20,1,15], x = 5
<strong>Output:</strong> 13
<strong>Explanation:</strong> Initially, the chocolate types are [0,1,2]. We will buy the 1<sup>st</sup>&nbsp;type of chocolate at a cost of 1.
Now, we will perform the operation at a cost of 5, and the types of chocolates will become [1,2,0]. We will buy the 2<sup>nd</sup><sup> </sup>type of chocolate at a cost of 1.
Now, we will again perform the operation at a cost of 5, and the chocolate types will become [2,0,1]. We will buy the 0<sup>th </sup>type of chocolate at a cost of 1. 
Thus, the total cost will become (1 + 5 + 1 + 5 + 1) = 13. We can prove that this is optimal.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], x = 4
<strong>Output:</strong> 6
<strong>Explanation:</strong> We will collect all three types of chocolates at their own price without performing any operations. Therefore, the total cost is 1 + 2 + 3 = 6.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Enumeration

We consider enumerating the number of operations, and define $f[i][j]$ as the minimum cost after the $i$-th chocolate has undergone $j$ operations.

For the $i$-th chocolate:

-   If $j = 0$, i.e., no operation is performed, then $f[i][j] = nums[i]$.
-   If $0 < j \leq n-1$, its minimum cost is the minimum cost within the index range $[i,.. (i - j + n) \bmod n]$, i.e., $f[i][j] = \min\{nums[i], nums[i - 1], \cdots, nums[(i - j + n) \bmod n]\}$, or it can be written as $f[i][j] = \min\{f[i][j - 1], nums[(i - j + n) \bmod n]\}$.
-   If $j \ge n$, since when $j = n - 1$, all minimum costs have been covered, if $j$ continues to increase, the minimum cost will not change, but the increase in the number of operations will lead to an increase in the final cost, so we do not need to consider the case where $j \ge n$.

In summary, we can get the state transition equation:

$$
f[i][j] =
\begin{cases}
nums[i] ,& j = 0 \\
\min(f[i][j - 1], nums[(i - j + n) \bmod n]) ,& 0 \lt j \leq n - 1
\end{cases}
$$

Finally, we only need to enumerate the number of operations $j$, calculate the minimum cost under each number of operations, and take the minimum value. That is, the answer is $\min\limits_{0 \leq j \leq n - 1} \sum\limits_{i = 0}^{n - 1} f[i][j] + x \times j$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def minCost(self, nums: List[int], x: int) -> int:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        for i, v in enumerate(nums):
            f[i][0] = v
            for j in range(1, n):
                f[i][j] = min(f[i][j - 1], nums[(i - j) % n])
        return min(sum(f[i][j] for i in range(n)) + x * j for j in range(n))
```

```java
class Solution {
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][0] = nums[i];
            for (int j = 1; j < n; ++j) {
                f[i][j] = Math.min(f[i][j - 1], nums[(i - j + n) % n]);
            }
        }
        long ans = 1L << 60;
        for (int j = 0; j < n; ++j) {
            long cost = 1L * x * j;
            for (int i = 0; i < n; ++i) {
                cost += f[i][j];
            }
            ans = Math.min(ans, cost);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long minCost(vector<int>& nums, int x) {
        int n = nums.size();
        int f[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][0] = nums[i];
            for (int j = 1; j < n; ++j) {
                f[i][j] = min(f[i][j - 1], nums[(i - j + n) % n]);
            }
        }
        long long ans = 1LL << 60;
        for (int j = 0; j < n; ++j) {
            long long cost = 1LL * x * j;
            for (int i = 0; i < n; ++i) {
                cost += f[i][j];
            }
            ans = min(ans, cost);
        }
        return ans;
    }
};
```

```go
func minCost(nums []int, x int) int64 {
	n := len(nums)
	f := make([][]int, n)
	for i, v := range nums {
		f[i] = make([]int, n)
		f[i][0] = v
		for j := 1; j < n; j++ {
			f[i][j] = min(f[i][j-1], nums[(i-j+n)%n])
		}
	}
	ans := 1 << 60
	for j := 0; j < n; j++ {
		cost := x * j
		for i := 0; i < n; i++ {
			cost += f[i][j]
		}
		ans = min(ans, cost)
	}
	return int64(ans)
}
```

```ts
function minCost(nums: number[], x: number): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = 0; i < n; ++i) {
        f[i][0] = nums[i];
        for (let j = 1; j < n; ++j) {
            f[i][j] = Math.min(f[i][j - 1], nums[(i - j + n) % n]);
        }
    }
    let ans = Infinity;
    for (let j = 0; j < n; ++j) {
        let cost = x * j;
        for (let i = 0; i < n; ++i) {
            cost += f[i][j];
        }
        ans = Math.min(ans, cost);
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn min_cost(nums: Vec<i32>, x: i32) -> i64 {
        let n = nums.len();
        let mut f = vec![vec![0; n]; n];
        for i in 0..n {
            f[i][0] = nums[i];
            for j in 1..n {
                f[i][j] = f[i][j - 1].min(nums[(i - j + n) % n]);
            }
        }
        let mut ans = i64::MAX;
        for j in 0..n {
            let mut cost = (x as i64) * (j as i64);
            for i in 0..n {
                cost += f[i][j] as i64;
            }
            ans = ans.min(cost);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
