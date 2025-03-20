---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0486.Predict%20the%20Winner/README.md
tags:
    - 递归
    - 数组
    - 数学
    - 动态规划
    - 博弈
---

<!-- problem:start -->

# [486. 预测赢家](https://leetcode.cn/problems/predict-the-winner)

[English Version](/solution/0400-0499/0486.Predict%20the%20Winner/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。</p>

<p>玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 <code>0</code> 。每一回合，玩家从数组的任意一端取一个数字（即，<code>nums[0]</code> 或 <code>nums[nums.length - 1]</code>），取到的数字将会从数组中移除（数组长度减 <code>1</code> ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。</p>

<p>如果玩家 1 能成为赢家，返回 <code>true</code> 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 <code>true</code> 。你可以假设每个玩家的玩法都会使他的分数最大化。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,2]
<strong>输出：</strong>false
<strong>解释：</strong>一开始，玩家 1 可以从 1 和 2 中进行选择。
如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。 
所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
因此，玩家 1 永远不会成为赢家，返回 false 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,5,233,7]
<strong>输出：</strong>true
<strong>解释：</strong>玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 true，表示玩家 1 可以成为赢家。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 20</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $\textit{dfs}(i, j)$，表示从第 $i$ 个数到第 $j$ 个数，当前玩家与另一个玩家的得分之差的最大值。那么答案就是 $\textit{dfs}(0, n - 1) \geq 0$。

函数 $\textit{dfs}(i, j)$ 的计算方法如下：

-   如果 $i > j$，说明当前没有数字了，所以当前玩家没有分数可以拿，差值为 $0$，即 $\textit{dfs}(i, j) = 0$。
-   否则，当前玩家有两种选择，如果选择第 $i$ 个数，那么当前玩家与另一个玩家的得分之差为 $\textit{nums}[i] - \textit{dfs}(i + 1, j)$；如果选择第 $j$ 个数，那么当前玩家与另一个玩家的得分之差为 $\textit{nums}[j] - \textit{dfs}(i, j - 1)$。当前玩家会选择两种情况中差值较大的情况，也就是说 $\textit{dfs}(i, j) = \max(\textit{nums}[i] - \textit{dfs}(i + 1, j), \textit{nums}[j] - \textit{dfs}(i, j - 1))$。

最后，我们只需要判断 $\textit{dfs}(0, n - 1) \geq 0$ 即可。

为了避免重复计算，我们可以使用记忆化搜索的方法，用一个数组 $f$ 记录所有的 $\textit{dfs}(i, j)$ 的值，当函数再次被调用到时，我们可以直接从 $f$ 中取出答案而不需要重新计算。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def predictTheWinner(self, nums: List[int]) -> bool:
        @cache
        def dfs(i: int, j: int) -> int:
            if i > j:
                return 0
            return max(nums[i] - dfs(i + 1, j), nums[j] - dfs(i, j - 1))

        return dfs(0, len(nums) - 1) >= 0
```

#### Java

```java
class Solution {
    private int[] nums;
    private int[][] f;

    public boolean predictTheWinner(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        f = new int[n][n];
        return dfs(0, n - 1) >= 0;
    }

