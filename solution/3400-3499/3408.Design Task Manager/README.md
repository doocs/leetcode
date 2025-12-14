---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3408.Design%20Task%20Manager/README.md
rating: 1806
source: 第 147 场双周赛 Q2
tags:
    - 设计
    - 哈希表
    - 有序集合
    - 堆（优先队列）
---

<!-- problem:start -->

# [3408. 设计任务管理器](https://leetcode.cn/problems/design-task-manager)

[English Version](/solution/3400-3499/3408.Design%20Task%20Manager/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个任务管理器系统可以让用户管理他们的任务，每个任务有一个优先级。这个系统需要高效地处理添加、修改、执行和删除任务的操作。</p>

<p>请你设计一个&nbsp;<code>TaskManager</code>&nbsp;类：</p>

<ul>
	<li>
	<p><code>TaskManager(vector&lt;vector&lt;int&gt;&gt;&amp; tasks)</code>&nbsp;初始化任务管理器，初始化的数组格式为&nbsp;<code>[userId, taskId, priority]</code>&nbsp;，表示给 <code>userId</code>&nbsp;添加一个优先级为 <code>priority</code>&nbsp;的任务 <code>taskId</code>&nbsp;。</p>
	</li>
	<li>
	<p><code>void add(int userId, int taskId, int priority)</code>&nbsp;表示给用户 <code>userId</code>&nbsp;添加一个优先级为 <code>priority</code>&nbsp;的任务 <code>taskId</code>&nbsp;，输入 <strong>保证&nbsp;</strong><code>taskId</code>&nbsp;不在系统中。</p>
	</li>
	<li>
	<p><code>void edit(int taskId, int newPriority)</code>&nbsp;更新已经存在的任务&nbsp;<code>taskId</code>&nbsp;的优先级为&nbsp;<code>newPriority</code>&nbsp;。输入 <strong>保证</strong>&nbsp;<code>taskId</code>&nbsp;存在于系统中。</p>
	</li>
	<li>
	<p><code>void rmv(int taskId)</code>&nbsp;从系统中删除任务&nbsp;<code>taskId</code>&nbsp;。输入 <strong>保证</strong>&nbsp;<code>taskId</code>&nbsp;存在于系统中。</p>
	</li>
	<li>
	<p><code>int execTop()</code>&nbsp;执行所有用户的任务中优先级 <strong>最高</strong>&nbsp;的任务，如果有多个任务优先级相同且都为 <strong>最高</strong>&nbsp;，执行&nbsp;<code>taskId</code>&nbsp;最大的一个任务。执行完任务后，<code>taskId</code><strong>&nbsp;</strong>从系统中 <strong>删除</strong>&nbsp;。同时请你返回这个任务所属的用户&nbsp;<code>userId</code>&nbsp;。如果不存在任何任务，返回&nbsp;-1 。</p>
	</li>
</ul>

<p><strong>注意</strong> ，一个用户可能被安排多个任务。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><br />
<span class="example-io">["TaskManager", "add", "edit", "execTop", "rmv", "add", "execTop"]<br />
[[[[1, 101, 10], [2, 102, 20], [3, 103, 15]]], [4, 104, 5], [102, 8], [], [101], [5, 105, 15], []]</span></p>

<p><strong>输出：</strong><br />
<span class="example-io">[null, null, null, 3, null, null, 5] </span></p>

<p><strong>解释：</strong></p>
TaskManager taskManager = new TaskManager([[1, 101, 10], [2, 102, 20], [3, 103, 15]]); // 分别给用户 1 ，2 和 3 初始化一个任务。<br />
taskManager.add(4, 104, 5); // 给用户 4 添加优先级为 5 的任务 104 。<br />
taskManager.edit(102, 8); // 更新任务 102 的优先级为 8 。<br />
taskManager.execTop(); // 返回 3 。执行用户 3 的任务 103 。<br />
taskManager.rmv(101); // 将系统中的任务 101 删除。<br />
taskManager.add(5, 105, 15); // 给用户 5 添加优先级为 15 的任务 105 。<br />
taskManager.execTop(); // 返回 5 。执行用户 5 的任务 105 。</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= userId &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= taskId &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= priority &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= newPriority &lt;= 10<sup>9</sup></code></li>
	<li><code>add</code>&nbsp;，<code>edit</code>&nbsp;，<code>rmv</code>&nbsp;和&nbsp;<code>execTop</code>&nbsp;的总操作次数 <strong>加起来</strong>&nbsp;不超过&nbsp;<code>2 * 10<sup>5</sup></code> 次。</li>
	<li>输入保证&nbsp;<code>taskId</code> 是合法的。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 有序集合

我们用一个哈希表 $\text{d}$ 来存储任务信息，键为任务 ID，值为一个二元组 $(\text{userId}, \text{priority})$，表示该任务所属的用户 ID 以及任务的优先级。

我们用一个有序集合 $\text{st}$ 来存储当前系统中的所有任务，元素为一个二元组 $(-\text{priority}, -\text{taskId})$，表示任务的优先级和任务 ID 的相反数。我们将优先级和任务 ID 取相反数是为了让优先级最高且任务 ID 最大的任务在有序集合中排在最前面。

对于每个操作，我们可以按如下方式进行处理：

- **初始化**：对于每个任务 $(\text{userId}, \text{taskId}, \text{priority})$，我们将其添加到哈希表 $\text{d}$ 和有序集合 $\text{st}$ 中。
- **添加任务**：将任务 $(\text{userId}, \text{taskId}, \text{priority})$ 添加到哈希表 $\text{d}$ 和有序集合 $\text{st}$ 中。
- **编辑任务**：从哈希表 $\text{d}$ 中获取任务 ID 对应的用户 ID 和旧优先级，然后从有序集合 $\text{st}$ 中删除旧的任务信息，再将新的任务信息添加到哈希表 $\text{d}$ 和有序集合 $\text{st}$ 中。
- **删除任务**：从哈希表 $\text{d}$ 中获取任务 ID 对应的优先级，然后从有序集合 $\text{st}$ 中删除任务信息，并从哈希表 $\text{d}$ 中删除任务。
- **执行最高优先级任务**：如果有序集合 $\text{st}$ 为空，返回 -1。否则，从有序集合 $\text{st}$ 中取出第一个元素，获取任务 ID，然后从哈希表 $\text{d}$ 中获取对应的用户 ID，并将任务从哈希表 $\text{d}$ 和有序集合 $\text{st}$ 中删除，最后返回用户 ID。

时间复杂度方面，初始化操作需要 $O(n \log n)$ 的时间，其中 $n$ 是初始任务的数量。每个添加、编辑、删除和执行操作都需要 $O(\log m)$ 的时间，其中 $m$ 是当前系统中的任务数量。由于总操作次数不超过 $2 \times 10^5$，因此整体时间复杂度是可接受的。空间复杂度 $O(n + m)$，用于存储哈希表和有序集合。

<!-- tabs:start -->

#### Python3

```python
class TaskManager:

    def __init__(self, tasks: List[List[int]]):
        self.d = {}
        self.st = SortedList()
        for task in tasks:
            self.add(*task)

    def add(self, userId: int, taskId: int, priority: int) -> None:
        self.d[taskId] = (userId, priority)
        self.st.add((-priority, -taskId))

    def edit(self, taskId: int, newPriority: int) -> None:
        userId, priority = self.d[taskId]
        self.st.discard((-priority, -taskId))
        self.d[taskId] = (userId, newPriority)
        self.st.add((-newPriority, -taskId))

    def rmv(self, taskId: int) -> None:
        _, priority = self.d[taskId]
        self.d.pop(taskId)
        self.st.remove((-priority, -taskId))

    def execTop(self) -> int:
        if not self.st:
            return -1
        taskId = -self.st.pop(0)[1]
        userId, _ = self.d[taskId]
        self.d.pop(taskId)
        return userId


# Your TaskManager object will be instantiated and called as such:
# obj = TaskManager(tasks)
# obj.add(userId,taskId,priority)
# obj.edit(taskId,newPriority)
# obj.rmv(taskId)
# param_4 = obj.execTop()
```

#### Java

```java
class TaskManager {
    private final Map<Integer, int[]> d = new HashMap<>();
    private final TreeSet<int[]> st = new TreeSet<>((a, b) -> {
        if (a[0] == b[0]) {
            return b[1] - a[1];
        }
        return b[0] - a[0];
    });

    public TaskManager(List<List<Integer>> tasks) {
        for (var task : tasks) {
            add(task.get(0), task.get(1), task.get(2));
        }
    }

    public void add(int userId, int taskId, int priority) {
        d.put(taskId, new int[] {userId, priority});
        st.add(new int[] {priority, taskId});
    }

    public void edit(int taskId, int newPriority) {
        var e = d.get(taskId);
        int userId = e[0], priority = e[1];
        st.remove(new int[] {priority, taskId});
        st.add(new int[] {newPriority, taskId});
        d.put(taskId, new int[] {userId, newPriority});
    }

    public void rmv(int taskId) {
        var e = d.remove(taskId);
        int priority = e[1];
        st.remove(new int[] {priority, taskId});
    }

    public int execTop() {
        if (st.isEmpty()) {
            return -1;
        }
        var e = st.pollFirst();
        var t = d.remove(e[1]);
        return t[0];
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */
```

#### C++

```cpp
class TaskManager {
private:
    unordered_map<int, pair<int, int>> d;
    set<pair<int, int>> st;

public:
    TaskManager(vector<vector<int>>& tasks) {
        for (const auto& task : tasks) {
            add(task[0], task[1], task[2]);
        }
    }

    void add(int userId, int taskId, int priority) {
        d[taskId] = {userId, priority};
        st.insert({-priority, -taskId});
    }

    void edit(int taskId, int newPriority) {
        auto [userId, priority] = d[taskId];
        st.erase({-priority, -taskId});
        st.insert({-newPriority, -taskId});
        d[taskId] = {userId, newPriority};
    }

    void rmv(int taskId) {
        auto [userId, priority] = d[taskId];
        st.erase({-priority, -taskId});
        d.erase(taskId);
    }

    int execTop() {
        if (st.empty()) {
            return -1;
        }
        auto e = *st.begin();
        st.erase(st.begin());
        int taskId = -e.second;
        int userId = d[taskId].first;
        d.erase(taskId);
        return userId;
    }
};

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager* obj = new TaskManager(tasks);
 * obj->add(userId,taskId,priority);
 * obj->edit(taskId,newPriority);
 * obj->rmv(taskId);
 * int param_4 = obj->execTop();
 */
```

#### Go

```go
type TaskManager struct {
	d  map[int][2]int
	st *redblacktree.Tree[int, int]
}

func encode(priority, taskId int) int {
	return (priority << 32) | taskId
}

func comparator(a, b int) int {
	if a > b {
		return -1
	} else if a < b {
		return 1
	}
	return 0
}

func Constructor(tasks [][]int) TaskManager {
	tm := TaskManager{
		d:  make(map[int][2]int),
		st: redblacktree.NewWith[int, int](comparator),
	}
	for _, task := range tasks {
		tm.Add(task[0], task[1], task[2])
	}
	return tm
}

func (this *TaskManager) Add(userId int, taskId int, priority int) {
	this.d[taskId] = [2]int{userId, priority}
	this.st.Put(encode(priority, taskId), taskId)
}

func (this *TaskManager) Edit(taskId int, newPriority int) {
	if e, ok := this.d[taskId]; ok {
		priority := e[1]
		this.st.Remove(encode(priority, taskId))
		this.d[taskId] = [2]int{e[0], newPriority}
		this.st.Put(encode(newPriority, taskId), taskId)
	}
}

func (this *TaskManager) Rmv(taskId int) {
	if e, ok := this.d[taskId]; ok {
		priority := e[1]
		delete(this.d, taskId)
		this.st.Remove(encode(priority, taskId))
	}
}

func (this *TaskManager) ExecTop() int {
	if this.st.Empty() {
		return -1
	}
	it := this.st.Iterator()
	it.Next()
	taskId := it.Value()
	if e, ok := this.d[taskId]; ok {
		delete(this.d, taskId)
		this.st.Remove(it.Key())
		return e[0]
	}
	return -1
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * obj := Constructor(tasks);
 * obj.Add(userId,taskId,priority);
 * obj.Edit(taskId,newPriority);
 * obj.Rmv(taskId);
 * param_4 := obj.ExecTop();
 */
```

#### TypeScript

```ts
class TaskManager {
    private d: Map<number, [number, number]>;
    private pq: PriorityQueue<[number, number]>;

    constructor(tasks: number[][]) {
        this.d = new Map();
        this.pq = new PriorityQueue<[number, number]>((a, b) => {
            if (a[0] === b[0]) {
                return b[1] - a[1];
            }
            return b[0] - a[0];
        });
        for (const task of tasks) {
            this.add(task[0], task[1], task[2]);
        }
    }

    add(userId: number, taskId: number, priority: number): void {
        this.d.set(taskId, [userId, priority]);
        this.pq.enqueue([priority, taskId]);
    }

    edit(taskId: number, newPriority: number): void {
        const e = this.d.get(taskId);
        if (!e) return;
        const userId = e[0];
        this.d.set(taskId, [userId, newPriority]);
        this.pq.enqueue([newPriority, taskId]);
    }

    rmv(taskId: number): void {
        this.d.delete(taskId);
    }

    execTop(): number {
        while (!this.pq.isEmpty()) {
            const [priority, taskId] = this.pq.dequeue();
            const e = this.d.get(taskId);
            if (e && e[1] === priority) {
                this.d.delete(taskId);
                return e[0];
            }
        }
        return -1;
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * var obj = new TaskManager(tasks)
 * obj.add(userId,taskId,priority)
 * obj.edit(taskId,newPriority)
 * obj.rmv(taskId)
 * var param_4 = obj.execTop()
 */
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
