---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md
rating: 2230
source: 第 128 场周赛 Q4
tags:
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [1012. 至少有 1 位重复的数字](https://leetcode.cn/problems/numbers-with-repeated-digits)

[English Version](/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定正整数&nbsp;<code>n</code>，返回在<em>&nbsp;</em><code>[1, n]</code><em>&nbsp;</em>范围内具有 <strong>至少 1 位</strong> 重复数字的正整数的个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 20
<strong>输出：</strong>1
<strong>解释：</strong>具有至少 1 位重复数字的正数（&lt;= 20）只有 11 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 100
<strong>输出：</strong>10
<strong>解释：</strong>具有至少 1 位重复数字的正数（&lt;= 100）有 11，22，33，44，55，66，77，88，99 和 100 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 1000
<strong>输出：</strong>262
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：状态压缩 + 数位 DP

题目要求统计 $[1,..n]$ 中至少有一位重复的数字的个数，我们可以换一种思路，用一个函数 $f(n)$ 统计 $[1,..n]$ 中没有重复数字的个数，那么答案就是 $n - f(n)$。

另外，我们可以用一个二进制数来记录数字中出现过的数字，比如数字中出现了 $1$, $2$, $4$，那么对应的二进制数就是 $\underline{1}0\underline{1}\underline{1}0$。

接下来，我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

基本步骤如下：

我们将数字 $n$ 转为字符串 $s$，接下来，我们设计一个函数 $\textit{dfs}(i, \textit{mask}, \textit{lead}, \textit{limit})$，其中：

- 数字 $i$ 表示当前处理的数字下标，从 $0$ 开始。
- 数字 $\textit{mask}$ 表示当前数字中出现过的数字，用二进制数表示。其中 $\textit{mask}$ 的二进制的第 $j$ 位为 $1$ 表示数字 $j$ 出现过，为 $0$ 表示数字 $j$ 没有出现过。
- 布尔值 $\textit{lead}$ 表示是否只包含前导零。
- 布尔值 $\textit{limit}$ 表示当前位置是否受到上界的限制。

函数的执行过程如下：

如果 $i$ 大于等于 $m$，说明我们已经处理完了所有的位数，此时如果 $\textit{lead}$ 为真，说明当前的数字是前导零，我们应当返回 $0$；否则，我们应当返回 $1$。

否则，我们计算当前位置的上界 $\textit{up}$，如果 $\textit{limit}$ 为真，则 $up$ 为 $s[i]$ 对应的数字，否则 $up$ 为 $9$。

然后，我们在 $[0, \textit{up}]$ 的范围内枚举当前位置的数字 $j$，如果 $j$ 为 $0$ 且 $\textit{lead}$ 为真，我们递归计算 $\textit{dfs}(i + 1, \textit{mask}, \text{true}, \textit{limit} \wedge j = \textit{up})$；否则，如果 $\textit{mask}$ 的第 $j$ 位为 $0$，我们递归计算 $\textit{dfs}(i + 1, \textit{mask} \,|\, 2^j, \text{false}, \textit{limit} \wedge j = \textit{up})$。累加所有的结果即为答案。

答案为 $n - \textit{dfs}(0, 0, \text{true}, \text{true})$。

时间复杂度 $O(\log n \times 2^D \times D)$，空间复杂度 $O(\log n \times 2^D)$。其中 $D = 10$。

相似题目：

- [233. 数字 1 的个数](https://github.com/doocs/leetcode/blob/main/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)
- [357. 统计各位数字都不同的数字个数](https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
- [600. 不含连续 1 的非负整数](https://github.com/doocs/leetcode/blob/main/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)
- [788. 旋转数字](https://github.com/doocs/leetcode/blob/main/solution/0700-0799/0788.Rotated%20Digits/README.md)
- [902. 最大为 N 的数字组合](https://github.com/doocs/leetcode/blob/main/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
- [2376. 统计特殊整数](https://github.com/doocs/leetcode/blob/main/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numDupDigitsAtMostN(self, n: int) -> int:
        @cache
        def dfs(i: int, mask: int, lead: bool, limit: bool) -> int:
            if i >= len(s):
                return lead ^ 1
            up = int(s[i]) if limit else 9
            ans = 0
            for j in range(up + 1):
                if lead and j == 0:
                    ans += dfs(i + 1, mask, True, False)
                elif mask >> j & 1 ^ 1:
                    ans += dfs(i + 1, mask | 1 << j, False, limit and j == up)
            return ans

        s = str(n)
        return n - dfs(0, 0, True, True)
```

#### Java

```java
class Solution {
    private char[] s;
    private Integer[][] f;

    public int numDupDigitsAtMostN(int n) {
        s = String.valueOf(n).toCharArray();
        f = new Integer[s.length][1 << 10];
        return n - dfs(0, 0, true, true);
    }

    private int dfs(int i, int mask, boolean lead, boolean limit) {
        if (i >= s.length) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[i][mask] != null) {
            return f[i][mask];
        }
        int up = limit ? s[i] - '0' : 9;
        int ans = 0;
        for (int j = 0; j <= up; ++j) {
            if (lead && j == 0) {
                ans += dfs(i + 1, mask, true, false);
            } else if ((mask >> j & 1) == 0) {
                ans += dfs(i + 1, mask | 1 << j, false, limit && j == up);
            }
        }
        if (!lead && !limit) {
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
    int numDupDigitsAtMostN(int n) {
        string s = to_string(n);
        int m = s.size();
        int f[m][1 << 10];
        memset(f, -1, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i, int mask, bool lead, bool limit) -> int {
            if (i >= m) {
                return lead ^ 1;
            }
            if (!lead && !limit && f[i][mask] != -1) {
                return f[i][mask];
            }
            int up = limit ? s[i] - '0' : 9;
            int ans = 0;
            for (int j = 0; j <= up; ++j) {
                if (lead && j == 0) {
                    ans += dfs(i + 1, mask, true, limit && j == up);
                } else if (mask >> j & 1 ^ 1) {
                    ans += dfs(i + 1, mask | (1 << j), false, limit && j == up);
                }
            }
            if (!lead && !limit) {
                f[i][mask] = ans;
            }
            return ans;
        };
        return n - dfs(0, 0, true, true);
    }
};
```

#### Go

```go
func numDupDigitsAtMostN(n int) int {
	s := []byte(strconv.Itoa(n))
	m := len(s)
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, 1<<10)
		for j := range f[i] {
			f[i][j] = -1
		}
	}

	var dfs func(i, mask int, lead, limit bool) int
	dfs = func(i, mask int, lead, limit bool) int {
		if i >= m {
			if lead {
				return 0
			}
			return 1
		}
		if !lead && !limit && f[i][mask] != -1 {
			return f[i][mask]
		}
		up := 9
		if limit {
			up = int(s[i] - '0')
		}
		ans := 0
		for j := 0; j <= up; j++ {
			if lead && j == 0 {
				ans += dfs(i+1, mask, true, limit && j == up)
			} else if mask>>j&1 == 0 {
				ans += dfs(i+1, mask|(1<<j), false, limit && j == up)
			}
		}
		if !lead && !limit {
			f[i][mask] = ans
		}
		return ans
	}
	return n - dfs(0, 0, true, true)
}
```

#### TypeScript

```ts
function numDupDigitsAtMostN(n: number): number {
    const s = n.toString();
    const m = s.length;
    const f = Array.from({ length: m }, () => Array(1 << 10).fill(-1));

    const dfs = (i: number, mask: number, lead: boolean, limit: boolean): number => {
        if (i >= m) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[i][mask] !== -1) {
            return f[i][mask];
        }
        const up = limit ? parseInt(s[i]) : 9;
        let ans = 0;
        for (let j = 0; j <= up; j++) {
            if (lead && j === 0) {
                ans += dfs(i + 1, mask, true, limit && j === up);
            } else if (((mask >> j) & 1) === 0) {
                ans += dfs(i + 1, mask | (1 << j), false, limit && j === up);
            }
        }
        if (!lead && !limit) {
            f[i][mask] = ans;
        }
        return ans;
    };

    return n - dfs(0, 0, true, true);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
