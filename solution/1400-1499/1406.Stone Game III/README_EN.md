# [1406. Stone Game III](https://leetcode.com/problems/stone-game-iii)

[中文文档](/solution/1400-1499/1406.Stone%20Game%20III/README.md)

## Description

<p>Alice and Bob continue their games with piles of stones. There are several stones <strong>arranged in a row</strong>, and each stone has an associated value which is an integer given in the array <code>stoneValue</code>.</p>

<p>Alice and Bob take turns, with Alice starting first. On each player&#39;s turn, that player can take <code>1</code>, <code>2</code>, or <code>3</code> stones from the <strong>first</strong> remaining stones in the row.</p>

<p>The score of each player is the sum of the values of the stones taken. The score of each player is <code>0</code> initially.</p>

<p>The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.</p>

<p>Assume Alice and Bob <strong>play optimally</strong>.</p>

<p>Return <code>&quot;Alice&quot;</code><em> if Alice will win, </em><code>&quot;Bob&quot;</code><em> if Bob will win, or </em><code>&quot;Tie&quot;</code><em> if they will end the game with the same score</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> values = [1,2,3,7]
<strong>Output:</strong> &quot;Bob&quot;
<strong>Explanation:</strong> Alice will always lose. Her best move will be to take three piles and the score become 6. Now the score of Bob is 7 and Bob wins.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> values = [1,2,3,-9]
<strong>Output:</strong> &quot;Alice&quot;
<strong>Explanation:</strong> Alice must choose all the three piles at the first move to win and leave Bob with negative score.
If Alice chooses one pile her score will be 1 and the next move Bob&#39;s score becomes 5. In the next move, Alice will take the pile with value = -9 and lose.
If Alice chooses two piles her score will be 3 and the next move Bob&#39;s score becomes 3. In the next move, Alice will take the pile with value = -9 and also lose.
Remember that both play optimally so here Alice will choose the scenario that makes her win.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> values = [1,2,3,6]
<strong>Output:</strong> &quot;Tie&quot;
<strong>Explanation:</strong> Alice cannot win this game. She can end the game in a draw if she decided to choose all the first three piles, otherwise she will lose.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= stoneValue.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= stoneValue[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def stoneGameIII(self, stoneValue: List[int]) -> str:
        @cache
        def dfs(i: int) -> int:
            if i >= len(stoneValue):
                return 0
            t = min(dfs(i + j) for j in range(1, 4))
            return s[-1] - s[i] - t

        s = list(accumulate(stoneValue, initial=0))
        a = dfs(0)
        b = s[-1] - a
        if a == b:
            return 'Tie'
        return 'Alice' if a > b else 'Bob'
```

### **Java**

```java
class Solution {
    private int n;
    private int[] s;
    private Integer[] f;

    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        s = new int[n + 1];
        f = new Integer[n];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        int a = dfs(0);
        int b = s[n] - a;
        return a == b ? "Tie" : a > b ? "Alice" : "Bob";
    }

    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int t = 1 << 30;
        for (int j = 1; j < 4; ++j) {
            t = Math.min(t, dfs(i + j));
        }
        f[i] = s[n] - s[i] - t;
        return f[i];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string stoneGameIII(vector<int>& stoneValue) {
        int n = stoneValue.size();
        int s[n + 1];
        s[0] = 0;
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + stoneValue[i - 1];
        }
        int f[n];
        memset(f, 0x3f, sizeof(f));
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) {
                return 0;
            }
            if (f[i] != 0x3f3f3f3f) {
                return f[i];
            }
            int t = 1 << 30;
            for (int j = 1; j < 4; ++j) {
                t = min(t, dfs(i + j));
            }
            return f[i] = s[n] - s[i] - t;
        };
        int a = dfs(0);
        int b = s[n] - a;
        return a == b ? "Tie" : (a > b ? "Alice" : "Bob");
    }
};
```

### **Go**

```go
func stoneGameIII(stoneValue []int) string {
	n := len(stoneValue)
	s := make([]int, n+1)
	for i, x := range stoneValue {
		s[i+1] = s[i] + x
	}
	const inf = 1 << 30
	f := make([]int, n)
	for i := range f {
		f[i] = inf
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != inf {
			return f[i]
		}
		t := inf
		for j := 1; j <= 3; j++ {
			t = min(t, dfs(i+j))
		}
		f[i] = s[n] - s[i] - t
		return f[i]
	}
	a := dfs(0)
	b := s[n] - a
	if a == b {
		return "Tie"
	}
	if a > b {
		return "Alice"
	}
	return "Bob"
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
