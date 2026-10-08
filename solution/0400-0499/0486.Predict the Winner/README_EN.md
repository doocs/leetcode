---
comments: true
difficulty: Medium
tags:
    - Recursion
    - Minimax
    - Array
    - Math
    - Dynamic Programming
    - Game Theory
    - Zero-Sum Game
---

<!-- problem:start -->

# [486. Predict the Winner](https://leetcode.com/problems/predict-the-winner)

[中文文档](/solution/0400-0499/0486.Predict%20the%20Winner/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Two players are playing a game with this array: Player 1 and Player 2.</p>

<p>Player 1 and Player 2 take turns, with Player 1 starting first. Both players start the game with a score of 0.</p>

<p>At each turn, the current player takes the number at <strong>either end</strong> of the array (i.e., <code>nums[0]</code> or <code>nums[nums.length - 1]</code>), <strong>removing</strong> it from the array and adding it to their own score. The game ends when there are no more elements in the array.</p>

<p>Return <code>true</code> if Player 1&#39;s final score is <strong>greater than or equal</strong> to Player 2&#39;s final score, and <code>false</code> otherwise.</p>

<p><strong>Note</strong> that a tie counts as a win for Player 1. You may assume that both players play optimally.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,5,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Player 1 must open by taking either 1 or 2, and both choices lead to the same result.</li>
	<li>If Player 1 takes 1, the array becomes <code>[5,2]</code>. Player 2 takes 5, leaving 2 for Player 1.</li>
	<li>If Player 1 takes 2, the array becomes <code>[1,5]</code>. Player 2 takes 5, leaving 1 for Player 1.</li>
	<li>Either way, Player 1 finishes with <code>1 + 2 = 3</code> and Player 2 finishes with <code>5</code>. Player 1 can never win, so return <code>false</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,5,233,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Player 1 first takes 1, leaving <code>[5,233,7]</code>. Player 2 must then choose between 5 and 7, and no matter which number Player 2 chooses, Player 1 can take 233 on the next turn.</li>
	<li>Player 1 finishes with a higher score (234) than Player 2 (12), so return <code>true</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 20</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

<!-- thinking:start -->

> **Thinking**
>
> Players take from the two ends; the first player wins if her score is at least the second's. The game tree repeats the same interval.
>
> $dfs(i,j)$ is the best score gap on $[i,j]$: take the left end for $nums[i]-dfs(i+1,j)$, or the right end symmetrically. The first player wins iff $dfs(0,n-1)\ge 0$.
>
> There are $O(n^2)$ intervals; memoization makes every leftover interval optimal for the player to move.

<!-- thinking:end -->

We design a function $\textit{dfs}(i, j)$, which represents the maximum difference in scores between the current player and the other player from the $i$-th number to the $j$-th number. The answer is $\textit{dfs}(0, n - 1) \geq 0$.

The function $\textit{dfs}(i, j)$ is calculated as follows:

- If $i > j$, it means there are no numbers left, so the current player cannot take any points, and the difference is $0$, i.e., $\textit{dfs}(i, j) = 0$.
- Otherwise, the current player has two choices. If they choose the $i$-th number, the difference in scores between the current player and the other player is $\textit{nums}[i] - \textit{dfs}(i + 1, j)$. If they choose the $j$-th number, the difference in scores between the current player and the other player is $\textit{nums}[j] - \textit{dfs}(i, j - 1)$. The current player will choose the option with the larger difference, so $\textit{dfs}(i, j) = \max(\textit{nums}[i] - \textit{dfs}(i + 1, j), \textit{nums}[j] - \textit{dfs}(i, j - 1))$.

Finally, we only need to check if $\textit{dfs}(0, n - 1) \geq 0$.

To avoid repeated calculations, we can use memoization. We use an array $f$ to record all the values of $\textit{dfs}(i, j)$. When the function is called again, we can directly retrieve the answer from $f$ without recalculating it.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the array $\textit{nums}$.

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

### Solution 2: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> The same recurrence fills a table: $f[i][j]$ starts from the length-$1$ diagonal, then grows by decreasing $i$ and increasing $j$. No call stack, same asymptotics.

<!-- thinking:end -->

We can also use dynamic programming. Define $f[i][j]$ to represent the maximum score difference the current player can achieve in the range $\textit{nums}[i..j]$. The final answer is $f[0][n - 1] \geq 0$.

Initially, $f[i][i] = \textit{nums}[i]$, because with only one number, the current player can only take that number, and the score difference is $\textit{nums}[i]$.

Consider $f[i][j]$ where $i < j$, there are two cases:

- If the current player takes $\textit{nums}[i]$, the remaining numbers are $\textit{nums}[i + 1..j]$, and it is the other player's turn. So, $f[i][j] = \textit{nums}[i] - f[i + 1][j]$.
- If the current player takes $\textit{nums}[j]$, the remaining numbers are $\textit{nums}[i..j - 1]$, and it is the other player's turn. So, $f[i][j] = \textit{nums}[j] - f[i][j - 1]$.

Therefore, the state transition equation is $f[i][j] = \max(\textit{nums}[i] - f[i + 1][j], \textit{nums}[j] - f[i][j - 1])$.

Finally, we only need to check if $f[0][n - 1] \geq 0$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the array $\textit{nums}$.

Similar problem:

- [877. Stone Game](https://github.com/doocs/leetcode/blob/main/solution/0800-0899/0877.Stone%20Game/README_EN.md)

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
