---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3946.Maximum%20Number%20of%20Items%20From%20Sale%20I/README_EN.md
rating: 1728
source: Weekly Contest 504 Q2
---

<!-- problem:start -->

# [3946. Maximum Number of Items From Sale I](https://leetcode.com/problems/maximum-number-of-items-from-sale-i)

[中文文档](/solution/3900-3999/3946.Maximum%20Number%20of%20Items%20From%20Sale%20I/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>items</code>, where <code>items[i] = [factor<sub>i</sub>, price<sub>i</sub>]</code> represents the <code>i<sup>th</sup></code> item. You are also given an integer <code>budget</code>.</p>

<p>There are unlimited copies of each item available for purchase.You may buy any number of copies of any items such that the total cost of the purchased copies is at most <code>budget</code>.</p>

<p>After buying items, you may receive free copies according to the following rules:</p>

<ul>
	<li>For each item <code>i</code> that you bought <strong>at least one copy</strong> of, you receive <strong>one free copy</strong> of every item <code>j</code> such that <code>j != i</code> and <code>factor<sub>i</sub></code> divides <code>factor<sub>j</sub></code>.</li>
	<li>Buying multiple copies of the same item <code>i</code> does <strong>not</strong> give additional free copies through item <code>i</code>.</li>
	<li>The same item <code>j</code> can be received multiple times for free if it is received from purchases of different item types.</li>
</ul>

<p>Return the <strong>maximum total number of item copies</strong> you can obtain, including both purchased copies and free copies, while spending at most <code>budget</code> on purchased items.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">items = [[6,2],[2,6],[3,4]], budget = 9</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>You can buy 2 copies of item 0 and 1 copy of item 2 for a total cost of <code>2 * 2 + 4 = 8</code>, which is not greater than <code>budget = 9</code>.</li>
	<li>Buying item 2 gives 1 free copy of item 0, because <code>factor<sub>2</sub> = 3</code> divides <code>factor<sub>0</sub> = 6</code>.</li>
	<li>You leave with 3 purchased copies and 1 free copy, for a total of 4 item copies.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">items = [[2,4],[3,2],[4,1],[6,4],[12,4]], budget = 8</span></p>

<p><strong>Output:</strong> <span class="example-io">10</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>You can buy 1 copy of item 0, 1 copy of item 1, and 2 copies of item 2 for a total cost of <code>4 + 2 + 2 * 1 = 8</code>.</li>
	<li>Buying item 0 gives 1 free copy of items 2, 3, and 4.</li>
	<li>Buying item 1 gives 1 free copy of items 3 and 4.</li>
	<li>Buying item 2 gives 1 free copy of item 4.</li>
	<li>Thus, you receive 6 free copies. You leave with 4 purchased copies and 6 free copies, for a total of 10 item copies.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items.length &lt;= 1000</code></li>
	<li><code>items[i] = [factor<sub>i</sub>, price<sub>i</sub>]</code></li>
	<li><code>1 &lt;= factor<sub>i</sub>, price<sub>i</sub> &lt;= 1500</code></li>
	<li><code>1 &lt;= budget &lt;= 1500</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Dynamic Programming (0-1 Knapsack)

Since buying the first item of a type is special and yields free items, we consider the first purchased item separately from the later purchases.

For the first purchased item, suppose we spend a budget of $i$ and obtain $f[i]$ items in total, including both the purchased item and the free items. For the later purchases, we can use the remaining budget $\text{budget} - i$ to buy the cheapest item, obtaining $\lfloor \frac{\text{budget} - i}{\text{mn}} \rfloor$ items, where $\text{mn}$ is the minimum price among all items. Therefore, we enumerate the budget $i$ spent on the first purchase and compute the maximum value of $f[i] + \lfloor \frac{\text{budget} - i}{\text{mn}} \rfloor$, which is the final answer.

The time complexity is $O(n^2 + n \times m)$, where $n$ is the number of items and $m$ is the budget.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        f = [0] * (budget + 1)
        mn = inf
        for factor, price in items:
            mn = min(mn, price)
            cnt = sum(factor_j % factor == 0 for factor_j, _ in items)
            for j in range(budget, price - 1, -1):
                f[j] = max(f[j], f[j - price] + cnt)
        return max(x + (budget - i) // mn for i, x in enumerate(f))
```

#### Java

```java
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int[] f = new int[budget + 1];
        int mn = Integer.MAX_VALUE;

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];

            mn = Math.min(mn, price);

            int cnt = 0;
            for (int[] jItem : items) {
                if (jItem[0] % factor == 0) {
                    cnt++;
                }
            }

            for (int j = budget; j >= price; j--) {
                f[j] = Math.max(f[j], f[j - price] + cnt);
            }
        }

        int ans = 0;
        for (int i = 0; i <= budget; i++) {
            ans = Math.max(ans, f[i] + (budget - i) / mn);
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumSaleItems(vector<vector<int>>& items, int budget) {
        vector<int> f(budget + 1, 0);
        int mn = INT_MAX;

        for (const auto& item : items) {
            int factor = item[0];
            int price = item[1];

            mn = min(mn, price);

            int cnt = 0;
            for (const auto& jItem : items) {
                if (jItem[0] % factor == 0) {
                    cnt++;
                }
            }

            for (int j = budget; j >= price; --j) {
                f[j] = max(f[j], f[j - price] + cnt);
            }
        }

        int ans = 0;
        for (int i = 0; i <= budget; ++i) {
            ans = max(ans, f[i] + (budget - i) / mn);
        }

        return ans;
    }
};
```

#### Go

```go
func maximumSaleItems(items [][]int, budget int) int {
	f := make([]int, budget+1)
	mn := math.MaxInt32

	for _, item := range items {
		factor := item[0]
		price := item[1]
		mn = min(mn, price)

		cnt := 0
		for _, jItem := range items {
			if jItem[0]%factor == 0 {
				cnt++
			}
		}

		for j := budget; j >= price; j-- {
			if f[j-price]+cnt > f[j] {
				f[j] = f[j-price] + cnt
			}
		}
	}

	ans := 0
	for i, x := range f {
		extra := (budget - i) / mn
		ans = max(ans, x+extra)
	}

	return ans
}
```

#### TypeScript

```ts
function maximumSaleItems(items: number[][], budget: number): number {
    const f: number[] = new Array(budget + 1).fill(0);
    let mn: number = Infinity;

    for (const [factor, price] of items) {
        mn = Math.min(mn, price);

        let cnt = 0;
        for (const [factor_j, _] of items) {
            if (factor_j % factor === 0) {
                cnt++;
            }
        }

        for (let j = budget; j >= price; j--) {
            f[j] = Math.max(f[j], f[j - price] + cnt);
        }
    }

    let ans = 0;
    for (let i = 0; i <= budget; i++) {
        ans = Math.max(ans, f[i] + Math.floor((budget - i) / mn));
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
