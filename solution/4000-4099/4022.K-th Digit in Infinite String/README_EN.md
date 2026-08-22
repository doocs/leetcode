---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4022.K-th%20Digit%20in%20Infinite%20String/README_EN.md
tags:
    - Math
    - Binary Search
---

<!-- problem:start -->

# [4022. K-th Digit in Infinite String](https://leetcode.com/problems/k-th-digit-in-infinite-string)

[中文文档](/solution/4000-4099/4022.K-th%20Digit%20in%20Infinite%20String/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>k</code>.</p>

<p>An <strong>infinite</strong> string is formed by <strong>concatenating</strong> the <strong>decimal</strong> representations of the <strong>positive</strong> integers, without separators.</p>

<p>For every nonnegative integer <code>b</code>, block <code>b</code> contains the <strong>positive</strong> integers from <code>10 * b</code> through <code>10 * b + 9</code>. The integers in each block are appended as follows:</p>

<ul>
	<li>If <code>b</code> is even, append the integers in <strong>increasing</strong> order.</li>
	<li>If <code>b</code> is odd, append the integers in <strong>decreasing</strong> order.</li>
</ul>

<p>Therefore, the string starts with the integers 1 through 9, followed by 19 through 10, then 20 through 29, then 39 through 30, and so on.</p>

<p>Return the <code>k<sup>th</sup></code> digit (1-indexed) of this string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">k = 4</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The string begins as <code>&quot;123<u>4</u>56789..&quot;</code>. The 4<sup>th</sup> digit is <code>&#39;4&#39;</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">k = 15</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<p>The string begins as <code>&quot;12345678919181<u>7</u>..&quot;</code>. The 15<sup>th</sup> digit is <code>&#39;7&#39;</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">k = 11</span></p>

<p><strong>Output:</strong> <span class="example-io">9</span></p>

<p><strong>Explanation:</strong></p>

<p>The string begins as <code>&quot;1234567891<u>9</u>..&quot;</code>. The 11<sup>th</sup> digit is <code>&#39;9&#39;</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>15</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

The infinite string is formed by concatenating blocks: block $b$ contains the positive integers from $10b$ to $10b+9$ (block $0$ starts from $1$). Even blocks are appended in increasing order, and odd blocks in decreasing order.

We first handle $1$ through $9$ ($9$ digits in total). Then we group by the number of digits $d = 2, 3, \ldots$: $d$-digit numbers correspond to blocks $b \in [10^{d-2}, 10^{d-1} - 1]$, i.e., $9 \times 10^{d-2}$ blocks. Each block has $10$ numbers of $d$ digits, so each block contributes $10d$ digits.

We subtract the total number of digits of each group until we locate the group that contains the $k$-th digit. Then we compute the block index $b$ and the position within the block from the remaining offset, determine the corresponding integer according to the parity of $b$, and extract the required digit.

The time complexity is $O(\log k)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def kthDigit(self, k: int) -> int:
        if k <= 9:
            return k

        k -= 9
        d = 2
        start = 1

        while True:
            cnt = 9 * 10 ** (d - 2)
            size = 10 * d

            if k <= cnt * size:
                break

            k -= cnt * size
            d += 1
            start *= 10

        b = start + (k - 1) // size
        pos = (k - 1) % size

        i = pos // d
        num = 10 * b + i if b % 2 == 0 else 10 * b + 9 - i

        return int(str(num)[pos % d])
```

#### Java

```java
class Solution {
    public int kthDigit(long k) {
        if (k <= 9) {
            return (int) k;
        }

        k -= 9;
        long d = 2;
        long start = 1;
        long size = 0;

        while (true) {
            long cnt = 9 * (long) Math.pow(10, d - 2);
            size = 10 * d;

            if (k <= cnt * size) {
                break;
            }

            k -= cnt * size;
            d++;
            start *= 10;
        }

        long b = start + (k - 1) / size;
        long pos = (k - 1) % size;

        long i = pos / d;

        long num = (b % 2 == 0) ? 10 * b + i : 10 * b + 9 - i;

        return String.valueOf(num).charAt((int) (pos % d)) - '0';
    }
}
```

#### C++

```cpp
class Solution {
public:
    int kthDigit(long long k) {
        if (k <= 9) {
            return (int) k;
        }

        k -= 9;
        long long d = 2;
        long long start = 1;
        long long size = 0;

        while (true) {
            long long cnt = 9 * (long long) pow(10, d - 2);
            size = 10 * d;

            if (k <= cnt * size) {
                break;
            }

            k -= cnt * size;
            d++;
            start *= 10;
        }

        long long b = start + (k - 1) / size;
        long long pos = (k - 1) % size;

        long long i = pos / d;

        long long num;
        if (b % 2 == 0) {
            num = 10 * b + i;
        } else {
            num = 10 * b + 9 - i;
        }

        return to_string(num)[pos % d] - '0';
    }
};
```

#### Go

```go
import (
	"math"
	"strconv"
)

func kthDigit(k int64) int {
	if k <= 9 {
		return int(k)
	}

	k -= 9
	var d int64 = 2
	var start int64 = 1
	var size int64

	for {
		cnt := int64(9) * int64(math.Pow10(int(d-2)))
		size = 10 * d

		if k <= cnt*size {
			break
		}

		k -= cnt * size
		d++
		start *= 10
	}

	b := start + (k-1)/size
	pos := (k - 1) % size

	i := pos / d

	var num int64
	if b%2 == 0 {
		num = 10*b + i
	} else {
		num = 10*b + 9 - i
	}

	s := strconv.FormatInt(num, 10)

	return int(s[pos%d] - '0')
}
```

#### TypeScript

```ts
function kthDigit(k: number): number {
    if (k <= 9) {
        return k;
    }

    k -= 9;
    let d = 2;
    let start = 1;
    let size = 0;

    while (true) {
        const cnt = 9 * Math.pow(10, d - 2);
        size = 10 * d;

        if (k <= cnt * size) {
            break;
        }

        k -= cnt * size;
        d++;
        start *= 10;
    }

    const b = start + Math.floor((k - 1) / size);
    const pos = (k - 1) % size;

    const i = Math.floor(pos / d);

    let num: number;
    if (b % 2 === 0) {
        num = 10 * b + i;
    } else {
        num = 10 * b + 9 - i;
    }

    return Number(String(num)[pos % d]);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
