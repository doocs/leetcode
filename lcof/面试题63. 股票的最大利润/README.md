# [面试题 63. 股票的最大利润](https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/)

## 题目描述

<p>假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [7,1,5,3,6,4]
<strong>输出:</strong> 5
<strong>解释: </strong>在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [7,6,4,3,1]
<strong>输出:</strong> 0
<strong>解释: </strong>在这种情况下, 没有交易完成, 所以最大利润为 0。</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>0 &lt;= 数组长度 &lt;= 10^5</code></p>

<p>&nbsp;</p>

<p><strong>注意：</strong>本题与主站 121 题相同：<a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/">https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/</a></p>

## 解法

**方法一：动态规划**

我们可以枚举当前的股票价格作为卖出价格，那么买入价格就是在它之前的最低股票价格，此时的利润就是卖出价格减去买入价格。我们可以用一个变量 `mi` 记录之前的最低股票价格，用一个变量 `ans` 记录最大利润，找出最大利润即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 是数组 `prices` 的长度。

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        mi, ans = inf, 0
        for x in prices:
            ans = max(ans, x - mi)
            mi = min(mi, x)
        return ans
```

### **Java**

```java
class Solution {
    public int maxProfit(int[] prices) {
        int mi = 1 << 30, ans = 0;
        for (int x : prices) {
            ans = Math.max(ans, x - mi);
            mi = Math.min(mi, x);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int mi = 1 << 30, ans = 0;
        for (int& x : prices) {
            ans = max(ans, x - mi);
            mi = min(mi, x);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxProfit(prices []int) (ans int) {
	mi := 1 << 30
	for _, x := range prices {
		ans = max(ans, x-mi)
		mi = min(mi, x)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
    let mi = 1 << 30;
    let ans = 0;
    for (const x of prices) {
        ans = Math.max(ans, x - mi);
        mi = Math.min(mi, x);
    }
    return ans;
};
```

### **TypeScript**

```ts
function maxProfit(prices: number[]): number {
    let res = 0;
    let min = Infinity;
    for (const price of prices) {
        res = Math.max(res, price - min);
        min = Math.min(min, price);
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_profit(prices: Vec<i32>) -> i32 {
        let mut res = 0;
        let mut min = i32::MAX;
        for price in prices {
            res = res.max(price - min);
            min = min.min(price);
        }
        res
    }
}
```

### **C#**

```cs
public class Solution {
    public int MaxProfit(int[] prices) {
        int mi = 1 << 30;
        int ans = 0;
        foreach(int x in prices) {
            ans = Math.Max(ans, x - mi);
            mi = Math.Min(mi, x);
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
