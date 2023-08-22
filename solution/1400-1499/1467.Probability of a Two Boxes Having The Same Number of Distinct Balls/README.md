# [1467. 两个盒子中球的颜色数相同的概率](https://leetcode.cn/problems/probability-of-a-two-boxes-having-the-same-number-of-distinct-balls)

[English Version](/solution/1400-1499/1467.Probability%20of%20a%20Two%20Boxes%20Having%20The%20Same%20Number%20of%20Distinct%20Balls/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>桌面上有 <code>2n</code> 个颜色不完全相同的球，球上的颜色共有 <code>k</code> 种。给你一个大小为 <code>k</code> 的整数数组 <code>balls</code> ，其中 <code>balls[i]</code> 是颜色为&nbsp;<code>i</code> 的球的数量。</p>

<p>所有的球都已经 <strong>随机打乱顺序</strong> ，前 <code>n</code> 个球放入第一个盒子，后 <code>n</code> 个球放入另一个盒子（请认真阅读示例 2 的解释部分）。</p>

<p><strong>注意：</strong>这两个盒子是不同的。例如，两个球颜色分别为 <code>a</code> 和 <code>b</code>，盒子分别为 <code>[]</code> 和 <code>()</code>，那么 <code>[a] (b)</code> 和 <code>[b] (a)</code> 这两种分配方式是不同的（请认真阅读示例的解释部分）。</p>

<p>请返回「两个盒子中球的颜色数相同」的情况的概率。答案与真实值误差在 <code>10^-5</code> 以内，则被视为正确答案</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>balls = [1,1]
<strong>输出：</strong>1.00000
<strong>解释：</strong>球平均分配的方式只有两种：
- 颜色为 1 的球放入第一个盒子，颜色为 2 的球放入第二个盒子
- 颜色为 2 的球放入第一个盒子，颜色为 1 的球放入第二个盒子
这两种分配，两个盒子中球的颜色数都相同。所以概率为 2/2 = 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>balls = [2,1,1]
<strong>输出：</strong>0.66667
<strong>解释：</strong>球的列表为 [1, 1, 2, 3]
随机打乱，得到 12 种等概率的不同打乱方案，每种方案概率为 1/12 ：
[1,1 / 2,3], [1,1 / 3,2], [1,2 / 1,3], [1,2 / 3,1], [1,3 / 1,2], [1,3 / 2,1], [2,1 / 1,3], [2,1 / 3,1], [2,3 / 1,1], [3,1 / 1,2], [3,1 / 2,1], [3,2 / 1,1]
然后，我们将前两个球放入第一个盒子，后两个球放入第二个盒子。
这 12 种可能的随机打乱方式中的 8 种满足「两个盒子中球的颜色数相同」。
概率 = 8/12 = 0.66667
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>balls = [1,2,1,2]
<strong>输出：</strong>0.60000
<strong>解释：</strong>球的列表为 [1, 2, 2, 3, 4, 4]。要想显示所有 180 种随机打乱方案是很难的，但只检查「两个盒子中球的颜色数相同」的 108 种情况是比较容易的。
概率 = 108 / 180 = 0.6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= balls.length &lt;= 8</code></li>
	<li><code>1 &lt;= balls[i] &lt;= 6</code></li>
	<li><code>sum(balls)</code> 是偶数</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索 + 组合数学**

我们知道 $2n$ 个球，平均分到两个盒子中，总共有 $C_{2n}^n$ 种分法。接下来，我们可以求出每种分法中，两个盒子中球的颜色数相同的情况数。最后，将两者相除即可。

我们可以预处理出组合数 $C_{n}^m$，然后使用记忆化搜索求解。

设计一个函数 $dfs(i, j, diff)$，表示当前从第 $i$ 种球开始，第一个盒子剩余可放置 $j$ 个球，两个盒子中球的颜色数的差为 $diff$ 的方案数。

函数 $dfs(i, j, diff)$ 的执行逻辑如下：

-   如果 $i \geq k$，表示所有球都已经放完，如果 $j = 0$ 且 $diff = 0$，表示两个盒子中球的颜色数相同，返回 $1$，否则返回 $0$；
-   如果 $j < 0$，表示第一个盒子中球的数量超过了 $n$，返回 $0$；
-   如果 $f[i][j][diff]$ 不为 $-1$，表示已经计算过，直接返回 $f[i][j][diff]$；
-   否则，枚举第 $i$ 种球放入第一个盒子中的数量 $x$，则第 $i$ 种球放入第二个盒子中的数量为 $balls[i] - x$，两个盒子中球的颜色数的变化量为 $y$。如果所有球都放入第一个盒子中，那么 $y = 1$；如果所有球都放入第二个盒子中，那么 $y = -1$；否则 $y = 0$。然后，递归计算 $dfs(i + 1, j - x, diff + y)$，并将结果与 $C_{balls[i]}^x$ 相乘，累加到答案中。最后，将答案存入 $f[i][j][diff]$ 中，并返回答案。

时间复杂度 $O(n^2 \times k^2)$，空间复杂度 $O(n \times k^2)$。其中 $n$ 和 $k$ 分别是球的总数和颜色的种数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getProbability(self, balls: List[int]) -> float:
        @cache
        def dfs(i: int, j: int, diff: int) -> float:
            if i >= k:
                return 1 if j == 0 and diff == 0 else 0
            if j < 0:
                return 0
            ans = 0
            for x in range(balls[i] + 1):
                y = 1 if x == balls[i] else (-1 if x == 0 else 0)
                ans += dfs(i + 1, j - x, diff + y) * comb(balls[i], x)
            return ans

        n = sum(balls) >> 1
        k = len(balls)
        return dfs(0, n, 0) / comb(n << 1, n)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int n;
    private long[][] c;
    private int[] balls;
    private Map<List<Integer>, Long> f = new HashMap<>();

    public double getProbability(int[] balls) {
        int mx = 0;
        for (int x : balls) {
            n += x;
            mx = Math.max(mx, x);
        }
        n >>= 1;
        this.balls = balls;
        int m = Math.max(mx, n << 1);
        c = new long[m + 1][m + 1];
        for (int i = 0; i <= m; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        return dfs(0, n, 0) * 1.0 / c[n << 1][n];
    }

    private long dfs(int i, int j, int diff) {
        if (i >= balls.length) {
            return j == 0 && diff == 0 ? 1 : 0;
        }
        if (j < 0) {
            return 0;
        }
        List<Integer> key = List.of(i, j, diff);
        if (f.containsKey(key)) {
            return f.get(key);
        }
        long ans = 0;
        for (int x = 0; x <= balls[i]; ++x) {
            int y = x == balls[i] ? 1 : (x == 0 ? -1 : 0);
            ans += dfs(i + 1, j - x, diff + y) * c[balls[i]][x];
        }
        f.put(key, ans);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double getProbability(vector<int>& balls) {
        int n = accumulate(balls.begin(), balls.end(), 0) / 2;
        int mx = *max_element(balls.begin(), balls.end());
        int m = max(mx, n << 1);
        long long c[m + 1][m + 1];
        memset(c, 0, sizeof(c));
        for (int i = 0; i <= m; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        int k = balls.size();
        long long f[k][n + 1][k << 1 | 1];
        memset(f, -1, sizeof(f));
        function<long long(int, int, int)> dfs = [&](int i, int j, int diff) -> long long {
            if (i >= k) {
                return j == 0 && diff == k ? 1 : 0;
            }
            if (j < 0) {
                return 0;
            }
            if (f[i][j][diff] != -1) {
                return f[i][j][diff];
            }
            long long ans = 0;
            for (int x = 0; x <= balls[i]; ++x) {
                int y = x == balls[i] ? 1 : (x == 0 ? -1 : 0);
                ans += dfs(i + 1, j - x, diff + y) * c[balls[i]][x];
            }
            return f[i][j][diff] = ans;
        };
        return dfs(0, n, k) * 1.0 / c[n << 1][n];
    }
};
```

### **Go**

```go
func getProbability(balls []int) float64 {
	n, mx := 0, 0
	for _, x := range balls {
		n += x
		mx = max(mx, x)
	}
	n >>= 1
	m := max(mx, n<<1)
	c := make([][]int, m+1)
	for i := range c {
		c[i] = make([]int, m+1)
	}
	for i := 0; i <= m; i++ {
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = c[i-1][j-1] + c[i-1][j]
		}
	}
	k := len(balls)
	f := make([][][]int, k)
	for i := range f {
		f[i] = make([][]int, n+1)
		for j := range f[i] {
			f[i][j] = make([]int, k<<1|1)
			for h := range f[i][j] {
				f[i][j][h] = -1
			}
		}
	}
	var dfs func(int, int, int) int
	dfs = func(i, j, diff int) int {
		if i >= k {
			if j == 0 && diff == k {
				return 1
			}
			return 0
		}
		if j < 0 {
			return 0
		}
		if f[i][j][diff] != -1 {
			return f[i][j][diff]
		}
		ans := 0
		for x := 0; x <= balls[i]; x++ {
			y := 1
			if x != balls[i] {
				if x == 0 {
					y = -1
				} else {
					y = 0
				}
			}
			ans += dfs(i+1, j-x, diff+y) * c[balls[i]][x]
		}
		f[i][j][diff] = ans
		return ans
	}
	return float64(dfs(0, n, k)) / float64(c[n<<1][n])
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function getProbability(balls: number[]): number {
    const n = balls.reduce((a, b) => a + b, 0) >> 1;
    const mx = Math.max(...balls);
    const m = Math.max(mx, n << 1);
    const c: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(m + 1).fill(0));
    for (let i = 0; i <= m; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= i; ++j) {
            c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
        }
    }
    const k = balls.length;
    const f: number[][][] = Array(k)
        .fill(0)
        .map(() =>
            Array(n + 1)
                .fill(0)
                .map(() => Array((k << 1) | 1).fill(-1)),
        );
    const dfs = (i: number, j: number, diff: number): number => {
        if (i >= k) {
            return j === 0 && diff === k ? 1 : 0;
        }
        if (j < 0) {
            return 0;
        }
        if (f[i][j][diff] !== -1) {
            return f[i][j][diff];
        }
        let ans = 0;
        for (let x = 0; x <= balls[i]; ++x) {
            const y = x === balls[i] ? 1 : x === 0 ? -1 : 0;
            ans += dfs(i + 1, j - x, diff + y) * c[balls[i]][x];
        }
        return (f[i][j][diff] = ans);
    };
    return dfs(0, n, k) / c[n << 1][n];
}
```

### **...**

```

```

<!-- tabs:end -->
