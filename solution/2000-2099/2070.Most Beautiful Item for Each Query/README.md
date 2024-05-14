# [2070. 每一个查询的最大美丽值](https://leetcode.cn/problems/most-beautiful-item-for-each-query)

[English Version](/solution/2000-2099/2070.Most%20Beautiful%20Item%20for%20Each%20Query/README_EN.md)

<!-- tags:数组,二分查找,排序 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维整数数组&nbsp;<code>items</code>&nbsp;，其中&nbsp;<code>items[i] = [price<sub>i</sub>, beauty<sub>i</sub>]</code>&nbsp;分别表示每一个物品的 <strong>价格</strong>&nbsp;和 <strong>美丽值</strong>&nbsp;。</p>

<p>同时给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>queries</code>&nbsp;。对于每个查询&nbsp;<code>queries[j]</code>&nbsp;，你想求出价格小于等于&nbsp;<code>queries[j]</code>&nbsp;的物品中，<strong>最大的美丽值</strong>&nbsp;是多少。如果不存在符合条件的物品，那么查询的结果为&nbsp;<code>0</code>&nbsp;。</p>

<p>请你返回一个长度与 <code>queries</code>&nbsp;相同的数组<em>&nbsp;</em><code>answer</code>，其中<em>&nbsp;</em><code>answer[j]</code>是第&nbsp;<code>j</code>&nbsp;个查询的答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
<b>输出：</b>[2,4,5,5,6,6]
<strong>解释：</strong>
- queries[0]=1 ，[1,2] 是唯一价格 &lt;= 1 的物品。所以这个查询的答案为 2 。
- queries[1]=2 ，符合条件的物品有 [1,2] 和 [2,4] 。
  它们中的最大美丽值为 4 。
- queries[2]=3 和 queries[3]=4 ，符合条件的物品都为 [1,2] ，[3,2] ，[2,4] 和 [3,5] 。
  它们中的最大美丽值为 5 。
- queries[4]=5 和 queries[5]=6 ，所有物品都符合条件。
  所以，答案为所有物品中的最大美丽值，为 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
<b>输出：</b>[4]
<b>解释：</b>
每个物品的价格均为 1 ，所以我们选择最大美丽值 4 。
注意，多个物品可能有相同的价格和美丽值。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>items = [[10,1000]], queries = [5]
<b>输出：</b>[0]
<strong>解释：</strong>
没有物品的价格小于等于 5 ，所以没有物品可以选择。
因此，查询的结果为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= items.length, queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>1 &lt;= price<sub>i</sub>, beauty<sub>i</sub>, queries[j] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：排序 + 离线查询

对于每一个查询，我们需要找到价格小于等于查询价格的物品中的最大美丽值，我们不妨采用离线查询的方式，先对物品按价格排序，然后对查询按照价格排序。

接下来，我们从小到大遍历查询，对于每一个查询，我们用一个指针 $i$ 指向物品数组，如果物品的价格小于等于查询价格，我们更新当前的最大美丽值，向右移动指针 $i$，直到物品的价格大于查询价格，我们将当前的最大美丽值记录下来，就是当前查询的答案。继续遍历下一个查询，直到所有的查询都处理完。

时间复杂度 $(n \times \log n + m \times \log m)$，空间复杂度 $O(\log n + m)$。其中 $n$ 和 $m$ 分别为物品数组和查询数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumBeauty(self, items: List[List[int]], queries: List[int]) -> List[int]:
        items.sort()
        n, m = len(items), len(queries)
        ans = [0] * len(queries)
        i = mx = 0
        for q, j in sorted(zip(queries, range(m))):
            while i < n and items[i][0] <= q:
                mx = max(mx, items[i][1])
                i += 1
            ans[j] = mx
        return ans
