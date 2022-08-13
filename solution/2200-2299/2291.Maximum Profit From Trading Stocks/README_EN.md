# [2291. Maximum Profit From Trading Stocks](https://leetcode.com/problems/maximum-profit-from-trading-stocks)

[中文文档](/solution/2200-2299/2291.Maximum%20Profit%20From%20Trading%20Stocks/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays of the same length <code>present</code> and <code>future</code> where <code>present[i]</code> is the current price of the <code>i<sup>th</sup></code> stock and <code>future[i]</code> is the price of the <code>i<sup>th</sup></code> stock a year in the future. You may buy each stock at most <strong>once</strong>. You are also given an integer <code>budget</code> representing the amount of money you currently have.</p>

<p>Return <em>the maximum amount of profit you can make.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> present = [5,4,6,2,3], future = [8,5,4,3,5], budget = 10
<strong>Output:</strong> 6
<strong>Explanation:</strong> One possible way to maximize your profit is to:
Buy the 0<sup>th</sup>, 3<sup>rd</sup>, and 4<sup>th</sup> stocks for a total of 5 + 2 + 3 = 10.
Next year, sell all three stocks for a total of 8 + 3 + 5 = 16.
The profit you made is 16 - 10 = 6.
It can be shown that the maximum profit you can make is 6.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> present = [2,2,5], future = [3,4,10], budget = 6
<strong>Output:</strong> 5
<strong>Explanation:</strong> The only possible way to maximize your profit is to:
Buy the 2<sup>nd</sup> stock, and make a profit of 10 - 5 = 5.
It can be shown that the maximum profit you can make is 5.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> present = [3,3,12], future = [0,3,15], budget = 10
<strong>Output:</strong> 0
<strong>Explanation:</strong> One possible way to maximize your profit is to:
Buy the 1<sup>st</sup> stock, and make a profit of 3 - 3 = 0.
It can be shown that the maximum profit you can make is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == present.length == future.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= present[i], future[i] &lt;= 100</code></li>
	<li><code>0 &lt;= budget &lt;= 1000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumProfit(self, present: List[int], future: List[int], budget: int) -> int:
        arr = [(a, b - a) for a, b in zip(present, future) if b > a]
        dp = [0] * (budget + 1)
        for v, w in arr:
            for j in range(budget, v - 1, -1):
                dp[j] = max(dp[j], dp[j - v] + w)
        return dp[-1]
```

### **Java**

```java
class Solution {
    public int maximumProfit(int[] present, int[] future, int budget) {
        List<int[]> arr = new ArrayList<>();
        for (int i = 0; i < present.length; ++i) {
            if (future[i] > present[i]) {
                arr.add(new int[]{present[i], future[i] - present[i]});
            }
        }
        int[] dp = new int[budget + 1];
        for (int[] e : arr) {
            int v = e[0], w = e[1];
            for (int j = budget; j >= v; --j) {
                dp[j] = Math.max(dp[j], dp[j - v] + w);
            }
        }
        return dp[budget];
    }
}
```

### **TypeScript**

```ts
function maximumProfit(
    present: number[],
    future: number[],
    budget: number,
): number {
    let packet = present.map((v, i) => [v, future[i] - v]);
    let dp = new Array(budget + 1).fill(0);
    for (let [v, w] of packet) {
        for (let j = budget; j >= v; j--) {
            dp[j] = Math.max(dp[j], dp[j - v] + w);
        }
    }
    return dp[budget];
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumProfit(vector<int>& present, vector<int>& future, int budget) {
        int n = present.size();
        vector<int> dp(budget + 1);
        for (int i = 0; i < n; i++) {
            for (int j = budget; j >= present[i]; j--) {
                dp[j] = max(dp[j], dp[j - present[i]] + future[i] - present[i]);
            }
        }
        return dp.back();
    }
};
```

### **Go**

```go
func maximumProfit(present []int, future []int, budget int) int {
	arr := [][]int{}
	for i, v := range present {
		if future[i] > v {
			arr = append(arr, []int{v, future[i] - v})
		}
	}
	dp := make([]int, budget+1)
	for _, e := range arr {
		v, w := e[0], e[1]
		for j := budget; j >= v; j-- {
			dp[j] = max(dp[j], dp[j-v]+w)
		}
	}
	return dp[budget]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
