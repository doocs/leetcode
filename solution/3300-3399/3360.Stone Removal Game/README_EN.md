---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3360.Stone%20Removal%20Game/README_EN.md
rating: 1267
source: Biweekly Contest 144 Q1
tags:
    - Math
    - Simulation
---

<!-- problem:start -->

# [3360. Stone Removal Game](https://leetcode.com/problems/stone-removal-game)

[中文文档](/solution/3300-3399/3360.Stone%20Removal%20Game/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob are playing a game where they take turns removing stones from a pile, with <em>Alice going first</em>.</p>

<ul>
	<li>Alice starts by removing <strong>exactly</strong> 10 stones on her first turn.</li>
	<li>For each subsequent turn, each player removes <strong>exactly</strong> 1 fewer<strong> </strong>stone<strong> </strong>than the previous opponent.</li>
</ul>

<p>The player who cannot make a move loses the game.</p>

<p>Given a positive integer <code>n</code>, return <code>true</code> if Alice wins the game and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 12</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Alice removes 10 stones on her first turn, leaving 2 stones for Bob.</li>
	<li>Bob cannot remove 9 stones, so Alice wins.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Alice cannot remove 10 stones, so Alice loses.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 50</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We simulate the game process according to the problem description until the game can no longer continue.

Specifically, we maintain two variables $x$ and $k$, representing the current number of stones that can be removed and the number of operations performed, respectively. Initially, $x = 10$ and $k = 0$.

In each round of operations, if the current number of stones that can be removed $x$ does not exceed the remaining number of stones $n$, we remove $x$ stones, decrease $x$ by $1$, and increase $k$ by $1$. Otherwise, we cannot perform the operation, and the game ends.

Finally, we check the parity of $k$. If $k$ is odd, Alice wins the game; otherwise, Bob wins the game.

The time complexity is $O(\sqrt{n})$, where $n$ is the number of stones. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canAliceWin(self, n: int) -> bool:
        x, k = 10, 0
        while n >= x:
            n -= x
            x -= 1
            k += 1
        return k % 2 == 1
```

#### Java

```java
class Solution {
    public boolean canAliceWin(int n) {
        int x = 10, k = 0;
        while (n >= x) {
            n -= x;
            --x;
            ++k;
        }
        return k % 2 == 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canAliceWin(int n) {
        int x = 10, k = 0;
        while (n >= x) {
            n -= x;
            --x;
            ++k;
        }
        return k % 2;
    }
};
```

#### Go

```go
func canAliceWin(n int) bool {
	x, k := 10, 0
	for n >= x {
		n -= x
		x--
		k++
	}
	return k%2 == 1
}
```

#### TypeScript

```ts
function canAliceWin(n: number): boolean {
    let [x, k] = [10, 0];
    while (n >= x) {
        n -= x;
        --x;
        ++k;
    }
    return k % 2 === 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
