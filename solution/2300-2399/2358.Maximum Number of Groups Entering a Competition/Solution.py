class Solution:
    def maximumGroups(self, grades: List[int]) -> int:
        grades.sort()
        ans = 1
        prev = [1, grades[0]]
        curr = [0, 0]
        for v in grades[1:]:
            curr[0] += 1
            curr[1] += v
            if prev[0] < curr[0] and prev[1] < curr[1]:
                prev = curr
                curr = [0, 0]
                ans += 1
        return ans
