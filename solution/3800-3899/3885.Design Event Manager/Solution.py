class EventManager:

    def __init__(self, events: list[list[int]]):
        self.sl = SortedList()
        self.d = {}
        for eventId, priority in events:
            self.sl.add((-priority, eventId))
            self.d[eventId] = priority

    def updatePriority(self, eventId: int, newPriority: int) -> None:
        old_priority = self.d[eventId]
        self.sl.remove((-old_priority, eventId))
        self.sl.add((-newPriority, eventId))
        self.d[eventId] = newPriority

    def pollHighest(self) -> int:
        if not self.sl:
            return -1
        eventId = self.sl.pop(0)[1]
        self.d.pop(eventId)
        return eventId


# Your EventManager object will be instantiated and called as such:
# obj = EventManager(events)
# obj.updatePriority(eventId,newPriority)
# param_2 = obj.pollHighest()
