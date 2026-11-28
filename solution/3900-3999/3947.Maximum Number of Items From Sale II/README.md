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
