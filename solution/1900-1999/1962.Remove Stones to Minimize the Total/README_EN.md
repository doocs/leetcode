---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1962.Remove%20Stones%20to%20Minimize%20the%20Total/README_EN.md
rating: 1418
source: Weekly Contest 253 Q2
tags:
    - Greedy
    - Array
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [1962. Remove Stones to Minimize the Total](https://leetcode.com/problems/remove-stones-to-minimize-the-total)

[中文文档](/solution/1900-1999/1962.Remove%20Stones%20to%20Minimize%20the%20Total/README.md)

## Description

<!-- description:start -->

<p>You are given a <strong>0-indexed</strong> integer array <code>piles</code>, where <code>piles[i]</code> represents the number of stones in the <code>i<sup>th</sup></code> pile, and an integer <code>k</code>. You should apply the following operation <strong>exactly</strong> <code>k</code> times:</p>

<ul>
	<li>Choose any <code>piles[i]</code> and <strong>remove</strong> <code>ceil(piles[i] / 2)</code> stones from it.</li>
</ul>

<p><strong>Notice</strong> that you can apply the operation on the <strong>same</strong> pile more than once.</p>

<p>Return <em>the <strong>minimum</strong> possible total number of stones remaining after applying the </em><code>k</code><em> operations</em>.</p>

<p><code>ceil(x)</code> is the <b>smallest</b> integer that is <strong>greater</strong> than or <strong>equal</strong> to <code>x</code> (i.e., rounds <code>x</code> up).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> piles = [5,4,9], k = 2
<strong>Output:</strong> 12
<strong>Explanation:</strong>&nbsp;Steps of a possible scenario are:
- Apply the operation on pile 2. The resulting piles are [5,4,<u>5</u>].
- Apply the operation on pile 0. The resulting piles are [<u>3</u>,4,5].
The total number of stones in [3,4,5] is 12.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> piles = [4,3,6,7], k = 3
<strong>Output:</strong> 12
<strong>Explanation:</strong>&nbsp;Steps of a possible scenario are:
- Apply the operation on pile 2. The resulting piles are [4,3,<u>3</u>,7].
- Apply the operation on pile 3. The resulting piles are [4,3,3,<u>4</u>].
- Apply the operation on pile 0. The resulting piles are [<u>2</u>,3,3,4].
The total number of stones in [2,3,3,4] is 12.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= piles.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= piles[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Priority Queue (Max Heap)

According to the problem description, in order to minimize the total number of remaining stones, we need to remove as many stones as possible from the stone piles. Therefore, we should always choose the pile with the most stones for removal.

We create a priority queue (max heap) $pq$ to store the number of stones in each pile. Initially, we add the number of stones in all piles to the priority queue.

Next, we perform $k$ operations. In each operation, we take out the top element $x$ of the priority queue, halve $x$, and then add it back to the priority queue.

After performing $k$ operations, the sum of all elements in the priority queue is the answer.

The time complexity is $O(n + k \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array `piles`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minStoneSum(self, piles: List[int], k: int) -> int:
        pq = [-x for x in piles]
        heapify(pq)
        for _ in range(k):
            heapreplace(pq, pq[0] // 2)
        return -sum(pq)
```

#### Java

```java
class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int x : piles) {
            pq.offer(x);
        }
        while (k-- > 0) {
            int x = pq.poll();
            pq.offer(x - x / 2);
        }
        int ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minStoneSum(vector<int>& piles, int k) {
        priority_queue<int> pq;
        for (int x : piles) {
            pq.push(x);
        }
        while (k--) {
            int x = pq.top();
            pq.pop();
            pq.push(x - x / 2);
        }
        int ans = 0;
        while (!pq.empty()) {
            ans += pq.top();
            pq.pop();
        }
        return ans;
    }
};
```

#### Go

```go
func minStoneSum(piles []int, k int) (ans int) {
	pq := &hp{piles}
	heap.Init(pq)
	for ; k > 0; k-- {
		x := pq.pop()
		pq.push(x - x/2)
	}
	for pq.Len() > 0 {
		ans += pq.pop()
	}
	return
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
function minStoneSum(piles: number[], k: number): number {
    const pq = new MaxPriorityQueue();
    for (const x of piles) {
        pq.enqueue(x);
    }
    while (k--) {
        pq.enqueue((pq.dequeue().element + 1) >> 1);
    }

    return pq.toArray().reduce((a, b) => a + b.element, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
