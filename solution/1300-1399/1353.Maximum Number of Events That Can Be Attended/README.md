---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1353.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended/README.md
rating: 2015
source: 第 176 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 排序
    - 堆（优先队列）
---

<!-- problem:start -->

# [1353. 最多可以参加的会议数目](https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended)

[English Version](/solution/1300-1399/1353.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个数组&nbsp;<code>events</code>，其中&nbsp;<code>events[i] = [startDay<sub>i</sub>, endDay<sub>i</sub>]</code>&nbsp;，表示会议&nbsp;<code>i</code>&nbsp;开始于&nbsp;<code>startDay<sub>i</sub></code>&nbsp;，结束于&nbsp;<code>endDay<sub>i</sub></code>&nbsp;。</p>

<p>你可以在满足&nbsp;<code>startDay<sub>i</sub>&nbsp;&lt;= d &lt;= endDay<sub>i</sub></code><sub>&nbsp;</sub>中的任意一天&nbsp;<code>d</code>&nbsp;参加会议&nbsp;<code>i</code>&nbsp;。在任意一天&nbsp;<code>d</code>&nbsp;中只能参加一场会议。</p>

<p>请你返回你可以参加的&nbsp;<strong>最大&nbsp;</strong>会议数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1353.Maximum%20Number%20of%20Events%20That%20Can%20Be%20Attended/images/e1.png" style="height: 267px; width: 400px;" /></p>

<pre>
<strong>输入：</strong>events = [[1,2],[2,3],[3,4]]
<strong>输出：</strong>3
<strong>解释：</strong>你可以参加所有的三个会议。
安排会议的一种方案如上图。
第 1 天参加第一个会议。
第 2 天参加第二个会议。
第 3 天参加第三个会议。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>events= [[1,2],[2,3],[3,4],[1,2]]
<strong>输出：</strong>4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong>​​​​​​</p>

<ul>
	<li><code>1 &lt;= events.length &lt;= 10<sup>5</sup></code></li>
	<li><code>events[i].length == 2</code></li>
	<li><code>1 &lt;= startDay<sub>i</sub>&nbsp;&lt;= endDay<sub>i</sub>&nbsp;&lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 贪心 + 优先队列

我们用一个哈希表 $\textit{g}$ 记录每个会议的开始和结束时间。键为会议的开始时间，值为一个列表，包含所有在该开始时间开始的会议的结束时间。用两个变量 $\textit{l}$ 和 $\textit{r}$ 分别记录会议的最小开始时间和最大结束时间。

对于从小到大每个在 $\textit{l}$ 到 $\textit{r}$ 的时间点 $s$，我们需要做以下操作：

1. 从优先队列中移除所有结束时间小于当前时间 $s$ 的会议。
2. 将所有开始时间等于当前时间 $s$ 的会议的结束时间加入优先队列中。
3. 如果优先队列不为空，则取出结束时间最小的会议，累加答案数，并从优先队列中移除该会议。

这样，我们可以确保在每个时间点 $s$，我们都能参加结束时间最早的会议，从而最大化参加的会议数。

时间复杂度 $O(M \times \log n)$，空间复杂度 $O(n)$，其中 $M$ 和 $n$ 分别为会议的最大结束时间和会议的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxEvents(self, events: List[List[int]]) -> int:
        g = defaultdict(list)
        l, r = inf, 0
        for s, e in events:
            g[s].append(e)
            l = min(l, s)
            r = max(r, e)
        pq = []
        ans = 0
        for s in range(l, r + 1):
            while pq and pq[0] < s:
                heappop(pq)
            for e in g[s]:
                heappush(pq, e)
            if pq:
                heappop(pq)
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int maxEvents(int[][] events) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        int l = Integer.MAX_VALUE, r = 0;
        for (int[] event : events) {
            int s = event[0], e = event[1];
            g.computeIfAbsent(s, k -> new ArrayList<>()).add(e);
            l = Math.min(l, s);
            r = Math.max(r, e);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        for (int s = l; s <= r; s++) {
            while (!pq.isEmpty() && pq.peek() < s) {
                pq.poll();
            }
            for (int e : g.getOrDefault(s, List.of())) {
                pq.offer(e);
            }
            if (!pq.isEmpty()) {
                pq.poll();
                ans++;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxEvents(vector<vector<int>>& events) {
        unordered_map<int, vector<int>> g;
        int l = INT_MAX, r = 0;
        for (auto& event : events) {
            int s = event[0], e = event[1];
            g[s].push_back(e);
            l = min(l, s);
            r = max(r, e);
        }
        priority_queue<int, vector<int>, greater<int>> pq;
        int ans = 0;
        for (int s = l; s <= r; ++s) {
            while (!pq.empty() && pq.top() < s) {
                pq.pop();
            }
            for (int e : g[s]) {
                pq.push(e);
            }
            if (!pq.empty()) {
                pq.pop();
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxEvents(events [][]int) (ans int) {
	g := map[int][]int{}
	l, r := math.MaxInt32, 0
	for _, event := range events {
		s, e := event[0], event[1]
		g[s] = append(g[s], e)
		l = min(l, s)
		r = max(r, e)
	}

	pq := &hp{}
	heap.Init(pq)
	for s := l; s <= r; s++ {
		for pq.Len() > 0 && pq.IntSlice[0] < s {
			heap.Pop(pq)
		}
		for _, e := range g[s] {
			heap.Push(pq, e)
		}
		if pq.Len() > 0 {
			heap.Pop(pq)
			ans++
		}
	}
	return
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v any) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	n := len(h.IntSlice)
	v := h.IntSlice[n-1]
	h.IntSlice = h.IntSlice[:n-1]
	return v
}
func (h *hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
```

#### TypeScript

```ts
function maxEvents(events: number[][]): number {
    const g: Map<number, number[]> = new Map();
    let l = Infinity,
        r = 0;
    for (const [s, e] of events) {
        if (!g.has(s)) g.set(s, []);
        g.get(s)!.push(e);
        l = Math.min(l, s);
        r = Math.max(r, e);
    }

    const pq = new MinPriorityQueue<number>();
    let ans = 0;
    for (let s = l; s <= r; s++) {
        while (!pq.isEmpty() && pq.front() < s) {
            pq.dequeue();
        }
        for (const e of g.get(s) || []) {
            pq.enqueue(e);
        }
        if (!pq.isEmpty()) {
            pq.dequeue();
            ans++;
        }
    }
    return ans;
}
```

#### Rust

```rust
use std::collections::{BinaryHeap, HashMap};
use std::cmp::Reverse;

impl Solution {
    pub fn max_events(events: Vec<Vec<i32>>) -> i32 {
        let mut g: HashMap<i32, Vec<i32>> = HashMap::new();
        let mut l = i32::MAX;
        let mut r = 0;

        for event in events {
            let s = event[0];
            let e = event[1];
            g.entry(s).or_default().push(e);
            l = l.min(s);
            r = r.max(e);
        }

        let mut pq = BinaryHeap::new();
        let mut ans = 0;

        for s in l..=r {
            while let Some(&Reverse(top)) = pq.peek() {
                if top < s {
                    pq.pop();
                } else {
                    break;
                }
            }
            if let Some(ends) = g.get(&s) {
                for &e in ends {
                    pq.push(Reverse(e));
                }
            }
            if pq.pop().is_some() {
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