```

```java
class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> a[0] - b[0]);
        int n = items.length;
        int m = queries.length;
        int[] ans = new int[m];
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[i] - queries[j]);
        int i = 0, mx = 0;
        for (int j : idx) {
            while (i < n && items[i][0] <= queries[j]) {
                mx = Math.max(mx, items[i][1]);
                ++i;
            }
            ans[j] = mx;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> maximumBeauty(vector<vector<int>>& items, vector<int>& queries) {
        sort(items.begin(), items.end());
        int n = items.size();
        int m = queries.size();
        vector<int> idx(m);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return queries[i] < queries[j];
        });
        int mx = 0, i = 0;
        vector<int> ans(m);
        for (int j : idx) {
            while (i < n && items[i][0] <= queries[j]) {
                mx = max(mx, items[i][1]);
                ++i;
            }
            ans[j] = mx;
        }
        return ans;
    }
};
```

```go
func maximumBeauty(items [][]int, queries []int) []int {
	sort.Slice(items, func(i, j int) bool {
		return items[i][0] < items[j][0]
	})
	n, m := len(items), len(queries)
	idx := make([]int, m)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return queries[idx[i]] < queries[idx[j]] })
	ans := make([]int, m)
	i, mx := 0, 0
	for _, j := range idx {
		for i < n && items[i][0] <= queries[j] {
			mx = max(mx, items[i][1])
			i++
		}
		ans[j] = mx
	}
	return ans
}
```

```ts
function maximumBeauty(items: number[][], queries: number[]): number[] {
    const n = items.length;
    const m = queries.length;
    items.sort((a, b) => a[0] - b[0]);
    const idx: number[] = Array(m)
        .fill(0)
        .map((_, i) => i);
    idx.sort((i, j) => queries[i] - queries[j]);
    let [i, mx] = [0, 0];
    const ans: number[] = Array(m).fill(0);
    for (const j of idx) {
        while (i < n && items[i][0] <= queries[j]) {
            mx = Math.max(mx, items[i][1]);
            ++i;
        }
        ans[j] = mx;
    }
    return ans;
}
```

<!-- tabs:end -->

### 方法二：排序 + 二分查找

我们可以将物品按照价格排序，然后预处理出小于等于每个价格的物品中的最大美丽值，记录在数组 $mx$ 或者原 $items$ 数组中。

对于每一个查询，我们可以使用二分查找找到第一个价格大于查询价格的物品的下标 $j$，然后 $j - 1$ 就是小于等于查询价格且最大美丽值的物品的下标，添加到答案中。

时间复杂度 $O((m + n) \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为物品数组和查询数组的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumBeauty(self, items: List[List[int]], queries: List[int]) -> List[int]:
        items.sort()
        prices = [p for p, _ in items]
        n = len(items)
        mx = [items[0][1]]
        for i in range(1, n):
            mx.append(max(mx[i - 1], items[i][1]))
        ans = []
        for q in queries:
            j = bisect_right(prices, q) - 1
            ans.append(0 if j < 0 else mx[j])
        return ans
```

```java
class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> a[0] - b[0]);
        int n = items.length;
        int m = queries.length;
        int[] prices = new int[n];
        prices[0] = items[0][0];
        for (int i = 1; i < n; ++i) {
            prices[i] = items[i][0];
            items[i][1] = Math.max(items[i - 1][1], items[i][1]);
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int j = Arrays.binarySearch(prices, queries[i] + 1);
            j = j < 0 ? -j - 2 : j - 1;
            ans[i] = j < 0 ? 0 : items[j][1];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> maximumBeauty(vector<vector<int>>& items, vector<int>& queries) {
        sort(items.begin(), items.end());
        int n = items.size();
        int m = queries.size();
        vector<int> prices(n, items[0][0]);
        for (int i = 1; i < n; ++i) {
            prices[i] = items[i][0];
            items[i][1] = max(items[i - 1][1], items[i][1]);
        }
        vector<int> ans;
        for (int q : queries) {
            int j = upper_bound(prices.begin(), prices.end(), q) - prices.begin() - 1;
            ans.push_back(j < 0 ? 0 : items[j][1]);
        }
        return ans;
    }
};
```

```go
func maximumBeauty(items [][]int, queries []int) []int {
	sort.Slice(items, func(i, j int) bool {
		return items[i][0] < items[j][0]
	})
	n, m := len(items), len(queries)
	prices := make([]int, n)
	prices[0] = items[0][0]
	for i := 1; i < n; i++ {
		prices[i] = items[i][0]
		items[i][1] = max(items[i][1], items[i-1][1])
	}
	ans := make([]int, m)
	for i, q := range queries {
		j := sort.SearchInts(prices, q+1) - 1
		if j >= 0 {
			ans[i] = items[j][1]
		}
	}
	return ans
}
```

```ts
function maximumBeauty(items: number[][], queries: number[]): number[] {
    items.sort((a, b) => a[0] - b[0]);
    const n = items.length;
    for (let i = 1; i < n; ++i) {
        items[i][1] = Math.max(items[i][1], items[i - 1][1]);
    }
    const ans: number[] = [];
    for (const q of queries) {
        let l = 0,
            r = n;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (items[mid][0] > q) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        ans.push(--l >= 0 ? items[l][1] : 0);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
