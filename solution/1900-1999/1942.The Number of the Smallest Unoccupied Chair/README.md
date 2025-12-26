---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1942.The%20Number%20of%20the%20Smallest%20Unoccupied%20Chair/README.md
rating: 1695
source: 第 57 场双周赛 Q2
tags:
    - 数组
    - 哈希表
    - 堆（优先队列）
---

<!-- problem:start -->

# [1942. 最小未被占据椅子的编号](https://leetcode.cn/problems/the-number-of-the-smallest-unoccupied-chair)

[English Version](/solution/1900-1999/1942.The%20Number%20of%20the%20Smallest%20Unoccupied%20Chair/README_EN.md)

## 题目描述

<!-- description:start -->

<p>有 <code>n</code> 个朋友在举办一个派对，这些朋友从 <code>0</code> 到 <code>n - 1</code> 编号。派对里有 <strong>无数</strong> 张椅子，编号为 <code>0</code> 到 <code>infinity</code> 。当一个朋友到达派对时，他会占据 <strong>编号最小</strong> 且未被占据的椅子。</p>

<ul>
	<li>比方说，当一个朋友到达时，如果椅子 <code>0</code> ，<code>1</code> 和 <code>5</code> 被占据了，那么他会占据 <code>2</code> 号椅子。</li>
</ul>

<p>当一个朋友离开派对时，他的椅子会立刻变成未占据状态。如果同一时刻有另一个朋友到达，可以立即占据这张椅子。</p>

<p>给你一个下标从 <strong>0</strong> 开始的二维整数数组 <code>times</code> ，其中 <code>times[i] = [arrival<sub>i</sub>, leaving<sub>i</sub>]</code> 表示第 <code>i</code> 个朋友到达和离开的时刻，同时给你一个整数 <code>targetFriend</code> 。所有到达时间 <strong>互不相同</strong> 。</p>

<p>请你返回编号为 <code>targetFriend</code> 的朋友占据的 <strong>椅子编号</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>times = [[1,4],[2,3],[4,6]], targetFriend = 1
<b>输出：</b>1
<b>解释：</b>
- 朋友 0 时刻 1 到达，占据椅子 0 。
- 朋友 1 时刻 2 到达，占据椅子 1 。
- 朋友 1 时刻 3 离开，椅子 1 变成未占据。
- 朋友 0 时刻 4 离开，椅子 0 变成未占据。
- 朋友 2 时刻 4 到达，占据椅子 0 。
朋友 1 占据椅子 1 ，所以返回 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>times = [[3,10],[1,5],[2,6]], targetFriend = 0
<b>输出：</b>2
<b>解释：</b>
- 朋友 1 时刻 1 到达，占据椅子 0 。
- 朋友 2 时刻 2 到达，占据椅子 1 。
- 朋友 0 时刻 3 到达，占据椅子 2 。
- 朋友 1 时刻 5 离开，椅子 0 变成未占据。
- 朋友 2 时刻 6 离开，椅子 1 变成未占据。
- 朋友 0 时刻 10 离开，椅子 2 变成未占据。
朋友 0 占据椅子 2 ，所以返回 2 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == times.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>times[i].length == 2</code></li>
	<li><code>1 &lt;= arrival<sub>i</sub> &lt; leaving<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= targetFriend &lt;= n - 1</code></li>
	<li>每个 <code>arrival<sub>i</sub></code> 时刻 <strong>互不相同</strong> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（小根堆）

我们首先将每个朋友的到达时间、离开时间和编号组成一个三元组，然后按到达时间排序。

我们使用一个小根堆 $\textit{idle}$ 来存储当前空闲的椅子编号，初始时，我们将 $0, 1, \ldots, n-1$ 加入 $\textit{idle}$ 中。使用一个小根堆 $\textit{busy}$ 存储二元组 $(\textit{leaving}, \textit{chair})$，其中 $\textit{leaving}$ 表示离开时间，而 $\textit{chair}$ 表示椅子编号。

遍历每个朋友的到达时间、离开时间和编号，对于每个朋友，我们首先将所有离开时间小于等于当前朋友到达时间的朋友从 $\textit{busy}$ 中弹出，将他们占据的椅子编号加入 $\textit{idle}$ 中。然后我们从 $\textit{idle}$ 中弹出一个椅子编号，将其分配给当前朋友，将 $(\textit{leaving}, \textit{chair})$ 加入 $\textit{busy}$ 中。如果当前朋友的编号等于 $\textit{targetFriend}$，我们返回当前分配的椅子编号。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为朋友的个数。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallestChair(self, times: List[List[int]], targetFriend: int) -> int:
        n = len(times)
        for i in range(n):
            times[i].append(i)
        times.sort()
        idle = list(range(n))
        heapify(idle)
        busy = []
        for arrival, leaving, i in times:
            while busy and busy[0][0] <= arrival:
                heappush(idle, heappop(busy)[1])
            j = heappop(idle)
            if i == targetFriend:
                return j
            heappush(busy, (leaving, j))
