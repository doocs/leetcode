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
