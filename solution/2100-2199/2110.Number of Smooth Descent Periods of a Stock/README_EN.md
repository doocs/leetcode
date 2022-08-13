# [2110. Number of Smooth Descent Periods of a Stock](https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock)

[中文文档](/solution/2100-2199/2110.Number%20of%20Smooth%20Descent%20Periods%20of%20a%20Stock/README.md)

## Description

<p>You are given an integer array <code>prices</code> representing the daily price history of a stock, where <code>prices[i]</code> is the stock price on the <code>i<sup>th</sup></code> day.</p>

<p>A <strong>smooth descent period</strong> of a stock consists of <strong>one or more contiguous</strong> days such that the price on each day is <strong>lower</strong> than the price on the <strong>preceding day</strong> by <strong>exactly</strong> <code>1</code>. The first day of the period is exempted from this rule.</p>

<p>Return <em>the number of <strong>smooth descent periods</strong></em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [3,2,1,4]
<strong>Output:</strong> 7
<strong>Explanation:</strong> There are 7 smooth descent periods:
[3], [2], [1], [4], [3,2], [2,1], and [3,2,1]
Note that a period with one day is a smooth descent period by the definition.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [8,6,7,7]
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 smooth descent periods: [8], [6], [7], and [7]
Note that [8,6] is not a smooth descent period as 8 - 6 &ne; 1.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is 1 smooth descent period: [1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def getDescentPeriods(self, prices: List[int]) -> int:
        i, n = 0, len(prices)
        ans = 0
        while i < n:
            j = i
            while j + 1 < n and prices[j] - prices[j + 1] == 1:
                j += 1
            t = j - i + 1
            ans += t * (t + 1) // 2
            i = j + 1
        return ans
```

### **Java**

```java
class Solution {
    public long getDescentPeriods(int[] prices) {
        long ans = 0;
        for (int i = 0, n = prices.length; i < n;) {
            int j = i;
            for (; j + 1 < n && prices[j] - prices[j + 1] == 1; ++j);
            int t = j - i + 1;
            ans += (long) t * (t + 1) / 2;
            i = j + 1;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long getDescentPeriods(vector<int>& prices) {
        long long ans = 0;
        for (int i = 0, n = prices.size(); i < n;) {
            int j = i;
            for (; j + 1 < n && prices[j] - prices[j + 1] == 1; ++j)
                ;
            int t = j - i + 1;
            ans += (long long)t * (t + 1) / 2;
            i = j + 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func getDescentPeriods(prices []int) int64 {
	var ans int64
	for i, n := 0, len(prices); i < n; {
		j := i
		for ; j+1 < n && prices[j]-prices[j+1] == 1; j++ {
		}
		t := j - i + 1
		ans += int64(t * (t + 1) / 2)
		i = j + 1
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
