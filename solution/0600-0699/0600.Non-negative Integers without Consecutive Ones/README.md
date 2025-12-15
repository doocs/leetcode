---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md
tags:
    - 动态规划
---

<!-- problem:start -->

# [600. 不含连续1的非负整数](https://leetcode.cn/problems/non-negative-integers-without-consecutive-ones)

[English Version](/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个正整数 <code>n</code> ，请你统计在&nbsp;<code>[0, n]</code> 范围的非负整数中，有多少个整数的二进制表示中不存在 <strong>连续的 1 </strong>。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> n = 5
<strong>输出:</strong> 5
<strong>解释:</strong> 
下面列出范围在 [0, 5] 的非负整数与其对应的二进制表示：
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
其中，只有整数 3 违反规则（有两个连续的 1 ），其他 5 个满足规则。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> n = 1
<strong>输出:</strong> 2
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> n = 2
<strong>输出:</strong> 3
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数位 DP

这道题实际上是求在给定区间 $[l,..r]$ 中，数字的二进制表示不包含连续的 $1$ 的个数。个数与数的位数以及每个二进制位上的数字有关。我们可以用数位 DP 的思路来解决这道题。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[0,..r]$ 然后再减去 $[0,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=0}^{r} ans_i -  \sum_{i=0}^{l-1} ans_i
$$

不过对于本题而言，我们只需要求出区间 $[0,..r]$ 的值即可。

这里我们用记忆化搜索来实现数位 DP。基本步骤如下：

我们首先获取数字 $n$ 的二进制长度，记为 $m$。然后根据题目信息，我们设计函数 $\textit{dfs}(i, \textit{pre}, \textit{limit})$，其中：

- 数字 $i$ 表示当前搜索到的位置，我们从数字的最高位开始搜索，即二进制字符串的首字符；
- 数字 $\textit{pre}$ 表示上一个数字二进制位上的数字，对于本题，$\textit{pre}$ 的初始值为 $0$；
- 布尔值 $\textit{limit}$ 表示可填的数字的限制，如果无限制，那么可以选择 $[0,1]$，否则，只能选择 $[0, \textit{up}]$。

函数的执行过程如下：

如果 $i$ 超过了数字 $n$ 的长度，即 $i \lt 0$，说明搜索结束，直接返回 $1$。否则，我们从 $0$ 到 $\textit{up}$ 枚举位置 $i$ 的数字 $j$，对于每一个 $j$：

- 如果 $\textit{pre}$ 和 $j$ 都为 $1$，说明有连续的 $1$，直接跳过；
- 否则，我们递归到下一层，更新 $\textit{pre}$ 为 $j$，并将 $\textit{limit}$ 更新为 $\textit{limit}$ 与 $j$ 是否等于 $\textit{up}$ 的逻辑与。

最后，我们将所有递归到下一层的结果累加，即为答案。

时间复杂度 $O(\log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为题目给定的正整数。

相似题目：

- [233. 数字 1 的个数](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)
- [357. 统计各位数字都不同的数字个数](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
- [788. 旋转数字](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0788.Rotated%20Digits/README.md)
- [902. 最大为 N 的数字组合](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
- [1012. 至少有 1 位重复的数字](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md)
- [2376. 统计特殊整数](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findIntegers(self, n: int) -> int:
        @cache
        def dfs(i: int, pre: int, limit: bool) -> int:
            if i < 0:
                return 1
            up = (n >> i & 1) if limit else 1
            ans = 0
            for j in range(up + 1):
                if pre and j:
                    continue
                ans += dfs(i - 1, j, limit and j == up)
            return ans

        return dfs(n.bit_length() - 1, 0, True)
```

#### Java

```java
class Solution {
    private int n;
    private Integer[][] f;

    public int findIntegers(int n) {
        this.n = n;
        int m = Integer.SIZE - Integer.numberOfLeadingZeros(n);
        f = new Integer[m][2];
        return dfs(m - 1, 0, true);
    }

    private int dfs(int i, int pre, boolean limit) {
        if (i < 0) {
            return 1;
        }
        if (!limit && f[i][pre] != null) {
            return f[i][pre];
        }
        int up = limit ? (n >> i & 1) : 1;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if (j == 1 && pre == 1) {
                continue;
            }
            ans += dfs(i - 1, j, limit && j == up);
        }
        if (!limit) {
            f[i][pre] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int findIntegers(int n) {
        int m = 32 - __builtin_clz(n);
        int f[m][2];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int pre, bool limit) -> int {
            if (i < 0) {
                return 1;
            }
            if (!limit && f[i][pre] != -1) {
                return f[i][pre];
            }
            int up = limit ? (n >> i & 1) : 1;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (j && pre) {
                    continue;
                }
                ans += dfs(i - 1, j, limit && j == up);
            }
            if (!limit) {
                f[i][pre] = ans;
            }
            return ans;
        };
        return dfs(m - 1, 0, true);
    }
};
```

#### Go

```go
func findIntegers(n int) int {
	m := bits.Len(uint(n))
	f := make([][2]int, m)
	for i := range f {
		f[i] = [2]int{-1, -1}
	}
	var dfs func(i, pre int, limit bool) int
	dfs = func(i, pre int, limit bool) int {
		if i < 0 {
			return 1
		}
		if !limit && f[i][pre] != -1 {
			return f[i][pre]
		}
		up := 1
		if limit {
			up = n >> i & 1
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if j == 1 && pre == 1 {
				continue
			}
			ans += dfs(i-1, j, limit && j == up)
		}
		if !limit {
			f[i][pre] = ans
		}
		return ans
	}
	return dfs(m-1, 0, true)
}
```

#### TypeScript

```ts
function findIntegers(n: number): number {
    const m = n.toString(2).length;
    const f: number[][] = Array.from({ length: m }, () => Array(2).fill(-1));
    const dfs = (i: number, pre: number, limit: boolean): number => {
        if (i < 0) {
            return 1;
        }
        if (!limit && f[i][pre] !== -1) {
            return f[i][pre];
        }
        const up = limit ? (n >> i) & 1 : 1;
        let ans = 0;
        for (let j = 0; j <= up; ++j) {
            if (pre === 1 && j === 1) {
                continue;
            }
            ans += dfs(i - 1, j, limit && j === up);
        }
        if (!limit) {
            f[i][pre] = ans;
        }
        return ans;
    };
    return dfs(m - 1, 0, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
