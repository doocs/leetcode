---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0494.Target%20Sum/README_EN.md
tags:
    - Array
    - Dynamic Programming
    - Backtracking
---

<!-- problem:start -->

# [494. Target Sum](https://leetcode.com/problems/target-sum)

[中文文档](/solution/0400-0499/0494.Target%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>target</code>.</p>

<p>You want to build an <strong>expression</strong> out of nums by adding one of the symbols <code>&#39;+&#39;</code> and <code>&#39;-&#39;</code> before each integer in nums and then concatenate all the integers.</p>

<ul>
	<li>For example, if <code>nums = [2, 1]</code>, you can add a <code>&#39;+&#39;</code> before <code>2</code> and a <code>&#39;-&#39;</code> before <code>1</code> and concatenate them to build the expression <code>&quot;+2-1&quot;</code>.</li>
</ul>

<p>Return the number of different <strong>expressions</strong> that you can build, which evaluates to <code>target</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1], target = 3
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1], target = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 20</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= sum(nums[i]) &lt;= 1000</code></li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

Let's denote the sum of all elements in the array $\textit{nums}$ as $s$, and the sum of elements to which we assign a negative sign as $x$. Therefore, the sum of elements with a positive sign is $s - x$. We have:

$$
(s - x) - x = \textit{target} \Rightarrow x = \frac{s - \textit{target}}{2}
$$

Since $x \geq 0$ and $x$ must be an integer, it follows that $s \geq \textit{target}$ and $s - \textit{target}$ must be even. If these two conditions are not met, we directly return $0$.

Next, we can transform the problem into: selecting several elements from the array $\textit{nums}$ such that the sum of these elements equals $\frac{s - \textit{target}}{2}$. We are asked how many ways there are to make such a selection.

We can use dynamic programming to solve this problem. Define $f[i][j]$ as the number of ways to select several elements from the first $i$ elements of the array $\textit{nums}$ such that the sum of these elements equals $j$.

For $\textit{nums}[i - 1]$, we have two choices: to select or not to select. If we do not select $\textit{nums}[i - 1]$, then $f[i][j] = f[i - 1][j]$; if we do select $\textit{nums}[i - 1]$, then $f[i][j] = f[i - 1][j - \textit{nums}[i - 1]]$. Therefore, the state transition equation is:

$$
f[i][j] = f[i - 1][j] + f[i - 1][j - \textit{nums}[i - 1]]
$$

This selection is based on the premise that $j \geq \textit{nums}[i - 1]$.

The final answer is $f[m][n]$, where $m$ is the length of the array $\textit{nums}$, and $n = \frac{s - \textit{target}}{2}$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s < target or (s - target) % 2:
            return 0
        m, n = len(nums), (s - target) // 2
        f = [[0] * (n + 1) for _ in range(m + 1)]
        f[0][0] = 1
        for i, x in enumerate(nums, 1):
            for j in range(n + 1):
                f[i][j] = f[i - 1][j]
                if j >= x:
                    f[i][j] += f[i - 1][j - x]
        return f[m][n]
```

#### Java

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int s = Arrays.stream(nums).sum();
        if (s < target || (s - target) % 2 != 0) {
            return 0;
        }
        int m = nums.length;
        int n = (s - target) / 2;
        int[][] f = new int[m + 1][n + 1];
        f[0][0] = 1;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= nums[i - 1]) {
                    f[i][j] += f[i - 1][j - nums[i - 1]];
                }
            }
        }
        return f[m][n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s < target || (s - target) % 2) {
            return 0;
        }
        int m = nums.size();
        int n = (s - target) / 2;
        int f[m + 1][n + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= nums[i - 1]) {
                    f[i][j] += f[i - 1][j - nums[i - 1]];
                }
            }
        }
        return f[m][n];
    }
};
```

#### Go

```go
func findTargetSumWays(nums []int, target int) int {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s < target || (s-target)%2 != 0 {
		return 0
	}
	m, n := len(nums), (s-target)/2
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	f[0][0] = 1
	for i := 1; i <= m; i++ {
		for j := 0; j <= n; j++ {
			f[i][j] = f[i-1][j]
			if j >= nums[i-1] {
				f[i][j] += f[i-1][j-nums[i-1]]
			}
		}
	}
	return f[m][n]
}
```

#### TypeScript

