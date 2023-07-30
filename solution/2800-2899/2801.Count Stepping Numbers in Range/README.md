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

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

```ts
function countSteppingNumbers(low: string, high: string): number {
    const mod = 1e9 + 7;
    const m = high.length;
    let f: number[][] = Array(m + 1)
        .fill(0)
        .map(() => Array(10).fill(-1));
    let num = high;
    const dfs = (
        pos: number,
        pre: number,
        lead: boolean,
        limit: boolean,
    ): number => {
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

### **...**

```

```

<!-- tabs:end -->
