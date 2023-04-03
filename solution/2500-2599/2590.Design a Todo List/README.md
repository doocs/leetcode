# [2590. Design a Todo List](https://leetcode.cn/problems/design-a-todo-list)

[English Version](/solution/2500-2599/2590.Design%20a%20Todo%20List/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Design a Todo List Where users can add <strong>tasks</strong>, mark them as <strong>complete</strong>, or get a list of pending tasks. Users can also add <strong>tags</strong> to tasks and can filter the tasks by certain tags.</p>

<p>Implement the <code>TodoList</code> class:</p>

<ul>
	<li><code>TodoList()</code> Initializes the object.</li>
	<li><code>int addTask(int userId, String taskDescription, int dueDate, List&lt;String&gt; tags)</code> Adds a task for the user with the ID <code>userId</code> with a due date equal to <code>dueDate</code> and a list of tags attached to the task. The return value is the ID of the task. This ID starts at <code>1</code> and is <strong>sequentially</strong> increasing. That is, the first task&#39;s id should be <code>1</code>, the second task&#39;s id should be <code>2</code>, and so on.</li>
	<li><code>List&lt;String&gt; getAllTasks(int userId)</code> Returns a list of all the tasks not marked as complete for the user with ID <code>userId</code>, ordered by the due date. You should return an empty list if the user has no uncompleted tasks.</li>
	<li><code>List&lt;String&gt; getTasksForTag(int userId, String tag)</code> Returns a list of all the tasks that are not marked as complete for the user with the ID <code>userId</code> and have <code>tag</code> as one of their tags, ordered by their due date. Return an empty list if no such task exists.</li>
	<li><code>void completeTask(int userId, int taskId)</code> Marks the task with the ID <code>taskId</code> as completed only if the task exists and the user with the ID <code>userId</code> has this task, and it is uncompleted.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;TodoList&quot;, &quot;addTask&quot;, &quot;addTask&quot;, &quot;getAllTasks&quot;, &quot;getAllTasks&quot;, &quot;addTask&quot;, &quot;getTasksForTag&quot;, &quot;completeTask&quot;, &quot;completeTask&quot;, &quot;getTasksForTag&quot;, &quot;getAllTasks&quot;]
[[], [1, &quot;Task1&quot;, 50, []], [1, &quot;Task2&quot;, 100, [&quot;P1&quot;]], [1], [5], [1, &quot;Task3&quot;, 30, [&quot;P1&quot;]], [1, &quot;P1&quot;], [5, 1], [1, 2], [1, &quot;P1&quot;], [1]]
<strong>Output</strong>
[null, 1, 2, [&quot;Task1&quot;, &quot;Task2&quot;], [], 3, [&quot;Task3&quot;, &quot;Task2&quot;], null, null, [&quot;Task3&quot;], [&quot;Task3&quot;, &quot;Task1&quot;]]

<strong>Explanation</strong>
TodoList todoList = new TodoList();
todoList.addTask(1, &quot;Task1&quot;, 50, []); // return 1. This adds a new task for the user with id 1.
todoList.addTask(1, &quot;Task2&quot;, 100, [&quot;P1&quot;]); // return 2. This adds another task for the user with id 1.
todoList.getAllTasks(1); // return [&quot;Task1&quot;, &quot;Task2&quot;]. User 1 has two uncompleted tasks so far.
todoList.getAllTasks(5); // return []. User 5 does not have any tasks so far.
todoList.addTask(1, &quot;Task3&quot;, 30, [&quot;P1&quot;]); // return 3. This adds another task for the user with id 1.
todoList.getTasksForTag(1, &quot;P1&quot;); // return [&quot;Task3&quot;, &quot;Task2&quot;]. This returns the uncompleted tasks that have the tag &quot;P1&quot; for the user with id 1.
todoList.completeTask(5, 1); // This does nothing, since task 1 does not belong to user 5.
todoList.completeTask(1, 2); // This marks task 2 as completed.
todoList.getTasksForTag(1, &quot;P1&quot;); // return [&quot;Task3&quot;]. This returns the uncompleted tasks that have the tag &quot;P1&quot; for the user with id 1.
                                  // Notice that we did not include &quot;Task2&quot; because it is completed now.
todoList.getAllTasks(1); // return [&quot;Task3&quot;, &quot;Task1&quot;]. User 1 now has 2 uncompleted tasks.

