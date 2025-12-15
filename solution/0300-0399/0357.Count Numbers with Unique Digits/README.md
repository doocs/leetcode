---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md
tags:
    - 数学
    - 动态规划
    - 回溯
---

<!-- problem:start -->

# [357. 统计各位数字都不同的数字个数](https://leetcode.cn/problems/count-numbers-with-unique-digits)

[English Version](/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README_EN.md)

## 题目描述

<!-- description:start -->

给你一个整数 <code>n</code> ，统计并返回各位数字都不同的数字 <code>x</code> 的个数，其中 <code>0 &lt;= x &lt; 10<sup>n</sup></code><sup>&nbsp;</sup>。

<div class="original__bRMd">
<div>
<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>91
<strong>解释：</strong>答案应为除去 <code>11、22、33、44、55、66、77、88、99 </code>外，在 0 ≤ x &lt; 100 范围内的所有数字。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 0
<strong>输出：</strong>1
</pre>
</div>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 8</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：状态压缩 + 数位 DP

这道题实际上是求在给定区间 $[l,..r]$ 中，满足条件的数的个数。条件与数的大小无关，而只与数的组成有关，因此可以使用数位 DP 的思想求解。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[1,..r]$ 然后再减去 $[1,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

不过对于本题而言，我们只需要求出区间 $[1,..10^n-1]$ 的值即可。

这里我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

我们根据题目信息，设计一个函数 $\textit{dfs}(i, \textit{mask}, \textit{lead})$，其中：

- 数字 $i$ 表示当前搜索到的位置，我们从高位开始搜索，即 $i = 0$ 表示最高位。
- 数字 $\textit{mask}$ 表示当前数字的状态，即 $\textit{mask}$ 的第 $j$ 位为 $1$ 表示数字 $j$ 已经被使用过。
- 布尔值 $\textit{lead}$ 表示当前是否只包含前导 $0$。

函数的执行过程如下：

如果 $i$ 超过了数字 $n$ 的长度，即 $i \lt 0$，说明搜索结束，直接返回 $1$。

否则，我们从 $0$ 到 $9$ 枚举位置 $i$ 的数字 $j$，对于每一个 $j$：

- 如果 $\textit{mask}$ 的第 $j$ 位为 $1$，说明数字 $j$ 已经被使用过，直接跳过。
- 如果 $\textit{lead}$ 为真且 $j = 0$，说明当前数字只包含前导 $0$，递归到下一层时，此时 $\textit{lead}$ 仍为真。
- 否则，我们递归到下一层，更新 $\textit{mask}$ 的第 $j$ 位为 $1$，并将 $\textit{lead}$ 更新为假。

最后，我们将所有递归到下一层的结果累加，即为答案。

答案为 $\textit{dfs}(n - 1, 0, \textit{True})$。

关于函数的实现细节，可以参考下面的代码。

时间复杂度 $O(n \times 2^D \times D)$，空间复杂度 $O(n \times 2^D)$。其中 $n$ 为数字 $n$ 的长度，而 $D = 10$。

相似题目：

- [233. 数字 1 的个数](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)
- [600. 不含连续 1 的非负整数](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)
- [788. 旋转数字](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0788.Rotated%20Digits/README.md)
- [902. 最大为 N 的数字组合](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
- [1012. 至少有 1 位重复的数字](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md)
- [2376. 统计特殊整数](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countNumbersWithUniqueDigits(self, n: int) -> int:
        @cache
        def dfs(i: int, mask: int, lead: bool) -> int:
            if i < 0:
                return 1
            ans = 0
            for j in range(10):
                if mask >> j & 1:
                    continue
                if lead and j == 0:
                    ans += dfs(i - 1, mask, True)
                else:
                    ans += dfs(i - 1, mask | 1 << j, False)
            return ans

        return dfs(n - 1, 0, True)
```

#### Java

```java
class Solution {
    private Integer[][] f;

    public int countNumbersWithUniqueDigits(int n) {
        f = new Integer[n][1 << 10];
        return dfs(n - 1, 0, true);
    }

    private int dfs(int i, int mask, boolean lead) {
        if (i < 0) {
            return 1;
        }
        if (!lead && f[i][mask] != null) {
            return f[i][mask];
        }
        int ans = 0;
        for (int j = 0; j <= 9; ++j) {
            if ((mask >> j & 1) == 1) {
                continue;
            }
            if (lead && j == 0) {
                ans += dfs(i - 1, mask, true);
            } else {
                ans += dfs(i - 1, mask | 1 << j, false);
            }
        }
        if (!lead) {
            f[i][mask] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countNumbersWithUniqueDigits(int n) {
        int f[n + 1][1 << 10];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int mask, bool lead) -> int {
            if (i < 0) {
                return 1;
            }
            if (!lead && f[i][mask] != -1) {
                return f[i][mask];
            }
            int ans = 0;
            for (int j = 0; j <= 9; ++j) {
                if (mask >> j & 1) {
                    continue;
                }
                if (lead && j == 0) {
                    ans += dfs(i - 1, mask, true);
                } else {
                    ans += dfs(i - 1, mask | 1 << i, false);
                }
            }
            if (!lead) {
                f[i][mask] = ans;
            }
            return ans;
        };
        return dfs(n - 1, 0, true);
    }
};
```

#### Go

```go
func countNumbersWithUniqueDigits(n int) int {
	f := make([][1 << 10]int, n)
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, mask int, lead bool) int
	dfs = func(i, mask int, lead bool) int {
		if i < 0 {
			return 1
		}
		if !lead && f[i][mask] != -1 {
			return f[i][mask]
		}
		ans := 0
		for j := 0; j < 10; j++ {
			if mask>>j&1 == 1 {
				continue
			}
			if lead && j == 0 {
				ans += dfs(i-1, mask, true)
			} else {
				ans += dfs(i-1, mask|1<<j, false)
			}
		}
		if !lead {
			f[i][mask] = ans
		}
		return ans
	}
	return dfs(n-1, 0, true)
}
```

#### TypeScript

```ts
function countNumbersWithUniqueDigits(n: number): number {
    const f: number[][] = Array.from({ length: n }, () => Array(1 << 10).fill(-1));
    const dfs = (i: number, mask: number, lead: boolean): number => {
        if (i < 0) {
            return 1;
        }
        if (!lead && f[i][mask] !== -1) {
            return f[i][mask];
        }
        let ans = 0;
        for (let j = 0; j < 10; ++j) {
            if ((mask >> j) & 1) {
                continue;
            }
            if (lead && j === 0) {
                ans += dfs(i - 1, mask, true);
            } else {
                ans += dfs(i - 1, mask | (1 << j), false);
            }
        }
        if (!lead) {
            f[i][mask] = ans;
        }
        return ans;
    };
    return dfs(n - 1, 0, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
