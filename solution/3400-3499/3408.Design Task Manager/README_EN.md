---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3408.Design%20Task%20Manager/README_EN.md
rating: 1806
source: Biweekly Contest 147 Q2
tags:
    - Design
    - Hash Table
    - Ordered Set
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3408. Design Task Manager](https://leetcode.com/problems/design-task-manager)

[中文文档](/solution/3400-3499/3408.Design%20Task%20Manager/README.md)

## Description

<!-- description:start -->

<p>There is a task management system that allows users to manage their tasks, each associated with a priority. The system should efficiently handle adding, modifying, executing, and removing tasks.</p>

<p>Implement the <code>TaskManager</code> class:</p>

<ul>
	<li>
	<p><code>TaskManager(vector&lt;vector&lt;int&gt;&gt;&amp; tasks)</code> initializes the task manager with a list of user-task-priority triples. Each element in the input list is of the form <code>[userId, taskId, priority]</code>, which adds a task to the specified user with the given priority.</p>
	</li>
	<li>
	<p><code>void add(int userId, int taskId, int priority)</code> adds a task with the specified <code>taskId</code> and <code>priority</code> to the user with <code>userId</code>. It is <strong>guaranteed</strong> that <code>taskId</code> does not <em>exist</em> in the system.</p>
	</li>
	<li>
	<p><code>void edit(int taskId, int newPriority)</code> updates the priority of the existing <code>taskId</code> to <code>newPriority</code>. It is <strong>guaranteed</strong> that <code>taskId</code> <em>exists</em> in the system.</p>
	</li>
	<li>
	<p><code>void rmv(int taskId)</code> removes the task identified by <code>taskId</code> from the system. It is <strong>guaranteed</strong> that <code>taskId</code> <em>exists</em> in the system.</p>
	</li>
	<li>
	<p><code>int execTop()</code> executes the task with the <strong>highest</strong> priority across all users. If there are multiple tasks with the same <strong>highest</strong> priority, execute the one with the highest <code>taskId</code>. After executing, the<strong> </strong><code>taskId</code><strong> </strong>is <strong>removed</strong> from the system. Return the <code>userId</code> associated with the executed task. If no tasks are available, return -1.</p>
	</li>
</ul>

<p><strong>Note</strong> that a user may be assigned multiple tasks.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong><br />
<span class="example-io">[&quot;TaskManager&quot;, &quot;add&quot;, &quot;edit&quot;, &quot;execTop&quot;, &quot;rmv&quot;, &quot;add&quot;, &quot;execTop&quot;]<br />
[[[[1, 101, 10], [2, 102, 20], [3, 103, 15]]], [4, 104, 5], [102, 8], [], [101], [5, 105, 15], []]</span></p>

<p><strong>Output:</strong><br />
<span class="example-io">[null, null, null, 3, null, null, 5] </span></p>

<p><strong>Explanation</strong></p>
TaskManager taskManager = new TaskManager([[1, 101, 10], [2, 102, 20], [3, 103, 15]]); // Initializes with three tasks for Users 1, 2, and 3.<br />
taskManager.add(4, 104, 5); // Adds task 104 with priority 5 for User 4.<br />
taskManager.edit(102, 8); // Updates priority of task 102 to 8.<br />
taskManager.execTop(); // return 3. Executes task 103 for User 3.<br />
taskManager.rmv(101); // Removes task 101 from the system.<br />
taskManager.add(5, 105, 15); // Adds task 105 with priority 15 for User 5.<br />
taskManager.execTop(); // return 5. Executes task 105 for User 5.</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tasks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= userId &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= taskId &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= priority &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= newPriority &lt;= 10<sup>9</sup></code></li>
	<li>At most <code>2 * 10<sup>5</sup></code> calls will be made in <strong>total</strong> to <code>add</code>, <code>edit</code>, <code>rmv</code>, and <code>execTop</code> methods.</li>
	<li>The input is generated such that <code>taskId</code> will be valid.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Map + Ordered Set

We use a hash map $\text{d}$ to store task information, where the key is the task ID and the value is a tuple $(\text{userId}, \text{priority})$ representing the user ID and the priority of the task.

We use an ordered set $\text{st}$ to store all tasks currently in the system, where each element is a tuple $(-\text{priority}, -\text{taskId})$ representing the negative priority and negative task ID. We use negative values so that the task with the highest priority and largest task ID appears first in the ordered set.

For each operation, we can process as follows:

-   **Initialization**: For each task $(\text{userId}, \text{taskId}, \text{priority})$, add it to the hash map $\text{d}$ and the ordered set $\text{st}$.
-   **Add Task**: Add the task $(\text{userId}, \text{taskId}, \text{priority})$ to the hash map $\text{d}$ and the ordered set $\text{st}$.
-   **Edit Task**: Retrieve the user ID and old priority for the given task ID from the hash map $\text{d}$, remove the old task information from the ordered set $\text{st}$, then add the new task information to both the hash map and the ordered set.
-   **Remove Task**: Retrieve the priority for the given task ID from the hash map $\text{d}$, remove the task information from the ordered set $\text{st}$, and delete the task from the hash map.
-   **Execute Top Priority Task**: If the ordered set $\text{st}$ is empty, return -1. Otherwise, take the first element from the ordered set, get the task ID, retrieve the corresponding user ID from the hash map, and remove the task from both the hash map and the ordered set. Finally, return the user ID.

For time complexity, initialization requires $O(n \log n)$ time, where $n$ is the number of initial tasks. Each add, edit, remove, and execute operation requires $O(\log m)$ time, where $m$ is the current number of tasks in the system. Since the total number of operations does not exceed $2 \times 10^5$, the overall time complexity is acceptable. The space complexity is $O(n + m)$ for storing the hash map and ordered set.

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
