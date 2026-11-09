class Solution:
    def countStudents(self, students: list[int], sandwiches: list[int]) -> int:
        cnt = Counter(students)
        for v in sandwiches:
            if cnt[v] == 0:
                return cnt[v ^ 1]
            cnt[v] -= 1
        return 0
