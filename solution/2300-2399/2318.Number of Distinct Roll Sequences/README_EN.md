# [2318. Number of Distinct Roll Sequences](https://leetcode.com/problems/number-of-distinct-roll-sequences)

[中文文档](/solution/2300-2399/2318.Number%20of%20Distinct%20Roll%20Sequences/README.md)

## Description

<p>You are given an integer <code>n</code>. You roll a fair 6-sided dice <code>n</code> times. Determine the total number of <strong>distinct</strong> sequences of rolls possible such that the following conditions are satisfied:</p>

<ol>
	<li>The <strong>greatest common divisor</strong> of any <strong>adjacent</strong> values in the sequence is equal to <code>1</code>.</li>
	<li>There is <strong>at least</strong> a gap of <code>2</code> rolls between <strong>equal</strong> valued rolls. More formally, if the value of the <code>i<sup>th</sup></code> roll is <strong>equal</strong> to the value of the <code>j<sup>th</sup></code> roll, then <code>abs(i - j) &gt; 2</code>.</li>
</ol>

<p>Return <em>the<strong> total number</strong> of distinct sequences possible</em>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>Two sequences are considered distinct if at least one element is different.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> 184
<strong>Explanation:</strong> Some of the possible sequences are (1, 2, 3, 4), (6, 1, 2, 3), (1, 2, 3, 1), etc.
Some invalid sequences are (1, 2, 1, 3), (1, 2, 3, 6).
(1, 2, 1, 3) is invalid since the first and third roll have an equal value and abs(1 - 3) = 2 (i and j are 1-indexed).
(1, 2, 3, 6) is invalid since the greatest common divisor of 3 and 6 = 3.
There are a total of 184 distinct sequences possible, so we return 184.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 22
<strong>Explanation:</strong> Some of the possible sequences are (1, 2), (2, 1), (3, 2).
Some invalid sequences are (3, 6), (2, 4) since the greatest common divisor is not equal to 1.
There are a total of 22 distinct sequences possible, so we return 22.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def distinctSequences(self, n: int) -> int:
        if n == 1:
            return 6
        mod = 10**9 + 7
        dp = [[[0] * 6 for _ in range(6)] for _ in range(n + 1)]
        for i in range(6):
            for j in range(6):
                if gcd(i + 1, j + 1) == 1 and i != j:
                    dp[2][i][j] = 1
        for k in range(3, n + 1):
            for i in range(6):
                for j in range(6):
                    if gcd(i + 1, j + 1) == 1 and i != j:
                        for h in range(6):
                            if gcd(h + 1, i + 1) == 1 and h != i and h != j:
                                dp[k][i][j] += dp[k - 1][h][i]
        ans = 0
        for i in range(6):
            for j in range(6):
                ans += dp[-1][i][j]
        return ans % mod
```

### **Java**

```java
class Solution {
    public int distinctSequences(int n) {
        if (n == 1) {
            return 6;
        }
        int mod = (int) 1e9 + 7;
        int[][][] dp = new int[n + 1][6][6];
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                if (gcd(i + 1, j + 1) == 1 && i != j) {
                    dp[2][i][j] = 1;
                }
            }
        }
        for (int k = 3; k <= n; ++k) {
            for (int i = 0; i < 6; ++i) {
                for (int j = 0; j < 6; ++j) {
                    if (gcd(i + 1, j + 1) == 1 && i != j) {
                        for (int h = 0; h < 6; ++h) {
                            if (gcd(h + 1, i + 1) == 1 && h != i && h != j) {
                                dp[k][i][j] = (dp[k][i][j] + dp[k - 1][h][i]) % mod;
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                ans = (ans + dp[n][i][j]) % mod;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int distinctSequences(int n) {
        if (n == 1) return 6;
        int mod = 1e9 + 7;
        vector<vector<vector<int>>> dp(n + 1, vector<vector<int>>(6, vector<int>(6)));
        for (int i = 0; i < 6; ++i)
            for (int j = 0; j < 6; ++j)
                if (gcd(i + 1, j + 1) == 1 && i != j)
                    dp[2][i][j] = 1;
        for (int k = 3; k <= n; ++k)
            for (int i = 0; i < 6; ++i)
                for (int j = 0; j < 6; ++j)
                    if (gcd(i + 1, j + 1) == 1 && i != j)
                        for (int h = 0; h < 6; ++h)
                            if (gcd(h + 1, i + 1) == 1 && h != i && h != j)
                                dp[k][i][j] = (dp[k][i][j] + dp[k - 1][h][i]) % mod;
        int ans = 0;
        for (int i = 0; i < 6; ++i)
            for (int j = 0; j < 6; ++j)
                ans = (ans + dp[n][i][j]) % mod;
        return ans;
    }

    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
};
```

### **Go**

```go
func distinctSequences(n int) int {
	if n == 1 {
		return 6
	}
	dp := make([][][]int, n+1)
	for k := range dp {
		dp[k] = make([][]int, 6)
		for i := range dp[k] {
			dp[k][i] = make([]int, 6)
		}
	}
	for i := 0; i < 6; i++ {
		for j := 0; j < 6; j++ {
			if gcd(i+1, j+1) == 1 && i != j {
				dp[2][i][j] = 1
			}
		}
	}
	mod := int(1e9) + 7
	for k := 3; k <= n; k++ {
		for i := 0; i < 6; i++ {
			for j := 0; j < 6; j++ {
				if gcd(i+1, j+1) == 1 && i != j {
					for h := 0; h < 6; h++ {
						if gcd(h+1, i+1) == 1 && h != i && h != j {
							dp[k][i][j] = (dp[k][i][j] + dp[k-1][h][i]) % mod
						}
					}
				}
			}
		}
	}
	ans := 0
	for i := 0; i < 6; i++ {
		for j := 0; j < 6; j++ {
			ans = (ans + dp[n][i][j]) % mod
		}
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
