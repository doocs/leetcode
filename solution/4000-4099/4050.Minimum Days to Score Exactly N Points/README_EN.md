---
comments: true
difficulty: Medium
rating: 1696
source: Biweekly Contest 191 Q3
---

<!-- problem:start -->

# [4050. Minimum Days to Score Exactly N Points](https://leetcode.com/problems/minimum-days-to-score-exactly-n-points)

[中文文档](/solution/4000-4099/4050.Minimum%20Days%20to%20Score%20Exactly%20N%20Points/README.md)

## Description

<!-- description:start -->

<p>You are given an integer <code>n</code> representing a target score.</p>

<p>Your score starts at 0, and each day you either <strong>earn</strong> points or <strong>skip</strong>.</p>

<p>Points are earned during a streak. On the first day of a streak you earn 1 point, on the second day 2 points, on the third day 3 points, and so on. <strong>Skipping</strong> a day earns <strong>nothing</strong> and <strong>resets</strong> the streak, so the next time you earn points, you start from 1 again.</p>

<p>Return the <strong>minimum</strong> number of days, including any skipped days, needed to reach a score of <strong>exactly</strong> <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Day 1: earn 1 point. Score is 1.</li>
	<li>Day 2: skip, which resets the streak. Earning here would add 2 points and take the score past <code>n = 2</code>.</li>
	<li>Day 3: the streak has reset, so earning gives 1 point. Score is exactly <code>n = 2</code> in 3 days.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 9</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Days 1 to 3: earn 1, 2, and 3 points. Score is <code>1 + 2 + 3 = 6</code>.</li>
	<li>Day 4: skip, which resets the streak.</li>
	<li>Days 5 and 6: earn 1 and 2 points. Score is exactly <code>6 + 1 + 2 = 9</code> in 6 days.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 12</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Days 1 to 3: earn 1, 2, and 3 points. Score is <code>1 + 2 + 3 = 6</code>.</li>
	<li>Day 4: skip, which resets the streak.</li>
	<li>Days 5 to 7: earn 1, 2, and 3 points. Score is exactly <code>6 + 1 + 2 + 3 = 12</code> in 7 days.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming

<!-- thinking:start -->

> **Thinking**
>
> The score is a sum of streaks: a streak of length $j$ contributes the triangular number $j(j+1)/2$. Consecutive streaks must be separated by a skip that resets the streak; the last streak needs no trailing skip.
>
> $n = 10^5$ rules out day-by-day simulation and searching over partitions. Treat “exactly $i$ points” as an unbounded knapsack whose items are streaks and whose cost is the number of days.
>
> Set $f[0] = -1$ and always add $j + 1$ (the extra skip) in the transition. The extra skip on the last streak is cancelled by $f[0] = -1$, so the answer is $f[n]$.

<!-- thinking:end -->

A streak of $j$ days scores the triangular number $s = \frac{j(j+1)}{2}$. Two consecutive streaks must be separated by exactly one skipped day that resets the streak, while the last streak needs no extra skip.

Let $f[i]$ be the minimum number of days needed to score exactly $i$ points. Set $f[0] = -1$ and initialize the remaining entries to $+\infty$. Enumerate the length $j$ of the last streak (with score $s$):

$$
f[i] = \min\bigl(f[i],\, f[i - s] + j + 1\bigr)
$$

The $j + 1$ accounts for the $j$ earning days plus one skip. $f[0] = -1$ cancels the extra skip on the last streak: if a single streak of length $j$ already scores $n$, then $f[n] = f[0] + j + 1 = j$.

Since $n \le 10^5$, we precompute up to the limit and answer each query in $O(1)$. The largest useful $j$ is about $\sqrt{2n}$.

The time complexity is $O(n \times \sqrt{n})$ for preprocessing, and the space complexity is $O(n)$. Each query is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
mx = 10**5 + 1
f = [inf] * mx
f[0] = -1
for i in range(1, mx):
    j = 1
    while (s := (1 + j) * j // 2) <= i:
        f[i] = min(f[i], f[i - s] + j + 1)
        j += 1


class Solution:
    def minDays(self, n: int) -> int:
        return f[n]
```

#### Java

```java
class Solution {
    private static final int MX = 100001;
    private static final int[] f = new int[MX];

    static {
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = -1;

        for (int i = 1; i < MX; i++) {
            for (int j = 1; j * (j + 1) / 2 <= i; j++) {
                int s = j * (j + 1) / 2;
                f[i] = Math.min(f[i], f[i - s] + j + 1);
            }
        }
    }

    public int minDays(int n) {
        return f[n];
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minDays(int n) {
        static const auto f = [] {
            constexpr int mx = 100001;
            vector<int> f(mx, INT_MAX);

            f[0] = -1;

            for (int i = 1; i < mx; i++) {
                for (int j = 1; j * (j + 1) / 2 <= i; j++) {
                    int s = j * (j + 1) / 2;
                    f[i] = std::min(f[i], f[i - s] + j + 1);
                }
            }

            return f;
        }();

        return f[n];
    }
};
```

#### Go

```go
const mx = 100001

var f = func() []int {
	f := make([]int, mx)

	for i := range f {
		f[i] = int(^uint(0) >> 1)
	}

	f[0] = -1

	for i := 1; i < mx; i++ {
		for j := 1; j*(j+1)/2 <= i; j++ {
			s := j * (j + 1) / 2
			f[i] = min(f[i], f[i-s]+j+1)
		}
	}

	return f
}()

func minDays(n int) int {
	return f[n]
}
```

#### TypeScript

```ts
const MX = 100001;

const f = new Array<number>(MX).fill(Infinity);

f[0] = -1;

for (let i = 1; i < MX; i++) {
    for (let j = 1; (j * (j + 1)) / 2 <= i; j++) {
        const s = (j * (j + 1)) / 2;
        f[i] = Math.min(f[i], f[i - s] + j + 1);
    }
}

function minDays(n: number): number {
    return f[n];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
