# [2706. Buy Two Chocolates](https://leetcode.com/problems/buy-two-chocolates)

[中文文档](/solution/2700-2799/2706.Buy%20Two%20Chocolates/README.md)

## Description

<p>You are given an integer array <code>prices</code> representing the prices of various chocolates in a store. You are also given a single integer <code>money</code>, which represents your initial amount of money.</p>

<p>You must buy <strong>exactly</strong> two chocolates in such a way that you still have some <strong>non-negative</strong> leftover money. You would like to minimize the sum of the prices of the two chocolates you buy.</p>

<p>Return <em>the amount of money you will have leftover after buying the two chocolates</em>. If there is no way for you to buy two chocolates without ending up in debt, return <code>money</code>. Note that the leftover must be non-negative.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,2,2], money = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> Purchase the chocolates priced at 1 and 2 units respectively. You will have 3 - 3 = 0 units of money afterwards. Thus, we return 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [3,2,3], money = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> You cannot buy 2 chocolates without going in debt, so we return 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= prices.length &lt;= 50</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 100</code></li>
	<li><code>1 &lt;= money &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def buyChoco(self, prices: List[int], money: int) -> int:
        prices.sort()
        cost = prices[0] + prices[1]
        return money if money < cost else money - cost
```

### **Java**

```java
class Solution {
    public int buyChoco(int[] prices, int money) {
        Arrays.sort(prices);
        int cost = prices[0] + prices[1];
        return money < cost ? money : money - cost;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int buyChoco(vector<int>& prices, int money) {
        sort(prices.begin(), prices.end());
        int cost = prices[0] + prices[1];
        return money < cost ? money : money - cost;
    }
};
```

### **Go**

```go
func buyChoco(prices []int, money int) int {
	sort.Ints(prices)
	cost := prices[0] + prices[1]
	if money < cost {
		return money
	}
	return money - cost
}
```

### **TypeScript**

```ts
function buyChoco(prices: number[], money: number): number {
    prices.sort((a, b) => a - b);
    const cost = prices[0] + prices[1];
    return money < cost ? money : money - cost;
}
```

### **Rust**

```rust
impl Solution {
    pub fn buy_choco(mut prices: Vec<i32>, money: i32) -> i32 {
        prices.sort();

        let sum = prices[0] + prices[1];
        if sum > money {
            return money;
        }

        money - sum
    }
}
```

```rust
impl Solution {
    pub fn buy_choco(mut prices: Vec<i32>, money: i32) -> i32 {
        prices.sort();

        let sum = prices.iter().take(2).sum::<i32>();

        if sum > money {
            return money;
        }

        money - sum
    }
}
```

### **...**

```

```

<!-- tabs:end -->
