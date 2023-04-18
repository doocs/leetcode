# [121. 买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock)

[English Version](/solution/0100-0199/0121.Best%20Time%20to%20Buy%20and%20Sell%20Stock/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个数组 <code>prices</code> ，它的第 <code>i</code> 个元素 <code>prices[i]</code> 表示一支给定股票第 <code>i</code> 天的价格。</p>

<p>你只能选择 <strong>某一天</strong> 买入这只股票，并选择在 <strong>未来的某一个不同的日子</strong> 卖出该股票。设计一个算法来计算你所能获取的最大利润。</p>

<p>返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 <code>0</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[7,1,5,3,6,4]
<strong>输出：</strong>5
<strong>解释：</strong>在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>prices = [7,6,4,3,1]
<strong>输出：</strong>0
<strong>解释：</strong>在这种情况下, 没有交易完成, 所以最大利润为 0。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= prices.length <= 10<sup>5</sup></code></li>
	<li><code>0 <= prices[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：枚举 + 维护前缀最小值**

我们可以枚举数组 $nums$ 每个元素作为卖出价格，那么我们需要在前面找到一个最小值作为买入价格，这样才能使得利润最大化。

因此，我们用一个变量 $mi$ 维护数组 $nums$ 的前缀最小值。接下来遍历数组 $nums$，对于每个元素 $v$，计算其与前面元素的最小值 $mi$ 的差值，更新答案为差值的最大值。然后更新 $mi = min(mi, v)$。继续遍历数组 $nums$，直到遍历结束。

最后返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        ans, mi = 0, inf
        for v in prices:
            ans = max(ans, v - mi)
            mi = min(mi, v)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0, mi = prices[0];
        for (int v : prices) {
            ans = Math.max(ans, v - mi);
            mi = Math.min(mi, v);
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
        int ans = 0, mi = prices[0];
        for (int& v : prices) {
            ans = max(ans, v - mi);
            mi = min(mi, v);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxProfit(prices []int) (ans int) {
	mi := prices[0]
	for _, v := range prices {
		ans = max(ans, v-mi)
		mi = min(mi, v)
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
    let ans = 0;
    let mi = prices[0];
    for (const v of prices) {
        ans = Math.max(ans, v - mi);
        mi = Math.min(mi, v);
    }
    return ans;
};
```

### **C#**

```cs
public class Solution {
    public int MaxProfit(int[] prices) {
        int ans = 0, mi = prices[0];
        foreach (int v in prices) {
            ans = Math.Max(ans, v - mi);
            mi = Math.Min(mi, v);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function maxProfit(prices: number[]): number {
    let ans = 0;
    let mi = prices[0];
    for (const v of prices) {
        ans = Math.max(ans, v - mi);
        mi = Math.min(mi, v);
    }
    return ans;
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

### **PHP**

```php
class Solution {
    /**
     * @param Integer[] $prices
     * @return Integer
     */
    function maxProfit($prices) {
        $win = 0;
        $minPrice = $prices[0];
        $len = count($prices);
        for ($i = 1; $i < $len; $i++) {
            $minPrice = min($minPrice, $prices[$i]);
            $win = max($win, $prices[$i] - $minPrice);
        }
        return $win;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
