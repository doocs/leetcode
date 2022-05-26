"""
# Definition for Employee.
class Employee:
    def __init__(self, id: int, importance: int, subordinates: List[int]):
        self.id = id
        self.importance = importance
        self.subordinates = subordinates
"""


class Solution:
    def getImportance(self, employees: List['Employee'], id: int) -> int:
        m = {emp.id: emp for emp in employees}

        def dfs(id: int) -> int:
            emp = m[id]
            s = emp.importance
            for sub in emp.subordinates:
                s += dfs(sub)
            return s

        return dfs(id)
