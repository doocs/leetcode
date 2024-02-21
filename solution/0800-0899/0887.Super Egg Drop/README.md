# [887. 鸡蛋掉落](https://leetcode.cn/problems/super-egg-drop)

[English Version](/solution/0800-0899/0887.Super%20Egg%20Drop/README_EN.md)

<!-- tags:数学,二分查找,动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你 <code>k</code> 枚相同的鸡蛋，并可以使用一栋从第 <code>1</code> 层到第 <code>n</code> 层共有 <code>n</code> 层楼的建筑。</p>

<p>已知存在楼层 <code>f</code> ，满足 <code>0 <= f <= n</code> ，任何从<strong> 高于</strong> <code>f</code> 的楼层落下的鸡蛋都会碎，从 <code>f</code> 楼层或比它低的楼层落下的鸡蛋都不会破。</p>

<p>每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 <code>x</code> 扔下（满足 <code>1 <= x <= n</code>）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 <strong>重复使用</strong> 这枚鸡蛋。</p>

<p>请你计算并返回要确定 <code>f</code> <strong>确切的值</strong> 的 <strong>最小操作次数</strong> 是多少？</p>
 

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>k = 1, n = 2
<strong>输出：</strong>2
<strong>解释：</strong>
鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。 
否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。 
如果它没碎，那么肯定能得出 f = 2 。 
因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>k = 2, n = 6
<strong>输出：</strong>3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>k = 3, n = 14
<strong>输出：</strong>4
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= k <= 100</code></li>
	<li><code>1 <= n <= 10<sup>4</sup></code></li>
</ul>

## 解法

### 方法一：记忆化搜索 + 二分查找

我们设计一个函数 $dfs(i, j)$，表示有 $i$ 层楼以及 $j$ 个鸡蛋时，确定 $f$ 值的最小操作次数，那么答案就是 $dfs(n, k)$。

函数 $dfs(i, j)$ 的执行逻辑如下：

如果 $i \lt 1$，说明楼层已经小于等于 $0$，此时返回 $0$；

如果 $j = 1$，说明只有一个鸡蛋，那么只能从第一层开始一层一层试，最坏情况下需要试 $i$ 次，此时返回 $i$；

否则，我们考虑枚举第一个鸡蛋从第 $x$ 层扔下的情况，其中 $1 \le x \le i$。如果鸡蛋在第 $x$ 层扔下时碎了，说明 $f \lt x$，此时我们接下来需要在 $x - 1$ 层下方以及剩下的 $j - 1$ 个鸡蛋确定 $f$ 值，总共需要的最小操作次数为 $dfs(x - 1, j - 1) + 1$ 次；如果鸡蛋在第 $x$ 层扔下时没碎，说明 $f \gt x$，此时我们需要在第 $x + 1$ 层及以上以及剩下的 $j$ 个鸡蛋确定 $f$ 值，总共需要的最小操作次数为 $dfs(i - x, j) + 1$ 次。由于我们要保证最坏情况下操作次数最少，因此 $dfs(i, j) = \min_{1 \le x \le i} \max(dfs(x - 1, j - 1), dfs(i - x, j)) + 1$。

如果按照这样的方式枚举，由于状态数有 $n \times k$，每个状态需要枚举 $n$ 次，那么总时间复杂度达到 $O(n^2 \times k)$，这会超出时间限制，我们考虑如何进行优化。

我们注意到函数 $dfs(x - 1, j - 1)$ 随着 $x$ 的增大而单调递增，而函数 $dfs(i - x, j)$ 随着 $x$ 的增大而单调递减，因此存在一个最优的 $x$ 值使得 $\max(dfs(x - 1, j - 1), dfs(i - x, j))$ 达到最小值。我们可以对 $x$ 进行二分查找，找出这个最优的 $x$ 值。其中 $x$ 是满足 $dfs(x - 1, j - 1) \le dfs(i - x, j)$ 的最大整数。这样我们可以将时间复杂度降低到 $O(n \times k \log n)$。

时间复杂度 $O(n \times k \log n)$，空间复杂度 $O(n \times k)$。其中 $n$ 和 $k$ 分别是楼层数和鸡蛋数。

<!-- tabs:start -->

```python
class Solution:
    def superEggDrop(self, k: int, n: int) -> int:
        @cache
        def dfs(i: int, j: int) -> int:
            if i < 1:
                return 0
            if j == 1:
                return i
            l, r = 1, i
            while l < r:
                mid = (l + r + 1) >> 1
                a = dfs(mid - 1, j - 1)
                b = dfs(i - mid, j)
                if a <= b:
                    l = mid
                else:
                    r = mid - 1
            return max(dfs(l - 1, j - 1), dfs(i - l, j)) + 1

        return dfs(n, k)
```

```java
class Solution {
    private int[][] f;

    public int superEggDrop(int k, int n) {
        f = new int[n + 1][k + 1];
        return dfs(n, k);
    }

    private int dfs(int i, int j) {
        if (i < 1) {
            return 0;
        }
        if (j == 1) {
            return i;
        }
        if (f[i][j] != 0) {
            return f[i][j];
        }
        int l = 1, r = i;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            int a = dfs(mid - 1, j - 1);
            int b = dfs(i - mid, j);
            if (a <= b) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return f[i][j] = Math.max(dfs(l - 1, j - 1), dfs(i - l, j)) + 1;
    }
}
```

