# [2462. 雇佣 K 位工人的总代价](https://leetcode.cn/problems/total-cost-to-hire-k-workers)

[English Version](/solution/2400-2499/2462.Total%20Cost%20to%20Hire%20K%20Workers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>costs</code>&nbsp;，其中&nbsp;<code>costs[i]</code>&nbsp;是雇佣第 <code>i</code>&nbsp;位工人的代价。</p>

<p>同时给你两个整数&nbsp;<code>k</code> 和&nbsp;<code>candidates</code>&nbsp;。我们想根据以下规则恰好雇佣&nbsp;<code>k</code>&nbsp;位工人：</p>

<ul>
	<li>总共进行&nbsp;<code>k</code>&nbsp;轮雇佣，且每一轮恰好雇佣一位工人。</li>
	<li>在每一轮雇佣中，从最前面 <code>candidates</code>&nbsp;和最后面 <code>candidates</code>&nbsp;人中选出代价最小的一位工人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。
	<ul>
		<li>比方说，<code>costs = [3,2,7,7,1,2]</code> 且&nbsp;<code>candidates = 2</code>&nbsp;，第一轮雇佣中，我们选择第&nbsp;<code>4</code>&nbsp;位工人，因为他的代价最小&nbsp;<code>[<em>3,2</em>,7,7,<em><strong>1</strong>,2</em>]</code>&nbsp;。</li>
		<li>第二轮雇佣，我们选择第&nbsp;<code>1</code>&nbsp;位工人，因为他们的代价与第&nbsp;<code>4</code>&nbsp;位工人一样都是最小代价，而且下标更小，<code>[<em>3,<strong>2</strong></em>,7,<em>7,2</em>]</code>&nbsp;。注意每一轮雇佣后，剩余工人的下标可能会发生变化。</li>
	</ul>
	</li>
	<li>如果剩余员工数目不足 <code>candidates</code>&nbsp;人，那么下一轮雇佣他们中代价最小的一人，如果有多位代价相同且最小的工人，选择下标更小的一位工人。</li>
	<li>一位工人只能被选择一次。</li>
</ul>

<p>返回雇佣恰好<em>&nbsp;</em><code>k</code>&nbsp;位工人的总代价。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
<b>输出：</b>11
<b>解释：</b>我们总共雇佣 3 位工人。总代价一开始为 0 。
- 第一轮雇佣，我们从 [<strong><em>17,12,10,2</em></strong>,7,<strong><em>2,11,20,8</em></strong>] 中选择。最小代价是 2 ，有两位工人，我们选择下标更小的一位工人，即第 3 位工人。总代价是 0 + 2 = 2 。
- 第二轮雇佣，我们从 [<strong><em>17,12,10,7</em></strong>,<strong><em>2,11,20,8</em></strong>] 中选择。最小代价是 2 ，下标为 4 ，总代价是 2 + 2 = 4 。
- 第三轮雇佣，我们从 [<strong><em>17,12,10,7,11,20,8</em></strong>] 中选择，最小代价是 7 ，下标为 3 ，总代价是 4 + 7 = 11 。注意下标为 3 的工人同时在最前面和最后面 4 位工人中。
总雇佣代价是 11 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>costs = [1,2,4,1], k = 3, candidates = 3
<b>输出：</b>4
<b>解释：</b>我们总共雇佣 3 位工人。总代价一开始为 0 。
- 第一轮雇佣，我们从 [<strong><em>1,2,4,1</em></strong>] 中选择。最小代价为 1 ，有两位工人，我们选择下标更小的一位工人，即第 0 位工人，总代价是 0 + 1 = 1 。注意，下标为 1 和 2 的工人同时在最前面和最后面 3 位工人中。
- 第二轮雇佣，我们从 [<strong><em>2,4,1</em></strong>] 中选择。最小代价为 1 ，下标为 2 ，总代价是 1 + 1 = 2 。
- 第三轮雇佣，少于 3 位工人，我们从剩余工人 [<strong><em>2,4</em></strong>] 中选择。最小代价是 2 ，下标为 0 。总代价为 2 + 2 = 4 。
总雇佣代价是 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= costs.length &lt;= 10<sup>5 </sup></code></li>
	<li><code>1 &lt;= costs[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k, candidates &lt;= costs.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：优先队列（小根堆）**

我们用一个优先队列（小根堆）维护当前的候选工人，用变量 $i$ 和 $j$ 标记最前面工人的最大小标和最后面工人的最小下标。初始时 $i = candidates - 1$，而 $j = n - candidates$。

我们先将前面 $candidates$ 个工人的代价放入优先队列中，再将最后面 $candidates$ 个工人的代价放入优先队列中，放入之前需要判断根据 $i$ 或 $j$ 是否已经在优先队列中，如果已经在优先队列中，则不需要再放入。

循环 $k$ 次，每次从优先队列中取出最小代价的工人，累加代价。如果当前取出的工人下标 $x$ 在最前面工人的下标范围 $[0,..i]$ 中，则将 $i$ 向右移动一位，然后判断是否要将 $i$ 对应的工人代价放入优先队列中；如果取出的下标在最后面工人的下标范围 $[j,..n-1]$ 中，则将 $j$ 向左移动一位，然后判断是否要将 $j$ 对应的工人代价放入优先队列中。

遍历结束后，将累加的代价作为答案返回。

时间复杂度 $O(n\times \log n)$。其中 $n$ 为数组 $costs$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def totalCost(self, costs: List[int], k: int, candidates: int) -> int:
        q = []
        n = len(costs)
        i, j = candidates - 1, n - candidates
        for h in range(candidates):
            q.append((costs[h], h))
        for h in range(n - candidates, n):
            if h > i:
                q.append((costs[h], h))
        heapify(q)
        ans = 0
        for _ in range(k):
            c, x = heappop(q)
            ans += c
            if x <= i:
                i += 1
                if i < j:
                    heappush(q, (costs[i], i))
            if x >= j:
                j -= 1
                if i < j:
                    heappush(q, (costs[j], j))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int n = costs.length;
        int i = candidates - 1, j = n - candidates;
        for (int h = 0; h < candidates; ++h) {
            q.offer(new int[] {costs[h], h});
        }
        for (int h = n - candidates; h < n; ++h) {
            if (h > i) {
                q.offer(new int[] {costs[h], h});
            }
        }
        long ans = 0;
        while (k-- > 0) {
            var e = q.poll();
            int c = e[0], x = e[1];
            ans += c;
            if (x <= i) {
                if (++i < j) {
                    q.offer(new int[] {costs[i], i});
                }
            }
            if (x >= j) {
                if (--j > i) {
                    q.offer(new int[] {costs[j], j});
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
using pii = pair<int, int>;

class Solution {
public:
    long long totalCost(vector<int>& costs, int k, int candidates) {
        priority_queue<pii, vector<pii>, greater<pii>> q;
        int n = costs.size();
        int i = candidates - 1, j = n - candidates;
        for (int h = 0; h < candidates; ++h) q.push({costs[h], h});
        for (int h = n - candidates; h < n; ++h) if (h > i) q.push({costs[h], h});
        long long ans = 0;
        while (k--) {
            auto [c, x] = q.top();
            q.pop();
            ans += c;
            if (x <= i) {
                if (++i < j) {
                    q.push({costs[i], i});
                }
            }
            if (x >= j) {
                if (--j > i) {
                    q.push({costs[j], j});
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func totalCost(costs []int, k int, candidates int) int64 {
	q := hp{}
	n := len(costs)
	i, j := candidates-1, n-candidates
	for h := 0; h < candidates; h++ {
		heap.Push(&q, pair{costs[h], h})
	}
	for h := n - candidates; h < n; h++ {
		if h > i {
			heap.Push(&q, pair{costs[h], h})
		}
	}
	ans := 0
	for k > 0 {
		p := heap.Pop(&q).(pair)
		c, x := p.c, p.x
		ans += c
		if x <= i {
			i++
			if i < j {
				heap.Push(&q, pair{costs[i], i})
			}
		}
		if x >= j {
			j--
			if i < j {
				heap.Push(&q, pair{costs[j], j})
			}
		}
		k--
	}
	return int64(ans)
}

type pair struct{ c, x int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].c < h[j].c || h[i].c == h[j].c && h[i].x < h[j].x }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
