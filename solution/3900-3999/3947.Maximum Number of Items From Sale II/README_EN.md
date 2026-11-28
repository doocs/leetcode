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

#### Python

```python
class Solution(object):
    def maximumSaleItems(self, items, budget):
        n = len(items)
        freq = [0] * (n + 1)
        min_price = float('inf')

        for factor, price in items:
            freq[factor] += 1
            if price < min_price:
                min_price = price

        div_count = [0] * (n + 1)
        for f in range(1, n + 1):
            if freq[f]:
                for m in range(f, n + 1, f):
                    div_count[f] += freq[m]

        sorted_items = sorted((price, div_count[factor] - 1) for factor, price in items)

        ans = 0
        rem = budget
        limit = 2 * min_price

        for price, gain in sorted_items:
            if price >= limit:
                break
            if gain <= 0:
                continue

            can_buy = min(gain, rem // price)
            ans += can_buy * 2
            rem -= can_buy * price

            if can_buy < gain:
                break

        ans += int(rem // min_price)
        return ans
```

#### Python3

```python
class Solution:
    def maximumSaleItems(self, items: List[List[int]], budget: int) -> int:
        n = len(items)
        freq = [0] * (n + 1)
        min_price = float('inf')

        for factor, price in items:
            freq[factor] += 1
            if price < min_price:
                min_price = price

        div_count = [0] * (n + 1)
        for f in range(1, n + 1):
            if freq[f]:
                for m in range(f, n + 1, f):
                    div_count[f] += freq[m]

        sorted_items = sorted((price, div_count[factor] - 1) for factor, price in items)

        ans = 0
        rem = budget
        limit = 2 * min_price

        for price, gain in sorted_items:
            if price >= limit:
                break
            if gain <= 0:
                continue

            can_buy = min(gain, rem // price)
            ans += can_buy * 2
            rem -= can_buy * price

            if can_buy < gain:
                break

        ans += rem // min_price
        return ans
```

#### Java

```java
class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] divCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                divCount[f] += freq[m];
            }
        }

        int[][] sortedItems = new int[n][2];
        for (int i = 0; i < n; i++) {
            sortedItems[i][0] = items[i][1];
            sortedItems[i][1] = divCount[items[i][0]] - 1;
        }

        Arrays.sort(sortedItems, Comparator.comparingInt(a -> a[0]));

        long rem = budget;
        long ans = 0;
        long limit = 2L * minPrice;

        for (int[] item : sortedItems) {
            int price = item[0];
            int gain = item[1];
            if (price >= limit) break;
            if (gain <= 0) continue;

            long canBuy = Math.min((long) gain, rem / price);
            ans += canBuy * 2;
            rem -= canBuy * price;

            if (canBuy < gain) break;
        }

        ans += rem / minPrice;
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumSaleItems(vector<vector<int>>& items, int budget) {
        int n = items.size();
        vector<int> freq(n + 1, 0);
        int min_price = 2e9;

        for (const auto& item : items) {
            freq[item[0]]++;
            min_price = min(min_price, item[1]);
        }

        vector<int> div_count(n + 1, 0);
        for (int f = 1; f <= n; ++f) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                div_count[f] += freq[m];
            }
        }

        vector<pair<int, int>> sorted_items;
        sorted_items.reserve(n);
        for (const auto& item : items) {
            sorted_items.push_back({item[1], div_count[item[0]] - 1});
        }
        sort(sorted_items.begin(), sorted_items.end());

        long long rem = budget;
        long long ans = 0;

        for (const auto& [price, gain] : sorted_items) {
            if (price >= 2LL * min_price) break;
            if (gain <= 0) continue;

            long long can_buy = min((long long)gain, rem / price);
            ans += can_buy * 2;
            rem -= can_buy * price;

            if (can_buy < gain) break;
        }

        ans += rem / min_price;
        return ans;
    }
};
```

#### Go

```go
func maximumSaleItems(items [][]int, budget int) int {
	n := len(items)
	freq := make([]int, n+1)
	minPrice := int(2e9)

	for _, item := range items {
		freq[item[0]]++
		if item[1] < minPrice {
			minPrice = item[1]
		}
	}

	divCount := make([]int, n+1)
	for f := 1; f <= n; f++ {
		if freq[f] == 0 {
			continue
		}
		for m := f; m <= n; m += f {
			divCount[f] += freq[m]
		}
	}

	type pair struct {
		price int
		gain  int
	}
	sortedItems := make([]pair, n)
	for i, item := range items {
		sortedItems[i] = pair{price: item[1], gain: divCount[item[0]] - 1}
	}

	sort.Slice(sortedItems, func(i, j int) bool {
		return sortedItems[i].price < sortedItems[j].price
	})

	rem := int64(budget)
	var ans int64 = 0
	limit := int64(2) * int64(minPrice)

	for _, item := range sortedItems {
		if int64(item.price) >= limit {
			break
		}
		if item.gain <= 0 {
			continue
		}

		canBuy := rem / int64(item.price)
		if int64(item.gain) < canBuy {
			canBuy = int64(item.gain)
		}

		ans += canBuy * 2
		rem -= canBuy * int64(item.price)

		if canBuy < int64(item.gain) {
			break
		}
	}

	ans += rem / int64(minPrice)
	return int(ans)
}
```

