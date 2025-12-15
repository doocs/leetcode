---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2300-2399/2376.Count%20Special%20Integers/README.md
rating: 2120
source: 第 306 场周赛 Q4
tags:
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [2376. 统计特殊整数](https://leetcode.cn/problems/count-special-integers)

[English Version](/solution/2300-2399/2376.Count%20Special%20Integers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>如果一个正整数每一个数位都是 <strong>互不相同</strong>&nbsp;的，我们称它是 <strong>特殊整数</strong> 。</p>

<p>给你一个 <strong>正</strong>&nbsp;整数&nbsp;<code>n</code>&nbsp;，请你返回区间<em>&nbsp;</em><code>[1, n]</code>&nbsp;之间特殊整数的数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 20
<b>输出：</b>19
<b>解释：</b>1 到 20 之间所有整数除了 11 以外都是特殊整数。所以总共有 19 个特殊整数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 5
<b>输出：</b>5
<b>解释：</b>1 到 5 所有整数都是特殊整数。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>n = 135
<b>输出：</b>110
<b>解释：</b>从 1 到 135 总共有 110 个整数是特殊整数。
不特殊的部分数字为：22 ，114 和 131 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>9</sup></code></li>
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

不过对于本题而言，我们只需要求出区间 $[1,..n]$ 的值即可。

这里我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

我们根据题目信息，设计一个函数 $\textit{dfs}(i, \textit{mask}, \textit{lead}, \textit{limit})$，其中：

- 数字 $i$ 表示当前搜索到的位置，我们从高位开始搜索，即 $i = 0$ 表示最高位。
- 数字 $\textit{mask}$ 表示当前数字的状态，即 $\textit{mask}$ 的第 $j$ 位为 $1$ 表示数字 $j$ 已经被使用过。
- 布尔值 $\textit{lead}$ 表示当前是否只包含前导 $0$。
- 布尔值 $\textit{limit}$ 表示当前是否受到上界的限制。

函数的执行过程如下：

如果 $i$ 超过了数字 $n$ 的长度，说明搜索结束，如果此时 $\textit{lead}$ 为真，说明当前数字只包含前导 $0$，直接返回 $0$，否则返回 $1$。

如果 $\textit{limit}$ 为假且 $\textit{lead}$ 为假且 $\textit{mask}$ 的状态已经被记忆化，直接返回记忆化的结果。

否则，我们计算当前数字的上界 $up$，如果 $\textit{limit}$ 为真，$up$ 为当前数字的第 $i$ 位，否则 $up = 9$。

然后我们遍历 $[0, up]$，对于每个数字 $j$，如果 $\textit{mask}$ 的第 $j$ 位为 $1$，说明数字 $j$ 已经被使用过，直接跳过。否则，如果 $\textit{lead}$ 为真且 $j = 0$，说明当前数字只包含前导 $0$，递归搜索下一位，否则递归搜索下一位并更新 $\textit{mask}$ 的状态。

最后，如果 $\textit{limit}$ 为假且 $\textit{lead}$ 为假，将当前状态记忆化。

最终返回答案。

时间复杂度 $O(m \times 2^D \times D)$，空间复杂度 $O(m \times 2^D)$。其中 $m$ 为数字 $n$ 的长度，而 $D = 10$。

相似题目：

- [233. 数字 1 的个数](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)
- [357. 统计各位数字都不同的数字个数](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
- [600. 不含连续 1 的非负整数](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)
- [788. 旋转数字](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0788.Rotated%20Digits/README.md)
- [902. 最大为 N 的数字组合](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
- [1012. 至少有 1 位重复的数字](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSpecialNumbers(self, n: int) -> int:
        @cache
        def dfs(i: int, mask: int, lead: bool, limit: bool) -> int:
            if i >= len(s):
                return int(lead ^ 1)
            up = int(s[i]) if limit else 9
            ans = 0
            for j in range(up + 1):
                if mask >> j & 1:
                    continue
                if lead and j == 0:
                    ans += dfs(i + 1, mask, True, limit and j == up)
                else:
                    ans += dfs(i + 1, mask | 1 << j, False, limit and j == up)
            return ans

        s = str(n)
        return dfs(0, 0, True, True)
```

#### Java

```java
class Solution {
    private char[] s;
    private Integer[][] f;

    public int countSpecialNumbers(int n) {
        s = String.valueOf(n).toCharArray();
        f = new Integer[s.length][1 << 10];
        return dfs(0, 0, true, true);
    }

    private int dfs(int i, int mask, boolean lead, boolean limit) {
        if (i >= s.length) {
            return lead ? 0 : 1;
        }
        if (!limit && !lead && f[i][mask] != null) {
            return f[i][mask];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if ((mask >> j & 1) == 1) {
                continue;
            }
            if (lead && j == 0) {
                ans += dfs(i + 1, mask, true, limit && j == up);
            } else {
                ans += dfs(i + 1, mask | (1 << j), false, limit && j == up);
            }
        }
        if (!limit && !lead) {
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
    int countSpecialNumbers(int n) {
        string s = to_string(n);
        int m = s.size();
        int f[m][1 << 10];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int mask, bool lead, bool limit) -> int {
            if (i >= m) {
                return lead ^ 1;
            }
            if (!limit && !lead && f[i][mask] != -1) {
                return f[i][mask];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (mask >> j & 1) {
                    continue;
                }
                if (lead && j == 0) {
                    ans += dfs(i + 1, mask, true, limit && j == up);
                } else {
                    ans += dfs(i + 1, mask | (1 << j), false, limit && j == up);
                }
            }
            if (!limit && !lead) {
                f[i][mask] = ans;
            }
            return ans;
        };
        return dfs(0, 0, true, true);
    }
};
```

#### Go

```go
func countSpecialNumbers(n int) int {
	s := strconv.Itoa(n)
	m := len(s)
	f := make([][1 << 10]int, m+1)
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int, bool, bool) int
	dfs = func(i, mask int, lead, limit bool) int {
		if i >= m {
			if lead {
				return 0
			}
			return 1
		}
		if !limit && !lead && f[i][mask] != -1 {
			return f[i][mask]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if mask>>j&1 == 1 {
				continue
			}
			if lead && j == 0 {
				ans += dfs(i+1, mask, true, limit && j == up)
			} else {
				ans += dfs(i+1, mask|1<<j, false, limit && j == up)
			}
		}
		if !limit && !lead {
			f[i][mask] = ans
		}
		return ans
	}
	return dfs(0, 0, true, true)
}
```

#### TypeScript

```ts
function countSpecialNumbers(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f: number[][] = Array.from({ length: m }, () => Array(1 << 10).fill(-1));
    const dfs = (i: number, mask: number, lead: boolean, limit: boolean): number => {
        if (i >= m) {
            return lead ? 0 : 1;
        }
        if (!limit && !lead && f[i][mask] !== -1) {
            return f[i][mask];
        }
        const up = limit ? +s[i] : 9;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            if ((mask >> j) & 1) {
                continue;
            }
            if (lead && j === 0) {
                ans += dfs(i + 1, mask, true, limit && j === up);
            } else {
                ans += dfs(i + 1, mask | (1 << j), false, limit && j === up);
            }
        }
        if (!limit && !lead) {
            f[i][mask] = ans;
        }
        return ans;
    };
    return dfs(0, 0, true, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
