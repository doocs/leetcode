---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3821.Find%20Nth%20Smallest%20Integer%20With%20K%20One%20Bits/README_EN.md
---

<!-- problem:start -->

# [3821. Find Nth Smallest Integer With K One Bits](https://leetcode.com/problems/find-nth-smallest-integer-with-k-one-bits)

[中文文档](/solution/3800-3899/3821.Find%20Nth%20Smallest%20Integer%20With%20K%20One%20Bits/README.md)

## Description

<!-- description:start -->

<p>You are given two positive integers <code>n</code> and <code>k</code>.</p>

<p>Return an integer denoting the <code>n<sup>th</sup></code> smallest positive integer that has <strong>exactly</strong> <code>k</code> ones in its binary representation. It is guaranteed that the answer is <strong>strictly less</strong> than <code>2<sup>50</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>The 4 smallest positive integers that have exactly <code>k = 2</code> ones in their binary representations are:</p>

<ul>
	<li><code>3 = 11<sub>2</sub></code></li>
	<li><code>5 = 101<sub>2</sub></code></li>
	<li><code>6 = 110<sub>2</sub></code></li>
	<li><code>9 = 1001<sub>2</sub></code></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The 3 smallest positive integers that have exactly <code>k = 1</code> one in their binary representations are:</p>

<ul>
	<li><code>1 = 1<sub>2</sub></code></li>
	<li><code>2 = 10<sub>2</sub></code></li>
	<li><code>4 = 100<sub>2</sub></code></li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>50</sup></code></li>
	<li><code>1 &lt;= k &lt;= 50</code></li>
	<li>The answer is strictly less than <code>2<sup>50</sup></code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Combinatorics + Greedy

We need to find the $n$-th smallest positive integer that contains exactly $k$ ones in its binary representation. We can determine each bit from the most significant to the least significant, deciding whether it is $0$ or $1$.

Suppose we are currently processing the $i$-th bit (from $49$ down to $0$). If we set this bit to $0$, then the remaining $k$ ones need to be chosen from the lower $i$ bits, and the number of possible combinations is $C(i, k)$. If $n$ is greater than $C(i, k)$, it implies that the $i$-th bit of the $n$-th number must be $1$. In this case, we set this bit to $1$, subtract $C(i, k)$ from $n$, and decrement $k$ by $1$ (since we have already used one $1$). Otherwise, we set this bit to $0$.

We repeat the above process until all bits are processed or $k$ becomes $0$.

The time complexity is $O(\log^2 M)$, and the space complexity is $O(\log^2 M)$, where $M$ is the upper bound of the answer, $2^{50}$.

<!-- tabs:start -->

#### Python3

```python
mx = 50
c = [[0] * (mx + 1) for _ in range(mx)]
for i in range(mx):
    c[i][0] = 1
    for j in range(1, i + 1):
        c[i][j] = c[i - 1][j - 1] + c[i - 1][j]


class Solution:
    def nthSmallest(self, n: int, k: int) -> int:
        ans = 0
        for i in range(49, -1, -1):
            if n > c[i][k]:
                n -= c[i][k]
                ans |= 1 << i
                k -= 1
                if k == 0:
                    break
        return ans
```

#### Java

```java
class Solution {
    private static final int MX = 50;
    private static final long[][] c = new long[MX][MX + 1];

    static {
        for (int i = 0; i < MX; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
            }
        }
    }

    public long nthSmallest(long n, int k) {
        long ans = 0;
        for (int i = 49; i >= 0; i--) {
            if (n > c[i][k]) {
                n -= c[i][k];
                ans |= 1L << i;
                k--;
                if (k == 0) {
                    break;
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
constexpr int MX = 50;
long long c[MX][MX + 1];

auto init = [] {
    for (int i = 0; i < MX; i++) {
        c[i][0] = 1;
        for (int j = 1; j <= i; j++) {
            c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
        }
    }
    return 0;
}();

class Solution {
public:
    long long nthSmallest(long long n, int k) {
        long long ans = 0;
        for (int i = 49; i >= 0; i--) {
            if (n > c[i][k]) {
                n -= c[i][k];
                ans |= 1LL << i;
                if (--k == 0) {
                    break;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
const MX = 50

var c [MX][MX + 1]int64

func init() {
	for i := 0; i < MX; i++ {
		c[i][0] = 1
		for j := 1; j <= i; j++ {
			c[i][j] = c[i-1][j-1] + c[i-1][j]
		}
	}
}

func nthSmallest(n int64, k int) int64 {
	var ans int64 = 0
	for i := 49; i >= 0; i-- {
		if n > c[i][k] {
			n -= c[i][k]
			ans |= 1 << i
			k--
			if k == 0 {
				break
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
const MX = 50;
const c: bigint[][] = Array.from({ length: MX }, () => Array(MX + 1).fill(0n));

for (let i = 0; i < MX; i++) {
    c[i][0] = 1n;
    for (let j = 1; j <= i; j++) {
        c[i][j] = c[i - 1][j - 1] + c[i - 1][j];
    }
}

function nthSmallest(n: number, k: number): number {
    let nn = BigInt(n);
    let ans = 0n;
    for (let i = 49; i >= 0; i--) {
        if (nn > c[i][k]) {
            nn -= c[i][k];
            ans |= 1n << BigInt(i);
            if (--k === 0) {
                break;
            }
        }
    }
    return Number(ans);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
