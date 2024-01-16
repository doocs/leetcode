# [2912. 在网格上移动到目的地的方法数](https://leetcode.cn/problems/number-of-ways-to-reach-destination-in-the-grid)

[English Version](/solution/2900-2999/2912.Number%20of%20Ways%20to%20Reach%20Destination%20in%20the%20Grid/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定两个整数 <code>n</code> 和 <code>m</code>，它们表示一个 <strong>下标从 1 开始&nbsp;</strong>的网格的大小。还给定一个整数 <code>k</code>，以及两个 <b>下标从 1 开始</b>&nbsp;的整数数组 <code>source</code> 和 <code>dest</code>。这两个数组 <code>source</code> 和 <code>dest</code> 形如 <code>[x, y]</code>，表示网格上的一个单元格。</p>

<p>你可以按照以下方式在网格上移动：</p>

<ul>
	<li>你可以从单元格 <code>[x1, y1]</code> 移动到 <code>[x2, y2]</code>，只要 <code>x1 == x2</code> 或 <code>y1 == y2</code>。</li>
	<li>注意，你 <strong>不能</strong> 移动到当前所在的单元格，即 <code>x1 == x2</code> 且 <code>y1 == y2</code>。</li>
</ul>

<p>请返回你在网格上从 <code>source</code> 到 <code>dest</code>&nbsp;移动 <code>k</code> 次一共可以有&nbsp;<strong>多少种 </strong>方法。</p>

<p>由于答案可能非常大，因此请对&nbsp;<code>10<sup>9</sup>&nbsp;+ 7</code>&nbsp;<strong>取模</strong> 后返回。</p>

<p>&nbsp;</p>

<p><b>示例 1:</b></p>

<pre>
<b>输入：</b> n = 3, m = 2, k = 2, source = [1,1], dest = [2,2]
<b>输出：</b> 2
<b>解释： </b>有两种可能的方式从 [1,1] 到达 [2,2]：
- [1,1] -&gt; [1,2] -&gt; [2,2]
- [1,1] -&gt; [2,1] -&gt; [2,2]
</pre>

<p><b>示例 2:</b></p>

<pre>
<b>输入：</b> n = 3, m = 4, k = 3, source = [1,2], dest = [2,3]
<b>输出：</b> 9
<b>解释：</b> 有 9 种可能的方式从 [1,2] 到达 [2,3]：:
- [1,2] -&gt; [1,1] -&gt; [1,3] -&gt; [2,3]
- [1,2] -&gt; [1,1] -&gt; [2,1] -&gt; [2,3]
- [1,2] -&gt; [1,3] -&gt; [3,3] -&gt; [2,3]
- [1,2] -&gt; [1,4] -&gt; [1,3] -&gt; [2,3]
- [1,2] -&gt; [1,4] -&gt; [2,4] -&gt; [2,3]
- [1,2] -&gt; [2,2] -&gt; [2,1] -&gt; [2,3]
- [1,2] -&gt; [2,2] -&gt; [2,4] -&gt; [2,3]
- [1,2] -&gt; [3,2] -&gt; [2,2] -&gt; [2,3]
- [1,2] -&gt; [3,2] -&gt; [3,3] -&gt; [2,3]
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>2 &lt;= n, m &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>source.length == dest.length == 2</code></li>
	<li><code>1 &lt;= source[1], dest[1] &lt;= n</code></li>
	<li><code>1 &lt;= source[2], dest[2] &lt;= m</code></li>
</ul>

## 解法

### 方法一：动态规划

我们定义以下几个状态，其中：

-   $f[0]$ 表示从 $source$ 到 $source$ 本身的方法数；
-   $f[1]$ 表示从 $source$ 移动到同一列其它行的方法数；
-   $f[2]$ 表示从 $source$ 移动到同一行其它列的方法数；
-   $f[3]$ 表示从 $source$ 移动到其它行其它列的方法数。

初始时，$f[0] = 1$，其余状态均为 $0$。

对于每个状态，我们可以根据上一次的状态计算出当前的状态，具体如下：

$$
\begin{aligned}
g[0] &= (n - 1) \times f[1] + (m - 1) \times f[2] \\
g[1] &= f[0] + (n - 2) \times f[1] + (m - 1) \times f[3] \\
g[2] &= f[0] + (m - 2) \times f[2] + (n - 1) \times f[3] \\
g[3] &= f[1] + f[2] + (n - 2) \times f[3] + (m - 2) \times f[3]
\end{aligned}
$$

我们循环 $k$ 次，最后判断 $source$ 和 $dest$ 是否在同一行或同一列，返回对应的状态即可。

时间复杂度 $O(k)$，其中 $k$ 为移动次数。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def numberOfWays(
        self, n: int, m: int, k: int, source: List[int], dest: List[int]
    ) -> int:
        mod = 10**9 + 7
        a, b, c, d = 1, 0, 0, 0
        for _ in range(k):
            aa = ((n - 1) * b + (m - 1) * c) % mod
            bb = (a + (n - 2) * b + (m - 1) * d) % mod
            cc = (a + (m - 2) * c + (n - 1) * d) % mod
            dd = (b + c + (n - 2) * d + (m - 2) * d) % mod
            a, b, c, d = aa, bb, cc, dd
        if source[0] == dest[0]:
            return a if source[1] == dest[1] else c
        return b if source[1] == dest[1] else d
```

```java
class Solution {
    public int numberOfWays(int n, int m, int k, int[] source, int[] dest) {
        final int mod = 1000000007;
        long[] f = new long[4];
        f[0] = 1;
        while (k-- > 0) {
            long[] g = new long[4];
            g[0] = ((n - 1) * f[1] + (m - 1) * f[2]) % mod;
            g[1] = (f[0] + (n - 2) * f[1] + (m - 1) * f[3]) % mod;
            g[2] = (f[0] + (m - 2) * f[2] + (n - 1) * f[3]) % mod;
            g[3] = (f[1] + f[2] + (n - 2) * f[3] + (m - 2) * f[3]) % mod;
            f = g;
        }
        if (source[0] == dest[0]) {
            return source[1] == dest[1] ? (int) f[0] : (int) f[2];
        }
        return source[1] == dest[1] ? (int) f[1] : (int) f[3];
    }
}
```

```cpp
class Solution {
public:
    int numberOfWays(int n, int m, int k, vector<int>& source, vector<int>& dest) {
        const int mod = 1e9 + 7;
        vector<long long> f(4);
        f[0] = 1;
        while (k--) {
            vector<long long> g(4);
            g[0] = ((n - 1) * f[1] + (m - 1) * f[2]) % mod;
            g[1] = (f[0] + (n - 2) * f[1] + (m - 1) * f[3]) % mod;
            g[2] = (f[0] + (m - 2) * f[2] + (n - 1) * f[3]) % mod;
            g[3] = (f[1] + f[2] + (n - 2) * f[3] + (m - 2) * f[3]) % mod;
            f = move(g);
        }
        if (source[0] == dest[0]) {
            return source[1] == dest[1] ? f[0] : f[2];
        }
        return source[1] == dest[1] ? f[1] : f[3];
    }
};
```

```go
func numberOfWays(n int, m int, k int, source []int, dest []int) int {
	const mod int = 1e9 + 7
	f := []int{1, 0, 0, 0}
	for i := 0; i < k; i++ {
		g := make([]int, 4)
		g[0] = ((n-1)*f[1] + (m-1)*f[2]) % mod
		g[1] = (f[0] + (n-2)*f[1] + (m-1)*f[3]) % mod
		g[2] = (f[0] + (m-2)*f[2] + (n-1)*f[3]) % mod
		g[3] = (f[1] + f[2] + (n-2)*f[3] + (m-2)*f[3]) % mod
		f = g
	}

	if source[0] == dest[0] {
		if source[1] == dest[1] {
			return f[0]
		}
		return f[2]
	}

	if source[1] == dest[1] {
		return f[1]
	}
	return f[3]
}
```

<!-- tabs:end -->

### 方法二

<!-- tabs:start -->

```python
class Solution:
    def numberOfWays(
        self, n: int, m: int, k: int, source: List[int], dest: List[int]
    ) -> int:
        mod = 10**9 + 7
        f = [1, 0, 0, 0]
        for _ in range(k):
            g = [0] * 4
            g[0] = ((n - 1) * f[1] + (m - 1) * f[2]) % mod
            g[1] = (f[0] + (n - 2) * f[1] + (m - 1) * f[3]) % mod
            g[2] = (f[0] + (m - 2) * f[2] + (n - 1) * f[3]) % mod
            g[3] = (f[1] + f[2] + (n - 2) * f[3] + (m - 2) * f[3]) % mod
            f = g
        if source[0] == dest[0]:
            return f[0] if source[1] == dest[1] else f[2]
        return f[1] if source[1] == dest[1] else f[3]
```

<!-- tabs:end -->

<!-- end -->
