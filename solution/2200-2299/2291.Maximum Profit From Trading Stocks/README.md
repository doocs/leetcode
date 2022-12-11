# [2291. 最大股票收益](https://leetcode.cn/problems/maximum-profit-from-trading-stocks)

[English Version](/solution/2200-2299/2291.Maximum%20Profit%20From%20Trading%20Stocks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个下标从 <strong>0</strong>&nbsp;开始的数组 <code>present</code> 和 <code>future</code> ，<code>present[i]</code> 和 <code>future[i]</code> 分别代表第 <code>i</code> 支股票现在和将来的价格。每支股票你最多购买 <strong>一次</strong> ，你的预算为 <code>budget</code> 。</p>

<p>求最大的收益。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>present = [5,4,6,2,3], future = [8,5,4,3,5], budget = 10
<strong>输出：</strong>6
<strong>解释：</strong>你可以选择购买第 0,3,4 支股票获得最大收益：6 。总开销为：5 + 2 + 3 = 10 , 总收益是: 8 + 3 + 5 - 10 = 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>present = [2,2,5], future = [3,4,10], budget = 6
<strong>输出：</strong>5
<strong>解释：</strong>你可以选择购买第 2 支股票获得最大收益：5 。总开销为：5 , 总收益是: 10 - 5 = 5 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>present = [3,3,12], future = [0,3,15], budget = 10
<strong>输出：</strong>0
<strong>解释：</strong>你无法购买唯一一支正收益股票 2 ，因此你的收益是 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == present.length == future.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>0 &lt;= present[i], future[i] &lt;= 100</code></li>
	<li><code>0 &lt;= budget &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

$0-1$ 背包问题。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumProfit(int[] present, int[] future, int budget) {
        List<int[]> arr = new ArrayList<>();
        for (int i = 0; i < present.length; ++i) {
            if (future[i] > present[i]) {
                arr.add(new int[] {present[i], future[i] - present[i]});
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
