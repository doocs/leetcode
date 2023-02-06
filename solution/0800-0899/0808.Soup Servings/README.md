# [808. 分汤](https://leetcode.cn/problems/soup-servings)

[English Version](/solution/0800-0899/0808.Soup%20Servings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有&nbsp;<strong>A&nbsp;和&nbsp;B 两种类型&nbsp;</strong>的汤。一开始每种类型的汤有&nbsp;<code>n</code>&nbsp;毫升。有四种分配操作：</p>

<ol>
	<li>提供 <code>100ml</code> 的 <strong>汤A</strong> 和 <code>0ml</code> 的 <strong>汤B</strong> 。</li>
	<li>提供 <code>75ml</code> 的 <strong>汤A</strong> 和 <code>25ml</code> 的 <strong>汤B</strong> 。</li>
	<li>提供 <code>50ml</code> 的 <strong>汤A</strong> 和 <code>50ml</code> 的 <strong>汤B</strong> 。</li>
	<li>提供 <code>25ml</code> 的 <strong>汤A</strong> 和 <code>75ml</code> 的 <strong>汤B</strong> 。</li>
</ol>

<p>当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为 <code>0.25</code> 的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。</p>

<p><strong>注意&nbsp;</strong>不存在先分配 <code>100</code> ml <strong>汤B</strong> 的操作。</p>

<p>需要返回的值：&nbsp;<strong>汤A&nbsp;</strong>先分配完的概率 +&nbsp;&nbsp;<strong>汤A和汤B&nbsp;</strong>同时分配完的概率 / 2。返回值在正确答案&nbsp;<code>10<sup>-5</sup></code>&nbsp;的范围内将被认为是正确的。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = 50
<strong>输出:</strong> 0.62500
<strong>解释:</strong>如果我们选择前两个操作<strong>，</strong>A 首先将变为空。
对于第三个操作，A 和 B 会同时变为空。
对于第四个操作，B 首先将变为空。<strong>
</strong>所以 A 变为空的总概率加上 A 和 B 同时变为空的概率的一半是 0.25 *(1 + 1 + 0.5 + 0)= 0.625。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 100
<strong>输出:</strong> 0.71875
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code>​​​​​​​</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

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

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def soupServings(self, n: int) -> float:
        @cache
        def dfs(i, j):
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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

```cpp
class Solution {
public:
    double soupServings(int n) {
        double f[200][200] = {0.0};
        function<double(int, int)> dfs = [&](int i, int j) -> double {
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

### **Go**

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

### **...**

```

```

<!-- tabs:end -->
