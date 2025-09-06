---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0837.New%2021%20Game/README.md
tags:
    - 数学
    - 动态规划
    - 滑动窗口
    - 概率与统计
---

<!-- problem:start -->

# [837. 新 21 点](https://leetcode.cn/problems/new-21-game)

[English Version](/solution/0800-0899/0837.New%2021%20Game/README_EN.md)

## 题目描述

<!-- description:start -->

<p>爱丽丝参与一个大致基于纸牌游戏 <strong>“21点”</strong> 规则的游戏，描述如下：</p>

<p>爱丽丝以 <code>0</code> 分开始，并在她的得分少于 <code>k</code> 分时抽取数字。 抽取时，她从 <code>[1, maxPts]</code> 的范围中随机获得一个整数作为分数进行累计，其中 <code>maxPts</code> 是一个整数。 每次抽取都是独立的，其结果具有相同的概率。</p>

<p>当爱丽丝获得 <code>k</code> 分 <strong>或更多分</strong> 时，她就停止抽取数字。</p>

<p>爱丽丝的分数不超过 <code>n</code> 的概率是多少？</p>

<p>与实际答案误差不超过&nbsp;<code>10<sup>-5</sup></code> 的答案将被视为正确答案。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 10, k = 1, maxPts = 10
<strong>输出：</strong>1.00000
<strong>解释：</strong>爱丽丝得到一张牌，然后停止。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 6, k = 1, maxPts = 10
<strong>输出：</strong>0.60000
<strong>解释：</strong>爱丽丝得到一张牌，然后停止。 在 10 种可能性中的 6 种情况下，她的得分不超过 6 分。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 21, k = 17, maxPts = 10
<strong>输出：</strong>0.73278
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= k &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= maxPts &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们设计一个函数 $dfs(i)$，表示当前分数为 $i$ 时，到最终停止抽取数字时，分数不超过 $n$ 的概率。那么答案就是 $dfs(0)$。

函数 $dfs(i)$ 的计算方法如下：

-   如果 $i \ge k$，那么停止抽取数字，如果 $i \le n$，返回 $1$，否则返回 $0$；
-   否则，可以在 $[1,..\textit{maxPts}]$ 范围内抽取下一个数字 $j$，那么 $dfs(i) = \frac{1}{maxPts} \sum_{j=1}^{maxPts} dfs(i+j)$。

这里我们可以使用记忆化搜索来加速计算。

以上方法的时间复杂度为 $O(k \times \textit{maxPts})$，会超出时间限制，我们需要优化一下。

当 $i \lt k$ 时，以下等式成立：

$$
\begin{aligned}
dfs(i) &= (dfs(i + 1) + dfs(i + 2) + \cdots + dfs(i + \textit{maxPts})) / \ & (1)
\end{aligned}
$$

当 $i \lt k - 1$ 时，以下等式成立：

$$
\begin{aligned}
dfs(i+1) &= (dfs(i + 2) + dfs(i + 3) + \cdots + dfs(i + \textit{maxPts} + 1)) / \textit{maxPts} & (2)
\end{aligned}
$$

因此，当 $i \lt k-1$ 时，我们将等式 $(1)$ 减去等式 $(2)$，得到：

$$
\begin{aligned}
dfs(i) - dfs(i+1) &= (dfs(i + 1) - dfs(i + \textit{maxPts} + 1)) / \textit{maxPts}
\end{aligned}
$$

即：

$$
\begin{aligned}
dfs(i) &= dfs(i + 1) + (dfs(i + 1) - dfs(i + \textit{maxPts} + 1)) / \textit{maxPts}
\end{aligned}
$$

如果 $i=k-1$，有：

$$
\begin{aligned}
dfs(i) &= dfs(k - 1) &= dfs(k) + dfs(k + 1) + \cdots + dfs(k + \textit{maxPts} - 1) / \textit{maxPts} & (3)
\end{aligned}
$$

我们假设有 $i$ 个数不超过 $n$，那么 $k+i-1 \leq n$，又因为 $i\leq \textit{maxPts}$，所以 $i \leq \min(n-k+1, \textit{maxPts})$，因此等式 $(3)$ 可以写成：

$$
\begin{aligned}
dfs(k-1) &= \min(n-k+1, \textit{maxPts}) / \textit{maxPts}
\end{aligned}
$$

综上所述，有以下状态转移方程：

$$
\begin{aligned}
dfs(i) &= \begin{cases}
1, & i \geq k, i \leq n \\
0, & i \geq k, i \gt n \\
\min(n-k+1, \textit{maxPts}) / \textit{maxPts}, & i = k - 1 \\
dfs(i + 1) + (dfs(i + 1) - dfs(i + \textit{maxPts} + 1)) / \textit{maxPts}, & i < k - 1
\end{cases}
\end{aligned}
$$

