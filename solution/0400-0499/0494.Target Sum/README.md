---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0494.Target%20Sum/README.md
tags:
    - 数组
    - 动态规划
    - 回溯
---

<!-- problem:start -->

# [494. 目标和](https://leetcode.cn/problems/target-sum)

[English Version](/solution/0400-0499/0494.Target%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个非负整数数组 <code>nums</code> 和一个整数 <code>target</code> 。</p>

<p>向数组中的每个整数前添加&nbsp;<code>'+'</code> 或 <code>'-'</code> ，然后串联起所有整数，可以构造一个 <strong>表达式</strong> ：</p>

<ul>
	<li>例如，<code>nums = [2, 1]</code> ，可以在 <code>2</code> 之前添加 <code>'+'</code> ，在 <code>1</code> 之前添加 <code>'-'</code> ，然后串联起来得到表达式 <code>"+2-1"</code> 。</li>
</ul>

<p>返回可以通过上述方法构造的、运算结果等于 <code>target</code> 的不同 <strong>表达式</strong> 的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,1,1], target = 3
<strong>输出：</strong>5
<strong>解释：</strong>一共有 5 种方法让最终目标和为 3 。
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1], target = 1
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 20</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>0 &lt;= sum(nums[i]) &lt;= 1000</code></li>
	<li><code>-1000 &lt;= target &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们记数组 $\text{nums}$ 所有元素的和为 $s$，添加负号的元素之和为 $x$，则添加正号的元素之和为 $s - x$，则有：

$$
(s - x) - x = \text{target} \Rightarrow x = \frac{s - \text{target}}{2}
$$

由于 $x \geq 0$，且 $x$ 为整数，所以 $s \geq \text{target}$ 且 $s - \text{target}$ 为偶数。如果不满足这两个条件，则直接返回 $0$。

接下来，我们可以将问题转化为：在数组 $\text{nums}$ 中选取若干元素，使得这些元素之和等于 $\frac{s - \text{target}}{2}$，问有多少种选取方法。

我们可以使用动态规划来解决这个问题。定义 $f[i][j]$ 表示在数组 $\text{nums}$ 的前 $i$ 个元素中选取若干元素，使得这些元素之和等于 $j$ 的选取方案数。

对于 $\text{nums}[i - 1]$，我们有两种选择：选取或不选取。如果我们不选取 $\text{nums}[i - 1]$，则 $f[i][j] = f[i - 1][j]$；如果我们选取 $\text{nums}[i - 1]$，则 $f[i][j] = f[i - 1][j - \text{nums}[i - 1]]$。因此，状态转移方程为：

$$
f[i][j] = f[i - 1][j] + f[i - 1][j - \text{nums}[i - 1]]
$$

其中，选取的前提是 $j \geq \text{nums}[i - 1]$。

最终答案即为 $f[m][n]$。其中 $m$ 为数组 $\text{nums}$ 的长度，而 $n = \frac{s - \text{target}}{2}$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。

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

### 方法二：动态规划（空间优化）

我们可以发现，方法一中的状态转移方程中，$f[i][j]$ 的值只和 $f[i - 1][j]$ 以及 $f[i - 1][j - \text{nums}[i - 1]]$ 有关，因此我们去掉第一维空间，只使用一维数组即可。

时间复杂度 $O(m \times n)$，空间复杂度 $O(n)$。

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
