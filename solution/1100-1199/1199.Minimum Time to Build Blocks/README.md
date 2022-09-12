# [1199. 建造街区的最短时间](https://leetcode.cn/problems/minimum-time-to-build-blocks)

[English Version](/solution/1100-1199/1199.Minimum%20Time%20to%20Build%20Blocks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你是个城市规划工作者，手里负责管辖一系列的街区。在这个街区列表中&nbsp;<code>blocks[i] = t</code>&nbsp;意味着第 &nbsp;<code>i</code>&nbsp;个街区需要&nbsp;<code>t</code>&nbsp;个单位的时间来建造。</p>

<p>由于一个街区只能由一个工人来完成建造。</p>

<p>所以，一个工人要么需要再召唤一个工人（工人数增加 1）；要么建造完一个街区后回家。这两个决定都需要花费一定的时间。</p>

<p>一个工人再召唤一个工人所花费的时间由整数&nbsp;<code>split</code>&nbsp;给出。</p>

<p>注意：如果两个工人同时召唤别的工人，那么他们的行为是并行的，所以时间花费仍然是&nbsp;<code>split</code>。</p>

<p>最开始的时候只有&nbsp;<strong>一个&nbsp;</strong>工人，请你最后输出建造完所有街区所需要的最少时间。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>blocks = [1], split = 1
<strong>输出：</strong>1
<strong>解释：</strong>我们使用 1 个工人在 1 个时间单位内来建完 1 个街区。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre><strong>输入：</strong>blocks = [1,2], split = 5
<strong>输出：</strong>7
<strong>解释：</strong>我们用 5 个时间单位将这个工人分裂为 2 个工人，然后指派每个工人分别去建造街区，从而时间花费为 5 + max(1, 2) = 7
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>blocks = [1,2,3], split = 1
<strong>输出：</strong>4
<strong>解释：
</strong>将 1 个工人分裂为 2 个工人，然后指派第一个工人去建造最后一个街区，并将第二个工人分裂为 2 个工人。
然后，用这两个未分派的工人分别去建造前两个街区。
时间花费为 1 + max(3, 1 + max(1, 2)) = 4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= blocks.length &lt;= 1000</code></li>
	<li><code>1 &lt;= blocks[i] &lt;= 10^5</code></li>
	<li><code>1 &lt;= split &lt;= 100</code></li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 优先队列（小根堆）**

先考虑只有一个街区的情况，此时不需要分裂工人，直接让他去建造街区，时间花费为 `block[0]`。

如果有两个街区，此时需要把工人分裂为两个，然后让他们分别去建造街区，时间花费为 `split + max(block[0], block[1])`。

如果有超过两个街区，此时每一步都需要考虑将几个工人进行分裂，正向思维不好处理。

我们不妨采用逆向思维，不分裂工人，而是将街区进行合并。我们选取任意两个街区 $i$, $j$ 进行合并，建造一个新的街区的时间为 `split + max(block[i], block[j])`。

为了让耗时长的街区尽可能少参与到合并中，我们可以每次贪心地选取耗时最小的两个街区进行合并。因此，我们可以维护一个小根堆，每次取出最小的两个街区进行合并，直到只剩下一个街区。最后剩下的这个街区的建造时间就是答案。

时间复杂度 $O(n\log n)$。其中 $n$ 是街区数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
