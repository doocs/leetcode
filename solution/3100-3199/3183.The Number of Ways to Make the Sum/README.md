---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3183.The%20Number%20of%20Ways%20to%20Make%20the%20Sum/README.md
---

<!-- problem:start -->

# [3183. 达到总和的方法数量 🔒](https://leetcode.cn/problems/the-number-of-ways-to-make-the-sum)

[English Version](/solution/3100-3199/3183.The%20Number%20of%20Ways%20to%20Make%20the%20Sum/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定 <strong>无限</strong> 数量的面值为 1，2，6 的硬币，并且&nbsp;<strong>只有</strong> 2 枚硬币面值为 4。</p>

<p>给定一个整数&nbsp;<code>n</code>&nbsp;，返回用你持有的硬币达到总和&nbsp;<code>n</code> 的方法数量。</p>

<p>因为答案可能会很大，将其 <strong>取模&nbsp;</strong><code>10<sup>9</sup>&nbsp;+ 7</code>。</p>

<p><strong>注意</strong>&nbsp;硬币的顺序并不重要，<code>[2, 2, 3]</code> 与&nbsp;<code>[2, 3, 2]</code>&nbsp;相同。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 4</span></p>

<p><span class="example-io"><b>输出：</b>4</span></p>

<p><strong>解释：</strong></p>

<p>有四种组合：<code>[1, 1, 1, 1]</code>，<code>[1, 1, 2]</code>，<code>[2, 2]</code>，<code>[4]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 12</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">22</span></p>

<p><strong>解释：</strong></p>

<p>注意&nbsp;<code>[4, 4, 4]</code>&nbsp;<strong>不是</strong> 一个有效的组合，因为我们无法使用 4 三次。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 5</span></p>

<p><span class="example-io"><b>输出：</b></span><span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>有四种组合：<code>[1, 1, 1, 1, 1]</code>，<code>[1, 1, 1, 2]</code>，<code>[1, 2, 2]</code>，<code>[1, 4]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划（完全背包）

我们可以先忽略硬币 $4$，定义硬币数组 `coins = [1, 2, 6]`，然后使用完全背包的思想，定义 $f[j]$ 表示使用前 $i$ 种硬币凑成金额 $j$ 的方案数，初始时 $f[0] = 1$，然后我们遍历硬币数组 `coins`，对于每一种硬币 $x$，我们遍历 $x$ 到 $n$ 的金额，更新 $f[j] = f[j] + f[j - x]$。

最后 $f[n]$ 就是使用硬币 $1, 2, 6$ 凑成金额 $n$ 的方案数，然后如果 $n \geq 4$，我们考虑选择一个硬币 $4$，那么方案数就是 $f[n] + f[n - 4]$，如果 $n \geq 8$，我们再考虑选择两个硬币 $4$，那么方案数就是 $f[n] + f[n - 4] + f[n - 8]$。

注意答案的取模操作。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是金额。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfWays(self, n: int) -> int:
        mod = 10**9 + 7
        coins = [1, 2, 6]
        f = [0] * (n + 1)
        f[0] = 1
        for x in coins:
            for j in range(x, n + 1):
                f[j] = (f[j] + f[j - x]) % mod
        ans = f[n]
        if n >= 4:
            ans = (ans + f[n - 4]) % mod
        if n >= 8:
            ans = (ans + f[n - 8]) % mod
        return ans
```

#### Java

```java
class Solution {
    public int numberOfWays(int n) {
        final int mod = (int) 1e9 + 7;
        int[] coins = {1, 2, 6};
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int x : coins) {
            for (int j = x; j <= n; ++j) {
                f[j] = (f[j] + f[j - x]) % mod;
            }
        }
        int ans = f[n];
        if (n >= 4) {
            ans = (ans + f[n - 4]) % mod;
        }
        if (n >= 8) {
            ans = (ans + f[n - 8]) % mod;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfWays(int n) {
        const int mod = 1e9 + 7;
        int coins[3] = {1, 2, 6};
        int f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int x : coins) {
            for (int j = x; j <= n; ++j) {
                f[j] = (f[j] + f[j - x]) % mod;
            }
        }
        int ans = f[n];
        if (n >= 4) {
            ans = (ans + f[n - 4]) % mod;
        }
        if (n >= 8) {
            ans = (ans + f[n - 8]) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func numberOfWays(n int) int {
	const mod int = 1e9 + 7
	coins := []int{1, 2, 6}
	f := make([]int, n+1)
	f[0] = 1
	for _, x := range coins {
		for j := x; j <= n; j++ {
			f[j] = (f[j] + f[j-x]) % mod
		}
	}
	ans := f[n]
	if n >= 4 {
		ans = (ans + f[n-4]) % mod
	}
	if n >= 8 {
		ans = (ans + f[n-8]) % mod
	}
	return ans
}
```

#### TypeScript

```ts
function numberOfWays(n: number): number {
    const mod = 10 ** 9 + 7;
    const f: number[] = Array(n + 1).fill(0);
    f[0] = 1;
    for (const x of [1, 2, 6]) {
        for (let j = x; j <= n; ++j) {
            f[j] = (f[j] + f[j - x]) % mod;
        }
    }
    let ans = f[n];
    if (n >= 4) {
        ans = (ans + f[n - 4]) % mod;
    }
    if (n >= 8) {
        ans = (ans + f[n - 8]) % mod;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：预处理 + 动态规划（完全背包）

我们可以先预处理出 $1$ 到 $10^5$ 的所有金额的方案数，然后根据 $n$ 的大小直接返回对应的方案数：

-   如果 $n < 4$，直接返回 $f[n]$；
-   如果 $4 \leq n < 8$，返回 $f[n] + f[n - 4]$；
-   如果 $n \geq 8$，返回 $f[n] + f[n - 4] + f[n - 8]$。

注意答案的取模操作。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是金额。

<!-- tabs:start -->

#### Python3

```python
m = 10**5 + 1
mod = 10**9 + 7
coins = [1, 2, 6]
f = [0] * (m)
f[0] = 1
for x in coins:
    for j in range(x, m):
        f[j] = (f[j] + f[j - x]) % mod


class Solution:
    def numberOfWays(self, n: int) -> int:
        ans = f[n]
        if n >= 4:
            ans = (ans + f[n - 4]) % mod
        if n >= 8:
            ans = (ans + f[n - 8]) % mod
        return ans
```

#### Java

```java
class Solution {
    private static final int MOD = 1000000007;
    private static final int M = 100001;
    private static final int[] COINS = {1, 2, 6};
    private static final int[] f = new int[M];

    static {
        f[0] = 1;
        for (int x : COINS) {
            for (int j = x; j < M; ++j) {
                f[j] = (f[j] + f[j - x]) % MOD;
            }
        }
    }

    public int numberOfWays(int n) {
        int ans = f[n];
        if (n >= 4) {
            ans = (ans + f[n - 4]) % MOD;
        }
        if (n >= 8) {
            ans = (ans + f[n - 8]) % MOD;
        }
        return ans;
    }
}
```

#### C++

```cpp
const int m = 1e5 + 1;
const int mod = 1e9 + 7;
int f[m + 1];

auto init = [] {
    f[0] = 1;
    int coins[3] = {1, 2, 6};
    for (int x : coins) {
        for (int j = x; j < m; ++j) {
            f[j] = (f[j] + f[j - x]) % mod;
        }
    }
    return 0;
}();


class Solution {
public:
    int numberOfWays(int n) {
        int ans = f[n];
        if (n >= 4) {
            ans = (ans + f[n - 4]) % mod;
        }
        if (n >= 8) {
            ans = (ans + f[n - 8]) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
const (
    m   = 100001
    mod = 1000000007
)

var f [m]int

func init() {
    f[0] = 1
    coins := []int{1, 2, 6}
    for _, x := range coins {
        for j := x; j < m; j++ {
            f[j] = (f[j] + f[j-x]) % mod
        }
    }
}

func numberOfWays(n int) int {
    ans := f[n]
    if n >= 4 {
        ans = (ans + f[n-4]) % mod
    }
    if n >= 8 {
        ans = (ans + f[n-8]) % mod
    }
    return ans
}
```

#### TypeScript

```ts
const m: number = 10 ** 5 + 1;
const mod: number = 10 ** 9 + 7;
const f: number[] = Array(m).fill(0);

(() => {
    f[0] = 1;
    const coins: number[] = [1, 2, 6];
    for (const x of coins) {
        for (let j = x; j < m; ++j) {
            f[j] = (f[j] + f[j - x]) % mod;
        }
    }
})();

function numberOfWays(n: number): number {
    let ans = f[n];
    if (n >= 4) {
        ans = (ans + f[n - 4]) % mod;
    }
    if (n >= 8) {
        ans = (ans + f[n - 8]) % mod;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
