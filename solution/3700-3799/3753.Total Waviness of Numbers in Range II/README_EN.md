---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3753.Total%20Waviness%20of%20Numbers%20in%20Range%20II/README_EN.md
rating: 2296
source: Biweekly Contest 170 Q4
tags:
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [3753. Total Waviness of Numbers in Range II](https://leetcode.com/problems/total-waviness-of-numbers-in-range-ii)

[中文文档](/solution/3700-3799/3753.Total%20Waviness%20of%20Numbers%20in%20Range%20II/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>num1</code> and <code>num2</code> representing an <strong>inclusive</strong> range <code>[num1, num2]</code>.</p>

<p>The <strong>waviness</strong> of a number is defined as the total count of its <strong>peaks</strong> and <strong>valleys</strong>:</p>

<ul>
	<li>A digit is a <strong>peak</strong> if it is <strong>strictly greater</strong> than both of its immediate neighbors.</li>
	<li>A digit is a <strong>valley</strong> if it is <strong>strictly less</strong> than both of its immediate neighbors.</li>
	<li>The first and last digits of a number <strong>cannot</strong> be peaks or valleys.</li>
	<li>Any number with fewer than 3 digits has a waviness of 0.</li>
</ul>
Return the total sum of waviness for all numbers in the range <code>[num1, num2]</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num1 = 120, num2 = 130</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>In the range <code>[120, 130]</code>:</p>

<ul>
	<li><code>120</code>: middle digit 2 is a peak, waviness = 1.</li>
	<li><code>121</code>: middle digit 2 is a peak, waviness = 1.</li>
	<li><code>130</code>: middle digit 3 is a peak, waviness = 1.</li>
	<li>All other numbers in the range have a waviness of 0.</li>
</ul>

<p>Thus, total waviness is <code>1 + 1 + 1 = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num1 = 198, num2 = 202</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>In the range <code>[198, 202]</code>:</p>

<ul>
	<li><code>198</code>: middle digit 9 is a peak, waviness = 1.</li>
	<li><code>201</code>: middle digit 0 is a valley, waviness = 1.</li>
	<li><code>202</code>: middle digit 0 is a valley, waviness = 1.</li>
	<li>All other numbers in the range have a waviness of 0.</li>
</ul>

<p>Thus, total waviness is <code>1 + 1 + 1 = 3</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">num1 = 4848, num2 = 4848</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Number <code>4848</code>: the second digit 8 is a peak, and the third digit 4 is a valley, giving a waviness of 2.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num1 &lt;= num2 &lt;= 10<sup>15</sup></code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Digit DP

We need the total waviness of all numbers in $[num1, num2]$. Convert the range query to $calc(num2) - calc(num1 - 1)$, where $calc(x)$ is the total waviness in $[1, x]$.

Use digit DP from the most significant digit. Let $dfs(pos, prev2, prev1, started, limit)$ be the number of valid numbers and their total waviness when we are filling position $pos$, the previous two digits are $prev2$ and $prev1$ (use $10$ if a digit is not yet filled), $started$ indicates whether a non-leading zero has been placed, and $limit$ indicates whether we are still bounded by the upper limit.

Enumerate the current digit $d$. If at least two digits have been placed and $prev1$ is strictly greater (or smaller) than both $prev2$ and $d$, then $prev1$ is a peak (or valley) and contributes $1$ to waviness, multiplied by the number of ways to fill the remaining digits.

The time complexity is $O(\log x)$, and the space complexity is $O(\log x)$, where $x$ is the upper bound.

Similar problems:

- [3751. Total Waviness of Numbers in Range I](https://github.com/doocs/leetcode/blob/main/solution/3700-3799/3751.Total%20Waviness%20of%20Numbers%20in%20Range%20I/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def totalWaviness(self, num1: int, num2: int) -> int:
        def calc(x: int) -> int:
            if x < 0:
                return 0
            s = str(x)

            @cache
            def dfs(
                pos: int, prev2: int, prev1: int, started: int, limit: bool
            ) -> tuple:
                if pos == len(s):
                    return (started, 0)
                up = int(s[pos]) if limit else 9
                cnt = wav = 0
                for d in range(up + 1):
                    nlimit = limit and d == up
                    add = 0
                    if started == 0:
                        if d == 0:
                            ns, np2, np1 = 0, 10, 10
                        else:
                            ns, np2, np1 = 1, 10, d
                    else:
                        ns, np2, np1 = 1, prev1, d
                        if prev2 != 10 and (
                            (prev1 > prev2 and prev1 > d)
                            or (prev1 < prev2 and prev1 < d)
                        ):
                            add = 1
                    c, w = dfs(pos + 1, np2, np1, ns, nlimit)
                    cnt += c
                    wav += w + c * add
                return cnt, wav

            return dfs(0, 10, 10, 0, True)[1]

        return calc(num2) - calc(num1 - 1)
```

#### Java

```java
class Solution {
    private char[] cs;
    private long[][][][] cnt;
    private long[][][][] wav;

    public long totalWaviness(long num1, long num2) {
        return calc(num2) - calc(num1 - 1);
    }

    private long calc(long x) {
        if (x < 0) {
            return 0;
        }
        cs = Long.toString(x).toCharArray();
        int n = cs.length;
        cnt = new long[n][11][11][2];
        wav = new long[n][11][11][2];
        for (int i = 0; i < n; ++i) {
            for (int a = 0; a < 11; ++a) {
                for (int b = 0; b < 11; ++b) {
                    Arrays.fill(cnt[i][a][b], -1);
                    Arrays.fill(wav[i][a][b], -1);
                }
            }
        }
        return dfs(0, 10, 10, 0, true)[1];
    }

    private long[] dfs(int pos, int prev2, int prev1, int started, boolean limit) {
        if (pos == cs.length) {
            return new long[] {started, 0};
        }
        if (!limit && cnt[pos][prev2][prev1][started] != -1) {
            return new long[] {cnt[pos][prev2][prev1][started], wav[pos][prev2][prev1][started]};
        }
        int up = limit ? cs[pos] - '0' : 9;
        long c = 0, w = 0;
        for (int d = 0; d <= up; ++d) {
            boolean nlimit = limit && d == up;
            int ns, np2, np1, add = 0;
            if (started == 0) {
                if (d == 0) {
                    ns = 0;
                    np2 = 10;
                    np1 = 10;
                } else {
                    ns = 1;
                    np2 = 10;
                    np1 = d;
                }
            } else {
                ns = 1;
                np2 = prev1;
                np1 = d;
                if (prev2 != 10 && ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d))) {
                    add = 1;
                }
            }
            long[] t = dfs(pos + 1, np2, np1, ns, nlimit);
            c += t[0];
            w += t[1] + t[0] * add;
        }
        if (!limit) {
            cnt[pos][prev2][prev1][started] = c;
            wav[pos][prev2][prev1][started] = w;
        }
        return new long[] {c, w};
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long totalWaviness(long long num1, long long num2) {
        return calc(num2) - calc(num1 - 1);
    }

private:
    string s;
    long long fCnt[20][11][11][2];
    long long fWav[20][11][11][2];
    bool vis[20][11][11][2];

    long long calc(long long x) {
        if (x < 0) {
            return 0;
        }
        s = to_string(x);
        memset(vis, 0, sizeof(vis));
        return dfs(0, 10, 10, 0, true).second;
    }

    pair<long long, long long> dfs(int pos, int prev2, int prev1, int started, bool limit) {
        if (pos == s.size()) {
            return {started, 0};
        }
        if (!limit && vis[pos][prev2][prev1][started]) {
            return {fCnt[pos][prev2][prev1][started], fWav[pos][prev2][prev1][started]};
        }
        int up = limit ? s[pos] - '0' : 9;
        long long c = 0, w = 0;
        for (int d = 0; d <= up; ++d) {
            bool nlimit = limit && d == up;
            int ns, np2, np1, add = 0;
            if (started == 0) {
                if (d == 0) {
                    ns = 0;
                    np2 = 10;
                    np1 = 10;
                } else {
                    ns = 1;
                    np2 = 10;
                    np1 = d;
                }
            } else {
                ns = 1;
                np2 = prev1;
                np1 = d;
                if (prev2 != 10 && ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d))) {
                    add = 1;
                }
            }
            auto [tc, tw] = dfs(pos + 1, np2, np1, ns, nlimit);
            c += tc;
            w += tw + tc * add;
        }
        if (!limit) {
            vis[pos][prev2][prev1][started] = true;
            fCnt[pos][prev2][prev1][started] = c;
            fWav[pos][prev2][prev1][started] = w;
        }
        return {c, w};
    }
};
```

#### Go

```go
import "strconv"

