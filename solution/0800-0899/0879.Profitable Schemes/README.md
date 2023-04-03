# [879. 盈利计划](https://leetcode.cn/problems/profitable-schemes)

[English Version](/solution/0800-0899/0879.Profitable%20Schemes/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>集团里有 <code>n</code> 名员工，他们可以完成各种各样的工作创造利润。</p>

<p>第 <code>i</code> 种工作会产生 <code>profit[i]</code> 的利润，它要求 <code>group[i]</code> 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。</p>

<p>工作的任何至少产生 <code>minProfit</code> 利润的子集称为 <strong>盈利计划</strong> 。并且工作的成员总数最多为 <code>n</code> 。</p>

<p>有多少种计划可以选择？因为答案很大，所以<strong> 返回结果模 </strong><code>10^9 + 7</code><strong> 的值</strong>。</p>

<div class="original__bRMd">
<div>
<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 5, minProfit = 3, group = [2,2], profit = [2,3]
<strong>输出：</strong>2
<strong>解释：</strong>至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
总的来说，有两种计划。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
<strong>输出：</strong>7
<strong>解释：</strong>至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。</pre>
</div>
</div>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 100</code></li>
	<li><code>0 <= minProfit <= 100</code></li>
	<li><code>1 <= group.length <= 100</code></li>
	<li><code>1 <= group[i] <= 100</code></li>
	<li><code>profit.length == group.length</code></li>
	<li><code>0 <= profit[i] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i][j][k]$ 表示前 $i$ 个工作中，选择了不超过 $j$ 个员工，且至少产生 $k$ 的利润的方案数。初始时 $f[0][j][0] = 1$，表示不选择任何工作，且至少产生 $0$ 的利润的方案数为 $1$。答案即为 $f[m][n][minProfit]$。

对于第 $i$ 个工作，我们可以选择参与或不参与。如果不参与，则 $f[i][j][k] = f[i - 1][j][k]$；如果参与，则 $f[i][j][k] = f[i - 1][j - group[i - 1]][max(0, k - profit[i - 1])]$。我们需要枚举 $j$ 和 $k$，并将所有的方案数相加。

最终的答案即为 $f[m][n][minProfit]$。

时间复杂度 $O(m \times n \times minProfit)$，空间复杂度 $O(m \times n \times minProfit)$。其中 $m$ 和 $n$ 分别为工作的数量和员工的数量，而 $minProfit$ 为至少产生的利润。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def profitableSchemes(self, n: int, minProfit: int, group: List[int], profit: List[int]) -> int:
        mod = 10**9 + 7
        m = len(group)
        f = [[[0] * (minProfit + 1) for _ in range(n + 1)]
             for _ in range(m + 1)]
        for j in range(n + 1):
            f[0][j][0] = 1
        for i, (x, p) in enumerate(zip(group, profit), 1):
            for j in range(n + 1):
                for k in range(minProfit + 1):
                    f[i][j][k] = f[i - 1][j][k]
                    if j >= x:
                        f[i][j][k] = (f[i][j][k] + f[i - 1]
                                      [j - x][max(0, k - p)]) % mod
        return f[m][n][minProfit]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        final int mod = (int) 1e9 + 7;
        int m = group.length;
        int[][][] f = new int[m + 1][n + 1][minProfit + 1];
        for (int j = 0; j <= n; ++j) {
            f[0][j][0] = 1;
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                for (int k = 0; k <= minProfit; ++k) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (j >= group[i - 1]) {
                        f[i][j][k]
                            = (f[i][j][k]
                                  + f[i - 1][j - group[i - 1]][Math.max(0, k - profit[i - 1])])
                            % mod;
                    }
                }
            }
        }
        return f[m][n][minProfit];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int profitableSchemes(int n, int minProfit, vector<int>& group, vector<int>& profit) {
        int m = group.size();
        int f[m + 1][n + 1][minProfit + 1];
        memset(f, 0, sizeof(f));
        for (int j = 0; j <= n; ++j) {
            f[0][j][0] = 1;
        }
        const int mod = 1e9 + 7;
        for (int i = 1; i <= m; ++i) {
            for (int j = 0; j <= n; ++j) {
                for (int k = 0; k <= minProfit; ++k) {
                    f[i][j][k] = f[i - 1][j][k];
                    if (j >= group[i - 1]) {
                        f[i][j][k] = (f[i][j][k] + f[i - 1][j - group[i - 1]][max(0, k - profit[i - 1])]) % mod;
                    }
                }
            }
        }
        return f[m][n][minProfit];
    }
};
```

### **Go**

```go
func profitableSchemes(n int, minProfit int, group []int, profit []int) int {
	m := len(group)
	f := make([][][]int, m+1)
	for i := range f {
		f[i] = make([][]int, n+1)
		for j := range f[i] {
			f[i][j] = make([]int, minProfit+1)
		}
	}
	for j := 0; j <= n; j++ {
		f[0][j][0] = 1
	}
	const mod = 1e9 + 7
	for i := 1; i <= m; i++ {
		for j := 0; j <= n; j++ {
			for k := 0; k <= minProfit; k++ {
				f[i][j][k] = f[i-1][j][k]
				if j >= group[i-1] {
					f[i][j][k] += f[i-1][j-group[i-1]][max(0, k-profit[i-1])]
					f[i][j][k] %= mod
				}
			}
		}
	}
	return f[m][n][minProfit]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