```

#### Java

```java
class Solution {
    public int smallestChair(int[][] times, int targetFriend) {
        int n = times.length;
        PriorityQueue<Integer> idle = new PriorityQueue<>();
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < n; ++i) {
            times[i] = new int[] {times[i][0], times[i][1], i};
            idle.offer(i);
        }
        Arrays.sort(times, Comparator.comparingInt(a -> a[0]));
        for (var e : times) {
            int arrival = e[0], leaving = e[1], i = e[2];
            while (!busy.isEmpty() && busy.peek()[0] <= arrival) {
                idle.offer(busy.poll()[1]);
            }
            int j = idle.poll();
            if (i == targetFriend) {
                return j;
            }
            busy.offer(new int[] {leaving, j});
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int smallestChair(vector<vector<int>>& times, int targetFriend) {
        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> busy;
        priority_queue<int, vector<int>, greater<int>> idle;
        int n = times.size();
        for (int i = 0; i < n; ++i) {
            times[i].push_back(i);
            idle.push(i);
        }
        ranges::sort(times);
        for (const auto& e : times) {
            int arrival = e[0], leaving = e[1], i = e[2];
            while (!busy.empty() && busy.top().first <= arrival) {
                idle.push(busy.top().second);
                busy.pop();
            }
            int j = idle.top();
            if (i == targetFriend) {
                return j;
            }
            idle.pop();
            busy.emplace(leaving, j);
        }
        return -1;
    }
};
```

#### Go

```go
func smallestChair(times [][]int, targetFriend int) int {
	idle := hp{}
	busy := hp2{}
	for i := range times {
		times[i] = append(times[i], i)
		heap.Push(&idle, i)
	}
	sort.Slice(times, func(i, j int) bool { return times[i][0] < times[j][0] })
	for _, e := range times {
		arrival, leaving, i := e[0], e[1], e[2]
		for len(busy) > 0 && busy[0].t <= arrival {
			heap.Push(&idle, heap.Pop(&busy).(pair).i)
		}
		j := heap.Pop(&idle).(int)
		if i == targetFriend {
			return j
		}
		heap.Push(&busy, pair{leaving, j})
	}
	return -1
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}

type pair struct{ t, i int }
type hp2 []pair

func (h hp2) Len() int           { return len(h) }
func (h hp2) Less(i, j int) bool { return h[i].t < h[j].t || (h[i].t == h[j].t && h[i].i < h[j].i) }
func (h hp2) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp2) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp2) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

#### TypeScript

```ts
function smallestChair(times: number[][], targetFriend: number): number {
    const n = times.length;
    const idle = new MinPriorityQueue<number>();
    const busy = new PriorityQueue((a, b) => a[0] - b[0]);
    for (let i = 0; i < n; ++i) {
        times[i].push(i);
        idle.enqueue(i);
    }
    times.sort((a, b) => a[0] - b[0]);
    for (const [arrival, leaving, i] of times) {
        while (busy.size() > 0 && busy.front()[0] <= arrival) {
            idle.enqueue(busy.dequeue()[1]);
        }
        const j = idle.dequeue();
        if (i === targetFriend) {
            return j;
        }
        busy.enqueue([leaving, j]);
    }
    return -1;
}
```

#### JavaScript

```js
/**
 * @param {number[][]} times
 * @param {number} targetFriend
 * @return {number}
 */
var smallestChair = function (times, targetFriend) {
    const n = times.length;
    const idle = new MinPriorityQueue();
    const busy = new PriorityQueue((a, b) => a[0] - b[0]);
    for (let i = 0; i < n; ++i) {
        times[i].push(i);
        idle.enqueue(i);
    }
    times.sort((a, b) => a[0] - b[0]);
    for (const [arrival, leaving, i] of times) {
        while (busy.size() > 0 && busy.front()[0] <= arrival) {
            idle.enqueue(busy.dequeue()[1]);
        }
        const j = idle.dequeue();
        if (i === targetFriend) {
            return j;
        }
        busy.enqueue([leaving, j]);
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
