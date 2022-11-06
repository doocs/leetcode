# [2462. Total Cost to Hire K Workers](https://leetcode.com/problems/total-cost-to-hire-k-workers)

[中文文档](/solution/2400-2499/2462.Total%20Cost%20to%20Hire%20K%20Workers/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>costs</code> where <code>costs[i]</code> is the cost of hiring the <code>i<sup>th</sup></code> worker.</p>

<p>You are also given two integers <code>k</code> and <code>candidates</code>. We want to hire exactly <code>k</code> workers according to the following rules:</p>

<ul>
	<li>You will run <code>k</code> sessions and hire exactly one worker in each session.</li>
	<li>In each hiring session, choose the worker with the lowest cost from either the first <code>candidates</code> workers or the last <code>candidates</code> workers. Break the tie by the smallest index.
	<ul>
		<li>For example, if <code>costs = [3,2,7,7,1,2]</code> and <code>candidates = 2</code>, then in the first hiring session, we will choose the <code>4<sup>th</sup></code> worker because they have the lowest cost <code>[<u>3,2</u>,7,7,<u><strong>1</strong>,2</u>]</code>.</li>
		<li>In the second hiring session, we will choose <code>1<sup>st</sup></code> worker because they have the same lowest cost as <code>4<sup>th</sup></code> worker but they have the smallest index <code>[<u>3,<strong>2</strong></u>,7,<u>7,2</u>]</code>. Please note that the indexing may be changed in the process.</li>
	</ul>
	</li>
	<li>If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break the tie by the smallest index.</li>
	<li>A worker can only be chosen once.</li>
</ul>

<p>Return <em>the total cost to hire exactly </em><code>k</code><em> workers.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
<strong>Output:</strong> 11
<strong>Explanation:</strong> We hire 3 workers in total. The total cost is initially 0.
- In the first hiring round we choose the worker from [<u>17,12,10,2</u>,7,<u>2,11,20,8</u>]. The lowest cost is 2, and we break the tie by the smallest index, which is 3. The total cost = 0 + 2 = 2.
- In the second hiring round we choose the worker from [<u>17,12,10,7</u>,<u>2,11,20,8</u>]. The lowest cost is 2 (index 4). The total cost = 2 + 2 = 4.
- In the third hiring round we choose the worker from [<u>17,12,10,7,11,20,8</u>]. The lowest cost is 7 (index 3). The total cost = 4 + 7 = 11. Notice that the worker with index 3 was common in the first and last four workers.
The total hiring cost is 11.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> costs = [1,2,4,1], k = 3, candidates = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> We hire 3 workers in total. The total cost is initially 0.
- In the first hiring round we choose the worker from [<u>1,2,4,1</u>]. The lowest cost is 1, and we break the tie by the smallest index, which is 0. The total cost = 0 + 1 = 1. Notice that workers with index 1 and 2 are common in the first and last 3 workers.
- In the second hiring round we choose the worker from [<u>2,4,1</u>]. The lowest cost is 1 (index 2). The total cost = 1 + 1 = 2.
- In the third hiring round there are less than three candidates. We choose the worker from the remaining workers [<u>2,4</u>]. The lowest cost is 2 (index 0). The total cost = 2 + 2 = 4.
The total hiring cost is 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= costs.length &lt;= 10<sup>5 </sup></code></li>
	<li><code>1 &lt;= costs[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k, candidates &lt;= costs.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
