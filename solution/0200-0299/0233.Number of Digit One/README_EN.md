# [233. Number of Digit One](https://leetcode.com/problems/number-of-digit-one)

[中文文档](/solution/0200-0299/0233.Number%20of%20Digit%20One/README.md)

## Description

<p>Given an integer <code>n</code>, count <em>the total number of digit </em><code>1</code><em> appearing in all non-negative integers less than or equal to</em> <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 13
<strong>Output:</strong> 6
</pre>

<p><strong>Example 2:</strong></p>

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
        dp = [[-1] * 10 for _ in range(10)]
        digit = []
        while n:
            digit.append(n % 10)
            n //= 10

        def dfs(pos: int, cnt: int, limit: bool) -> int:
            if pos == -1:
                return cnt
            if not limit and dp[pos][cnt] != -1:
                return dp[pos][cnt]
            up = digit[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                nxt = cnt + 1 if i == 1 else cnt
                ans += dfs(pos - 1, nxt, limit and i == digit[pos])
            if not limit:
                dp[pos][cnt] = ans
            return ans

        return dfs(len(digit) - 1, 0, True)
```

### **Java**

```java
class Solution {
    public int countDigitOne(int n) {
        int index = 1;
        int count = 0;
        int high = n,cur = 0,low = 0;
        while(high > 0){
            high /= 10;
            cur = (n / index) % 10;
            low = n - (n / index) * index;
            if(cur == 0) count += high * index;
            if(cur == 1) count += high * index + low + 1;
            if(cur > 1) count += (high+1) * index;
            index *= 10;
        }
        return count;
    }
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

### **Go**

```go
func countDigitOne(n int) int {
	digit := make([]int, 0)
	for i := n; i > 0; i /= 10 {
		digit = append(digit, i%10)
	}

	dp := make([][]int, 10)
	for i := range dp {
		dp[i] = make([]int, 10)
		for j := 0; j < 10; j++ {
			dp[i][j] = -1
		}
	}

	var dfs func(pos, cnt int, limit bool) int
	dfs = func(pos, cnt int, limit bool) int {
		if pos == -1 {
			return cnt
		}
		if !limit && dp[pos][cnt] != -1 {
			return dp[pos][cnt]
		}
		up := 9
		if limit {
			up = digit[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			next := cnt
			if i == 1 {
				next++
			}
			ans += dfs(pos-1, next, limit && i == digit[pos])
		}
		if !limit {
			dp[pos][cnt] = ans
		}
		return ans
	}

	return dfs(len(digit)-1, 0, true)
}
```

### **...**

```

```

<!-- tabs:end -->
