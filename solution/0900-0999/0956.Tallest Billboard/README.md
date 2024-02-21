# [956. 最高的广告牌](https://leetcode.cn/problems/tallest-billboard)

[English Version](/solution/0900-0999/0956.Tallest%20Billboard/README_EN.md)

<!-- tags:数组,动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>你正在安装一个广告牌，并希望它高度最大。这块广告牌将有两个钢制支架，两边各一个。每个钢支架的高度必须相等。</p>

<p>你有一堆可以焊接在一起的钢筋 <code>rods</code>。举个例子，如果钢筋的长度为 <code>1</code>、<code>2</code> 和 <code>3</code>，则可以将它们焊接在一起形成长度为 <code>6</code>&nbsp;的支架。</p>

<p>返回 <em>广告牌的最大可能安装高度</em> 。如果没法安装广告牌，请返回 <code>0</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[1,2,3,6]
<strong>输出：</strong>6
<strong>解释：</strong>我们有两个不相交的子集 {1,2,3} 和 {6}，它们具有相同的和 sum = 6。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[1,2,3,4,5,6]
<strong>输出：</strong>10
<strong>解释：</strong>我们有两个不相交的子集 {2,3,5} 和 {4,6}，它们具有相同的和 sum = 10。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>[1,2]
<strong>输出：</strong>0
<strong>解释：</strong>没法安装广告牌，所以返回 0。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= rods.length &lt;= 20</code></li>
	<li><code>1 &lt;= rods[i] &lt;= 1000</code></li>
	<li><code>sum(rods[i]) &lt;= 5000</code></li>
</ol>

## 解法

### 方法一：记忆化搜索

我们设计一个函数 $dfs(i, j)$，表示从第 $i$ 根钢筋开始，且当前高度差为 $j$ 时，两边的最大共同高度。那么答案就是 $dfs(0, 0)$。

函数 $dfs(i, j)$ 的计算过程如下：

如果 $i=n$，此时判断 $j$ 是否为 $0$，如果是则返回 $0$，否则返回 $-\infty$。

如果 $i \lt n$，此时有三种情况：

1. 不选择第 $i$ 根钢筋，此时 $dfs(i, j) = dfs(i+1, j)$；
1. 选择第 $i$ 根钢筋，并且放置在原本高度较高的一边，那么 $dfs(i, j) = dfs(i+1, j+rods[i])$；
1. 选择第 $i$ 根钢筋，并且放置在高度较低的一边，此时共同高度增加了 $\min(j, rods[i])$，那么 $dfs(i, j) = dfs(i+1, |rods[i]-j|) + \min(j, rods[i])$。

我们取这三种情况下的最大值，即可得到 $dfs(i, j)$ 的值。

为了避免重复计算，我们使用一个二维数组 $f$ 来记录已经计算过的 $dfs(i, j)$ 的值，调用 $dfs(i, j)$ 时，如果 $f[i][j]$ 已经被计算过，则直接返回 $f[i][j]$。否则，我们计算 $dfs(i, j)$ 的值，并将其存入 $f[i][j]$。

时间复杂度 $O(n \times S)$，空间复杂度 $O(n \times S)$。其中 $n$ 和 $S$ 分别为 $rods$ 的长度和 $rods$ 中所有元素的和。

<!-- tabs:start -->

```python
class Solution:
    def tallestBillboard(self, rods: List[int]) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i >= len(rods):
                return 0 if j == 0 else -inf
            ans = max(dfs(i + 1, j), dfs(i + 1, j + rods[i]))
            ans = max(ans, dfs(i + 1, abs(rods[i] - j)) + min(j, rods[i]))
            return ans

        return dfs(0, 0)
```

```java
class Solution {
    private Integer[][] f;
    private int[] rods;
    private int n;

    public int tallestBillboard(int[] rods) {
        int s = 0;
        for (int x : rods) {
            s += x;
        }
        n = rods.length;
        this.rods = rods;
        f = new Integer[n][s + 1];
        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i >= n) {
            return j == 0 ? 0 : -(1 << 30);
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int ans = Math.max(dfs(i + 1, j), dfs(i + 1, j + rods[i]));
        ans = Math.max(ans, dfs(i + 1, Math.abs(rods[i] - j)) + Math.min(j, rods[i]));
        return f[i][j] = ans;
    }
}
```

```cpp
class Solution {
public:
    int tallestBillboard(vector<int>& rods) {
        int s = accumulate(rods.begin(), rods.end(), 0);
        int n = rods.size();
        int f[n][s + 1];
        memset(f, -1, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i >= n) {
                return j == 0 ? 0 : -(1 << 30);
            }
            if (f[i][j] != -1) {
                return f[i][j];
            }
            int ans = max(dfs(i + 1, j), dfs(i + 1, j + rods[i]));
            ans = max(ans, dfs(i + 1, abs(j - rods[i])) + min(j, rods[i]));
            return f[i][j] = ans;
        };
        return dfs(0, 0);
    }
};
```

```go
func tallestBillboard(rods []int) int {
	s := 0
	for _, x := range rods {
		s += x
	}
	n := len(rods)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, s+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i >= n {
			if j == 0 {
				return 0
			}
			return -(1 << 30)
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		ans := max(dfs(i+1, j), dfs(i+1, j+rods[i]))
		ans = max(ans, dfs(i+1, abs(j-rods[i]))+min(j, rods[i]))
		f[i][j] = ans
		return ans
	}
	return dfs(0, 0)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function tallestBillboard(rods: number[]): number {
    const s = rods.reduce((a, b) => a + b, 0);
    const n = rods.length;
    const f = new Array(n).fill(0).map(() => new Array(s + 1).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (i >= n) {
            return j === 0 ? 0 : -(1 << 30);
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        let ans = Math.max(dfs(i + 1, j), dfs(i + 1, j + rods[i]));
        ans = Math.max(ans, dfs(i + 1, Math.abs(j - rods[i])) + Math.min(j, rods[i]));
        return (f[i][j] = ans);
    };
    return dfs(0, 0);
}
```

