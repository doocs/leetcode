# [17.06. Number Of 2s In Range](https://leetcode.cn/problems/number-of-2s-in-range-lcci)

[中文文档](/lcci/17.06.Number%20Of%202s%20In%20Range/README.md)

## Description

<p>Write a method to count the number of 2s that appear in all the numbers between 0&nbsp;and n (inclusive).</p>
<p><strong>Example:</strong></p>
<pre>

<strong>Input: </strong>25

<strong>Output: </strong>9

<strong>Explanation: </strong>(2, 12, 20, 21, 22, 23, 24, 25)(Note that 22 counts for two 2s.)</pre>

<p>Note:</p>
<ul>
	<li><code>n &lt;= 10^9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOf2sInRange(self, n: int) -> int:
        @cache
        def dfs(pos, cnt, limit):
            if pos <= 0:
                return cnt
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                ans += dfs(pos - 1, cnt + (i == 2), limit and i == up)
            return ans

        a = [0] * 12
        l = 0
        while n:
            l += 1
            a[l] = n % 10
            n //= 10
        return dfs(l, 0, True)
```

### **Java**

```java
class Solution {
    private int[] a = new int[12];
    private int[][] dp = new int[12][12];

    public int numberOf2sInRange(int n) {
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
            ans += dfs(pos - 1, cnt + (i == 2 ? 1 : 0), limit && i == up);
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

    int numberOf2sInRange(int n) {
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
            ans += dfs(pos - 1, cnt + (i == 2), limit && i == up);
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
func numberOf2sInRange(n int) int {
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
			if i == 2 {
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

### **...**

```

```

<!-- tabs:end -->