</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= userId, taskId, dueDate &lt;= 100</code></li>
	<li><code>0 &lt;= tags.length &lt;= 100</code></li>
	<li><code>1 &lt;= taskDescription.length &lt;= 50</code></li>
	<li><code>1 &lt;= tags[i].length, tag.length &lt;= 20</code></li>
	<li>All <code>dueDate</code> values are unique.</li>
	<li>All the strings consist of lowercase and uppercase English letters and digits.</li>
	<li>At most <code>100</code> calls will be made for each method.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 有序集合**

我们使用哈希表 $tasks$ 记录每个用户的任务集合，其中键为用户 ID，值为一个有序集合，按照任务的截止日期排序。另外用一个变量 $i$ 记录当前任务的 ID。

调用 `addTask` 方法时，我们将任务添加到对应用户的任务集合中，返回任务 ID。此操作的时间复杂度为 $O(\log n)$。

调用 `getAllTasks` 方法时，我们遍历对应用户的任务集合，将未完成的任务的描述添加到结果列表中，返回结果列表。此操作的时间复杂度为 $O(n)$。

调用 `getTasksForTag` 方法时，我们遍历对应用户的任务集合，将未完成的任务的描述添加到结果列表中，返回结果列表。此操作的时间复杂度为 $O(n)$。

调用 `completeTask` 方法时，我们遍历对应用户的任务集合，将任务 ID 为 $taskId$ 的任务标记为已完成。此操作的时间复杂度为 $(n)$。

空间复杂度 $O(n)$。其中 $n$ 为所有任务的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
from sortedcontainers import SortedList


class TodoList:

    def __init__(self):
        self.i = 1
        self.tasks = defaultdict(SortedList)

    def addTask(self, userId: int, taskDescription: str, dueDate: int, tags: List[str]) -> int:
        taskId = self.i
        self.i += 1
        self.tasks[userId].add(
            [dueDate, taskDescription, set(tags), taskId, False])
        return taskId

    def getAllTasks(self, userId: int) -> List[str]:
        return [x[1] for x in self.tasks[userId] if not x[4]]

    def getTasksForTag(self, userId: int, tag: str) -> List[str]:
        return [x[1] for x in self.tasks[userId] if not x[4] and tag in x[2]]

    def completeTask(self, userId: int, taskId: int) -> None:
        for task in self.tasks[userId]:
            if task[3] == taskId:
                task[4] = True
                break


# Your TodoList object will be instantiated and called as such:
# obj = TodoList()
# param_1 = obj.addTask(userId,taskDescription,dueDate,tags)
# param_2 = obj.getAllTasks(userId)
# param_3 = obj.getTasksForTag(userId,tag)
# obj.completeTask(userId,taskId)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Task {
    int taskId;
    String taskName;
    int dueDate;
    Set<String> tags;
    boolean finish;

    public Task(int taskId, String taskName, int dueDate, Set<String> tags) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.tags = tags;
    }
}

class TodoList {
    private int i = 1;
    private Map<Integer, TreeSet<Task>> tasks = new HashMap<>();

    public TodoList() {
    }

    public int addTask(int userId, String taskDescription, int dueDate, List<String> tags) {
        Task task = new Task(i++, taskDescription, dueDate, new HashSet<>(tags));
        tasks.computeIfAbsent(userId, k -> new TreeSet<>(Comparator.comparingInt(a -> a.dueDate)))
            .add(task);
        return task.taskId;
    }

    public List<String> getAllTasks(int userId) {
        List<String> ans = new ArrayList<>();
        if (tasks.containsKey(userId)) {
            for (Task task : tasks.get(userId)) {
                if (!task.finish) {
                    ans.add(task.taskName);
                }
            }
        }
        return ans;
    }

    public List<String> getTasksForTag(int userId, String tag) {
        List<String> ans = new ArrayList<>();
        if (tasks.containsKey(userId)) {
            for (Task task : tasks.get(userId)) {
                if (task.tags.contains(tag) && !task.finish) {
                    ans.add(task.taskName);
                }
            }
        }
        return ans;
    }

    public void completeTask(int userId, int taskId) {
        if (tasks.containsKey(userId)) {
            for (Task task : tasks.get(userId)) {
                if (task.taskId == taskId) {
                    task.finish = true;
                    break;
                }
            }
        }
    }
}

/**
 * Your TodoList object will be instantiated and called as such:
 * TodoList obj = new TodoList();
 * int param_1 = obj.addTask(userId,taskDescription,dueDate,tags);
 * List<String> param_2 = obj.getAllTasks(userId);
 * List<String> param_3 = obj.getTasksForTag(userId,tag);
 * obj.completeTask(userId,taskId);
 */
```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
