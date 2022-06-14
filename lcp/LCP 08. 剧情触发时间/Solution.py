class Solution:
    def getTriggerTime(
        self, increase: List[List[int]], requirements: List[List[int]]
    ) -> List[int]:
        increase.insert(0, [0, 0, 0])
        m, n = len(increase), len(requirements)
        for i in range(1, m):
            for j in range(3):
                increase[i][j] += increase[i - 1][j]
        ans = [-1] * n
        for i, req in enumerate(requirements):
            left, right = 0, m
            while left < right:
                mid = (left + right) >> 1
                if all(a >= b for a, b in zip(increase[mid], req)):
                    ans[i] = mid
                    right = mid
                else:
                    left = mid + 1
        return ans
