---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2931.Maximum%20Spending%20After%20Buying%20Items/README_EN.md
rating: 1822
source: Biweekly Contest 117 Q4
tags:
    - Greedy
    - Array
    - Matrix
    - Sorting
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [2931. Maximum Spending After Buying Items](https://leetcode.com/problems/maximum-spending-after-buying-items)

[中文文档](/solution/2900-2999/2931.Maximum%20Spending%20After%20Buying%20Items/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <code>m * n</code> integer matrix <code>values</code>, representing the values of <code>m * n</code> different items in <code>m</code> different shops. Each shop has <code>n</code> items where the <code>j<sup>th</sup></code> item in the <code>i<sup>th</sup></code> shop has a value of <code>values[i][j]</code>. Additionally, the items in the <code>i<sup>th</sup></code> shop are sorted in non-increasing order of value. That is, <code>values[i][j] &gt;= values[i][j + 1]</code> for all <code>0 &lt;= j &lt; n - 1</code>.</p>

<p>On each day, you would like to buy a single item from one of the shops. Specifically, On the <code>d<sup>th</sup></code> day you can:</p>

<ul>
	<li>Pick any shop <code>i</code>.</li>
	<li>Buy the rightmost available item <code>j</code> for the price of <code>values[i][j] * d</code>. That is, find the greatest index <code>j</code> such that item <code>j</code> was never bought before, and buy it for the price of <code>values[i][j] * d</code>.</li>
</ul>

<p><strong>Note</strong> that all items are pairwise different. For example, if you have bought item <code>0</code> from shop <code>1</code>, you can still buy item <code>0</code> from any other shop.</p>

<p>Return <em>the <strong>maximum amount of money that can be spent</strong> on buying all </em> <code>m * n</code> <em>products</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> values = [[8,5,2],[6,4,1],[9,7,3]]
<strong>Output:</strong> 285
<strong>Explanation:</strong> On the first day, we buy product 2 from shop 1 for a price of values[1][2] * 1 = 1.
On the second day, we buy product 2 from shop 0 for a price of values[0][2] * 2 = 4.
On the third day, we buy product 2 from shop 2 for a price of values[2][2] * 3 = 9.
On the fourth day, we buy product 1 from shop 1 for a price of values[1][1] * 4 = 16.
On the fifth day, we buy product 1 from shop 0 for a price of values[0][1] * 5 = 25.
On the sixth day, we buy product 0 from shop 1 for a price of values[1][0] * 6 = 36.
On the seventh day, we buy product 1 from shop 2 for a price of values[2][1] * 7 = 49.
On the eighth day, we buy product 0 from shop 0 for a price of values[0][0] * 8 = 64.
On the ninth day, we buy product 0 from shop 2 for a price of values[2][0] * 9 = 81.
Hence, our total spending is equal to 285.
It can be shown that 285 is the maximum amount of money that can be spent buying all m * n products. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> values = [[10,8,6,4,2],[9,7,5,3,2]]
<strong>Output:</strong> 386
<strong>Explanation:</strong> On the first day, we buy product 4 from shop 0 for a price of values[0][4] * 1 = 2.
On the second day, we buy product 4 from shop 1 for a price of values[1][4] * 2 = 4.
On the third day, we buy product 3 from shop 1 for a price of values[1][3] * 3 = 9.
On the fourth day, we buy product 3 from shop 0 for a price of values[0][3] * 4 = 16.
On the fifth day, we buy product 2 from shop 1 for a price of values[1][2] * 5 = 25.
On the sixth day, we buy product 2 from shop 0 for a price of values[0][2] * 6 = 36.
On the seventh day, we buy product 1 from shop 1 for a price of values[1][1] * 7 = 49.
On the eighth day, we buy product 1 from shop 0 for a price of values[0][1] * 8 = 64
On the ninth day, we buy product 0 from shop 1 for a price of values[1][0] * 9 = 81.
On the tenth day, we buy product 0 from shop 0 for a price of values[0][0] * 10 = 100.
Hence, our total spending is equal to 386.
It can be shown that 386 is the maximum amount of money that can be spent buying all m * n products.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= m == values.length &lt;= 10</code></li>
	<li><code>1 &lt;= n == values[i].length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= values[i][j] &lt;= 10<sup>6</sup></code></li>
	<li><code>values[i]</code> are sorted in non-increasing order.</li>
</ul>

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Priority Queue

According to the problem description, we should prioritize purchasing items with smaller values and leave items with larger values to be purchased later in order to maximize the total cost. Therefore, we use a priority queue (min-heap) to store the smallest value item that has not been purchased in each store. Initially, we add the rightmost item in each store to the priority queue.

Each day, we take out the item with the smallest value from the priority queue, add it to the answer, and add the previous item in the store where the item is located to the priority queue. We repeat the above operation until the priority queue is empty.

The time complexity is $O(m \times n \times \log m)$, and the space complexity is $O(m)$. Here, $m$ and $n$ are the number of rows and columns of the array $values$, respectively.

<!-- tabs:start -->

```python
class Solution:
    def maxSpending(self, values: List[List[int]]) -> int:
        n = len(values[0])
        pq = [(row[-1], i, n - 1) for i, row in enumerate(values)]
        heapify(pq)
        ans = d = 0
        while pq:
            d += 1
            v, i, j = heappop(pq)
            ans += v * d
            if j:
                heappush(pq, (values[i][j - 1], i, j - 1))
        return ans
```

```java
class Solution {
    public long maxSpending(int[][] values) {
        int m = values.length, n = values[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < m; ++i) {
            pq.offer(new int[] {values[i][n - 1], i, n - 1});
        }
        long ans = 0;
        for (int d = 1; !pq.isEmpty(); ++d) {
            var p = pq.poll();
            int v = p[0], i = p[1], j = p[2];
            ans += (long) v * d;
            if (j > 0) {
                pq.offer(new int[] {values[i][j - 1], i, j - 1});
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long maxSpending(vector<vector<int>>& values) {
        priority_queue<tuple<int, int, int>, vector<tuple<int, int, int>>, greater<tuple<int, int, int>>> pq;
        int m = values.size(), n = values[0].size();
        for (int i = 0; i < m; ++i) {
            pq.emplace(values[i][n - 1], i, n - 1);
        }
        long long ans = 0;
        for (int d = 1; pq.size(); ++d) {
            auto [v, i, j] = pq.top();
            pq.pop();
            ans += 1LL * v * d;
            if (j) {
                pq.emplace(values[i][j - 1], i, j - 1);
            }
        }
        return ans;
    }
};
```

```go
func maxSpending(values [][]int) (ans int64) {
	pq := hp{}
	n := len(values[0])
	for i, row := range values {
		heap.Push(&pq, tuple{row[n-1], i, n - 1})
	}
	for d := 1; len(pq) > 0; d++ {
		p := heap.Pop(&pq).(tuple)
		ans += int64(p.v * d)
		if p.j > 0 {
			heap.Push(&pq, tuple{values[p.i][p.j-1], p.i, p.j - 1})
		}
	}
	return
}

type tuple struct{ v, i, j int }
type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].v < h[j].v }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

```ts
function maxSpending(values: number[][]): number {
    const m = values.length;
    const n = values[0].length;
    const pq = new PriorityQueue({ compare: (a, b) => a[0] - b[0] });
    for (let i = 0; i < m; ++i) {
        pq.enqueue([values[i][n - 1], i, n - 1]);
    }

    let ans = 0;
    for (let d = 1; !pq.isEmpty(); ++d) {
        const [v, i, j] = pq.dequeue()!;
        ans += v * d;
        if (j > 0) {
            pq.enqueue([values[i][j - 1], i, j - 1]);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
