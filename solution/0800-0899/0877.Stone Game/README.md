---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0877.Stone%20Game/README.md
tags:
    - 数组
    - 数学
    - 动态规划
    - 博弈
---

<!-- problem:start -->

# [877. 石子游戏](https://leetcode.cn/problems/stone-game)

[English Version](/solution/0800-0899/0877.Stone%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，<strong>排成一行</strong>；每堆都有 <strong>正</strong> 整数颗石子，数目为 <code>piles[i]</code>&nbsp;。</p>

<p>游戏以谁手中的石子最多来决出胜负。石子的 <strong>总数</strong> 是 <strong>奇数</strong> ，所以没有平局。</p>

<p>Alice 和 Bob 轮流进行，<strong>Alice 先开始</strong> 。 每回合，玩家从行的 <strong>开始</strong> 或 <strong>结束</strong> 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中 <strong>石子最多</strong> 的玩家 <strong>获胜</strong> 。</p>

<p>假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回&nbsp;<code>true</code>&nbsp;，当 Bob 赢得比赛时返回&nbsp;<code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>piles = [5,3,4,5]
<strong>输出：</strong>true
<strong>解释：</strong>
Alice 先开始，只能拿前 5 颗或后 5 颗石子 。
假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
如果 Bob 拿走前 3 颗，那么剩下的是 [4,5]，Alice 拿走后 5 颗赢得 10 分。
如果 Bob 拿走后 5 颗，那么剩下的是 [3,4]，Alice 拿走后 4 颗赢得 9 分。
这表明，取前 5 颗石子对 Alice 来说是一个胜利的举动，所以返回 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>piles = [3,7,2,3]
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= piles.length &lt;= 500</code></li>
	<li><code>piles.length</code> 是 <strong>偶数</strong></li>
	<li><code>1 &lt;= piles[i] &lt;= 500</code></li>
	<li><code>sum(piles[i])</code>&nbsp;是 <strong>奇数</strong></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $dfs(i, j)$，表示从第 $i$ 堆石子到第 $j$ 堆石子，当前玩家与另一个玩家的石子数量之差的最大值。那么答案就是 $dfs(0, n - 1) \gt 0$。

函数 $dfs(i, j)$ 的计算方法如下：

-   如果 $i \gt j$，说明当前没有石子了，所以当前玩家没有石子可以拿，差值为 $0$，即 $dfs(i, j) = 0$。
-   否则，当前玩家有两种选择，如果选择第 $i$ 堆石子，那么当前玩家与另一个玩家的石子数量之差为 $piles[i] - dfs(i + 1, j)$；如果选择第 $j$ 堆石子，那么当前玩家与另一个玩家的石子数量之差为 $piles[j] - dfs(i, j - 1)$。当前玩家会选择两种情况中差值较大的情况，也就是说 $dfs(i, j) = \max(piles[i] - dfs(i + 1, j), piles[j] - dfs(i, j - 1))$。

最后，我们只需要判断 $dfs(0, n - 1) \gt 0$ 即可。

为了避免重复计算，我们可以使用记忆化搜索的方法，用一个数组 $f$ 记录所有的 $dfs(i, j)$ 的值，当函数再次被调用到时，我们可以直接从 $f$ 中取出答案而不需要重新计算。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是石子的堆数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stoneGame(self, piles: List[int]) -> bool:
        @cache
        def dfs(i: int, j: int) -> int:
            if i > j:
                return 0
            return max(piles[i] - dfs(i + 1, j), piles[j] - dfs(i, j - 1))

        return dfs(0, len(piles) - 1) > 0
```

#### Java

```java
class Solution {
    private int[] piles;
    private int[][] f;

    public boolean stoneGame(int[] piles) {
        this.piles = piles;
        int n = piles.length;
        f = new int[n][n];
        return dfs(0, n - 1) > 0;
    }

    private int dfs(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (f[i][j] != 0) {
            return f[i][j];
        }
        return f[i][j] = Math.max(piles[i] - dfs(i + 1, j), piles[j] - dfs(i, j - 1));
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool stoneGame(vector<int>& piles) {
        int n = piles.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        auto dfs = [&](auto&& dfs, int i, int j) -> int {
            if (i > j) {
                return 0;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            return f[i][j] = max(piles[i] - dfs(dfs, i + 1, j), piles[j] - dfs(dfs, i, j - 1));
        };
        return dfs(dfs, 0, n - 1) > 0;
    }
};
```

#### Go

```go
func stoneGame(piles []int) bool {
	n := len(piles)
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
			f[i][j] = max(piles[i]-dfs(i+1, j), piles[j]-dfs(i, j-1))
		}
		return f[i][j]
	}
	return dfs(0, n-1) > 0
}
```

#### TypeScript

```ts
function stoneGame(piles: number[]): boolean {
    const n = piles.length;
    const f: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    const dfs = (i: number, j: number): number => {
        if (i > j) {
            return 0;
        }
        if (f[i][j] === 0) {
            f[i][j] = Math.max(piles[i] - dfs(i + 1, j), piles[j] - dfs(i, j - 1));
        }
        return f[i][j];
    };
    return dfs(0, n - 1) > 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

我们也可以使用动态规划的方法，定义 $f[i][j]$ 表示当前玩家在 $piles[i..j]$ 这部分石子中能够获得的最大石子数的差值。那么最后答案就是 $f[0][n - 1] \gt 0$。

初始时 $f[i][i]=piles[i]$，因为只有一堆石子，所以当前玩家只能拿取这堆石子，差值为 $piles[i]$。

考虑 $f[i][j]$，其中 $i \lt j$，有两种情况：

-   如果当前玩家拿走了石子堆 $piles[i]$，那么剩下的石子堆为 $piles[i + 1..j]$，此时轮到另一个玩家进行游戏，所以 $f[i][j] = piles[i] - f[i + 1][j]$。
-   如果当前玩家拿走了石子堆 $piles[j]$，那么剩下的石子堆为 $piles[i..j - 1]$，此时轮到另一个玩家进行游戏，所以 $f[i][j] = piles[j] - f[i][j - 1]$。

因此，最终的状态转移方程为 $f[i][j] = \max(piles[i] - f[i + 1][j], piles[j] - f[i][j - 1])$。

最后，我们只需要判断 $f[0][n - 1] \gt 0$ 即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是石子的堆数。

相似题目：

-   [486. 预测赢家](https://github.com/doocs/leetcode/blob/main/solution/0400-0499/0486.Predict%20the%20Winner/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stoneGame(self, piles: List[int]) -> bool:
        n = len(piles)
        f = [[0] * n for _ in range(n)]
        for i, x in enumerate(piles):
            f[i][i] = x
        for i in range(n - 2, -1, -1):
            for j in range(i + 1, n):
                f[i][j] = max(piles[i] - f[i + 1][j], piles[j] - f[i][j - 1])
        return f[0][n - 1] > 0
```

#### Java

```java
class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] f = new int[n][n];
        for (int i = 0; i < n; ++i) {
            f[i][i] = piles[i];
        }
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = Math.max(piles[i] - f[i + 1][j], piles[j] - f[i][j - 1]);
            }
        }
        return f[0][n - 1] > 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool stoneGame(vector<int>& piles) {
        int n = piles.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        for (int i = 0; i < n; ++i) {
            f[i][i] = piles[i];
        }
        for (int i = n - 2; ~i; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = max(piles[i] - f[i + 1][j], piles[j] - f[i][j - 1]);
            }
        }
        return f[0][n - 1] > 0;
    }
};
```

#### Go

```go
func stoneGame(piles []int) bool {
	n := len(piles)
	f := make([][]int, n)
	for i, x := range piles {
		f[i] = make([]int, n)
		f[i][i] = x
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			f[i][j] = max(piles[i]-f[i+1][j], piles[j]-f[i][j-1])
		}
	}
	return f[0][n-1] > 0
}
```

#### TypeScript

```ts
function stoneGame(piles: number[]): boolean {
    const n = piles.length;
    const f: number[][] = new Array(n).fill(0).map(() => new Array(n).fill(0));
    for (let i = 0; i < n; ++i) {
        f[i][i] = piles[i];
    }
    for (let i = n - 2; i >= 0; --i) {
        for (let j = i + 1; j < n; ++j) {
            f[i][j] = Math.max(piles[i] - f[i + 1][j], piles[j] - f[i][j - 1]);
        }
    }
    return f[0][n - 1] > 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
