---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3233.Find%20the%20Count%20of%20Numbers%20Which%20Are%20Not%20Special/README_EN.md
---

<!-- problem:start -->

# [3233. Find the Count of Numbers Which Are Not Special](https://leetcode.com/problems/find-the-count-of-numbers-which-are-not-special)

[中文文档](/solution/3200-3299/3233.Find%20the%20Count%20of%20Numbers%20Which%20Are%20Not%20Special/README.md)

## Description

<!-- description:start -->

<p>You are given 2 <strong>positive</strong> integers <code>l</code> and <code>r</code>. For any number <code>x</code>, all positive divisors of <code>x</code> <em>except</em> <code>x</code> are called the <strong>proper divisors</strong> of <code>x</code>.</p>

<p>A number is called <strong>special</strong> if it has exactly 2 <strong>proper divisors</strong>. For example:</p>

<ul>
	<li>The number 4 is <em>special</em> because it has proper divisors 1 and 2.</li>
	<li>The number 6 is <em>not special</em> because it has proper divisors 1, 2, and 3.</li>
</ul>

<p>Return the count of numbers in the range <code>[l, r]</code> that are <strong>not</strong> <strong>special</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 5, r = 7</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no special numbers in the range <code>[5, 7]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">l = 4, r = 16</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<p>The special numbers in the range <code>[4, 16]</code> are 4 and 9.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= l &lt;= r &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

According to the problem description, we can observe that only the squares of prime numbers are special numbers. Therefore, we can first preprocess all prime numbers less than or equal to $\sqrt{10^9}$, and then iterate through the interval $[\lceil\sqrt{l}\rceil, \lfloor\sqrt{r}\rfloor]$, counting the number of primes $\textit{cnt}$ in the interval. Finally, we return $r - l + 1 - \textit{cnt}$.

The time complexity is $O(\sqrt{m})$, and the space complexity is $O(\sqrt{m})$, where $m = 10^9$.

<!-- tabs:start -->

#### Python3

```python
m = 31623
primes = [True] * (m + 1)
primes[0] = primes[1] = False
for i in range(2, m + 1):
    if primes[i]:
        for j in range(i + i, m + 1, i):
            primes[j] = False


class Solution:
    def nonSpecialCount(self, l: int, r: int) -> int:
        lo = ceil(sqrt(l))
        hi = floor(sqrt(r))
        cnt = sum(primes[i] for i in range(lo, hi + 1))
        return r - l + 1 - cnt
```

#### Java

```java
class Solution {
    static int m = 31623;
    static boolean[] primes = new boolean[m + 1];

    static {
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i = 2; i <= m; i++) {
            if (primes[i]) {
                for (int j = i + i; j <= m; j += i) {
                    primes[j] = false;
                }
            }
        }
    }

    public int nonSpecialCount(int l, int r) {
        int lo = (int) Math.ceil(Math.sqrt(l));
        int hi = (int) Math.floor(Math.sqrt(r));
        int cnt = 0;
        for (int i = lo; i <= hi; i++) {
            if (primes[i]) {
                cnt++;
            }
        }
        return r - l + 1 - cnt;
    }
}
```

#### C++

```cpp
const int m = 31623;
bool primes[m + 1];

auto init = [] {
    memset(primes, true, sizeof(primes));
    primes[0] = primes[1] = false;
    for (int i = 2; i <= m; ++i) {
        if (primes[i]) {
            for (int j = i * 2; j <= m; j += i) {
                primes[j] = false;
            }
        }
    }
    return 0;
}();

class Solution {
public:
    int nonSpecialCount(int l, int r) {
        int lo = ceil(sqrt(l));
        int hi = floor(sqrt(r));
        int cnt = 0;
        for (int i = lo; i <= hi; ++i) {
            if (primes[i]) {
                ++cnt;
            }
        }
        return r - l + 1 - cnt;
    }
};
```

#### Go

```go
const m = 31623

var primes [m + 1]bool

func init() {
	for i := range primes {
		primes[i] = true
	}
	primes[0] = false
	primes[1] = false
	for i := 2; i <= m; i++ {
		if primes[i] {
			for j := i * 2; j <= m; j += i {
				primes[j] = false
			}
		}
	}
}

func nonSpecialCount(l int, r int) int {
	lo := int(math.Ceil(math.Sqrt(float64(l))))
	hi := int(math.Floor(math.Sqrt(float64(r))))
	cnt := 0
	for i := lo; i <= hi; i++ {
		if primes[i] {
			cnt++
		}
	}
	return r - l + 1 - cnt
}
```

#### TypeScript

```ts
const m = 31623;
const primes: boolean[] = Array(m + 1).fill(true);

(() => {
    primes[0] = primes[1] = false;
    for (let i = 2; i <= m; ++i) {
        if (primes[i]) {
            for (let j = i * 2; j <= m; j += i) {
                primes[j] = false;
            }
        }
    }
})();

function nonSpecialCount(l: number, r: number): number {
    const lo = Math.ceil(Math.sqrt(l));
    const hi = Math.floor(Math.sqrt(r));
    let cnt = 0;
    for (let i = lo; i <= hi; ++i) {
        if (primes[i]) {
            ++cnt;
        }
    }
    return r - l + 1 - cnt;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
