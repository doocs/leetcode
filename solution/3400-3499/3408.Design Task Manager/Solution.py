from sortedcontainers import SortedList


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
