---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0808.Soup%20Servings/README.md
tags:
    - 数学
    - 动态规划
    - 概率与统计
---

<!-- problem:start -->

# [808. 分汤](https://leetcode.cn/problems/soup-servings)

[English Version](/solution/0800-0899/0808.Soup%20Servings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你有两种汤，<strong>A</strong> 和 <strong>B</strong>，每种初始为 <code>n</code>&nbsp;毫升。在每一轮中，会随机选择以下四种操作中的一种，每种操作的概率为 <code>0.25</code>，且与之前的所有轮次 <strong>无关</strong>：</p>

<ol>
	<li>从汤 A 取 100 毫升，从汤 B 取 0 毫升</li>
	<li>从汤 A 取 75 毫升，从汤 B 取 25 毫升</li>
	<li>从汤 A 取 50 毫升，从汤 B 取 50 毫升</li>
	<li>从汤 A 取 25 毫升，从汤 B 取 75 毫升</li>
</ol>

<p><strong>注意：</strong></p>

<ul>
	<li>不存在从汤 A 取&nbsp;<code>0</code>&nbsp;ml 和从汤 B 取&nbsp;<code>100</code> ml 的操作。</li>
	<li>汤 A 和 B 在每次操作中同时被取出。</li>
	<li>如果一次操作要求你取出比剩余的汤更多的量，请取出该汤剩余的所有部分。</li>
</ul>

<p>操作过程在任何回合中任一汤被取完后立即停止。</p>

<p>返回汤 A 在 B 前取完的概率，加上两种汤在 <strong>同一回合&nbsp;</strong>取完概率的一半。返回值在正确答案&nbsp;<code>10<sup>-5</sup></code>&nbsp;的范围内将被认为是正确的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>n = 50
<strong>输出：</strong>0.62500
<strong>解释：
</strong>如果我们选择前两个操作<strong>，</strong>A 首先将变为空。
对于第三个操作，A 和 B 会同时变为空。
对于第四个操作，B 首先将变为空。<strong>
</strong>所以 A 变为空的总概率加上 A 和 B 同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>n = 100
<strong>输出：</strong>0.71875
<strong>解释：</strong>
如果我们选择第一个操作，A 首先将变为空。
如果我们选择第二个操作，A 将在执行操作 [1, 2, 3] 时变为空，然后 A 和 B 在执行操作 4 时同时变空。
如果我们选择第三个操作，A 将在执行操作 [1, 2] 时变为空，然后 A 和 B 在执行操作 3 时同时变空。
如果我们选择第四个操作，A 将在执行操作 1 时变为空，然后 A 和 B 在执行操作 2 时同时变空。
所以 A 变为空的总概率加上 A 和 B 同时变为空的概率的一半是 0.71875。
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

在这道题中，由于每次操作都是 $25$ 的倍数，因此，我们可以将每 $25ml$ 的汤视为一份。这样就能将数据规模缩小到 $\left \lceil \frac{n}{25} \right \rceil$。

我们设计一个函数 $dfs(i, j)$，表示当前剩余 $i$ 份汤 $A$ 和 $j$ 份汤 $B$ 的结果概率。

当 $i \leq 0$ 并且 $j \leq 0$ 时，表示两种汤都分配完了，此时应该返回 $0.5$；当 $i \leq 0$ 时，表示汤 $A$ 先分配完了，此时应该返回 $1$；当 $j \leq 0$ 时，表示汤 $B$ 先分配完了，此时应该返回 $0$。

接下来，对于每一次操作，我们都有四种选择，即：

-   从 $i$ 份汤 $A$ 中取出 $4$ 份，从 $j$ 份汤 $B$ 中取出 $0$ 份；
-   从 $i$ 份汤 $A$ 中取出 $3$ 份，从 $j$ 份汤 $B$ 中取出 $1$ 份；
-   从 $i$ 份汤 $A$ 中取出 $2$ 份，从 $j$ 份汤 $B$ 中取出 $2$ 份；
-   从 $i$ 份汤 $A$ 中取出 $1$ 份，从 $j$ 份汤 $B$ 中取出 $3$ 份；

每一种选择的概率都是 $0.25$，因此，我们可以得到：

$$
dfs(i, j) = 0.25 \times (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3))
$$

记忆化搜索即可。

另外，我们发现在 $n=4800$ 时，结果为 $0.999994994426$，而题目要求的精度为 $10^{-5}$，并且随着 $n$ 的增大，结果越来越接近 $1$，因此，当 $n \gt 4800$ 时，直接返回 $1$ 即可。

