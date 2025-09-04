---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0808.Soup%20Servings/README_EN.md
tags:
    - Math
    - Dynamic Programming
    - Probability and Statistics
---

<!-- problem:start -->

# [808. Soup Servings](https://leetcode.com/problems/soup-servings)

[中文文档](/solution/0800-0899/0808.Soup%20Servings/README.md)

## Description

<!-- description:start -->

<p>You have two soups, <strong>A</strong> and <strong>B</strong>, each starting with <code>n</code> mL. On every turn, one of the following four serving operations is chosen <em>at random</em>, each with probability <code>0.25</code> <strong>independent</strong> of all previous turns:</p>

<ul>
	<li>pour 100 mL from type A and 0 mL from type B</li>
	<li>pour 75 mL from type A and 25 mL from type B</li>
	<li>pour 50 mL from type A and 50 mL from type B</li>
	<li>pour 25 mL from type A and 75 mL from type B</li>
</ul>

<p><strong>Note:</strong></p>

<ul>
	<li>There is no operation that pours 0 mL from A and 100 mL from B.</li>
	<li>The amounts from A and B are poured <em>simultaneously</em> during the turn.</li>
	<li>If an operation asks you to pour <strong>more than</strong> you have left of a soup, pour all that remains of that soup.</li>
</ul>

<p>The process stops immediately after any turn in which <em>one of the soups</em> is used up.</p>

<p>Return the probability that A is used up <em>before</em> B, plus half the probability that both soups are used up in the<strong> same turn</strong>. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 50
<strong>Output:</strong> 0.62500
<strong>Explanation:</strong> 
If we perform either of the first two serving operations, soup A will become empty first.
If we perform the third operation, A and B will become empty at the same time.
If we perform the fourth operation, B will become empty first.
So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 100
<strong>Output:</strong> 0.71875
<strong>Explanation:</strong> 
If we perform the first serving operation, soup A will become empty first.
If we perform the second serving operations, A will become empty on performing operation [1, 2, 3], and both A and B become empty on performing operation 4.
If we perform the third operation, A will become empty on performing operation [1, 2], and both A and B become empty on performing operation 3.
If we perform the fourth operation, A will become empty on performing operation 1, and both A and B become empty on performing operation 2.
So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.71875.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoization Search

In this problem, since each operation is a multiple of $25$, we can consider every $25ml$ of soup as one unit. This reduces the data scale to $\left \lceil \frac{n}{25} \right \rceil$.

We design a function $dfs(i, j)$, which represents the probability result when there are $i$ units of soup $A$ and $j$ units of soup $B$ remaining.

When $i \leq 0$ and $j \leq 0$, it means both soups are finished, and we should return $0.5$. When $i \leq 0$, it means soup $A$ is finished first, and we should return $1$. When $j \leq 0$, it means soup $B$ is finished first, and we should return $0$.

Next, for each operation, we have four choices:

-   Take $4$ units from soup $A$ and $0$ units from soup $B$;
-   Take $3$ units from soup $A$ and $1$ unit from soup $B$;
-   Take $2$ units from soup $A$ and $2$ units from soup $B$;
-   Take $1$ unit from soup $A$ and $3$ units from soup $B$.

Each choice has a probability of $0.25$, so we can derive:

$$
dfs(i, j) = 0.25 \times (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3))
$$

We use memoization to store the results of the function.

Additionally, we find that when $n=4800$, the result is $0.999994994426$, and the required precision is $10^{-5}$. As $n$ increases, the result gets closer to $1$. Therefore, when $n \gt 4800$, we can directly return $1$.

