# [322. 零钱兑换](https://leetcode.cn/problems/coin-change)

[English Version](/solution/0300-0399/0322.Coin%20Change/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>coins</code> ，表示不同面额的硬币；以及一个整数 <code>amount</code> ，表示总金额。</p>

<p>计算并返回可以凑成总金额所需的 <strong>最少的硬币个数</strong> 。如果没有任何一种硬币组合能组成总金额，返回&nbsp;<code>-1</code> 。</p>

<p>你可以认为每种硬币的数量是无限的。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>coins = <code>[1, 2, 5]</code>, amount = <code>11</code>
<strong>输出：</strong><code>3</code> 
<strong>解释：</strong>11 = 5 + 5 + 1</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>coins = <code>[2]</code>, amount = <code>3</code>
<strong>输出：</strong>-1</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>coins = [1], amount = 0
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= coins.length &lt;= 12</code></li>
	<li><code>1 &lt;= coins[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= amount &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一：动态规划(完全背包)

我们定义 $f[i][j]$ 表示使用前 $i$ 种硬币，凑出金额 $j$ 的最少硬币数。初始时 $f[0][0] = 0$，其余位置的值均为正无穷。

我们可以枚举使用的最后一枚硬币的数量 $k$，那么有：

$$
f[i][j] = \min(f[i - 1][j], f[i - 1][j - x] + 1, \cdots, f[i - 1][j - k \times x] + k)
$$

其中 $x$ 表示第 $i$ 种硬币的面值。

不妨令 $j = j - x$，那么有：

$$
f[i][j - x] = \min(f[i - 1][j - x], f[i - 1][j - 2 \times x] + 1, \cdots, f[i - 1][j - k \times x] + k - 1)
$$

将二式代入一式，我们可以得到以下状态转移方程：

$$
f[i][j] = \min(f[i - 1][j], f[i][j - x] + 1)
$$

最后答案即为 $f[m][n]$。

时间复杂度 $O(m \times n)$，空间复杂度 $O(m \times n)$。其中 $m$ 和 $n$ 分别为硬币的种类数和总金额。

注意到 $f[i][j]$ 只与 $f[i - 1][j]$ 和 $f[i][j - x]$ 有关，因此我们可以将二维数组优化为一维数组，空间复杂度降为 $O(n)$。

相似题目：

-   [279. 完全平方数](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0279.Perfect%20Squares/README.md)

<!-- tabs:start -->

```python
class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        m, n = len(coins), amount
        f = [[inf] * (n + 1) for _ in range(m + 1)]
        f[0][0] = 0
        for i, x in enumerate(coins, 1):
            for j in range(n + 1):
                f[i][j] = f[i - 1][j]
                if j >= x:
                    f[i][j] = min(f[i][j], f[i][j - x] + 1)
        return -1 if f[m][n] >= inf else f[m][n]
```

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        final int inf = 1 << 30;
        int m = coins.length;
        int n = amount;
        int[][] f = new int[m + 1][n + 1];
        for (var g : f) {
            Arrays.fill(g, inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= coins[i - 1]) {
                    f[i][j] = Math.min(f[i][j], f[i][j - coins[i - 1]] + 1);
                }
            }
        }
        return f[m][n] >= inf ? -1 : f[m][n];
    }
}
```

```cpp
class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        int m = coins.size(), n = amount;
        int f[m + 1][n + 1];
        memset(f, 0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= coins[i - 1]) {
                    f[i][j] = min(f[i][j], f[i][j - coins[i - 1]] + 1);
                }
            }
        }
        return f[m][n] > n ? -1 : f[m][n];
    }
};
```

```go
func coinChange(coins []int, amount int) int {
	m, n := len(coins), amount
	f := make([][]int, m+1)
	const inf = 1 << 30
	for i := range f {
		f[i] = make([]int, n+1)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= m; i++ {
		for j := 0; j <= n; j++ {
			f[i][j] = f[i-1][j]
			if j >= coins[i-1] {
				f[i][j] = min(f[i][j], f[i][j-coins[i-1]]+1)
			}
		}
	}
	if f[m][n] > n {
		return -1
	}
	return f[m][n]
}
```

```ts
function coinChange(coins: number[], amount: number): number {
    const m = coins.length;
    const n = amount;
    const f: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(1 << 30));
    f[0][0] = 0;
    for (let i = 1; i <= m; ++i) {
        for (let j = 0; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (j >= coins[i - 1]) {
                f[i][j] = Math.min(f[i][j], f[i][j - coins[i - 1]] + 1);
            }
        }
    }
    return f[m][n] > n ? -1 : f[m][n];
}
```

```rust
impl Solution {
    pub fn coin_change(coins: Vec<i32>, amount: i32) -> i32 {
        let n = amount as usize;
        let mut f = vec![n + 1; n + 1];
        f[0] = 0;
        for &x in &coins {
            for j in x as usize..=n {
                f[j] = f[j].min(f[j - (x as usize)] + 1);
            }
        }
        if f[n] > n {
            -1
        } else {
            f[n] as i32
        }
    }
}
```

```js
/**
 * @param {number[]} coins
 * @param {number} amount
 * @return {number}
 */
