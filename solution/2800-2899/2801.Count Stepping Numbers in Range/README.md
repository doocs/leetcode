# [2801. 统计范围内的步进数字数目](https://leetcode.cn/problems/count-stepping-numbers-in-range)

[English Version](/solution/2800-2899/2801.Count%20Stepping%20Numbers%20in%20Range/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个正整数&nbsp;<code>low</code> 和&nbsp;<code>high</code>&nbsp;，都用字符串表示，请你统计闭区间 <code>[low, high]</code>&nbsp;内的 <strong>步进数字</strong>&nbsp;数目。</p>

<p>如果一个整数相邻数位之间差的绝对值都 <strong>恰好</strong>&nbsp;是 <code>1</code>&nbsp;，那么这个数字被称为 <strong>步进数字</strong>&nbsp;。</p>

<p>请你返回一个整数，表示闭区间&nbsp;<code>[low, high]</code>&nbsp;之间步进数字的数目。</p>

<p>由于答案可能很大，请你将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p><b>注意：</b>步进数字不能有前导 0 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>low = "1", high = "11"
<b>输出：</b>10
<strong>解释：</strong>区间 [1,11] 内的步进数字为 1 ，2 ，3 ，4 ，5 ，6 ，7 ，8 ，9 和 10 。总共有 10 个步进数字。所以输出为 10 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>low = "90", high = "101"
<b>输出：</b>2
<strong>解释：</strong>区间 [90,101] 内的步进数字为 98 和 101 。总共有 2 个步进数字。所以输出为 2 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= int(low) &lt;= int(high) &lt; 10<sup>100</sup></code></li>
	<li><code>1 &lt;= low.length, high.length &lt;= 100</code></li>
	<li><code>low</code> 和&nbsp;<code>high</code>&nbsp;只包含数字。</li>
	<li><code>low</code> 和&nbsp;<code>high</code>&nbsp;都不含前导 0 。</li>
</ul>

## 解法

### 方法一：数位 DP

我们注意到，题目求的是区间 $[low, high]$ 内的步进数的个数，对于这种区间 $[l,..r]$ 的问题，我们通常可以考虑转化为求 $[1, r]$ 和 $[1, l-1]$ 的答案，然后相减即可。另外，题目中只涉及到不同数位之间的关系，而不涉及具体的数值，因此我们可以考虑使用数位 DP 来解决。

我们设计一个函数 $dfs(pos, pre, lead, limit)$，表示当前处理到第 $pos$ 位，前一位数字是 $pre$，当前数字是否只包含前导零 $lead$，当前数字是否达到上界 $limit$ 的方案数。其中，而 $pos$ 的范围是 $[0, len(num))$。

函数 $dfs(pos, pre, lead, limit)$ 的执行逻辑如下：

如果 $pos$ 超出了 $num$ 的长度，说明我们已经处理完了所有数位，如果此时 $lead$ 为真，说明当前数字只包含前导零，不是一个合法的数字，我们可以返回 $0$ 表示方案数为 $0$；否则我们返回 $1$ 表示方案数为 $1$。

否则，我们计算得到当前数位的上界 $up$，然后在 $[0,..up]$ 范围内枚举当前数位的数字 $i$：

-   如果 $i=0$ 且 $lead$ 为真，说明当前数字只包含前导零，我们递归计算 $dfs(pos+1,pre, true, limit\ and\ i=up)$ 的值并累加到答案中；
-   否则，如果 $pre$ 为 $-1$，或者 $i$ 和 $pre$ 之间的差的绝对值为 $1$，说明当前数字是一个合法的步进数，我们递归计算 $dfs(pos+1,i, false, limit\ and\ i=up)$ 的值并累加到答案中。

最终我们返回答案。

在主函数中，我们分别计算 $[1, high]$ 和 $[1, low-1]$ 的答案 $a$ 和 $b$，最终答案为 $a-b$。注意答案的取模运算。

时间复杂度 $O(\log M \times |\Sigma|^2)$，空间复杂度 $O(\log M \times |\Sigma|)$，其中 $M$ 表示 $high$ 数字的大小，而 $|\Sigma|$ 表示数字集合。

相似题目：

-   [2719. 统计整数数目](https://github.com/doocs/leetcode/blob/main/solution/2700-2799/2719.Count%20of%20Integers/README.md)

<!-- tabs:start -->

```python
class Solution:
    def countSteppingNumbers(self, low: str, high: str) -> int:
        @cache
        def dfs(pos: int, pre: int, lead: bool, limit: bool) -> int:
            if pos >= len(num):
                return int(not lead)
            up = int(num[pos]) if limit else 9
            ans = 0
            for i in range(up + 1):
                if i == 0 and lead:
                    ans += dfs(pos + 1, pre, True, limit and i == up)
                elif pre == -1 or abs(i - pre) == 1:
                    ans += dfs(pos + 1, i, False, limit and i == up)
            return ans % mod

        mod = 10**9 + 7
        num = high
        a = dfs(0, -1, True, True)
        dfs.cache_clear()
        num = str(int(low) - 1)
        b = dfs(0, -1, True, True)
        return (a - b) % mod
```

```java
import java.math.BigInteger;

class Solution {
    private final int mod = (int) 1e9 + 7;
    private String num;
    private Integer[][] f;

    public int countSteppingNumbers(String low, String high) {
        f = new Integer[high.length() + 1][10];
        num = high;
        int a = dfs(0, -1, true, true);
        f = new Integer[high.length() + 1][10];
        num = new BigInteger(low).subtract(BigInteger.ONE).toString();
        int b = dfs(0, -1, true, true);
        return (a - b + mod) % mod;
    }

    private int dfs(int pos, int pre, boolean lead, boolean limit) {
        if (pos >= num.length()) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[pos][pre] != null) {
            return f[pos][pre];
        }
        int ans = 0;
        int up = limit ? num.charAt(pos) - '0' : 9;
        for (int i = 0; i <= up; ++i) {
            if (i == 0 && lead) {
                ans += dfs(pos + 1, pre, true, limit && i == up);
            } else if (pre == -1 || Math.abs(pre - i) == 1) {
                ans += dfs(pos + 1, i, false, limit && i == up);
            }
            ans %= mod;
        }
        if (!lead && !limit) {
            f[pos][pre] = ans;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int countSteppingNumbers(string low, string high) {
        const int mod = 1e9 + 7;
        int m = high.size();
        int f[m + 1][10];
        memset(f, -1, sizeof(f));
        string num = high;

        function<int(int, int, bool, bool)> dfs = [&](int pos, int pre, bool lead, bool limit) {
            if (pos >= num.size()) {
                return lead ? 0 : 1;
            }
            if (!lead && !limit && f[pos][pre] != -1) {
                return f[pos][pre];
            }
            int up = limit ? num[pos] - '0' : 9;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                if (i == 0 && lead) {
                    ans += dfs(pos + 1, pre, true, limit && i == up);
                } else if (pre == -1 || abs(pre - i) == 1) {
                    ans += dfs(pos + 1, i, false, limit && i == up);
                }
                ans %= mod;
            }
            if (!lead && !limit) {
                f[pos][pre] = ans;
            }
            return ans;
        };

        int a = dfs(0, -1, true, true);
        memset(f, -1, sizeof(f));
        for (int i = low.size() - 1; i >= 0; --i) {
            if (low[i] == '0') {
                low[i] = '9';
            } else {
                low[i] -= 1;
                break;
            }
        }
        num = low;
        int b = dfs(0, -1, true, true);
        return (a - b + mod) % mod;
    }
};
```

```go
func countSteppingNumbers(low string, high string) int {
	const mod = 1e9 + 7
	f := [110][10]int{}
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	num := high
	var dfs func(int, int, bool, bool) int
	dfs = func(pos, pre int, lead bool, limit bool) int {
		if pos >= len(num) {
			if lead {
				return 0
			}
			return 1
		}
		if !lead && !limit && f[pos][pre] != -1 {
			return f[pos][pre]
		}
		var ans int
		up := 9
		if limit {
			up = int(num[pos] - '0')
		}
		for i := 0; i <= up; i++ {
			if i == 0 && lead {
				ans += dfs(pos+1, pre, true, limit && i == up)
			} else if pre == -1 || abs(pre-i) == 1 {
				ans += dfs(pos+1, i, false, limit && i == up)
			}
			ans %= mod
		}
		if !lead && !limit {
			f[pos][pre] = ans
		}
		return ans
	}
	a := dfs(0, -1, true, true)
	t := []byte(low)
	for i := len(t) - 1; i >= 0; i-- {
		if t[i] != '0' {
			t[i]--
			break
		}
		t[i] = '9'
	}
	num = string(t)
	f = [110][10]int{}
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	b := dfs(0, -1, true, true)
	return (a - b + mod) % mod
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

```ts
function countSteppingNumbers(low: string, high: string): number {
    const mod = 1e9 + 7;
    const m = high.length;
    let f: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(10).fill(-1));
    let num = high;
    const dfs = (pos: number, pre: number, lead: boolean, limit: boolean): number => {
        if (pos >= num.length) {
            return lead ? 0 : 1;
        }
        if (!lead && !limit && f[pos][pre] !== -1) {
            return f[pos][pre];
        }
        let ans = 0;
        const up = limit ? +num[pos] : 9;
        for (let i = 0; i <= up; i++) {
            if (i == 0 && lead) {
                ans += dfs(pos + 1, pre, true, limit && i == up);
            } else if (pre == -1 || Math.abs(pre - i) == 1) {
                ans += dfs(pos + 1, i, false, limit && i == up);
            }
            ans %= mod;
        }
        if (!lead && !limit) {
            f[pos][pre] = ans;
        }
        return ans;
    };
    const a = dfs(0, -1, true, true);
    num = (BigInt(low) - 1n).toString();
    f = Array(m + 1)
        .fill(0)
        .map(() => Array(10).fill(-1));
    const b = dfs(0, -1, true, true);
    return (a - b + mod) % mod;
}
```

<!-- tabs:end -->

<!-- end -->
