---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2590.Design%20a%20Todo%20List/README.md
tags:
    - 设计
    - 数组
    - 哈希表
    - 字符串
    - 排序
---

<!-- problem:start -->

# [2590. 设计一个待办事项清单 🔒](https://leetcode.cn/problems/design-a-todo-list)

[English Version](/solution/2500-2599/2590.Design%20a%20Todo%20List/README_EN.md)

## 题目描述

<!-- description:start -->

<p>设计一个待办事项清单，用户可以添加 <strong>任务</strong> ，标记任务为 <strong>完成状态</strong> ，或获取待办任务列表。用户还可以为任务添加 <strong>标签</strong> ，并可以按照特定标签筛选任务。</p>

<p>实现 <code>TodoList</code> 类：</p>

<ul>
	<li><code>TodoList()</code> 初始化对象。</li>
	<li><code>int addTask(int userId, String taskDescription, int dueDate, List&lt;String&gt; tags)</code> 为用户 ID 为 <code>userId</code> 的用户添加一个任务，该任务的到期日期为 <code>dueDate</code>&nbsp;，附带了一个标签列表 <code>tags</code>&nbsp;。返回值为任务的 ID 。该 ID 从 <code>1</code> 开始，<strong>依次</strong> 递增。即，第一个任务的ID应为 <code>1</code> ，第二个任务的 ID 应为 <code>2</code> ，以此类推。</li>
	<li><code>List&lt;String&gt; getAllTasks(int userId)</code> 返回未标记为完成状态的 ID 为 <code>userId</code> 的用户的所有任务列表，按照到期日期排序。如果用户没有未完成的任务，则应返回一个空列表。</li>
	<li><code>List&lt;String&gt; getTasksForTag(int userId, String tag)</code> 返回 ID 为 <code>userId</code> 的用户未标记为完成状态且具有 <code>tag</code> 标签之一的所有任务列表，按照到期日期排序。如果不存在此类任务，则返回一个空列表。</li>
	<li><code>void completeTask(int userId, int taskId)</code> 仅在任务存在且 ID 为 <code>userId</code> 的用户拥有此任务且它是未完成状态时，将 ID 为 <code>taskId</code> 的任务标记为已完成状态。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<strong>输入</strong>
["TodoList", "addTask", "addTask", "getAllTasks", "getAllTasks", "addTask", "getTasksForTag", "completeTask", "completeTask", "getTasksForTag", "getAllTasks"]
[[], [1, "Task1", 50, []], [1, "Task2", 100, ["P1"]], [1], [5], [1, "Task3", 30, ["P1"]], [1, "P1"], [5, 1], [1, 2], [1, "P1"], [1]]
<strong>输出</strong>
[null, 1, 2, ["Task1", "Task2"], [], 3, ["Task3", "Task2"], null, null, ["Task3"], ["Task3", "Task1"]]

<strong>解释</strong>
TodoList todoList = new TodoList(); 
todoList.addTask(1, "Task1", 50, []); // 返回1。为ID为1的用户添加一个新任务。 
todoList.addTask(1, "Task2", 100, ["P1"]); // 返回2。为ID为1的用户添加另一个任务，并给它添加标签“P1”。 
todoList.getAllTasks(1); // 返回["Task1", "Task2"]。用户1目前有两个未完成的任务。 
todoList.getAllTasks(5); // 返回[]。用户5目前没有任务。 
todoList.addTask(1, "Task3", 30, ["P1"]); // 返回3。为ID为1的用户添加另一个任务，并给它添加标签“P1”。 
todoList.getTasksForTag(1, "P1"); // 返回["Task3", "Task2"]。返回ID为1的用户未完成的带有“P1”标签的任务。 
todoList.completeTask(5, 1); // 不做任何操作，因为任务1不属于用户5。 
todoList.completeTask(1, 2); // 将任务2标记为已完成。 
todoList.getTasksForTag(1, "P1"); // 返回["Task3"]。返回ID为1的用户未完成的带有“P1”标签的任务。 
                                  // 注意，现在不包括 “Task2” ，因为它已经被标记为已完成。 
todoList.getAllTasks(1); // 返回["Task3", "Task1"]。用户1现在有两个未完成的任务。

</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= userId, taskId, dueDate &lt;= 100</code></li>
	<li><code>0 &lt;= tags.length &lt;= 100</code></li>
	<li><code>1 &lt;= taskDescription.length &lt;= 50</code></li>
	<li><code>1 &lt;= tags[i].length, tag.length &lt;= 20</code></li>
	<li>所有的&nbsp;<code>dueDate</code> 值都是唯一的。</li>
	<li>所有字符串都由小写和大写英文字母和数字组成。</li>
	<li>每个方法最多被调用 <code>100</code> 次。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 有序集合

