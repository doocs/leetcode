# [1199. Minimum Time to Build Blocks](https://leetcode.com/problems/minimum-time-to-build-blocks)

[中文文档](/solution/1100-1199/1199.Minimum%20Time%20to%20Build%20Blocks/README.md)

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minBuildTime(self, blocks: List[int], split: int) -> int:
        heapify(blocks)
        while len(blocks) > 1:
            heappop(blocks)
            heappush(blocks, heappop(blocks) + split)
        return blocks[0]
```

### **Java**

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

### **C++**

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

### **Go**

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

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
```

### **...**

```

```

<!-- tabs:end -->
