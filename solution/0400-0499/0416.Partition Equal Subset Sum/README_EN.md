---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0416.Partition%20Equal%20Subset%20Sum/README_EN.md
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum)

[中文文档](/solution/0400-0499/0416.Partition%20Equal%20Subset%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code>, return <code>true</code> <em>if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or </em><code>false</code><em> otherwise</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,11,5]
<strong>Output:</strong> true
<strong>Explanation:</strong> The array can be partitioned as [1, 5, 5] and [11].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,5]
<strong>Output:</strong> false
<strong>Explanation:</strong> The array cannot be partitioned into equal sum subsets.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

First, we calculate the total sum $s$ of the array. If the total sum is odd, it cannot be divided into two subsets with equal sums, so we directly return `false`. If the total sum is even, we set the target subset sum to $m = \frac{s}{2}$. The problem is then transformed into: does there exist a subset whose element sum is $m$?

We define $f[i][j]$ to represent whether it is possible to select several numbers from the first $i$ numbers so that their sum is exactly $j$. Initially, $f[0][0] = true$ and the rest $f[i][j] = false$. The answer is $f[n][m]$.

Considering $f[i][j]$, if we select the $i$-th number $x$, then $f[i][j] = f[i - 1][j - x]$. If we do not select the $i$-th number $x$, then $f[i][j] = f[i - 1][j]$. Therefore, the state transition equation is:

$$
f[i][j] = f[i - 1][j] \textit{ or } f[i - 1][j - x] \textit{ if } j \geq x
$$

The final answer is $f[n][m]$.

The time complexity is $O(m \times n)$, and the space complexity is $O(m \times n)$. Where $m$ and $n$ are half of the total sum of the array and the length of the array, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        m, mod = divmod(sum(nums), 2)
        if mod:
            return False
        n = len(nums)
        f = [[False] * (m + 1) for _ in range(n + 1)]
        f[0][0] = True
        for i, x in enumerate(nums, 1):
            for j in range(m + 1):
                f[i][j] = f[i - 1][j] or (j >= x and f[i - 1][j - x])
        return f[n][m]
