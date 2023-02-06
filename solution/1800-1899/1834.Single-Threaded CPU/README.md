# [1834. 单线程 CPU](https://leetcode.cn/problems/single-threaded-cpu)

[English Version](/solution/1800-1899/1834.Single-Threaded%20CPU/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个二维数组 <code>tasks</code> ，用于表示 <code>n</code>​​​​​​ 项从 <code>0</code> 到 <code>n - 1</code> 编号的任务。其中 <code>tasks[i] = [enqueueTime<sub>i</sub>, processingTime<sub>i</sub>]</code> 意味着第 <code>i<sup>​​​​​​</sup></code>​​​​ 项任务将会于 <code>enqueueTime<sub>i</sub></code> 时进入任务队列，需要 <code>processingTime<sub>i</sub></code><sub> </sub>的时长完成执行。</p>

<p>现有一个单线程 CPU ，同一时间只能执行 <strong>最多一项</strong> 任务，该 CPU 将会按照下述方式运行：</p>

<ul>
	<li>如果 CPU 空闲，且任务队列中没有需要执行的任务，则 CPU 保持空闲状态。</li>
	<li>如果 CPU 空闲，但任务队列中有需要执行的任务，则 CPU 将会选择 <strong>执行时间最短</strong> 的任务开始执行。如果多个任务具有同样的最短执行时间，则选择下标最小的任务开始执行。</li>
	<li>一旦某项任务开始执行，CPU 在 <strong>执行完整个任务</strong> 前都不会停止。</li>
	<li>CPU 可以在完成一项任务后，立即开始执行一项新任务。</li>
</ul>

<p>返回<em> </em>CPU<em> </em>处理任务的顺序。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>tasks = [[1,2],[2,4],[3,2],[4,1]]
<strong>输出：</strong>[0,2,3,1]
<strong>解释：</strong>事件按下述流程运行： 
- time = 1 ，任务 0 进入任务队列，可执行任务项 = {0}
- 同样在 time = 1 ，空闲状态的 CPU 开始执行任务 0 ，可执行任务项 = {}
- time = 2 ，任务 1 进入任务队列，可执行任务项 = {1}
- time = 3 ，任务 2 进入任务队列，可执行任务项 = {1, 2}
- 同样在 time = 3 ，CPU 完成任务 0 并开始执行队列中用时最短的任务 2 ，可执行任务项 = {1}
- time = 4 ，任务 3 进入任务队列，可执行任务项 = {1, 3}
- time = 5 ，CPU 完成任务 2 并开始执行队列中用时最短的任务 3 ，可执行任务项 = {1}
- time = 6 ，CPU 完成任务 3 并开始执行任务 1 ，可执行任务项 = {}
- time = 10 ，CPU 完成任务 1 并进入空闲状态
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
<strong>输出：</strong>[4,3,2,0,1]
<strong>解释：</strong>事件按下述流程运行： 
- time = 7 ，所有任务同时进入任务队列，可执行任务项  = {0,1,2,3,4}
- 同样在 time = 7 ，空闲状态的 CPU 开始执行任务 4 ，可执行任务项 = {0,1,2,3}
- time = 9 ，CPU 完成任务 4 并开始执行任务 3 ，可执行任务项 = {0,1,2}
- time = 13 ，CPU 完成任务 3 并开始执行任务 2 ，可执行任务项 = {0,1}
- time = 18 ，CPU 完成任务 2 并开始执行任务 0 ，可执行任务项 = {1}
- time = 28 ，CPU 完成任务 0 并开始执行任务 1 ，可执行任务项 = {}
- time = 40 ，CPU 完成任务 1 并进入空闲状态</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>tasks.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= enqueueTime<sub>i</sub>, processingTime<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 优先队列（小根堆）**

我们先将任务按照 `enqueueTime` 从小到大排序，接下来用一个优先队列（小根堆）维护当前可执行的任务，队列中的元素为 `(processingTime, index)`，即任务的执行时间和任务的编号。另外用一个变量 $t$ 表示当前时间，初始值为 $0$。

接下来我们模拟任务的执行过程。

如果当前队列为空，说明当前没有可执行的任务，我们将 $t$ 更新为下一个任务的 `enqueueTime` 与当前时间 $t$ 中的较大值。接下来将所有 `enqueueTime` 小于等于 $t$ 的任务加入队列。

然后从队列中取出一个任务，将其编号加入答案数组，然后将 $t$ 更新为当前时间 $t$ 与当前任务的执行时间之和。

循环上述过程，直到队列为空，且所有任务都已经加入过队列。

时间复杂度 $O(n \times \log n)$，其中 $n$ 为任务的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def getOrder(self, tasks: List[List[int]]) -> List[int]:
        for i, task in enumerate(tasks):
            task.append(i)
        tasks.sort()
        ans = []
        q = []
        n = len(tasks)
        i = t = 0
        while q or i < n:
            if not q:
                t = max(t, tasks[i][0])
            while i < n and tasks[i][0] <= t:
                heappush(q, (tasks[i][1], tasks[i][2]))
                i += 1
            pt, j = heappop(q)
            ans.append(j)
            t += pt
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] ts = new int[n][3];
        for (int i = 0; i < n; ++i) {
            ts[i] = new int[] {tasks[i][0], tasks[i][1], i};
        }
        Arrays.sort(ts, (a, b) -> a[0] - b[0]);
        int[] ans = new int[n];
        PriorityQueue<int[]> q
            = new PriorityQueue<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int i = 0, t = 0, k = 0;
        while (!q.isEmpty() || i < n) {
            if (q.isEmpty()) {
                t = Math.max(t, ts[i][0]);
            }
            while (i < n && ts[i][0] <= t) {
                q.offer(new int[] {ts[i][1], ts[i][2]});
                ++i;
            }
            var p = q.poll();
            ans[k++] = p[1];
            t += p[0];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> getOrder(vector<vector<int>>& tasks) {
        int n = 0;
        for (auto& task : tasks) task.push_back(n++);
        sort(tasks.begin(), tasks.end());
        using pii = pair<int, int>;
        priority_queue<pii, vector<pii>, greater<pii>> q;
        int i = 0;
        long long t = 0;
        vector<int> ans;
        while (!q.empty() || i < n) {
            if (q.empty()) t = max(t, (long long) tasks[i][0]);
            while (i < n && tasks[i][0] <= t) {
                q.push({tasks[i][1], tasks[i][2]});
                ++i;
            }
            auto [pt, j] = q.top();
            q.pop();
            ans.push_back(j);
            t += pt;
        }
        return ans;
    }
};
```

### **Go**

```go
func getOrder(tasks [][]int) (ans []int) {
	for i := range tasks {
		tasks[i] = append(tasks[i], i)
	}
	sort.Slice(tasks, func(i, j int) bool { return tasks[i][0] < tasks[j][0] })
	q := hp{}
	i, t, n := 0, 0, len(tasks)
	for len(q) > 0 || i < n {
		if len(q) == 0 {
			t = max(t, tasks[i][0])
		}
		for i < n && tasks[i][0] <= t {
			heap.Push(&q, pair{tasks[i][1], tasks[i][2]})
			i++
		}
		p := heap.Pop(&q).(pair)
		ans = append(ans, p.i)
		t += p.t
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

type pair struct{ t, i int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].t < h[j].t || (h[i].t == h[j].t && h[i].i < h[j].i) }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
```

### **...**

```

```

<!-- tabs:end -->
