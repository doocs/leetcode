---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2800-2899/2827.Number%20of%20Beautiful%20Integers%20in%20the%20Range/README.md
rating: 2324
source: 第 111 场双周赛 Q4
tags:
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [2827. 范围中美丽整数的数目](https://leetcode.cn/problems/number-of-beautiful-integers-in-the-range)

[English Version](/solution/2800-2899/2827.Number%20of%20Beautiful%20Integers%20in%20the%20Range/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你正整数&nbsp;<code>low</code>&nbsp;，<code>high</code>&nbsp;和&nbsp;<code>k</code>&nbsp;。</p>

<p>如果一个数满足以下两个条件，那么它是 <strong>美丽的</strong>&nbsp;：</p>

<ul>
	<li>偶数数位的数目与奇数数位的数目相同。</li>
	<li>这个整数可以被&nbsp;<code>k</code>&nbsp;整除。</li>
</ul>

<p>请你返回范围&nbsp;<code>[low, high]</code>&nbsp;中美丽整数的数目。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>low = 10, high = 20, k = 3
<b>输出：</b>2
<b>解释：</b>给定范围中有 2 个美丽数字：[12,18]
- 12 是美丽整数，因为它有 1 个奇数数位和 1 个偶数数位，而且可以被 k = 3 整除。
- 18 是美丽整数，因为它有 1 个奇数数位和 1 个偶数数位，而且可以被 k = 3 整除。
以下是一些不是美丽整数的例子：
- 16 不是美丽整数，因为它不能被 k = 3 整除。
- 15 不是美丽整数，因为它的奇数数位和偶数数位的数目不相等。
给定范围内总共有 2 个美丽整数。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>low = 1, high = 10, k = 1
<b>输出：</b>1
<b>解释：</b>给定范围中有 1 个美丽数字：[10]
- 10 是美丽整数，因为它有 1 个奇数数位和 1 个偶数数位，而且可以被 k = 1 整除。
给定范围内总共有 1 个美丽整数。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>low = 5, high = 5, k = 2
<b>输出：</b>0
<b>解释：</b>给定范围中有 0 个美丽数字。
- 5 不是美丽整数，因为它的奇数数位和偶数数位的数目不相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt; low &lt;= high &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt; k &lt;= 20</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数位 DP

我们注意到，题目求的是区间 $[low, high]$ 内的美丽整数的个数，对于这种区间 $[l,..r]$ 的问题，我们通常可以考虑转化为求 $[1, r]$ 和 $[1, l-1]$ 的答案，然后相减即可。另外，题目中只涉及到不同数位之间的关系，而不涉及具体的数值，因此我们可以考虑使用数位 DP 来解决。

我们设计一个函数 $dfs(pos, mod, diff, lead, limit)$，表示当前处理到第 $pos$ 位，当前数字模 $k$ 的结果为 $mod$，当前数字的奇偶数位差为 $diff$，当前数字是否有前导零为 $lead$，当前数字是否达到上界为 $limit$ 时的方案数。

函数 $dfs(pos, mod, diff, lead, limit)$ 的执行逻辑如下：

如果 $pos$ 超出了 $num$ 的长度，说明我们已经处理完了所有数位，如果此时 $mod=0$，并且 $diff=0$，说明当前数字满足题目要求，我们返回 $1$，否则返回 $0$。

否则，我们计算得到当前数位的上界 $up$，然后在 $[0,..up]$ 范围内枚举当前数位的数字 $i$：

-   如果 $i=0$ 且 $lead$ 为真，说明当前数字只包含前导零，我们递归计算 $dfs(pos + 1, mod, diff, 1, limit\ and\ i=up)$ 的值并累加到答案中；
-   否则，我们根据 $i$ 的奇偶性更新 $diff$ 的值，然后递归计算 $dfs(pos + 1, (mod \times 10 + i) \bmod k, diff, 0, limit\ and\ i=up)$ 的值并累加到答案中。

最终我们返回答案。

在主函数中，我们分别计算 $[1, high]$ 和 $[1, low-1]$ 的答案 $a$ 和 $b$，最终答案为 $a-b$。

时间复杂度 $O((\log M)^2 \times k \times |\Sigma|)$，空间复杂度 $O((\log M)^2 \times k \times)$，其中 $M$ 表示 $high$ 数字的大小，而 $|\Sigma|$ 表示数字集合。

相似题目：

-   [2719. 统计整数数目](https://github.com/doocs/leetcode/blob/main/solution/2700-2799/2719.Count%20of%20Integers/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def numberOfBeautifulIntegers(self, low: int, high: int, k: int) -> int:
        @cache
        def dfs(pos: int, mod: int, diff: int, lead: int, limit: int) -> int:
            if pos >= len(s):
                return mod == 0 and diff == 10
            up = int(s[pos]) if limit else 9
            ans = 0
            for i in range(up + 1):
                if i == 0 and lead:
                    ans += dfs(pos + 1, mod, diff, 1, limit and i == up)
                else:
                    nxt = diff + (1 if i % 2 == 1 else -1)
                    ans += dfs(pos + 1, (mod * 10 + i) % k, nxt, 0, limit and i == up)
            return ans

        s = str(high)
        a = dfs(0, 0, 10, 1, 1)
        dfs.cache_clear()
        s = str(low - 1)
        b = dfs(0, 0, 10, 1, 1)
        return a - b
```

#### Java

```java
class Solution {
    private String s;
    private int k;
    private Integer[][][] f = new Integer[11][21][21];

    public int numberOfBeautifulIntegers(int low, int high, int k) {
        this.k = k;
        s = String.valueOf(high);
        int a = dfs(0, 0, 10, true, true);
        s = String.valueOf(low - 1);
        f = new Integer[11][21][21];
        int b = dfs(0, 0, 10, true, true);
        return a - b;
    }

    private int dfs(int pos, int mod, int diff, boolean lead, boolean limit) {
        if (pos >= s.length()) {
            return mod == 0 && diff == 10 ? 1 : 0;
        }
        if (!lead && !limit && f[pos][mod][diff] != null) {
            return f[pos][mod][diff];
        }
        int ans = 0;
        int up = limit ? s.charAt(pos) - '0' : 9;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead) {
                ans += dfs(pos + 1, mod, diff, true, limit && i == up);
            } else {
                int nxt = diff + (i % 2 == 1 ? 1 : -1);
                ans += dfs(pos + 1, (mod * 10 + i) % k, nxt, false, limit && i == up);
            }
        }
        if (!lead && !limit) {
            f[pos][mod][diff] = ans;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int numberOfBeautifulIntegers(int low, int high, int k) {
        int f[11][21][21];
        memset(f, -1, sizeof(f));
        string s = to_string(high);

        function<int(int, int, int, bool, bool)> dfs = [&](int pos, int mod, int diff, bool lead, bool limit) {
            if (pos >= s.size()) {
                return mod == 0 && diff == 10 ? 1 : 0;
            }
            if (!lead && !limit && f[pos][mod][diff] != -1) {
                return f[pos][mod][diff];
            }
            int ans = 0;
            int up = limit ? s[pos] - '0' : 9;
            for (int i = 0; i <= up; ++i) {
                if (i == 0 && lead) {
                    ans += dfs(pos + 1, mod, diff, true, limit && i == up);
                } else {
                    int nxt = diff + (i % 2 == 1 ? 1 : -1);
                    ans += dfs(pos + 1, (mod * 10 + i) % k, nxt, false, limit && i == up);
                }
            }
            if (!lead && !limit) {
                f[pos][mod][diff] = ans;
            }
            return ans;
        };

        int a = dfs(0, 0, 10, true, true);
        memset(f, -1, sizeof(f));
        s = to_string(low - 1);
        int b = dfs(0, 0, 10, true, true);
        return a - b;
    }
};
```

#### Go

```go
func numberOfBeautifulIntegers(low int, high int, k int) int {
	s := strconv.Itoa(high)
	f := g(len(s), k, 21)

	var dfs func(pos, mod, diff int, lead, limit bool) int
	dfs = func(pos, mod, diff int, lead, limit bool) int {
		if pos >= len(s) {
			if mod == 0 && diff == 10 {
				return 1
			}
			return 0
		}
		if !lead && !limit && f[pos][mod][diff] != -1 {
			return f[pos][mod][diff]
		}
		up := 9
		if limit {
			up = int(s[pos] - '0')
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if i == 0 && lead {
				ans += dfs(pos+1, mod, diff, true, limit && i == up)
			} else {
				nxt := diff + 1
				if i%2 == 0 {
					nxt -= 2
				}
				ans += dfs(pos+1, (mod*10+i)%k, nxt, false, limit && i == up)
			}
		}
		if !lead && !limit {
			f[pos][mod][diff] = ans
		}
		return ans
	}

	a := dfs(0, 0, 10, true, true)
	s = strconv.Itoa(low - 1)
	f = g(len(s), k, 21)
	b := dfs(0, 0, 10, true, true)
	return a - b
}

func g(m, n, k int) [][][]int {
	f := make([][][]int, m)
	for i := 0; i < m; i++ {
		f[i] = make([][]int, n)
		for j := 0; j < n; j++ {
			f[i][j] = make([]int, k)
			for d := 0; d < k; d++ {
				f[i][j][d] = -1
			}
		}
	}
	return f
}
```

#### TypeScript

```ts
function numberOfBeautifulIntegers(low: number, high: number, k: number): number {
    let s = String(high);
    let f: number[][][] = Array(11)
        .fill(0)
        .map(() =>
            Array(21)
                .fill(0)
                .map(() => Array(21).fill(-1)),
        );
    const dfs = (pos: number, mod: number, diff: number, lead: boolean, limit: boolean): number => {
        if (pos >= s.length) {
            return mod == 0 && diff == 10 ? 1 : 0;
        }
        if (!lead && !limit && f[pos][mod][diff] != -1) {
            return f[pos][mod][diff];
        }
        let ans = 0;
        const up = limit ? Number(s[pos]) : 9;
        for (let i = 0; i <= up; ++i) {
            if (i === 0 && lead) {
                ans += dfs(pos + 1, mod, diff, true, limit && i === up);
            } else {
                const nxt = diff + (i % 2 ? 1 : -1);
                ans += dfs(pos + 1, (mod * 10 + i) % k, nxt, false, limit && i === up);
            }
        }
        if (!lead && !limit) {
            f[pos][mod][diff] = ans;
        }
        return ans;
    };
    const a = dfs(0, 0, 10, true, true);
    s = String(low - 1);
    f = Array(11)
        .fill(0)
        .map(() =>
            Array(21)
                .fill(0)
                .map(() => Array(21).fill(-1)),
        );
    const b = dfs(0, 0, 10, true, true);
    return a - b;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
