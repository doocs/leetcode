# [2931. 购买物品的最大开销](https://leetcode.cn/problems/maximum-spending-after-buying-items)

[English Version](/solution/2900-2999/2931.Maximum%20Spending%20After%20Buying%20Items/README_EN.md)

<!-- tags:贪心,数组,矩阵,排序,堆（优先队列） -->

<!-- difficulty:困难 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始大小为&nbsp;<code>m * n</code>&nbsp;的整数矩阵&nbsp;<code>values</code>&nbsp;，表示&nbsp;<code>m</code>&nbsp;个不同商店里&nbsp;<code>m * n</code>&nbsp;件不同的物品。每个商店有 <code>n</code>&nbsp;件物品，第&nbsp;<code>i</code>&nbsp;个商店的第 <code>j</code>&nbsp;件物品的价值为&nbsp;<code>values[i][j]</code>&nbsp;。除此以外，第&nbsp;<code>i</code>&nbsp;个商店的物品已经按照价值非递增排好序了，也就是说对于所有&nbsp;<code>0 &lt;= j &lt; n - 1</code>&nbsp;都有&nbsp;<code>values[i][j] &gt;= values[i][j + 1]</code>&nbsp;。</p>

<p>每一天，你可以在一个商店里购买一件物品。具体来说，在第&nbsp;<code>d</code>&nbsp;天，你可以：</p>

<ul>
	<li>选择商店&nbsp;<code>i</code>&nbsp;。</li>
	<li>购买数组中最右边的物品&nbsp;<code>j</code>&nbsp;，开销为&nbsp;<code>values[i][j] * d</code>&nbsp;。换句话说，选择该商店中还没购买过的物品中最大的下标&nbsp;<code>j</code>&nbsp;，并且花费&nbsp;<code>values[i][j] * d</code>&nbsp;去购买。</li>
</ul>

<p><strong>注意</strong>，所有物品都视为不同的物品。比方说如果你已经从商店 <code>1</code>&nbsp;购买了物品&nbsp;<code>0</code>&nbsp;，你还可以在别的商店里购买其他商店的物品&nbsp;<code>0</code>&nbsp;。</p>

<p>请你返回购买所有 <code>m * n</code>&nbsp;件物品需要的 <strong>最大开销</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>values = [[8,5,2],[6,4,1],[9,7,3]]
<b>输出：</b>285
<b>解释：</b>第一天，从商店 1 购买物品 2 ，开销为 values[1][2] * 1 = 1 。
第二天，从商店 0 购买物品 2 ，开销为 values[0][2] * 2 = 4 。
第三天，从商店 2 购买物品 2 ，开销为 values[2][2] * 3 = 9 。
第四天，从商店 1 购买物品 1 ，开销为 values[1][1] * 4 = 16 。
第五天，从商店 0 购买物品 1 ，开销为 values[0][1] * 5 = 25 。
第六天，从商店 1 购买物品 0 ，开销为 values[1][0] * 6 = 36 。
第七天，从商店 2 购买物品 1 ，开销为 values[2][1] * 7 = 49 。
第八天，从商店 0 购买物品 0 ，开销为 values[0][0] * 8 = 64 。
第九天，从商店 2 购买物品 0 ，开销为 values[2][0] * 9 = 81 。
所以总开销为 285 。
285 是购买所有 m * n 件物品的最大总开销。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>values = [[10,8,6,4,2],[9,7,5,3,2]]
<b>输出：</b>386
<b>解释：</b>第一天，从商店 0 购买物品 4 ，开销为 values[0][4] * 1 = 2 。
第二天，从商店 1 购买物品 4 ，开销为 values[1][4] * 2 = 4 。
第三天，从商店 1 购买物品 3 ，开销为 values[1][3] * 3 = 9 。
第四天，从商店 0 购买物品 3 ，开销为 values[0][3] * 4 = 16 。
第五天，从商店 1 购买物品 2 ，开销为 values[1][2] * 5 = 25 。
第六天，从商店 0 购买物品 2 ，开销为 values[0][2] * 6 = 36 。
第七天，从商店 1 购买物品 1 ，开销为 values[1][1] * 7 = 49 。
第八天，从商店 0 购买物品 1 ，开销为 values[0][1] * 8 = 64 。
第九天，从商店 1 购买物品 0 ，开销为 values[1][0] * 9 = 81 。
第十天，从商店 0 购买物品 0 ，开销为 values[0][0] * 10 = 100 。
所以总开销为 386 。
386 是购买所有 m * n 件物品的最大总开销。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= m == values.length &lt;= 10</code></li>
	<li><code>1 &lt;= n == values[i].length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= values[i][j] &lt;= 10<sup>6</sup></code></li>
	<li><code>values[i]</code>&nbsp;按照非递增顺序排序。</li>
</ul>

## 解法

### 方法一：贪心 + 优先队列

根据题目描述，我们应该优先选择价值越小的物品，把价值越大的物品留到后面购买，这样才能使得总开销最大。因此，我们使用优先队列（小根堆）存储每个商店中还未购买的最小价值的物品。初始时，我们将每个商店中最右边的物品加入优先队列。

在每一天，我们从优先队列中取出价值最小的物品，将其加入答案，并将该物品所在商店中的上一个物品加入优先队列。我们重复上述操作，直到优先队列为空。

时间复杂度 $O(m \times n \times \log m)$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别是数组 $values$ 的行数和列数。

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

<!-- end -->
