# [面试题 63. 股票的最大利润](https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/)

## 题目描述

假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

**示例 1:**

```
输入: [7,1,5,3,6,4]
输出: 5
解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
```

**示例 2:**

```
输入: [7,6,4,3,1]
输出: 0
解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
```

**限制：**

- `0 <= 数组长度 <= 10^5`

## 解法

纯粹的说，此题是在数组当中寻找最大值与最小值，但存在一个限制条件，最大值必须在最小值的后面（相对数组中的存储位置）。

-   暴力解法
    -   双指针遍历，记录两数最大差值。
        ```txt
        for i = 0 in arr.length - 1
            for j = i in arr.length
                r = max(r, arr[j] - arr[i])
        ```
-   动态规划
    -   准备一变量记录最大差值，初始化为 0；一变量记录最小值，初始化为无限大。
    -   遍历数组，计算当前遍历元素与最小值的差值，并更新最大差值；再更新最小值。
        ```txt
        r = 0
        m = ∞
        for i = 0 in arr.length
            r = max(r, arr[i] - m)
            m = min(m, arr[i])
        ```

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        if len(prices) == 0:
            return 0
        mi = prices[0]
        res = 0
        for val in prices[1:]:
            res = max(res, val - mi)
            mi = min(mi, val)
        return res
```

### **Java**

```java
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }
        int min = prices[0];
        int res = 0;
        for (int i = 1; i < len; ++i) {
            res = Math.max(res, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return res;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} prices
 * @return {number}
 */
var maxProfit = function (prices) {
    let a = 0;
    let b = Infinity;
    for (let p of prices) {
        a = Math.max(a, p - b);
        b = Math.min(b, p);
    }
    return a;
};
```

### **C++**

```cpp
class Solution {
public:
    int maxProfit(vector<int>& prices) {
        if (prices.size() < 2) {
            return 0;    // 如果小于两个，直接返回0值
        }

        int curMin = prices[0];
        int maxDiff = prices[1] - prices[0];

        // 贪心循环，记录当前最小值，和最大diff值
        for (int i = 2; i < prices.size(); i++) {
            if (curMin > prices[i-1]) {
                curMin = prices[i-1];
            }

            int diff = prices[i] - curMin;
            if (maxDiff < diff) {
                maxDiff = diff;
            }
        }

        // 根据题意，如果是负数的话，则返回0值
        return maxDiff > 0 ? maxDiff : 0;
    }
};
```

### **Go**

```go
func maxProfit(prices []int) int {
	mi, mx := math.MaxInt32, 0
	for _, price := range prices {
		mx = max(mx, price-mi)
		mi = min(mi, price)
	}
    return mx
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
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

### **...**

```

```

<!-- tabs:end -->
