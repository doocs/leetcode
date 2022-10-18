class Solution:
    def countStudents(self, students: List[int], sandwiches: List[int]) -> int:
        cnt = Counter(students)
        for i, v in enumerate(sandwiches):
            if cnt[v] == 0:
                return len(students) - i
            cnt[v] -= 1
        return 0
