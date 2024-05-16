---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2999.Count%20the%20Number%20of%20Powerful%20Integers/README_EN.md
rating: 2351
source: Biweekly Contest 121 Q4
tags:
    - Math
    - String
    - Dynamic Programming
---

# [2999. Count the Number of Powerful Integers](https://leetcode.com/problems/count-the-number-of-powerful-integers)

[中文文档](/solution/2900-2999/2999.Count%20the%20Number%20of%20Powerful%20Integers/README.md)

## Description

<p>You are given three integers <code>start</code>, <code>finish</code>, and <code>limit</code>. You are also given a <strong>0-indexed</strong> string <code>s</code> representing a <strong>positive</strong> integer.</p>

<p>A <strong>positive</strong> integer <code>x</code> is called <strong>powerful</strong> if it ends with <code>s</code> (in other words, <code>s</code> is a <strong>suffix</strong> of <code>x</code>) and each digit in <code>x</code> is at most <code>limit</code>.</p>

<p>Return <em>the <strong>total</strong> number of powerful integers in the range</em> <code>[start..finish]</code>.</p>

<p>A string <code>x</code> is a suffix of a string <code>y</code> if and only if <code>x</code> is a substring of <code>y</code> that starts from some index (<strong>including </strong><code>0</code>) in <code>y</code> and extends to the index <code>y.length - 1</code>. For example, <code>25</code> is a suffix of <code>5125</code> whereas <code>512</code> is not.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> start = 1, finish = 6000, limit = 4, s = &quot;124&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The powerful integers in the range [1..6000] are 124, 1124, 2124, 3124, and, 4124. All these integers have each digit &lt;= 4, and &quot;124&quot; as a suffix. Note that 5124 is not a powerful integer because the first digit is 5 which is greater than 4.
It can be shown that there are only 5 powerful integers in this range.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> start = 15, finish = 215, limit = 6, s = &quot;10&quot;
<strong>Output:</strong> 2
<strong>Explanation:</strong> The powerful integers in the range [15..215] are 110 and 210. All these integers have each digit &lt;= 6, and &quot;10&quot; as a suffix.
It can be shown that there are only 2 powerful integers in this range.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> start = 1000, finish = 2000, limit = 4, s = &quot;3000&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> All integers in the range [1000..2000] are smaller than 3000, hence &quot;3000&quot; cannot be a suffix of any integer in this range.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= start &lt;= finish &lt;= 10<sup>15</sup></code></li>
	<li><code>1 &lt;= limit &lt;= 9</code></li>
	<li><code>1 &lt;= s.length &lt;= floor(log<sub>10</sub>(finish)) + 1</code></li>
	<li><code>s</code> only consists of numeric digits which are at most <code>limit</code>.</li>
	<li><code>s</code> does not have leading zeros.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def numberOfPowerfulInt(self, start: int, finish: int, limit: int, s: str) -> int:
        @cache
        def dfs(pos: int, lim: int):
            if len(t) < n:
                return 0
            if len(t) - pos == n:
                return int(s <= t[pos:]) if lim else 1
            up = min(int(t[pos]) if lim else 9, limit)
            ans = 0
            for i in range(up + 1):
                ans += dfs(pos + 1, lim and i == int(t[pos]))
            return ans

        n = len(s)
        t = str(start - 1)
        a = dfs(0, True)
        dfs.cache_clear()
        t = str(finish)
        b = dfs(0, True)
        return b - a
```

```java
class Solution {
    private String s;
    private String t;
    private Long[] f;
    private int limit;

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        this.s = s;
        this.limit = limit;
        t = String.valueOf(start - 1);
        f = new Long[20];
        long a = dfs(0, true);
        t = String.valueOf(finish);
        f = new Long[20];
        long b = dfs(0, true);
        return b - a;
    }

    private long dfs(int pos, boolean lim) {
        if (t.length() < s.length()) {
            return 0;
        }
        if (!lim && f[pos] != null) {
            return f[pos];
        }
        if (t.length() - pos == s.length()) {
            return lim ? (s.compareTo(t.substring(pos)) <= 0 ? 1 : 0) : 1;
        }
        int up = lim ? t.charAt(pos) - '0' : 9;
        up = Math.min(up, limit);
        long ans = 0;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos + 1, lim && i == (t.charAt(pos) - '0'));
        }
        if (!lim) {
            f[pos] = ans;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long numberOfPowerfulInt(long long start, long long finish, int limit, string s) {
        string t = to_string(start - 1);
        long long f[20];
        memset(f, -1, sizeof(f));

        function<long long(int, bool)> dfs = [&](int pos, bool lim) -> long long {
            if (t.size() < s.size()) {
                return 0;
            }
            if (!lim && f[pos] != -1) {
                return f[pos];
            }
            if (t.size() - pos == s.size()) {
                return lim ? s <= t.substr(pos) : 1;
            }
            long long ans = 0;
            int up = min(lim ? t[pos] - '0' : 9, limit);
            for (int i = 0; i <= up; ++i) {
                ans += dfs(pos + 1, lim && i == (t[pos] - '0'));
            }
            if (!lim) {
                f[pos] = ans;
            }
            return ans;
        };

        long long a = dfs(0, true);
        t = to_string(finish);
        memset(f, -1, sizeof(f));
        long long b = dfs(0, true);
        return b - a;
    }
};
```

```go
func numberOfPowerfulInt(start, finish int64, limit int, s string) int64 {
	t := strconv.FormatInt(start-1, 10)
	f := make([]int64, 20)
	for i := range f {
		f[i] = -1
	}

	var dfs func(int, bool) int64
	dfs = func(pos int, lim bool) int64 {
		if len(t) < len(s) {
			return 0
		}
		if !lim && f[pos] != -1 {
			return f[pos]
		}
		if len(t)-pos == len(s) {
			if lim {
				if s <= t[pos:] {
					return 1
				}
				return 0
			}
			return 1
		}

		ans := int64(0)
		up := 9
		if lim {
			up = int(t[pos] - '0')
		}
		up = min(up, limit)
		for i := 0; i <= up; i++ {
			ans += dfs(pos+1, lim && i == int(t[pos]-'0'))
		}
		if !lim {
			f[pos] = ans
		}
		return ans
	}

	a := dfs(0, true)
	t = strconv.FormatInt(finish, 10)
	for i := range f {
		f[i] = -1
	}
	b := dfs(0, true)
	return b - a
}
```

```ts
function numberOfPowerfulInt(start: number, finish: number, limit: number, s: string): number {
    let t: string = (start - 1).toString();
    let f: number[] = Array(20).fill(-1);

    const dfs = (pos: number, lim: boolean): number => {
        if (t.length < s.length) {
            return 0;
        }
        if (!lim && f[pos] !== -1) {
            return f[pos];
        }
        if (t.length - pos === s.length) {
            if (lim) {
                return s <= t.substring(pos) ? 1 : 0;
            }
            return 1;
        }

        let ans: number = 0;
        const up: number = Math.min(lim ? +t[pos] : 9, limit);
        for (let i = 0; i <= up; i++) {
            ans += dfs(pos + 1, lim && i === +t[pos]);
        }

        if (!lim) {
            f[pos] = ans;
        }
        return ans;
    };

    const a: number = dfs(0, true);
    t = finish.toString();
    f = Array(20).fill(-1);
    const b: number = dfs(0, true);

    return b - a;
}
```

<!-- tabs:end -->

<!-- end -->
