# [600. Non-negative Integers without Consecutive Ones](https://leetcode.com/problems/non-negative-integers-without-consecutive-ones)

[中文文档](/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)

## Description

<p>Given a positive integer <code>n</code>, return the number of the integers in the range <code>[0, n]</code> whose binary representations <strong>do not</strong> contain consecutive ones.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 5
<strong>Explanation:</strong>
Here are the non-negative integers &lt;= 5 with their corresponding binary representations:
0 : 0
1 : 1
2 : 10
3 : 11
4 : 100
5 : 101
Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findIntegers(self, n: int) -> int:
        @cache
        def dfs(pos, pre, limit):
            if pos <= 0:
                return 1
            up = a[pos] if limit else 1
            ans = 0
            for i in range(up + 1):
                if pre == 1 and i == 1:
                    continue
                ans += dfs(pos - 1, i, limit and i == up)
            return ans

        a = [0] * 33
        l = 0
        while n:
            l += 1
            a[l] = n & 1
            n >>= 1
        return dfs(l, 0, True)
```

### **Java**

```java
class Solution {
    private int[] a = new int[33];
    private int[][] dp = new int[33][2];

    public int findIntegers(int n) {
        int len = 0;
        while (n > 0) {
            a[++len] = n & 1;
            n >>= 1;
        }
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        return dfs(len, 0, true);
    }

    private int dfs(int pos, int pre, boolean limit) {
        if (pos <= 0) {
            return 1;
        }
        if (!limit && dp[pos][pre] != -1) {
            return dp[pos][pre];
        }
        int up = limit ? a[pos] : 1;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (!(pre == 1 && i == 1)) {
                ans += dfs(pos - 1, i, limit && i == up);
            }
        }
        if (!limit) {
            dp[pos][pre] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int a[33];
    int dp[33][2];

    int findIntegers(int n) {
        int len = 0;
        while (n) {
            a[++len] = n & 1;
            n >>= 1;
        }
        memset(dp, -1, sizeof dp);
        return dfs(len, 0, true);
    }

    int dfs(int pos, int pre, bool limit) {
        if (pos <= 0) {
            return 1;
        }
        if (!limit && dp[pos][pre] != -1) {
            return dp[pos][pre];
        }
        int ans = 0;
        int up = limit ? a[pos] : 1;
        for (int i = 0; i <= up; ++i) {
            if (!(pre == 1 && i == 1)) {
                ans += dfs(pos - 1, i, limit && i == up);
            }
        }
        if (!limit) {
            dp[pos][pre] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func findIntegers(n int) int {
	a := make([]int, 33)
	dp := make([][2]int, 33)
	for i := range dp {
		dp[i] = [2]int{-1, -1}
	}
	l := 0
	for n > 0 {
		l++
		a[l] = n & 1
		n >>= 1
	}
	var dfs func(int, int, bool) int
	dfs = func(pos, pre int, limit bool) int {
		if pos <= 0 {
			return 1
		}
		if !limit && dp[pos][pre] != -1 {
			return dp[pos][pre]
		}
		up := 1
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			if !(pre == 1 && i == 1) {
				ans += dfs(pos-1, i, limit && i == up)
			}
		}
		if !limit {
			dp[pos][pre] = ans
		}
		return ans
	}
	return dfs(l, 0, true)
}
```

### **...**

```

```

<!-- tabs:end -->
