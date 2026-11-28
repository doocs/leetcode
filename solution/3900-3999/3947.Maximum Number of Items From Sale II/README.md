---
comments: true
difficulty: 中等
rating: 2215
source: 第 504 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [3947. 购买最多物品数目 II](https://leetcode.cn/problems/maximum-number-of-items-from-sale-ii)

[English Version](/solution/3900-3999/3947.Maximum%20Number%20of%20Items%20From%20Sale%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二维整数数组 <code>items</code>，其中 <code>items[i] = [factor<sub>i</sub>, price<sub>i</sub>]</code> 表示下标为 <code>i</code> 的物品。同时给你一个整数 <code>budget</code>。</p>

<p>每种物品都有无限个可供购买。你可以购买任意数量的任意物品，但购买物品的总花费最多为 <code>budget</code>。</p>

<p>购买物品后，你可以根据以下规则获得免费的物品：</p>

<ul>
	<li>购买的每一份物品 <code>i</code> <strong>最多&nbsp;</strong>可以让你获得 <strong>一份</strong> 免费的其他物品 <code>j</code>。</li>
	<li>免费物品必须满足 <code>i != j</code> 且 <code>factor<sub>i</sub></code> 可以整除 <code>factor<sub>j</sub></code>。</li>
	<li>对于每个有序对 <code>(i, j)</code>，无论你购买了多少个物品 <code>i</code>，你从物品 <code>i</code> 的购买中&nbsp;<strong>最多只能一次&nbsp;</strong>免费获得物品 <code>j</code>。</li>
	<li>如果免费物品 <code>j</code> 是通过购买不同种类的物品获得的，那么同一种物品 <code>j</code> 可以被免费获得多次。</li>
</ul>

<p>返回你在购买物品花费最多为 <code>budget</code> 的前提下，能够获得的&nbsp;<strong>物品最大总数&nbsp;</strong>，包括购买的物品和免费的物品。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">items = [[1,6],[2,4],[3,5]], budget = 19</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>你可以购买 2 个物品 0 和 1 个物品 1，总花费为 <code>2 * 6 + 4 = 16</code>，不超过 <code>budget = 19</code>。</li>
	<li>购买的其中 1 个物品 0 可以免费获得 1 个物品 1，因为 <code>factor<sub>0</sub> = 1</code> 可以整除 <code>factor<sub>1</sub> = 2</code>。</li>
	<li>购买的另一个物品 0 可以免费获得 1 个物品 2，因为 <code>factor<sub>0</sub> = 1</code> 可以整除 <code>factor<sub>2</sub> = 3</code>。</li>
	<li>你最终拥有 3 个购买的物品和 2 个免费物品，总共 5 个物品。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">items = [[2,8],[1,10],[6,6],[4,12],[5,20],[5,17]], budget = 35</span></p>

<p><strong>输出：</strong> <span class="example-io">7</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>你可以购买 2 个物品 0、1 个物品 1 以及 1 个物品 2，总花费为 <code>2 * 8 + 10 + 6 = 32</code>，不超过 <code>budget = 35</code>。</li>
	<li>购买的其中 1 个物品 0 可以免费获得 1 个物品 2，因为 <code>factor<sub>0</sub> = 2</code> 可以整除 <code>factor<sub>2</sub> = 6</code>。</li>
	<li>购买的另一个物品 0 可以免费获得 1 个物品 3，因为 <code>factor<sub>0</sub> = 2</code> 可以整除 <code>factor<sub>3</sub> = 4</code>。</li>
	<li>购买的 1 个物品 1 可以免费获得 1 个物品 2，因为 <code>factor<sub>1</sub> = 1</code> 可以整除 <code>factor<sub>2</sub> = 6</code>。</li>
	<li>购买物品 2 没有获得免费物品，因为 <code>factor<sub>2</sub> = 6</code> 不能整除任何其他物品的 factor。</li>
	<li>你最终拥有 4 个购买的物品和 3 个免费物品，总共 7 个物品。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= items.length &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i] = [factor<sub>i</sub>, price<sub>i</sub>]</code></li>
	<li><code>1 &lt;= factor<sub>i</sub> &lt;= items.length</code></li>
	<li><code>1 &lt;= price<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= budget &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 相对 I，物品数与预算都升到很大，背包不再可行。赠送图由因子关系决定， $\textit{factor}$ 不超过 $n$，可按倍数计数每个因子能带走多少种类。
>
> 最优策略仍是「花一笔钱触发一次赠送，其余预算全买最便宜」。需要在线性或线性对数时间内对每个候选第一种物品算出赠送量，再与单价比较。
>
> 仓库中该题尚无实现代码，思考止于「倍数计数替代背包」。

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
cclass Solution:
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
