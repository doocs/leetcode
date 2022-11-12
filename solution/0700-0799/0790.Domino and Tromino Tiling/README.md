# [790. 多米诺和托米诺平铺](https://leetcode.cn/problems/domino-and-tromino-tiling)

[English Version](/solution/0700-0799/0790.Domino%20and%20Tromino%20Tiling/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有两种形状的瓷砖：一种是&nbsp;<code>2 x 1</code> 的多米诺形，另一种是形如&nbsp;"L" 的托米诺形。两种形状都可以旋转。</p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0790.Domino%20and%20Tromino%20Tiling/images/lc-domino.jpg" style="height: 195px; width: 362px;" /></p>

<p>给定整数 n ，返回可以平铺&nbsp;<code>2 x n</code> 的面板的方法的数量。<strong>返回对</strong>&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;<strong>取模&nbsp;</strong>的值。</p>

<p>平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0700-0799/0790.Domino%20and%20Tromino%20Tiling/images/lc-domino1.jpg" style="height: 226px; width: 500px;" /></p>

<pre>
<strong>输入:</strong> n = 3
<strong>输出:</strong> 5
<strong>解释:</strong> 五种不同的方法如上所示。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 1
<strong>输出:</strong> 1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们首先要读懂题意，题目实际上是让我们求铺满 $2\times n$ 的面板的方案数，其中面板上的每个正方形只能被一个瓷砖覆盖。

瓷砖的形状有两种，分别是 `2 x 1` 型 和 `L` 型，并且两种瓷砖都可以旋转。我们将旋转后的瓷砖分别记为 `1 x 2` 型 和 `L'` 型。

我们定义 $f[i][j]$ 表示平铺前 $2\times i$ 的面板，其中 $j$ 表示最后一列的状态。最后一列有 $4$ 种状态，分别是：

-   最后一列铺满，记为 $0$
-   最后一列只铺了上方一个瓷砖，记为 $1$
-   最后一列只铺了下方一个瓷砖，记为 $2$
-   最后一列没有铺瓷砖，记为 $3$

那么答案就是 $f[n][0]$。初始时 $f[0][0]=1$，其余 $f[0][j]=0$。

我们考虑铺到第 $i$ 列，来看看状态转移方程：

当 $j=0$ 时，最后一列铺满，可由前一列的 $0,1,2,3$ 四种状态铺上对应的瓷砖转移而来，即 $f[i-1][0]$ 铺上 `1 x 2` 型瓷砖，或者 $f[i-1][1]$ 铺上 `L'` 型瓷砖，或者 $f[i-1][2]$ 铺上 `L'` 型瓷砖，或者 $f[i-1][3]$ 铺上两块 `2 x 1` 型瓷砖。因此 $f[i][0]=\sum_{j=0}^3f[i-1][j]$。

当 $j=1$ 时，最后一列只铺了上方一个瓷砖，可由前一列的 $2,3$ 两种状态转移而来，即 $f[i-1][2]$ 铺上 `2 x 1` 型瓷砖，或者 $f[i-1][3]$ 铺上 `L` 型瓷砖。因此 $f[i][1]=f[i-1][2]+f[i-1][3]$。

当 $j=2$ 时，最后一列只铺了下方一个瓷砖，可由前一列的 $1,3$ 两种状态转移而来，即 $f[i-1][1]$ 铺上 `2 x 1` 型瓷砖，或者 $f[i-1][3]$ 铺上 `L'` 型瓷砖。因此 $f[i][2]=f[i-1][1]+f[i-1][3]$。

当 $j=3$ 时，最后一列没有铺瓷砖，可由前一列的 $0$ 一种状态转移而来。因此 $f[i][3]=f[i-1][0]$。

可以发现，状态转移方程中只涉及到前一列的状态，因此我们可以使用滚动数组优化空间复杂度。

注意，过程中的状态数值可能会很大，因此需要对 $10^9+7$ 取模。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为面板的列数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numTilings(self, n: int) -> int:
        @cache
        def dfs(i, j):
            if i > n or j > n:
                return 0
            if i == n and j == n:
                return 1
            ans = 0
            if i == j:
                ans = dfs(i + 2, j + 2) + dfs(i + 1, j + 1) + dfs(i + 2, j + 1) + dfs(i + 1, j + 2)
            elif i > j:
                ans = dfs(i, j + 2) + dfs(i + 1, j + 2)
            else:
                ans = dfs(i + 2, j) + dfs(i + 2, j + 1)
            return ans % mod

        mod = 10**9 + 7
        return dfs(0, 0)
```

```python
class Solution:
    def numTilings(self, n: int) -> int:
        f = [1, 0, 0, 0]
        mod = 10**9 + 7
        for i in range(1, n + 1):
            g = [0] * 4
            g[0] = (f[0] + f[1] + f[2] + f[3]) % mod
            g[1] = (f[2] + f[3]) % mod
            g[2] = (f[1] + f[3]) % mod
            g[3] = f[0]
            f = g
        return f[0]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numTilings(int n) {
        long[] f = {1, 0, 0, 0};
        int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            long[] g = new long[4];
            g[0] = (f[0] + f[1] + f[2] + f[3]) % mod;
            g[1] = (f[2] + f[3]) % mod;
            g[2] = (f[1] + f[3]) % mod;
            g[3] = f[0];
            f = g;
        }
        return (int) f[0];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int numTilings(int n) {
        long f[4] = {1, 0, 0, 0};
        for (int i = 1; i <= n; ++i) {
            long g[4] = {0, 0, 0, 0};
            g[0] = (f[0] + f[1] + f[2] + f[3]) % mod;
            g[1] = (f[2] + f[3]) % mod;
            g[2] = (f[1] + f[3]) % mod;
            g[3] = f[0];
            memcpy(f, g, sizeof(g));
        }
        return f[0];
    }
};
```

### **Go**

```go
func numTilings(n int) int {
	f := [4]int{}
	f[0] = 1
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		g := [4]int{}
		g[0] = (f[0] + f[1] + f[2] + f[3]) % mod
		g[1] = (f[2] + f[3]) % mod
		g[2] = (f[1] + f[3]) % mod
		g[3] = f[0]
		f = g
	}
	return f[0]
}
```

### **...**

```

```

<!-- tabs:end -->
