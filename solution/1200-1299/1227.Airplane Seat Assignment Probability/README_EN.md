# [1227. Airplane Seat Assignment Probability](https://leetcode.com/problems/airplane-seat-assignment-probability)

[中文文档](/solution/1200-1299/1227.Airplane%20Seat%20Assignment%20Probability/README.md)

<!-- tags:Brainteaser,Math,Dynamic Programming,Probability and Statistics -->

<!-- difficulty:Medium -->

## Description

<p><code>n</code> passengers board an airplane with exactly <code>n</code> seats. The first passenger has lost the ticket and picks a seat randomly. But after that, the rest of the passengers will:</p>

<ul>
	<li>Take their own seat if it is still available, and</li>
	<li>Pick other seats randomly when they find their seat occupied</li>
</ul>

<p>Return <em>the probability that the </em><code>n<sup>th</sup></code><em> person gets his own seat</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1.00000
<strong>Explanation: </strong>The first person can only get the first seat.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 0.50000
<strong>Explanation: </strong>The second person has a probability of 0.5 to get the second seat (when first person gets the first seat).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Mathematics

Let $f(n)$ represent the probability that the $n$th passenger will sit in their own seat when there are $n$ passengers boarding. Consider from the simplest case:

-   When $n=1$, there is only 1 passenger and 1 seat, so the first passenger can only sit in the first seat, $f(1)=1$;

-   When $n=2$, there are 2 seats, each seat has a probability of 0.5 to be chosen by the first passenger. After the first passenger chooses a seat, the second passenger can only choose the remaining seat, so the second passenger has a probability of 0.5 to sit in their own seat, $f(2)=0.5$.

When $n>2$, how to calculate the value of $f(n)$? Consider the seat chosen by the first passenger, there are three cases.

-   The first passenger has a probability of $\frac{1}{n}$ to choose the first seat, then all passengers can sit in their own seats, so the probability of the $n$th passenger sitting in their own seat is 1.0.

-   The first passenger has a probability of $\frac{1}{n}$ to choose the $n$th seat, then the second to the $(n-1)$th passengers can sit in their own seats, the $n$th passenger can only sit in the first seat, so the probability of the $n$th passenger sitting in their own seat is 0.0.

-   The first passenger has a probability of $\frac{n-2}{n}$ to choose the remaining seats, each seat has a probability of $\frac{1}{n}$ to be chosen.
    Suppose the first passenger chooses the $i$th seat, where $2 \le i \le n-1$, then the second to the $(i-1)$th passengers can sit in their own seats, the seats of the $i$th to the $n$th passengers are uncertain, the $i$th passenger will randomly choose from the remaining $n-(i-1)=n-i+1$ seats (including the first seat and the $(i+1)$th to the $n$th seats). Since the number of remaining passengers and seats is $n-i+1$, and 1 passenger will randomly choose a seat, the problem size is reduced from $n$ to $n-i+1$.

Combining the above three cases, we can get the recursive formula of $f(n)$:

$$
\begin{aligned}
f(n) &= \frac{1}{n} \times 1.0 + \frac{1}{n} \times 0.0 + \frac{1}{n} \times \sum_{i=2}^{n-1} f(n-i+1) \\
&= \frac{1}{n}(1.0+\sum_{i=2}^{n-1} f(n-i+1))
\end{aligned}
$$

In the above recursive formula, there are $n-2$ values of $i$, since the number of values of $i$ must be a non-negative integer, so the above recursive formula only holds when $n-2 \ge 0$ i.e., $n \ge 2$.

If you directly use the above recursive formula to calculate the value of $f(n)$, the time complexity is $O(n^2)$, which cannot pass all test cases, so it needs to be optimized.

Replace $n$ with $n-1$ in the above recursive formula, you can get the recursive formula:

$$
f(n-1) = \frac{1}{n-1}(1.0+\sum_{i=2}^{n-2} f(n-i))
$$

In the above recursive formula, there are $n-3$ values of $i$, and the above recursive formula only holds when $n-3 \ge 0$ i.e., $n \ge 3$.

When $n \ge 3$, the above two recursive formulas can be written as follows:

$$
\begin{aligned}
n \times f(n) &= 1.0+\sum_{i=2}^{n-1} f(n-i+1) \\
(n-1) \times f(n-1) &= 1.0+\sum_{i=2}^{n-2} f(n-i)
\end{aligned}
$$

Subtract the above two formulas:

$$
\begin{aligned}
&~~~~~ n \times f(n) - (n-1) \times f(n-1) \\
&= (1.0+\sum_{i=2}^{n-1} f(n-i+1)) - (1.0+\sum_{i=2}^{n-2} f(n-i)) \\
&= \sum_{i=2}^{n-1} f(n-i+1) - \sum_{i=2}^{n-2} f(n-i) \\
&= f(2)+f(3)+...+f(n-1) - (f(2)+f(3)+...+f(n-2)) \\
&= f(n-1)
\end{aligned}
$$

After simplification, we get the simplified recursive formula:

$$
\begin{aligned}
n \times f(n) &= n \times f(n-1) \\
f(n) &= f(n-1)
\end{aligned}
$$

Since we know that $f(1)=1$ and $f(2)=0.5$, therefore when $n \ge 3$, according to $f(n) = f(n-1)$, we know that for any positive integer $n$, $f(n)=0.5$. And since $f(2)=0.5$, therefore when $n \ge 2$, for any positive integer $n$, $f(n)=0.5$.

From this, we can get the result of $f(n)$:

$$
f(n) = \begin{cases}
1.0, & n = 1 \\
0.5, & n \ge 2
\end{cases}
$$

<!-- tabs:start -->

```python
class Solution:
    def nthPersonGetsNthSeat(self, n: int) -> float:
        return 1 if n == 1 else 0.5
```

```java
class Solution {
    public double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1 : .5;
    }
}
```

```cpp
class Solution {
public:
    double nthPersonGetsNthSeat(int n) {
        return n == 1 ? 1 : .5;
    }
};
```

```go
func nthPersonGetsNthSeat(n int) float64 {
	if n == 1 {
		return 1
	}
	return .5
}
```

<!-- tabs:end -->

<!-- end -->