var coinChange = function (coins, amount) {
    const m = coins.length;
    const n = amount;
    const f = Array(m + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(1 << 30));
    f[0][0] = 0;
    for (let i = 1; i <= m; ++i) {
        for (let j = 0; j <= n; ++j) {
            f[i][j] = f[i - 1][j];
            if (j >= coins[i - 1]) {
                f[i][j] = Math.min(f[i][j], f[i][j - coins[i - 1]] + 1);
            }
        }
    }
    return f[m][n] > n ? -1 : f[m][n];
};
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        n = amount
        f = [0] + [inf] * n
        for x in coins:
            for j in range(x, n + 1):
                f[j] = min(f[j], f[j - x] + 1)
        return -1 if f[n] >= inf else f[n]
```

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        final int inf = 1 << 30;
        int n = amount;
        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int x : coins) {
            for (int j = x; j <= n; ++j) {
                f[j] = Math.min(f[j], f[j - x] + 1);
            }
        }
        return f[n] >= inf ? -1 : f[n];
    }
}
```

```cpp
class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        int n = amount;
        int f[n + 1];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int x : coins) {
            for (int j = x; j <= n; ++j) {
                f[j] = min(f[j], f[j - x] + 1);
            }
        }
        return f[n] > n ? -1 : f[n];
    }
};
```

```go
func coinChange(coins []int, amount int) int {
	n := amount
	f := make([]int, n+1)
	for i := range f {
		f[i] = 1 << 30
	}
	f[0] = 0
	for _, x := range coins {
		for j := x; j <= n; j++ {
			f[j] = min(f[j], f[j-x]+1)
		}
	}
	if f[n] > n {
		return -1
	}
	return f[n]
}
```

```ts
function coinChange(coins: number[], amount: number): number {
    const n = amount;
    const f: number[] = Array(n + 1).fill(1 << 30);
    f[0] = 0;
    for (const x of coins) {
        for (let j = x; j <= n; ++j) {
            f[j] = Math.min(f[j], f[j - x] + 1);
        }
    }
    return f[n] > n ? -1 : f[n];
}
```

```js
/**
 * @param {number[]} coins
 * @param {number} amount
 * @return {number}
 */
var coinChange = function (coins, amount) {
    const n = amount;
    const f = Array(n + 1).fill(1 << 30);
    f[0] = 0;
    for (const x of coins) {
        for (let j = x; j <= n; ++j) {
            f[j] = Math.min(f[j], f[j - x] + 1);
        }
    }
    return f[n] > n ? -1 : f[n];
};
```

<!-- tabs:end -->

<!-- end -->
