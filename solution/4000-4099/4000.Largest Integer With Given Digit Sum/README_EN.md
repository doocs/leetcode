---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4000.Largest%20Integer%20With%20Given%20Digit%20Sum/README_EN.md
rating: 1199
source: Weekly Contest 512 Q1
---

<!-- problem:start -->

# [4000. Largest Integer With Given Digit Sum](https://leetcode.com/problems/largest-integer-with-given-digit-sum)

[中文文档](/solution/4000-4099/4000.Largest%20Integer%20With%20Given%20Digit%20Sum/README.md)

## Description

<!-- description:start -->

<p>You are given two non-negative integers <code>n</code> and <code>s</code>.</p>

<p>Return the <strong>largest</strong> integer that has <strong>at most</strong> <code>n</code> digits and whose sum of digits is <code>s</code>. If no such integer exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, s = 9</span></p>

<p><strong>Output:</strong> <span class="example-io">90</span></p>

<p><strong>Explanation:</strong></p>

<p>The largest integer with at most 2 digits that has a sum of digits of 9 is 90.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, s = 19</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>There is no integer with at most 2 digits that has a sum of digits of 19, so the answer is -1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, s = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The only non-negative integer whose digits sum to 0 is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 5</code></li>
	<li><code>0 &lt;= s &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy

If $n \times 9 < s$, even filling every digit with $9$ cannot reach digit sum $s$, so return $-1$.

Otherwise, to maximize the integer, assign as large a digit as possible to higher places. Construct $n$ digits from high to low: each digit takes $\min(s, 9)$, then subtract that value from $s$. The resulting integer is the answer (if $s = 0$, the result is $0$).

The time complexity is $O(n)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestInteger(self, n: int, s: int) -> int:
        if n * 9 < s:
            return -1
        ans = 0
        for _ in range(n):
            x = min(s, 9)
            ans = ans * 10 + x
            s -= x
        return ans
```

#### Java

```java
class Solution {
    public int largestInteger(int n, int s) {
        if (n * 9 < s) {
            return -1;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = Math.min(s, 9);
            ans = ans * 10 + x;
            s -= x;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestInteger(int n, int s) {
        if (n * 9 < s) {
            return -1;
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int x = min(s, 9);
            ans = ans * 10 + x;
            s -= x;
        }
        return ans;
    }
};
```

#### Go

```go
func largestInteger(n int, s int) (ans int) {
	if n*9 < s {
		return -1
	}
	for i := 0; i < n; i++ {
		x := min(s, 9)
		ans = ans*10 + x
		s -= x
	}
	return
}
```

#### TypeScript

```ts
function largestInteger(n: number, s: number): number {
    if (n * 9 < s) {
        return -1;
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        const x = Math.min(s, 9);
        ans = ans * 10 + x;
        s -= x;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
