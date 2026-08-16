---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4014.Minimum%20Total%20Price%20After%20Applying%20Discounts/README_EN.md
rating: 1192
source: Weekly Contest 514 Q1
---

<!-- problem:start -->

# [4014. Minimum Total Price After Applying Discounts](https://leetcode.com/problems/minimum-total-price-after-applying-discounts)

[中文文档](/solution/4000-4099/4014.Minimum%20Total%20Price%20After%20Applying%20Discounts/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>prices</code> and <code>discounts</code>.</p>

<p>The value <code>prices[i]</code> represents the price of the <code>i<sup>th</sup></code> item, and <code>discounts[j]</code> represents a discount percentage.</p>

<p>You may apply discounts subject to the following rules:</p>

<ul>
	<li>Each discount can be applied to <strong>at most</strong> one item.</li>
	<li>Each item can receive <strong>at most</strong> one discount.</li>
	<li>An item may also receive no discount.</li>
</ul>

<p>If a discount of <code>d</code> percent is applied to an item with price <code>p</code>, its final price becomes <code>(p * (100 - d)) / 100</code>. The final price is <strong>not</strong> rounded.</p>

<p>Return the <strong>minimum</strong> possible sum of final prices after assigning discounts optimally. Answers within <code>10<sup>-5</sup></code> of the actual answer will be accepted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [10,30,21], discounts = [50,60]</span></p>

<p><strong>Output:</strong> <span class="example-io">32.50000</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Apply <code>discounts[1] = 60</code> to <code>prices[1] = 30</code>, thus <code>30 * (100 - 60) / 100 = 12</code>.</li>
	<li>Apply <code>discounts[0] = 50</code> to <code>prices[2] = 21</code>, thus <code>21 * (100 - 50) / 100 = 10.5</code>.</li>
	<li><code>prices[0] = 10</code> receives no discount, so it stays 10.</li>
</ul>

<p>The total is <code>12 + 10.5 + 10 = 32.50000</code>, which is the minimum possible.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [100,70], discounts = [10,40,50]</span></p>

<p><strong>Output:</strong> <span class="example-io">92.00000</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>Apply <code>discounts[2] = 50</code> to <code>prices[0] = 100</code>, thus <code>100 * (100 - 50) / 100 = 50</code>.</li>
	<li>Apply <code>discounts[1] = 40</code> to <code>prices[1] = 70</code>, thus <code>70 * (100 - 40) / 100 = 42</code>.</li>
</ul>

<p>The total is <code>50 + 42 = 92.00000</code>, which is the minimum possible.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">prices = [7,3,9], discounts = [100,100]</span></p>

<p><strong>Output:</strong> <span class="example-io">3.00000</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Apply <code>discounts[0] = 100</code> to <code>prices[2] = 9</code>, thus <code>9 * (100 - 100) / 100 = 0</code>.</li>
	<li>Apply <code>discounts[1] = 100</code> to <code>prices[0] = 7</code>, thus <code>7 * (100 - 100) / 100 = 0</code>.</li>
	<li><code>prices[1] = 3</code> receives no discount, so it stays 3.</li>
</ul>

<p>The total is <code>0 + 0 + 3 = 3.00000</code>, which is the minimum possible.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= prices.length, discounts.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= discounts[j] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

To minimize the total price, we need to maximize the total amount saved by discounts. Applying a discount $d$ to an item with price $p$ saves $p \times d / 100$. By the rearrangement inequality, applying larger discounts to more expensive items maximizes the total savings.

Therefore, we sort both $\textit{prices}$ and $\textit{discounts}$ in ascending order, then use two pointers starting from the ends of both arrays, repeatedly applying the current largest discount to the current most expensive item and accumulating the discounted price. Once all discounts are used up, the remaining items are added at their original prices.

The time complexity is $O(n \times \log n + m \times \log m)$, and the space complexity is $O(\log n + \log m)$. Here, $n$ and $m$ are the lengths of the arrays $\textit{prices}$ and $\textit{discounts}$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minPrice(self, prices: list[int], discounts: list[int]) -> float:
        prices.sort()
        discounts.sort()
        i, j = len(prices) - 1, len(discounts) - 1
        ans = 0
        while i >= 0 and j >= 0:
            ans += prices[i] * (100 - discounts[j]) / 100
            i -= 1
            j -= 1
        while i >= 0:
            ans += prices[i]
            i -= 1
        return ans
```

#### Java

```java
class Solution {
    public double minPrice(int[] prices, int[] discounts) {
        Arrays.sort(prices);
        Arrays.sort(discounts);

        int i = prices.length - 1;
        int j = discounts.length - 1;

        double ans = 0;

        while (i >= 0 && j >= 0) {
            ans += prices[i] * (100 - discounts[j]) / 100.0;
            i--;
            j--;
        }

        while (i >= 0) {
            ans += prices[i];
            i--;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    double minPrice(vector<int>& prices, vector<int>& discounts) {
        sort(prices.begin(), prices.end());
        sort(discounts.begin(), discounts.end());

        int i = prices.size() - 1;
        int j = discounts.size() - 1;

        double ans = 0;

        while (i >= 0 && j >= 0) {
            ans += prices[i] * (100 - discounts[j]) / 100.0;
            i--;
            j--;
        }

        while (i >= 0) {
            ans += prices[i];
            i--;
        }

        return ans;
    }
};
```

#### Go

```go
func minPrice(prices []int, discounts []int) float64 {
	sort.Ints(prices)
	sort.Ints(discounts)

	i := len(prices) - 1
	j := len(discounts) - 1

	var ans float64

	for i >= 0 && j >= 0 {
		ans += float64(prices[i]) * float64(100-discounts[j]) / 100.0
		i--
		j--
	}

	for i >= 0 {
		ans += float64(prices[i])
		i--
	}

	return ans
}
```

#### TypeScript

```ts
function minPrice(prices: number[], discounts: number[]): number {
    prices.sort((a, b) => a - b);
    discounts.sort((a, b) => a - b);

    let i = prices.length - 1;
    let j = discounts.length - 1;

    let ans = 0;

    while (i >= 0 && j >= 0) {
        ans += (prices[i] * (100 - discounts[j])) / 100;
        i--;
        j--;
    }

    while (i >= 0) {
        ans += prices[i];
        i--;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
