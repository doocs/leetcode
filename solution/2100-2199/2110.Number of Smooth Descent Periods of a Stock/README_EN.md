# [2110. Number of Smooth Descent Periods of a Stock](https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock)

[中文文档](/solution/2100-2199/2110.Number%20of%20Smooth%20Descent%20Periods%20of%20a%20Stock/README.md)

## Description

<p>You are given an integer array <code>prices</code> representing the daily price history of a stock, where <code>prices[i]</code> is the stock price on the <code>i<sup>th</sup></code> day.</p>

<p>A <strong>smooth descent period</strong> of a stock consists of <strong>one or more contiguous</strong> days such that the price on each day is <strong>lower</strong> than the price on the <strong>preceding day</strong> by <strong>exactly</strong> <code>1</code>. The first day of the period is exempted from this rule.</p>

<p>Return <em>the number of <strong>smooth descent periods</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [3,2,1,4]
<strong>Output:</strong> 7
<strong>Explanation:</strong> There are 7 smooth descent periods:
[3], [2], [1], [4], [3,2], [2,1], and [3,2,1]
Note that a period with one day is a smooth descent period by the definition.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [8,6,7,7]
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 smooth descent periods: [8], [6], [7], and [7]
Note that [8,6] is not a smooth descent period as 8 - 6 &ne; 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

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
        ans = 0
        i, n = 0, len(prices)
        while i < n:
            j = i + 1
            while j < n and prices[j - 1] - prices[j] == 1:
                j += 1
            cnt = j - i
            ans += (1 + cnt) * cnt // 2
            i = j
        return ans
```

### **Java**

```java
class Solution {
    public long getDescentPeriods(int[] prices) {
        long ans = 0;
        int n = prices.length;
        for (int i = 0, j = 0; i < n; i = j) {
            j = i + 1;
            while (j < n && prices[j - 1] - prices[j] == 1) {
                ++j;
            }
            int cnt = j - i;
            ans += (1L + cnt) * cnt / 2;
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
        int n = prices.size();
        for (int i = 0, j = 0; i < n; i = j) {
            j = i + 1;
            while (j < n && prices[j - 1] - prices[j] == 1) {
                ++j;
            }
            int cnt = j - i;
            ans += (1LL + cnt) * cnt / 2;
        }
        return ans;
    }
};
```

### **Go**

```go
func getDescentPeriods(prices []int) (ans int64) {
	n := len(prices)
	for i, j := 0, 0; i < n; i = j {
		j = i + 1
		for j < n && prices[j-1]-prices[j] == 1 {
			j++
		}
		cnt := j - i
		ans += int64((1 + cnt) * cnt / 2)
	}
	return
}
```

### **TypeScript**

```ts
function getDescentPeriods(prices: number[]): number {
    let ans = 0;
    const n = prices.length;
    for (let i = 0, j = 0; i < n; i = j) {
        j = i + 1;
        while (j < n && prices[j - 1] - prices[j] === 1) {
            ++j;
        }
        const cnt = j - i;
        ans += Math.floor(((1 + cnt) * cnt) / 2);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