时间复杂度 $O(k + \textit{maxPts})$，空间复杂度 $O(k + \textit{maxPts})$。其中 $k$ 为最大分数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def new21Game(self, n: int, k: int, maxPts: int) -> float:
        @cache
        def dfs(i: int) -> float:
            if i >= k:
                return int(i <= n)
            if i == k - 1:
                return min(n - k + 1, maxPts) / maxPts
            return dfs(i + 1) + (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts

        return dfs(0)
```

#### Java

```java
class Solution {
    private double[] f;
    private int n, k, maxPts;

    public double new21Game(int n, int k, int maxPts) {
        f = new double[k];
        this.n = n;
        this.k = k;
        this.maxPts = maxPts;
        return dfs(0);
    }

    private double dfs(int i) {
        if (i >= k) {
            return i <= n ? 1 : 0;
        }
        if (i == k - 1) {
            return Math.min(n - k + 1, maxPts) * 1.0 / maxPts;
        }
        if (f[i] != 0) {
            return f[i];
        }
        return f[i] = dfs(i + 1) + (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double new21Game(int n, int k, int maxPts) {
        vector<double> f(k);
        function<double(int)> dfs = [&](int i) -> double {
            if (i >= k) {
                return i <= n ? 1 : 0;
            }
            if (i == k - 1) {
                return min(n - k + 1, maxPts) * 1.0 / maxPts;
            }
            if (f[i]) {
                return f[i];
            }
            return f[i] = dfs(i + 1) + (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func new21Game(n int, k int, maxPts int) float64 {
	f := make([]float64, k)
	var dfs func(int) float64
	dfs = func(i int) float64 {
		if i >= k {
			if i <= n {
				return 1
			}
			return 0
		}
		if i == k-1 {
			return float64(min(n-k+1, maxPts)) / float64(maxPts)
		}
		if f[i] > 0 {
			return f[i]
		}
		f[i] = dfs(i+1) + (dfs(i+1)-dfs(i+maxPts+1))/float64(maxPts)
		return f[i]
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function new21Game(n: number, k: number, maxPts: number): number {
    const f: number[] = Array(k).fill(0);
    const dfs = (i: number): number => {
        if (i >= k) {
            return i <= n ? 1 : 0;
        }
        if (i === k - 1) {
            return Math.min(n - k + 1, maxPts) / maxPts;
        }
        if (f[i] !== 0) {
            return f[i];
        }
        return (f[i] = dfs(i + 1) + (dfs(i + 1) - dfs(i + maxPts + 1)) / maxPts);
    };
    return dfs(0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：动态规划

我们可以将方法一中的记忆化搜索改成动态规划。

定义 $f[i]$ 表示当前分数为 $i$ 时，到最终停止抽取数字时，分数不超过 $n$ 的概率。那么答案就是 $f[0]$。

当 $k \leq i \leq \min(n, k + \textit{maxPts} - 1)$ 时，有 $f[i] = 1$。

当 $i = k - 1$ 时，有 $f[i] = \min(n-k+1, \textit{maxPts}) / \textit{maxPts}$。

当 $i \lt k - 1$ 时，有 $f[i] = f[i + 1] + (f[i + 1] - f[i + \textit{maxPts} + 1]) / \textit{maxPts}$。

时间复杂度 $O(k + \textit{maxPts})$，空间复杂度 $O(k + \textit{maxPts})$。其中 $k$ 为最大分数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def new21Game(self, n: int, k: int, maxPts: int) -> float:
        f = [0] * (k + maxPts)
        for i in range(k, min(n + 1, k + maxPts)):
            f[i] = 1
        f[k - 1] = min(n - k + 1, maxPts) / maxPts
        for i in range(k - 2, -1, -1):
            f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts
        return f[0]
```

#### Java

```java
class Solution {
    public double new21Game(int n, int k, int maxPts) {
        if (k == 0) {
            return 1.0;
        }
        double[] f = new double[k + maxPts];
        for (int i = k; i < Math.min(n + 1, k + maxPts); ++i) {
            f[i] = 1;
        }
        f[k - 1] = Math.min(n - k + 1, maxPts) * 1.0 / maxPts;
        for (int i = k - 2; i >= 0; --i) {
            f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts;
        }
        return f[0];
    }
}
```

#### C++

```cpp
class Solution {
public:
    double new21Game(int n, int k, int maxPts) {
        if (k == 0) {
            return 1.0;
        }
        double f[k + maxPts];
        memset(f, 0, sizeof(f));
        for (int i = k; i < min(n + 1, k + maxPts); ++i) {
            f[i] = 1;
        }
        f[k - 1] = min(n - k + 1, maxPts) * 1.0 / maxPts;
        for (int i = k - 2; i >= 0; --i) {
            f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts;
        }
        return f[0];
    }
};
```

#### Go

```go
func new21Game(n int, k int, maxPts int) float64 {
	if k == 0 {
		return 1
	}
	f := make([]float64, k+maxPts)
	for i := k; i < min(n+1, k+maxPts); i++ {
		f[i] = 1
	}
	f[k-1] = float64(min(n-k+1, maxPts)) / float64(maxPts)
	for i := k - 2; i >= 0; i-- {
		f[i] = f[i+1] + (f[i+1]-f[i+maxPts+1])/float64(maxPts)
	}
	return f[0]
}
```

#### TypeScript

```ts
function new21Game(n: number, k: number, maxPts: number): number {
    if (k === 0) {
        return 1;
    }
    const f: number[] = Array(k + maxPts).fill(0);
    for (let i = k; i < Math.min(n + 1, k + maxPts); ++i) {
        f[i] = 1;
    }
    f[k - 1] = Math.min(n - k + 1, maxPts) / maxPts;
    for (let i = k - 2; i >= 0; --i) {
        f[i] = f[i + 1] + (f[i + 1] - f[i + maxPts + 1]) / maxPts;
    }
    return f[0];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
