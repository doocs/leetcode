# [2029. Stone Game IX](https://leetcode.com/problems/stone-game-ix)

[中文文档](/solution/2000-2099/2029.Stone%20Game%20IX/README.md)

## Description

<p>Alice and Bob continue their games with stones. There is a row of n stones, and each stone has an associated value. You are given an integer array <code>stones</code>, where <code>stones[i]</code> is the <strong>value</strong> of the <code>i<sup>th</sup></code> stone.</p>

<p>Alice and Bob take turns, with <strong>Alice</strong> starting first. On each turn, the player may remove any stone from <code>stones</code>. The player who removes a stone <strong>loses</strong> if the <strong>sum</strong> of the values of <strong>all removed stones</strong> is divisible by <code>3</code>. Bob will win automatically if there are no remaining stones (even if it is Alice&#39;s turn).</p>

<p>Assuming both players play <strong>optimally</strong>, return <code>true</code> <em>if Alice wins and</em> <code>false</code> <em>if Bob wins</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> stones = [2,1]
<strong>Output:</strong> true
<strong>Explanation:</strong>&nbsp;The game will be played as follows:
- Turn 1: Alice can remove either stone.
- Turn 2: Bob removes the remaining stone. 
The sum of the removed stones is 1 + 2 = 3 and is divisible by 3. Therefore, Bob loses and Alice wins the game.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> stones = [2]
<strong>Output:</strong> false
<strong>Explanation:</strong>&nbsp;Alice will remove the only stone, and the sum of the values on the removed stones is 2. 
Since all the stones are removed and the sum of values is not divisible by 3, Bob wins the game.
</pre>

<p><strong>Example 3:</strong></p>

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def stoneGameIX(self, stones: List[int]) -> bool:
        def check(c):
            if c[1] == 0:
                return False
            c[1] -= 1
            turn = 1 + min(c[1], c[2]) * 2 + c[0]
            if c[1] > c[2]:
                turn += 1
                c[1] -= 1
            return turn % 2 == 1 and c[1] != c[2]

        c = [0] * 3
        for s in stones:
            c[s % 3] += 1
        c1 = [c[0], c[2], c[1]]
        return check(c) or check(c1)
```

### **Java**

```java
class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] c = new int[3];
        for (int s : stones) {
            ++c[s % 3];
        }
        int[] t = new int[]{c[0], c[2], c[1]};
        return check(c) || check(t);
    }

    private boolean check(int[] c) {
        if (c[1] == 0) {
            return false;
        }
        --c[1];
        int turn = 1 + Math.min(c[1], c[2]) * 2 + c[0];
        if (c[1] > c[2]) {
            --c[1];
            ++turn;
        }
        return turn % 2 == 1 && c[1] != c[2];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool stoneGameIX(vector<int>& stones) {
        vector<int> c(3);
        for (int s : stones) ++c[s % 3];
        vector<int> t = {c[0], c[2], c[1]};
        return check(c) || check(t);
    }

    bool check(vector<int>& c) {
        if (c[1] == 0) return false;
        --c[1];
        int turn = 1 + min(c[1], c[2]) * 2 + c[0];
        if (c[1] > c[2]) {
            --c[1];
            ++turn;
        }
        return turn % 2 == 1 && c[1] != c[2];
    }
};
```

### **Go**

```go
func stoneGameIX(stones []int) bool {
	check := func(c [3]int) bool {
		if c[1] == 0 {
			return false
		}
		c[1]--
		turn := 1 + min(c[1], c[2])*2 + c[0]
		if c[1] > c[2] {
			c[1]--
			turn++
		}
		return turn%2 == 1 && c[1] != c[2]
	}
	c := [3]int{}
	for _, s := range stones {
		c[s%3]++
	}
	return check(c) || check([3]int{c[0], c[2], c[1]})
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