#### TypeScript

```ts
function maximumSaleItems(items: number[][], budget: number): number {
    const n = items.length;
    const have: number[] = Array(n + 1).fill(0); // have[f]: items with factor f
    let cheapest = Infinity;
    for (const [factor, price] of items) {
        have[factor]++;
        cheapest = Math.min(cheapest, price);
    }
    const deals: number[] = Array(n + 1).fill(-1); // counted when an item is reached

    const worth = items.filter(([, price]) => price < 2 * cheapest);
    const heap = MinPriorityQueue.fromArray(worth, ([, price]) => price); // built in O(n)
    let copies = 0;
    while (!heap.isEmpty() && heap.front()![1] <= budget) {
        // the cheapest deal left is affordable
        const [factor, price] = heap.dequeue()!;
        if (deals[factor] < 0)
            // from -1: the item itself is not a deal
            for (let multiple = factor; multiple <= n; multiple += factor)
                deals[factor] += have[multiple];
        const pairs = Math.min(deals[factor], Math.floor(budget / price));
        copies += 2 * pairs;
        budget -= pairs * price;
    }
    return copies + Math.floor(budget / cheapest);
}
```

#### JavaScript

```js
var maximumSaleItems = function (items, budget) {
    const n = items.length;
    const freq = new Int32Array(n + 1);
    let minPrice = Infinity;

    for (let i = 0; i < n; i++) {
        freq[items[i][0]]++;
        if (items[i][1] < minPrice) minPrice = items[i][1];
    }

    const divCount = new Int32Array(n + 1);
    for (let f = 1; f <= n; f++) {
        if (freq[f] === 0) continue;
        for (let m = f; m <= n; m += f) {
            divCount[f] += freq[m];
        }
    }

    const sortedItems = items.map(([factor, price]) => [price, divCount[factor] - 1]);
    sortedItems.sort((a, b) => a[0] - b[0]);

    let rem = budget;
    let ans = 0;
    const limit = 2 * minPrice;

    for (let i = 0; i < n; i++) {
        const [price, gain] = sortedItems[i];
        if (price >= limit) break;
        if (gain <= 0) continue;

        const canBuy = Math.min(gain, Math.floor(rem / price));
        ans += canBuy * 2;
        rem -= canBuy * price;

        if (canBuy < gain) break;
    }

    ans += Math.floor(rem / minPrice);
    return ans;
};
```

#### C#

```cs
public class Solution {
    public int MaximumSaleItems(int[][] items, int budget) {
        int n = items.Length;
        int[] freq = new int[n + 1];
        int minPrice = int.MaxValue;

        foreach (var item in items) {
            freq[item[0]]++;
            if (item[1] < minPrice) minPrice = item[1];
        }

        int[] divCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                divCount[f] += freq[m];
            }
        }

        var sortedItems = new (int price, int gain)[n];
        for (int i = 0; i < n; i++) {
            sortedItems[i] = (items[i][1], divCount[items[i][0]] - 1);
        }

        Array.Sort(sortedItems, (a, b) => a.price.CompareTo(b.price));

        long rem = budget;
        long ans = 0;
        long limit = 2L * minPrice;

        foreach (var (price, gain) in sortedItems) {
            if (price >= limit) break;
            if (gain <= 0) continue;

            long canBuy = Math.Min((long)gain, rem / price);
            ans += canBuy * 2;
            rem -= canBuy * price;

            if (canBuy < gain) break;
        }

        ans += rem / minPrice;
        return (int)ans;
    }
}
```

#### PHP

```php
class Solution {
    function maximumSaleItems($items, $budget) {
        $n = count($items);
        $freq = array_fill(0, $n + 1, 0);
        $minPrice = PHP_INT_MAX;

        foreach ($items as $item) {
            $freq[$item[0]]++;
            if ($item[1] < $minPrice) {
                $minPrice = $item[1];
            }
        }

        $divCount = array_fill(0, $n + 1, 0);
        for ($f = 1; $f <= $n; $f++) {
            if ($freq[$f] === 0) {
                continue;
            }
            for ($m = $f; $m <= $n; $m += $f) {
                $divCount[$f] += $freq[$m];
            }
        }

        $sortedItems = [];
        foreach ($items as $item) {
            $sortedItems[] = [$item[1], $divCount[$item[0]] - 1];
        }

        usort($sortedItems, fn($a, $b) => $a[0] <=> $b[0]);

        $rem = $budget;
        $ans = 0;
        $limit = 2 * $minPrice;

        foreach ($sortedItems as [$price, $gain]) {
            if ($price >= $limit) {
                break;
            }
            if ($gain <= 0) {
                continue;
            }

            $canBuy = min($gain, intdiv($rem, $price));
            $ans += $canBuy * 2;
            $rem -= $canBuy * $price;

            if ($canBuy < $gain) {
                break;
            }
        }

        $ans += intdiv($rem, $minPrice);
        return $ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
