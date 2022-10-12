# [464. Can I Win](https://leetcode.com/problems/can-i-win)

[中文文档](/solution/0400-0499/0464.Can%20I%20Win/README.md)

## Description

<p>In the &quot;100 game&quot; two players take turns adding, to a running total, any integer from <code>1</code> to <code>10</code>. The player who first causes the running total to <strong>reach or exceed</strong> 100 wins.</p>

<p>What if we change the game so that players <strong>cannot</strong> re-use integers?</p>

<p>For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total &gt;= 100.</p>

<p>Given two integers <code>maxChoosableInteger</code> and <code>desiredTotal</code>, return <code>true</code> if the first player to move can force a win, otherwise, return <code>false</code>. Assume both players play <strong>optimally</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> maxChoosableInteger = 10, desiredTotal = 11
<strong>Output:</strong> false
<strong>Explanation:</strong>
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is &gt;= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> maxChoosableInteger = 10, desiredTotal = 0
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> maxChoosableInteger = 10, desiredTotal = 1
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= maxChoosableInteger &lt;= 20</code></li>
	<li><code>0 &lt;= desiredTotal &lt;= 300</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def canIWin(self, maxChoosableInteger: int, desiredTotal: int) -> bool:
        @cache
        def dfs(state, t):
            for i in range(1, maxChoosableInteger + 1):
                if (state >> i) & 1:
                    continue
                if t + i >= desiredTotal or not dfs(state | 1 << i, t + i):
                    return True
            return False

        s = (1 + maxChoosableInteger) * maxChoosableInteger // 2
        if s < desiredTotal:
            return False
        return dfs(0, 0)
```

### **Java**

```java
class Solution {
    private Map<Integer, Boolean> memo = new HashMap<>();

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int s = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (s < desiredTotal) {
            return false;
        }
        return dfs(0, 0, maxChoosableInteger, desiredTotal);
    }

    private boolean dfs(int state, int t, int maxChoosableInteger, int desiredTotal) {
        if (memo.containsKey(state)) {
            return memo.get(state);
        }
        boolean res = false;
        for (int i = 1; i <= maxChoosableInteger; ++i) {
            if (((state >> i) & 1) == 0) {
                if (t + i >= desiredTotal
                    || !dfs(state | 1 << i, t + i, maxChoosableInteger, desiredTotal)) {
                    res = true;
                    break;
                }
            }
        }
        memo.put(state, res);
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool canIWin(int maxChoosableInteger, int desiredTotal) {
        int s = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (s < desiredTotal) return false;
        unordered_map<int, bool> memo;
        return dfs(0, 0, maxChoosableInteger, desiredTotal, memo);
    }

    bool dfs(int state, int t, int maxChoosableInteger, int desiredTotal, unordered_map<int, bool>& memo) {
        if (memo.count(state)) return memo[state];
        bool res = false;
        for (int i = 1; i <= maxChoosableInteger; ++i) {
            if ((state >> i) & 1) continue;
            if (t + i >= desiredTotal || !dfs(state | 1 << i, t + i, maxChoosableInteger, desiredTotal, memo)) {
                res = true;
                break;
            }
        }
        memo[state] = res;
        return res;
    }
};
```

### **Go**

```go
func canIWin(maxChoosableInteger int, desiredTotal int) bool {
	s := (1 + maxChoosableInteger) * maxChoosableInteger / 2
	if s < desiredTotal {
		return false
	}
	memo := map[int]bool{}
	var dfs func(int, int) bool
	dfs = func(state, t int) bool {
		if v, ok := memo[state]; ok {
			return v
		}
		res := false
		for i := 1; i <= maxChoosableInteger; i++ {
			if (state>>i)&1 == 1 {
				continue
			}
			if t+i >= desiredTotal || !dfs(state|1<<i, t+i) {
				res = true
				break
			}
		}
		memo[state] = res
		return res
	}
	return dfs(0, 0)
}
```

### **...**

```

```

<!-- tabs:end -->
