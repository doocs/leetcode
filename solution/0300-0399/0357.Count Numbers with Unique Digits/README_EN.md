# [357. Count Numbers with Unique Digits](https://leetcode.com/problems/count-numbers-with-unique-digits)

[中文文档](/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)

## Description

<p>Given an integer <code>n</code>, return the count of all numbers with unique digits, <code>x</code>, where <code>0 &lt;= x &lt; 10<sup>n</sup></code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 91
<strong>Explanation:</strong> The answer should be the total numbers in the range of 0 &le; x &lt; 100, excluding 11,22,33,44,55,66,77,88,99
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 0
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 8</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countNumbersWithUniqueDigits(self, n: int) -> int:
        if n == 0:
            return 1
        if n == 1:
            return 10
        ans, cur = 10, 9
        for i in range(n - 1):
            cur *= 9 - i
            ans += cur
        return ans
```

```python
class Solution:
    def countNumbersWithUniqueDigits(self, n: int) -> int:
        @cache
        def dfs(pos, mask, lead):
            if pos <= 0:
                return 1
            ans = 0
            for i in range(10):
                if (mask >> i) & 1:
                    continue
                if i == 0 and lead:
                    ans += dfs(pos - 1, mask, lead)
                else:
                    ans += dfs(pos - 1, mask | (1 << i), False)
            return ans

        return dfs(n, 0, True)
```

### **Java**

```java
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int ans = 10;
        for (int i = 0, cur = 9; i < n - 1; ++i) {
            cur *= (9 - i);
            ans += cur;
        }
        return ans;
    }
}
```

```java
class Solution {
    private int[][] dp = new int[10][1 << 11];

    public int countNumbersWithUniqueDigits(int n) {
        for (var e : dp) {
            Arrays.fill(e, -1);
        }
        return dfs(n, 0, true);
    }

    private int dfs(int pos, int mask, boolean lead) {
        if (pos <= 0) {
            return 1;
        }
        if (!lead && dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        int ans = 0;
        for (int i = 0; i < 10; ++i) {
            if (((mask >> i) & 1) == 1) {
                continue;
            }
            if (i == 0 && lead) {
                ans += dfs(pos - 1, mask, lead);
            } else {
                ans += dfs(pos - 1, mask | (1 << i), false);
            }
        }
        if (!lead) {
            dp[pos][mask] = ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        if (n == 1) return 10;
        int ans = 10;
        for (int i = 0, cur = 9; i < n - 1; ++i) {
            cur *= (9 - i);
            ans += cur;
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int dp[10][1 << 11];

    int countNumbersWithUniqueDigits(int n) {
        memset(dp, -1, sizeof dp);
        return dfs(n, 0, true);
    }

    int dfs(int pos, int mask, bool lead) {
        if (pos <= 0) {
            return 1;
        }
        if (!lead && dp[pos][mask] != -1) {
            return dp[pos][mask];
        }
        int ans = 0;
        for (int i = 0; i < 10; ++i) {
            if ((mask >> i) & 1) continue;
            if (i == 0 && lead) {
                ans += dfs(pos - 1, mask, lead);
            } else {
                ans += dfs(pos - 1, mask | 1 << i, false);
            }
        }
        if (!lead) {
            dp[pos][mask] = ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func countNumbersWithUniqueDigits(n int) int {
	if n == 0 {
		return 1
	}
	if n == 1 {
		return 10
	}
	ans := 10
	for i, cur := 0, 9; i < n-1; i++ {
		cur *= (9 - i)
		ans += cur
	}
	return ans
}
```

```go
func countNumbersWithUniqueDigits(n int) int {
	dp := make([][]int, 10)
	for i := range dp {
		dp[i] = make([]int, 1<<11)
		for j := range dp[i] {
			dp[i][j] = -1
		}
	}
	var dfs func(int, int, bool) int
	dfs = func(pos, mask int, lead bool) int {
		if pos <= 0 {
			return 1
		}
		if !lead && dp[pos][mask] != -1 {
			return dp[pos][mask]
		}
		ans := 0
		for i := 0; i < 10; i++ {
			if ((mask >> i) & 1) == 1 {
				continue
			}
			if i == 0 && lead {
				ans += dfs(pos-1, mask, lead)
			} else {
				ans += dfs(pos-1, mask|1<<i, false)
			}
		}
		if !lead {
			dp[pos][mask] = ans
		}
		return ans
	}

	return dfs(n, 0, true)
}
```

### **...**

```

```

<!-- tabs:end -->
