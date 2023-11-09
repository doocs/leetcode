# [2590. Design a Todo List](https://leetcode.com/problems/design-a-todo-list)

[中文文档](/solution/2500-2599/2590.Design%20a%20Todo%20List/README.md)

## Description

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

## Solutions

**Solution 1: Hash Table + Sorted Set**

We use a hash table $tasks$ to record the set of tasks for each user, where the key is the user ID and the value is a sorted set sorted by the deadline of the task. In addition, we use a variable $i$ to record the current task ID.

When calling the `addTask` method, we add the task to the task set of the corresponding user and return the task ID. The time complexity of this operation is $O(\log n)$.

When calling the `getAllTasks` method, we traverse the task set of the corresponding user and add the description of the unfinished task to the result list, and then return the result list. The time complexity of this operation is $O(n)$.

When calling the `getTasksForTag` method, we traverse the task set of the corresponding user and add the description of the unfinished task to the result list, and then return the result list. The time complexity of this operation is $O(n)$.

When calling the `completeTask` method, we traverse the task set of the corresponding user and mark the task whose task ID is $taskId$ as completed. The time complexity of this operation is $(n)$.

The space complexity is $O(n)$. Where $n$ is the number of all tasks.

<!-- tabs:start -->

### **Python3**

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

### **Java**

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

### **Rust**

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
