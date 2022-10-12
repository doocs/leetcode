# [233. Number of Digit One](https://leetcode.com/problems/number-of-digit-one)

[中文文档](/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)

## Description

<p>Given an integer <code>n</code>, count <em>the total number of digit </em><code>1</code><em> appearing in all non-negative integers less than or equal to</em> <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 13
<strong>Output:</strong> 6
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

simple digital dp problem (or it can be solved by finding a rule)

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countDigitOne(self, n: int) -> int:
        @cache
        def dfs(pos, cnt, limit):
            if pos <= 0:
                return cnt
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                ans += dfs(pos - 1, cnt + (i == 1), limit and i == up)
            return ans

        a = [0] * 12
        l = 1
        while n:
            a[l] = n % 10
            n //= 10
            l += 1
        return dfs(l, 0, True)
```

### **Java**

```java
class Solution {
    private int[] a = new int[12];
    private int[][] dp = new int[12][12];

    public int countDigitOne(int n) {
        int len = 0;
        while (n > 0) {
            a[++len] = n % 10;
            n /= 10;
        }
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        return dfs(len, 0, true);
    }

    private int dfs(int pos, int cnt, boolean limit) {
        if (pos <= 0) {
            return cnt;
        }
        if (!limit && dp[pos][cnt] != -1) {
            return dp[pos][cnt];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 1 ? 1 : 0), limit && i == up);
        }
        if (!limit) {
            dp[pos][cnt] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int a[12];
    int dp[12][12];

    int countDigitOne(int n) {
        int len = 0;
        while (n) {
            a[++len] = n % 10;
            n /= 10;
        }
        memset(dp, -1, sizeof dp);
        return dfs(len, 0, true);
    }

    int dfs(int pos, int cnt, bool limit) {
        if (pos <= 0) {
            return cnt;
        }
        if (!limit && dp[pos][cnt] != -1) {
            return dp[pos][cnt];
        }
        int ans = 0;
        int up = limit ? a[pos] : 9;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 1), limit && i == up);
        }
        if (!limit) {
            dp[pos][cnt] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func countDigitOne(n int) int {
	a := make([]int, 12)
	dp := make([][]int, 12)
	for i := range dp {
		dp[i] = make([]int, 12)
		for j := range dp[i] {
			dp[i][j] = -1
		}
	}
	l := 0
	for n > 0 {
		l++
		a[l] = n % 10
		n /= 10
	}
	var dfs func(int, int, bool) int
	dfs = func(pos, cnt int, limit bool) int {
		if pos <= 0 {
			return cnt
		}
		if !limit && dp[pos][cnt] != -1 {
			return dp[pos][cnt]
		}
		up := 9
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			t := cnt
			if i == 1 {
				t++
			}
			ans += dfs(pos-1, t, limit && i == up)
		}
		if !limit {
			dp[pos][cnt] = ans
		}
		return ans
	}
	return dfs(l, 0, true)
}
```

### **C#**

```cs
public class Solution {
    public int CountDigitOne(int n) {
        if (n <= 0) return 0;
        if (n < 10) return 1;
        return CountDigitOne(n / 10 - 1) * 10 + n / 10 + CountDigitOneOfN(n / 10) * (n % 10 + 1) + (n % 10 >= 1 ? 1 : 0);
    }

    private int CountDigitOneOfN(int n) {
        var count = 0;
        while (n > 0)
        {
            if (n % 10 == 1) ++count;
            n /= 10;
        }
        return count;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
