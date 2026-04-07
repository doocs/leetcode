---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3869.Count%20Fancy%20Numbers%20in%20a%20Range/README_EN.md
rating: 2254
source: Biweekly Contest 178 Q4
tags:
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [3869. Count Fancy Numbers in a Range](https://leetcode.com/problems/count-fancy-numbers-in-a-range)

[中文文档](/solution/3800-3899/3869.Count%20Fancy%20Numbers%20in%20a%20Range/README.md)

## Description

<!-- description:start -->

<p>You are given two integers <code>l</code> and <code>r</code>.</p>

<p>An integer is called <strong>good</strong> if its digits form a <strong>strictly monotone</strong> sequence, meaning the digits are <strong>strictly increasing</strong> or <strong>strictly decreasing</strong>. All single-digit integers are considered good.</p>

<p>An integer is called <strong>fancy</strong> if it is good, or if the <strong>sum of its digits</strong> is good.</p>

<p>Return an integer representing the number of fancy integers in the range <code>[l, r]</code> (inclusive).</p>

<p>A sequence is said to be <strong>strictly increasing</strong> if each element is <strong>strictly greater</strong> than its previous one (if exists).</p>

<p>A sequence is said to be <strong>strictly decreasing</strong> if each element is <strong>strictly less</strong> than its previous one (if exists).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 8, r = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>8 and 9 are single-digit integers, so they are good and therefore fancy.</li>
	<li>10 has digits <code>[1, 0]</code>, which form a strictly decreasing sequence, so 10 is good and thus fancy.</li>
</ul>

<p>Therefore, the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 12340, r = 12341</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>12340
	<ul>
		<li>12340 is not good because <code>[1, 2, 3, 4, 0]</code> is not strictly monotone.</li>
		<li>The digit sum is <code>1 + 2 + 3 + 4 + 0 = 10</code>.</li>
		<li>10 is good as it has digits <code>[1, 0]</code>, which is strictly decreasing. Therefore, 12340 is fancy.</li>
	</ul>
	</li>
	<li>12341
	<ul>
		<li>12341 is not good because <code>[1, 2, 3, 4, 1]</code> is not strictly monotone.</li>
		<li>The digit sum is <code>1 + 2 + 3 + 4 + 1 = 11</code>.</li>
		<li>11 is not good as it has digits <code>[1, 1]</code>, which is not strictly monotone. Therefore, 12341 is not fancy.</li>
	</ul>
	</li>
</ul>

<p>Therefore, the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 123456788, r = 123456788</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>123456788 is not good because its digits are not strictly monotone.</li>
	<li>The digit sum is <code>1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 8 = 44</code>.</li>
	<li>44 is not good as it has digits <code>[4, 4]</code>, which is not strictly monotone. Therefore, 123456788 is not fancy.</li>
</ul>

<p>Therefore, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= l &lt;= r &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Digit DP

We first define a function $\text{check}(s)$ to determine whether an integer $s$ is a good number. For $s < 100$, we only need to check whether $s$ is a multiple of 11; if so, $s$ is not a good number. For $s \geq 100$, we need to check whether the digits of $s$ form a strictly monotonic sequence, i.e., strictly increasing or strictly decreasing. Since the range of digit sums is small, when the digit sum exceeds $100$, we only need to check the relationship between the tens digit and the units digit of the digit sum.

Next, we use digit DP to count the number of fancy numbers in the interval $[l, r]$. We define a recursive function $\text{dfs}(pos, s, prev, st, lim)$, where:

- Integer $pos$ represents the current digit position being processed, from high to low.
- Integer $s$ represents the current digit sum.
- Integer $prev$ represents the value of the previous digit.
- Integer $st$ represents the state of the current digit sequence, where state $0$ means there is at most one digit so far, state $1$ means strictly increasing, state $2$ means strictly decreasing, and state $3$ means not strictly monotonic.
- Boolean $lim$ indicates whether the current digit is constrained by the upper bound.

In the recursive function, if $pos$ exceeds the length of the number, we have finished processing a number. If the state $st$ is not equal to $3$, the number is a good number and we return $1$; otherwise, we call $\text{check}(s)$ to determine whether the digit sum is a good number, returning $1$ if it is, and $0$ otherwise.

During the recursion, we enumerate the range of the current digit: if $lim$ is true, the range is $[0, \text{num}[pos]]$; otherwise, it is $[0, 9]$. For each value, we update the state $st$ and recursively call $\text{dfs}$ to process the next digit.

Finally, we compute the count of fancy numbers in $[0, r]$ and $[0, l-1]$ separately, and the answer is their difference.

The time complexity is $O(D^3 \times \log^2 r)$ and the space complexity is $O(D^2 \times \log^2 r)$, where $D = 10$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countFancy(self, l: int, r: int) -> int:
        def check(s: int) -> bool:
            if s < 100:
                return s % 11 != 0
            return 1 < s // 10 % 10 < s % 10

        @cache
        def dfs(pos: int, s: int, prev: int, st: int, lim: bool) -> int:
            if pos >= len(num):
                if st != 3:
                    return 1
                return int(check(s))
            up = int(num[pos]) if lim else 9
            res = 0
            for i in range(up + 1):
                nxt_st = st
                if st == 0:
                    if prev == 0:
                        nxt_st = 0
                    elif i > prev:
                        nxt_st = 1
                    elif i < prev:
                        nxt_st = 2
                    else:
                        nxt_st = 3
                elif st == 1:
                    if i > prev:
                        nxt_st = 1
                    else:
                        nxt_st = 3
                elif st == 2:
                    if i < prev:
                        nxt_st = 2
                    else:
                        nxt_st = 3
                else:
                    nxt_st = 3
                res += dfs(pos + 1, s + i, i, nxt_st, lim and i == up)
            return res

        num = str(l - 1)
        a = dfs(0, 0, 0, 0, True)
        dfs.cache_clear()
        num = str(r)
        b = dfs(0, 0, 0, 0, True)
        return b - a
```

#### Java

```java
class Solution {
    private String num;
    private Long[][][][] f;

    public long countFancy(long l, long r) {
        num = String.valueOf(l - 1);
        init();
        long a = dfs(0, 0, 0, 0, true);

        num = String.valueOf(r);
        init();
        long b = dfs(0, 0, 0, 0, true);

        return b - a;
    }

    private void init() {
        int n = num.length();
        f = new Long[n][9 * n + 1][10][4];
    }

    private boolean check(int s) {
        if (s < 100) {
            return s % 11 != 0;
        }
        int mid = (s / 10) % 10;
        int last = s % 10;
        return mid > 1 && mid < last;
    }

    private long dfs(int pos, int s, int prev, int st, boolean lim) {
        if (pos >= num.length()) {
            if (st != 3) {
                return 1;
            }
            return check(s) ? 1 : 0;
        }

        if (!lim && f[pos][s][prev][st] != null) {
            return f[pos][s][prev][st];
        }

        int up = lim ? num.charAt(pos) - '0' : 9;
        long res = 0;

        for (int i = 0; i <= up; i++) {
            int nxtSt = st;

            if (st == 0) {
                if (prev == 0) {
                    nxtSt = 0;
                } else if (i > prev) {
                    nxtSt = 1;
                } else if (i < prev) {
                    nxtSt = 2;
                } else {
                    nxtSt = 3;
                }
            } else if (st == 1) {
                if (i > prev) {
                    nxtSt = 1;
                } else {
                    nxtSt = 3;
                }
            } else if (st == 2) {
                if (i < prev) {
                    nxtSt = 2;
                } else {
                    nxtSt = 3;
                }
            } else {
                nxtSt = 3;
            }

            res += dfs(pos + 1, s + i, i, nxtSt, lim && i == up);
        }

        if (!lim) {
            f[pos][s][prev][st] = res;
        }

        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countFancy(long long l, long long r) {
        auto check = [&](int s) -> bool {
            if (s < 100) {
                return s % 11 != 0;
            }
            int mid = (s / 10) % 10;
            int last = s % 10;
            return mid > 1 && mid < last;
        };

        string num = to_string(l - 1);
        int n = num.size();
        vector f(n, vector(9 * n + 1, vector(10, vector<long long>(4, -1))));

        auto dfs = [&](this auto&& dfs, int pos, int s, int prev, int st, bool lim) -> long long {
            if (pos >= n) {
                if (st != 3) return 1;
                return check(s) ? 1LL : 0LL;
            }

            if (!lim && f[pos][s][prev][st] != -1) {
                return f[pos][s][prev][st];
            }

            int up = lim ? num[pos] - '0' : 9;
            long long res = 0;

            for (int i = 0; i <= up; i++) {
                int nxtSt = st;

                if (st == 0) {
                    if (prev == 0)
                        nxtSt = 0;
                    else if (i > prev)
                        nxtSt = 1;
                    else if (i < prev)
                        nxtSt = 2;
                    else
                        nxtSt = 3;
                } else if (st == 1) {
                    if (i > prev)
                        nxtSt = 1;
                    else
                        nxtSt = 3;
                } else if (st == 2) {
                    if (i < prev)
                        nxtSt = 2;
                    else
                        nxtSt = 3;
                } else {
                    nxtSt = 3;
                }

                res += dfs(pos + 1, s + i, i, nxtSt, lim && i == up);
            }

            if (!lim) {
                f[pos][s][prev][st] = res;
            }

            return res;
        };

        long long a = dfs(0, 0, 0, 0, true);

        num = to_string(r);
        n = num.size();
        f.assign(n, vector(9 * n + 1, vector(10, vector<long long>(4, -1))));

        long long b = dfs(0, 0, 0, 0, true);

        return b - a;
    }
};
```

#### Go

```go
func countFancy(l int64, r int64) int64 {
	check := func(s int) bool {
		if s < 100 {
			return s%11 != 0
		}
		mid := (s / 10) % 10
		last := s % 10
		return mid > 1 && mid < last
	}

	var num string
	var n int
	var f [][][][]int64

	var dfs func(pos, s, prev, st int, lim bool) int64
	dfs = func(pos, s, prev, st int, lim bool) int64 {
		if pos >= n {
			if st != 3 {
				return 1
			}
			if check(s) {
				return 1
			}
			return 0
		}

		if !lim && f[pos][s][prev][st] != -1 {
			return f[pos][s][prev][st]
		}

		up := 9
		if lim {
			up = int(num[pos] - '0')
		}

		var res int64 = 0

		for i := 0; i <= up; i++ {
			nxtSt := st

			if st == 0 {
				if prev == 0 {
					nxtSt = 0
				} else if i > prev {
					nxtSt = 1
				} else if i < prev {
					nxtSt = 2
				} else {
					nxtSt = 3
				}
			} else if st == 1 {
				if i > prev {
					nxtSt = 1
				} else {
					nxtSt = 3
				}
			} else if st == 2 {
				if i < prev {
					nxtSt = 2
				} else {
					nxtSt = 3
				}
			} else {
				nxtSt = 3
			}

			res += dfs(pos+1, s+i, i, nxtSt, lim && i == up)
		}

		if !lim {
			f[pos][s][prev][st] = res
		}

		return res
	}

	calc := func(x int64) int64 {
		num = strconv.FormatInt(x, 10)
		n = len(num)

		f = make([][][][]int64, n)
		for i := 0; i < n; i++ {
			f[i] = make([][][]int64, 9*n+1)
			for j := 0; j <= 9*n; j++ {
				f[i][j] = make([][]int64, 10)
				for k := 0; k < 10; k++ {
					f[i][j][k] = make([]int64, 4)
					for t := 0; t < 4; t++ {
						f[i][j][k][t] = -1
					}
				}
			}
		}

		return dfs(0, 0, 0, 0, true)
	}

	return calc(r) - calc(l-1)
}
```

#### TypeScript

```ts
function countFancy(l: number, r: number): number {
    const check = (s: number): boolean => {
        if (s < 100) {
            return s % 11 !== 0;
        }
        const mid = Math.floor(s / 10) % 10;
        const last = s % 10;
        return mid > 1 && mid < last;
    };

    let num: string;
    let n: number;
    let f: number[][][][];

    const dfs = (pos: number, s: number, prev: number, st: number, lim: boolean): number => {
        if (pos >= n) {
            if (st !== 3) return 1;
            return check(s) ? 1 : 0;
        }

        if (!lim && f[pos][s][prev][st] !== -1) {
            return f[pos][s][prev][st];
        }

        const up = lim ? Number(num[pos]) : 9;
        let res = 0;

        for (let i = 0; i <= up; i++) {
            let nxtSt = st;

            if (st === 0) {
                if (prev === 0) nxtSt = 0;
                else if (i > prev) nxtSt = 1;
                else if (i < prev) nxtSt = 2;
                else nxtSt = 3;
            } else if (st === 1) {
                if (i > prev) nxtSt = 1;
                else nxtSt = 3;
            } else if (st === 2) {
                if (i < prev) nxtSt = 2;
                else nxtSt = 3;
            } else {
                nxtSt = 3;
            }

            res += dfs(pos + 1, s + i, i, nxtSt, lim && i === up);
        }

        if (!lim) {
            f[pos][s][prev][st] = res;
        }

        return res;
    };

    const calc = (x: number): number => {
        num = x.toString();
        n = num.length;

        f = Array.from({ length: n }, () =>
            Array.from({ length: 9 * n + 1 }, () =>
                Array.from({ length: 10 }, () => Array(4).fill(-1)),
            ),
        );

        return dfs(0, 0, 0, 0, true);
    };

    return calc(r) - calc(l - 1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
