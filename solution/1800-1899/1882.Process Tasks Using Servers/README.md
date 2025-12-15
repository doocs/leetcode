---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1882.Process%20Tasks%20Using%20Servers/README.md
rating: 1979
source: 第 243 场周赛 Q3
tags:
    - 数组
    - 堆（优先队列）
---

<!-- problem:start -->

# [1882. 使用服务器处理任务](https://leetcode.cn/problems/process-tasks-using-servers)

[English Version](/solution/1800-1899/1882.Process%20Tasks%20Using%20Servers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个 <strong>下标从 0 开始</strong> 的整数数组 <code>servers</code> 和 <code>tasks</code> ，长度分别为 <code>n</code>​​​​​​ 和 <code>m</code>​​​​​​ 。<code>servers[i]</code> 是第 <code>i<sup>​​​​​​</sup></code>​​​​ 台服务器的 <strong>权重</strong> ，而 <code>tasks[j]</code> 是处理第 <code>j<sup>​​​​​​</sup></code> 项任务 <strong>所需要的时间</strong>（单位：秒）。</p>

<p>你正在运行一个仿真系统，在处理完所有任务后，该系统将会关闭。每台服务器只能同时处理一项任务。第 <code>0</code> 项任务在第 <code>0</code> 秒可以开始处理，相应地，第 <code>j</code> 项任务在第 <code>j</code> 秒可以开始处理。处理第 <code>j</code> 项任务时，你需要为它分配一台 <strong>权重最小</strong> 的空闲服务器。如果存在多台相同权重的空闲服务器，请选择 <strong>下标最小</strong> 的服务器。如果一台空闲服务器在第 <code>t</code> 秒分配到第 <code>j</code> 项任务，那么在 <code>t + tasks[j]</code> 时它将恢复空闲状态。</p>

<p>如果没有空闲服务器，则必须等待，直到出现一台空闲服务器，并 <strong>尽可能早</strong> 地处理剩余任务。 如果有多项任务等待分配，则按照 <strong>下标递增</strong> 的顺序完成分配。</p>

<p>如果同一时刻存在多台空闲服务器，可以同时将多项任务分别分配给它们。</p>

<p>构建长度为 <code>m</code> 的答案数组 <code>ans</code> ，其中 <code>ans[j]</code> 是第 <code>j</code> 项任务分配的服务器的下标。</p>

<p>返回答案数组<em> </em><code>ans</code>​​​​ 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>servers = [3,3,2], tasks = [1,2,3,2,1,2]
<strong>输出：</strong>[2,2,0,2,1,2]
<strong>解释：</strong>事件按时间顺序如下：
- 0 秒时，第 0 项任务加入到任务队列，使用第 2 台服务器处理到 1 秒。
- 1 秒时，第 2 台服务器空闲，第 1 项任务加入到任务队列，使用第 2 台服务器处理到 3 秒。
- 2 秒时，第 2 项任务加入到任务队列，使用第 0 台服务器处理到 5 秒。
- 3 秒时，第 2 台服务器空闲，第 3 项任务加入到任务队列，使用第 2 台服务器处理到 5 秒。
- 4 秒时，第 4 项任务加入到任务队列，使用第 1 台服务器处理到 5 秒。
- 5 秒时，所有服务器都空闲，第 5 项任务加入到任务队列，使用第 2 台服务器处理到 7 秒。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>servers = [5,1,4,3,2], tasks = [2,1,2,4,5,2,1]
<strong>输出：</strong>[1,4,1,4,1,3,2]
<strong>解释：</strong>事件按时间顺序如下：
- 0 秒时，第 0 项任务加入到任务队列，使用第 1 台服务器处理到 2 秒。
- 1 秒时，第 1 项任务加入到任务队列，使用第 4 台服务器处理到 2 秒。
- 2 秒时，第 1 台和第 4 台服务器空闲，第 2 项任务加入到任务队列，使用第 1 台服务器处理到 4 秒。
- 3 秒时，第 3 项任务加入到任务队列，使用第 4 台服务器处理到 7 秒。
- 4 秒时，第 1 台服务器空闲，第 4 项任务加入到任务队列，使用第 1 台服务器处理到 9 秒。
- 5 秒时，第 5 项任务加入到任务队列，使用第 3 台服务器处理到 7 秒。
- 6 秒时，第 6 项任务加入到任务队列，使用第 2 台服务器处理到 7 秒。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>servers.length == n</code></li>
	<li><code>tasks.length == m</code></li>
	<li><code>1 <= n, m <= 2 * 10<sup>5</sup></code></li>
	<li><code>1 <= servers[i], tasks[j] <= 2 * 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：优先队列（小根堆）

我们用一个小根堆 $\textit{idle}$ 来维护所有的空闲服务器，其中每个元素是一个二元组 $(x, i)$，表示第 $i$ 台服务器的权重为 $x$。用一个小根堆 $\textit{busy}$ 来维护所有的忙碌服务器，其中每个元素是一个三元组 $(w, s, i)$，表示第 $i$ 台服务器在第 $w$ 秒恢复空闲，权重为 $s$。初始时我们将所有的服务器加入到 $\textit{idle}$ 中。

接下来，我们遍历所有的任务，对于第 $j$ 项任务，我们首先将所有在第 $j$ 秒或之前恢复空闲的服务器从 $\textit{busy}$ 中移除，添加到 $\textit{idle}$ 中。然后我们从 $\textit{idle}$ 中取出一个权重最小的服务器，将其加入到 $\textit{busy}$ 中，处理第 $j$ 项任务。如果 $\textit{idle}$ 为空，我们从 $\textit{busy}$ 中取出一个恢复时间最早的服务器，将其加入到 $\textit{busy}$ 中，处理第 $j$ 项任务。

遍历结束后，我们得到了答案数组 $\textit{ans}$。

时间复杂度 $O((n + m) \log n)$，其中 $n$ 为服务器的数量，$m$ 为任务的数量。空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别为服务器和任务的数量。

相似题目：

- [2402. 会议室 III](https://github.com/doocs/leetcode/blob/main/solution/2400-2499/2402.Meeting%20Rooms%20III/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def assignTasks(self, servers: List[int], tasks: List[int]) -> List[int]:
        idle = [(x, i) for i, x in enumerate(servers)]
        heapify(idle)
        busy = []
        ans = []
        for j, t in enumerate(tasks):
            while busy and busy[0][0] <= j:
                _, s, i = heappop(busy)
                heappush(idle, (s, i))
            if idle:
                s, i = heappop(idle)
                heappush(busy, (j + t, s, i))
            else:
                w, s, i = heappop(busy)
                heappush(busy, (w + t, s, i))
            ans.append(i)
        return ans
```

#### Java

```java
class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        PriorityQueue<int[]> idle = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        for (int i = 0; i < n; i++) {
            idle.offer(new int[] {servers[i], i});
        }
        int m = tasks.length;
        int[] ans = new int[m];
        for (int j = 0; j < m; ++j) {
            int t = tasks[j];
            while (!busy.isEmpty() && busy.peek()[0] <= j) {
                int[] p = busy.poll();
                idle.offer(new int[] {p[1], p[2]});
            }
            if (!idle.isEmpty()) {
                int i = idle.poll()[1];
                ans[j] = i;
                busy.offer(new int[] {j + t, servers[i], i});
            } else {
                int[] p = busy.poll();
                int i = p[2];
                ans[j] = i;
                busy.offer(new int[] {p[0] + t, p[1], i});
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
    vector<int> assignTasks(vector<int>& servers, vector<int>& tasks) {
        using pii = pair<int, int>;
        using arr3 = array<int, 3>;
        priority_queue<pii, vector<pii>, greater<pii>> idle;
        priority_queue<arr3, vector<arr3>, greater<arr3>> busy;
        for (int i = 0; i < servers.size(); ++i) {
            idle.push({servers[i], i});
        }
        int m = tasks.size();
        vector<int> ans(m);
        for (int j = 0; j < m; ++j) {
            int t = tasks[j];
            while (!busy.empty() && busy.top()[0] <= j) {
                auto [_, s, i] = busy.top();
                busy.pop();
                idle.push({s, i});
            }

            if (!idle.empty()) {
                auto [s, i] = idle.top();
                idle.pop();
                ans[j] = i;
                busy.push({j + t, s, i});
            } else {
                auto [w, s, i] = busy.top();
                busy.pop();
                ans[j] = i;
                busy.push({w + t, s, i});
            }
        }
        return ans;
    }
};
```

#### Go

```go
func assignTasks(servers []int, tasks []int) (ans []int) {
	idle := hp{}
	busy := hp2{}
	for i, x := range servers {
		heap.Push(&idle, pair{x, i})
	}
	for j, t := range tasks {
		for len(busy) > 0 && busy[0].w <= j {
			p := heap.Pop(&busy).(tuple)
			heap.Push(&idle, pair{p.s, p.i})
		}
		if idle.Len() > 0 {
			p := heap.Pop(&idle).(pair)
			ans = append(ans, p.i)
			heap.Push(&busy, tuple{j + t, p.s, p.i})
		} else {
			p := heap.Pop(&busy).(tuple)
			ans = append(ans, p.i)
			heap.Push(&busy, tuple{p.w + t, p.s, p.i})
		}
	}
	return
}

type pair struct {
	s int
	i int
}

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.s < b.s || a.s == b.s && a.i < b.i
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }

type tuple struct {
	w int
	s int
	i int
}

type hp2 []tuple

func (h hp2) Len() int { return len(h) }
func (h hp2) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.w < b.w || a.w == b.w && (a.s < b.s || a.s == b.s && a.i < b.i)
}
func (h hp2) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp2) Push(v any)   { *h = append(*h, v.(tuple)) }
func (h *hp2) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

#### TypeScript

```ts
function assignTasks(servers: number[], tasks: number[]): number[] {
    const idle = new PriorityQueue({
        compare: (a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]),
    });
    const busy = new PriorityQueue({
        compare: (a, b) =>
            a[0] === b[0] ? (a[1] === b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0],
    });
    for (let i = 0; i < servers.length; ++i) {
        idle.enqueue([servers[i], i]);
    }
    const ans: number[] = [];
    for (let j = 0; j < tasks.length; ++j) {
        const t = tasks[j];
        while (busy.size() > 0 && busy.front()![0] <= j) {
            const [_, s, i] = busy.dequeue()!;
            idle.enqueue([s, i]);
        }
        if (idle.size() > 0) {
            const [s, i] = idle.dequeue()!;
            busy.enqueue([j + t, s, i]);
            ans.push(i);
        } else {
            const [w, s, i] = busy.dequeue()!;
            busy.enqueue([w + t, s, i]);
            ans.push(i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
