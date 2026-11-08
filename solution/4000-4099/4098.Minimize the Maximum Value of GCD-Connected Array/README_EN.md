---
comments: true
difficulty: Medium
rating: 1729
source: Weekly Contest 526 Q2
---

<!-- problem:start -->

# [4098. Minimize the Maximum Value of GCD-Connected Array](https://leetcode.com/problems/minimize-the-maximum-value-of-gcd-connected-array)

[中文文档](/solution/4000-4099/4098.Minimize%20the%20Maximum%20Value%20of%20GCD-Connected%20Array/README.md)

## Description

<!-- description:start -->

<p>You are given three integers <code>n</code>, <code>index</code>, and <code>value</code>.</p>

<p>An integer array <code>nums</code> of length <code>n</code> is called <strong>GCD-Connected</strong> if:</p>

<ul>
	<li><code>nums[i] &gt; 0</code> for <code>0 &lt;= i &lt;= n - 1</code>.</li>
	<li><code>nums[i] &lt; nums[i + 1]</code> for <code>0 &lt;= i &lt;= n - 2</code>.</li>
	<li><code>nums[index] = value</code>.</li>
	<li><code><span data-keyword="gcd-function">gcd</span>(nums[i], nums[i + 1]) &gt; 1</code> for <code>0 &lt;= i &lt;= n - 2</code>.</li>
</ul>

<p>Return the <strong>minimum</strong> possible maximum value, <code>nums[n - 1]</code>, among all GCD-Connected arrays <code>nums</code>. If no such array exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 4, index = 2, value = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The GCD-Connected array will look like: ​​​​​​​<code>nums = [?, ?, 8, ?]</code> since <code>nums[2] = 8</code>.</li>
	<li>Since <code>nums[3] &gt; 8</code> and <code>gcd(8, 9) = 1</code>, every valid GCD-Connected array must have <code>nums[3] &gt;= 10</code>.</li>
	<li>One optimal array satisfying all conditions is <code>[2, 4, 8, 10]</code>.</li>
	<li>Thus, the minimum possible value of <code>nums[3]</code> is 10.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, index = 1, value = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The GCD-Connected array will look like: <code>nums = [?, 3]</code> since <code>nums[1] = 3</code>.</li>
	<li>Since we need <code>0 &lt; nums[0] &lt; nums[1] = 3</code>, there are only two possible arrays: <code>[1, 3]</code> and <code>[2, 3]</code>. Neither of them satisfies <code>gcd(nums[0], nums[1]) &gt; 1</code>.</li>
	<li>Therefore, no valid GCD-Connected array exists, and the answer is -1.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, index = 0, value = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The GCD-Connected array will look like: <code>nums = [3, ?]</code> since <code>nums[0] = 3</code>.</li>
	<li>Since <code>nums[1] &gt; 3</code> and <code>gcd(3, 4) = gcd(3, 5) = 1</code>, every valid GCD-Connected array must have <code>nums[1] &gt;= 6</code>.</li>
	<li>One optimal array is <code>[3, 6]</code>.</li>
	<li>Thus, the minimum possible value of <code>nums[1]</code> is 6.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= value &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= index &lt;= n - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Math + Greedy

<!-- thinking:start -->

> **Thinking**
>
> Both $n$ and $\textit{value}$ can be up to $10^9$, so we cannot construct the array element by element and instead need closed-form bounds for the endpoints.
>
> Consecutive elements satisfy $\textit{nums}[i] < \textit{nums}[i + 1]$ and $\gcd(\textit{nums}[i], \textit{nums}[i + 1]) > 1$. Since the greatest common divisor divides their difference, any two adjacent elements must differ by at least $2$. Moreover, any element directly adjacent to $\textit{nums}[\textit{index}] = \textit{value}$ must share a factor greater than $1$ with $\textit{value}$, so their difference is at least the smallest prime factor $p$ of $\textit{value}$.
>
> Observe that whether $\textit{value}$ is even ($p = 2$) or odd ($p$ is also odd), both $\textit{value} - p$ and $\textit{value} + p$ are always even. Once we land on an even number, every remaining step only needs to change by $2$ so that all subsequent numbers stay even (giving adjacent pairs a $\gcd$ of at least $2$), achieving the theoretical minimum step size. This lets us directly verify whether positive integers fit on the left and compute the minimum possible value of $\textit{nums}[n - 1]$ on the right.

