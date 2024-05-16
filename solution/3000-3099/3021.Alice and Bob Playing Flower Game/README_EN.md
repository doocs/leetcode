---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3021.Alice%20and%20Bob%20Playing%20Flower%20Game/README_EN.md
rating: 1581
source: Weekly Contest 382 Q3
tags:
    - Math
---

<!-- problem:start -->

# [3021. Alice and Bob Playing Flower Game](https://leetcode.com/problems/alice-and-bob-playing-flower-game)

[中文文档](/solution/3000-3099/3021.Alice%20and%20Bob%20Playing%20Flower%20Game/README.md)

## Description

<p>Alice and Bob are playing a turn-based game on a circular field surrounded by flowers. The circle represents the field, and there are <code>x</code> flowers in the clockwise direction between Alice and Bob, and <code>y</code> flowers in the anti-clockwise direction between them.</p>

<p>The game proceeds as follows:</p>

<ol>
	<li>Alice takes the first turn.</li>
	<li>In each turn, a player must choose either the clockwise or anti-clockwise direction and pick one flower from that side.</li>
	<li>At the end of the turn, if there are no flowers left at all, the <strong>current</strong> player captures their opponent and wins the game.</li>
</ol>

<p>Given two integers, <code>n</code> and <code>m</code>, the task is to compute the number of possible pairs <code>(x, y)</code> that satisfy the conditions:</p>

<ul>
	<li>Alice must win the game according to the described rules.</li>
	<li>The number of flowers <code>x</code> in the clockwise direction must be in the range <code>[1,n]</code>.</li>
	<li>The number of flowers <code>y</code> in the anti-clockwise direction must be in the range <code>[1,m]</code>.</li>
</ul>

<p>Return <em>the number of possible pairs</em> <code>(x, y)</code> <em>that satisfy the conditions mentioned in the statement</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 3, m = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> The following pairs satisfy conditions described in the statement: (1,2), (3,2), (2,1).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1, m = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> No pairs satisfy the conditions described in the statement.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, m &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

According to the problem description, in each move, the player will choose to move in a clockwise or counterclockwise direction and then pick a flower. Since Alice moves first, when $x + y$ is odd, Alice will definitely win the game.

Therefore, the number of flowers $x$ and $y$ meet the following conditions:

1. $x + y$ is odd;
2. $1 \le x \le n$;
3. $1 \le y \le m$.

If $x$ is odd, $y$ must be even. At this time, the number of values of $x$ is $\lceil \frac{n}{2} \rceil$, the number of values of $y$ is $\lfloor \frac{m}{2} \rfloor$, so the number of pairs that meet the conditions is $\lceil \frac{n}{2} \rceil \times \lfloor \frac{m}{2} \rfloor$.

If $x$ is even, $y$ must be odd. At this time, the number of values of $x$ is $\lfloor \frac{n}{2} \rfloor$, the number of values of $y$ is $\lceil \frac{m}{2} \rceil$, so the number of pairs that meet the conditions is $\lfloor \frac{n}{2} \rfloor \times \lceil \frac{m}{2} \rceil$.

Therefore, the number of pairs that meet the conditions is $\lceil \frac{n}{2} \rceil \times \lfloor \frac{m}{2} \rfloor + \lfloor \frac{n}{2} \rfloor \times \lceil \frac{m}{2} \rceil$, which is $\lfloor \frac{n + 1}{2} \rfloor \times \lfloor \frac{m}{2} \rfloor + \lfloor \frac{n}{2} \rfloor \times \lfloor \frac{m + 1}{2} \rfloor$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def flowerGame(self, n: int, m: int) -> int:
        a1 = (n + 1) // 2
        b1 = (m + 1) // 2
        a2 = n // 2
        b2 = m // 2
        return a1 * b2 + a2 * b1
```

```java
class Solution {
    public long flowerGame(int n, int m) {
        long a1 = (n + 1) / 2;
        long b1 = (m + 1) / 2;
        long a2 = n / 2;
        long b2 = m / 2;
        return a1 * b2 + a2 * b1;
    }
}
```

```cpp
class Solution {
public:
    long long flowerGame(int n, int m) {
        long long a1 = (n + 1) / 2;
        long long b1 = (m + 1) / 2;
        long long a2 = n / 2;
        long long b2 = m / 2;
        return a1 * b2 + a2 * b1;
    }
};
```

```go
func flowerGame(n int, m int) int64 {
	a1, b1 := (n+1)/2, (m+1)/2
	a2, b2 := n/2, m/2
	return int64(a1*b2 + a2*b1)
}
```

```ts
function flowerGame(n: number, m: number): number {
    const [a1, b1] = [(n + 1) >> 1, (m + 1) >> 1];
    const [a2, b2] = [n >> 1, m >> 1];
    return a1 * b2 + a2 * b1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Mathematics (Optimized)

The result obtained from Solution 1 is $\lfloor \frac{n + 1}{2} \rfloor \times \lfloor \frac{m}{2} \rfloor + \lfloor \frac{n}{2} \rfloor \times \lfloor \frac{m + 1}{2} \rfloor$.

If both $n$ and $m$ are odd, then the result is $\frac{n + 1}{2} \times \frac{m - 1}{2} + \frac{n - 1}{2} \times \frac{m + 1}{2}$, which is $\frac{n \times m - 1}{2}$.

If both $n$ and $m$ are even, then the result is $\frac{n}{2} \times \frac{m}{2} + \frac{n}{2} \times \frac{m}{2}$, which is $\frac{n \times m}{2}$.

If $n$ is odd and $m$ is even, then the result is $\frac{n + 1}{2} \times \frac{m}{2} + \frac{n - 1}{2} \times \frac{m}{2}$, which is $\frac{n \times m}{2}$.

If $n$ is even and $m$ is odd, then the result is $\frac{n}{2} \times \frac{m - 1}{2} + \frac{n}{2} \times \frac{m + 1}{2}$, which is $\frac{n \times m}{2}$.

The above four cases can be combined into $\lfloor \frac{n \times m}{2} \rfloor$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def flowerGame(self, n: int, m: int) -> int:
        return (n * m) // 2
```

```java
class Solution {
    public long flowerGame(int n, int m) {
        return ((long) n * m) / 2;
    }
}
```

```cpp
class Solution {
public:
    long long flowerGame(int n, int m) {
        return ((long long) n * m) / 2;
    }
};
```

```go
func flowerGame(n int, m int) int64 {
	return int64((n * m) / 2)
}
```

```ts
function flowerGame(n: number, m: number): number {
    return Number(((BigInt(n) * BigInt(m)) / 2n) | 0n);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