```ts
function findTargetSumWays(nums: number[], target: number): number {
    const s = nums.reduce((a, b) => a + b, 0);
    if (s < target || (s - target) % 2) {
        return 0;
    }
    const [m, n] = [nums.length, ((s - target) / 2) | 0];
    const f: number[][] = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= m; i++) {
        for (let j = 0; j <= n; j++) {
            f[i][j] = f[i - 1][j];
            if (j >= nums[i - 1]) {
                f[i][j] += f[i - 1][j - nums[i - 1]];
            }
        }
    }
    return f[m][n];
}
```

#### Rust

```rust
impl Solution {
    pub fn find_target_sum_ways(nums: Vec<i32>, target: i32) -> i32 {
        let s: i32 = nums.iter().sum();
        if s < target || (s - target) % 2 != 0 {
            return 0;
        }
        let m = nums.len();
        let n = ((s - target) / 2) as usize;
        let mut f = vec![vec![0; n + 1]; m + 1];
        f[0][0] = 1;
        for i in 1..=m {
            for j in 0..=n {
                f[i][j] = f[i - 1][j];
                if j as i32 >= nums[i - 1] {
                    f[i][j] += f[i - 1][j - nums[i - 1] as usize];
                }
            }
        }
        f[m][n]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var findTargetSumWays = function (nums, target) {
    const s = nums.reduce((a, b) => a + b, 0);
    if (s < target || (s - target) % 2) {
        return 0;
    }
    const [m, n] = [nums.length, ((s - target) / 2) | 0];
    const f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= m; i++) {
        for (let j = 0; j <= n; j++) {
            f[i][j] = f[i - 1][j];
            if (j >= nums[i - 1]) {
                f[i][j] += f[i - 1][j - nums[i - 1]];
            }
        }
    }
    return f[m][n];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming (Space Optimization)

We can observe that in the state transition equation of Solution 1, the value of $f[i][j]$ is only related to $f[i - 1][j]$ and $f[i - 1][j - \textit{nums}[i - 1]]$. Therefore, we can eliminate the first dimension of the space and use only a one-dimensional array.

The time complexity is $O(m \times n)$, and the space complexity is $O(n)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findTargetSumWays(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        if s < target or (s - target) % 2:
            return 0
        n = (s - target) // 2
        f = [0] * (n + 1)
        f[0] = 1
        for x in nums:
            for j in range(n, x - 1, -1):
                f[j] += f[j - x]
        return f[n]
```

#### Java

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int s = Arrays.stream(nums).sum();
        if (s < target || (s - target) % 2 != 0) {
            return 0;
        }
        int n = (s - target) / 2;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int num : nums) {
            for (int j = n; j >= num; --j) {
                f[j] += f[j - num];
            }
        }
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s < target || (s - target) % 2) {
            return 0;
        }
        int n = (s - target) / 2;
        int f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int x : nums) {
            for (int j = n; j >= x; --j) {
                f[j] += f[j - x];
            }
        }
        return f[n];
    }
};
```

#### Go

```go
func findTargetSumWays(nums []int, target int) int {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s < target || (s-target)%2 != 0 {
		return 0
	}
	n := (s - target) / 2
	f := make([]int, n+1)
	f[0] = 1
	for _, x := range nums {
		for j := n; j >= x; j-- {
			f[j] += f[j-x]
		}
	}
	return f[n]
}
```

#### TypeScript

```ts
function findTargetSumWays(nums: number[], target: number): number {
    const s = nums.reduce((a, b) => a + b, 0);
    if (s < target || (s - target) % 2) {
        return 0;
    }
    const n = ((s - target) / 2) | 0;
    const f = Array(n + 1).fill(0);
    f[0] = 1;
    for (const x of nums) {
        for (let j = n; j >= x; j--) {
            f[j] += f[j - x];
        }
    }
    return f[n];
}
```

#### Rust

```rust
impl Solution {
    pub fn find_target_sum_ways(nums: Vec<i32>, target: i32) -> i32 {
        let s: i32 = nums.iter().sum();
        if s < target || (s - target) % 2 != 0 {
            return 0;
        }
        let n = ((s - target) / 2) as usize;
        let mut f = vec![0; n + 1];
        f[0] = 1;
        for x in nums {
            for j in (x as usize..=n).rev() {
                f[j] += f[j - x as usize];
            }
        }
        f[n]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var findTargetSumWays = function (nums, target) {
    const s = nums.reduce((a, b) => a + b, 0);
    if (s < target || (s - target) % 2) {
        return 0;
    }
    const n = (s - target) / 2;
    const f = Array(n + 1).fill(0);
    f[0] = 1;
    for (const x of nums) {
        for (let j = n; j >= x; j--) {
            f[j] += f[j - x];
        }
    }
    return f[n];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
