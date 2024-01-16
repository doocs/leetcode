# [2719. 统计整数数目](https://leetcode.cn/problems/count-of-integers)

[English Version](/solution/2700-2799/2719.Count%20of%20Integers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个数字字符串&nbsp;<code>num1</code>&nbsp;和&nbsp;<code>num2</code>&nbsp;，以及两个整数&nbsp;<code>max_sum</code> 和&nbsp;<code>min_sum</code>&nbsp;。如果一个整数&nbsp;<code>x</code>&nbsp;满足以下条件，我们称它是一个好整数：</p>

<ul>
	<li><code>num1 &lt;= x &lt;= num2</code></li>
	<li><code>min_sum &lt;= digit_sum(x) &lt;= max_sum</code>.</li>
</ul>

<p>请你返回好整数的数目。答案可能很大，请返回答案对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;取余后的结果。</p>

<p>注意，<code>digit_sum(x)</code>&nbsp;表示&nbsp;<code>x</code>&nbsp;各位数字之和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>num1 = "1", num2 = "12", min_num = 1, max_num = 8
<b>输出：</b>11
<b>解释：</b>总共有 11 个整数的数位和在 1 到 8 之间，分别是 1,2,3,4,5,6,7,8,10,11 和 12 。所以我们返回 11 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>num1 = "1", num2 = "5", min_num = 1, max_num = 5
<b>输出：</b>5
<b>解释：</b>数位和在 1 到 5 之间的 5 个整数分别为 1,2,3,4 和 5 。所以我们返回 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num1 &lt;= num2 &lt;= 10<sup>22</sup></code></li>
	<li><code>1 &lt;= min_sum &lt;= max_sum &lt;= 400</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数位 DP**

题目实际上求的是区间 $[num1,..num2]$ 中，数位和在 $[min\_sum,..max\_sum]$ 的数的个数。对于这种区间 $[l,..r]$ 的问题，我们可以考虑转化为求 $[1,..r]$ 和 $[1,..l-1]$ 的答案，然后相减即可。

对于 $[1,..r]$ 的答案，我们可以使用数位 DP 来求解。我们设计一个函数 $dfs(pos, s, limit)$ 表示当前处理到第 $pos$ 位，数位和为 $s$，当前数是否有上界限制 $limit$ 的方案数。其中 $pos$ 从高到低枚举。

对于 $dfs(pos, s, limit)$，我们可以枚举当前数位 $i$ 的值，然后递归计算 $dfs(pos+1, s+i, limit \bigcap  i==up)$，其中 $up$ 表示当前数位的上界。如果 $limit$ 为真，那么 $up$ 就是当前数位的上界，否则 $up$ 为 $9$。如果 $pos$ 大于等于 $num$ 的长度，那么我们就可以判断 $s$ 是否在 $[min\_sum,..max\_sum]$ 的范围内，如果在就返回 $1$，否则返回 $0$。

时间复杂度 $O(10 \times n \times max\_sum)$，空间复杂度 $O(n \times max\_sum)$。其中 $n$ 表示 $num$ 的长度。

相似题目：

-   [2801. 统计范围内的步进数字数目](/solution/2800-2899/2801.Count%20Stepping%20Numbers%20in%20Range/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def count(self, num1: str, num2: str, min_sum: int, max_sum: int) -> int:
        @cache
        def dfs(pos: int, s: int, limit: bool) -> int:
            if pos >= len(num):
                return int(min_sum <= s <= max_sum)
            up = int(num[pos]) if limit else 9
            return (
                sum(dfs(pos + 1, s + i, limit and i == up) for i in range(up + 1)) % mod
            )

        mod = 10**9 + 7
        num = num2
        a = dfs(0, 0, True)
        dfs.cache_clear()
        num = str(int(num1) - 1)
        b = dfs(0, 0, True)
        return (a - b) % mod
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
import java.math.BigInteger;

class Solution {
    private final int mod = (int) 1e9 + 7;
    private Integer[][] f;
    private String num;
    private int min;
    private int max;

    public int count(String num1, String num2, int min_sum, int max_sum) {
        min = min_sum;
        max = max_sum;
        num = num2;
        f = new Integer[23][220];
        int a = dfs(0, 0, true);
        num = new BigInteger(num1).subtract(BigInteger.ONE).toString();
        f = new Integer[23][220];
        int b = dfs(0, 0, true);
        return (a - b + mod) % mod;
    }

    private int dfs(int pos, int s, boolean limit) {
        if (pos >= num.length()) {
            return s >= min && s <= max ? 1 : 0;
        }
        if (!limit && f[pos][s] != null) {
            return f[pos][s];
        }
        int ans = 0;
        int up = limit ? num.charAt(pos) - '0' : 9;
        for (int i = 0; i <= up; ++i) {
            ans = (ans + dfs(pos + 1, s + i, limit && i == up)) % mod;
        }
        if (!limit) {
            f[pos][s] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int count(string num1, string num2, int min_sum, int max_sum) {
        const int mod = 1e9 + 7;
        int f[23][220];
        memset(f, -1, sizeof(f));
        string num = num2;

        function<int(int, int, bool)> dfs = [&](int pos, int s, bool limit) -> int {
            if (pos >= num.size()) {
                return s >= min_sum && s <= max_sum ? 1 : 0;
            }
            if (!limit && f[pos][s] != -1) {
                return f[pos][s];
            }
            int up = limit ? num[pos] - '0' : 9;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                ans += dfs(pos + 1, s + i, limit && i == up);
                ans %= mod;
            }
            if (!limit) {
                f[pos][s] = ans;
            }
            return ans;
        };

        int a = dfs(0, 0, true);
        for (int i = num1.size() - 1; ~i; --i) {
            if (num1[i] == '0') {
                num1[i] = '9';
            } else {
                num1[i] -= 1;
                break;
            }
        }
        num = num1;
        memset(f, -1, sizeof(f));
        int b = dfs(0, 0, true);
        return (a - b + mod) % mod;
    }
};
```

### **Go**

```go
func count(num1 string, num2 string, min_sum int, max_sum int) int {
	const mod = 1e9 + 7
	f := [23][220]int{}
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	num := num2
	var dfs func(int, int, bool) int
	dfs = func(pos, s int, limit bool) int {
		if pos >= len(num) {
			if s >= min_sum && s <= max_sum {
				return 1
			}
			return 0
		}
		if !limit && f[pos][s] != -1 {
			return f[pos][s]
		}
		var ans int
		up := 9
		if limit {
			up = int(num[pos] - '0')
		}
		for i := 0; i <= up; i++ {
			ans = (ans + dfs(pos+1, s+i, limit && i == up)) % mod
		}
		if !limit {
			f[pos][s] = ans
		}
		return ans
	}
	a := dfs(0, 0, true)
	t := []byte(num1)
	for i := len(t) - 1; i >= 0; i-- {
		if t[i] != '0' {
			t[i]--
			break
		}
		t[i] = '9'
	}
	num = string(t)
	f = [23][220]int{}
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	b := dfs(0, 0, true)
	return (a - b + mod) % mod
}
```

### **TypeScript**

```ts
function count(num1: string, num2: string, min_sum: number, max_sum: number): number {
    const mod = 1e9 + 7;
    const f: number[][] = Array.from({ length: 23 }, () => Array(220).fill(-1));
    let num = num2;
    const dfs = (pos: number, s: number, limit: boolean): number => {
        if (pos >= num.length) {
            return s >= min_sum && s <= max_sum ? 1 : 0;
        }
        if (!limit && f[pos][s] !== -1) {
            return f[pos][s];
        }
        let ans = 0;
        const up = limit ? +num[pos] : 9;
        for (let i = 0; i <= up; i++) {
            ans = (ans + dfs(pos + 1, s + i, limit && i === up)) % mod;
        }
        if (!limit) {
            f[pos][s] = ans;
        }
        return ans;
    };
    const a = dfs(0, 0, true);
    num = (BigInt(num1) - 1n).toString();
    f.forEach(v => v.fill(-1));
    const b = dfs(0, 0, true);
    return (a - b + mod) % mod;
}
```

### **...**

```

```

<!-- tabs:end -->
