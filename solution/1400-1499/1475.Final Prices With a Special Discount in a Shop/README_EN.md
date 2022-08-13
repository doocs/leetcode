# [1475. Final Prices With a Special Discount in a Shop](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop)

[中文文档](/solution/1400-1499/1475.Final%20Prices%20With%20a%20Special%20Discount%20in%20a%20Shop/README.md)

## Description

<p>Given the array <code>prices</code> where <code>prices[i]</code> is the price of the <code>ith</code> item in a shop. There is a special discount for items in the shop, if you buy the <code>ith</code> item, then you will receive a discount equivalent to <code>prices[j]</code> where <code>j</code> is the <strong>minimum</strong>&nbsp;index such that <code>j &gt; i</code> and <code>prices[j] &lt;= prices[i]</code>, otherwise, you will not receive any discount at all.</p>

<p><em>Return an array where the <code>ith</code> element is the final price you will pay for the <code>ith</code> item of the shop considering the special discount.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [8,4,6,2,3]
<strong>Output:</strong> [4,2,4,2,3]
<strong>Explanation:</strong>&nbsp;
For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.&nbsp;
For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.&nbsp;
For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.&nbsp;
For items 3 and 4 you will not receive any discount at all.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,2,3,4,5]
<strong>Output:</strong> [1,2,3,4,5]
<strong>Explanation:</strong> In this case, for all items, you will not receive any discount at all.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [10,1,1,6]
<strong>Output:</strong> [9,0,1,6]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 500</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10^3</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        stk = []
        ans = prices[:]
        for i, v in enumerate(prices):
            while stk and prices[stk[-1]] >= v:
                ans[stk.pop()] -= v
            stk.append(i)
        return ans
```

### **Java**

```java
class Solution {
    public int[] finalPrices(int[] prices) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = prices.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = prices[i];
            while (!stk.isEmpty() && prices[stk.peek()] >= prices[i]) {
                ans[stk.pop()] -= prices[i];
            }
            stk.push(i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> finalPrices(vector<int>& prices) {
        stack<int> stk;
        vector<int> ans = prices;
        for (int i = 0; i < prices.size(); ++i) {
            while (!stk.empty() && prices[stk.top()] >= prices[i]) {
                ans[stk.top()] -= prices[i];
                stk.pop();
            }
            stk.push(i);
        }
        return ans;
    }
};
```

### **Go**

```go
func finalPrices(prices []int) []int {
	var stk []int
	n := len(prices)
	ans := make([]int, n)
	for i, v := range prices {
		ans[i] = v
		for len(stk) > 0 && prices[stk[len(stk)-1]] >= v {
			ans[stk[len(stk)-1]] -= v
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	return ans
}
```

### **TypeScript**

```ts
function finalPrices(prices: number[]): number[] {
    const n = prices.length;
    const stack = [];
    const res = new Array(n);
    for (let i = n - 1; i >= 0; i--) {
        const price = prices[i];
        while (stack.length !== 0 && stack[stack.length - 1] > price) {
            stack.pop();
        }
        res[i] = price - (stack[stack.length - 1] ?? 0);
        stack.push(price);
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn final_prices(prices: Vec<i32>) -> Vec<i32> {
        let n = prices.len();
        let mut stack = Vec::new();
        let mut res = vec![0; n];
        for i in (0..n).rev() {
            let price = prices[i];
            while !stack.is_empty() && *stack.last().unwrap() > price {
                stack.pop();
            }
            res[i] = price - stack.last().unwrap_or(&0);
            stack.push(price);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
