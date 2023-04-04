# [1000. 合并石头的最低成本](https://leetcode.cn/problems/minimum-cost-to-merge-stones)

[English Version](/solution/1000-1099/1000.Minimum%20Cost%20to%20Merge%20Stones/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有 <code>N</code> 堆石头排成一排，第 <code>i</code> 堆中有&nbsp;<code>stones[i]</code>&nbsp;块石头。</p>

<p>每次<em>移动（move）</em>需要将<strong>连续的</strong>&nbsp;<code>K</code>&nbsp;堆石头合并为一堆，而这个移动的成本为这&nbsp;<code>K</code>&nbsp;堆石头的总数。</p>

<p>找出把所有石头合并成一堆的最低成本。如果不可能，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>stones = [3,2,4,1], K = 2
<strong>输出：</strong>20
<strong>解释：</strong>
从 [3, 2, 4, 1] 开始。
合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。
合并 [4, 1]，成本为 5，剩下 [5, 5]。
合并 [5, 5]，成本为 10，剩下 [10]。
总成本 20，这是可能的最小值。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>stones = [3,2,4,1], K = 3
<strong>输出：</strong>-1
<strong>解释：</strong>任何合并操作后，都会剩下 2 堆，我们无法再进行合并。所以这项任务是不可能完成的。.
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>stones = [3,5,1,2,6], K = 3
<strong>输出：</strong>25
<strong>解释：</strong>
从 [3, 5, 1, 2, 6] 开始。
合并 [5, 1, 2]，成本为 8，剩下 [3, 8, 6]。
合并 [3, 8, 6]，成本为 17，剩下 [17]。
总成本 25，这是可能的最小值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= stones.length &lt;= 30</code></li>
	<li><code>2 &lt;= K &lt;= 30</code></li>
	<li><code>1 &lt;= stones[i] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划（区间 DP）+ 前缀和**

我们不妨记题目中的 $k$ 为 $K$，石头的堆数为 $n$。

定义 $f[i][j][k]$ 表示将区间 $[i, j]$ 中的石头合并成 $k$ 堆的最小成本。初始时 $f[i][i][1] = 0$，其他位置的值均为 $\infty$。

注意到 $k$ 的取值范围为 $[1, K]$，因此我们需要枚举 $k$ 的值。

对于 $f[i][j][k]$，我们可以枚举 $i \leq h \lt j$，将区间 $[i, j]$ 拆分成两个区间 $[i, h]$ 和 $[h + 1, j]$，然后将 $[i, h]$ 中的石头合并成 $1$ 堆，将 $[h + 1, j]$ 中的石头合并成 $k - 1$ 堆，最后将这两堆石头合并成一堆，这样就可以将区间 $[i, j]$ 中的石头合并成 $k$ 堆。因此，我们可以得到状态转移方程：

$$
f[i][j][k] = \min_{i \leq h < j} \{f[i][h][1] + f[h + 1][j][k - 1]\}
$$

我们将区间 $[i, j]$ 的 $K$ 堆石头合并成一堆，因此 $f[i][j][1] = f[i][j][K] + \sum_{t = i}^j stones[t]$，其中 $\sum_{t = i}^j stones[t]$ 表示区间 $[i, j]$ 中石头的总数。

最后答案即为 $f[1][n][1]$，其中 $n$ 为石头的堆数。

时间复杂度 $O(n^3 \times k)$，空间复杂度 $O(n^2 \times k)$。其中 $n$ 为石头的堆数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def mergeStones(self, stones: List[int], K: int) -> int:
        n = len(stones)
        if (n - 1) % (K - 1):
            return -1
        s = list(accumulate(stones, initial=0))
        f = [[[inf] * (K + 1) for _ in range(n + 1)] for _ in range(n + 1)]
        for i in range(1, n + 1):
            f[i][i][1] = 0
        for l in range(2, n + 1):
            for i in range(1, n - l + 2):
                j = i + l - 1
                for k in range(1, K + 1):
                    for h in range(i, j):
                        f[i][j][k] = min(f[i][j][k], f[i][h][1] + f[h + 1][j][k - 1])
                f[i][j][1] = f[i][j][K] + s[j] - s[i - 1]
        return f[1][n][1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if ((n - 1) % (K - 1) != 0) {
            return -1;
        }
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stones[i - 1];
        }
        int[][][] f = new int[n + 1][n + 1][K + 1];
        final int inf = 1 << 20;
        for (int[][] g : f) {
            for (int[] e : g) {
                Arrays.fill(e, inf);
            }
        }
        for (int i = 1; i <= n; ++i) {
            f[i][i][1] = 0;
        }
        for (int l = 2; l <= n; ++l) {
            for (int i = 1; i + l - 1 <= n; ++i) {
                int j = i + l - 1;
                for (int k = 1; k <= K; ++k) {
                    for (int h = i; h < j; ++h) {
                        f[i][j][k] = Math.min(f[i][j][k], f[i][h][1] + f[h + 1][j][k - 1]);
                    }
                }
                f[i][j][1] = f[i][j][K] + s[j] - s[i - 1];
            }
        }
        return f[1][n][1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int mergeStones(vector<int>& stones, int K) {
        int n = stones.size();
        if ((n - 1) % (K - 1)) {
            return -1;
        }
        int s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stones[i - 1];
        }
        int f[n + 1][n + 1][K + 1];
        memset(f, 0x3f, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            f[i][i][1] = 0;
        }
        for (int l = 2; l <= n; ++l) {
            for (int i = 1; i + l - 1 <= n; ++i) {
                int j = i + l - 1;
                for (int k = 1; k <= K; ++k) {
                    for (int h = i; h < j; ++h) {
                        f[i][j][k] = min(f[i][j][k], f[i][h][1] + f[h + 1][j][k - 1]);
                    }
                }
                f[i][j][1] = f[i][j][K] + s[j] - s[i - 1];
            }
        }
        return f[1][n][1];
    }
};
```

### **Go**

```go
func mergeStones(stones []int, K int) int {
	n := len(stones)
	if (n-1)%(K-1) != 0 {
		return -1
	}
	s := make([]int, n+1)
	for i, x := range stones {
		s[i+1] = s[i] + x
	}
	f := make([][][]int, n+1)
	for i := range f {
		f[i] = make([][]int, n+1)
		for j := range f[i] {
			f[i][j] = make([]int, K+1)
			for k := range f[i][j] {
				f[i][j][k] = 1 << 20
			}
		}
	}
	for i := 1; i <= n; i++ {
		f[i][i][1] = 0
	}
	for l := 2; l <= n; l++ {
		for i := 1; i <= n-l+1; i++ {
			j := i + l - 1
			for k := 2; k <= K; k++ {
				for h := i; h < j; h++ {
					f[i][j][k] = min(f[i][j][k], f[i][h][k-1]+f[h+1][j][1])
				}
			}
			f[i][j][1] = f[i][j][K] + s[j] - s[i-1]
		}
	}
	return f[1][n][1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
