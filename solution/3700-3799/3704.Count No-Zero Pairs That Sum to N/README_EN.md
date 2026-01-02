---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3704.Count%20No-Zero%20Pairs%20That%20Sum%20to%20N/README_EN.md
rating: 2419
source: Weekly Contest 470 Q4
tags:
    - Math
    - Dynamic Programming
---

<!-- problem:start -->

# [3704. Count No-Zero Pairs That Sum to N](https://leetcode.com/problems/count-no-zero-pairs-that-sum-to-n)

[中文文档](/solution/3700-3799/3704.Count%20No-Zero%20Pairs%20That%20Sum%20to%20N/README.md)

## Description

<!-- description:start -->

<p>A <strong>no-zero</strong> integer is a <strong>positive</strong> integer that <strong>does not contain the digit</strong> 0 in its decimal representation.</p>

<p>Given an integer <code>n</code>, count the number of pairs <code>(a, b)</code> where:</p>

<ul>
	<li><code>a</code> and <code>b</code> are <strong>no-zero</strong> integers.</li>
	<li><code>a + b = n</code></li>
</ul>

<p>Return an integer denoting the number of such pairs.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>The only pair is <code>(1, 1)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The pairs are <code>(1, 2)</code> and <code>(2, 1)</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 11</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p>The pairs are <code>(2, 9)</code>, <code>(3, 8)</code>, <code>(4, 7)</code>, <code>(5, 6)</code>, <code>(6, 5)</code>, <code>(7, 4)</code>, <code>(8, 3)</code>, and <code>(9, 2)</code>. Note that <code>(1, 10)</code> and <code>(10, 1)</code> do not satisfy the conditions because 10 contains 0 in its decimal representation.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Digit DP

We do a digit DP over the decimal representation of $n$ from the least-significant digit to the most-significant digit.

State: `dp[pos][carry][aliveA][aliveB]` = number of ways for the processed suffix.

- `carry` is the carry into the current digit (0 or 1).
- `aliveA/aliveB` indicates whether the number still has digits in higher positions. If `aliveX = 0`, all remaining higher digits must be leading zeros (digit 0), which are not part of the decimal representation.

Transition: choose digits `da` and `db`:

- If `aliveX = 1`, digit is in `[1..9]` (no-zero).
- Otherwise digit is `0`.

They must satisfy `(da + db + carry) % 10 == digit_n[pos]`. After that, `aliveA`/`aliveB` can stay `1` or become `0` (ending the number at this digit).

We append one extra leading digit `0` to $n$ so the last carry is fully handled. The answer is `dp[last][0][0][0]`.

Time complexity is $O(L \cdot 9^2)$ and space complexity is $O(1)$, where $L$ is the number of digits of $n$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
	def countNoZeroPairs(self, n: int) -> int:
		digits = list(map(int, str(n)))[::-1]
		digits.append(0)
		L = len(digits)

		# dp[carry][aliveA][aliveB]
		dp = [[[0] * 2 for _ in range(2)] for _ in range(2)]
		dp[0][1][1] = 1

		for pos in range(L):
			ndp = [[[0] * 2 for _ in range(2)] for _ in range(2)]
			target = digits[pos]
			for carry in range(2):
				for aliveA in range(2):
					for aliveB in range(2):
						ways = dp[carry][aliveA][aliveB]
						if ways == 0:
							continue
						aDigits = range(1, 10) if aliveA else (0,)
						bDigits = range(1, 10) if aliveB else (0,)
						for da in aDigits:
							for db in bDigits:
								s = da + db + carry
								if s % 10 != target:
									continue
								ncarry = s // 10
								nextAs = (aliveA, 0) if aliveA else (0,)
								nextBs = (aliveB, 0) if aliveB else (0,)
								for na in nextAs:
									for nb in nextBs:
										ndp[ncarry][na][nb] += ways
			dp = ndp

		return dp[0][0][0]

