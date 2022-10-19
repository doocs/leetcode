class Solution:
    def countStudents(self, students: List[int], sandwiches: List[int]) -> int:
        cnt = Counter(students)
        for v in sandwiches:
            if cnt[v] == 0:
                return cnt[v ^ 1]
            cnt[v] -= 1
        return 0