我们使用哈希表 $tasks$ 记录每个用户的任务集合，其中键为用户 ID，值为一个有序集合，按照任务的截止日期排序。另外用一个变量 $i$ 记录当前任务的 ID。

调用 `addTask` 方法时，我们将任务添加到对应用户的任务集合中，返回任务 ID。此操作的时间复杂度为 $O(\log n)$。

调用 `getAllTasks` 方法时，我们遍历对应用户的任务集合，将未完成的任务的描述添加到结果列表中，返回结果列表。此操作的时间复杂度为 $O(n)$。

调用 `getTasksForTag` 方法时，我们遍历对应用户的任务集合，将未完成的任务的描述添加到结果列表中，返回结果列表。此操作的时间复杂度为 $O(n)$。

调用 `completeTask` 方法时，我们遍历对应用户的任务集合，将任务 ID 为 $taskId$ 的任务标记为已完成。此操作的时间复杂度为 $(n)$。

空间复杂度 $O(n)$。其中 $n$ 为所有任务的数量。

<!-- tabs:start -->

#### Python3

```python
from sortedcontainers import SortedList


class TodoList:
    def __init__(self):
        self.i = 1
        self.tasks = defaultdict(SortedList)

    def addTask(
        self, userId: int, taskDescription: str, dueDate: int, tags: List[str]
    ) -> int:
        taskId = self.i
        self.i += 1
        self.tasks[userId].add([dueDate, taskDescription, set(tags), taskId, False])
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

#### Java

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

#### Rust

```rust
use std::collections::{ HashMap, HashSet };

#[derive(Clone)]
struct Task {
    task_id: i32,
    description: String,
    tags: HashSet<String>,
    due_date: i32,
}

struct TodoList {
    /// The global task id
    id: i32,
    /// The mapping from `user_id` to `task`
    user_map: HashMap<i32, Vec<Task>>,
}

impl TodoList {
    fn new() -> Self {
        Self {
            id: 1,
            user_map: HashMap::new(),
        }
    }

    fn add_task(
        &mut self,
        user_id: i32,
        task_description: String,
        due_date: i32,
        tags: Vec<String>
    ) -> i32 {
        if self.user_map.contains_key(&user_id) {
            // Just add the task
            self.user_map
                .get_mut(&user_id)
                .unwrap()
                .push(Task {
                    task_id: self.id,
                    description: task_description,
                    tags: tags.into_iter().collect::<HashSet<String>>(),
                    due_date,
                });
            // Increase the global id
            self.id += 1;
            return self.id - 1;
        }
        // Otherwise, create a new user
        self.user_map.insert(
            user_id,
            vec![Task {
                task_id: self.id,
                description: task_description,
                tags: tags.into_iter().collect::<HashSet<String>>(),
                due_date,
            }]
        );
        self.id += 1;
        self.id - 1
    }

    fn get_all_tasks(&self, user_id: i32) -> Vec<String> {
        if
            !self.user_map.contains_key(&user_id) ||
            self.user_map.get(&user_id).unwrap().is_empty()
        {
            return vec![];
        }
        // Get the task vector
        let mut ret_vec = (*self.user_map.get(&user_id).unwrap()).clone();
        // Sort by due date
        ret_vec.sort_by(|lhs, rhs| { lhs.due_date.cmp(&rhs.due_date) });
        // Return the description vector
        ret_vec
            .into_iter()
            .map(|x| x.description)
            .collect()
    }

    fn get_tasks_for_tag(&self, user_id: i32, tag: String) -> Vec<String> {
        if
            !self.user_map.contains_key(&user_id) ||
            self.user_map.get(&user_id).unwrap().is_empty()
        {
            return vec![];
        }
        // Get the task vector
        let mut ret_vec = (*self.user_map.get(&user_id).unwrap()).clone();
        // Sort by due date
        ret_vec.sort_by(|lhs, rhs| { lhs.due_date.cmp(&rhs.due_date) });
        // Return the description vector
        ret_vec
            .into_iter()
            .filter(|x| x.tags.contains(&tag))
            .map(|x| x.description)
            .collect()
    }

    fn complete_task(&mut self, user_id: i32, task_id: i32) {
        if
            !self.user_map.contains_key(&user_id) ||
            self.user_map.get(&user_id).unwrap().is_empty()
        {
            return;
        }
        self.user_map
            .get_mut(&user_id)
            .unwrap()
            .retain(|x| (*x).task_id != task_id);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