```

#### Java

```java
class Solution {
	public long countNoZeroPairs(long n) {
		char[] cs = Long.toString(n).toCharArray();
		int m = cs.length;
		int[] digits = new int[m + 1];
		for (int i = 0; i < m; i++) {
			digits[i] = cs[m - 1 - i] - '0';
		}
		digits[m] = 0;

		long[][][] dp = new long[2][2][2];
		dp[0][1][1] = 1;

		for (int pos = 0; pos < m + 1; pos++) {
			long[][][] ndp = new long[2][2][2];
			int target = digits[pos];
			for (int carry = 0; carry <= 1; carry++) {
				for (int aliveA = 0; aliveA <= 1; aliveA++) {
					for (int aliveB = 0; aliveB <= 1; aliveB++) {
						long ways = dp[carry][aliveA][aliveB];
						if (ways == 0) {
							continue;
						}
						int aStart = aliveA == 1 ? 1 : 0;
						int aEnd = aliveA == 1 ? 9 : 0;
						int bStart = aliveB == 1 ? 1 : 0;
						int bEnd = aliveB == 1 ? 9 : 0;
						for (int da = aStart; da <= aEnd; da++) {
							for (int db = bStart; db <= bEnd; db++) {
								int s = da + db + carry;
								if (s % 10 != target) {
									continue;
								}
								int ncarry = s / 10;
								int[] nextAs = aliveA == 1 ? new int[] {1, 0} : new int[] {0};
								int[] nextBs = aliveB == 1 ? new int[] {1, 0} : new int[] {0};
								for (int na : nextAs) {
									for (int nb : nextBs) {
										ndp[ncarry][na][nb] += ways;
									}
								}
							}
						}
					}
				}
			}
			dp = ndp;
		}

		return dp[0][0][0];
	}
}

```

#### C++

```cpp
class Solution {
public:
	long long countNoZeroPairs(long long n) {
		std::string s = std::to_string(n);
		int m = (int) s.size();
		std::vector<int> digits(m + 1);
		for (int i = 0; i < m; i++) {
			digits[i] = s[m - 1 - i] - '0';
		}
		digits[m] = 0;

		long long dp[2][2][2] = {};
		dp[0][1][1] = 1;

		for (int pos = 0; pos < m + 1; pos++) {
			long long ndp[2][2][2] = {};
			int target = digits[pos];
			for (int carry = 0; carry <= 1; carry++) {
				for (int aliveA = 0; aliveA <= 1; aliveA++) {
					for (int aliveB = 0; aliveB <= 1; aliveB++) {
						long long ways = dp[carry][aliveA][aliveB];
						if (!ways) continue;
						int aStart = aliveA ? 1 : 0;
						int aEnd = aliveA ? 9 : 0;
						int bStart = aliveB ? 1 : 0;
						int bEnd = aliveB ? 9 : 0;
						for (int da = aStart; da <= aEnd; da++) {
							for (int db = bStart; db <= bEnd; db++) {
								int sum = da + db + carry;
								if (sum % 10 != target) continue;
								int ncarry = sum / 10;
								int nextAs[2] = {0, 0};
								int nextBs[2] = {0, 0};
								int naLen = aliveA ? 2 : 1;
								int nbLen = aliveB ? 2 : 1;
								nextAs[0] = aliveA;
								nextAs[1] = 0;
								nextBs[0] = aliveB;
								nextBs[1] = 0;
								for (int ia = 0; ia < naLen; ia++) {
									for (int ib = 0; ib < nbLen; ib++) {
										ndp[ncarry][nextAs[ia]][nextBs[ib]] += ways;
									}
								}
							}
						}
					}
				}
			}
			std::memcpy(dp, ndp, sizeof(dp));
		}
		return dp[0][0][0];
	}
};

```

#### Go

```go
package main

import "strconv"

func countNoZeroPairs(n int64) int64 {
	s := []byte(strconv.FormatInt(n, 10))
	m := len(s)
	digits := make([]int, m+1)
	for i := 0; i < m; i++ {
		digits[i] = int(s[m-1-i] - '0')
	}
	digits[m] = 0

	var dp [2][2][2]int64
	dp[0][1][1] = 1

	for pos := 0; pos < m+1; pos++ {
		var ndp [2][2][2]int64
		target := digits[pos]
		for carry := 0; carry <= 1; carry++ {
			for aliveA := 0; aliveA <= 1; aliveA++ {
				for aliveB := 0; aliveB <= 1; aliveB++ {
					ways := dp[carry][aliveA][aliveB]
					if ways == 0 {
						continue
					}
					aStart, aEnd := 0, 0
					if aliveA == 1 {
						aStart, aEnd = 1, 9
					}
					bStart, bEnd := 0, 0
					if aliveB == 1 {
						bStart, bEnd = 1, 9
					}
					nextAs := []int{0}
					if aliveA == 1 {
						nextAs = []int{1, 0}
					}
					nextBs := []int{0}
					if aliveB == 1 {
						nextBs = []int{1, 0}
					}
					for da := aStart; da <= aEnd; da++ {
						for db := bStart; db <= bEnd; db++ {
							sum := da + db + carry
							if sum%10 != target {
								continue
							}
							ncarry := sum / 10
							for _, na := range nextAs {
								for _, nb := range nextBs {
									ndp[ncarry][na][nb] += ways
								}
							}
						}
					}
				}
			}
		}
		dp = ndp
	}

	return dp[0][0][0]
}

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
