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

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def flowerGame(self, n: int, m: int) -> int:
        count = (n + 1) // 2
        tol = (m + 1) // 2
        ecount = n // 2
        etol = m // 2
        return count * etol + ecount * tol

```

```java
class Solution {
    public long flowerGame(int n, int m) {
        long count = (n + 1) / 2;
        long tol = (m + 1) / 2;
        long ecount = n / 2;
        long etol = m / 2;
        return (count * etol + ecount * tol);
    }
}
```

```cpp
class Solution {
public:
    long long flowerGame(int n, int m) {
        long long count = (n + 1) / 2;
        long long tol = (m + 1) / 2;
        long long ecount = n / 2;
        long long etol = m / 2;
        return (count * etol + ecount * tol);
    }
};
```

```go
func flowerGame(n int, m int) int64 {
    count := int64((n + 1) / 2)
    tol := int64((m + 1) / 2)
    ecount := int64(n / 2)
    etol := int64(m / 2)
    return count*etol + ecount*tol
}
```

<!-- tabs:end -->

<!-- end -->
