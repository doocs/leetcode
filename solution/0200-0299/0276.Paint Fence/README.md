# [276. 栅栏涂色](https://leetcode.cn/problems/paint-fence)

[English Version](/solution/0200-0299/0276.Paint%20Fence/README_EN.md)

<!-- tags:动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>k</code> 种颜色的涂料和一个包含 <code>n</code> 个栅栏柱的栅栏，请你按下述规则为栅栏设计涂色方案：</p>

<ul>
	<li>每个栅栏柱可以用其中 <strong>一种</strong> 颜色进行上色。</li>
	<li>相邻的栅栏柱 <strong>最多连续两个 </strong>颜色相同。</li>
</ul>

<p>给你两个整数 <code>k</code> 和 <code>n</code> ，返回所有有效的涂色 <strong>方案数</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0276.Paint%20Fence/images/paintfenceex1.png" style="width: 507px; height: 313px;" />
<pre>
<strong>输入：</strong>n = 3, k = 2
<strong>输出：</strong>6
<strong>解释：</strong>所有的可能涂色方案如上图所示。注意，全涂红或者全涂绿的方案属于无效方案，因为相邻的栅栏柱 <strong>最多连续两个 </strong>颜色相同。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1, k = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 7, k = 2
<strong>输出：</strong>42
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= n <= 50</code></li>
	<li><code>1 <= k <= 10<sup>5</sup></code></li>
	<li>题目数据保证：对于输入的 <code>n</code> 和 <code>k</code> ，其答案在范围 <code>[0, 2<sup>31</sup> - 1]</code> 内</li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $f[i]$ 表示表示 $[0..i]$ 的栅栏柱且最后两个栅栏柱颜色不同的涂色方法数，定义 $g[i]$ 表示表示 $[0..i]$ 的栅栏柱且最后两个栅栏柱颜色相同的涂色方法数。初始时 $f[0] = k$，而 $g[0] = 0$。

当 $i > 0$ 时，有如下状态转移方程：

$$
\begin{aligned}
f[i] & = (f[i - 1] + g[i - 1]) \times (k - 1) \\
g[i] & = f[i - 1]
\end{aligned}
$$

最终的答案即为 $f[n - 1] + g[n - 1]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是栅栏的数量。

<!-- tabs:start -->

```python
class Solution:
    def numWays(self, n: int, k: int) -> int:
        f = [0] * n
        g = [0] * n
        f[0] = k
        for i in range(1, n):
            f[i] = (f[i - 1] + g[i - 1]) * (k - 1)
            g[i] = f[i - 1]
        return f[-1] + g[-1]
```

```java
class Solution {
    public int numWays(int n, int k) {
        int[] f = new int[n];
        int[] g = new int[n];
        f[0] = k;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + g[i - 1]) * (k - 1);
            g[i] = f[i - 1];
        }
        return f[n - 1] + g[n - 1];
    }
}
```

```cpp
class Solution {
public:
    int numWays(int n, int k) {
        vector<int> f(n);
        vector<int> g(n);
        f[0] = k;
        for (int i = 1; i < n; ++i) {
            f[i] = (f[i - 1] + g[i - 1]) * (k - 1);
            g[i] = f[i - 1];
        }
        return f[n - 1] + g[n - 1];
    }
};
```

```go
func numWays(n int, k int) int {
	f := make([]int, n)
	g := make([]int, n)
	f[0] = k
	for i := 1; i < n; i++ {
		f[i] = (f[i-1] + g[i-1]) * (k - 1)
		g[i] = f[i-1]
	}
	return f[n-1] + g[n-1]
}
```

```ts
function numWays(n: number, k: number): number {
    const f: number[] = Array(n).fill(0);
    const g: number[] = Array(n).fill(0);
    f[0] = k;
    for (let i = 1; i < n; ++i) {
        f[i] = (f[i - 1] + g[i - 1]) * (k - 1);
        g[i] = f[i - 1];
    }
    return f[n - 1] + g[n - 1];
}
```

<!-- tabs:end -->

### 方法二：动态规划（空间优化）

我们发现 $f[i]$ 和 $g[i]$ 只与 $f[i - 1]$ 和 $g[i - 1]$ 有关，因此我们可以使用两个变量 $f$ 和 $g$ 分别记录 $f[i - 1]$ 和 $g[i - 1]$ 的值，从而将空间复杂度优化到 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def numWays(self, n: int, k: int) -> int:
        f, g = k, 0
        for _ in range(n - 1):
            ff = (f + g) * (k - 1)
            g = f
            f = ff
        return f + g
```

```java
class Solution {
    public int numWays(int n, int k) {
        int f = k, g = 0;
        for (int i = 1; i < n; ++i) {
            int ff = (f + g) * (k - 1);
            g = f;
            f = ff;
        }
        return f + g;
    }
}
```

```cpp
class Solution {
public:
    int numWays(int n, int k) {
        int f = k, g = 0;
        for (int i = 1; i < n; ++i) {
            int ff = (f + g) * (k - 1);
            g = f;
            f = ff;
        }
        return f + g;
    }
};
```

```go
func numWays(n int, k int) int {
	f, g := k, 0
	for i := 1; i < n; i++ {
		f, g = (f+g)*(k-1), f
	}
	return f + g
}
```

```ts
function numWays(n: number, k: number): number {
    let [f, g] = [k, 0];
    for (let i = 1; i < n; ++i) {
        const ff = (f + g) * (k - 1);
        g = f;
        f = ff;
    }
    return f + g;
}
```

<!-- tabs:end -->

<!-- end -->
