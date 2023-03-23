# [1866. 恰有 K 根木棍可以看到的排列数目](https://leetcode.cn/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible)

[English Version](/solution/1800-1899/1866.Number%20of%20Ways%20to%20Rearrange%20Sticks%20With%20K%20Sticks%20Visible/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>n</code> 根长度互不相同的木棍，长度为从 <code>1</code> 到 <code>n</code> 的整数。请你将这些木棍排成一排，并满足从左侧 <strong>可以看到</strong> <strong>恰好</strong> <code>k</code> 根木棍。从左侧 <strong>可以看到</strong> 木棍的前提是这个木棍的 <strong>左侧</strong> 不存在比它 <strong>更长的</strong> 木棍。</p>

<ul>
	<li>例如，如果木棍排列为 <code>[<em><strong>1</strong></em>,<em><strong>3</strong></em>,2,<em><strong>5</strong></em>,4]</code> ，那么从左侧可以看到的就是长度分别为 <code>1</code>、<code>3</code> 、<code>5</code> 的木棍。</li>
</ul>

<p>给你 <code>n</code> 和 <code>k</code> ，返回符合题目要求的排列 <strong>数目</strong> 。由于答案可能很大，请返回对 <code>10<sup>9</sup> + 7</code> <strong>取余 </strong>的结果。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 3, k = 2
<strong>输出：</strong>3
<strong>解释：</strong>[<strong><em>1</em></strong>,<strong><em>3</em></strong>,2], [<em><strong>2</strong></em>,<em><strong>3</strong></em>,1] 和 [<em><strong>2</strong></em>,1,<em><strong>3</strong></em>] 是仅有的能满足恰好 2 根木棍可以看到的排列。
可以看到的木棍已经用粗体+斜体标识。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 5, k = 5
<strong>输出：</strong>1
<strong>解释：</strong>[<em><strong>1</strong></em>,<em><strong>2</strong></em>,<em><strong>3</strong></em>,<em><strong>4</strong></em>,<em><strong>5</strong></em>] 是唯一一种能满足全部 5 根木棍可以看到的排列。
可以看到的木棍已经用粗体+斜体标识。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>n = 20, k = 11
<strong>输出：</strong>647427950
<strong>解释：</strong>总共有 647427950 (mod 10<sup>9 </sup>+ 7) 种能满足恰好有 11 根木棍可以看到的排列。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i][j]$ 表示长度为 $i$ 的排列中，恰有 $j$ 根木棍可以看到的排列数目。初始时 $f[0][0]=1$，其余 $f[i][j]=0$。答案为 $f[n][k]$。

考虑最后一根木棍是否可以看到，如果可以看到，那么它一定是最长的，那么它的前面有 $i - 1$ 根木棍，恰有 $j - 1$ 根木棍可以看到，即 $f[i - 1][j - 1]$；如果最后一根木棍不可以看到，那么它可以是除了最长的木棍之外的任意一根，那么它的前面有 $i - 1$ 根木棍，恰有 $j$ 根木棍可以看到，即 $f[i - 1][j] \times (i - 1)$。

因此，状态转移方程为：

$$
f[i][j] = f[i - 1][j - 1] + f[i - 1][j] \times (i - 1)
$$

最终答案为 $f[n][k]$。

我们注意到 $f[i][j]$ 只跟 $f[i - 1][j - 1]$ 和 $f[i - 1][j]$ 有关，因此可以使用一维数组优化空间复杂度。

时间复杂度 $O(n \times k)$，空间复杂度 $O(k)$。其中 $n$ 和 $k$ 分别是题目中给定的两个整数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rearrangeSticks(self, n: int, k: int) -> int:
        mod = 10**9 + 7
        f = [[0] * (k + 1) for _ in range(n + 1)]
        f[0][0] = 1
        for i in range(1, n + 1):
            for j in range(1, k + 1):
                f[i][j] = (f[i - 1][j - 1] + f[i - 1][j] * (i - 1)) % mod
        return f[n][k]
```

```python
class Solution:
    def rearrangeSticks(self, n: int, k: int) -> int:
        mod = 10**9 + 7
        f = [1] + [0] * k
        for i in range(1, n + 1):
            for j in range(k, 0, -1):
                f[j] = (f[j] * (i - 1) + f[j - 1]) % mod
            f[0] = 0
        return f[k]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int rearrangeSticks(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[][] f = new int[n + 1][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j] = (int) ((f[i - 1][j - 1] + f[i - 1][j] * (long) (i - 1)) % mod);
            }
        }
        return f[n][k];
    }
}
```

```java
class Solution {
    public int rearrangeSticks(int n, int k) {
        final int mod = (int) 1e9 + 7;
        int[] f = new int[k + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = k; j > 0; --j) {
                f[j] = (int) ((f[j] * (i - 1L) + f[j - 1]) % mod);
            }
            f[0] = 0;
        }
        return f[k];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int rearrangeSticks(int n, int k) {
        const int mod = 1e9 + 7;
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        f[0][0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= k; ++j) {
                f[i][j] = (f[i - 1][j - 1] + (i - 1LL) * f[i - 1][j]) % mod;
            }
        }
        return f[n][k];
    }
};
```

```cpp
class Solution {
public:
    int rearrangeSticks(int n, int k) {
        const int mod = 1e9 + 7;
        int f[k + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int j = k; j; --j) {
                f[j] = (f[j - 1] + f[j] * (i - 1LL)) % mod;
            }
            f[0] = 0;
        }
        return f[k];
    }
};
```

### **Go**

```go
func rearrangeSticks(n int, k int) int {
	const mod = 1e9 + 7
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		for j := 1; j <= k; j++ {
			f[i][j] = (f[i-1][j-1] + (i-1)*f[i-1][j]) % mod
		}
	}
	return f[n][k]
}
```

```go
func rearrangeSticks(n int, k int) int {
	const mod = 1e9 + 7
	f := make([]int, k+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		for j := k; j > 0; j-- {
			f[j] = (f[j-1] + f[j]*(i-1)) % mod
		}
		f[0] = 0
	}
	return f[k]
}
```

### **...**

```

```

<!-- tabs:end -->