func totalWaviness(num1 int64, num2 int64) int64 {
	return calc(num2) - calc(num1-1)
}

func calc(x int64) int64 {
	if x < 0 {
		return 0
	}
	s := strconv.FormatInt(x, 10)
	n := len(s)
	var fCnt, fWav [20][11][11][2]int64
	var vis [20][11][11][2]bool
	var dfs func(pos, prev2, prev1, started int, limit bool) (int64, int64)
	dfs = func(pos, prev2, prev1, started int, limit bool) (int64, int64) {
		if pos == n {
			return int64(started), 0
		}
		if !limit && vis[pos][prev2][prev1][started] {
			return fCnt[pos][prev2][prev1][started], fWav[pos][prev2][prev1][started]
		}
		up := 9
		if limit {
			up = int(s[pos] - '0')
		}
		var c, w int64
		for d := 0; d <= up; d++ {
			nlimit := limit && d == up
			ns, np2, np1, add := started, prev1, d, 0
			if started == 0 {
				if d == 0 {
					ns, np2, np1 = 0, 10, 10
				} else {
					ns, np2, np1 = 1, 10, d
				}
			} else if prev2 != 10 && ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d)) {
				add = 1
			}
			tc, tw := dfs(pos+1, np2, np1, ns, nlimit)
			c += tc
			w += tw + tc*int64(add)
		}
		if !limit {
			vis[pos][prev2][prev1][started] = true
			fCnt[pos][prev2][prev1][started] = c
			fWav[pos][prev2][prev1][started] = w
		}
		return c, w
	}
	_, wav := dfs(0, 10, 10, 0, true)
	return wav
}
```

#### C

```c
static int len, digits[20];
static long long fCnt[20][11][11][2];
static long long fWav[20][11][11][2];
static char vis[20][11][11][2];
static long long cnt, wav;

