---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3687.Library%20Late%20Fee%20Calculator/README_EN.md
tags:
    - Array
    - Simulation
---

<!-- problem:start -->

# [3687. Library Late Fee Calculator 🔒](https://leetcode.com/problems/library-late-fee-calculator)

[中文文档](/solution/3600-3699/3687.Library%20Late%20Fee%20Calculator/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>daysLate</code> where <code>daysLate[i]</code> indicates how many days late the <code>i<sup>th</sup></code> book was returned.</p>

<p>The penalty is calculated as follows:</p>

<ul>
	<li>If <code>daysLate[i] == 1</code>, penalty is 1.</li>
	<li>If <code>2 &lt;= daysLate[i] &lt;= 5</code>, penalty is <code>2 * daysLate[i]</code>.</li>
	<li>If <code>daysLate[i] &gt; 5</code>, penalty is <code>3 * daysLate[i]</code>.</li>
</ul>

<p>Return the total penalty for all books.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">daysLate = [5,1,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">32</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>daysLate[0] = 5</code>: Penalty is <code>2 * daysLate[0] = 2 * 5 = 10</code>.</li>
	<li><code>daysLate[1] = 1</code>: Penalty is <code>1</code>.</li>
	<li><code>daysLate[2] = 7</code>: Penalty is <code>3 * daysLate[2] = 3 * 7 = 21</code>.</li>
	<li>Thus, the total penalty is <code>10 + 1 + 21 = 32</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">daysLate = [1,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>daysLate[0] = 1</code>: Penalty is <code>1</code>.</li>
	<li><code>daysLate[1] = 1</code>: Penalty is <code>1</code>.</li>
	<li>Thus, the total penalty is <code>1 + 1 = 2</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= daysLate.length &lt;= 100</code></li>
	<li><code>1 &lt;= daysLate[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We define a function $\text{f}(x)$ to calculate the late fee for each book:

$$
\text{f}(x) = \begin{cases}
1 & x = 1 \\
2x & 2 \leq x \leq 5 \\
3x & x > 5
\end{cases}
$$

Then, for each element $x$ in the array $\textit{daysLate}$, we compute $\text{f}(x)$ and sum them up to get the total late fee.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{daysLate}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def lateFee(self, daysLate: List[int]) -> int:
        def f(x: int) -> int:
            if x == 1:
                return 1
            if x > 5:
                return 3 * x
            return 2 * x

        return sum(f(x) for x in daysLate)
```

#### Java

```java
class Solution {
    public int lateFee(int[] daysLate) {
        IntUnaryOperator f = x -> {
            if (x == 1) {
                return 1;
            } else if (x > 5) {
                return 3 * x;
            } else {
                return 2 * x;
            }
        };

        int ans = 0;
        for (int x : daysLate) {
            ans += f.applyAsInt(x);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int lateFee(vector<int>& daysLate) {
        auto f = [](int x) {
            if (x == 1) {
                return 1;
            } else if (x > 5) {
                return 3 * x;
            } else {
                return 2 * x;
            }
        };

        int ans = 0;
        for (int x : daysLate) {
            ans += f(x);
        }
        return ans;
    }
};
```

#### Go

```go
func lateFee(daysLate []int) (ans int) {
	f := func(x int) int {
		if x == 1 {
			return 1
		} else if x > 5 {
			return 3 * x
		}
		return 2 * x
	}
	for _, x := range daysLate {
		ans += f(x)
	}
	return
}
```

#### TypeScript

```ts
function lateFee(daysLate: number[]): number {
    const f = (x: number): number => {
        if (x === 1) {
            return 1;
        } else if (x > 5) {
            return 3 * x;
        }
        return 2 * x;
    };
    return daysLate.reduce((acc, days) => acc + f(days), 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
