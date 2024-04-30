# [292. Nim Game](https://leetcode.com/problems/nim-game)

[中文文档](/solution/0200-0299/0292.Nim%20Game/README.md)

<!-- tags:Brainteaser,Math,Game Theory -->

## Description

<p>You are playing the following Nim Game with your friend:</p>

<ul>
	<li>Initially, there is a heap of stones on the table.</li>
	<li>You and your friend will alternate taking turns, and <strong>you go first</strong>.</li>
	<li>On each turn, the person whose turn it is will remove 1 to 3 stones from the heap.</li>
	<li>The one who removes the last stone is the winner.</li>
</ul>

<p>Given <code>n</code>, the number of stones in the heap, return <code>true</code><em> if you can win the game assuming both you and your friend play optimally, otherwise return </em><code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> false
<strong>Explanation:</strong> These are the possible outcomes:
1. You remove 1 stone. Your friend removes 3 stones, including the last stone. Your friend wins.
2. You remove 2 stones. Your friend removes 2 stones, including the last stone. Your friend wins.
3. You remove 3 stones. Your friend removes the last stone. Your friend wins.
In all outcomes, your friend wins.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

## Solutions

### Solution 1: Finding the Pattern

The first player who gets a multiple of $4$ (i.e., $n$ can be divided by $4$) will lose the game.

Proof:

1. When $n \lt 4$, the first player can directly take all the stones, so the first player will win the game.
1. When $n = 4$, no matter whether the first player chooses $1, 2, 3$, the second player can always choose the remaining number, so the first player will lose the game.
1. When $4 \lt n \lt 8$, i.e., $n = 5, 6, 7$, the first player can correspondingly reduce the number to $4$, then the "death number" $4$ is given to the second player, and the second player will lose the game.
1. When $n = 8$, no matter whether the first player chooses $1, 2, 3$, it will leave a number between $4 \lt n \lt 8$ to the second player, so the first player will lose the game.
1. ...
1. By induction, when a player gets the number $n$, and $n$ can be divided by $4$, he will lose the game, otherwise, he will win the game.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def canWinNim(self, n: int) -> bool:
        return n % 4 != 0
```

```java
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
```

```cpp
class Solution {
public:
    bool canWinNim(int n) {
        return n % 4 != 0;
    }
};
```

```go
func canWinNim(n int) bool {
	return n%4 != 0
}
```

```ts
function canWinNim(n: number): boolean {
    return n % 4 != 0;
}
```

```rust
impl Solution {
    pub fn can_win_nim(n: i32) -> bool {
        n % 4 != 0
    }
}
```

<!-- tabs:end -->

<!-- end -->
