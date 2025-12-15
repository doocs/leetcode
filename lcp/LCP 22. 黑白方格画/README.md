---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2022.%20%E9%BB%91%E7%99%BD%E6%96%B9%E6%A0%BC%E7%94%BB/README.md
---

<!-- problem:start -->

# [LCP 22. 黑白方格画](https://leetcode.cn/problems/ccw6C7)

## 题目描述

<!-- description:start -->

小扣注意到秋日市集上有一个创作黑白方格画的摊位。摊主给每个顾客提供一个固定在墙上的白色画板，画板不能转动。画板上有 `n * n` 的网格。绘画规则为，小扣可以选择任意多行以及任意多列的格子涂成黑色（选择的整行、整列均需涂成黑色），所选行数、列数均可为 0。

小扣希望最终的成品上需要有 `k` 个黑色格子，请返回小扣共有多少种涂色方案。

注意：两个方案中任意一个相同位置的格子颜色不同，就视为不同的方案。

**示例 1：**

> 输入：`n = 2, k = 2`
>
> 输出：`4`
>
> 解释：一共有四种不同的方案：
> 第一种方案：涂第一列；
> 第二种方案：涂第二列；
> 第三种方案：涂第一行；
> 第四种方案：涂第二行。

**示例 2：**

> 输入：`n = 2, k = 1`
>
> 输出：`0`
>
> 解释：不可行，因为第一次涂色至少会涂两个黑格。

**示例 3：**

> 输入：`n = 2, k = 4`
>
> 输出：`1`
>
> 解释：共有 2\*2=4 个格子，仅有一种涂色方案。

**限制：**

- `1 <= n <= 6`
- `0 <= k <= n * n`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：组合计数

我们可以选择涂黑 $n$ 行中的任意 $i$ 行，涂黑 $n$ 列中的任意 $j$ 列。那么涂黑的格子数为 $n \times (i + j) - i \times j$。如果满足 $n \times (i + j) - i \times j = k$，则方案数为 $\binom{n}{i} \times \binom{n}{j}$。累加所有满足条件的方案数即可。

注意，如果 $k = n \times n$，则只有一种方案，直接返回 $1$ 即可。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 是网格的边长。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def paintingPlan(self, n: int, k: int) -> int:
        if k == n * n:
            return 1
        ans = 0
        for i in range(n + 1):
            for j in range(n + 1):
                if n * (i + j) - i * j == k:
                    ans += comb(n, i) * comb(n, j)
        return ans
```

#### Java

```java
class Solution {
    public int paintingPlan(int n, int k) {
        if (k == n * n) {
            return 1;
        }
        int[][] c = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        int ans = 0;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (n * (i + j) - i * j == k) {
                    ans += c[n][i] * c[n][j];
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int paintingPlan(int n, int k) {
        if (k == n * n) {
            return 1;
        }
        int c[n + 1][n + 1];
        memset(c, 0, sizeof(c));
        for (int i = 0; i <= n; ++i) {
            c[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
        int ans = 0;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= n; ++j) {
                if (n * (i + j) - i * j == k) {
                    ans += c[n][i] * c[n][j];
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func paintingPlan(n int, k int) (ans int) {
	if k == n*n {
		return 1
	}
	c := make([][]int, n+1)
	for i := range c {
		c[i] = make([]int, n+1)
	}
	for i := 0; i <= n; i++ {
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = c[i-1][j] + c[i-1][j-1]
		}
	}
	for i := 0; i <= n; i++ {
		for j := 0; j <= n; j++ {
			if n*(i+j)-i*j == k {
				ans += c[n][i] * c[n][j]
			}
		}
	}
	return
}
```

#### TypeScript

```ts
function paintingPlan(n: number, k: number): number {
    if (k === n * n) {
        return 1;
    }
    const c: number[][] = Array(n + 1)
        .fill(0)
        .map(() => Array(n + 1).fill(0));
    for (let i = 0; i <= n; ++i) {
        c[i][0] = 1;
        for (let j = 1; j <= i; ++j) {
            c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
        }
    }
    let ans = 0;
    for (let i = 0; i <= n; ++i) {
        for (let j = 0; j <= n; ++j) {
            if (n * (i + j) - i * j === k) {
                ans += c[n][i] * c[n][j];
            }
        }
    }
    return ans;
}
```

#### Swift

```swift
class Solution {
    func paintingPlan(_ n: Int, _ k: Int) -> Int {
        if k == 0 || k == n * n {
            return 1
        }

        func combination(_ n: Int, _ r: Int) -> Int {
            guard r <= n else { return 0 }
            if r == 0 || r == n { return 1 }
            var result = 1
            for i in 0..<r {
                result = result * (n - i) / (i + 1)
            }
            return result
        }

        var ans = 0

        for i in 0...n {
            for j in 0...n {
                let paintedCells = n * (i + j) - i * j
                if paintedCells == k {
                    ans += combination(n, i) * combination(n, j)
                }
            }
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