<!-- thinking:end -->

If $\textit{value} = 1$, since $\gcd(1, x) = 1$ for any positive integer $x$, no valid array exists and we return $-1$.

When $\textit{value} > 1$, we find the smallest prime factor $p$ of $\textit{value}$ by trial division. Because the greatest common divisor of any two adjacent elements is greater than $1$, the difference between any two adjacent elements is at least $2$, and the difference between $\textit{nums}[\textit{index}] = \textit{value}$ and its immediate neighbors is at least $p$. Since $\textit{value}$ and its smallest prime factor $p$ always have the same parity, both $\textit{value} - p$ and $\textit{value} + p$ are even. Thus, all other adjacent elements can simply differ by $2$ while remaining even (which guarantees their $\gcd$ is at least $2 > 1$):

- To the left of $\textit{index}$, to make $\textit{nums}[0]$ as large as possible, we set $\textit{nums}[\textit{index} - 1] = \textit{value} - p$ and decrease by $2$ at each step to the left, giving $\textit{nums}[0] = \textit{value} - p - (\textit{index} - 1) \times 2$. If this value is at most $0$ (note that when $\textit{index} = 0$, $\textit{value} - p + 2 \ge 2 > 0$ always holds), we cannot place valid positive integers on the left, so we return $-1$.
- To the right of $\textit{index}$, if $\textit{index} = n - 1$, the maximum element is simply $\textit{value}$. Otherwise, we set $\textit{nums}[\textit{index} + 1] = \textit{value} + p$ and increase by $2$ at each subsequent step, yielding the minimum possible value $\textit{value} + p + (n - 2 - \textit{index}) \times 2$ for $\textit{nums}[n - 1]$.

The time complexity is $O(\sqrt{\textit{value}})$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumMaxValue(self, n: int, index: int, value: int) -> int:
        if value == 1:
            return -1
        p = value
        for x in range(2, isqrt(value) + 1):
            if value % x == 0:
                p = x
                break
        if value - p - (index - 1) * 2 <= 0:
            return -1
        if index == n - 1:
            return value
        return value + p + (n - 2 - index) * 2
```

#### Java

```java
class Solution {
    public long minimumMaxValue(int n, int index, int value) {
        if (value == 1) {
            return -1;
        }

        long p = value;
        for (long x = 2; x * x <= value; x++) {
            if (value % x == 0) {
                p = x;
                break;
            }
        }

        if ((long) value - p - ((long) index - 1) * 2 <= 0) {
            return -1;
        }
        if (index == n - 1) {
            return value;
        }
        return (long) value + p + ((long) n - 2 - index) * 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long minimumMaxValue(int n, int index, int value) {
        if (value == 1) {
            return -1;
        }

        long long p = value;
        for (long long x = 2; x * x <= value; ++x) {
            if (value % x == 0) {
                p = x;
                break;
            }
        }

        if ((long long) value - p - ((long long) index - 1) * 2 <= 0) {
            return -1;
        }
        if (index == n - 1) {
            return value;
        }
        return (long long) value + p + ((long long) n - 2 - index) * 2;
    }
};
```

#### Go

```go
func minimumMaxValue(n int, index int, value int) int64 {
	if value == 1 {
		return -1
	}

	p := int64(value)
	for x := int64(2); x*x <= int64(value); x++ {
		if int64(value)%x == 0 {
			p = x
			break
		}
	}

	if int64(value)-p-(int64(index)-1)*2 <= 0 {
		return -1
	}
	if index == n-1 {
		return int64(value)
	}
	return int64(value) + p + (int64(n)-2-int64(index))*2
}
```

#### TypeScript

```ts
function minimumMaxValue(n: number, index: number, value: number): number {
    if (value === 1) {
        return -1;
    }

    let p = value;
    for (let x = 2; x * x <= value; x++) {
        if (value % x === 0) {
            p = x;
            break;
        }
    }

    if (value - p - (index - 1) * 2 <= 0) {
        return -1;
    }
    if (index === n - 1) {
        return value;
    }
    return value + p + (n - 2 - index) * 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
