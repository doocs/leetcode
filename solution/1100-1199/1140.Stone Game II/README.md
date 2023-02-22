# [1140. 石子游戏 II](https://leetcode.cn/problems/stone-game-ii)

[English Version](/solution/1100-1199/1140.Stone%20Game%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>爱丽丝和鲍勃继续他们的石子游戏。许多堆石子&nbsp;<strong>排成一行</strong>，每堆都有正整数颗石子&nbsp;<code>piles[i]</code>。游戏以谁手中的石子最多来决出胜负。</p>

<p>爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，<code>M = 1</code>。</p>

<p>在每个玩家的回合中，该玩家可以拿走剩下的&nbsp;<strong>前</strong>&nbsp;<code>X</code>&nbsp;堆的所有石子，其中&nbsp;<code>1 &lt;= X &lt;= 2M</code>。然后，令&nbsp;<code>M = max(M, X)</code>。</p>

<p>游戏一直持续到所有石子都被拿走。</p>

<p>假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>piles = [2,7,9,4,4]
<strong>输出：</strong>10
<strong>解释：</strong>如果一开始Alice取了一堆，Bob取了两堆，然后Alice再取两堆。爱丽丝可以得到2 + 4 + 4 = 10堆。如果Alice一开始拿走了两堆，那么Bob可以拿走剩下的三堆。在这种情况下，Alice得到2 + 7 = 9堆。返回10，因为它更大。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入：</strong>piles = [1,2,3,4,5,100]
<strong>输出：</strong>104
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= piles.length &lt;= 100</code></li>
	<li><meta charset="UTF-8" /><code>1 &lt;= piles[i]&nbsp;&lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 记忆化搜索**

由于玩家每次可以拿走前 $X$ 堆的所有石子，也就是说能拿走一个区间的石子，因此，我们可以先预处理出一个长度为 $n+1$ 的前缀和数组 $s$，其中 $s[i]$ 表示数组 `piles` 的前 $i$ 个元素的和。

然后我们设计一个函数 $dfs(i, m)$，表示当前轮到的人可以从数组 `piles` 的下标 $i$ 开始拿，且当前的 $M$ 为 $m$ 时，当前轮到的人能够拿到的最大石子数。初始时爱丽丝从下标 $0$ 开始，且 $M=1$，所以我们需要求的答案为 $dfs(0, 1)$。

函数 $dfs(i, m)$ 的计算过程如下：

-   如果当前轮到的人可以拿走剩下的所有石子，能够拿到的最大石子数为 $s[n] - s[i]$；
-   否则，当前轮到的人可以拿走剩下的前 $x$ 堆的所有石子，其中 $1 \leq x \leq 2m$，能够拿到的最大石子数为 $s[n] - s[i] - dfs(i + x, max(m, x))$。也即是说，当前轮的人能够拿到的石子数为当前剩下的所有石子数减去下一轮对手能够拿到的石子数。我们需要枚举所有的 $x$，取其中的最大值作为函数 $dfs(i, m)$ 的返回值。

为了避免重复计算，我们可以使用记忆化搜索。

最后，我们返回将 $dfs(0, 1)$ 作为答案返回即可。

时间复杂度为 $O(n^3)$，空间复杂度为 $O(n^2)$。其中 $n$ 为数组 `piles` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def stoneGameII(self, piles: List[int]) -> int:
        @cache
        def dfs(i, m):
            if m * 2 >= n - i:
                return s[n] - s[i]
            return max(
                s[n] - s[i] - dfs(i + x, max(m, x)) for x in range(1, m << 1 | 1)
            )

        n = len(piles)
        s = list(accumulate(piles, initial=0))
        return dfs(0, 1)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] s;
    private Integer[][] f;
    private int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        s = new int[n + 1];
        f = new Integer[n][n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + piles[i];
        }
        return dfs(0, 1);
    }

    private int dfs(int i, int m) {
        if (m * 2 >= n - i) {
            return s[n] - s[i];
        }
        if (f[i][m] != null) {
            return f[i][m];
        }
        int res = 0;
        for (int x = 1; x <= m * 2; ++x) {
            res = Math.max(res, s[n] - s[i] - dfs(i + x, Math.max(m, x)));
        }
        return f[i][m] = res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int stoneGameII(vector<int>& piles) {
        int n = piles.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + piles[i];
        }
        int f[n][n + 1];
        memset(f, 0, sizeof f);
        function<int(int, int)> dfs = [&](int i, int m) -> int {
            if (m * 2 >= n - i) {
                return s[n] - s[i];
            }
            if (f[i][m]) {
                return f[i][m];
            }
            int res = 0;
            for (int x = 1; x <= m << 1; ++x) {
                res = max(res, s[n] - s[i] - dfs(i + x, max(x, m)));
            }
            return f[i][m] = res;
        };
        return dfs(0, 1);
    }
};
```

### **Go**

```go
func stoneGameII(piles []int) int {
	n := len(piles)
	s := make([]int, n+1)
	f := make([][]int, n+1)
	for i, x := range piles {
		s[i+1] = s[i] + x
		f[i] = make([]int, n+1)
	}
	var dfs func(i, m int) int
	dfs = func(i, m int) int {
		if m*2 >= n-i {
			return s[n] - s[i]
		}
		if f[i][m] > 0 {
			return f[i][m]
		}
		f[i][m] = 0
		for x := 1; x <= m<<1; x++ {
			f[i][m] = max(f[i][m], s[n]-s[i]-dfs(i+x, max(m, x)))
		}
		return f[i][m]
	}
	return dfs(0, 1)
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
function stoneGameII(piles: number[]): number {
    const n = piles.length;
    const f = Array.from({ length: n }, _ => new Array(n + 1).fill(0));
    const s = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + piles[i];
    }
    const dfs = (i: number, m: number) => {
        if (m * 2 >= n - i) {
            return s[n] - s[i];
        }
        if (f[i][m]) {
            return f[i][m];
        }
        let res = 0;
        for (let x = 1; x <= m * 2; ++x) {
            res = Math.max(res, s[n] - s[i] - dfs(i + x, Math.max(m, x)));
        }
        return (f[i][m] = res);
    };
    return dfs(0, 1);
}
```

### **...**

```

```

<!-- tabs:end -->
