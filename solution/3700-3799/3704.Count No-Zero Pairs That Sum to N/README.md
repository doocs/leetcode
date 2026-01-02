---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3704.Count%20No-Zero%20Pairs%20That%20Sum%20to%20N/README.md
rating: 2419
source: 第 470 场周赛 Q4
tags:
    - 数学
    - 动态规划
---

<!-- problem:start -->

# [3704. 统计和为 N 的无零数对](https://leetcode.cn/problems/count-no-zero-pairs-that-sum-to-n)

[English Version](/solution/3700-3799/3704.Count%20No-Zero%20Pairs%20That%20Sum%20to%20N/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个&nbsp;<strong>无零&nbsp;</strong>整数是一个十进制表示中&nbsp;<strong>不包含数字</strong> 0 的 <strong>正</strong>&nbsp;整数。</p>

<p>给定一个整数 <code>n</code>，计算满足以下条件的数对 <code>(a, b)</code> 的数量：</p>

<ul>
	<li><code>a</code> 和 <code>b</code> 都是&nbsp;<strong>无零&nbsp;</strong>整数。</li>
	<li><code>a + b = n</code></li>
</ul>

<p>返回一个整数，表示此类数对的数量。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 2</span></p>

<p><strong>输出:</strong> <span class="example-io">1</span></p>

<p><strong>解释:</strong></p>

<p>唯一的数对是 <code>(1, 1)</code>。</p>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 3</span></p>

<p><strong>输出:</strong> <span class="example-io">2</span></p>

<p><strong>解释:</strong></p>

<p>数对有 <code>(1, 2)</code> 和 <code>(2, 1)</code>。</p>
</div>

<p><strong class="example">示例 3:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">n = 11</span></p>

<p><strong>输出:</strong> <span class="example-io">8</span></p>

<p><strong>解释:</strong></p>

<p>数对有 <code>(2, 9)</code>、<code>(3, 8)</code>、<code>(4, 7)</code>、<code>(5, 6)</code>、<code>(6, 5)</code>、<code>(7, 4)</code>、<code>(8, 3)</code> 和 <code>(9, 2)</code>。请注意，<code>(1, 10)</code> 和 <code>(10, 1)</code> 不满足条件，因为 10 在其十进制表示中包含 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数位 DP

我们对 $n$ 的十进制表示做数位 DP，从最低位到最高位逐位处理。

状态：`dp[pos][carry][aliveA][aliveB]` 表示处理到第 `pos` 位（低位到高位）的方案数。

- `carry` 表示当前位的进位（0 或 1）。
- `aliveA/aliveB` 表示数在更高位是否还“存在”。若 `aliveX = 0`，则更高位只能是前导 0（不属于十进制表示），因此该位数字只能取 0。

转移：选择当前位的 `da`、`db`：

- 若 `aliveX = 1`，则该位数字只能在 `[1..9]`（无零）。
- 否则只能取 0。

需要满足 `(da + db + carry) % 10 == digit_n[pos]`。之后 `aliveA/aliveB` 可以保持为 1，或变为 0（表示在这一位结束该数）。

我们给 $n$ 额外补一位最高位 0，用来吸收最终进位。最终答案是 `dp[last][0][0][0]`。

时间复杂度 $O(L \cdot 9^2)$，空间复杂度 $O(1)$，其中 $L$ 是 $n$ 的位数。

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
