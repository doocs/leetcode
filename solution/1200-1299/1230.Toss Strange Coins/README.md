# [1230. 抛掷硬币 🔒](https://leetcode.cn/problems/toss-strange-coins)

[English Version](/solution/1200-1299/1230.Toss%20Strange%20Coins/README_EN.md)

<!-- tags:数组,数学,动态规划,概率与统计 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>有一些不规则的硬币。在这些硬币中，<code>prob[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;枚硬币正面朝上的概率。</p>

<p>请对每一枚硬币抛掷&nbsp;<strong>一次</strong>，然后返回正面朝上的硬币数等于&nbsp;<code>target</code>&nbsp;的概率。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>prob = [0.4], target = 1
<strong>输出：</strong>0.40000
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>prob = [0.5,0.5,0.5,0.5,0.5], target = 0
<strong>输出：</strong>0.03125
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prob.length &lt;= 1000</code></li>
	<li><code>0 &lt;= prob[i] &lt;= 1</code></li>
	<li><code>0 &lt;= target&nbsp;</code><code>&lt;= prob.length</code></li>
	<li>如果答案与标准答案的误差在&nbsp;<code>10^-5</code>&nbsp;内，则被视为正确答案。</li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $f[i][j]$ 表示前 $i$ 枚硬币中有 $j$ 枚正面朝上的概率，初始时 $f[0][0]=1$，答案即为 $f[n][target]$。

考虑 $f[i][j]$，其中 $i \geq 1$，如果当前硬币反面朝上，那么 $f[i][j] = (1 - p) \times f[i - 1][j]$；如果当前硬币正面朝上，并且 $j \gt 0$，那么 $f[i][j] = p \times f[i - 1][j - 1]$。因此状态转移方程为：

$$
f[i][j] = \begin{cases}
(1 - p) \times f[i - 1][j], & j = 0 \\
(1 - p) \times f[i - 1][j] + p \times f[i - 1][j - 1], & j \gt 0
\end{cases}
$$

其中 $p$ 表示第 $i$ 枚硬币正面朝上的概率。

我们注意到，状态 $f[i][j]$ 只与状态 $f[i - 1][j]$ 和 $f[i - 1][j - 1]$ 有关，因此，我们可以将二维空间优化为一维空间。

时间复杂度 $O(n \times target)$，空间复杂度 $O(target)$。其中 $n$ 为硬币的数量。

<!-- tabs:start -->

```python
class Solution:
    def probabilityOfHeads(self, prob: List[float], target: int) -> float:
        n = len(prob)
        f = [[0] * (target + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i, p in enumerate(prob, 1):
            for j in range(min(i, target) + 1):
                f[i][j] = (1 - p) * f[i - 1][j]
                if j:
                    f[i][j] += p * f[i - 1][j - 1]
        return f[n][target]
```

```java
class Solution {
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        double[][] f = new double[n + 1][target + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= Math.min(i, target); ++j) {
                f[i][j] = (1 - prob[i - 1]) * f[i - 1][j];
                if (j > 0) {
                    f[i][j] += prob[i - 1] * f[i - 1][j - 1];
                }
            }
        }
        return f[n][target];
    }
}
```

```cpp
class Solution {
public:
    double probabilityOfHeads(vector<double>& prob, int target) {
        int n = prob.size();
        double f[n + 1][target + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= min(i, target); ++j) {
                f[i][j] = (1 - prob[i - 1]) * f[i - 1][j];
                if (j > 0) {
                    f[i][j] += prob[i - 1] * f[i - 1][j - 1];
                }
            }
        }
        return f[n][target];
    }
};
```

```go
func probabilityOfHeads(prob []float64, target int) float64 {
	n := len(prob)
	f := make([][]float64, n+1)
	for i := range f {
		f[i] = make([]float64, target+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		for j := 0; j <= i && j <= target; j++ {
			f[i][j] = (1 - prob[i-1]) * f[i-1][j]
			if j > 0 {
				f[i][j] += prob[i-1] * f[i-1][j-1]
			}
		}
	}
	return f[n][target]
}
```

```ts
function probabilityOfHeads(prob: number[], target: number): number {
    const n = prob.length;
    const f = new Array(n + 1).fill(0).map(() => new Array(target + 1).fill(0));
    f[0][0] = 1;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j <= target; ++j) {
            f[i][j] = f[i - 1][j] * (1 - prob[i - 1]);
            if (j) {
                f[i][j] += f[i - 1][j - 1] * prob[i - 1];
            }
        }
    }
    return f[n][target];
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def probabilityOfHeads(self, prob: List[float], target: int) -> float:
        f = [0] * (target + 1)
        f[0] = 1
        for p in prob:
            for j in range(target, -1, -1):
                f[j] *= 1 - p
                if j:
                    f[j] += p * f[j - 1]
        return f[target]
```

```java
class Solution {
    public double probabilityOfHeads(double[] prob, int target) {
        double[] f = new double[target + 1];
        f[0] = 1;
        for (double p : prob) {
            for (int j = target; j >= 0; --j) {
                f[j] *= (1 - p);
                if (j > 0) {
                    f[j] += p * f[j - 1];
                }
            }
        }
        return f[target];
    }
}
```

```cpp
class Solution {
public:
    double probabilityOfHeads(vector<double>& prob, int target) {
        double f[target + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (double p : prob) {
            for (int j = target; j >= 0; --j) {
                f[j] *= (1 - p);
                if (j > 0) {
                    f[j] += p * f[j - 1];
                }
            }
        }
        return f[target];
    }
};
```

```go
func probabilityOfHeads(prob []float64, target int) float64 {
	f := make([]float64, target+1)
	f[0] = 1
	for _, p := range prob {
		for j := target; j >= 0; j-- {
			f[j] *= (1 - p)
			if j > 0 {
				f[j] += p * f[j-1]
			}
		}
	}
	return f[target]
}
```

```ts
function probabilityOfHeads(prob: number[], target: number): number {
    const f = new Array(target + 1).fill(0);
    f[0] = 1;
    for (const p of prob) {
        for (let j = target; j >= 0; --j) {
            f[j] *= 1 - p;
            if (j > 0) {
                f[j] += f[j - 1] * p;
            }
        }
    }
    return f[target];
}
```

<!-- tabs:end -->

<!-- end -->
