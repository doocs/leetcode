---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1475.Final%20Prices%20With%20a%20Special%20Discount%20in%20a%20Shop/README_EN.md
rating: 1212
source: Biweekly Contest 28 Q1
tags:
    - Stack
    - Array
    - Monotonic Stack
---

<!-- problem:start -->

# [1475. Final Prices With a Special Discount in a Shop](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop)

[中文文档](/solution/1400-1499/1475.Final%20Prices%20With%20a%20Special%20Discount%20in%20a%20Shop/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>prices</code> where <code>prices[i]</code> is the price of the <code>i<sup>th</sup></code> item in a shop.</p>

<p>There is a special discount for items in the shop. If you buy the <code>i<sup>th</sup></code> item, then you will receive a discount equivalent to <code>prices[j]</code> where <code>j</code> is the minimum index such that <code>j &gt; i</code> and <code>prices[j] &lt;= prices[i]</code>. Otherwise, you will not receive any discount at all.</p>

<p>Return an integer array <code>answer</code> where <code>answer[i]</code> is the final price you will pay for the <code>i<sup>th</sup></code> item of the shop, considering the special discount.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> prices = [8,4,6,2,3]
<strong>Output:</strong> [4,2,4,2,3]
<strong>Explanation:</strong> 
For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
For items 3 and 4 you will not receive any discount at all.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> prices = [1,2,3,4,5]
<strong>Output:</strong> [1,2,3,4,5]
<strong>Explanation:</strong> In this case, for all items, you will not receive any discount at all.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> prices = [10,1,1,6]
<strong>Output:</strong> [9,0,1,6]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 500</code></li>
	<li><code>1 &lt;= prices[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Monotonic Stack

The problem is essentially to find the first element on the right side that is smaller than each element. We can use a monotonic stack to solve this.

We traverse the array $\textit{prices}$ in reverse order, using the monotonic stack to find the nearest smaller element on the left side of the current element, and then calculate the discount.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{prices}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def finalPrices(self, prices: List[int]) -> List[int]:
        stk = []
        for i in reversed(range(len(prices))):
            x = prices[i]
            while stk and x < stk[-1]:
                stk.pop()
            if stk:
                prices[i] -= stk[-1]
            stk.append(x)
        return prices
```

#### Java

```java
class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; --i) {
            int x = prices[i];
            while (!stk.isEmpty() && stk.peek() > x) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                prices[i] -= stk.peek();
            }
            stk.push(x);
        }
        return prices;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> finalPrices(vector<int>& prices) {
        stack<int> stk;
        for (int i = prices.size() - 1; ~i; --i) {
            int x = prices[i];
            while (!stk.empty() && stk.top() > x) {
                stk.pop();
            }
            if (!stk.empty()) {
                prices[i] -= stk.top();
            }
            stk.push(x);
        }
        return prices;
    }
};
```

#### Go

```go
func finalPrices(prices []int) []int {
	stk := []int{}
	for i := len(prices) - 1; i >= 0; i-- {
		x := prices[i]
		for len(stk) > 0 && stk[len(stk)-1] > x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			prices[i] -= stk[len(stk)-1]
		}
		stk = append(stk, x)
	}
	return prices
}
```

#### TypeScript

```ts
function finalPrices(prices: number[]): number[] {
    const stk: number[] = [];
    for (let i = prices.length - 1; ~i; --i) {
        const x = prices[i];
        while (stk.length && stk.at(-1)! > x) {
            stk.pop();
        }
        prices[i] -= stk.at(-1) || 0;
        stk.push(x);
    }
    return prices;
}
```

#### Rust

```rust
impl Solution {
    pub fn final_prices(mut prices: Vec<i32>) -> Vec<i32> {
        let mut stk: Vec<i32> = Vec::new();
        for i in (0..prices.len()).rev() {
            let x = prices[i];
            while !stk.is_empty() && x < *stk.last().unwrap() {
                stk.pop();
            }
            if let Some(&top) = stk.last() {
                prices[i] -= top;
            }
            stk.push(x);
        }
        prices
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} prices
 * @return {number[]}
 */
var finalPrices = function (prices) {
    const stk = [];
    for (let i = prices.length - 1; ~i; --i) {
        const x = prices[i];
        while (stk.length && stk.at(-1) > x) {
            stk.pop();
        }
        prices[i] -= stk.at(-1) || 0;
        stk.push(x);
    }
    return prices;
};
```

#### PHP

```php
class Solution {
    /**
     * @param Integer[] $prices
     * @return Integer[]
     */
    function finalPrices($prices) {
        $stk = [];
        $n = count($prices);

        for ($i = $n - 1; $i >= 0; $i--) {
            $x = $prices[$i];
            while (!empty($stk) && $x < end($stk)) {
                array_pop($stk);
            }
            if (!empty($stk)) {
                $prices[$i] -= end($stk);
            }
            $stk[] = $x;
        }

        return $prices;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