```

#### Java

```java
class Solution {
    public boolean canPartition(int[] nums) {
        // int s = Arrays.stream(nums).sum();
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 == 1) {
            return false;
        }
        int n = nums.length;
        int m = s >> 1;
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            int x = nums[i - 1];
            for (int j = 0; j <= m; ++j) {
                f[i][j] = f[i - 1][j] || (j >= x && f[i - 1][j - x]);
            }
        }
        return f[n][m];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s % 2 == 1) {
            return false;
        }
        int n = nums.size();
        int m = s >> 1;
        bool f[n + 1][m + 1];
        memset(f, false, sizeof(f));
        f[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            int x = nums[i - 1];
            for (int j = 0; j <= m; ++j) {
                f[i][j] = f[i - 1][j] || (j >= x && f[i - 1][j - x]);
            }
        }
        return f[n][m];
    }
};
```

#### Go

```go
func canPartition(nums []int) bool {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s%2 == 1 {
		return false
	}
	n, m := len(nums), s>>1
	f := make([][]bool, n+1)
	for i := range f {
		f[i] = make([]bool, m+1)
	}
	f[0][0] = true
	for i := 1; i <= n; i++ {
		x := nums[i-1]
		for j := 0; j <= m; j++ {
			f[i][j] = f[i-1][j] || (j >= x && f[i-1][j-x])
		}
	}
	return f[n][m]
}
```

#### TypeScript

```ts
function canPartition(nums: number[]): boolean {
    const s = nums.reduce((a, b) => a + b, 0);
    if (s % 2 === 1) {
        return false;
    }
    const n = nums.length;
    const m = s >> 1;
    const f: boolean[][] = Array.from({ length: n + 1 }, () => Array(m + 1).fill(false));
    f[0][0] = true;
    for (let i = 1; i <= n; ++i) {
        const x = nums[i - 1];
        for (let j = 0; j <= m; ++j) {
            f[i][j] = f[i - 1][j] || (j >= x && f[i - 1][j - x]);
        }
    }
    return f[n][m];
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn can_partition(nums: Vec<i32>) -> bool {
        let mut sum = 0;
        for e in &nums {
            sum += *e;
        }

        if sum % 2 != 0 {
            return false;
        }

        let n = nums.len();
        let m = (sum / 2) as usize;
        let mut dp: Vec<Vec<bool>> = vec![vec![false; m + 1]; n + 1];

        // Initialize the dp vector
        dp[0][0] = true;

        // Begin the actual dp process
        for i in 1..=n {
            for j in 0..=m {
                dp[i][j] = if (nums[i - 1] as usize) > j {
                    dp[i - 1][j]
                } else {
                    dp[i - 1][j] || dp[i - 1][j - (nums[i - 1] as usize)]
                };
            }
        }

        dp[n][m]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canPartition = function (nums) {
    const s = nums.reduce((a, b) => a + b, 0);
    if (s % 2 === 1) {
        return false;
    }
    const n = nums.length;
    const m = s >> 1;
    const f = Array.from({ length: n + 1 }, () => Array(m + 1).fill(false));
    f[0][0] = true;
    for (let i = 1; i <= n; ++i) {
        const x = nums[i - 1];
        for (let j = 0; j <= m; ++j) {
            f[i][j] = f[i - 1][j] || (j >= x && f[i - 1][j - x]);
        }
    }
    return f[n][m];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Dynamic Programming (Space Optimization)

We notice that in Solution 1, $f[i][j]$ is only related to $f[i - 1][\cdot]$. Therefore, we can compress the two-dimensional array into a one-dimensional array.

The time complexity is $O(n \times m)$, and the space complexity is $O(m)$. Where $n$ is the length of the array, and $m$ is half of the total sum of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        m, mod = divmod(sum(nums), 2)
        if mod:
            return False
        f = [True] + [False] * m
        for x in nums:
            for j in range(m, x - 1, -1):
                f[j] = f[j] or f[j - x]
        return f[m]
```

#### Java

```java
class Solution {
    public boolean canPartition(int[] nums) {
        // int s = Arrays.stream(nums).sum();
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        if (s % 2 == 1) {
            return false;
        }
        int m = s >> 1;
        boolean[] f = new boolean[m + 1];
        f[0] = true;
        for (int x : nums) {
            for (int j = m; j >= x; --j) {
                f[j] |= f[j - x];
            }
        }
        return f[m];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canPartition(vector<int>& nums) {
        int s = accumulate(nums.begin(), nums.end(), 0);
        if (s % 2 == 1) {
            return false;
        }
        int m = s >> 1;
        bool f[m + 1];
        memset(f, false, sizeof(f));
        f[0] = true;
        for (int& x : nums) {
            for (int j = m; j >= x; --j) {
                f[j] |= f[j - x];
            }
        }
        return f[m];
    }
};
```

#### Go

```go
func canPartition(nums []int) bool {
	s := 0
	for _, x := range nums {
		s += x
	}
	if s%2 == 1 {
		return false
	}
	m := s >> 1
	f := make([]bool, m+1)
	f[0] = true
	for _, x := range nums {
		for j := m; j >= x; j-- {
			f[j] = f[j] || f[j-x]
		}
	}
	return f[m]
}
```

#### TypeScript

```ts
function canPartition(nums: number[]): boolean {
    const s = nums.reduce((a, b) => a + b, 0);
    if (s % 2 === 1) {
        return false;
    }
    const m = s >> 1;
    const f: boolean[] = Array(m + 1).fill(false);
    f[0] = true;
    for (const x of nums) {
        for (let j = m; j >= x; --j) {
            f[j] = f[j] || f[j - x];
        }
    }
    return f[m];
}
```

#### Rust

```rust
impl Solution {
    #[allow(dead_code)]
    pub fn can_partition(nums: Vec<i32>) -> bool {
        let mut sum = 0;
        for e in &nums {
            sum += *e;
        }

        if sum % 2 != 0 {
            return false;
        }

        let m = (sum >> 1) as usize;

        // Here dp[i] means if it can be sum up to `i` for all the number we've traversed through so far
        // Which is actually compressing the 2-D dp vector to 1-D
        let mut dp: Vec<bool> = vec![false; m + 1];

        // Initialize the dp vector
        dp[0] = true;

        // Begin the actual dp process
        for e in &nums {
            // For every num in nums vector
            for i in (*e as usize..=m).rev() {
                // Update the current status
                dp[i] |= dp[i - (*e as usize)];
            }
        }

        dp[m]
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canPartition = function (nums) {
    const s = nums.reduce((a, b) => a + b, 0);
    if (s % 2 === 1) {
        return false;
    }
    const m = s >> 1;
    const f = Array(m + 1).fill(false);
    f[0] = true;
    for (const x of nums) {
        for (let j = m; j >= x; --j) {
            f[j] = f[j] || f[j - x];
        }
    }
    return f[m];
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