static void dfs(int pos, int prev2, int prev1, int started, int limit) {
    if (pos == len) {
        cnt = started;
        wav = 0;
        return;
    }
    if (!limit && vis[pos][prev2][prev1][started]) {
        cnt = fCnt[pos][prev2][prev1][started];
        wav = fWav[pos][prev2][prev1][started];
        return;
    }
    int up = limit ? digits[pos] : 9;
    long long c = 0, w = 0;
    for (int d = 0; d <= up; ++d) {
        int nlimit = limit && d == up;
        int ns, np2, np1, add = 0;
        if (started == 0) {
            if (d == 0) {
                ns = 0;
                np2 = 10;
                np1 = 10;
            } else {
                ns = 1;
                np2 = 10;
                np1 = d;
            }
        } else {
            ns = 1;
            np2 = prev1;
            np1 = d;
            if (prev2 != 10 && ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d))) {
                add = 1;
            }
        }
        dfs(pos + 1, np2, np1, ns, nlimit);
        c += cnt;
        w += wav + add * cnt;
    }
    if (!limit) {
        vis[pos][prev2][prev1][started] = 1;
        fCnt[pos][prev2][prev1][started] = c;
        fWav[pos][prev2][prev1][started] = w;
    }
    cnt = c;
    wav = w;
}

static long long calc(long long x) {
    if (x < 0) {
        return 0;
    }
    len = 0;
    if (x == 0) {
        digits[len++] = 0;
    } else {
        int buf[20];
        int l = 0;
        while (x) {
            buf[l++] = x % 10;
            x /= 10;
        }
        for (int i = l - 1; i >= 0; --i) {
            digits[len++] = buf[i];
        }
    }
    memset(vis, 0, sizeof(vis));
    dfs(0, 10, 10, 0, 1);
    return wav;
}

long long totalWaviness(long long num1, long long num2) {
    return calc(num2) - calc(num1 - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
