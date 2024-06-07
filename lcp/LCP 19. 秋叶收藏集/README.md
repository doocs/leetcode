---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2019.%20%E7%A7%8B%E5%8F%B6%E6%94%B6%E8%97%8F%E9%9B%86/README.md
---

<!-- problem:start -->

# [LCP 19. 秋叶收藏集](https://leetcode.cn/problems/UlBDOe)

## 题目描述

<!-- description:start -->

小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 `leaves`， 字符串 `leaves` 仅包含小写字符 `r` 和 `y`， 其中字符 `r` 表示一片红叶，字符 `y` 表示一片黄叶。

出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。

**示例 1：**

> 输入：`leaves = "rrryyyrryyyrr"`

>

> 输出：`2`

>

> 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"

**示例 2：**

> 输入：`leaves = "ryr"`

>

> 输出：`0`

>

> 解释：已符合要求，不需要额外操作

**提示：**

-   `3 <= leaves.length <= 10^5`

-   `leaves` 中只包含字符 `'r'` 和字符 `'y'`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：动态规划

我们定义 $f[i][j]$ 表示对于第 $i$ 片叶子，处于状态 $j$ 时的最小操作次数，其中 $j$ 表示：

-   状态 $0$ 表示第 $i$ 片叶子处于左边的红色部分；
-   状态 $1$ 表示第 $i$ 片叶子处于中间的黄色部分；
-   状态 $2$ 表示第 $i$ 片叶子处于右边的红色部分。

初始时，如果第 $0$ 片叶子为红色，那么 $f[0][0] = 0$，如果第 $0$ 片叶子为黄色，那么 $f[0][0] = 1$。对于其余的状态，我们初始化为 $+\infty$，表示在这种状态下无法完成任务。

考虑 $f[i][j]$，其中 $i \ge 1$：

如果第 $i$ 片叶子为红色，那么 $f[i][0]$ 只能从 $f[i-1][0]$ 转移而来，而 $f[i][1]$ 可以从 $f[i-1][0]$ 和 $f[i-1][1]$ 转移而来，此时需要额外操作一次，而 $f[i][2]$ 可以从 $f[i-1][1]$ 和 $f[i-1][2]$ 转移而来，此时不需要额外操作一次。

如果第 $i$ 片叶子为黄色，那么 $f[i][0]$ 只能从 $f[i-1][0]$ 转移而来，并且需要额外操作一次，而 $f[i][1]$ 可以从 $f[i-1][0]$ 和 $f[i-1][1]$ 转移而来，此时不需要额外操作一次，而 $f[i][2]$ 可以从 $f[i-1][1]$ 和 $f[i-1][2]$ 转移而来，并且需要额外操作一次。

最终的答案即为 $f[n-1][2]$，其中 $n$ 表示红叶和黄叶的总数。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumOperations(self, leaves: str) -> int:
        n = len(leaves)
        f = [[inf] * 3 for _ in range(n)]
        f[0][0] = int(leaves[0] == "y")
        for i in range(1, n):
            if leaves[i] == "r":
                f[i][0] = f[i - 1][0]
                f[i][1] = min(f[i - 1][0], f[i - 1][1]) + 1
                f[i][2] = min(f[i - 1][2], f[i - 1][1])
            else:
                f[i][0] = f[i - 1][0] + 1
                f[i][1] = min(f[i - 1][0], f[i - 1][1])
                f[i][2] = min(f[i - 1][2], f[i - 1][1]) + 1
        return f[n - 1][2]
```

#### Java

```java
class Solution {
    public int minimumOperations(String leaves) {
        int n = leaves.length();
        final int inf = 1 << 30;
        var f = new int[n][3];
        for (var g : f) {
            Arrays.fill(g, inf);
        }
        f[0][0] = leaves.charAt(0) == 'r' ? 0 : 1;
        for (int i = 1; i < n; ++i) {
            if (leaves.charAt(i) == 'r') {
                f[i][0] = f[i - 1][0];
                f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + 1;
                f[i][2] = Math.min(f[i - 1][2], f[i - 1][1]);
            } else {
                f[i][0] = f[i - 1][0] + 1;
                f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]);
                f[i][2] = Math.min(f[i - 1][2], f[i - 1][1]) + 1;
            }
        }
        return f[n - 1][2];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumOperations(string leaves) {
        int n = leaves.size();
        int f[n][3];
        memset(f, 0x3f, sizeof(f));
        f[0][0] = leaves[0] == 'y';
        for (int i = 1; i < n; ++i) {
            if (leaves[i] == 'r') {
                f[i][0] = f[i - 1][0];
                f[i][1] = min(f[i - 1][0], f[i - 1][1]) + 1;
                f[i][2] = min(f[i - 1][2], f[i - 1][1]);
            } else {
                f[i][0] = f[i - 1][0] + 1;
                f[i][1] = min(f[i - 1][0], f[i - 1][1]);
                f[i][2] = min(f[i - 1][2], f[i - 1][1]) + 1;
            }
        }
        return f[n - 1][2];
    }
};
```

#### Go

```go
func minimumOperations(leaves string) int {
	n := len(leaves)
	f := make([][3]int, n)
	inf := 1 << 30
	for i := range f {
		f[i] = [3]int{inf, inf, inf}
	}
	if leaves[0] == 'y' {
		f[0][0] = 1
	} else {
		f[0][0] = 0
	}
	for i := 1; i < n; i++ {
		if leaves[i] == 'r' {
			f[i][0] = f[i-1][0]
			f[i][1] = min(f[i-1][0], f[i-1][1]) + 1
			f[i][2] = min(f[i-1][2], f[i-1][1])
		} else {
			f[i][0] = f[i-1][0] + 1
			f[i][1] = min(f[i-1][0], f[i-1][1])
			f[i][2] = min(f[i-1][2], f[i-1][1]) + 1
		}
	}
	return f[n-1][2]
}
```

#### TypeScript

```ts
function minimumOperations(leaves: string): number {
    const n = leaves.length;
    const inf = 1 << 30;
    const f: number[][] = new Array(n).fill(0).map(() => new Array(3).fill(inf));
    f[0][0] = leaves[0] === 'y' ? 1 : 0;
    for (let i = 1; i < n; ++i) {
        if (leaves[i] === 'r') {
            f[i][0] = f[i - 1][0];
            f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + 1;
            f[i][2] = Math.min(f[i - 1][2], f[i - 1][1]);
        } else {
            f[i][0] = f[i - 1][0] + 1;
            f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]);
            f[i][2] = Math.min(f[i - 1][2], f[i - 1][1]) + 1;
        }
    }
    return f[n - 1][2];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
