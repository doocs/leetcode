# [1199. Minimum Time to Build Blocks 🔒](https://leetcode.com/problems/minimum-time-to-build-blocks)

[中文文档](/solution/1100-1199/1199.Minimum%20Time%20to%20Build%20Blocks/README.md)

<!-- tags:Greedy,Array,Math,Heap (Priority Queue) -->

<!-- difficulty:Hard -->

## Description

<p>You are given a list of blocks, where <code>blocks[i] = t</code> means that the&nbsp;<code>i</code>-th block needs&nbsp;<code>t</code>&nbsp;units of time to be built. A block can only be built by exactly one worker.</p>

<p>A worker can either split into two workers (number of workers increases by one) or build a block then go home. Both decisions cost some time.</p>

<p>The time cost of spliting one worker into two workers is&nbsp;given as an integer <code>split</code>. Note that if two workers split at the same time, they split in parallel so the cost would be&nbsp;<code>split</code>.</p>

<p>Output the minimum time needed to build all blocks.</p>

<p>Initially, there is only <strong>one</strong> worker.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> blocks = [1], split = 1
<strong>Output:</strong> 1
<strong>Explanation: </strong>We use 1 worker to build 1 block in 1 time unit.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> blocks = [1,2], split = 5
<strong>Output:</strong> 7
<strong>Explanation: </strong>We split the worker into 2 workers in 5 time units then assign each of them to a block so the cost is 5 + max(1, 2) = 7.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> blocks = [1,2,3], split = 1
<strong>Output:</strong> 4
<strong>Explanation: </strong>Split 1 worker into 2, then assign the first worker to the last block and split the second worker into 2.
Then, use the two unassigned workers to build the first two blocks.
The cost is 1 + max(3, 1 + max(1, 2)) = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= blocks.length &lt;= 1000</code></li>
	<li><code>1 &lt;= blocks[i] &lt;= 10^5</code></li>
	<li><code>1 &lt;= split &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Greedy + Priority Queue (Min Heap)

First, consider the case where there is only one block. In this case, there is no need to split the worker, just let him build the block directly. The time cost is $block[0]$.

If there are two blocks, you need to split the worker into two, and then let them build the blocks separately. The time cost is $split + \max(block[0], block[1])$.

If there are more than two blocks, at each step you need to consider how many workers to split. This is not easy to handle with forward thinking.

We might as well use reverse thinking, not splitting workers, but merging blocks. We select any two blocks $i$, $j$ for merging. The time to build a new block is $split + \max(block[i], block[j])$.

In order to let the blocks with long time consumption participate in the merge as little as possible, we can greedily select the two blocks with the smallest time consumption for merging each time. Therefore, we can maintain a min heap, take out the two smallest blocks for merging each time, until there is only one block left. The build time of the last remaining block is the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the number of blocks.

<!-- tabs:start -->

```python
class Solution:
    def minBuildTime(self, blocks: List[int], split: int) -> int:
        heapify(blocks)
        while len(blocks) > 1:
            heappop(blocks)
            heappush(blocks, heappop(blocks) + split)
        return blocks[0]
```

```java
class Solution {
    public int minBuildTime(int[] blocks, int split) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int x : blocks) {
            q.offer(x);
        }
        while (q.size() > 1) {
            q.poll();
            q.offer(q.poll() + split);
        }
        return q.poll();
    }
}
```

```cpp
class Solution {
public:
    int minBuildTime(vector<int>& blocks, int split) {
        priority_queue<int, vector<int>, greater<int>> pq;
        for (int v : blocks) pq.push(v);
        while (pq.size() > 1) {
            pq.pop();
            int x = pq.top();
            pq.pop();
            pq.push(x + split);
        }
        return pq.top();
    }
};
```

```go
func minBuildTime(blocks []int, split int) int {
	q := hp{}
	for _, v := range blocks {
		heap.Push(&q, v)
	}
	for q.Len() > 1 {
		heap.Pop(&q)
		heap.Push(&q, heap.Pop(&q).(int)+split)
	}
	return q.IntSlice[0]
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v any) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

```ts
function minBuildTime(blocks: number[], split: number): number {
    const pq = new MinPriorityQueue();
    for (const x of blocks) {
        pq.enqueue(x);
    }
    while (pq.size() > 1) {
        pq.dequeue()!;
        pq.enqueue(pq.dequeue()!.element + split);
    }
    return pq.dequeue()!.element;
}
```

```rust
use std::collections::BinaryHeap;
use std::cmp::Reverse;

impl Solution {
    pub fn min_build_time(blocks: Vec<i32>, split: i32) -> i32 {
        let mut pq = BinaryHeap::new();

        for x in blocks {
            pq.push(Reverse(x));
        }

        while pq.len() > 1 {
            pq.pop();
            let new_element = pq.pop().unwrap().0 + split;
            pq.push(Reverse(new_element));
        }

        pq.pop().unwrap().0
    }
}
```

<!-- tabs:end -->

<!-- end -->
