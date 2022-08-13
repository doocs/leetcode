# [1230. 抛掷硬币](https://leetcode.cn/problems/toss-strange-coins)

[English Version](/solution/1200-1299/1230.Toss%20Strange%20Coins/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一些不规则的硬币。在这些硬币中，<code>prob[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;枚硬币正面朝上的概率。</p>

<p>请对每一枚硬币抛掷&nbsp;<strong>一次</strong>，然后返回正面朝上的硬币数等于&nbsp;<code>target</code>&nbsp;的概率。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>prob = [0.4], target = 1
<strong>输出：</strong>0.40000
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>prob = [0.5,0.5,0.5,0.5,0.5], target = 0
<strong>输出：</strong>0.03125
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= prob.length &lt;= 1000</code></li>
	<li><code>0 &lt;= prob[i] &lt;= 1</code></li>
	<li><code>0 &lt;= target&nbsp;</code><code>&lt;= prob.length</code></li>
	<li>如果答案与标准答案的误差在&nbsp;<code>10^-5</code>&nbsp;内，则被视为正确答案。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

`0-1` 背包问题。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

空间优化：

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
