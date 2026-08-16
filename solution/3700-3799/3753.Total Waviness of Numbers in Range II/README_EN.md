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

### Solution 1

<!-- tabs:start -->

#### Python3

```python

```

#### Java

```java
class Solution {

    private char[] s;
    private Pair[][][][] memo;

    static class Pair {
        long cnt;
        long wav;

        Pair(long cnt, long wav) {
            this.cnt = cnt;
            this.wav = wav;
        }
    }

    public long totalWaviness(long num1, long num2) {
        return calc(num2) - calc(num1 - 1);
    }

    private long calc(long x) {
        if (x <= 0) return 0;

        s = Long.toString(x).toCharArray();

        int n = s.length;
        memo = new Pair[n][11][11][2];

        return dfs(0, true, false, 10, 10).wav;
    }

    private Pair dfs(int pos,
                     boolean tight,
                     boolean started,
                     int prev2,
                     int prev1) {

        if (pos == s.length) {
            return new Pair(started ? 1 : 0, 0);
        }

        if (!tight) {
            Pair cached = memo[pos][prev2][prev1][started ? 1 : 0];
            if (cached != null) return cached;
        }

        int limit = tight ? s[pos] - '0' : 9;

        long totalCnt = 0;
        long totalWav = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nt = tight && (d == (s[pos] - '0'));

            if (!started && d == 0) {

                Pair nxt = dfs(pos + 1, nt, false, 10, 10);

                totalCnt += nxt.cnt;
                totalWav += nxt.wav;
            }
            else if (!started) {

                Pair nxt = dfs(pos + 1, nt, true, 10, d);

                totalCnt += nxt.cnt;
                totalWav += nxt.wav;
            }
            else if (prev2 == 10) {

                Pair nxt = dfs(pos + 1, nt, true, prev1, d);

                totalCnt += nxt.cnt;
                totalWav += nxt.wav;
            }
            else {

                int add =
                    ((prev1 > prev2 && prev1 > d) ||
                     (prev1 < prev2 && prev1 < d))
                    ? 1 : 0;

                Pair nxt = dfs(pos + 1, nt, true, prev1, d);

                totalCnt += nxt.cnt;
                totalWav += nxt.wav + nxt.cnt * add;
            }
        }

        Pair ans = new Pair(totalCnt, totalWav);

        if (!tight) {
            memo[pos][prev2][prev1][started ? 1 : 0] = ans;
        }

        return ans;
    }
}

```

#### C++

```cpp

```

#### Go

```go

```

#### C

```c
static int len, digits[20];
static long long memoCnt[20][11][11][2];
static long long memoSum[20][11][11][2];
static char vis[20][11][11][2];
static long long cnt, sum;

static void dfs(int pos, int pp, int pr, int st, int ti) {
    if (pos == len) {
        cnt = 1;
        sum = 0;
        return;
    }
    if (!ti && vis[pos][pp][pr][st]) {
        cnt = memoCnt[pos][pp][pr][st];
        sum = memoSum[pos][pp][pr][st];
        return;
    }
    int h = ti ? digits[pos] : 9;
    long long c = 0, s = 0;
    for (int d = 0; d <= h; d++) {
        int ns = st || d;
        long long a = 0;
        int npp, np;
        if (!ns) {
            npp = 10;
            np = 10;
        } else if (!st) {
            npp = 10;
            np = d;
        } else {
            if (pp != 10 && pr != 10 && ((pr > pp && pr > d) || (pr < pp && pr < d)))
                a = 1;
            npp = pr;
            np = d;
        }
        dfs(pos + 1, npp, np, ns, ti && d == h);
        c += cnt;
        s += sum + a * cnt;
    }
    if (!ti) {
        vis[pos][pp][pr][st] = 1;
        memoCnt[pos][pp][pr][st] = c;
        memoSum[pos][pp][pr][st] = s;
    }
    cnt = c;
    sum = s;
}

static long long calc(long long N) {
    if (N < 0) return 0;
    len = 0;
    long long x = N;
    if (!x) {
        digits[len++] = 0;
    } else {
        char buf[20];
        int l = 0;
        while (x) {
            buf[l++] = x % 10;
            x /= 10;
        }
        for (int i = l - 1; i >= 0; i--)
            digits[len++] = buf[i];
    }
    memset(vis, 0, sizeof(vis));
    dfs(0, 10, 10, 0, 1);
    return sum;
}

long long totalWaviness(long long a, long long b) {
    return calc(b) - calc(a - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
