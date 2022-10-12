# [486. Predict the Winner](https://leetcode.com/problems/predict-the-winner)

[中文文档](/solution/0400-0499/0486.Predict%20the%20Winner/README.md)

## Description

<p>You are given an integer array <code>nums</code>. Two players are playing a game with this array: player 1 and player 2.</p>

<p>Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of <code>0</code>. At each turn, the player takes one of the numbers from either end of the array (i.e., <code>nums[0]</code> or <code>nums[nums.length - 1]</code>) which reduces the size of the array by <code>1</code>. The player adds the chosen number to their score. The game ends when there are no more elements in the array.</p>

<p>Return <code>true</code> if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the winner, and you should also return <code>true</code>. You may assume that both players are playing optimally.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return false.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,5,233,7]
<strong>Output:</strong> true
<strong>Explanation:</strong> Player 1 first chooses 1. Then player 2 has to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 20</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def PredictTheWinner(self, nums: List[int]) -> bool:
        @cache
        def dfs(i, j):
            if i > j:
                return 0
            a = min(dfs(i + 1, j), dfs(i, j - 1))
            return s[j + 1] - s[i] - a

        s = list(accumulate(nums, initial=0))
        return dfs(0, len(nums) - 1) * 2 >= s[-1]
```

### **Java**

```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        if ((n & 1) == 0) {
            return true;
        }
        int[] f = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            f[i] = nums[i];
            for (int j = i + 1; j < n; ++j) {
                f[j] = Math.max(nums[i] - f[j], nums[j] - f[j - 1]);
            }
        }
        return f[n - 1] >= 0;
    }
}
```

```java
class Solution {
    private int[] s;
    private int[][] f;

    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        f = new int[n + 1][n + 1];
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        return dfs(0, n - 1) * 2 >= s[n];
    }

    private int dfs(int i, int j) {
        if (i > j) {
            return 0;
        }
        if (f[i][j] != -1) {
            return f[i][j];
        }
        int a = Math.min(dfs(i + 1, j), dfs(i, j - 1));
        int res = s[j + 1] - s[i] - a;
        f[i][j] = res;
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<vector<int>> f;
    vector<int> s;

    bool PredictTheWinner(vector<int>& nums) {
        int n = nums.size();
        s.resize(n + 1);
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        f.assign(n + 1, vector<int>(n + 1, -1));
        return dfs(0, n - 1) * 2 >= s[n];
    }

    int dfs(int i, int j) {
        if (i > j) return 0;
        if (f[i][j] != -1) return f[i][j];
        int a = min(dfs(i + 1, j), dfs(i, j - 1));
        int res = s[j + 1] - s[i] - a;
        f[i][j] = res;
        return res;
    }
};
```

### **Go**

```go
func PredictTheWinner(nums []int) bool {
	n := len(nums)
	s := make([]int, n+1)
	f := make([][]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	for i := range f {
		f[i] = make([]int, n+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		a := min(dfs(i+1, j), dfs(i, j-1))
		f[i][j] = s[j+1] - s[i] - a
		return f[i][j]
	}
	return dfs(0, n-1)*2 >= s[n]
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
