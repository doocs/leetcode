---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1025.Divisor%20Game/README_EN.md
rating: 1435
source: Weekly Contest 132 Q1
tags:
    - Brainteaser
    - Math
    - Dynamic Programming
    - Game Theory
---

<!-- problem:start -->

# [1025. Divisor Game](https://leetcode.com/problems/divisor-game)

[中文文档](/solution/1000-1099/1025.Divisor%20Game/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob take turns playing a game, with Alice starting first.</p>

<p>Initially, there is a number <code>n</code> on the chalkboard. On each player&#39;s turn, that player makes a move consisting of:</p>

<ul>
	<li>Choosing any integer <code>x</code> with <code>0 &lt; x &lt; n</code> and <code>n % x == 0</code>.</li>
	<li>Replacing the number <code>n</code> on the chalkboard with <code>n - x</code>.</li>
</ul>

<p>Also, if a player cannot make a move, they lose the game.</p>

<p>Return <code>true</code> <em>if and only if Alice wins the game, assuming both players play optimally</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> true
<strong>Explanation:</strong> Alice chooses 1, and Bob has no more moves.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> Alice chooses 1, Bob chooses 1, and Alice has no more moves.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematical Induction

- When $n=1$, the first player loses.
- When $n=2$, the first player takes $1$, leaving $1$, the second player loses, the first player wins.
- When $n=3$, the first player takes $1$, leaving $2$, the second player wins, the first player loses.
- When $n=4$, the first player takes $1$, leaving $3$, the second player loses, the first player wins.
- ...

We conjecture that when $n$ is odd, the first player loses; when $n$ is even, the first player wins.

Proof:

1. If $n=1$ or $n=2$, the conclusion holds.
1. If $n \gt 2$, assume that the conclusion holds when $n \le k$, then when $n=k+1$:
    - If $k+1$ is odd, since $x$ is a divisor of $k+1$, then $x$ can only be odd, so $k+1-x$ is even, the second player wins, the first player loses.
    - If $k+1$ is even, now $x$ can be either odd $1$ or even. If $x$ is odd, then $k+1-x$ is odd, the second player loses, the first player wins.

In conclusion, when $n$ is odd, the first player loses; when $n$ is even, the first player wins. The conclusion is correct.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def divisorGame(self, n: int) -> bool:
        return n % 2 == 0
```

#### Java

```java
class Solution {
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool divisorGame(int n) {
        return n % 2 == 0;
    }
};
```

#### Go

```go
func divisorGame(n int) bool {
	return n%2 == 0
}
```

#### JavaScript

```js
var divisorGame = function (n) {
    return n % 2 === 0;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
