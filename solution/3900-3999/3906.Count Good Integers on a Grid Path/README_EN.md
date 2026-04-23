---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3906.Count%20Good%20Integers%20on%20a%20Grid%20Path/README_EN.md
tags:
    - Dynamic Programming
---

<!-- problem:start -->

# [3906. Count Good Integers on a Grid Path](https://leetcode.com/problems/count-good-integers-on-a-grid-path)

[中文文档](/solution/3900-3999/3906.Count%20Good%20Integers%20on%20a%20Grid%20Path/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>l</code> and <code>r</code>, and a string <code>directions</code> consisting of <strong>exactly</strong> three <code>&#39;D&#39;</code> characters and three <code>&#39;R&#39;</code> characters.</p>

<p>For each integer <code>x</code> in the range <code>[l, r]</code> (inclusive), perform the following steps:</p>

<ol>
	<li>If <code>x</code> has fewer than 16 digits, pad it on the left with <strong>leading zeros</strong> to obtain a 16-digit string.</li>
	<li>Place the 16 digits into a <code>4 &times; 4</code> grid in <strong>row-major</strong> order (the first 4 digits form the first row from left to right, the next 4 digits form the second row, and so on).</li>
	<li>Starting at the <strong>top-left</strong> cell (<code>row = 0</code>, <code>column = 0</code>), apply the 6 characters of <code>directions</code> in order:
	<ul>
		<li><code>&#39;D&#39;</code> increments the row by 1.</li>
		<li><code>&#39;R&#39;</code> increments the column by 1.</li>
	</ul>
	</li>
	<li>Record the sequence of digits visited along the path (including the starting cell), producing a sequence of length 7.</li>
</ol>

<p>The integer <code>x</code> is considered <strong>good</strong> if the recorded sequence is <strong>non-decreasing</strong>.</p>

<p>Return an integer representing the number of good integers in the range <code>[l, r]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 8, r = 10, directions = &quot;DDDRRR&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The grid for <code>x = 8</code>:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">8</td>
		</tr>
	</tbody>
</table>

<ul>
	<li>Path: <code>(0,0) &rarr; (1,0) &rarr; (2,0) &rarr; (3,0) &rarr; (3,1) &rarr; (3,2) &rarr; (3,3)</code></li>
	<li>The sequence of digits visited is <code>[0, 0, 0, 0, 0, 0, 8]</code>.</li>
	<li>As the sequence of digits visited is non-decreasing, 8 is a good integer.</li>
</ul>

<p>The grid for <code>x = 9</code>:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">9</td>
		</tr>
	</tbody>
</table>

<ul>
	<li>The sequence of digits visited is <code>[0, 0, 0, 0, 0, 0, 9]</code>.</li>
	<li>As the sequence of digits visited is non-decreasing, 9 is a good integer.</li>
</ul>

<p>The grid for <code>x = 10</code>:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
	</tbody>
</table>

<ul>
	<li>The sequence of digits visited is <code>[0, 0, 0, 0, 0, 1, 0]</code>.</li>
	<li>As the sequence of digits visited is not non-decreasing, 10 is not a good integer.</li>
	<li>Hence, only 8 and 9 are good, giving a total of 2 good integers in the range.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 123456789, r = 123456790, directions = &quot;DDRRDR&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The grid for <code>x = 123456789</code>:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">8</td>
			<td style="border: 1px solid black;">9</td>
		</tr>
	</tbody>
</table>

<ul>
	<li>Path: <code>(0,0) &rarr; (1,0) &rarr; (2,0) &rarr; (2,1) &rarr; (2,2) &rarr; (3,2) &rarr; (3,3)</code></li>
	<li>The sequence of digits visited is <code>[0, 0, 2, 3, 4, 8, 9]</code>.</li>
	<li>As the sequence of digits visited is non-decreasing, 123456789 is a good integer.</li>
</ul>

<p>The grid for <code>x = 123456790</code>:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">0</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">4</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">9</td>
			<td style="border: 1px solid black;">0</td>
		</tr>
	</tbody>
</table>

<ul>
	<li>The sequence of digits visited is <code>[0, 0, 2, 3, 4, 9, 0]</code>.</li>
	<li>As the sequence of digits visited is not non-decreasing, 123456790 is not a good integer.</li>
	<li>Hence, only 123456789 is good, giving a total of 1 good integer in the range.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 1288561398769758, r = 1288561398769758, directions = &quot;RRRDDD&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The grid for <code>x = 1288561398769758</code>:</p>

<table style="border: 1px solid black;">
	<tbody>
		<tr style="background:none;">
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">8</td>
			<td style="border: 1px solid black;">8</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">6</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">9</td>
			<td style="border: 1px solid black;">8</td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">6</td>
		</tr>
		<tr style="background:none;">
			<td style="border: 1px solid black;">9</td>
			<td style="border: 1px solid black;">7</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">8</td>
		</tr>
	</tbody>
</table>

<ul>
	<li>Path: <code>(0,0) &rarr; (0,1) &rarr; (0,2) &rarr; (0,3) &rarr; (1,3) &rarr; (2,3) &rarr; (3,3)</code></li>
	<li>The sequence of digits visited is <code>[1, 2, 8, 8, 3, 6, 8]</code>.</li>
	<li>​​​​​​​As the sequence of digits visited is not non-decreasing, 1288561398769758 is not a good integer.</li>
	<li>No numbers are good, giving a total of 0 good integers in the range.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= l &lt;= r &lt;= 9 &times; 10<sup>15</sup></code></li>
	<li><code>directions.length == 6</code></li>
	<li><code>directions</code> consists of <strong>exactly</strong> three <code>&#39;D&#39;</code> characters and three <code>&#39;R&#39;</code> characters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Digit DP

Since the 6 characters in $\textit{directions}$ determine the path, we can preprocess a boolean array $\textit{key}$ of length 16, where $\textit{key}[i]$ indicates whether the $i$-th cell visited along the path is a key cell (i.e., a cell visited on the path). We can compute the $\textit{key}$ array based on $\textit{directions}$.

Next, we use digit dynamic programming (digit DP) to count the number of integers in the range $[l, r]$ that satisfy the condition. We convert $r$ and $l - 1$ to 16-digit strings $s$, then use a recursive function to count the number of valid integers in $[0, r]$, and subtract the count in $[0, l - 1]$ to get the answer for $[l, r]$.

We define a recursive function $\textit{dfs}(pos, last, lim)$, where $pos$ is the current digit position, $last$ is the digit of the previous key cell, and $lim$ indicates whether the current digit is restricted by $s$ (i.e., whether the current prefix matches $s$ so far).

In the recursive function, we first check if all positions have been processed; if so, return 1. Otherwise, we determine the range of digits to try at the current position: if $\textit{key}[pos]$ is true, the digit must be at least $last$; otherwise, it can start from 0. The upper bound is $s[pos]$ if $lim$ is true, or 9 otherwise.

We enumerate all possible digits for the current position, updating $last$ to the current digit if this is a key cell, or keeping it unchanged otherwise. We also update $lim$: if the current digit equals the upper bound, $lim$ remains true; otherwise, it becomes false. We sum the results of all branches and return the total.

The time complexity is $O(D^2 \times \log r)$ and the space complexity is $O(D \times \log r)$, where $D = 10$ is the range of digits and $\log r$ is the number of digits in $r$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countGoodIntegersOnPath(self, l: int, r: int, directions: str) -> int:
        key = [False] * 16
        row, col = 0, 0
        key[0] = True
        for c in directions:
            if c == "D":
                row += 1
            else:
                col += 1
            key[row * 4 + col] = True

        s = ""

        @cache
        def dfs(pos, last, lim):
            if pos == 16:
                return 1

            res = 0
            start = last if key[pos] else 0
            end = int(s[pos]) if lim else 9

            for i in range(start, end + 1):
                res += dfs(pos + 1, i if key[pos] else last, lim and (i == end))

            return res

        def calc(x):
            nonlocal s
            if x < 0:
                return 0
            s = str(x).zfill(16)
            dfs.cache_clear()
            return dfs(0, 0, True)

        return calc(r) - calc(l - 1)
```

#### Java

```java
class Solution {
    private boolean[] key;
    private long[][] f;
    private String s;

    public long countGoodIntegersOnPath(long l, long r, String directions) {
        key = new boolean[16];
        int row = 0, col = 0;
        key[0] = true;
        for (char c : directions.toCharArray()) {
            if (c == 'D') {
                row++;
            } else {
                col++;
            }
            key[row * 4 + col] = true;
        }

        return calc(r) - calc(l - 1);
    }

    private long dfs(int pos, int last, boolean lim) {
        if (pos == 16) {
            return 1;
        }
        if (!lim && f[pos][last] != -1) {
            return f[pos][last];
        }

        long res = 0;
        int start = key[pos] ? last : 0;
        int end = lim ? (s.charAt(pos) - '0') : 9;

        for (int i = start; i <= end; i++) {
            res += dfs(pos + 1, key[pos] ? i : last, lim && (i == end));
        }

        if (!lim) {
            f[pos][last] = res;
        }
        return res;
    }

    private long calc(long x) {
        if (x < 0) {
            return 0;
        }
        String t = String.valueOf(x);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16 - t.length(); i++) {
            sb.append('0');
        }
        s = sb.append(t).toString();
        f = new long[16][10];
        for (long[] row : f) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, true);
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countGoodIntegersOnPath(long long l, long long r, string directions) {
        bool key[16];
        memset(key, 0, sizeof(key));
        int row = 0, col = 0;
        key[0] = true;
        for (char c : directions) {
            if (c == 'D') {
                row++;
            } else {
                col++;
            }
            key[row * 4 + col] = true;
        }

        long long f[16][10];
        string s;

        auto dfs = [&](this auto&& dfs, int pos, int last, bool lim) -> long long {
            if (pos == 16) {
                return 1;
            }
            if (!lim && f[pos][last] != -1) {
                return f[pos][last];
            }

            long long res = 0;
            int start = key[pos] ? last : 0;
            int end = lim ? (s[pos] - '0') : 9;

            for (int i = start; i <= end; i++) {
                res += dfs(pos + 1, key[pos] ? i : last, lim && (i == end));
            }

            if (!lim) {
                f[pos][last] = res;
            }
            return res;
        };

        auto calc = [&](long long x) {
            if (x < 0) {
                return 0LL;
            }
            string t = to_string(x);
            s = string(16 - t.length(), '0') + t;
            memset(f, -1, sizeof(f));
            return dfs(0, 0, true);
        };

        return calc(r) - calc(l - 1);
    }
};
```

#### Go

```go
func countGoodIntegersOnPath(l int64, r int64, directions string) int64 {
	key := make([]bool, 16)
	row, col := 0, 0
	key[0] = true
	for _, c := range directions {
		if c == 'D' {
			row++
		} else {
			col++
		}
		key[row*4+col] = true
	}

	var s string
	var f [16][10]int64

	var dfs func(int, int, bool) int64
	dfs = func(pos int, last int, lim bool) int64 {
		if pos == 16 {
			return 1
		}
		if !lim && f[pos][last] != -1 {
			return f[pos][last]
		}

		var res int64 = 0
		start := 0
		if key[pos] {
			start = last
		}
		end := 9
		if lim {
			end = int(s[pos] - '0')
		}

		for i := start; i <= end; i++ {
			nextLast := last
			if key[pos] {
				nextLast = i
			}
			res += dfs(pos+1, nextLast, lim && (i == end))
		}

		if !lim {
			f[pos][last] = res
		}
		return res
	}

	calc := func(x int64) int64 {
		if x < 0 {
			return 0
		}
		t := strconv.FormatInt(x, 10)
		s = fmt.Sprintf("%016s", t)
		for i := 0; i < 16; i++ {
			for j := 0; j < 10; j++ {
				f[i][j] = -1
			}
		}
		return dfs(0, 0, true)
	}

	return calc(r) - calc(l-1)
}
```

#### TypeScript

```ts
function countGoodIntegersOnPath(l: number, r: number, directions: string): number {
    const key = new Array(16).fill(false);
    let row = 0,
        col = 0;
    key[0] = true;
    for (const c of directions) {
        if (c === 'D') {
            row++;
        } else {
            col++;
        }
        key[row * 4 + col] = true;
    }

    let s: string;
    let f: number[][];

    const dfs = (pos: number, last: number, lim: boolean): number => {
        if (pos === 16) {
            return 1;
        }
        if (!lim && f[pos][last] !== -1) {
            return f[pos][last];
        }

        let res = 0;
        const start = key[pos] ? last : 0;
        const end = lim ? parseInt(s[pos]) : 9;

        for (let i = start; i <= end; i++) {
            res += dfs(pos + 1, key[pos] ? i : last, lim && i === end);
        }

        if (!lim) {
            f[pos][last] = res;
        }
        return res;
    };

    const calc = (x: number): number => {
        if (x < 0) {
            return 0;
        }
        s = x.toString().padStart(16, '0');
        f = Array.from({ length: 16 }, () => {
            return new Array(10).fill(-1);
        });
        return dfs(0, 0, true);
    };

    return calc(r) - calc(l - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
