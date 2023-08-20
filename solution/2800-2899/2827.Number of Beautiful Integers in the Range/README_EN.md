# [2827. Number of Beautiful Integers in the Range](https://leetcode.com/problems/number-of-beautiful-integers-in-the-range)

[中文文档](/solution/2800-2899/2827.Number%20of%20Beautiful%20Integers%20in%20the%20Range/README.md)

## Description

<p>You are given positive integers <code>low</code>, <code>high</code>, and <code>k</code>.</p>

<p>A number is <strong>beautiful</strong> if it meets both of the following conditions:</p>

<ul>
	<li>The count of even digits in the number is equal to the count of odd digits.</li>
	<li>The number is divisible by <code>k</code>.</li>
</ul>

<p>Return <em>the number of beautiful integers in the range</em> <code>[low, high]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> low = 10, high = 20, k = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 beautiful integers in the given range: [12,18]. 
- 12 is beautiful because it contains 1 odd digit and 1 even digit, and is divisible by k = 3.
- 18 is beautiful because it contains 1 odd digit and 1 even digit, and is divisible by k = 3.
Additionally we can see that:
- 16 is not beautiful because it is not divisible by k = 3.
- 15 is not beautiful because it does not contain equal counts even and odd digits.
It can be shown that there are only 2 beautiful integers in the given range.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> low = 1, high = 10, k = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is 1 beautiful integer in the given range: [10].
- 10 is beautiful because it contains 1 odd digit and 1 even digit, and is divisible by k = 1.
It can be shown that there is only 1 beautiful integer in the given range.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> low = 5, high = 5, k = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong> There are 0 beautiful integers in the given range.
- 5 is not beautiful because it is not divisible by k = 2 and it does not contain equal even and odd digits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt; low &lt;= high &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt; k &lt;= 20</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **C++**

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

### **Go**

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

### **TypeScript**

```ts
function numberOfBeautifulIntegers(
    low: number,
    high: number,
    k: number,
): number {
    let s = String(high);
    let f: number[][][] = Array(11)
        .fill(0)
        .map(() =>
            Array(21)
                .fill(0)
                .map(() => Array(21).fill(-1)),
        );
    const dfs = (
        pos: number,
        mod: number,
        diff: number,
        lead: boolean,
        limit: boolean,
    ): number => {
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
                ans += dfs(
                    pos + 1,
                    (mod * 10 + i) % k,
                    nxt,
                    false,
                    limit && i === up,
                );
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

### **...**

```

```

<!-- tabs:end -->