The time complexity is $O(C^2)$, and the space complexity is $O(C^2)$. In this problem, $C=200$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def soupServings(self, n: int) -> float:
        @cache
        def dfs(i: int, j: int) -> float:
            if i <= 0 and j <= 0:
                return 0.5
            if i <= 0:
                return 1
            if j <= 0:
                return 0
            return 0.25 * (
                dfs(i - 4, j)
                + dfs(i - 3, j - 1)
                + dfs(i - 2, j - 2)
                + dfs(i - 1, j - 3)
            )

        return 1 if n > 4800 else dfs((n + 24) // 25, (n + 24) // 25)
```

#### Java

```java
class Solution {
    private double[][] f = new double[200][200];

    public double soupServings(int n) {
        return n > 4800 ? 1 : dfs((n + 24) / 25, (n + 24) / 25);
    }

    private double dfs(int i, int j) {
        if (i <= 0 && j <= 0) {
            return 0.5;
        }
        if (i <= 0) {
            return 1.0;
        }
        if (j <= 0) {
            return 0;
        }
        if (f[i][j] > 0) {
            return f[i][j];
        }
        double ans
            = 0.25 * (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3));
        f[i][j] = ans;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double soupServings(int n) {
        double f[200][200] = {0.0};
        auto dfs = [&](this auto&& dfs, int i, int j) -> double {
            if (i <= 0 && j <= 0) return 0.5;
            if (i <= 0) return 1;
            if (j <= 0) return 0;
            if (f[i][j] > 0) return f[i][j];
            double ans = 0.25 * (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3));
            f[i][j] = ans;
            return ans;
        };
        return n > 4800 ? 1 : dfs((n + 24) / 25, (n + 24) / 25);
    }
};
```

#### Go

```go
func soupServings(n int) float64 {
	if n > 4800 {
		return 1
	}
	f := [200][200]float64{}
	var dfs func(i, j int) float64
	dfs = func(i, j int) float64 {
		if i <= 0 && j <= 0 {
			return 0.5
		}
		if i <= 0 {
			return 1.0
		}
		if j <= 0 {
			return 0
		}
		if f[i][j] > 0 {
			return f[i][j]
		}
		ans := 0.25 * (dfs(i-4, j) + dfs(i-3, j-1) + dfs(i-2, j-2) + dfs(i-1, j-3))
		f[i][j] = ans
		return ans
	}
	return dfs((n+24)/25, (n+24)/25)
}
```

#### TypeScript

```ts
function soupServings(n: number): number {
    const f = Array.from({ length: 200 }, () => Array(200).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (i <= 0 && j <= 0) {
            return 0.5;
        }
        if (i <= 0) {
            return 1;
        }
        if (j <= 0) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        f[i][j] =
            0.25 * (dfs(i - 4, j) + dfs(i - 3, j - 1) + dfs(i - 2, j - 2) + dfs(i - 1, j - 3));
        return f[i][j];
    };
    return n >= 4800 ? 1 : dfs(Math.ceil(n / 25), Math.ceil(n / 25));
}
```

#### Rust

```rust
impl Solution {
    pub fn soup_servings(n: i32) -> f64 {
        if n > 4800 {
            return 1.0;
        }
        Self::dfs((n + 24) / 25, (n + 24) / 25)
    }

    fn dfs(i: i32, j: i32) -> f64 {
        static mut F: [[f64; 200]; 200] = [[0.0; 200]; 200];

        unsafe {
            if i <= 0 && j <= 0 {
                return 0.5;
            }
            if i <= 0 {
                return 1.0;
            }
            if j <= 0 {
                return 0.0;
            }
            if F[i as usize][j as usize] > 0.0 {
                return F[i as usize][j as usize];
            }

            let ans = 0.25 * (Self::dfs(i - 4, j) + Self::dfs(i - 3, j - 1) + Self::dfs(i - 2, j - 2) + Self::dfs(i - 1, j - 3));
            F[i as usize][j as usize] = ans;
            ans
        }
    }
}
```

#### C#

```cs
public class Solution {
    private double[,] f = new double[200, 200];

    public double SoupServings(int n) {
        if (n > 4800) {
            return 1.0;
        }

        return Dfs((n + 24) / 25, (n + 24) / 25);
    }

    private double Dfs(int i, int j) {
        if (i <= 0 && j <= 0) {
            return 0.5;
        }
        if (i <= 0) {
            return 1.0;
        }
        if (j <= 0) {
            return 0.0;
        }
        if (f[i, j] > 0) {
            return f[i, j];
        }

        double ans = 0.25 * (Dfs(i - 4, j) + Dfs(i - 3, j - 1) + Dfs(i - 2, j - 2) + Dfs(i - 1, j - 3));
        f[i, j] = ans;
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
