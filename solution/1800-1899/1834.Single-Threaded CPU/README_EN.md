# [1834. Single-Threaded CPU](https://leetcode.com/problems/single-threaded-cpu)

[中文文档](/solution/1800-1899/1834.Single-Threaded%20CPU/README.md)

## Description

<p>You are given <code>n</code>​​​​​​ tasks labeled from <code>0</code> to <code>n - 1</code> represented by a 2D integer array <code>tasks</code>, where <code>tasks[i] = [enqueueTime<sub>i</sub>, processingTime<sub>i</sub>]</code> means that the <code>i<sup>​​​​​​th</sup></code>​​​​ task will be available to process at <code>enqueueTime<sub>i</sub></code> and will take <code>processingTime<sub>i</sub></code><sub> </sub>to finish processing.</p>

<p>You have a single-threaded CPU that can process <strong>at most one</strong> task at a time and will act in the following way:</p>

<ul>
	<li>If the CPU is idle and there are no available tasks to process, the CPU remains idle.</li>
	<li>If the CPU is idle and there are available tasks, the CPU will choose the one with the <strong>shortest processing time</strong>. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.</li>
	<li>Once a task is started, the CPU will <strong>process the entire task</strong> without stopping.</li>
	<li>The CPU can finish a task then start a new one instantly.</li>
</ul>

<p>Return <em>the order in which the CPU will process the tasks.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tasks = [[1,2],[2,4],[3,2],[4,1]]
<strong>Output:</strong> [0,2,3,1]
<strong>Explanation: </strong>The events go as follows: 
- At time = 1, task 0 is available to process. Available tasks = {0}.
- Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
- At time = 2, task 1 is available to process. Available tasks = {1}.
- At time = 3, task 2 is available to process. Available tasks = {1, 2}.
- Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
- At time = 4, task 3 is available to process. Available tasks = {1, 3}.
- At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
- At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
- At time = 10, the CPU finishes task 1 and becomes idle.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
<strong>Output:</strong> [4,3,2,0,1]
<strong>Explanation</strong><strong>: </strong>The events go as follows:
- At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
- Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
- At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
- At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
- At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
- At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
- At time = 40, the CPU finishes task 1 and becomes idle.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>tasks.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= enqueueTime<sub>i</sub>, processingTime<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
