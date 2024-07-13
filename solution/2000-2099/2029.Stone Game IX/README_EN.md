---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2029.Stone%20Game%20IX/README_EN.md
rating: 2277
source: Weekly Contest 261 Q3
tags:
    - Greedy
    - Array
    - Math
    - Counting
    - Game Theory
---

<!-- problem:start -->

# [2029. Stone Game IX](https://leetcode.com/problems/stone-game-ix)

[中文文档](/solution/2000-2099/2029.Stone%20Game%20IX/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob continue their games with stones. There is a row of n stones, and each stone has an associated value. You are given an integer array <code>stones</code>, where <code>stones[i]</code> is the <strong>value</strong> of the <code>i<sup>th</sup></code> stone.</p>

<p>Alice and Bob take turns, with <strong>Alice</strong> starting first. On each turn, the player may remove any stone from <code>stones</code>. The player who removes a stone <strong>loses</strong> if the <strong>sum</strong> of the values of <strong>all removed stones</strong> is divisible by <code>3</code>. Bob will win automatically if there are no remaining stones (even if it is Alice&#39;s turn).</p>

<p>Assuming both players play <strong>optimally</strong>, return <code>true</code> <em>if Alice wins and</em> <code>false</code> <em>if Bob wins</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stones = [2,1]
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;The game will be played as follows:
- Turn 1: Alice can remove either stone.
- Turn 2: Bob removes the remaining stone. 
The sum of the removed stones is 1 + 2 = 3 and is divisible by 3. Therefore, Bob loses and Alice wins the game.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stones = [2]
<strong>Output:</strong> false
<strong>Explanation:</strong>&nbsp;Alice will remove the only stone, and the sum of the values on the removed stones is 2. 
Since all the stones are removed and the sum of values is not divisible by 3, Bob wins the game.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> stones = [5,1,2,4,3]
<strong>Output:</strong> false
<strong>Explanation:</strong> Bob will always win. One possible way for Bob to win is shown below:
- Turn 1: Alice can remove the second stone with value 1. Sum of removed stones = 1.
- Turn 2: Bob removes the fifth stone with value 3. Sum of removed stones = 1 + 3 = 4.
- Turn 3: Alices removes the fourth stone with value 4. Sum of removed stones = 1 + 3 + 4 = 8.
- Turn 4: Bob removes the third stone with value 2. Sum of removed stones = 1 + 3 + 4 + 2 = 10.
- Turn 5: Alice removes the first stone with value 5. Sum of removed stones = 1 + 3 + 4 + 2 + 5 = 15.
Alice loses the game because the sum of the removed stones (15) is divisible by 3. Bob wins the game.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= stones.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= stones[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Case Discussion

Since the player's goal is to ensure the total value of the removed stones is not divisible by $3$, we only need to consider the remainder of each stone's value when divided by $3$.

We use an array $\textit{cnt}$ of length $3$ to maintain the count of the current remaining stones' values modulo $3$, where $\textit{cnt}[0]$ represents the count of stones with a remainder of $0$, and $\textit{cnt}[1]$ and $\textit{cnt}[2]$ respectively represent the counts of stones with remainders of $1$ and $2$.

In the first round, Alice cannot remove stones with a remainder of $0$, as this would make the total value of the removed stones divisible by $3$. Therefore, Alice can only remove stones with a remainder of $1$ or $2$.

First, let's consider the case where Alice removes a stone with a remainder of $1$. If Alice removes a stone with a remainder of $1$, the remainder of the total value of stones $0$ against $3$ will not change, so stones with a value remainder of $0$ can be removed in any round, which we will not consider for now. Thus, Bob can only remove stones with a remainder of $1$, followed by Alice removing stones with a remainder of $2$, and so on, in the sequence $1, 1, 2, 1, 2, \ldots$. In this scenario, if the final round is odd and there are still remaining stones, then Alice wins; otherwise, Bob wins.

For the case where Alice removes a stone with a remainder of $2$ in the first round, we can draw a similar conclusion.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{stones}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def stoneGameIX(self, stones: List[int]) -> bool:
        def check(cnt: List[int]) -> bool:
            if cnt[1] == 0:
                return False
            cnt[1] -= 1
            r = 1 + min(cnt[1], cnt[2]) * 2 + cnt[0]
            if cnt[1] > cnt[2]:
                cnt[1] -= 1
                r += 1
            return r % 2 == 1 and cnt[1] != cnt[2]

        c1 = [0] * 3
        for x in stones:
            c1[x % 3] += 1
        c2 = [c1[0], c1[2], c1[1]]
        return check(c1) or check(c2)
```

#### Java

```java
class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] c1 = new int[3];
        for (int x : stones) {
            c1[x % 3]++;
        }
        int[] c2 = {c1[0], c1[2], c1[1]};
        return check(c1) || check(c2);
    }

    private boolean check(int[] cnt) {
        if (--cnt[1] < 0) {
            return false;
        }
        int r = 1 + Math.min(cnt[1], cnt[2]) * 2 + cnt[0];
        if (cnt[1] > cnt[2]) {
            --cnt[1];
            ++r;
        }
        return r % 2 == 1 && cnt[1] != cnt[2];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool stoneGameIX(vector<int>& stones) {
        vector<int> c1(3);
        for (int x : stones) {
            ++c1[x % 3];
        }
        vector<int> c2 = {c1[0], c1[2], c1[1]};
        auto check = [](auto& cnt) -> bool {
            if (--cnt[1] < 0) {
                return false;
            }
            int r = 1 + min(cnt[1], cnt[2]) * 2 + cnt[0];
            if (cnt[1] > cnt[2]) {
                --cnt[1];
                ++r;
            }
            return r % 2 && cnt[1] != cnt[2];
        };
        return check(c1) || check(c2);
    }
};
```

#### Go

```go
func stoneGameIX(stones []int) bool {
	c1 := [3]int{}
	for _, x := range stones {
		c1[x%3]++
	}
	c2 := [3]int{c1[0], c1[2], c1[1]}
	check := func(cnt [3]int) bool {
		if cnt[1] == 0 {
			return false
		}
		cnt[1]--
		r := 1 + min(cnt[1], cnt[2])*2 + cnt[0]
		if cnt[1] > cnt[2] {
			cnt[1]--
			r++
		}
		return r%2 == 1 && cnt[1] != cnt[2]
	}
	return check(c1) || check(c2)
}
```

#### TypeScript

```ts
function stoneGameIX(stones: number[]): boolean {
    const c1: number[] = Array(3).fill(0);
    for (const x of stones) {
        ++c1[x % 3];
    }
    const c2: number[] = [c1[0], c1[2], c1[1]];
    const check = (cnt: number[]): boolean => {
        if (--cnt[1] < 0) {
            return false;
        }
        let r = 1 + Math.min(cnt[1], cnt[2]) * 2 + cnt[0];
        if (cnt[1] > cnt[2]) {
            --cnt[1];
            ++r;
        }
        return r % 2 === 1 && cnt[1] !== cnt[2];
    };
    return check(c1) || check(c2);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
