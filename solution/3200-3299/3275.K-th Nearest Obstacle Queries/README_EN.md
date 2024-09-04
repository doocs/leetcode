---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3275.K-th%20Nearest%20Obstacle%20Queries/README_EN.md
tags:
    - Array
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3275. K-th Nearest Obstacle Queries](https://leetcode.com/problems/k-th-nearest-obstacle-queries)

[中文文档](/solution/3200-3299/3275.K-th%20Nearest%20Obstacle%20Queries/README.md)

## Description

<!-- description:start -->

<p>There is an infinite 2D plane.</p>

<p>You are given a positive integer <code>k</code>. You are also given a 2D array <code>queries</code>, which contains the following queries:</p>

<ul>
	<li><code>queries[i] = [x, y]</code>: Build an obstacle at coordinate <code>(x, y)</code> in the plane. It is guaranteed that there is <strong>no</strong> obstacle at this coordinate when this query is made.</li>
</ul>

<p>After each query, you need to find the <strong>distance</strong> of the <code>k<sup>th</sup></code> <strong>nearest</strong> obstacle from the origin.</p>

<p>Return an integer array <code>results</code> where <code>results[i]</code> denotes the <code>k<sup>th</sup></code> nearest obstacle after query <code>i</code>, or <code>results[i] == -1</code> if there are less than <code>k</code> obstacles.</p>

<p><strong>Note</strong> that initially there are <strong>no</strong> obstacles anywhere.</p>

<p>The <strong>distance</strong> of an obstacle at coordinate <code>(x, y)</code> from the origin is given by <code>|x| + |y|</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">queries = [[1,2],[3,4],[2,3],[-3,0]], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[-1,7,5,3]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Initially, there are 0 obstacles.</li>
	<li>After <code>queries[0]</code>, there are less than 2 obstacles.</li>
	<li>After <code>queries[1]</code>, there are obstacles at distances 3 and 7.</li>
	<li>After <code>queries[2]</code>, there are obstacles at distances 3, 5, and 7.</li>
	<li>After <code>queries[3]</code>, there are obstacles at distances 3, 3, 5, and 7.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">queries = [[5,5],[4,4],[3,3]], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[10,8,6]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>After <code>queries[0]</code>, there is an obstacle at distance 10.</li>
	<li>After <code>queries[1]</code>, there are obstacles at distances 8 and 10.</li>
	<li>After <code>queries[2]</code>, there are obstacles at distances 6, 8, and 10.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= queries.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li>All <code>queries[i]</code> are unique.</li>
	<li><code>-10<sup>9</sup> &lt;= queries[i][0], queries[i][1] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Priority Queue (Max-Heap)

We can use a priority queue (max-heap) to maintain the $k$ obstacles closest to the origin.

Traverse $\textit{queries}$, and for each query, calculate the sum of the absolute values of $x$ and $y$, then add it to the priority queue. If the size of the priority queue exceeds $k$, pop the top element. If the current size of the priority queue is equal to $k$, add the top element to the answer array; otherwise, add $-1$ to the answer array.

After the traversal, return the answer array.

The time complexity is $O(n \times \log k)$, and the space complexity is $O(k)$. Here, $n$ is the length of the array $\textit{queries}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def resultsArray(self, queries: List[List[int]], k: int) -> List[int]:
        ans = []
        pq = []
        for i, (x, y) in enumerate(queries):
            heappush(pq, -(abs(x) + abs(y)))
            if i >= k:
                heappop(pq)
            ans.append(-pq[0] if i >= k - 1 else -1)
        return ans
```

#### Java

```java
class Solution {
    public int[] resultsArray(int[][] queries, int k) {
        int n = queries.length;
        int[] ans = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; ++i) {
            int x = Math.abs(queries[i][0]) + Math.abs(queries[i][1]);
            pq.offer(x);
            if (i >= k) {
                pq.poll();
            }
            ans[i] = i >= k - 1 ? pq.peek() : -1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> resultsArray(vector<vector<int>>& queries, int k) {
        vector<int> ans;
        priority_queue<int> pq;
        for (const auto& q : queries) {
            int x = abs(q[0]) + abs(q[1]);
            pq.push(x);
            if (pq.size() > k) {
                pq.pop();
            }
            ans.push_back(pq.size() == k ? pq.top() : -1);
        }
        return ans;
    }
};
```

#### Go

```go
func resultsArray(queries [][]int, k int) (ans []int) {
	pq := &hp{}
	for _, q := range queries {
		x := abs(q[0]) + abs(q[1])
		pq.push(x)
		if pq.Len() > k {
			pq.pop()
		}
		if pq.Len() == k {
			ans = append(ans, pq.IntSlice[0])
		} else {
			ans = append(ans, -1)
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
```

#### TypeScript

```ts
function resultsArray(queries: number[][], k: number): number[] {
    const pq = new MaxPriorityQueue();
    const ans: number[] = [];
    for (const [x, y] of queries) {
        pq.enqueue(Math.abs(x) + Math.abs(y));
        if (pq.size() > k) {
            pq.dequeue();
        }
        ans.push(pq.size() === k ? pq.front().element : -1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