<!-- tabs:end -->

### 方法二：动态规划

我们定义 $f[i][j]$ 表示前 $i$ 根钢筋，两边高度差为 $j$ 时的最大共同高度。初始时 $f[0][0]=0$，其余 $f[i][j]=-\infty$。我们求出所有 $rods[i]$ 的和，记为 $s$，那么 $j$ 的取值范围为 $[0,..s]$。

对于第 $i$ 根钢筋，我们可以不选择它，此时 $f[i][j]=f[i-1][j]$；也可以选择它，此时有三种情况：

1. 放置在原本高度较高的一边，即满足 $j \geq rods[i-1]$，此时 $f[i][j] = max(f[i][j], f[i-1][j-rods[i-1]])$；
1. 放置在原本高度较低的一遍，如果满足 $j + rods[i-1] \leq s$，此时 $f[i][j] = max(f[i][j], f[i-1][j+rods[i-1]] + rods[i-1])$；如果满足 $j \lt rods[i-1]$，此时 $f[i][j] = max(f[i][j], f[i-1][rods[i-1]-j] + rods[i-1]-j)$。

综上，我们可以得到状态转移方程：

$$
\begin{aligned}
f[i][j] &= f[i-1][j] \\
f[i][j] &= max(f[i][j], f[i-1][j-rods[i-1]]) & \text{if } j \geq rods[i-1] \\
f[i][j] &= max(f[i][j], f[i-1][j+rods[i-1]] + rods[i-1]) & \text{if } j + rods[i-1] \leq s \\
f[i][j] &= max(f[i][j], f[i-1][rods[i-1]-j] + rods[i-1]-j) & \text{if } j \lt rods[i-1]
\end{aligned}
$$

最终答案即为 $f[n][0]$。

时间复杂度 $O(n \times S)$，空间复杂度 $O(n \times S)$。其中 $n$ 和 $S$ 分别为 $rods$ 的长度和 $rods$ 中所有元素的和。

<!-- tabs:start -->

```python
class Solution:
    def tallestBillboard(self, rods: List[int]) -> int:
        n = len(rods)
        s = sum(rods)
        f = [[-inf] * (s + 1) for _ in range(n + 1)]
        f[0][0] = 0
        t = 0
        for i, x in enumerate(rods, 1):
            t += x
            for j in range(t + 1):
                f[i][j] = f[i - 1][j]
                if j >= x:
                    f[i][j] = max(f[i][j], f[i - 1][j - x])
                if j + x <= t:
                    f[i][j] = max(f[i][j], f[i - 1][j + x] + x)
                if j < x:
                    f[i][j] = max(f[i][j], f[i - 1][x - j] + x - j)
        return f[n][0]
```

```java
class Solution {
    public int tallestBillboard(int[] rods) {
        int n = rods.length;
        int s = 0;
        for (int x : rods) {
            s += x;
        }
        int[][] f = new int[n + 1][s + 1];
        for (var e : f) {
            Arrays.fill(e, -(1 << 30));
        }
        f[0][0] = 0;
        for (int i = 1, t = 0; i <= n; ++i) {
            int x = rods[i - 1];
            t += x;
            for (int j = 0; j <= t; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= x) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - x]);
                }
                if (j + x <= t) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j + x] + x);
                }
                if (j < x) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][x - j] + x - j);
                }
            }
        }
        return f[n][0];
    }
}
```

```cpp
class Solution {
public:
    int tallestBillboard(vector<int>& rods) {
        int n = rods.size();
        int s = accumulate(rods.begin(), rods.end(), 0);
        int f[n + 1][s + 1];
        memset(f, -0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1, t = 0; i <= n; ++i) {
            int x = rods[i - 1];
            t += x;
            for (int j = 0; j <= t; ++j) {
                f[i][j] = f[i - 1][j];
                if (j >= x) {
                    f[i][j] = max(f[i][j], f[i - 1][j - x]);
                }
                if (j + x <= t) {
                    f[i][j] = max(f[i][j], f[i - 1][j + x] + x);
                }
                if (j < x) {
                    f[i][j] = max(f[i][j], f[i - 1][x - j] + x - j);
                }
            }
        }
        return f[n][0];
    }
};
```

```go
func tallestBillboard(rods []int) int {
	n := len(rods)
	s := 0
	for _, x := range rods {
		s += x
	}
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, s+1)
		for j := range f[i] {
			f[i][j] = -(1 << 30)
		}
	}
	f[0][0] = 0
	for i, t := 1, 0; i <= n; i++ {
		x := rods[i-1]
		t += x
		for j := 0; j <= t; j++ {
			f[i][j] = f[i-1][j]
			if j >= x {
				f[i][j] = max(f[i][j], f[i-1][j-x])
			}
			if j+x <= t {
				f[i][j] = max(f[i][j], f[i-1][j+x]+x)
			}
			if j < x {
				f[i][j] = max(f[i][j], f[i-1][x-j]+x-j)
			}
		}
	}
	return f[n][0]
}
```

<!-- tabs:end -->

<!-- end -->
