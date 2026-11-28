---
comments: true
difficulty: Medium
rating: 2215
source: Weekly Contest 504 Q3
tags:
    - Greedy
    - Array
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3947. Maximum Number of Items From Sale II](https://leetcode.com/problems/maximum-number-of-items-from-sale-ii)

[中文文档](/solution/3900-3999/3947.Maximum%20Number%20of%20Items%20From%20Sale%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a 2D integer array <code>items</code>, where <code>items[i] = [factor<sub>i</sub>, price<sub>i</sub>]</code> represents the <code>i<sup>th</sup></code> item. You are also given an integer <code>budget</code>.</p>

<p>There are unlimited copies of each item available for purchase. You may buy any number of copies of any items such that the total cost of the purchased copies is at most <code>budget</code>.</p>

<p>After buying items, you may receive free copies according to the following rules:</p>

<ul>
	<li>Each purchased copy of item <code>i</code> can give you <strong>at most one</strong> free copy of another item <code>j</code>.</li>
	<li>The free item must satisfy <code>i != j</code> and <code>factor<sub>i</sub></code> divides <code>factor<sub>j</sub></code>.</li>
	<li>For each ordered pair <code>(i, j)</code>, you can receive a free copy of item <code>j</code> from purchases of item <code>i</code> <strong>at most once</strong>, regardless of how many copies of item <code>i</code> you buy.</li>
	<li>The same item <code>j</code> can be received multiple times for free if it is received from purchases of different item types.</li>
</ul>

<p>Return the <strong>maximum total number of item copies</strong> you can obtain, including both purchased copies and free copies, while spending at most <code>budget</code> on purchased items.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">items = [[1,6],[2,4],[3,5]], budget = 19</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>You can buy 2 copies of item 0 and 1 copy of item 1 for a total cost of <code>2 * 6 + 4 = 16</code>, which is not greater than <code>budget = 19</code>.</li>
	<li>One purchased copy of item 0 gives 1 free copy of item 1, because <code>factor<sub>0</sub> = 1</code> divides <code>factor<sub>1</sub> = 2</code>.</li>
	<li>The other purchased copy of item 0 gives 1 free copy of item 2, because <code>factor<sub>0</sub> = 1</code> divides <code>factor<sub>2</sub> = 3</code>.</li>
	<li>You leave with 3 purchased copies and 2 free copies, for a total of 5 item copies.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">items = [[2,8],[1,10],[6,6],[4,12],[5,20],[5,17]], budget = 35</span></p>

<p><strong>Output:</strong> <span class="example-io">7</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>You can buy 2 copies of item 0, 1 copy of item 1, and 1 copy of item 2 for a total cost of <code>2 * 8 + 10 + 6 = 32</code>, which is not greater than <code>budget = 35</code>.</li>
	<li>One purchased copy of item 0 gives 1 free copy of item 2, because <code>factor<sub>0</sub> = 2</code> divides <code>factor<sub>2</sub> = 6</code>.</li>
	<li>The other purchased copy of item 0 gives 1 free copy of item 3, because <code>factor<sub>0</sub> = 2</code> divides <code>factor<sub>3</sub> = 4</code>.</li>
	<li>The purchased copy of item 1 gives 1 free copy of item 2, because <code>factor<sub>1</sub> = 1</code> divides <code>factor<sub>2</sub> = 6</code>.</li>
	<li>Buying item 2 gives no free copy, because <code>factor<sub>2</sub> = 6</code> does not divide the factor of any other item.</li>
	<li>You leave with 4 purchased copies and 3 free copies, for a total of 7 item copies.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items.length &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i] = [factor<sub>i</sub>, price<sub>i</sub>]</code></li>
	<li><code>1 &lt;= factor<sub>i</sub> &lt;= items.length</code></li>
	<li><code>1 &lt;= price<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= budget &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Compared with part I, both the number of items and the budget are huge, so knapsack is gone. Free gifts follow divisibility and each $\textit{factor}$ is at most $n$, so multiples can count how many types a factor unlocks.
>
> The shape of an optimum is still “one paid trigger plus leftover budget on the cheapest item”. Each candidate first item’s gift count must be obtained in near-linear time and compared against its price.
>
> This directory has no implemented solution yet; the walkthrough stops at replacing the knapsack by a multiples count.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        n = len(items)
        mn = min(p for f, p in items)
        mx = max(f for f, p in items)

        freq = [0] * (mx + 1)
        for f, p in items:
            freq[f] += 1

        cnt = [0] * (mx + 1)
        for f in range(1, mx + 1):
            for j in range(f, mx + 1, f):
                cnt[f] += freq[j]
            cnt[f] -= 1

        deals = [
            (p, cnt[f])
            for f, p in items
            if cnt[f] > 0 and p <= 2 * mn
        ]

        deals.sort()

        ans = 0
        for p, c in deals:
            take = min(c, budget // p)
            ans += take * 2
            budget -= take * p

        return ans + budget // mn
```

#### Java

```java
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int maxF = 0, minP = Integer.MAX_VALUE;

        for (int[] item : items) {
            maxF = Math.max(maxF, item[0]);
            minP = Math.min(minP, item[1]);
        }

        int[] freq = new int[maxF + 1];
        int[] cnt = new int[maxF + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        for (int f = 1; f <= maxF; f++) {
            for (int j = f; j <= maxF; j += f) {
                cnt[f] += freq[j];
            }
            cnt[f]--;
        }

        List<int[]> deals = new ArrayList<>();

        for (int[] item : items) {
            int f = item[0], p = item[1];

            if (cnt[f] > 0 && p <= 2 * minP) {
                deals.add(new int[]{p, cnt[f]});
            }
        }

        deals.sort(Comparator.comparingInt(a -> a[0]));

        int ans = 0;

        for (int[] deal : deals) {
            int take = Math.min(deal[1], budget / deal[0]);
            ans += take * 2;
            budget -= take * deal[0];
        }

        return ans + budget / minP;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumSaleItems(vector<vector<int>>& items, int budget) {
        int n = items.size(), mx = 0, mn = INT_MAX;

        for (auto& a : items) {
            mx = max(mx, a[0]);
            mn = min(mn, a[1]);
        }

        vector<int> freq(mx + 1), cnt(mx + 1);

        for (auto& a : items)
            freq[a[0]]++;

        for (int i = 1; i <= mx; i++) {
            for (int j = i; j <= mx; j += i)
                cnt[i] += freq[j];
            cnt[i]--;
        }

        vector<pair<int, int>> deals;

        for (auto& a : items) {
            if (cnt[a[0]] > 0 && a[1] <= 2 * mn)
                deals.push_back({a[1], cnt[a[0]]});
        }

        sort(deals.begin(), deals.end());

        long long ans = 0;

        for (auto [p, c] : deals) {
            int take = min(c, budget / p);
            ans += 2LL * take;
            budget -= take * p;
        }

        return ans + budget / mn;
    }
};
```

#### Go

```go
import "sort"

func maximumSaleItems(items [][]int, budget int) int {
    n := len(items)
    freq := make([]int, n+1)
    gain := make([]int, n+1)

    minPrice := int(^uint(0) >> 1)

    for _, item := range items {
        freq[item[0]]++
        minPrice = min(minPrice, item[1])
    }

    for f := 1; f <= n; f++ {
        for j := f; j <= n; j += f {
            gain[f] += freq[j]
        }
        gain[f]--
    }

    type Deal struct {
        price, count int
    }

    deals := []Deal{}

    for _, item := range items {
        f, p := item[0], item[1]
        if gain[f] > 0 && p <= 2*minPrice {
            deals = append(deals, Deal{p, gain[f]})
        }
    }

    sort.Slice(deals, func(i, j int) bool {
        return deals[i].price < deals[j].price
    })

    ans := 0

    for _, d := range deals {
        take := min(d.count, budget/d.price)
        ans += 2 * take
        budget -= take * d.price
    }

    return ans + budget/minPrice
}
```

#### Rust

```rs
use std::collections::BTreeMap;

impl Solution {
    pub fn maximum_sale_items(items: Vec<Vec<i32>>, budget: i32) -> i32 {
        let n = items.len();
        let mut freq = vec![0i64; n + 1];
        let mut min_price = i64::MAX;

        for item in &items {
            freq[item[0] as usize] += 1;
            min_price = min_price.min(item[1] as i64);
        }

        let mut cnt = vec![0i64; n + 1];

        for f in 1..=n {
            for j in (f..=n).step_by(f) {
                cnt[f] += freq[j];
            }
            cnt[f] -= 1;
        }

        let mut deals = BTreeMap::<i64, i64>::new();

        for item in &items {
            let f = item[0] as usize;
            let p = item[1] as i64;

            if cnt[f] > 0 && p <= 2 * min_price {
                *deals.entry(p).or_insert(0) += cnt[f];
            }
        }

        let mut budget = budget as i64;
        let mut ans = 0i64;

        for (price, count) in deals {
            let take = count.min(budget / price);
            ans += take * 2;
            budget -= take * price;
        }

        (ans + budget / min_price) as i32
    }
}
```

#### TypeScript

```ts
function maximumSaleItems(items: number[][], budget: number): number {
    const n = items.length;
    const freq = new Array(n + 1).fill(0);
    const cnt = new Array(n + 1).fill(0);

    let minPrice = Infinity;

    for (const [f, p] of items) {
        freq[f]++;
        minPrice = Math.min(minPrice, p);
    }

    for (let f = 1; f <= n; f++) {
        for (let j = f; j <= n; j += f) {
            cnt[f] += freq[j];
        }
        cnt[f]--;
    }

    const deals: number[][] = [];

    for (const [f, p] of items) {
        if (cnt[f] > 0 && p <= 2 * minPrice) {
            deals.push([p, cnt[f]]);
        }
    }

    deals.sort((a, b) => a[0] - b[0]);

    let ans = 0;

    for (const [price, count] of deals) {
        const take = Math.min(count, Math.floor(budget / price));
        ans += take * 2;
        budget -= take * price;
    }

    return ans + Math.floor(budget / minPrice);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
