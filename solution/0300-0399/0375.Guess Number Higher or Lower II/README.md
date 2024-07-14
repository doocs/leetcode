---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0375.Guess%20Number%20Higher%20or%20Lower%20II/README.md
tags:
    - 数学
    - 动态规划
    - 博弈
---

<!-- problem:start -->

# [375. 猜数字大小 II](https://leetcode.cn/problems/guess-number-higher-or-lower-ii)

[English Version](/solution/0300-0399/0375.Guess%20Number%20Higher%20or%20Lower%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>我们正在玩一个猜数游戏，游戏规则如下：</p>

<ol>
	<li>我从&nbsp;<code>1</code><strong>&nbsp;</strong>到 <code>n</code> 之间选择一个数字。</li>
	<li>你来猜我选了哪个数字。</li>
	<li>如果你猜到正确的数字，就会 <strong>赢得游戏</strong> 。</li>
	<li>如果你猜错了，那么我会告诉你，我选的数字比你的 <strong>更大或者更小</strong> ，并且你需要继续猜数。</li>
	<li>每当你猜了数字 <code>x</code> 并且猜错了的时候，你需要支付金额为 <code>x</code> 的现金。如果你花光了钱，就会<strong> 输掉游戏</strong> 。</li>
</ol>

<p>给你一个特定的数字 <code>n</code> ，返回能够 <strong>确保你获胜</strong> 的最小现金数，<strong>不管我选择那个数字</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0375.Guess%20Number%20Higher%20or%20Lower%20II/images/graph.png" style="width: 505px; height: 388px;" />
<pre>
<strong>输入：</strong>n = 10
<strong>输出：</strong>16
<strong>解释：</strong>制胜策略如下：
- 数字范围是 [1,10] 。你先猜测数字为 7 。
&nbsp;   - 如果这是我选中的数字，你的总费用为 $0 。否则，你需要支付 $7 。
&nbsp;   - 如果我的数字更大，则下一步需要猜测的数字范围是 [8,10] 。你可以猜测数字为 9 。
&nbsp;       - 如果这是我选中的数字，你的总费用为 $7 。否则，你需要支付 $9 。
&nbsp;       - 如果我的数字更大，那么这个数字一定是 10 。你猜测数字为 10 并赢得游戏，总费用为 $7 + $9 = $16 。
&nbsp;       - 如果我的数字更小，那么这个数字一定是 8 。你猜测数字为 8 并赢得游戏，总费用为 $7 + $9 = $16 。
&nbsp;   - 如果我的数字更小，则下一步需要猜测的数字范围是 [1,6] 。你可以猜测数字为 3 。
&nbsp;       - 如果这是我选中的数字，你的总费用为 $7 。否则，你需要支付 $3 。
&nbsp;       - 如果我的数字更大，则下一步需要猜测的数字范围是 [4,6] 。你可以猜测数字为 5 。
&nbsp;           - 如果这是我选中的数字，你的总费用为 $7 + $3 = $10 。否则，你需要支付 $5 。
&nbsp;           - 如果我的数字更大，那么这个数字一定是 6 。你猜测数字为 6 并赢得游戏，总费用为 $7 + $3 + $5 = $15 。
&nbsp;           - 如果我的数字更小，那么这个数字一定是 4 。你猜测数字为 4 并赢得游戏，总费用为 $7 + $3 + $5 = $15 。
&nbsp;       - 如果我的数字更小，则下一步需要猜测的数字范围是 [1,2] 。你可以猜测数字为 1 。
&nbsp;           - 如果这是我选中的数字，你的总费用为 $7 + $3 = $10 。否则，你需要支付 $1 。
&nbsp;           - 如果我的数字更大，那么这个数字一定是 2 。你猜测数字为 2 并赢得游戏，总费用为 $7 + $3 + $1 = $11 。
在最糟糕的情况下，你需要支付 $16 。因此，你只需要 $16 就可以确保自己赢得游戏。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>0
<strong>解释：</strong>只有一个可能的数字，所以你可以直接猜 1 并赢得游戏，无需支付任何费用。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>1
<strong>解释：</strong>有两个可能的数字 1 和 2 。
- 你可以先猜 1 。
&nbsp;   - 如果这是我选中的数字，你的总费用为 $0 。否则，你需要支付 $1 。
&nbsp;   - 如果我的数字更大，那么这个数字一定是 2 。你猜测数字为 2 并赢得游戏，总费用为 $1 。
最糟糕的情况下，你需要支付 $1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示在区间 $[i, j]$ 中猜中任意一个数最少需要花费的钱数。初始时 $f[i][i] = 0$，因为猜中了唯一的数不需要花费，对于 $i \gt j$ 的情况，也有 $f[i][j] = 0$。答案即为 $f[1][n]$。

对于 $f[i][j]$，我们可以枚举 $[i, j]$ 中的任意一个数 $k$，将区间 $[i, j]$ 分为 $[i, k - 1]$ 和 $[k + 1, j]$ 两部分，选择其中的较大值加上 $k$ 的花费，即 $\max(f[i][k - 1], f[k + 1][j]) + k$ 的最小值。

时间复杂度 $O(n^3)$，空间复杂度 $O(n^2)$。其中 $n$ 为猜测的数字范围。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def getMoneyAmount(self, n: int) -> int:
        f = [[0] * (n + 1) for _ in range(n + 1)]
        for i in range(n - 1, 0, -1):
            for j in range(i + 1, n + 1):
                f[i][j] = j + f[i][j - 1]
                for k in range(i, j):
                    f[i][j] = min(f[i][j], max(f[i][k - 1], f[k + 1][j]) + k)
        return f[1][n]
```

#### Java

```java
class Solution {
    public int getMoneyAmount(int n) {
        int[][] f = new int[n + 1][n + 1];
        for (int i = n - 1; i > 0; --i) {
            for (int j = i + 1; j <= n; ++j) {
                f[i][j] = j + f[i][j - 1];
                for (int k = i; k < j; ++k) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[i][k - 1], f[k + 1][j]) + k);
                }
            }
        }
        return f[1][n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int getMoneyAmount(int n) {
        int f[n + 1][n + 1];
        memset(f, 0, sizeof(f));
        for (int i = n - 1; i; --i) {
            for (int j = i + 1; j <= n; ++j) {
                f[i][j] = j + f[i][j - 1];
                for (int k = i; k < j; ++k) {
                    f[i][j] = min(f[i][j], max(f[i][k - 1], f[k + 1][j]) + k);
                }
            }
        }
        return f[1][n];
    }
};
```

#### Go

```go
func getMoneyAmount(n int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := n - 1; i > 0; i-- {
		for j := i + 1; j <= n; j++ {
			f[i][j] = j + f[i][j-1]
			for k := i; k < j; k++ {
				f[i][j] = min(f[i][j], k+max(f[i][k-1], f[k+1][j]))
			}
		}
	}
	return f[1][n]
}
```

#### TypeScript

```ts
function getMoneyAmount(n: number): number {
    const f: number[][] = Array.from({ length: n + 1 }, () => Array(n + 1).fill(0));
    for (let i = n - 1; i; --i) {
        for (let j = i + 1; j <= n; ++j) {
            f[i][j] = j + f[i][j - 1];
            for (let k = i; k < j; ++k) {
                f[i][j] = Math.min(f[i][j], k + Math.max(f[i][k - 1], f[k + 1][j]));
            }
        }
    }
    return f[1][n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