```cpp
class Solution {
public:
    int superEggDrop(int k, int n) {
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        function<int(int, int)> dfs = [&](int i, int j) -> int {
            if (i < 1) {
                return 0;
            }
            if (j == 1) {
                return i;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            int l = 1, r = i;
            while (l < r) {
                int mid = (l + r + 1) >> 1;
                int a = dfs(mid - 1, j - 1);
                int b = dfs(i - mid, j);
                if (a <= b) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return f[i][j] = max(dfs(l - 1, j - 1), dfs(i - l, j)) + 1;
        };
        return dfs(n, k);
    }
};
```

```go
func superEggDrop(k int, n int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i < 1 {
			return 0
		}
		if j == 1 {
			return i
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		l, r := 1, i
		for l < r {
			mid := (l + r + 1) >> 1
			a, b := dfs(mid-1, j-1), dfs(i-mid, j)
			if a <= b {
				l = mid
			} else {
				r = mid - 1
			}
		}
		f[i][j] = max(dfs(l-1, j-1), dfs(i-l, j)) + 1
		return f[i][j]
	}
	return dfs(n, k)
}
```

```ts
function superEggDrop(k: number, n: number): number {
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(k + 1).fill(0));
    const dfs = (i: number, j: number): number => {
        if (i < 1) {
            return 0;
        }
        if (j === 1) {
            return i;
        }
        if (f[i][j]) {
            return f[i][j];
        }
        let l = 1;
        let r = i;
        while (l < r) {
            const mid = (l + r + 1) >> 1;
            const a = dfs(mid - 1, j - 1);
            const b = dfs(i - mid, j);
            if (a <= b) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return (f[i][j] = Math.max(dfs(l - 1, j - 1), dfs(i - l, j)) + 1);
    };
    return dfs(n, k);
}
```

<!-- tabs:end -->

### 方法二：动态规划 + 二分查找

我们也可以使用动态规划的方法解决这个问题。

我们定义 $f[i][j]$ 表示有 $i$ 层楼以及 $j$ 个鸡蛋时，确定 $f$ 值的最小操作次数，那么答案就是 $f[n][k]$。

状态转移方程为 $f[i][j] = \min_{1 \le x \le i} \max(f[x - 1][j - 1], f[i - x][j]) + 1$。

与方法一类似，我们可以使用二分查找来优化 $x$ 的枚举过程。

时间复杂度 $O(n \times k \log n)$，空间复杂度 $O(n \times k)$。其中 $n$ 和 $k$ 分别是楼层数和鸡蛋数。

<!-- tabs:start -->

```python
class Solution:
    def superEggDrop(self, k: int, n: int) -> int:
        f = [[0] * (k + 1) for _ in range(n + 1)]
        for i in range(1, n + 1):
            f[i][1] = i
        for i in range(1, n + 1):
            for j in range(2, k + 1):
                l, r = 1, i
                while l < r:
                    mid = (l + r + 1) >> 1
                    a, b = f[mid - 1][j - 1], f[i - mid][j]
                    if a <= b:
                        l = mid
                    else:
                        r = mid - 1
                f[i][j] = max(f[l - 1][j - 1], f[i - l][j]) + 1
        return f[n][k]
```

```java
class Solution {
    public int superEggDrop(int k, int n) {
        int[][] f = new int[n + 1][k + 1];
        for (int i = 1; i <= n; ++i) {
            f[i][1] = i;
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 2; j <= k; ++j) {
                int l = 1, r = i;
                while (l < r) {
                    int mid = (l + r + 1) >> 1;
                    int a = f[mid - 1][j - 1];
                    int b = f[i - mid][j];
                    if (a <= b) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                f[i][j] = Math.max(f[l - 1][j - 1], f[i - l][j]) + 1;
            }
        }
        return f[n][k];
    }
}
```

```cpp
class Solution {
public:
    int superEggDrop(int k, int n) {
        int f[n + 1][k + 1];
        memset(f, 0, sizeof(f));
        for (int i = 1; i <= n; ++i) {
            f[i][1] = i;
        }
        for (int i = 1; i <= n; ++i) {
            for (int j = 2; j <= k; ++j) {
                int l = 1, r = i;
                while (l < r) {
                    int mid = (l + r + 1) >> 1;
                    int a = f[mid - 1][j - 1];
                    int b = f[i - mid][j];
                    if (a <= b) {
                        l = mid;
                    } else {
                        r = mid - 1;
                    }
                }
                f[i][j] = max(f[l - 1][j - 1], f[i - l][j]) + 1;
            }
        }
        return f[n][k];
    }
};
```

```go
func superEggDrop(k int, n int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
	}
	for i := 1; i <= n; i++ {
		f[i][1] = i
	}
	for i := 1; i <= n; i++ {
		for j := 2; j <= k; j++ {
			l, r := 1, i
			for l < r {
				mid := (l + r + 1) >> 1
				a, b := f[mid-1][j-1], f[i-mid][j]
				if a <= b {
					l = mid
				} else {
					r = mid - 1
				}
			}
			f[i][j] = max(f[l-1][j-1], f[i-l][j]) + 1
		}
	}
	return f[n][k]
}
```

```ts
function superEggDrop(k: number, n: number): number {
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(k + 1).fill(0));
    for (let i = 1; i <= n; ++i) {
        f[i][1] = i;
    }
    for (let i = 1; i <= n; ++i) {
        for (let j = 2; j <= k; ++j) {
            let l = 1;
            let r = i;
            while (l < r) {
                const mid = (l + r + 1) >> 1;
                const a = f[mid - 1][j - 1];
                const b = f[i - mid][j];
                if (a <= b) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            f[i][j] = Math.max(f[l - 1][j - 1], f[i - l][j]) + 1;
        }
    }
    return f[n][k];
}
```

<!-- tabs:end -->

<!-- end -->
