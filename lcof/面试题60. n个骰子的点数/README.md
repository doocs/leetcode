# [面试题 60. n 个骰子的点数](https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。</p>

<p>&nbsp;</p>

<p>你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 1
<strong>输出:</strong> [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> 2
<strong>输出:</strong> [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>1 &lt;= n &lt;= 11</code></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

动态规划求解。

扔 n 个骰子，点数之和的范围在 `[n, 6n]` 之间，总共有 `5n+1` 种，即为最后结果数组的长度。

假设 `dp[i][j]` 表示扔 i 个骰子，出现点数之和 j 的次数。n 个骰子，所以 i 的范围在 `1~n`，j 的范围在 `[1, 6n]`。

单看第 n 枚骰子，它的点数可能为 `1,2,3,...,6`，因此扔完 n 枚骰子后点数之和 j 出现的次数，可以由扔完 n-1 枚骰子后，对应点数 `j−1,j−2,j−3,...,j−6` 出现的次数之和转化过来。即：

```
for (第n枚骰子的点数 i = 1; i <= 6; i ++) {
    dp[n][j] += dp[n-1][j - i]
}
```

扔 1 枚骰子，点数可能是 `1,2,3,4,5,6`，且每个点数出现的次数均为 1。所以初始化如下：

```
for (int j = 1; j <= 6; ++j) {
    dp[1][j] = 1;
}
```

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def twoSum(self, n: int) -> List[float]:
        dp = [[0 for _ in range(6 * n + 1)] for _ in range(n + 1)]
        for j in range(1, 7):
            dp[1][j] = 1
        for i in range(2, n + 1):
            for j in range(i, 6 * i + 1):
                for k in range(1, 7):
                    if j <= k:
                        break
                    dp[i][j] += dp[i - 1][j - k]
        res, total = [], pow(6, n)
        for i in range(5 * n + 1):
            res.append(dp[n][n + i] / total)
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public double[] twoSum(int n) {
        int[][] dp = new int[n + 1][6 * n + 1];
        for (int j = 1; j <= 6; ++j) {
            dp[1][j] = 1;
        }
        for (int i = 2; i <= n; ++i) {
            for (int j = i; j <= 6 * i; ++j) {
                for (int k = 1; k <= 6 && j > k; ++k) {
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        double[] res = new double[5 * n + 1];
        double all = Math.pow(6, n);
        for (int i = 0; i <= 5 * n; ++i) {
            res[i] = dp[n][n + i] * 1.0 / all;
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number[]}
 */
var twoSum = function (n) {
    function backtrack(sum, time) {
        if (time === n) {
            res[sum]++;
            return;
        }
        for (let i = 1; i <= 6; i++) {
            backtrack(sum + i, time + 1);
        }
    }
    let len = n * 6;
    let t = 6 ** n;
    let res = new Array(len + 1).fill(0);
    backtrack(0, 0);
    return res.slice(n).map(e => e / t);
};
```

### **Go**

```go
func dicesProbability(n int) []float64 {
	dp := make([]float64, 7)
	for i := 1; i <= 6; i++ {
		dp[i] = 1.0 / 6.0
	}
	for i := 2; i <= n; i++ {
		n := len(dp)
		tmp := make([]float64, 6*i+1)
		for j := 0; j < n; j++ {
			for k := 1; k <= 6; k++ {
				tmp[j+k] += dp[j] / 6.0
			}
		}
		dp = tmp
	}
	return dp[n:]
}
```

### **C#**

```cs
public class Solution {
    public double[] DicesProbability(int n) {
        var bp = new double[6];
        for (int i = 0; i < 6; i++) {
            bp[i] = 1 / 6.0;
        }
        double[] ans = new double[]{1};
        for (int i = 1; i <= n; i++) {
            var tmp = ans;
            ans = new double[tmp.Length + 5];
            for (int i1 = 0; i1 < tmp.Length; i1++) {
                for (int i2 = 0; i2 < bp.Length; i2++) {
                    ans[i1+i2] += tmp[i1] * bp[i2];
                }
            }
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
