# [1690. Stone Game VII](https://leetcode.com/problems/stone-game-vii)

[中文文档](/solution/1600-1699/1690.Stone%20Game%20VII/README.md)

## Description

<p>Alice and Bob take turns playing a game, with <strong>Alice starting first</strong>.</p>

<p>There are <code>n</code> stones arranged in a row. On each player&#39;s turn, they can <strong>remove</strong> either the leftmost stone or the rightmost stone from the row and receive points equal to the <strong>sum</strong> of the remaining stones&#39; values in the row. The winner is the one with the higher score when there are no stones left to remove.</p>

<p>Bob found that he will always lose this game (poor Bob, he always loses), so he decided to <strong>minimize the score&#39;s difference</strong>. Alice&#39;s goal is to <strong>maximize the difference</strong> in the score.</p>

<p>Given an array of integers <code>stones</code> where <code>stones[i]</code> represents the value of the <code>i<sup>th</sup></code> stone <strong>from the left</strong>, return <em>the <strong>difference</strong> in Alice and Bob&#39;s score if they both play <strong>optimally</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stones = [5,3,1,4,2]
<strong>Output:</strong> 6
<strong>Explanation:</strong> 
- Alice removes 2 and gets 5 + 3 + 1 + 4 = 13 points. Alice = 13, Bob = 0, stones = [5,3,1,4].
- Bob removes 5 and gets 3 + 1 + 4 = 8 points. Alice = 13, Bob = 8, stones = [3,1,4].
- Alice removes 3 and gets 1 + 4 = 5 points. Alice = 18, Bob = 8, stones = [1,4].
- Bob removes 1 and gets 4 points. Alice = 18, Bob = 12, stones = [4].
- Alice removes 4 and gets 0 points. Alice = 18, Bob = 12, stones = [].
The score difference is 18 - 12 = 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stones = [7,90,5,1,100,10,10,2]
<strong>Output:</strong> 122</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == stones.length</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= stones[i] &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def stoneGameVII(self, stones: List[int]) -> int:
        @cache
        def dfs(i, j):
            if i > j:
                return 0
            a = s[j + 1] - s[i + 1] - dfs(i + 1, j)
            b = s[j] - s[i] - dfs(i, j - 1)
            return max(a, b)

        s = list(accumulate(stones, initial=0))
        ans = dfs(0, len(stones) - 1)
        dfs.cache_clear()
        return ans
```

### **Java**

```java
class Solution {
    private int[] s;
    private Integer[][] f;

    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        s = new int[n + 1];
        f = new Integer[n][n];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + stones[i];
        }
        return dfs(0, n - 1);
    }

    private int dfs(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (f[i][j] != null) {
            return f[i][j];
        }
        int a = s[j + 1] - s[i + 1] - dfs(i + 1, j);
        int b = s[j] - s[i] - dfs(i, j - 1);
        return f[i][j] = Math.max(a, b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int stoneGameVII(vector<int>& stones) {
        int n = stones.size();
        int f[n][n];
        memset(f, 0, sizeof f);
        int s[n + 1];
        s[0] = 0;
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + stones[i];
        }
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return 0;
            }
            if (f[i][j]) {
                return f[i][j];
            }
            int a = s[j + 1] - s[i + 1] - dfs(i + 1, j);
            int b = s[j] - s[i] - dfs(i, j - 1);
            return f[i][j] = max(a, b);
        };
        return dfs(0, n - 1);
    }
};
```

### **Go**

```go
func stoneGameVII(stones []int) int {
	n := len(stones)
	s := make([]int, n+1)
	f := make([][]int, n)
	for i, x := range stones {
		s[i+1] = s[i] + x
		f[i] = make([]int, n)
	}
	var dfs func(int, int) int
	dfs = func(i, j int) int {
		if i > j {
			return 0
		}
		if f[i][j] != 0 {
			return f[i][j]
		}
		a := s[j+1] - s[i+1] - dfs(i+1, j)
		b := s[j] - s[i] - dfs(i, j-1)
		f[i][j] = max(a, b)
		return f[i][j]
	}
	return dfs(0, n-1)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
