---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3476.Maximize%20Profit%20from%20Task%20Assignment/README.md
---

<!-- problem:start -->

# [3476. Maximize Profit from Task Assignment 🔒](https://leetcode.cn/problems/maximize-profit-from-task-assignment)

[English Version](/solution/3400-3499/3476.Maximize%20Profit%20from%20Task%20Assignment/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given an integer array <code>workers</code>, where <code>workers[i]</code> represents the skill level of the <code>i<sup>th</sup></code> worker. You are also given a 2D integer array <code>tasks</code>, where:</p>

<ul>
	<li><code>tasks[i][0]</code> represents the skill requirement needed to complete the task.</li>
	<li><code>tasks[i][1]</code> represents the profit earned from completing the task.</li>
</ul>

<p>Each worker can complete <strong>at most</strong> one task, and they can only take a task if their skill level is <strong>equal</strong> to the task&#39;s skill requirement. An <strong>additional</strong> worker joins today who can take up <em>any</em> task, <strong>regardless</strong> of the skill requirement.</p>

<p>Return the <strong>maximum</strong> total profit that can be earned by optimally assigning the tasks to the workers.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">workers = [1,2,3,4,5], tasks = [[1,100],[2,400],[3,100],[3,400]]</span></p>

<p><strong>Output:</strong> <span class="example-io">1000</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Worker 0 completes task 0.</li>
	<li>Worker 1 completes task 1.</li>
	<li>Worker 2 completes task 3.</li>
	<li>The additional worker completes task 2.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">workers = [10,10000,100000000], tasks = [[1,100]]</span></p>

<p><strong>Output:</strong> <span class="example-io">100</span></p>

<p><strong>Explanation:</strong></p>

<p>Since no worker matches the skill requirement, only the additional worker can complete task 0.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">workers = [7], tasks = [[3,3],[3,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The additional worker completes task 1. Worker 0 cannot work since no task has a skill requirement of 7.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= workers.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= workers[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>tasks[i].length == 2</code></li>
	<li><code>1 &lt;= tasks[i][0], tasks[i][1] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 优先队列

由于每个任务只能被一个特定技能的工人完成，因此，我们可以将任务按技能要求分组，放在一个哈希表 $\textit{d}$ 中，其中键是技能要求，值是一个优先队列，按照利润从大到小排序。

然后，我们遍历工人，对于每个工人，我们从哈希表 $\textit{d}$ 中找到其技能要求对应的优先队列，取出队首元素，即该工人能获得的最大利润，然后将其从优先队列中移除。如果优先队列为空，我们将其从哈希表中移除。

最后，我们将剩余任务中的最大利润加到结果中。

时间复杂度 $O((n + m) \times \log m)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别是工人和任务的数量。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxProfit(self, workers: List[int], tasks: List[List[int]]) -> int:
        d = defaultdict(SortedList)
        for skill, profit in tasks:
            d[skill].add(profit)
        ans = 0
        for skill in workers:
            if not d[skill]:
                continue
            ans += d[skill].pop()
        mx = 0
        for ls in d.values():
            if ls:
                mx = max(mx, ls[-1])
        ans += mx
        return ans
```

#### Java

```java
class Solution {
    public long maxProfit(int[] workers, int[][] tasks) {
        Map<Integer, PriorityQueue<Integer>> d = new HashMap<>();
        for (var t : tasks) {
            int skill = t[0], profit = t[1];
            d.computeIfAbsent(skill, k -> new PriorityQueue<>((a, b) -> b - a)).offer(profit);
        }
        long ans = 0;
        for (int skill : workers) {
            if (d.containsKey(skill)) {
                var pq = d.get(skill);
                ans += pq.poll();
                if (pq.isEmpty()) {
                    d.remove(skill);
                }
            }
        }
        int mx = 0;
        for (var pq : d.values()) {
            mx = Math.max(mx, pq.peek());
        }
        ans += mx;
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxProfit(vector<int>& workers, vector<vector<int>>& tasks) {
        unordered_map<int, priority_queue<int>> d;
        for (const auto& t : tasks) {
            d[t[0]].push(t[1]);
        }
        long long ans = 0;
        for (int skill : workers) {
            if (d.contains(skill)) {
                auto& pq = d[skill];
                ans += pq.top();
                pq.pop();
                if (pq.empty()) {
                    d.erase(skill);
                }
            }
        }
        int mx = 0;
        for (const auto& [_, pq] : d) {
            mx = max(mx, pq.top());
        }
        ans += mx;
        return ans;
    }
};
```

#### Go

```go
func maxProfit(workers []int, tasks [][]int) (ans int64) {
	d := make(map[int]*hp)
	for _, t := range tasks {
		skill, profit := t[0], t[1]
		if _, ok := d[skill]; !ok {
			d[skill] = &hp{}
		}
		d[skill].push(profit)
	}
	for _, skill := range workers {
		if _, ok := d[skill]; !ok {
			continue
		}
		ans += int64(d[skill].pop())
		if d[skill].Len() == 0 {
			delete(d, skill)
		}
	}
	mx := 0
	for _, pq := range d {
		for pq.Len() > 0 {
			mx = max(mx, pq.pop())
		}
	}
	ans += int64(mx)
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
function maxProfit(workers: number[], tasks: number[][]): number {
    const d = new Map();
    for (const [skill, profit] of tasks) {
        if (!d.has(skill)) {
            d.set(skill, new MaxPriorityQueue());
        }
        d.get(skill).enqueue(profit);
    }
    let ans = 0;
    for (const skill of workers) {
        const pq = d.get(skill);
        if (pq) {
            ans += pq.dequeue();
            if (pq.size() === 0) {
                d.delete(skill);
            }
        }
    }
    let mx = 0;
    for (const pq of d.values()) {
        mx = Math.max(mx, pq.front());
    }
    ans += mx;
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
