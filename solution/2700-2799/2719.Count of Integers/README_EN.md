# [2719. Count of Integers](https://leetcode.com/problems/count-of-integers)

[中文文档](/solution/2700-2799/2719.Count%20of%20Integers/README.md)

<!-- tags:Math,String,Dynamic Programming -->

## Description

<p>You are given two numeric strings <code>num1</code> and <code>num2</code> and two integers <code>max_sum</code> and <code>min_sum</code>. We denote an integer <code>x</code> to be <em>good</em> if:</p>

<ul>
	<li><code>num1 &lt;= x &lt;= num2</code></li>
	<li><code>min_sum &lt;= digit_sum(x) &lt;= max_sum</code>.</li>
</ul>

<p>Return <em>the number of good integers</em>. Since the answer may be large, return it modulo <code>10<sup>9</sup> + 7</code>.</p>

<p>Note that <code>digit_sum(x)</code> denotes the sum of the digits of <code>x</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num1 = &quot;1&quot;, num2 = &quot;12&quot;, <code>min_sum</code> = 1, max_sum = 8
<strong>Output:</strong> 11
<strong>Explanation:</strong> There are 11 integers whose sum of digits lies between 1 and 8 are 1,2,3,4,5,6,7,8,10,11, and 12. Thus, we return 11.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num1 = &quot;1&quot;, num2 = &quot;5&quot;, <code>min_sum</code> = 1, max_sum = 5
<strong>Output:</strong> 5
<strong>Explanation:</strong> The 5 integers whose sum of digits lies between 1 and 5 are 1,2,3,4, and 5. Thus, we return 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num1 &lt;= num2 &lt;= 10<sup>22</sup></code></li>
	<li><code>1 &lt;= min_sum &lt;= max_sum &lt;= 400</code></li>
</ul>

## Solutions

### Solution 1: Digit DP

The problem is actually asking for the number of integers in the range $[num1,..num2]$ whose digit sum is in the range $[min\_sum,..max\_sum]$. For this kind of range $[l,..r]$ problem, we can consider transforming it into finding the answers for $[1,..r]$ and $[1,..l-1]$, and then subtracting the latter from the former.

For the answer to $[1,..r]$, we can use digit DP to solve it. We design a function $dfs(pos, s, limit)$, which represents the number of schemes when we are currently processing the $pos$th digit, the digit sum is $s$, and whether the current number has an upper limit $limit$. Here, $pos$ is enumerated from high to low.

For $dfs(pos, s, limit)$, we can enumerate the value of the current digit $i$, and then recursively calculate $dfs(pos+1, s+i, limit \bigcap  i==up)$, where $up$ represents the upper limit of the current digit. If $limit$ is true, then $up$ is the upper limit of the current digit, otherwise $up$ is $9$. If $pos$ is greater than or equal to the length of $num$, then we can judge whether $s$ is in the range $[min\_sum,..max\_sum]$. If it is, return $1$, otherwise return $0$.

The time complexity is $O(10 \times n \times max\_sum)$, and the space complexity is $O(n \times max\_sum)$. Here, $n$ represents the length of $num$.

Similar problems:

-   [2801. Count Stepping Numbers in Range](https://github.com/doocs/leetcode/blob/main/solution/2800-2899/2801.Count%20Stepping%20Numbers%20in%20Range/README_EN.md)

<!-- tabs:start -->

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

<!-- tabs:end -->

<!-- end -->
