---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0416.Partition%20Equal%20Subset%20Sum/README.md
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [416. 分割等和子集](https://leetcode.cn/problems/partition-equal-subset-sum)

[English Version](/solution/0400-0499/0416.Partition%20Equal%20Subset%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>只包含正整数 </strong>的 <strong>非空 </strong>数组 <code>nums</code> 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,11,5]
<strong>输出：</strong>true
<strong>解释：</strong>数组可以分割成 [1, 5, 5] 和 [11] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,5]
<strong>输出：</strong>false
<strong>解释：</strong>数组不能分割成两个元素和相等的子集。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 200</code></li>
	<li><code>1 <= nums[i] <= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们先计算出数组的总和 $s$，如果总和是奇数，那么一定不能分割成两个和相等的子集，直接返回 $false$。如果总和是偶数，我们记目标子集的和为 $m = \frac{s}{2}$，那么问题就转化成了：是否存在一个子集，使得其元素的和为 $m$。

我们定义 $f[i][j]$ 表示前 $i$ 个数中选取若干个数，使得其元素的和恰好为 $j$。初始时 $f[0][0] = true$，其余 $f[i][j] = false$。答案为 $f[n][m]$。

考虑 $f[i][j]$，如果我们选取了第 $i$ 个数 $x$，那么 $f[i][j] = f[i - 1][j - x]$；如果我们没有选取第 $i$ 个数 $x$，那么 $f[i][j] = f[i - 1][j]$。因此状态转移方程为：

$$
f[i][j] = f[i - 1][j] \textit{ or } f[i - 1][j - x] \textit{ if } j \geq x
$$

最终答案为 $f[n][m]$。

时间复杂度 $(m \times n)$，空间复杂度 $(m \times n)$。其中 $m$ 和 $n$ 分别为数组的总和的一半和数组的长度。

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

### 方法二：动态规划（空间优化）

我们注意到，方法一中 $f[i][j]$ 只与 $f[i - 1][\cdot]$ 有关，因此我们可以将二维数组压缩成一维数组。

时间复杂度 $O(n \times m)$，空间复杂度 $O(m)$。其中 $n$ 是数组的长度，而 $m$ 是数组的总和的一半。

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
