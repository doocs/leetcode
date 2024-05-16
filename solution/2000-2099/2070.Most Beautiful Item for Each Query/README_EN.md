---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2070.Most%20Beautiful%20Item%20for%20Each%20Query/README_EN.md
rating: 1724
source: Biweekly Contest 65 Q3
tags:
    - Array
    - Binary Search
    - Sorting
---

# [2070. Most Beautiful Item for Each Query](https://leetcode.com/problems/most-beautiful-item-for-each-query)

[中文文档](/solution/2000-2099/2070.Most%20Beautiful%20Item%20for%20Each%20Query/README.md)

## Description

<p>You are given a 2D integer array <code>items</code> where <code>items[i] = [price<sub>i</sub>, beauty<sub>i</sub>]</code> denotes the <strong>price</strong> and <strong>beauty</strong> of an item respectively.</p>

<p>You are also given a <strong>0-indexed</strong> integer array <code>queries</code>. For each <code>queries[j]</code>, you want to determine the <strong>maximum beauty</strong> of an item whose <strong>price</strong> is <strong>less than or equal</strong> to <code>queries[j]</code>. If no such item exists, then the answer to this query is <code>0</code>.</p>

<p>Return <em>an array </em><code>answer</code><em> of the same length as </em><code>queries</code><em> where </em><code>answer[j]</code><em> is the answer to the </em><code>j<sup>th</sup></code><em> query</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
<strong>Output:</strong> [2,4,5,5,6,6]
<strong>Explanation:</strong>
- For queries[0]=1, [1,2] is the only item which has price &lt;= 1. Hence, the answer for this query is 2.
- For queries[1]=2, the items which can be considered are [1,2] and [2,4]. 
  The maximum beauty among them is 4.
- For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
  The maximum beauty among them is 5.
- For queries[4]=5 and queries[5]=6, all items can be considered.
  Hence, the answer for them is the maximum beauty of all items, i.e., 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
<strong>Output:</strong> [4]
<strong>Explanation:</strong> 
The price of every item is equal to 1, so we choose the item with the maximum beauty 4. 
Note that multiple items can have the same price and/or beauty.  
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> items = [[10,1000]], queries = [5]
<strong>Output:</strong> [0]
<strong>Explanation:</strong>
No item has a price less than or equal to 5, so no item can be chosen.
Hence, the answer to the query is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= items.length, queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>items[i].length == 2</code></li>
	<li><code>1 &lt;= price<sub>i</sub>, beauty<sub>i</sub>, queries[j] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Sorting + Offline Query

For each query, we need to find the maximum beauty value among the items with a price less than or equal to the query price. We can use the offline query method, first sort the items by price, and then sort the queries by price.

Next, we traverse the queries from small to large. For each query, we use a pointer $i$ to point to the item array. If the price of the item is less than or equal to the query price, we update the current maximum beauty value and move the pointer $i$ to the right until the price of the item is greater than the query price. We record the current maximum beauty value, which is the answer to the current query. Continue to traverse the next query until all queries are processed.

The time complexity is $O(n \times \log n + m \times \log m)$, and the space complexity is $O(\log n + m)$. Where $n$ and $m$ are the lengths of the item array and the query array, respectively.

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

### Solution 2: Sorting + Binary Search

We can sort the items by price, and then preprocess the maximum beauty value of the items that are less than or equal to each price, recorded in the array $mx$ or the original $items$ array.

For each query, we can use binary search to find the index $j$ of the first item with a price greater than the query price, then $j - 1$ is the index of the item with the maximum beauty value and a price less than or equal to the query price, which is added to the answer.

The time complexity is $O((m + n) \times \log n)$, and the space complexity is $O(n)$. Where $n$ and $m$ are the lengths of the item array and the query array, respectively.

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