时间复杂度 $O(C^2)$，空间复杂度 $O(C^2)$。本题中 $C=200$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def soupServings(self, n: int) -> float:
        @cache
        def dfs(i: int, j: int) -> float:
            if i <= 0 and j <= 0:
                return 0.5
            if i <= 0:
                return 1
            if j <= 0:
                return 0
            return 0.25 * (
                dfs(i - 4, j)
                + dfs(i - 3, j - 1)
                + dfs(i - 2, j - 2)
                + dfs(i - 1, j - 3)
            )

        return 1 if n > 4800 else dfs((n + 24) // 25, (n + 24) // 25)
```

#### Java

```java
class Solution {
    private double[][] f = new double[200][200];

    public double soupServings(int n) {
        return n > 4800 ? 1 : dfs((n + 24) / 25, (n + 24) / 25);
    }

    private double dfs(int i, int j) {
        if (i <= 0 && j <= 0) {
            return 0.5;
        }
        if (i <= 0) {
            return 1.0;
        }
        if (j <= 0) {
            return 0;
        }
        if (f[i][j] > 0) {
            return f[i][j];
        }
        double ans
            = 0.25 * (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3));
        f[i][j] = ans;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double soupServings(int n) {
        double f[200][200] = {0.0};
        auto dfs = [&](this auto&& dfs, int i, int j) -> double {
            if (i <= 0 && j <= 0) return 0.5;
            if (i <= 0) return 1;
            if (j <= 0) return 0;
            if (f[i][j] > 0) return f[i][j];
            double ans = 0.25 * (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3));
            f[i][j] = ans;
            return ans;
        };
        return n > 4800 ? 1 : dfs((n + 24) / 25, (n + 24) / 25);
    }
};
```

#### Go

```go
func soupServings(n int) float64 {
	if n > 4800 {
		return 1
	}
	f := [200][200]float64{}
	var dfs func(i, j int) float64
	dfs = func(i, j int) float64 {
		if i <= 0 && j <= 0 {
			return 0.5
		}
		if i <= 0 {
			return 1.0
		}
		if j <= 0 {
			return 0
		}
		if f[i][j] > 0 {
			return f[i][j]
		}
		ans := 0.25 * (dfs(i-4, j) + dfs(i-3, j-1) + dfs(i-2, j-2) + dfs(i-1, j-3))
		f[i][j] = ans
		return ans
	}
	return dfs((n+24)/25, (n+24)/25)
}
```

#### TypeScript

```ts
function soupServings(n: number): number {
    const f = Array.from({ length: 200 }, () => Array(200).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (i <= 0 && j <= 0) {
            return 0.5;
        }
        if (i <= 0) {
            return 1;
        }
        if (j <= 0) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        f[i][j] =
            0.25 * (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3));
        return f[i][j];
    };
    return n >= 4800 ? 1 : dfs(Math.ceil(n / 25), Math.ceil(n / 25));
}
```

#### Rust

```rust
impl Solution {
    pub fn soup_servings(n: i32) -> f64 {
        if n > 4800 {
            return 1.0;
        }
        Self::dfs((n + 24) / 25, (n + 24) / 25)
    }

    fn dfs(i: i32, j: i32) -> f64 {
        static mut F: [[f64; 200]; 200] = [[0.0; 200]; 200];

        unsafe {
            if i <= 0 && j <= 0 {
                return 0.5;
            }
            if i <= 0 {
                return 1.0;
            }
            if j <= 0 {
                return 0.0;
            }
            if F[i as usize][j as usize] > 0.0 {
                return F[i as usize][j as usize];
            }

            let ans = 0.25 * (Self::dfs(i - 4, j) + Self::dfs(i - 3, j - 1) + Self::dfs(i - 2, j - 2) + Self::dfs(i - 1, j - 3));
            F[i as usize][j as usize] = ans;
            ans
        }
    }
}
```

#### C#

```cs
public class Solution {
    private double[,] f = new double[200, 200];

    public double SoupServings(int n) {
        if (n > 4800) {
            return 1.0;
        }

        return Dfs((n + 24) / 25, (n + 24) / 25);
    }

    private double Dfs(int i, int j) {
        if (i <= 0 && j <= 0) {
            return 0.5;
        }
        if (i <= 0) {
            return 1.0;
        }
        if (j <= 0) {
            return 0.0;
        }
        if (f[i, j] > 0) {
            return f[i, j];
        }

        double ans = 0.25 * (Dfs(i - 4, j) + Dfs(i - 3, j - 1) + Dfs(i - 2, j - 2) + Dfs(i - 1, j - 3));
        f[i, j] = ans;
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
