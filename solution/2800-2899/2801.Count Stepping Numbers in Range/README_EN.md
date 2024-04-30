# [2801. Count Stepping Numbers in Range](https://leetcode.com/problems/count-stepping-numbers-in-range)

[中文文档](/solution/2800-2899/2801.Count%20Stepping%20Numbers%20in%20Range/README.md)

<!-- tags:String,Dynamic Programming -->

## Description

<p>Given two positive integers <code>low</code> and <code>high</code> represented as strings, find the count of <strong>stepping numbers</strong> in the inclusive range <code>[low, high]</code>.</p>

<p>A <strong>stepping number</strong> is an integer such that all of its adjacent digits have an absolute difference of <strong>exactly</strong> <code>1</code>.</p>

<p>Return <em>an integer denoting the count of stepping numbers in the inclusive range</em> <code>[low, high]</code><em>. </em></p>

<p>Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p><strong>Note:</strong> A stepping number should not have a leading zero.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> low = &quot;1&quot;, high = &quot;11&quot;
<strong>Output:</strong> 10
<strong>Explanation: </strong>The stepping numbers in the range [1,11] are 1, 2, 3, 4, 5, 6, 7, 8, 9 and 10. There are a total of 10 stepping numbers in the range. Hence, the output is 10.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> low = &quot;90&quot;, high = &quot;101&quot;
<strong>Output:</strong> 2
<strong>Explanation: </strong>The stepping numbers in the range [90,101] are 98 and 101. There are a total of 2 stepping numbers in the range. Hence, the output is 2. </pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= int(low) &lt;= int(high) &lt; 10<sup>100</sup></code></li>
	<li><code>1 &lt;= low.length, high.length &lt;= 100</code></li>
	<li><code>low</code> and <code>high</code> consist of only digits.</li>
	<li><code>low</code> and <code>high</code> don&#39;t have any leading zeros.</li>
</ul>

## Solutions

### Solution 1: Digit DP

We notice that the problem is asking for the number of stepping numbers in the interval $[low, high]$. For such an interval $[l,..r]$ problem, we can usually consider transforming it into finding the answers for $[1, r]$ and $[1, l-1]$, and then subtracting the latter from the former. Moreover, the problem only involves the relationship between different digits, not the specific values, so we can consider using Digit DP to solve it.

We design a function $dfs(pos, pre, lead, limit)$, which represents the number of schemes when we are currently processing the $pos$-th digit, the previous digit is $pre$, whether the current number only contains leading zeros is $lead$, and whether the current number has reached the upper limit is $limit$. The range of $pos$ is $[0, len(num))$.

The execution logic of the function $dfs(pos, pre, lead, limit)$ is as follows:

If $pos$ exceeds the length of $num$, it means that we have processed all the digits. If $lead$ is true at this time, it means that the current number only contains leading zeros and is not a valid number. We can return $0$ to indicate that the number of schemes is $0$; otherwise, we return $1$ to indicate that the number of schemes is $1$.

Otherwise, we calculate the upper limit $up$ of the current digit, and then enumerate the digit $i$ in the range $[0,..up]$:

-   If $i=0$ and $lead$ is true, it means that the current number only contains leading zeros. We recursively calculate the value of $dfs(pos+1,pre, true, limit\ and\ i=up)$ and add it to the answer.
-   Otherwise, if $pre$ is $-1$, or the absolute difference between $i$ and $pre$ is $1$, it means that the current number is a valid stepping number. We recursively calculate the value of $dfs(pos+1,i, false, limit\ and\ i=up)$ and add it to the answer.

Finally, we return the answer.

In the main function, we calculate the answers $a$ and $b$ for $[1, high]$ and $[1, low-1]$ respectively. The final answer is $a-b$. Note the modulo operation of the answer.

The time complexity is $O(\log M \times |\Sigma|^2)$, and the space complexity is $O(\log M \times |\Sigma|)$, where $M$ represents the size of the number $high$, and $|\Sigma|$ represents the digit set.

Similar problems:

-   [2719. Count of Integers](https://github.com/doocs/leetcode/blob/main/solution/2700-2799/2719.Count%20of%20Integers/README_EN.md)

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
