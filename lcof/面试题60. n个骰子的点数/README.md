# [面试题 60. n 个骰子的点数](https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。</p>

<p>&nbsp;</p>

<p>你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 1
<strong>输出:</strong> [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> 2
<strong>输出:</strong> [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>1 &lt;= n &lt;= 11</code></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i][j]$ 表示投掷 $i$ 个骰子，点数和为 $j$ 的方案数。那么我们可以写出状态转移方程：

$$
f[i][j] = \sum_{k=1}^6 f[i-1][j-k]
$$

其中 $k$ 表示当前骰子的点数，$f[i-1][j-k]$ 表示投掷 $i-1$ 个骰子，点数和为 $j-k$ 的方案数。

最终我们需要求的是 $f[n][n \sim 6n]$ 的和，即投掷 $n$ 个骰子，点数和为 $n \sim 6n$ 的方案数之和。

注意到 $f[i][j]$ 的值只与 $f[i-1][j-k]$ 有关，因此我们可以使用滚动数组的方法将空间复杂度降低到 $O(6n)$。

时间复杂度 $O(n^2)$，空间复杂度 $O(6n)$。其中 $n$ 为骰子个数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def dicesProbability(self, n: int) -> List[float]:
        f = [[0] * (6 * n + 1) for _ in range(n + 1)]
        for j in range(1, 7):
            f[1][j] = 1
        for i in range(2, n + 1):
            for j in range(i, 6 * i + 1):
                for k in range(1, 7):
                    if j - k >= 0:
                        f[i][j] += f[i - 1][j - k]
        m = pow(6, n)
        return [f[n][i] / m for i in range(n, 6 * n + 1)]
```

```python
class Solution:
    def dicesProbability(self, n: int) -> List[float]:
        f = [0] + [1] * 6
        for i in range(2, n + 1):
            g = [0] * (6 * i + 1)
            for j in range(i, 6 * i + 1):
                for k in range(1, 7):
                    if 0 <= j - k < len(f):
                        g[j] += f[j - k]
            f = g
        m = pow(6, n)
        return [f[j] / m for j in range(n, 6 * n + 1)]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double[] dicesProbability(int n) {
        int[][] f = new int[n + 1][6 * n + 1];
        for (int j = 1; j <= 6; ++j) {
            f[1][j] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j >= k) {
                        f[i][j] += f[i - 1][j - k];
                    }
                }
            }
        }
        double m = Math.pow(6, n);
        double[] ans = new double[5 * n + 1];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = f[n][n + i] / m;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<double> dicesProbability(int n) {
        int f[n + 1][6 * n + 1];
        memset(f, 0, sizeof f);
        for (int j = 1; j <= 6; ++j) {
            f[1][j] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j >= k) {
                        f[i][j] += f[i - 1][j - k];
                    }
                }
            }
        }
        vector<double> ans(5 * n + 1);
        double m = pow(6, n);
        for (int i = 0; i < ans.size(); ++i) {
            ans[i] = f[n][n + i] / m;
        }
        return ans;
    }
};
```

### **Go**

```go
func dicesProbability(n int) (ans []float64) {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, 6*n+1)
	}
	for j := 1; j <= 6; j++ {
		f[1][j] = 1
	}
	for i := 2; i <= n; i++ {
		for j := i; j <= 6*i; j++ {
			for k := 1; k <= 6; k++ {
				if j >= k {
					f[i][j] += f[i-1][j-k]
				}
			}
		}
	}
	m := math.Pow(6, float64(n))
	for j := n; j <= 6*n; j++ {
		ans = append(ans, float64(f[n][j])/m)
	}
	return
}
```

```go
func dicesProbability(n int) []float64 {
	dp := make([]float64, 7)
	for i := 1; i <= 6; i++ {
		dp[i] = 1.0 / 6.0
	}
	for i := 2; i <= n; i++ {
		n := len(dp)
		tmp := make([]float64, 6*i+1)
		for j := 0; j < n; j++ {
			for k := 1; k <= 6; k++ {
				tmp[j+k] += dp[j] / 6.0
			}
		}
		dp = tmp
	}
	return dp[n:]
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number[]}
 */
var dicesProbability = function (n) {
    const f = Array.from({ length: n + 1 }, () => Array(6 * n + 1).fill(0));
    for (let j = 1; j <= 6; ++j) {
        f[1][j] = 1;
    }
    for (let i = 2; i <= n; ++i) {
        for (let j = i; j <= 6 * i; ++j) {
            for (let k = 1; k <= 6; ++k) {
                if (j >= k) {
                    f[i][j] += f[i - 1][j - k];
                }
            }
        }
    }
    const ans = [];
    const m = Math.pow(6, n);
    for (let j = n; j <= 6 * n; ++j) {
        ans.push(f[n][j] / m);
    }
    return ans;
};
```

### **C#**

```cs
public class Solution {
    public double[] DicesProbability(int n) {
        var bp = new double[6];
        for (int i = 0; i < 6; i++) {
            bp[i] = 1 / 6.0;
        }
        double[] ans = new double[]{1};
        for (int i = 1; i <= n; i++) {
            var tmp = ans;
            ans = new double[tmp.Length + 5];
            for (int i1 = 0; i1 < tmp.Length; i1++) {
                for (int i2 = 0; i2 < bp.Length; i2++) {
                    ans[i1+i2] += tmp[i1] * bp[i2];
                }
            }
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
