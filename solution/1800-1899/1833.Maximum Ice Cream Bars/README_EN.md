---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1833.Maximum%20Ice%20Cream%20Bars/README_EN.md
rating: 1252
source: Weekly Contest 237 Q2
tags:
    - Greedy
    - Array
    - Counting Sort
    - Sorting
---

<!-- problem:start -->

# [1833. Maximum Ice Cream Bars](https://leetcode.com/problems/maximum-ice-cream-bars)

[中文文档](/solution/1800-1899/1833.Maximum%20Ice%20Cream%20Bars/README.md)

## Description

<!-- description:start -->

<p>It is a sweltering summer day, and a boy wants to buy some ice cream bars.</p>

<p>At the store, there are <code>n</code> ice cream bars. You are given an array <code>costs</code> of length <code>n</code>, where <code>costs[i]</code> is the price of the <code>i<sup>th</sup></code> ice cream bar in coins. The boy initially has <code>coins</code> coins to spend, and he wants to buy as many ice cream bars as possible.&nbsp;</p>

<p><strong>Note:</strong> The boy can buy the ice cream bars in any order.</p>

<p>Return <em>the <strong>maximum</strong> number of ice cream bars the boy can buy with </em><code>coins</code><em> coins.</em></p>

<p>You must solve the problem by counting sort.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> costs = [1,3,2,4,1], coins = 7
<strong>Output:</strong> 4
<strong>Explanation: </strong>The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> costs = [10,6,8,7,7,8], coins = 5
<strong>Output:</strong> 0
<strong>Explanation: </strong>The boy cannot afford any of the ice cream bars.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> costs = [1,6,3,1,2,5], coins = 20
<strong>Output:</strong> 6
<strong>Explanation: </strong>The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>costs.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= costs[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= coins &lt;= 10<sup>8</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

To buy as many ice creams as possible, and they can be purchased in any order, we should prioritize choosing ice creams with lower prices.

Sort the $costs$ array, and then start buying from the ice cream with the lowest price, one by one, until it is no longer possible to buy, and return the number of ice creams that can be bought.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$, where $n$ is the length of the $costs$ array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxIceCream(self, costs: List[int], coins: int) -> int:
        costs.sort()
        for i, c in enumerate(costs):
            if coins < c:
                return i
            coins -= c
        return len(costs)
```

#### Java

```java
class Solution {
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int n = costs.length;
        for (int i = 0; i < n; ++i) {
            if (coins < costs[i]) {
                return i;
            }
            coins -= costs[i];
        }
        return n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxIceCream(vector<int>& costs, int coins) {
        sort(costs.begin(), costs.end());
        int n = costs.size();
        for (int i = 0; i < n; ++i) {
            if (coins < costs[i]) return i;
            coins -= costs[i];
        }
        return n;
    }
};
```

#### Go

```go
func maxIceCream(costs []int, coins int) int {
	sort.Ints(costs)
	for i, c := range costs {
		if coins < c {
			return i
		}
		coins -= c
	}
	return len(costs)
}
```

#### TypeScript

```ts
function maxIceCream(costs: number[], coins: number): number {
    costs.sort((a, b) => a - b);
    const n = costs.length;
    for (let i = 0; i < n; ++i) {
        if (coins < costs[i]) {
            return i;
        }
        coins -= costs[i];
    }
    return n;
}
```

#### JavaScript

```js
/**
 * @param {number[]} costs
 * @param {number} coins
 * @return {number}
 */
var maxIceCream = function (costs, coins) {
    costs.sort((a, b) => a - b);
    const n = costs.length;
    for (let i = 0; i < n; ++i) {
        if (coins < costs[i]) {
            return i;
        }
        coins -= costs[i];
    }
    return n;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