    private int dfs(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (f[i][j] != 0) {
            return f[i][j];
        }
        return f[i][j] = Math.max(nums[i] - dfs(i + 1, j), nums[j] - dfs(i, j - 1));
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool predictTheWinner(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> f(n, vector<int>(n));
        auto dfs = [&](this auto&& dfs, int i, int j) -> int {
            if (i > j) {
                return 0;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            return f[i][j] = max(nums[i] - dfs(i + 1, j), nums[j] - dfs(i, j - 1));
        };
        return dfs(0, n - 1) >= 0;
    }
};
```

#### Go

```go
func predictTheWinner(nums []int) bool {
	n := len(nums)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return 0
		}
		if f[i][j] == 0 {
			f[i][j] = max(nums[i]-dfs(i+1, j), nums[j]-dfs(i, j-1))
		}
		return f[i][j]
	}
	return dfs(0, n-1) >= 0
}
```

#### TypeScript

```ts
function predictTheWinner(nums: number[]): boolean {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    const dfs = (i: number, j: number): number => {
        if (i > j) {
            return 0;
        }
        if (f[i][j] === 0) {
            f[i][j] = Math.max(nums[i] - dfs(i + 1, j), nums[j] - dfs(i, j - 1));
        }
        return f[i][j];
    };
    return dfs(0, n - 1) >= 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn predict_the_winner(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut f = vec![vec![0; n]; n];
        Self::dfs(&nums, &mut f, 0, n - 1) >= 0
    }

    fn dfs(nums: &Vec<i32>, f: &mut Vec<Vec<i32>>, i: usize, j: usize) -> i32 {
        if i == j {
            return nums[i] as i32;
        }
        if f[i][j] != 0 {
            return f[i][j];
        }
        f[i][j] = std::cmp::max(
            nums[i] - Self::dfs(nums, f, i + 1, j),
            nums[j] - Self::dfs(nums, f, i, j - 1)
        );
        f[i][j]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

我们也可以使用动态规划的方法，定义 $f[i][j]$ 表示当前玩家在 $\textit{nums}[i..j]$ 这些数字中能够获得的最大得分的差值。那么最后答案就是 $f[0][n - 1] \geq 0$。

初始时 $f[i][i]=\textit{nums}[i]$，因为只有一个数，所以当前玩家只能拿取这个数，得分差值为 $\textit{nums}[i]$。

考虑 $f[i][j]$，其中 $i < j$，有两种情况：

-   如果当前玩家拿走了 $\textit{nums}[i]$，那么剩下的数字为 $\textit{nums}[i + 1..j]$，此时轮到另一个玩家进行游戏，所以 $f[i][j] = \textit{nums}[i] - f[i + 1][j]$。
-   如果当前玩家拿走了 $\textit{nums}[j]$，那么剩下的数字为 $\textit{nums}[i..j - 1]$，此时轮到另一个玩家进行游戏，所以 $f[i][j] = \textit{nums}[j] - f[i][j - 1]$。

因此，最终的状态转移方程为 $f[i][j] = \max(\textit{nums}[i] - f[i + 1][j], \textit{nums}[j] - f[i][j - 1])$。

最后，我们只需要判断 $f[0][n - 1] \geq 0$ 即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

相似题目：

-   [877. 石子游戏](https://github.com/doocs/leetcode/blob/main/solution/0800-0899/0877.Stone%20Game/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def predictTheWinner(self, nums: List[int]) -> bool:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        for i, x in enumerate(nums):
            f[i][i] = x
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                f[i][j] = max(nums[i] - f[i + 1][j], nums[j] - f[i][j - 1])
        return f[0][n - 1] >= 0
```

#### Java

```java
class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][i] = nums[i];
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = Math.max(nums[i] - f[i + 1][j], nums[j] - f[i][j - 1]);
            }
        }
        return f[0][n - 1] >= 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool predictTheWinner(vector<int>& nums) {
        int n = nums.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            f[i][i] = nums[i];
        }
        for (int i = n - 2; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = max(nums[i] - f[i + 1][j], nums[j] - f[i][j - 1]);
            }
        }
        return f[0][n - 1] >= 0;
    }
};
```

#### Go

```go
func predictTheWinner(nums []int) bool {
	n := len(nums)
	f := make([][]int, n)
	for i, x := range nums {
		f[i] = make([]int, n)
		f[i][i] = x
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			f[i][j] = max(nums[i]-f[i+1][j], nums[j]-f[i][j-1])
		}
	}
	return f[0][n-1] >= 0
}
```

#### TypeScript

```ts
function predictTheWinner(nums: number[]): boolean {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(0));
    for (let i = 0; i < n; ++i) {
        f[i][i] = nums[i];
    }
    for (let i = n - 2; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            f[i][j] = Math.max(nums[i] - f[i + 1][j], nums[j] - f[i][j - 1]);
        }
    }
    return f[0][n - 1] >= 0;
}
```

#### Rust

```rust
impl Solution {
    pub fn predict_the_winner(nums: Vec<i32>) -> bool {
        let n = nums.len();
        let mut f = vec![vec![0; n]; n];

        for i in 0..n {
            f[i][i] = nums[i];
        }

        for i in (0..n - 1).rev() {
            for j in i + 1..n {
                f[i][j] = std::cmp::max(nums[i] - f[i + 1][j], nums[j] - f[i][j - 1]);
            }
        }

        f[0][n - 1] >= 0
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
