# [1230. Toss Strange Coins](https://leetcode.com/problems/toss-strange-coins)

[中文文档](/solution/1200-1299/1230.Toss%20Strange%20Coins/README.md)

## Description

<p>You have some coins.&nbsp; The <code>i</code>-th&nbsp;coin has a probability&nbsp;<code>prob[i]</code> of facing heads when tossed.</p>

<p>Return the probability that the number of coins facing heads equals <code>target</code> if you toss every coin exactly once.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> prob = [0.4], target = 1
<strong>Output:</strong> 0.40000
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> prob = [0.5,0.5,0.5,0.5,0.5], target = 0
<strong>Output:</strong> 0.03125
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prob.length &lt;= 1000</code></li>
	<li><code>0 &lt;= prob[i] &lt;= 1</code></li>
	<li><code>0 &lt;= target&nbsp;</code><code>&lt;= prob.length</code></li>
	<li>Answers will be accepted as correct if they are within <code>10^-5</code> of the correct answer.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def probabilityOfHeads(self, prob: List[float], target: int) -> float:
        m = len(prob)
        dp = [[0] * (target + 1) for _ in range(m + 1)]
        dp[0][0] = 1
        for i in range(1, m + 1):
            for j in range(target + 1):
                dp[i][j] = dp[i - 1][j] * (1 - prob[i - 1])
                if j >= 1:
                    dp[i][j] += dp[i - 1][j - 1] * prob[i - 1]
        return dp[-1][-1]
```

```python
class Solution:
    def probabilityOfHeads(self, prob: List[float], target: int) -> float:
        dp = [0] * (target + 1)
        dp[0] = 1
        for v in prob:
            for j in range(target, -1, -1):
                dp[j] *= (1 - v)
                if j >= 1:
                    dp[j] += dp[j - 1] * v
        return dp[-1]
```

### **Java**

```java
class Solution {
    public double probabilityOfHeads(double[] prob, int target) {
        double[] dp = new double[target + 1];
        dp[0] = 1;
        for (double v : prob) {
            for (int j = target; j >= 0; --j) {
                dp[j] *= (1 - v);
                if (j >= 1) {
                    dp[j] += dp[j - 1] * v;
                }
            }
        }
        return dp[target];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    double probabilityOfHeads(vector<double>& prob, int target) {
        vector<double> dp(target + 1);
        dp[0] = 1;
        for (auto v : prob) {
            for (int j = target; j >= 0; --j) {
                dp[j] *= (1 - v);
                if (j >= 1) dp[j] += dp[j - 1] * v;
            }
        }
        return dp[target];
    }
};
```

### **Go**

```go
func probabilityOfHeads(prob []float64, target int) float64 {
	dp := make([]float64, target+1)
	dp[0] = 1
	for _, v := range prob {
		for j := target; j >= 0; j-- {
			dp[j] *= (1 - v)
			if j >= 1 {
				dp[j] += dp[j-1] * v
			}
		}
	}
	return dp[target]
}
```

### **...**

```

```

<!-- tabs:end -->
